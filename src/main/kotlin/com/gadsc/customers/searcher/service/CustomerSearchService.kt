package com.gadsc.customers.searcher.service

import com.gadsc.customers.searcher.model.SearchableCustomer
import com.gadsc.customers.searcher.query.extractor.CustomerQueryExtractor
import com.gadsc.customers.searcher.repository.CustomerSearchRepository
import com.gadsc.customers.searcher.dto.SearchCustomerDTO
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service


@Service
class CustomerSearchService(
    private val customerSearchRepository: CustomerSearchRepository,
    private val elasticsearchOperations: ElasticsearchOperations,
    private val customerQueryExtractors: List<CustomerQueryExtractor>
) {
    fun createProduct(product: SearchableCustomer) = customerSearchRepository.save(product)

    fun findBy(searchCustomerDTO: SearchCustomerDTO): List<SearchableCustomer> {
        val nativeSearchQueryBuilder = NativeSearchQueryBuilder()

        customerQueryExtractors.mapNotNull { it.extract(searchCustomerDTO) }
            .forEach {
                nativeSearchQueryBuilder.withQuery(it)
            }

        return elasticsearchOperations
            .search(nativeSearchQueryBuilder.build(), SearchableCustomer::class.java)
            .map { it.content }
            .toList()
    }
}
