#!/usr/bin/env bash
lein run $1 $2 | sed 's/\\"/"/g' | sed 's/\"\[/[/g' | sed 's/\]\"/]/g'