package com.gadsc.customers.worker.indexer

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.util.UUID

interface CustomerSearchRepository: ElasticsearchRepository<CustomerSearch, String> {
    fun findByName(name: String): List<CustomerSearch>
    fun findByNameOrEmail(name: String, email: String): List<CustomerSearch>

    fun findByNameContaining(name: String): List<CustomerSearch>

//    fun findByManufacturerAndCategory(manufacturer: String, category: String): List<CustomerSearch>
}
