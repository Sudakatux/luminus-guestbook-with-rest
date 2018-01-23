(ns dbexample.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [dbexample.layout :refer [error-page]]
            [dbexample.routes.home :refer [home-routes]]
            [dbexample.routes.services :refer [service-routes]]
            [compojure.route :as route]
            [dbexample.env :refer [defaults]]
            [mount.core :as mount]
            [dbexample.middleware :as middleware]))

(mount/defstate init-app
  :start ((or (:init defaults) identity))
  :stop  ((or (:stop defaults) identity)))

(mount/defstate app
  :start
  (middleware/wrap-base
    (routes
      (-> #'home-routes
          (wrap-routes middleware/wrap-csrf)
          (wrap-routes middleware/wrap-formats))
          #'service-routes
      (route/not-found
        (:body
          (error-page {:status 404
                       :title "page not found"}))))))
