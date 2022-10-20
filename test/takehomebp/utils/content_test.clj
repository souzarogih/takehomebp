(ns takehomebp.utils.content-test
  (:require [clojure.test :refer :all]
            [utils.content :as content])
  (:import (java.lang String)
    (clojure.lang Keyword)))

(deftest content
  (testing "Teste que valida o tipo dos campos no retorno da função (get-content-database)"
   (let [content-database (content/get-content-database)
         first-part (first content-database)]
     (is (some? content-database))
     (is (-> (first content-database) coll?))
     (is (= (type (get (first content-database) :type)) Keyword))
     (is (= (type (-> first-part :type)) Keyword))
     (is (= (type (-> first-part :name)) String))
     (is (= (type (-> first-part :released-at)) String))
     (is (-> first-part map?))))

  (testing "Teste que valida se o retorno da função (get-content-database) possui os campos esperado."
    (let [content-database (content/get-content-database)]
      (is (contains? (first content-database) :type))
      (is (contains? (first content-database) :name))
      (is (contains? (first content-database) :released-at)))))