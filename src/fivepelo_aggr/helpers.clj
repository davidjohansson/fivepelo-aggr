(ns fivepelo-aggr.helpers
  (:require
    [clojure.data.json :as json]
    [cheshire.core :refer :all]
    [java-time :as jt]))

(defn parseDate
  [date]
  (jt/local-date "yyyy-MM-dd" date))

;Helpers
(defn jsonToClj [json]
  (json/read-str json :key-fn keyword))

(defn cljToJson [clj]
  (generate-string clj {:pretty true}))

(defn testData
  "Generate test data from the test.json file"
  []
  (jsonToClj (slurp "test.json")))

(defn testDataGs
  "Generate test data from the raw-from.gs.json file"
  []
  (jsonToClj (slurp "raw-from-gs.json")))

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

(defn pprnt
  ""
  [o]
  (clojure.pprint/pprint o))
