(ns clojure-lounge-test.views.welcome
  (:require [clojure-lounge-test.views.common :as common]inv)
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout "My Title"
           [:p "Welcome to clojure-lounge-test"]))


(defn gen-hal [href props]
  (let [resource-factory (com.theoryinpractise.halbuilder.ResourceFactory.)
          resource (doto (.newResource resource-factory href)
                         (.withLink "/home" "home"))]
    (doseq [prop props]
           (.withProperty resource (name (key prop)) (val prop)))
    (.renderContent resource "application/hal+xml")))


(defpage "/hal" []
  (gen-hal "/hal" {:name "Mark"
                   :age "old"
                   :knows "Clojure"}))
