package com.gadsc.customers.worker.indexer

import org.elasticsearch.index.query.QueryBuilder
import org.springframework.stereotype.Component

@Component
class MainDocumentCustomerQueryExtractor: CustomerQueryExtractor {
    override fun extract(searchCustomerDTO: SearchCustomerDTO): QueryBuilder? =
        searchCustomerDTO.mainDocument?.let {
            SearchCustomerQueryBuilder.Builder.instance("mainDocument")
                .withField(it.mainDocumentType, "mainDocument.mainDocumentType")
                .withField(it.code, "mainDocument.code")
                .build()
        }
}
