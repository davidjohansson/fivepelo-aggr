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
    ":team":   1 ,
    ":startdate":   "2016-07-25" ,
    ":enddate":   "2016-09-12"
  },
  ExpressionAttributeNames: {
    "#trainingdate" : "date",
  },

 KeyConditionExpression: "team = :team AND #trainingdate BETWEEN :startdate AND :enddate",
 //  KeyConditionExpression: "team = :t",
  ProjectionExpression: "payload",
  TableName: "Trainings",
  IndexName: "DateIndex"
};
docClient.query(params, function(err, data) {
  if (err) console.log(err, err.stack); // an error occurred
  else     console.log(JSON.stringify(data));           // successful response
});