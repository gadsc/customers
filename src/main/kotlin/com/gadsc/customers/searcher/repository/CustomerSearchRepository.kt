package com.gadsc.customers.searcher.repository

import com.gadsc.customers.searcher.model.SearchableCustomer
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface CustomerSearchRepository: ElasticsearchRepository<SearchableCustomer, String> {

}
