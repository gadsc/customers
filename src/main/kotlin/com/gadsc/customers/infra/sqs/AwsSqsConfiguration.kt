package com.gadsc.customers.infra.sqs

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.handler.annotation.support.PayloadArgumentResolver
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver


@Configuration
@ConditionalOnProperty(name = ["aws.enabled"], havingValue = "false")
class AwsSqsConfiguration(

    @Value("\${aws.sqs.endpoint}")
    private val awsSqsEndpoint: String,

    @Value("\${aws.region}")
    private val awsRegion: String
) {
    @Bean
    @Primary
    fun awsSqsClient(): AmazonSQSAsync {
        return AmazonSQSAsyncClientBuilder.standard()
            .withCredentials(DefaultAWSCredentialsProviderChain())
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(awsSqsEndpoint, awsRegion))
            .build()
    }

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate? {
        return QueueMessagingTemplate(awsSqsClient())
    }
}

@Configuration
@ConditionalOnProperty(name = ["aws.enabled"], havingValue = "true")
class SqsConfiguration {

    @Bean
    fun queueMessageHandlerFactory(objectMapper: ObjectMapper): QueueMessageHandlerFactory {
        val converter = MappingJackson2MessageConverter()
        objectMapper.registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
        converter.objectMapper = objectMapper
        converter.isStrictContentTypeMatch = false
        val factory = QueueMessageHandlerFactory()
        factory.setArgumentResolvers(mutableListOf<HandlerMethodArgumentResolver>(PayloadMethodArgumentResolver(converter)))
        return factory
    }
}
