(ns fivepelo-aggr.stateFromEventStream
  (:require
    [java-time :as jt]
    [fivepelo-aggr.helpers :as helpers]
    )
  (:import Woy)
  )

(defn weekOfYear
  [activity]
  (Woy/instant (get activity :date)))

(defn weekOfYearJ
  [activity]
  (Woy/instant (helpers/parseDate (get activity :date))))

(defn weekOfYeart
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
  (map (fn [[k v]]
         (def firstDateOfWeek (Woy/firstDateOfWeek (get-in v [1 :date])))
         (def lastDateOfWeek (Woy/lastDateOfWeek (get-in v [1 :date])))
         {:weekOfYear k :startDate firstDateOfWeek :endDate lastDateOfWeek :participants v})
       themap))

(defn groupByWeekAndPerson
  [eventStream periodId]
  (def weeks (groupByPerson (groupByWeek eventStream)) ))
  {:period periodId :weeks weeks}

(defn -main
  []
  ((comp helpers/pprnt helpers/cljToJson groupByWeekAndPerson) helpers/testDataEventStream 30))