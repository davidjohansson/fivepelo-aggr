#!/bin/bash
pushd ../


#curl -X POST https://kqhdqjuonc.execute-api.eu-north-1.amazonaws.com/default/fplperiod \
#  --data-binary "@test.json"

curl -X POST https://ni0ptbls43.execute-api.eu-north-1.amazonaws.com/default/fplperiod \
  --data-binary "@test.json"



 popd