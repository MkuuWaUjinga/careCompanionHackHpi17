'use strict';

console.log('Loading functions');

const doc = require('dynamodb-doc');

const dynamo = new doc.DynamoDB();

module.exports.functionName = (event, context, callback) => {
	console.log('Received event:', JSON.stringify(event, null, 2));
  const response = {
    statusCode: 200,
    body: JSON.stringify({
      message: 'Go Serverless v1.0! Your function executed successfully!'
    }),
  };

  callback(null, response);

  // Use this code if you don't use the http event with the LAMBDA-PROXY integration
  // callback(null, { message: 'Go Serverless v1.0! Your function executed successfully!', event });
};

module.exports.importData = (event, context, callback) => {
	console.log('Received event:', JSON.stringify(event, null, 2))
	//dynamo.putItem(JSON.parse(event.body), done);
	const response = {
		statusCode: 200, 
		 body: JSON.stringify({
      	   message: 'Go Serverless v1.0! Your function executed successfully!'
    	}),
	};
	
	callback(null, response);
};
