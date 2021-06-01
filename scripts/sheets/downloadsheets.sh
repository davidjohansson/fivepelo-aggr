#!/bin/bash

while read p; do
  period=`echo $p | cut -d' ' -f1`
  fileId=`echo $p | cut -d' ' -f2`
  path="output/period$period.json"
  echo "Storing period $period to $path"
  node index.js $fileId > $path
  echo 'Sleeping 10 to spare quota...'
  sleep 20
done <./docs_alla.txt