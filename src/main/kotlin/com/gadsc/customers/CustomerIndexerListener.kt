package com.gadsc.customers

import com.gadsc.customers.dto.CustomerDTO
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class CustomerIndexerListener {
    private val logger = LoggerFactory.getLogger(javaClass)

    @SqsListener("\${aws.sqs.queue.customer-indexer}")
    fun listen(customerDTO: CustomerDTO) {
        logger.info("Consumed from sqs successfully $customerDTO")
        logger.info("Consumed from sqs successfully name ${customerDTO.name}")
    }
}
