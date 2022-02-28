var AWS = require("aws-sdk");
var fs = require('fs');

AWS.config.update({
//  region: 'eu-north-1'
   region: "local",
   endpoint: "http://localhost:8000"
});

console.log(process.argv);

var docClient = new AWS.DynamoDB.DocumentClient();
// var allTraings = JSON.parse(fs.readFileSync('../output/period1.json', 'utf8'));
 var allTraings = JSON.parse(fs.readFileSync(process.argv[2], 'utf8'));


const chunks = chunk(allTraings, 25).map(trainingChunk => {
  let map = trainingChunk.map(training => ({
    PutRequest: {
      Item: {
        "participantId": training.participantId,
        "date": training.date,
        "payload": training
      }
    }
  }));

  return {
    RequestItems: {
      "Trainings": map
    }
  }
}).map((chunk) => {
      docClient.batchWrite(chunk, function (err, data) {
        if (err) {
          console.log("Error", err);
        } else {
          console.log("Success", data);
        }
      });

    }
)
function chunk(items, size) {
  const chunks = []
  items = [].concat(...items)

  while (items.length) {
    chunks.push(
        items.splice(0, size)
    )
  }

  return chunks
}
