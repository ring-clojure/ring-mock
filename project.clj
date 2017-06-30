(defproject ring/ring-mock "0.3.1"
  :description "A library for creating mock Ring request maps"
  :url "https://github.com/ring-clojure/ring-mock"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cheshire "5.8.0"]
                 [ring/ring-codec "1.0.1"]]
  :plugins [[lein-codox "0.10.3"]]
  :codox
  {:project     {:name "Ring-Mock"}
   :output-path "codox"
   :source-uri  "https://github.com/ring-clojure/ring/blob/{version}/{filepath}#L{line}"}
  :aliases {"test-all" ["with-profile" "default:+1.6:+1.7:+1.8:+1.9" "test"]}
  :profiles
  {:1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
   :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
   :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
   :1.9 {:dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                        [ring/ring-spec "0.0.3"]]}})
