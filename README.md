# fivepelo-aggr
Bygger en eventström av träningsaktiviteter utifrån ett google spreadsheet

Köra:

```
$ lein repl
user=> (require '[fivepelo-aggr.helpers :as h])
```

Nu kan man använda funktioner från helpers med `h` som prefix, tex

```
user=> (h/testData)
```

För att ladda namespace, t ex core:

```
user=> (use 'fivepelo-aggr.core :reload)
```

### Bygga upp en eventström av träningsaktiviteter med datum och deltagare

#### Extrahera träningsaktiviteter:

```
user=> (h/pprnt (extractTrainingEntriesFromSheet (h/testDataGs)))
```