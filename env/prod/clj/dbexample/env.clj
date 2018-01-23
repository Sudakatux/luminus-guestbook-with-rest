(ns dbexample.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[dbexample started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[dbexample has shut down successfully]=-"))
   :middleware identity})
