package com.gadsc.customers.searcher.indexer

import com.gadsc.customers.api.dto.CustomerDTO
import com.gadsc.customers.searcher.model.SearchableCustomer
import com.gadsc.customers.searcher.service.CustomerSearchService
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class CustomerIndexerListener(
    private val customerSearchService: CustomerSearchService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @SqsListener("\${aws.sqs.queue.customer-indexer}")
    fun listen(customerDTO: CustomerDTO) {
        logger.info("Consumed from sqs successfully $customerDTO")

        customerSearchService.createCustomer(SearchableCustomer.from(customerDTO))
    }
}
