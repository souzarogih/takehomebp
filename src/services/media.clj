(ns services.media
  (:require [utils.content :as content]
            [clojure.tools.logging :as log]))

(defmulti select-media
          "Este multi método deve ser usado para consultas as mídias disponíveis na plataforma. "
          (fn [type] (:media-type type)))

(defmethod select-media :course
  [type]
  (log/info "Selecionando as mídias do tipo course")
  (let [content (content/get-content-database)
        podcast-content (filter #(and (:type %) (= (:type %) :course)) content)]
    podcast-content)
  )

(defmethod select-media :podcast
  [type]
  (log/info "Selecionando as mídias do tipo podcast")
  (let [content (content/get-content-database)
        podcast-content (filter #(and (:type %) (= (:type %) :podcast)) content)]
    podcast-content)
  )

(defmethod select-media :debate
  [type]
  (log/info "Selecionando as mídias do tipo debate")
  (let [content (content/get-content-database)
        podcast-content (filter #(and (:type %) (= (:type %) :debate)) content)]
    podcast-content)
  )

(defmethod select-media :interview
  [type]
  (log/info "Selecionando as mídias do tipo interview")
  (let [content (content/get-content-database)
        podcast-content (filter #(and (:type %) (= (:type %) :interview)) content)]
    podcast-content)
  )

(defmethod select-media :series
  [type]
  (log/info "Selecionando as mídias do tipo series")
  (let [content (content/get-content-database)
        podcast-content (filter #(and (:type %) (= (:type %) :series)) content)]
    podcast-content)
  )

(defmethod select-media :patron
  [type]
  (log/info "Selecionando as mídias do tipo patron")
  (let [content (content/get-content-database)
        ;_ (println "cont:" content)
        podcast-content (filter #(and (:type %) (= (:type %) :patron)) content)]
    podcast-content)
  )

(defmethod select-media :all
  [type]
  (log/info "Selecionando todas as mídias da plataforma")
  (let [content (content/get-content-database)
        podcast-content (filter #(:type %) content)]
    podcast-content))

(defmethod select-media :default
  [type]
  (log/error (format "Erro ao consulta mídia %s" type))
  nil)