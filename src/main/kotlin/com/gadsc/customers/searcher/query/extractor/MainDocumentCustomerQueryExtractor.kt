package com.gadsc.customers.searcher.query.extractor

import com.gadsc.customers.searcher.query.dto.CustomerQueryDTO
import com.gadsc.customers.searcher.query.SearchCustomerQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.springframework.stereotype.Component

@Component
class MainDocumentCustomerQueryExtractor: CustomerQueryExtractor {
    override fun extract(customerQueryDTO: CustomerQueryDTO): QueryBuilder? =
        customerQueryDTO.mainDocument?.let {
            SearchCustomerQueryBuilder.instance("mainDocument")
                .withField(it.mainDocumentType, "mainDocument.mainDocumentType")
                .withField(it.code, "mainDocument.code")
                .build()
        }
}
