package com.gadsc.customers.searcher.query.extractor

import com.gadsc.customers.searcher.query.dto.CustomerQueryDTO
import com.gadsc.customers.searcher.query.SearchCustomerQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.springframework.stereotype.Component

@Component
class AddressCustomerQueryExtractor: CustomerQueryExtractor {
    override fun extract(customerQueryDTO: CustomerQueryDTO): QueryBuilder? =
        customerQueryDTO.addressQuery?.let {
            SearchCustomerQueryBuilder.instance("addresses")
                .withField(it.city, "addresses.city")
                .withField(it.state, "addresses.state")
                .withField(it.street, "addresses.street")
                .withField(it.zipcode, "addresses.zipcode")
                .withField(it.neighborhood, "addresses.neighborhood")
                .withField(it.complement, "addresses.complement")
                .withField(it.number, "addresses.number")
                .withField(it.addressType, "addresses.addressType")
                .build()
        }
}
