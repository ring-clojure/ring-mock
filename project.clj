(defproject ring-mock "0.1.5"
  :description "A library for creating mock Ring request maps"
  :url "https://github.com/ring-clojure/ring-mock"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [ring/ring-codec "1.0.0"]]
  :plugins [[codox "0.6.4"]]
  :profiles
  {:1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
   :1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}
   :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}})
