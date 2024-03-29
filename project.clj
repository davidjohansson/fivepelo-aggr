(defproject fivepelo-aggr "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "0.2.6"]
                 [uswitch/lambada "0.1.2"]
                 [clojure.java-time "0.3.2"]
                 [clj-time "0.15.2"]
                 [cheshire "5.10.0"]
                 [org.junit.jupiter/junit-jupiter-api "5.8.2"]
                 ]
  :source-paths      ["src/clojure"]
  :test-paths      ["test/clojure"]
  :java-source-paths ["src/java"]
  :java-test-paths ["test/java"]
  :uberjar-name "fivepelo-aggr.jar"
  ;:main fivepelo-aggr.eventStreamFromSheet
  :main fivepelo-aggr.stateFromEventStream
  :profiles {:uberjar {:aot :all :jvm-opts ["-Dclojure.compiler.direct-linking=true -Duser.country=SE -Duser.language=sv"]}})

