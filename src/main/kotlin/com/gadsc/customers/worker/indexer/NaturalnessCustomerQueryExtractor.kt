package com.gadsc.customers.worker.indexer

import org.elasticsearch.index.query.QueryBuilder
import org.springframework.stereotype.Component

@Component
class NaturalnessCustomerQueryExtractor: CustomerQueryExtractor {
    override fun extract(searchCustomerDTO: SearchCustomerDTO): QueryBuilder? =
        searchCustomerDTO.naturalness?.let {
            SearchCustomerQueryBuilder.Builder.instance("naturalness")
                .withField(it.cityOfBirth, "naturalness.cityOfBirth")
                .withField(it.stateOfBirth, "naturalness.stateOfBirth")
                .withField(it.countryOfBirth, "naturalness.countryOfBirth")
                .withField(it.nationality, "naturalness.nationality")
                .build()
        }
}
