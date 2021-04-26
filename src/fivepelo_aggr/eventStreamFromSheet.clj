(ns fivepelo-aggr.eventStreamFromSheet
  (:gen-class)
  (:require
    [fivepelo-aggr.helpers :as helpers]
    [java-time :as jt]
    )
  )

(defn dateFromDayNumber
  [date dayNumber]
  (jt/format "yyyy-MM-dd" (jt/plus (helpers/parseDate date) (jt/days dayNumber)))
  )

(defn padWithEmptyDays
  [listOfTrainings]
  (def newlist (flatten (conj (repeat (- 7 (count listOfTrainings)) "") listOfTrainings)))
  newlist
  )

(defn padWithEmptyWeeks
  [listOfWeeks]
  (def newlist (concat (repeat (- 7 (count listOfWeeks)) (empty '[])) listOfWeeks))
  newlist
  )

(defn idFromEntry
  [date participant]
  (str (clojure.string/replace date #"-" "") "_" (clojure.string/lower-case participant))
  )

(defn participantId
  [participantName]
  (def participants
    {
     "Peter"   :a0392991-046b-40f0-8ce5-595c761fdff6
     "Malin"   :1c861f59-647c-4543-b878-869e085ae65e
     "Johan"   :7ae50a4d-9f38-436c-ac78-3237d69644cc
     "Sophia"  :06775874-8e57-4331-a226-40d637485b69
     "Eric"    :5f126a92-b8e4-4ab7-8ac5-271e4651488c
     "Johanna" :244fa33d-4e6c-49d5-b2aa-da8f2ff1e74c
     "Lotta"   :66f32016-e302-411b-b9ce-080e0166d75b
     "David"   :d38b9d8f-c6c8-4952-acda-ce292c670ff8
     "Henrik"  :0f1a4028-6bfb-43ca-ae0c-2e71ef08a773
     "Tobbe"   :f320b482-0544-45b6-a262-541ec6976359
     "Mita"    :75a6c486-eac9-4c1e-a4a8-8b9f37a3e52b
     "Stefan"  :e9c42849-4080-4d32-8292-55cd0af25b8b
     "Carl"    :bea04582-920c-47d2-969b-a06a7d29bbc3
     "Annika"  :1a977747-afe5-4e60-8e89-6b12e777b1b2
     }
    )
  (def pn (get participants participantName))
  (and (nil? pn) (throw (IllegalArgumentException. (str "No user id mapping for name \"" participantName "\""))))
  (name pn)
  )


(defn createEvents
  [entry startDate]
  (letfn [
          (trainingListToEvent
            [week activities]

            (defn createEvent
              [dayCount activity]
              (def date (dateFromDayNumber startDate (+ (* week 7) dayCount)))
              (def participant (:name entry))
              (def partId (participantId participant))
              (assoc {} :id (idFromEntry date partId) :participantId partId :type "activity" :name participant :date date :activity (helpers/jsonClearString activity))
              )

            (map-indexed createEvent (padWithEmptyDays activities))
            )]
    (flatten (map-indexed trainingListToEvent (padWithEmptyWeeks (:training entry))))
    )
  )

(defn extractTrainingEntriesFromSheetV2
  [sheet]
  (letfn [(createEventsForDate
            [trainingData]
            (createEvents trainingData (:startDate sheet))
            )
          ]
    (flatten (map createEventsForDate (:trainingData sheet)))
    )
  )

(defn filterEmptyActivities
  [stream]
  (filter #(> (count (:activity %)) 1) stream)
  )

(defn filterByName
  [nameFilter stream]
  (filter #(= (:name %) nameFilter) stream)
  )

(defn -main
  ([file]
   ((comp helpers/pprnt helpers/cljToJson filterEmptyActivities extractTrainingEntriesFromSheetV2) (helpers/sheetData file))
   )
  ([file nameFilter]
   ;(helpers/pprnt nameFilter)
   ((comp helpers/pprnt helpers/cljToJson filterEmptyActivities #(filterByName nameFilter %) extractTrainingEntriesFromSheetV2) (helpers/sheetData file))
   )
  )
