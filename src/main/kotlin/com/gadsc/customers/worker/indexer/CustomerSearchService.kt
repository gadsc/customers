package com.gadsc.customers.worker.indexer

import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service


@Service
class CustomerSearchService(
    private val customerSearchRepository: CustomerSearchRepository,
    private val elasticsearchOperations: ElasticsearchOperations,
    private val customerQueryExtractors: List<CustomerQueryExtractor>
) {
    fun createProduct(product: CustomerSearch) = customerSearchRepository.save(product)

    fun findBy(searchCustomerDTO: SearchCustomerDTO): List<CustomerSearch> {
        val nativeSearchQueryBuilder = NativeSearchQueryBuilder()

        customerQueryExtractors.mapNotNull { it.extract(searchCustomerDTO) }
            .forEach {
                nativeSearchQueryBuilder.withQuery(it)
            }

        return elasticsearchOperations
            .search(nativeSearchQueryBuilder.build(), CustomerSearch::class.java)
            .map { it.content }
            .toList()
    }
}
