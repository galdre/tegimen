(ns tegimen
  (:require [clojure.java.shell :as shell]
            [backtick :refer [template-fn]]))

(defn parse-to-cmd
  [form]
  (->> form
       (map pr-str)
       (interpose " ")
       (apply str)
       (pr-str)
       (read-string)))

(defmacro show-command
  [& body]
  `(let [body# ~(template-fn body)]
     (-> body# parse-to-cmd)))

(defn sh*
  [cmd]
  (let [result (shell/sh "bash" "-c" cmd)]
    (if-let [err (not-empty (:err result))]
      (throw (Exception. err))
      (:out result))))

(defmacro sh
  "Just type the bash command as you would normally (no bash comments though!)."
  [& body]
  `(let [body# ~(template-fn body)]
     (-> body# parse-to-cmd sh*)))

