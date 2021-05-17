package com.gadsc.customers.api

import com.gadsc.customers.api.dto.CustomerDTO
import com.gadsc.customers.infra.sns.SnsClient
import com.gadsc.customers.infra.sns.SnsNotification
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CustomerRemoverPublisher(
    private val snsClient: SnsClient,
    @Value("\${aws.sns.topic.customer-remover}") private val topic: String
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun publish(customerDTO: CustomerDTO): CustomerDTO {
        snsClient.sendNotification(SnsNotification(topic, customerDTO))
        logger.info("Customer to remove sent $customerDTO")

        return customerDTO
    }
}
