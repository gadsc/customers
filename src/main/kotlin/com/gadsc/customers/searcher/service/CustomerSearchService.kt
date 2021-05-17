package com.gadsc.customers.searcher.service

import com.gadsc.customers.searcher.model.SearchableCustomer
import com.gadsc.customers.searcher.query.extractor.CustomerQueryExtractor
import com.gadsc.customers.searcher.repository.CustomerSearchRepository
import com.gadsc.customers.searcher.query.dto.CustomerQueryDTO
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service


@Service
class CustomerSearchService(
    private val customerSearchRepository: CustomerSearchRepository,
    private val elasticsearchOperations: ElasticsearchOperations,
    private val customerQueryExtractors: List<CustomerQueryExtractor>
) {
    fun createCustomer(customer: SearchableCustomer) = customerSearchRepository.save(customer)

    fun findBy(customerQueryDTO: CustomerQueryDTO): List<SearchableCustomer> {
        val boolQuery = QueryBuilders.boolQuery()

        customerQueryExtractors
            .mapNotNull { it.extract(customerQueryDTO) }
            .forEach { boolQuery.must(it) }

        return elasticsearchOperations
            .search(NativeSearchQueryBuilder().withQuery(boolQuery).build(), SearchableCustomer::class.java)
            .map { it.content }
            .toList()
    }
}
