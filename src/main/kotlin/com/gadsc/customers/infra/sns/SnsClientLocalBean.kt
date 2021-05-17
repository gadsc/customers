package com.gadsc.customers.infra.sns

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.amazonaws.services.sns.model.PublishRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["aws.enabled"], havingValue = "false", matchIfMissing = true)
class SnsClientLocalBean(
    @Value("\${aws.region}")
    private val awsRegion: String,
    @Value("\${aws.sns.endpoint}")
    private val serviceEndpoint: String,
    private val objectMapper: ObjectMapper
) : SnsClient {
    private val snsClient = AmazonSNSClientBuilder
        .standard()
        .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(serviceEndpoint, awsRegion))
        .build()

    override fun sendNotification(snsNotification: SnsNotification) {
        val publishRequest = PublishRequest()
            .withTopicArn(snsNotification.topicName)
            .withMessage(objectMapper.writeValueAsString(snsNotification.message))

        snsClient.publish(publishRequest)
    }
}
