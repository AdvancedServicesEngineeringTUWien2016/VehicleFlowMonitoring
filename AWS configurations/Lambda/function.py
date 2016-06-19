from __future__ import print_function

import boto3
import json

print('Loading function')


def lambda_handler(event, context):

        dynamo = boto3.resource('dynamodb').Table('zoneData')

        resp = dynamo.scan()

        return resp