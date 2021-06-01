exports.lambdaHandler = async (event, context) => {
  const startDate = event?.queryStringParameters?.startDate || 'NO START DATE'
  const endDate = event?.queryStringParameters?.endDate || 'NO END DATE'

  return {
    'statusCode': 200,
    'body': JSON.stringify(
        {
          startDate,
          endDate
        }
    )
  }
}