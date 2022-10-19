(ns services.accesscontrol
  (:require [services.media :as media]
            [clojure.tools.logging :as log]))

(defn- midia-control-all
  "Todos os usuários terão acesso a nossas séries"
  []
  (let [media-series (media/select-media {:media-type :series})
        media {:media-series media-series}]
    media))

(defn- midia-control-patriota
  "Usuários de tipo patriota terão acesso a todos os podcasts, todos os debates, e também
  a todas as entrevistas lançadas durante a validade de sua assinatura (12 meses)"
  []
  (let [media-podcast (media/select-media {:media-type :podcast})
        media-debate (media/select-media {:media-type :debate})
        media-series (media/select-media {:media-type :series})
        media-interview (media/select-media {:media-type :interview})
        media (merge {:media-podcast media-podcast
                     :media-debate media-debate
                      :media-series media-series
                      :media-interview media-interview})]
    media))

(defn- midia-control-premium
  "Usuários de tipo premium terão acesso a tudo que os patriotas têm, mais acesso a todos os cursos"
  []
  (let [media-podcast (media/select-media {:media-type :podcast})
        media-debate (media/select-media {:media-type :debate})
        media-series (media/select-media {:media-type :series})
        media-interview (media/select-media {:media-type :interview})
        media-course (media/select-media {:media-type :course})
        media (merge {:media-podcast media-podcast
                      :media-debate media-debate
                      :media-series media-series
                      :media-interview media-interview
                      :media-course media-course})]
    media))

(defn- midia-control-mecenas
  "Usuários de tipo mecenas terão acesso a tudo que os usuários premium têm, mais os
  relatórios mecenas lançados durante a validade de sua assinatura (12 meses)"
  []
  (let [media-podcast (media/select-media {:media-type :podcast})
        media-debate (media/select-media {:media-type :debate})
        media-series (media/select-media {:media-type :series})
        media-interview (media/select-media {:media-type :interview})
        media-course (media/select-media {:media-type :course})
        media-patron (media/select-media {:media-type :patron})
        media (merge {:media-podcast media-podcast
                      :media-debate media-debate
                      :media-series media-series
                      :media-interview media-interview
                      :media-course media-course
                      :media-patron media-patron})]
    media))

(defn access-control
  "Esta função serve como interface de acesso as funções de controle de mídia."
  [user-type]
  (case user-type
    :patriota (midia-control-patriota)
    :premium (midia-control-premium)
    :mecenas (midia-control-mecenas)
    :all (midia-control-all)
    (do
      false
      (log/error (format "Erro ao localizar a midia do usuário %s" user-type)))))





;Todos os usuários terão acesso a nossas séries
;Usuários de tipo patriota terão acesso a todos os podcasts, todos os debates, e também a todas as entrevistas lançadas durante a validade de sua assinatura (12 meses)
;Usuários de tipo premium terão acesso a tudo que os patriotas têm, mais acesso a todos os cursos
;Usuários de tipo mecenas terão acesso a tudo que os usuários premium têm, mais os "relatórios mecenas" lançados durante a validade de sua assinatura (12 meses)