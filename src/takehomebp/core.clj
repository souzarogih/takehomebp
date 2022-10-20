(ns takehomebp.core
  (:require [services.accesscontrol :as ac]))

(defn can-access? [user type]
  (if-let [content (ac/access-control type)]
    content
    {:msg (format "Tipo de acesso não identificado - user: %s" user)}))