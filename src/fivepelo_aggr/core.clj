(ns fivepelo-aggr.core
  (:require
    [clojure.data.json :as json]
    [cheshire.core :refer :all]
    [java-time :as jt]
    [clojure.edn :as edn]
    [fivepelo-aggr.helpers :as helpers]
    ))


(defn weekOfYear
  [activity]
  (jt/as (helpers/parseDate (get activity :date)) :aligned-week-of-year))

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


(defn trainingEntry
  [entry]
  (:gs$cell entry)
  )

(defn betweenInclusive
  [actual from to]
  (and (>= actual from) (<= actual to))
  )


(defn extractTrainingEntriesFromSheet
  "Returns entries containing row number, colnumber and activity from the spreadsheet for one person/gs sheet
  Example output:  {:row 7, :col 6, :activity \"Arnold\"}
  Rows and cols as they appear in the spreadsheet"
  [sheet]
  (filter #(and (betweenInclusive (:col %) 2 8) (betweenInclusive (:row %) 3 9))
          (map #(assoc {} :row (edn/read-string(:row %)) :col (edn/read-string(:col %)) :activity (:inputValue %) )
               (map trainingEntry (:entry (:feed sheet)))
               )
          )
  )