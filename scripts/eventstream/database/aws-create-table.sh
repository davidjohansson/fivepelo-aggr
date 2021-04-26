#!/bin/bash

aws dynamodb create-table --profile db-admin \
    --table-name Training \
    --attribute-definitions \
        AttributeName=Id,AttributeType=S \
        AttributeName=Date,AttributeType=S \
    --key-schema \
        AttributeName=Id,KeyType=HASH \
        AttributeName=Date,KeyType=RANGE \
--provisioned-throughput \
        ReadCapacityUnits=10,WriteCapacityUnits=5