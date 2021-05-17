package com.gadsc.customers.searcher.query.extractor

import com.gadsc.customers.searcher.query.dto.CustomerQueryDTO
import org.elasticsearch.index.query.QueryBuilder

interface CustomerQueryExtractor {
    fun extract(from: CustomerQueryDTO): QueryBuilder?
}
