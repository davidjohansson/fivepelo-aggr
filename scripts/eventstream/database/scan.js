var AWS = require("aws-sdk");

AWS.config.update({
  //  region: "eu-north-1",

  region: "local",
   endpoint: "http://localhost:8000"
});

var docClient = new AWS.DynamoDB.DocumentClient();

var params = {
  TableName: "Trainings",
  ProjectionExpression: "payload",
  FilterExpression: "#trainingdate between :startdate and :enddate",
  ExpressionAttributeValues: {
    ":startdate":   "2016-08-27" ,
    ":enddate":   "2016-08-30"
  },
  ExpressionAttributeNames: {
    "#trainingdate" : "date",
  },
};

console.log("Scanning Traings table.");
docClient.scan(params, onScan);

function onScan(err, data) {
  if (err) {
    console.error("Unable to scan the table. Error JSON:", JSON.stringify(err, null, 2));
  } else {
    // print all the movies
    console.log("Scan succeeded.");
    data.Items.forEach(function(training) {
      console.log(JSON.stringify(training))
      // console.log(
      //     training.name + ": ",
      //     training.date, "-activity :", training.actvity);
    });

    // continue scanning if we have more training, because
    // scan can retrieve a maximum of 1MB of data
    if (typeof data.LastEvaluatedKey != "undefined") {
      console.log("Scanning for more...");
      params.ExclusiveStartKey = data.LastEvaluatedKey;
      docClient.scan(params, onScan);
    }
  }
}
