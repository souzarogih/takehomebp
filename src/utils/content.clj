(ns utils.content
  (:require [clojure.java.io :as io]
            [clojure.tools.logging :as log]))

(defn- get-resource-content-database
  "Função não pública que acessa os dados do arquivo content-database.edn"
  []
  (log/info "Obtendo os dados do arquivo content-database.")
  (read-string (slurp (io/resource "content-database.edn"))))

(defn get-content-database
  "Função que deve ser utilizada como acesso aos dados do arquivo"
  []
  (log/info "Processando os dado de mídia.")
  (let [content (get-resource-content-database)]
    content
    )
  )



