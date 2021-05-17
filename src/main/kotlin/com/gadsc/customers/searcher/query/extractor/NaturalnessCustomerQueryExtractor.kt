package com.gadsc.customers.searcher.query.extractor

import com.gadsc.customers.searcher.query.dto.CustomerQueryDTO
import com.gadsc.customers.searcher.query.SearchCustomerQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.springframework.stereotype.Component

@Component
class NaturalnessCustomerQueryExtractor: CustomerQueryExtractor {
    override fun extract(customerQueryDTO: CustomerQueryDTO): QueryBuilder? =
        customerQueryDTO.naturalness?.let {
            SearchCustomerQueryBuilder.instance("naturalness")
                .withField(it.cityOfBirth, "naturalness.cityOfBirth")
                .withField(it.stateOfBirth, "naturalness.stateOfBirth")
                .withField(it.countryOfBirth, "naturalness.countryOfBirth")
                .withField(it.nationality, "naturalness.nationality")
                .build()
        }
}
