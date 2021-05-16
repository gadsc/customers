package com.gadsc.customers.worker.indexer

import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.stereotype.Service


@Service
class CustomerSearchService(
    private val customerSearchRepository: CustomerSearchRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) {
    fun createProduct(product: CustomerSearch) = customerSearchRepository.save(product)

    fun findByName(name: String): List<CustomerSearch> = customerSearchRepository.findByName(name)
    fun findByNameOrCategory(query: String): List<CustomerSearch> = customerSearchRepository.findByNameOrEmail(query, query)

    fun findBy() {
        val criteria = Criteria()
    }

    fun findByNameContaining(name: String): List<CustomerSearch> = customerSearchRepository.findByNameContaining(name)

//    fun findByManufacturerAndCategory(manufacturer: String, category: String): List<CustomerSearch> =
//        customerSearchRepository.findByManufacturerAndCategory(manufacturer, category)
}
