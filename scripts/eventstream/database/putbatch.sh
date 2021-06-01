#!/bin/bash
FILES=../output/*
for f in $FILES
do
  filename=`basename $f`
  echo "Processing $f file"
   node put_batch.js $f
done
