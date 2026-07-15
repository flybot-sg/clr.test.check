# clr.test.check

> [!WARNING]
> **Deprecated: use the upstream [`clojure/clr.test.check`](https://github.com/clojure/clr.test.check) (David Miller's ClojureCLR port) directly.**
>
> This fork only existed while MAGIC needed a dedicated build harness here. MAGIC now builds the unmodified upstream library from a small `magic.edn`, so there is nothing to fork. That build and test run is exercised continuously against upstream by [flybot-sg/magic-conformance](https://github.com/flybot-sg/magic-conformance). This repo is kept for reference only.

[![test](https://github.com/flybot-sg/clr.test.check/actions/workflows/test.yml/badge.svg?branch=magic)](https://github.com/flybot-sg/clr.test.check/actions/workflows/test.yml?query=branch%3Amagic)
[![MAGIC](https://img.shields.io/badge/MAGIC-v0.10.0-blue?logo=githubactions&logoColor=white)](https://github.com/flybot-sg/magic/blob/v0.10.0/CHANGELOG.md)
[![ci-clj-clr](https://img.shields.io/badge/ci--clj--clr-2.5.0-lightgrey)](https://github.com/flybot-sg/ci-clj-clr/releases/tag/v2.5.0)

A port of [clojure/test/check](https://github.com/clojure/test.check) library to ClojureCLR.

From the original's README:

> _test.check_ is a Clojure property-based testing tool inspired by QuickCheck. The core idea of _test.check_ is that instead of enumerating expected input and output for unit tests, you write properties about your function that should hold true for all inputs. This lets you write concise, powerful tests

## About this fork

A flybot-sg fork of [clojure/clr.test.check](https://github.com/clojure/clr.test.check) (David Miller's repo) that runs the suite under [Magic](https://github.com/flybot-sg/magic) via Nostrand.

- `master` is a clean mirror of upstream. Fork-specific changes never land here, so upstream's `master` can always be fast-forwarded in.
- The [`magic`](../../tree/magic) branch adds a Nostrand/Magic build harness and GitHub Actions CI on top of upstream. The `src/` tree is identical to upstream, with no source patches needed against MAGIC [`v0.10.0`](https://github.com/flybot-sg/magic/releases/tag/v0.10.0).

## Magic / Nostrand build (`magic` branch)

Build via [Nostrand](https://github.com/flybot-sg/magic#nostrand):

```bash
bb clr-test    # run the test suite on CLR
bb clr-build   # compile production namespaces to .dll
```

CI runs `bb clr-test` on push and PR against `magic` under the pinned `ghcr.io/flybot-sg/ci-clj-clr:2.5.0` image, which bundles **MAGIC `v0.10.0`**. See [.github/workflows/test.yml](.github/workflows/test.yml).

## License

Original:

> Copyright © Rich Hickey, Reid Draper and contributors

Distributed under the Eclipse Public License, the same as Clojure.