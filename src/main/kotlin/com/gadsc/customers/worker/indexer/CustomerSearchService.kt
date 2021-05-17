package com.gadsc.customers.worker.indexer

import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service


@Service
class CustomerSearchService(
    private val customerSearchRepository: CustomerSearchRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) {
    fun createProduct(product: CustomerSearch) = customerSearchRepository.save(product)

    fun findBy(searchCustomerDTO: SearchCustomerDTO): List<CustomerSearch> {
        val rootQuery = QueryBuilders.boolQuery()
        searchCustomerDTO.toCriteria().forEach { rootQuery.must(it) }

        val nestedQueries = listOfNotNull(
            searchCustomerDTO.phone?.toQuery(),
            searchCustomerDTO.address?.toQuery(),
            searchCustomerDTO.naturalness?.toQuery(),
            searchCustomerDTO.mainDocument?.toQuery()
        )

        val nativeSearchQueryBuilder = NativeSearchQueryBuilder().withQuery(rootQuery)

        nestedQueries.forEach {
            nativeSearchQueryBuilder.withQuery(it)
        }

        return elasticsearchOperations
            .search(nativeSearchQueryBuilder.build(), CustomerSearch::class.java)
            .map { it.content }
            .toList()
    }
}
