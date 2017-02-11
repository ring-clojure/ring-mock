(ns ring.mock.request
  "Functions to create mock request maps."
  (:require [clojure.string :as string]
            [ring.util.codec :as codec]))

(defn- encode-params
  "Turn a map of parameters into a urlencoded string."
  [params]
  (if params
    (codec/form-encode params)))

(defn header
  "Add a HTTP header to the request map."
  [request header value]
  (let [header (string/lower-case (name header))]
    (assoc-in request [:headers header] (str value))))

(defn cookie
  "Add a cookie to the request headers map"
  [request cookie-name value]
  (let [cookie-name (name cookie-name)
        cookie-string (str cookie-name "=" value)]
    (update-in request [:headers "cookie"]
               (fn [cookie-header]
                 (if cookie-header
                   (str cookie-header "; " cookie-string)
                   cookie-string)))))

(defn content-type
  "Set the content type of the request map."
  [request mime-type]
  (-> request
      (assoc :content-type mime-type)
      (header :content-type mime-type)))

(defn content-length
  "Set the content length of the request map."
  [request length]
  (-> request
      (assoc :content-length length)
      (header :content-length length)))

(defn- combined-query
  "Create a query string from a URI and a map of parameters."
  [request params]
  (let [query (:query-string request)]
    (if (or query params)
      (string/join "&"
        (remove string/blank?
                [query (encode-params params)])))))

(defn- merge-query
  "Merge the supplied parameters into the query string of the request."
  [request params]
  (assoc request :query-string (combined-query request params)))

(defn query-string
  "Set the query string of the request to a string or a map of parameters."
  [request params]
  (if (map? params)
    (assoc request :query-string (encode-params params))
    (assoc request :query-string params)))

(defmulti body
  "Set the body of the request. The supplied body value can be a string or
  a map of parameters to be url-encoded."
  {:arglists '([request body-value])}
  (fn [request x] (type x)))

(defmethod body String [request ^String string]
  (body request (.getBytes string)))

(defmethod body (class (byte-array 0)) [request bytes]
  (-> request
      (content-length (count bytes))
      (assoc :body (java.io.ByteArrayInputStream. bytes))))

(defmethod body java.util.Map [request params]
  (-> request
      (content-type "application/x-www-form-urlencoded")
      (body (encode-params params))))

(defmethod body nil [request params]
  request)

(def default-port
  "A map of the default ports for a scheme."
  {:http 80
   :https 443})

(defn request
  "Create a minimal valid request map from a HTTP method keyword, a string
  containing a URI, and an optional map of parameters that will be added to
  the query string of the URI. The URI can be relative or absolute. Relative
  URIs are assumed to go to http://localhost."
  ([method uri]
     (request method uri nil))
  ([method uri params]
     (let [uri    (java.net.URI. uri)
           scheme (keyword (or (.getScheme uri) "http"))
           host   (or (.getHost uri) "localhost")
           port   (when (not= (.getPort uri) -1) (.getPort uri))
           path   (.getRawPath uri)
           query  (.getRawQuery uri)
           request {:server-port    (or port (default-port scheme))
                    :server-name    host
                    :remote-addr    "localhost"
                    :uri            (if (string/blank? path) "/" path)
                    :query-string   query
                    :scheme         scheme
                    :request-method method
                    :headers        {"host" (if port
                                              (str host ":" port)
                                              host)}}]
       (if (#{:get :head :delete} method)
         (merge-query request params)
         (body request params)))))
