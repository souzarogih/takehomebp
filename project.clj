(defproject takehomebp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clojure.java-time "0.3.2"]
                 [org.clojure/tools.logging "1.2.4"]
                 [seancorfield/next.jdbc "1.2.659"]
                 [com.layerware/hugsql-core "0.5.1"]
                 [com.layerware/hugsql-adapter-next-jdbc "0.5.1"]
                 ]
  :repl-options {:init-ns takehomebp.core})
