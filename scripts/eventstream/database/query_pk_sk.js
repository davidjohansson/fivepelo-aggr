var AWS = require("aws-sdk");
var fs = require('fs');

AWS.config.update({
  region: "eu-north-1",
  // region: "local",
  // endpoint: "http://localhost:8000"
});

var docClient = new AWS.DynamoDB.DocumentClient();

var params = {
  ExpressionAttributeValues: {
    ":t": process.argv[2],
    ":startdate": "2020-01-01",
    ":enddate": "2020-12-32"
  },
  ExpressionAttributeNames: {
    "#trainingdate": "date",
  },

  KeyConditionExpression: "participantId = :t AND #trainingdate BETWEEN :startdate AND :enddate",
  ProjectionExpression: "payload",
  TableName: "Trainings"
};
docClient.query(params, function (err, data) {
  if (err) {
    console.log(err, err.stack);
  }// an error occurred
  else {
    console.log(JSON.stringify(data));
  }           // successful response
});