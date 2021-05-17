package com.gadsc.customers.searcher.query.extractor

import com.gadsc.customers.searcher.query.dto.CustomerQueryDTO
import com.gadsc.customers.searcher.query.SearchCustomerQueryBuilder
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.stereotype.Component

@Component
class SearchCustomerQueryExtractor : CustomerQueryExtractor {
    override fun extract(customerQueryDTO: CustomerQueryDTO): QueryBuilder? =
        customerQueryDTO.let {
            SearchCustomerQueryBuilder.instance()
                .withCustomQuery(field = it.name, matchQueryBuilder = QueryBuilders.matchQuery("name", it)
                    .fuzziness(Fuzziness.ONE)
                    .minimumShouldMatch("65%")
                    .prefixLength(3)
                )
                .withField(it.email, "email")
                .withField(it.jobTitle, "jobTitle")
                .withField(it.civilStatus, "civilStatus")
                .withField(it.birthdate, "birthdate")
                .withField(it.motherFullName, "motherFullName")
                .withField(it.fatherFullName, "fatherFullName")
                .withField(it.politicallyExposed, "politicallyExposed")
                .withField(it.createdAt, "createdAt")
                .withField(it.updatedAt, "updatedAt")
                .withField(it.deletedAt, "deletedAt")
                .build()
        }
}
