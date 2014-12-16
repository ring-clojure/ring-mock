# Ring-Mock

Ring-Mock is a library for creating [Ring][] request maps for testing
purposes.

[ring]: https://github.com/ring-clojure/ring

## Installation

Add the following development dependency to your `project.clj` file:

    [ring/ring-mock "0.1.5"]

## Documentation

* [API Documentation](https://ring-clojure.github.io/ring-mock/ring.mock.request.html)

## Example

```clojure
(ns your-app.test.core
  (:use your-app.core
        clojure.test
        ring.mock.request))

(deftest your-handler-test
  (is (= (your-handler (request :get "/doc/10"))
         {:status 200
          :headers {"content-type" "text/plain"}
          :body "Your expected result"})))
```

## License

Copyright © 2014 James Reeves

Distributed under the MIT License.
