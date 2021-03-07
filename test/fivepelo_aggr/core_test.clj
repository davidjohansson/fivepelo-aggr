(ns fivepelo-aggr.core-test
  (:require [clojure.test :refer :all]
            [fivepelo-aggr.core :refer :all]))

(deftest groupByWeekAndPerson-test
  (def sut (groupByWeekAndPerson (testData)))
  (def firstWeekParticipants (comp :participants #(first %)))
  (def firstWeekParticipantsFirstTraining (comp #(map first %) #(map :training %) #(firstWeekParticipants %)))

  (testing "A list of length 1"
    (is (= 2 (count sut)))
    (is (= 48 (get (first sut) :weekOfYear)))
    (is (= '("David Johansson" "Johanna Ljung") (map :name (firstWeekParticipants sut))))
    (is (= '("Löpning Rocklunda" "Yoga") (map :activity (firstWeekParticipantsFirstTraining sut))))
    (is (= '("20201201" "20201131") (map :date (firstWeekParticipantsFirstTraining sut))))

    (is (= 49 (get (nth sut 1) :weekOfYear)))

    )
  )
