'use strict';

console.log('Loading functions');

const doc = require('dynamodb-doc');

const dynamo = new doc.DynamoDB();

module.exports.functionName = (event, context, callback) => {
  console.log('Received event:', JSON.stringify(event, null, 2));
  const response = {
    statusCode: 200,
    body: JSON.stringify({
      patientId: 1,
      inFlat: 0, 
      heartRate: 100,
      random: 55
    }),
  };

  callback(null, response);

  // Use this code if you don't use the http event with the LAMBDA-PROXY integration
  // callback(null, { message: 'Go Serverless v1.0! Your function executed successfully!', event });
};

module.exports.importData = (event, context, callback) => {
  console.log('Received event:', event);
  var dictionary = {};
  dictionary["Item"] = event.queryStringParameters;
  dictionary["TableName"] = "CareCompanion";
  dynamo.putItem(dictionary);
  console.log(dynamo.getItem({"Key":{"patientId" : {
  "beac":N}}, "TableName" : "CareCompanion"}))
  const response = {
    statusCode: 200,  
     body: JSON.stringify({
           message: 'Go Serverless v1.0! Your function executed successfully!'
      }),
  };
  
  callback(null, response);
};
