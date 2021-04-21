#!/bin/bash
pushd ../
aws lambda invoke --invocation-type RequestResponse --function-name fplperiod --region eu-north-1 --log-type Tail --payload fileb://test.json outfile.json && cat outfile.json | jq
popd