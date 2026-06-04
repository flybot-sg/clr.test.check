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
  (tasks/compile-project :exclude cljs-only))

(defn run-tests
  "Run all the tests on the CLR.
  nos dotnet/run-tests"
  []
  (tasks/run-clojure-tests :aliases [:test] :exclude cljs-only))
