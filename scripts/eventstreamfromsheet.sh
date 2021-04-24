#!/usr/bin/env bash
#pushd  >/dev/null ..
 lein run $1 $2 | sed 's/\\"/"/g' | sed 's/\"\[/[/g' | sed 's/\]\"/]/g'
#popd >/dev/null