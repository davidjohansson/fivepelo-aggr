var AWS = require("aws-sdk");
var fs = require('fs');

AWS.config.update({
  region: 'eu-north-1'
  // region: "local",
  // endpoint: "http://localhost:8000"
});

var docClient = new AWS.DynamoDB.DocumentClient();

console.log("Importing trainings into DynamoDB. Please wait.");

var allMovies = JSON.parse(fs.readFileSync('../output/period1.json', 'utf8'));
allMovies.forEach(function(training) {
  var params = {
    TableName: "Trainings2",
    Item: {
      "team":  training.team,
      "date": training.date,
      "payload":  training
    }
  };

  docClient.put(params, function(err, data) {
    function formatTraining(training) {
      return `${training.name}, ${training.date}`;
    }

    if (err) {
      console.error("Unable to add training", formatTraining( training ), ". Error JSON:", JSON.stringify(err, null, 2));
    } else {
      console.log("PutItem succeeded:", formatTraining( training ));
    }
  });
});