(ns dotnet
  "Dotnet related tasks to be called by `nostrand`.
  Nostrand uses the `magic` compiler.

  ## Motivation

  This namespace provides convenient functions to:
  - compile the prod namespaces to .net assemblies
  - run the tests in the CLR"
  (:require [nostrand.tasks :as tasks]))

(def cljs-only
  "ClojureScript-only namespaces that must not load on the CLR."
  '[clojure.test.check.clojure-test.assertions.cljs])

(defn build
  "Compiles the project to dlls.
  nos dotnet/build"
  []
  (tasks/compile-project :clean? true :exclude cljs-only))

(defn run-tests
  "Run all the tests on the CLR.
  nos dotnet/run-tests"
  []
  ;; run-clojure-tests calls run-all-tests, which sweeps every loaded
  ;; namespace (clojure.*, magic.*, nostrand.*, dependency suites), not
  ;; just this project's. :re scopes the run to our own namespaces via
  ;; re-matches (full match, hence the trailing .*). :exclude still does
  ;; a different job: it stops the CLJS-only ns from loading at all.
  (tasks/run-clojure-tests :aliases [:test]
                           :exclude cljs-only
                           :re #"clojure\.test\.check.*"))
