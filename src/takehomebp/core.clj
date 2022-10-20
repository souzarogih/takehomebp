(ns takehomebp.core
  (:require [utils.content :as content]
            [java-time :as time]
            [services.media :as media]
            [services.accesscontrol :as ac]))

;Usar para consulta os dados no banco
;(println "res:" (content/get-content-database))

;Usar para consulta mídia
;(println ":" (media/select-media {:media-type :course}))
;(println ":" (media/select-media {:media-type :podcast}))
;(println ":" (media/select-media {:media-type :debate}))
;(println ":" (media/select-media {:media-type :series}))
;(println ":" (media/select-media {:media-type :interview}))
;(println "\n:" (media/select-media {:media-type :patron}))
;(println ":" (media/select-media {:media-type :all}))
;(println ":" (media/select-media {:media-type :asdasd}))

;Controle de acesso do conteúdo
;(clojure.pprint/pprint (ac/access-control :patriota))
;(clojure.pprint/pprint (ac/access-control :premium))
;(clojure.pprint/pprint (ac/access-control :mecenas))
;(clojure.pprint/pprint (ac/access-control :all))
;(clojure.pprint/pprint (ac/access-control :higuinhoass))

(println "teste")


(defn can-access? [object purchase]
  (if (= (:type object) :movie)
    (and (= (:type purchase) :patriota)
         (time/before? (:subscription-start purchase)
                       (:released-at object)
                       (:subscription-end purchase)))))



;incluir try catch
;incluir docker