#!/bin/bash

pushd ../../../
lein uberjar && aws lambda update-function-code --function-name fplperiod --zip-file fileb://target/fivepelo-aggr.jar
popd
