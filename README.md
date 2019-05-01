# Ring-Mock

[![Build Status](https://travis-ci.org/ring-clojure/ring-mock.svg?branch=master)](https://travis-ci.org/ring-clojure/ring-mock)

Ring-Mock is a library for creating [Ring][] request maps for testing
purposes.

[ring]: https://github.com/ring-clojure/ring

## Installation

Add the following development dependency to your `project.clj` file:

    [ring/ring-mock "0.4.0"]

## Documentation

* [API Documentation](https://ring-clojure.github.io/ring-mock/ring.mock.request.html)

## Example

```clojure
(ns your-app.core-test
  (:require [clojure.test :refer :all]
            [your-app.core :refer :all]
            [ring.mock.request :as mock]))

(deftest your-handler-test
  (is (= (your-handler (mock/request :get "/doc/10"))
         {:status  200
          :headers {"content-type" "text/plain"}
          :body    "Your expected result"})))
          
(deftest your-json-handler-test
  (is (= (your-handler (-> (mock/request :post "/api/endpoint")
                           (mock/json-body {:foo "bar"})))
         {:status  201
          :headers {"content-type" "application/json"}
          :body    {:key "your expected result"}})))
```

## License

Copyright © 2019 James Reeves

Distributed under the MIT License.
