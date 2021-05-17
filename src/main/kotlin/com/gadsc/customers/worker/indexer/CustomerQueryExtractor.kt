package com.gadsc.customers.worker.indexer

import org.elasticsearch.index.query.QueryBuilder

interface CustomerQueryExtractor {
    fun extract(from: SearchCustomerDTO): QueryBuilder?
}
