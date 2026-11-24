## Librerie e tecnologie (elenco non esaustivo) ##
- Java
- jdk25: per una maggior durata di vita del progetto
- RabbitMQ: message broker leggero, semplice, plug&play
- Spring-boot 4.2.0: ultima versione stabile, per una maggior durata di vita del progetto
- Spring-webflux: reactive API, per una miglior scalabilità
- r2dbc: db in memoria, per non dover fare un altro microservizio con il database
- OpenAPI: documentazione immediata con swagger, perfetto anche per testare le chiamate
- Docker Desktop, docker-compose: semplice gestione dei container dei microservizi.
- Visual Studio Code: editor lightweight
- Spring Initialzr: per avere una base di progetto da cui partire velocemente
- GitHub

## Architettura: ##
- Progetti maven distinti gestiti come monorepo
- 3 code di messaggi durable per mantenerle anche in caso di spegnimento di RabbitMQ:
  - LETTURE: per le letture dei sensori, scritta da sensors-emulator, letta da sensor-data-processor
  - PERSISTENT_DATA: per i dati da persistere a DB, scritta da sensor-data-processor e letta da sensor-data-persistence
  - COMANDI: per i comandi ai sensori *con priorità*, scritta da sensor-controller e letta da sensors-emulator
- 4 microservizi (controllore dei sensori, emulatore dei sensori, elaboratore delle letture e persistenza delle letture + api)
- Microservizi e RabbitMQ conteinerizzati con docker e raggruppati con docker-compose in modo da averli sotto la stessa rete
- 1 libreria (sensor-datatypes) per contenere gli elementi comuni ai microservizi (oggetti SensorDTO, SensorReadingDTO, SensorCommand e le costanti QueueNames con i nomi delle code)
- numero di sensori nel sistema configurabile, intervallo di schedulazione dei comandi e dell'emissione delle letture configurabile
- i container partono tutti dall'immagine eclipse-temurin:25-jre, i container di microservizi che espongono API hanno anche il comando curl disponibile all'interno del container

## Uso dell'AI ##
Ho utilizzato Microsoft 365 Copilot come acceleratore per:
- Aiutarmi con la sintassi dei comandi git e docker e con la configurazione di Visual Studio Code e RabbitMQ
- Farmi generare esempi semplificati di codice o spiegare porzioni di codice presi dalla documentazione ufficiale e dai tutorial di Spring e RabbitMQ
- Brainstorming nel valutare diverse opzioni architetturali (es: utilizzare una coda dedicata per sensore per i comandi o una coda condivisa, oppure polyrepo vs monorepo)
- Risoluzione eccezioni, per velocità ho spesso fornito al chatbot la stacktrace e porzioni di codice per identificare velocemente le cause

## il sistema ##
### sensor-controller ###
#### proprietà configurabili: ####
- sensor.command.scheduling.interval=30000 -> ogni quanti millisecondi viene mandato un comando a un sensori, se non valorizzato il default è 60 secondi
- sensor.number=5 -> quanti sensori sono presenti nel sistema, obbligatorio

#### cosa fa: ####
un task schedulato emette comandi (SensorCommand) ai sensori scrivendoli sulla coda COMANDI e assegnando a ciascuno una priorità da 1 a 3  (restart=1, stop=2, cambia intervallo di schedulazione=3), cicla sia i sensori che i comandi per coprire più casi.

### sensors-emulator ###
#### proprietà configurabili: ####
- sensor.emulator.scheduling.interval=10 -> ogni quanti secondi un sensore emette una lettura, se non valorizzato di default vale 10 secondi
- sensor.number=5 -> quanti sensori sono presenti nel sistema, obbligatorio

#### cosa fa: ####
- mantiene in memoria lo stato dei sensori ed espone un'api reactive per vedere i sensori
  -  documentazione http://localhost:8082/swagger-ui/index.html
  -  chiamata dall'interno del container curl "http://localhost:8080/sensors/all" 
- riceve i comandi (SensorCommand) dalla coda COMANDI e li esegue (cambia lo stato del sensore o cambia il suo intervallo di schedulazione e lo rischedula per applicare le modifiche) -> attenzione: il cambio di stato del sensore non ha alcun effetto, avrei voluto interrompere la schedulazione per i sensori spenti, ma non ho fatto in tempo
- un task schedulato per ogni sensore scrive le letture (SensorReadingDTO) sulla coda LETTURE, emette anche letture non valide (timestamp futuro o valori di umidità, temperatura e luce fuori dal range valido)


### sensor-data-processor ###
#### cosa fa: ####
riceve le letture (SensorReadingDTO) dalla coda LETTURE, verifica la validità dei dati (timestamp passato, valori di umidità, temperatura e luce nel range valido). I dati che non superano le verifiche vengono scartati, quelli che le superano vengono spediti nella coda PERSISTENT_DATA.

### sensor-data-persistence ###
#### cosa fa: ####
- espone le api per vedere le letture dei sensori che sono presenti a DB
  -  documentazione http://localhost:8081/swagger-ui/index.html
  -  esempi di chiamate dall'interno del container:
    -  curl "http://localhost:8080/readings/lastRead?sensorId=1"
    -  curl "http://localhost:8080/readings/historicRead?sensorId=1&tsFrom=2026-11-22T10:30:00Z"
    -  curl "http://localhost:8080/readings/all"
- riceve le letture (SensorReadingDTO) dalla coda PERSISTENT_DATA e provvede a salvarle nel DB

## con più tempo e in produzione... ##
- autenticazione su API
- gestione degli errori (inserimento, connessione)
- gestione di ack e nack sulle code
- DB "vero" e non solo in memoria
- CI/CD
- batterie test (junit, integrazione, carico)
- ottimizzazione delle immagini docker
- orchestrazione dei container
- utilizzo di un tool di AI specializzato in coding
- lombok -> non l'ho usato perché il progetto era contenuto, ma col senno di poi era meglio farlo
- RabbitMQ? -> l'ho usato da principiante, sicuramente si poteva usare meglio
