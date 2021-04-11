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

### Bygga upp  eventström 
1. Extrahera datum från "Ställning"-sidan: TODO
2. Extrahera namn från personsheeten: TODO   
2. Extrahera träningsaktiviteter från personsheeten:
```
user=> (h/pprnt (extractTrainingEntriesFromSheet (h/testDataGs)))
```