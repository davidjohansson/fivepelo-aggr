(ns fivepelo-aggr.stateFromEventStream-test
  (:require [clojure.test :refer :all]
            [fivepelo-aggr.stateFromEventStream :refer :all]
            [fivepelo-aggr.helpers :refer :all]
            ))

(deftest groupByWeekAndPerson-test

  (def sut (groupByWeekAndPerson (sheetData "scripts/eventstream/output/period30.json") 12))
  (def nthWeekParticipants (comp :participants #(nth %1 %2)))

  (def firstWeekParticipantsFirstTrainings (comp #(map first %) #(map :training %) #(nthWeekParticipants % 0)))

  (testing "The state constructed from the eventstream"
    ; (pprnt sut)
    (testing "should have 7 weeks"
      (is (= 7 (count sut))))

    (testing "should start with week 1"
      (is (= 1 (get (first sut) :weekOfYear))))

    (testing "should contain weeks 1 to 7"
      (is (= '(1 2 3 4 5 6 7) (map :weekOfYear sut))))

    (testing "should contain start week for each week"
      (is (= '("2021-01-04"
                "2021-01-11"
                "2021-01-18"
                "2021-01-25"
                "2021-02-01"
                "2021-02-08"
                "2021-02-15") (map :startDate sut))))

    (testing "should contain start week for each week"
      (is (= '("2021-01-10"
                "2021-01-17"
                "2021-01-24"
                "2021-01-31"
                "2021-02-07"
                "2021-02-14"
                "2021-02-21") (map :endDate sut))))

    (testing "should have the specified participants"
      (is (= '("Johan"
                "Peter"
                "Sophia"
                "Eric"
                "Johanna"
                "Lotta"
                "Malin"
                "David"
                "Tobbe") (map :name (nthWeekParticipants sut 0)))))

    (testing "should have the specified activities"
      (is (= '("Löpning 9,5 km"
                "Yoga 3 + knä-Arnold"
                "PT crossfit "
                "Löpning med Claes "
                "Yoga breath dag 3 + överkropp + core"
                "Hajk Great Falls m Henrik 2h"
                "Yoga ink huvudstående"
                "Styrka"
                "Hemmastyrka ")

             (map :activity (firstWeekParticipantsFirstTrainings sut)))))


    (testing "should have the specified dates"
      (is (= '("2021-01-04"
                "2021-01-04"
                "2021-01-04"
                "2021-01-04"
                "2021-01-04"
                "2021-01-04"
                "2021-01-04"
                "2021-01-04"
                "2021-01-05")
             (map :date (firstWeekParticipantsFirstTrainings sut))))
      )

    (testing "should have the specified participants the second week"
      (is (= '("Johan"
                "Peter"
                "Sophia"
                "Eric"
                "Johanna"
                "Lotta"
                "Malin"
                "David"
                "Tobbe") (map :name (nthWeekParticipants sut 1))))))
  )
