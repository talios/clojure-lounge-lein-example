(ns clojure-lounge-test.views.login
  (:require [clojure-lounge-test.views.common :as common]
            [noir.response :as resp]
            [noir.session :as session])
  (:use [noir.core]
        [hiccup.core]
        [hiccup.form-helpers]))

(pre-route "/secret*" []
  (when-not (session/get :authenticated)
    (session/flash-put! "Denied loooser!")      
    (resp/redirect "/login")))

(defpage "/logout" []
  (session/clear!)
  (resp/redirect "/login"))

(defpage [:get "/login"] []
  (let [msg (session/flash-get)]
    (common/layout "Login"
           (when msg (session/clear!)
                     [:b msg])

           [:p "Please login:"]

           (form-to [:post "/login"]
             (text-field "Username")
             (password-field "Password")
             (submit-button "Login")))))


(defn valid-credentials? [username password]
  "Check user authentication"
  (and (= username "mark")
       (= password "password")))

(defpage [:post "/login"] {:as login}

  (if (valid-credentials? (:Username login) (:Password login))

    (do
      (session/put! :authenticated "true")
      (resp/redirect "/secret/things"))

    (do
      (session/flash-put! "Denied loooser!")      
      (resp/redirect "/login"))))

(defpage "/secret/things" []
  (print "this is secret")
  (common/layout "Secret"
    [:p "Secret"]

    (form-to [:get "/logout"]
      (submit-button "Logout"))))
  