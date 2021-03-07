(ns fivepelo-aggr.api
  (:require
   [clojure.data.json :as json]
   [clojure.java.io :as io]
   [fivepelo-aggr.core :as core]
   [uswitch.lambada.core :refer [deflambdafn]]))

(defn handle-event
  [event]
  (println "Got the following event: " (pr-str event))
  {:status "ok", :body (core/groupByWeekAndPerson event)})

(deflambdafn fivepelo-aggr.core.LambdaFn
  [in out ctx]
  (let [event (json/read (io/reader in) :key-fn keyword)
        res (handle-event event)]
    (with-open [w (io/writer out)]
      (json/write res w))))