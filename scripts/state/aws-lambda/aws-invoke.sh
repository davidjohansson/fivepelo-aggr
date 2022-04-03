#!/bin/bash
pushd ../
aws lambda invoke --invocation-type RequestResponse --function-name fplperiod --region eu-north-1 --log-type Tail --payload fileb:///Users/johansson044/kod/fivepelo-aggr/scripts/eventstream/output/period30.json outfile.json && cat outfile.json | jq
popd
