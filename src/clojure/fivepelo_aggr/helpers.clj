(ns fivepelo-aggr.helpers
  (:gen-class)
  (:require
    [clojure.data.json :as json]
    [cheshire.core :refer :all]
    [java-time :as jt]
    [clj-time.core :as t]
    [clj-time.coerce :as c]
    ))

(defn parseDate
  [date]
  (jt/local-date "yyyy-MM-dd" date))


(defn epoch
  [date]
  (clj-time.coerce/to-long date))



;Helpers
(defn jsonToClj [json]
  (json/read-str json :key-fn keyword))

(defn cljToJson [clj]
  ;(generate-string clj {:pretty true}))
  (generate-string clj))

(defn testData
  "Generate test data from the test.json file"
  []
  (jsonToClj (slurp "test.json")))

(defn sheetData
  "Generate test data from the provider .json file"
  [file]
  (jsonToClj (slurp file)))

(defn testDataGs
  "Generate test data from the raw-from.gs.json file"
  []
  (sheetData "raw-from-gs.json"))

(def testDataEventStream (sheetData "scripts/eventstream/output/period30.json"))

(def a {:participants [{:type     "activity",
                        :name     "David Johansson",
                        :id       "23ekjlkf22",
                        :date     "20201201",
                        :activity "Löpning Rocklunda"}
                       {:type     "activity",
                        :name     "Johanna Ljung",
                        :id       "djk22lad2",
                        :date     "20201201",
                        :activity "Yoga m adrienned"}]})

(defn jsonClearString
  [str]
  (clojure.string/replace str #"\"" "")
  )

(defn pprnt
  ""
  [o]
  (clojure.pprint/pprint o))
