'use strict';

console.log('Loading functions');

const doc = require('dynamodb-doc');

const dynamo = new doc.DynamoDB();

module.exports.functionName = (event, context, callback) => {
  dynamo.getItem({
    TableName: "CareCompanion",
    Key: {
      patientId: "1"
    }}
    ).promise()
  .then(item => {
    console.log(item.Item);
    const response = {
      statusCode: 200,
      body: JSON.stringify({
      patientId: 1,
      inFlat: item.Item.data.beaconconnected, 
      heartRate: item.Item.data.pulse,
      latitude: item.Item.data.latitude,
      longitude: item.Item.data.latitude
      }),
    };
  callback(null, response);
  });

};

module.exports.importData = (event, context, callback) => {
  console.log('Received event:', event);
  dynamo.putItem({
        TableName: "CareCompanion",
        Item: {
          patientId: "1",
          data: event.queryStringParameters
        }
    }).promise()
    .then(item => {
      console.log(item);
      const response = {
        statusCode: 200,  
        body: JSON.stringify({
           message: 'Go Serverless v1.0! Your function executed successfully!'
        }),
      };
      callback(null, response);
    });
};
