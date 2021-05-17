package com.gadsc.customers.searcher.query.extractor

import com.gadsc.customers.searcher.query.dto.CustomerQueryDTO
import com.gadsc.customers.searcher.query.SearchCustomerQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.springframework.stereotype.Component

@Component
class PhoneCustomerQueryExtractor: CustomerQueryExtractor {
    override fun extract(customerQueryDTO: CustomerQueryDTO): QueryBuilder? =
        customerQueryDTO.phoneQuery?.let {
            SearchCustomerQueryBuilder.instance("phones")
                .withField(it.number, "phones.number")
                .withField(it.type, "phones.type")
                .build()
        }
}
