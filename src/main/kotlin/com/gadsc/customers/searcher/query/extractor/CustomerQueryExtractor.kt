package com.gadsc.customers.searcher.query.extractor

import com.gadsc.customers.searcher.dto.SearchCustomerDTO
import org.elasticsearch.index.query.QueryBuilder

interface CustomerQueryExtractor {
    fun extract(from: SearchCustomerDTO): QueryBuilder?
}
