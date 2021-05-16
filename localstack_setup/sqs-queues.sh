#!/bin/bash

set -e
set -u

echo "installing jq"
apk add jq

echo "configuring aws-cli"
aws_dir="/root/.aws"
if [[ -d "$aws_dir" ]]
then
    echo "'${aws_dir}' already exists, skipping aws configuration with dummy credentials"
else
   mkdir /root/.aws

    NewFile=aws-dummy-credentials-temp
    (
cat <<'AWSDUMMYCREDENTIALS'
[default]
AWS_ACCESS_KEY_ID = dummy
AWS_SECRET_ACCESS_KEY = dummy
AWSDUMMYCREDENTIALS
    ) > ${NewFile}
    mv aws-dummy-credentials-temp /root/.aws/credentials

    NewFile=aws-config-temp
    (
cat <<'AWSCONFIG'
[default]
region = us-east-1
AWSCONFIG
    ) > ${NewFile}
    mv aws-config-temp /root/.aws/config
fi

echo "configuring sns/sqs"
echo "==================="
LOCALSTACK_HOST=localhost
AWS_REGION=us-east-1
LOCALSTACK_DUMMY_ID=000000000000

get_all_queues() {
  aws --endpoint-url=http://${LOCALSTACK_HOST}:4566 sqs list-queues
}

create_queue() {
    local QUEUE_NAME_TO_CREATE=$1
    aws --endpoint-url=http://${LOCALSTACK_HOST}:4566 sqs create-queue --queue-name ${QUEUE_NAME_TO_CREATE}
}

get_all_topics() {
    aws --endpoint-url=http://${LOCALSTACK_HOST}:4566 sns list-topics
}

create_topic() {
    local TOPIC_NAME_TO_CREATE=$1
    aws --endpoint-url=http://${LOCALSTACK_HOST}:4566 sns create-topic --name ${TOPIC_NAME_TO_CREATE} | jq -r '.TopicArn'
}

link_queue_and_topic() {
    local TOPIC_ARN_TO_LINK=$1
    local QUEUE_ARN_TO_LINK=$2
    aws --endpoint-url=http://${LOCALSTACK_HOST}:4566 sns subscribe --topic-arn ${TOPIC_ARN_TO_LINK} --protocol sqs --attributes RawMessageDelivery=true --notification-endpoint ${QUEUE_ARN_TO_LINK}
}

guess_queue_arn_from_name() {
    local QUEUE_NAME=$1
    echo "arn:aws:sqs:${AWS_REGION}:${LOCALSTACK_DUMMY_ID}:$QUEUE_NAME"
}

get_messages_from_queue() {
  local QUEUE_URL=$1
  aws --endpoint-url=http://${LOCALSTACK_HOST}:4566 sqs receive-message --queue-url $QUEUE_URL --max-number-of-messages 40
}

if [ -n "$SNS_WITH_SQS" ]; then
	echo "Multiple SNS Topics and Queues creation requested: $SNS_WITH_SQS"
	for topic_and_queue in $(echo "$SNS_WITH_SQS" | tr ',' ' '); do
	  echo "creating topic $topic_and_queue"
		CREATED_TOPIC_ARN=$(create_topic ${topic_and_queue})
		echo "created topic: $CREATED_TOPIC_ARN"

		echo "creating queue $topic_and_queue"
		CREATED_QUEUE_URL=$(create_queue ${topic_and_queue})
		echo "created queue: $CREATED_QUEUE_URL"

		CREATED_QUEUE_ARN=$(guess_queue_arn_from_name $topic_and_queue)
		echo "created queue arn: $CREATED_QUEUE_ARN"

		LINKING_RESULT=$(link_queue_and_topic $CREATED_TOPIC_ARN $CREATED_QUEUE_ARN)

		echo "linking done:"
    echo "$LINKING_RESULT"

    echo "all messages from queue:"
    MESSAGES_IN_QUEUE=$(get_messages_from_queue $CREATED_QUEUE_URL)
    echo "$MESSAGES_IN_QUEUE"
	done
	echo "Multiple topic and queues with subscription created"
fi

echo "all topics are:"
echo "$(get_all_topics)"

echo "all queues are:"
echo "$(get_all_queues)"

