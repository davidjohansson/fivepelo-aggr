# fivepelo-aggr
Bygger en eventström av träningsaktiviteter utifrån ett google spreadsheet

Köra:

```
$ lein repl
user=> (require '[fivepelo-aggr.helpers :as h])
user=> (require '[fivepelo-aggr.stateFromEventStream :as sfe])
user=> (use 'fivepelo-aggr.eventStreamFromSheet :reload)
user=> ((comp h/pprnt  sfe/groupByWeekAndPerson  extractTrainingEntriesFromSheetV2) (h/testDataGs))
```

### Bygga upp eventström 

### TODO:
1. ~~Bryta upp olika delar i namespaces~~ 
2. ~~Extrahera datum från "Ställning"-sidan: TODO~~
3. ~~Extrahera namn från personsheeten: TODO~~
4. ~~Extrahera träningsaktiviteter från personsheeten~~
5. Varje del har egen aws lambda? Flytta in api-delen i varje namespace/del?

### LOG
 * 20210411: Givet en exporterad sheet från google spreadshetet som sparas som `raw-from-gs.json`, extraherat en lista av aktiviteter med rows and cols. Vi är i det här läget helt kvar i GS-världen där vi fortfarande pratar kolumner, rader, sheets o s v. Det känns som det behövs en modul  som helt och hållet jobbar med den här nivån (alltså som har kunskap om Google Spreadsheet), den kod vi redan har jobbar med att extrahera ett state som passar reactappen från en eventström som redan är färdig, d v s som innehåller datum, namn, id, aktivitet o s v. Splitta upp .core-modulen i två, en som hanterar GS och en som bygger statet utifrån eventströmmen.