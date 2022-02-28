var AWS = require("aws-sdk");

AWS.config.update({
//  region: "eu-north-1",
  region: "local",
   endpoint: "http://localhost:8000"
});

var dynamodb = new AWS.DynamoDB();

var params = {
  TableName : "Trainings",
  KeySchema: [
    { AttributeName: "participantId", KeyType: "HASH"},  //Partition key
    { AttributeName: "date", KeyType: "RANGE" }  //Sort key
  ],
  AttributeDefinitions: [
    { AttributeName: "participantId", AttributeType: "S" },
    { AttributeName: "team", AttributeType: "N" },
    { AttributeName: "date", AttributeType: "S" }
  ],

  GlobalSecondaryIndexes: [{
    IndexName: "DateIndex",
    KeySchema: [
      {
        AttributeName: "team",
        KeyType: "HASH"
      },
      {
        AttributeName: "date",
        KeyType: "RANGE"
      }
    ],
    Projection: {
      ProjectionType: "ALL"
    },
    ProvisionedThroughput: {
      ReadCapacityUnits: 1,
      WriteCapacityUnits: 1
    }
  }],
  ProvisionedThroughput: {
    ReadCapacityUnits: 10,
    WriteCapacityUnits: 10
  }
};

dynamodb.createTable(params, function(err, data) {
  if (err) {
    console.error("Unable to create table. Error JSON:", JSON.stringify(err, null, 2));
  } else {
    console.log("Created table. Table description JSON:", JSON.stringify(data, null, 2));
  }
});