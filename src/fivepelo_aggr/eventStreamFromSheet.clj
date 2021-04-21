(ns fivepelo-aggr.eventStreamFromSheet
  (:require
    [fivepelo-aggr.helpers :as helpers]
    [java-time :as jt]
    )
  )
(defn dateFromDayNumber
  [date dayNumber]
  (jt/format "yyyy-MM-dd" (jt/plus (helpers/parseDate date) (jt/days dayNumber)))
  )

(defn createEvents
  [entry]
  (letfn [(trainingListToEvente
            [week activities]
            (map-indexed #(assoc {} :type "activity" :name (:name entry) :date (dateFromDayNumber "2016-07-25" (+ (* week 7) (+ 1 %))) :activity %2) activities)
            )]
    (flatten (map-indexed trainingListToEvente (:training entry)))
    )
  )

(defn extractTrainingEntriesFromSheetV2
  [sheet]
  (flatten (map createEvents (:trainingData sheet)))
  )

