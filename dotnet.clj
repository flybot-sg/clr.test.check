(ns dotnet
  "Dotnet related tasks to be called by `nostrand`.
  Nostrand uses the `magic` compiler.

  ## Motivation

  This namespace provides convenient functions to:
  - compile the prod namespaces to .net assemblies
  - run the tests in the CLR"
  (:require [clojure.test :refer [run-all-tests]]
            [magic.flags :as mflags]))

(def prod-namespaces
  '[clojure.test.check.impl
    clojure.test.check.random
    clojure.test.check.rose-tree
    clojure.test.check.results
    clojure.test.check.generators
    clojure.test.check.properties
    clojure.test.check.clojure-test
    clojure.test.check.clojure-test.assertions
    clojure.test.check])

(def test-namespaces
  '[clojure.test.check.test-specs
    clojure.test.check.results-test
    clojure.test.check.random-test
    clojure.test.check.rose-tree-test
    clojure.test.check.clojure-test-test
    clojure.test.check.test])

(defn build
  "Compiles the project to dlls.
  nos dotnet/build"
  []
  (binding [*compile-path*                  "build"
            *unchecked-math*                *warn-on-reflection*
            mflags/*strongly-typed-invokes* true
            mflags/*direct-linking*         true
            mflags/*elide-meta*             false]
    (println "Compile into DLL To : " *compile-path*)
    (doseq [ns prod-namespaces]
      (println (str "Compiling " ns))
      (compile ns))))

(defn run-tests
  "Run all the tests on the CLR.
  nos dotnet/run-tests"
  []
  (binding [*unchecked-math*                *warn-on-reflection*
            mflags/*strongly-typed-invokes* true
            mflags/*direct-linking*         true
            mflags/*elide-meta*             false]
    (doseq [ns (concat prod-namespaces test-namespaces)]
      (require ns))
    (let [{:keys [fail error]} (run-all-tests)]
      (when (or (pos? fail) (pos? error))
        (Environment/Exit 1)))))
