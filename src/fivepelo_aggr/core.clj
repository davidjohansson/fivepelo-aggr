(ns fivepelo-aggr.core
  (:require
   [clojure.data.json :as json]
   [cheshire.core :refer :all]
   [java-time :as jt]))

(defn parseDate
  [date]
  (jt/local-date "yyyyMMdd" date))

(defn weekOfYear
  [activity]
  (jt/as (parseDate (get activity :date)) :aligned-week-of-year))

(defn groupByPersonInternal
  [entry]
  (def themap (group-by :name (get entry :participants)))
  (map (fn [[k v]] {:name k :training v}) themap))

(defn groupByPerson
  [aListOfMaps]
  (map #(assoc % :participants (groupByPersonInternal %)) aListOfMaps))

(defn groupByWeek
  [eventStream]
  (def themap (group-by weekOfYear eventStream))
  (map (fn [[k v]] {:weekOfYear k :participants v}) themap))

(defn groupByWeekAndPerson
  [eventStream]
  (groupByPerson (groupByWeek eventStream)))

;Helpers
(defn jsonToClj [json]
  (json/read-str json :key-fn keyword))

(defn cljToJson [clj]
  (generate-string clj {:pretty true}))

(defn testData
  "Generate test data from the test.json file"
  []
  (jsonToClj (slurp "test.json")))

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

(defn pp
  ""
  [o]
  (clojure.pprint/pprint o))
