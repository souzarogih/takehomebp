(ns takehomebp.services.media_test
  (:require [clojure.test :refer :all]
            [services.media :as media])
  (:import (java.lang String)
           (clojure.lang Keyword)))

(def fixture
  {:type        :series,
   :name        "The Witcher - Podcast Cultura Paralela #9",
   :released-at "2020-03-29T20:02:34.369"})

(deftest media
  (testing "Teste que valida a função (select-media) do tipo de midia podcast."
    (let [media-podcast (media/select-media {:media-type :podcast})
          first-part (first media-podcast)]
      (is (some? media-podcast))
      (is (-> (first media-podcast) coll?))
      (is (= (type (get first-part :type)) Keyword))
      (is (= (type (-> first-part :type)) Keyword))
      (is (= (type (-> first-part :name)) String))
      (is (= (type (-> first-part :released-at)) String))
      (is (-> first-part map?))
      (is (contains? first-part :type))
      (is (contains? first-part :name))
      (is (contains? first-part :released-at))))

  (testing "Teste que valida a função (select-media) do tipo de midia series."
    (let [media-series (media/select-media {:media-type :series})
          first-part (first media-series)]
      (is (some? media-series))
      (is (-> (first media-series) coll?))
      (is (= (type (get first-part :type)) Keyword))
      (is (= (type (-> first-part :type)) Keyword))
      (is (= (type (-> first-part :name)) String))
      (is (= (type (-> first-part :released-at)) String))
      (is (-> first-part map?))
      (is (contains? first-part :type))
      (is (contains? first-part :name))
      (is (contains? first-part :released-at))))

  (testing "Teste que valida os dados da função (get-content-database) mokada."
    (with-redefs [media/select-media (fn [& _] {:type        :series,
                                                :name        "The Witcher - Podcast Cultura Paralela #9",
                                                :released-at "2020-03-29T20:02:34.369"}) ]
     (let [media-series (media/select-media {:media-type :series})]
       (are [value expected] (= expected value)
                             (get media-series :type) :series
                             (get media-series :name) "The Witcher - Podcast Cultura Paralela #9"
                             (get media-series :released-at) "2020-03-29T20:02:34.369"
                             media-series fixture)))))