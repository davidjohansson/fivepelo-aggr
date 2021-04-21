(ns fivepelo-aggr.stateFromEventStream
  (:require
    [java-time :as jt]
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

