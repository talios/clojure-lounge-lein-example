(ns clojure-lounge-test.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [title & content]
            (html5
              [:head
               [:title title]
               (include-css "/css/reset.css")]
              [:body
               [:div#wrapper
                content]]))
