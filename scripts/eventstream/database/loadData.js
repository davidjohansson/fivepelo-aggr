var AWS = require("aws-sdk");
var fs = require('fs');

AWS.config.update({
  region: "local",
  endpoint: "http://localhost:8000"
});

var docClient = new AWS.DynamoDB.DocumentClient();

console.log("Importing trainings into DynamoDB. Please wait.");

var allMovies = JSON.parse(fs.readFileSync('../output/period1.json', 'utf8'));
allMovies.forEach(function(training) {
  var params = {
    TableName: "Trainings",
    Item: {
      "id":  training.id,
      "date": training.date,
      "payload":  training
    }
  };

  docClient.put(params, function(err, data) {
    if (err) {
      console.error("Unable to add training", training.id, ". Error JSON:", JSON.stringify(err, null, 2));
    } else {
      console.log("PutItem succeeded:", training.id);
    }
  });
});