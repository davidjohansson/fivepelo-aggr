#!/bin/bash
FILES=../sheets/output/*
for f in $FILES
do
  filename=`basename $f`
  echo "Processing $f file"
   ./eventstreamfromsheet.sh scripts/sheets/output/$filename > output/$filename
done
