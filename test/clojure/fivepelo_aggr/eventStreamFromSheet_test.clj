(ns fivepelo-aggr.eventStreamFromSheet_test
  (:require [clojure.test :refer :all]
            [fivepelo-aggr.eventStreamFromSheet :refer :all]
            [fivepelo-aggr.helpers :refer :all]
            ))

(deftest eventStreamFromSheet
  (def sut (extractTrainingEntriesFromSheetV2 (testDataGs)))
  (def startDate  (:startDate (testDataGs)))

  (pprnt startDate)
  (pprnt (nth sut 0))


  (testing "All training participants should have 7 * 7 trainings in total"
    (is (every? #(= % 49) (map #(count %) (vals (group-by :name sut)))))
  )

  (testing "First event date should match start date"
    (is (= startDate (:date (nth sut 0)) )))

  )