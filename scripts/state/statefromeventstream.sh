#!/usr/bin/env bash
#lein run kommer köra den clojurefil som finns specad under :main i project.clj
lein run $1 $2 | sed 's/\\"/"/g' | sed 's/\"\[/[/g' | sed 's/\]\"/]/g' | sed 's/\"{/{/g' | sed 's/}\"/}/g'
