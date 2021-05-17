package com.gadsc.customers.infra.sns

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.amazonaws.services.sns.model.PublishRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["aws.enabled"], havingValue = "true")
class SnsClientBean(
    @Value("\${aws.region}")
    private val awsRegion: String,
    private val objectMapper: ObjectMapper
) : SnsClient {
    private val awsSnsClient: AmazonSNS = AmazonSNSClientBuilder
        .standard()
        .withRegion(awsRegion)
        .withCredentials(DefaultAWSCredentialsProviderChain())
        .build()

    override fun sendNotification(snsNotification: SnsNotification) {
        val publishRequest = PublishRequest()
            .withTopicArn(snsNotification.topicName)
            .withMessage(objectMapper.writeValueAsString(snsNotification.message))

        awsSnsClient.publish(publishRequest)
    }
}
