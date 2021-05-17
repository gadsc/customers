package com.gadsc.customers.searcher.query.extractor

import com.gadsc.customers.searcher.dto.SearchCustomerDTO
import com.gadsc.customers.searcher.query.SearchCustomerQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.springframework.stereotype.Component

@Component
class PhoneCustomerQueryExtractor: CustomerQueryExtractor {
    override fun extract(searchCustomerDTO: SearchCustomerDTO): QueryBuilder? =
        searchCustomerDTO.phone?.let {
            SearchCustomerQueryBuilder.instance("phones")
                .withField(it.number, "phones.number")
                .withField(it.type, "phones.type")
                .build()
        }
}
