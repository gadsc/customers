package com.gadsc.customers.searcher.repository

import com.gadsc.customers.searcher.model.SearchableCustomer
import com.gadsc.customers.searcher.model.SearchableMainDocument
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface CustomerSearchRepository: ElasticsearchRepository<SearchableCustomer, String> {
    fun findByExternalId(externalId: String): SearchableCustomer?
}
