package com.gadsc.customers.worker.indexer

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface CustomerSearchRepository: ElasticsearchRepository<CustomerSearch, String> {

}
