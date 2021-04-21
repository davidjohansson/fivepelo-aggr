(ns fivepelo-aggr.stateFromEventStream_test
  (:require [clojure.test :refer :all]
            [fivepelo-aggr.stateFromEventStream :refer :all]
            [fivepelo-aggr.helpers :refer :all]
            ))

(deftest groupByWeekAndPerson-test
  (def sut (groupByWeekAndPerson (testData)))
  (def nthWeekParticipants (comp :participants #(nth %1 %2)))

  (def firstWeekParticipantsFirstTrainings (comp #(map first %) #(map :training %) #(nthWeekParticipants % 0)))

  (testing "A list of length 1"
    (is (= 2 (count sut)))

    (is (= 48 (get (first sut) :weekOfYear)))
    (is (= '("David Johansson" "Johanna Ljung") (map :name (nthWeekParticipants sut 0))))
    (is (= '("Löpning Rocklunda" "Yoga") (map :activity (firstWeekParticipantsFirstTrainings sut))))
    (is (= '("20201201" "20201131") (map :date (firstWeekParticipantsFirstTrainings sut))))

    (is (= 49 (get (nth sut 1) :weekOfYear)))
    (is (= '("David Johansson" "Johanna Ljung" "Peter") (map :name (nthWeekParticipants sut 1))))
    )
  )
