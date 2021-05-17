package com.gadsc.customers.worker.indexer

import org.elasticsearch.index.query.QueryBuilder
import org.springframework.stereotype.Component

@Component
class PhoneCustomerQueryExtractor: CustomerQueryExtractor {
    override fun extract(searchCustomerDTO: SearchCustomerDTO): QueryBuilder? =
        searchCustomerDTO.phone?.let {
            SearchCustomerQueryBuilder.Builder.instance("phones")
                .withField(it.number, "phones.number")
                .withField(it.type, "phones.type")
                .build()
        }
}
