(defproject ring/ring-mock "0.3.2"
  :description "A library for creating mock Ring request maps"
  :url "https://github.com/ring-clojure/ring-mock"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [cheshire "5.8.1"]
                 [ring/ring-codec "1.1.1"]]
  :plugins [[lein-codox "0.10.3"]]
  :codox
  {:project     {:name "Ring-Mock"}
   :output-path "codox"
   :source-uri  "https://github.com/ring-clojure/ring-mock/blob/{version}/{filepath}#L{line}"}
  :aliases {"test-all" ["with-profile" "default:+1.8:+1.9:+1.10" "test"]}
  :profiles
  {:1.8  {:dependencies [[org.clojure/clojure "1.8.0"]]}
   :1.9  {:dependencies [[org.clojure/clojure "1.9.0"]
                         [ring/ring-spec "0.0.4"]]}
   :1.10 {:dependencies [[org.clojure/clojure "1.10.0"]
                         [ring/ring-spec "0.0.4"]]}})
