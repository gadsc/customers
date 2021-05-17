package com.gadsc.customers.searcher.service

import com.gadsc.customers.api.exception.ResourceNotFoundException
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
    fun createOrUpdateCustomer(customer: SearchableCustomer): SearchableCustomer =
        customerSearchRepository.save(
            customerSearchRepository.findByExternalId(customer.externalId)?.let {
                it.update(customer)
            } ?: customer
        )

    fun delete(customer: SearchableCustomer) {
        customerSearchRepository.findByExternalId(customer.externalId)?.let {
            customerSearchRepository.delete(it)
        } ?: throw ResourceNotFoundException("Resource with id ${customer.externalId} to delete not found")
    }

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
