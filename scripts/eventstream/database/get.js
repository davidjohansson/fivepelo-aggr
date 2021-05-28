var AWS = require("aws-sdk");

AWS.config.update({
  region: "local",
  endpoint: "http://localhost:8000"
});

var docClient = new AWS.DynamoDB.DocumentClient();

var params = {
  TableName: 'Trainings',
  Key:{
    "id": '20160727_66f32016-e302-411b-b9ce-080e0166d75b',
    "date": '2016-07-27'
  }
};

docClient.get(params, function(err, data) {
  if (err) {
    console.error("Unable to read item. Error JSON:", JSON.stringify(err, null, 2));
  } else {
    console.log("GetItem succeeded:", JSON.stringify(data, null, 2));
  }
});