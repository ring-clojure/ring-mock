(defproject ring/ring-mock "0.6.1"
  :description "A library for creating mock Ring request maps"
  :url "https://github.com/ring-clojure/ring-mock"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [cheshire "6.0.0"]
                 [org.apache.httpcomponents.client5/httpclient5 "5.5"]
                 [ring/ring-codec "1.3.0"]
                 [ring/ring-core "1.14.2"]]
  :plugins [[lein-codox "0.10.8"]]
  :codox
  {:project     {:name "Ring-Mock"}
   :output-path "codox"
   :source-uri  "https://github.com/ring-clojure/ring-mock/blob/{version}/{filepath}#L{line}"}
  :aliases {"test-all" ["with-profile" "default:+1.10:+1.11:+1.12" "test"]}
  :profiles
  {:1.10 {:dependencies [[org.clojure/clojure "1.10.0"]
                         [ring/ring-spec "0.0.4"]]}
   :1.11 {:dependencies [[org.clojure/clojure "1.11.0"]
                         [ring/ring-spec "0.0.4"]]}
   :1.12 {:dependencies [[org.clojure/clojure "1.12.0"]
                         [ring/ring-spec "0.0.4"]]}})
