(ns clojure-lounge-test.views.welcome
  (:require [clojure-lounge-test.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to clojure-lounge-test"]))

(defpage "/hal" []
  (let [resource-factory (com.theoryinpractise.halbuilder.ResourceFactory.)
                resource (doto (.newResource resource-factory "/foo")
                               (.withLink "/home" "home")
                               (.withProperty "name" "Mark" ))]
    (.renderContent resource "application/hal+xml")))
