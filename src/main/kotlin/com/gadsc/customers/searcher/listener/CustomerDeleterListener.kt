package com.gadsc.customers.searcher.listener

import com.gadsc.customers.api.dto.CustomerDTO
import com.gadsc.customers.searcher.model.SearchableCustomer
import com.gadsc.customers.searcher.service.CustomerSearchService
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class CustomerDeleterListener(
    private val customerSearchService: CustomerSearchService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @SqsListener("\${aws.sqs.queue.customer-remover}")
    fun listen(customerDTO: CustomerDTO) {
        logger.info("Consumed from sqs successfully ${customerDTO.id} to remove")

        customerSearchService.delete(SearchableCustomer.from(customerDTO))
    }
}
