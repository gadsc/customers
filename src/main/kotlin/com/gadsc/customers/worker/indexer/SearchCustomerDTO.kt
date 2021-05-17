package com.gadsc.customers.worker.indexer

import org.apache.lucene.search.join.ScoreMode
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.MatchQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import java.time.LocalDate
import java.time.LocalDateTime

class SearchCustomerDTO(
    val name: String? = null,

    val email: String? = null,

    val jobTitle: String? = null,

    val civilStatus: String? = null,

    val birthdate: LocalDate? = null,

    val motherFullName: String? = null,

    val fatherFullName: String? = null,

    val politicallyExposed: Boolean? = null,

    val createdAt: LocalDateTime? = null,

    val updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,

    val phone: SearchPhoneDTO? = null,

    val address: SearchAddressDTO? = null,

    val naturalness: SearchNaturalnessDTO? = null,

    val mainDocument: SearchMainDocumentDTO? = null
) {
    fun toCriteria(): List<MatchQueryBuilder> = listOfNotNull(
        name?.let {
            QueryBuilders.matchQuery("name", it)
                .fuzziness(Fuzziness.ONE)
                .minimumShouldMatch("65%")
                .prefixLength(3)
        },
        email?.let {
            QueryBuilders.matchQuery("email", it)
        },
        jobTitle?.let {
            QueryBuilders.matchQuery("jobTitle", it)
        },
        civilStatus?.let {
            QueryBuilders.matchQuery("civilStatus", it)
        },
        birthdate?.let {
            QueryBuilders.matchQuery("birthdate", it)
        },
        motherFullName?.let {
            QueryBuilders.matchQuery("motherFullName", it)
        },
        fatherFullName?.let {
            QueryBuilders.matchQuery("fatherFullName", it)
        },
        politicallyExposed?.let {
            QueryBuilders.matchQuery("politicallyExposed", it)
        },
        createdAt?.let {
            QueryBuilders.matchQuery("createdAt", it)
        },
        updatedAt?.let {
            QueryBuilders.matchQuery("updatedAt", it)
        },
        deletedAt?.let {
            QueryBuilders.matchQuery("deletedAt", it)
        }
    )
}

class SearchPhoneDTO(
    val number: String? = null,

    val type: String? = null
) {
    fun toQuery(): QueryBuilder? {
        val queries = listOfNotNull(
            number?.let {
                QueryBuilders.matchQuery("phones.number", it)
            },
            type?.let {
                QueryBuilders.matchQuery("phones.type", it)
            }
        )

        return if (queries.isEmpty()) null else {
            val boolQuery = QueryBuilders.boolQuery()
            queries.forEach { boolQuery.must(it) }

            QueryBuilders.nestedQuery("phones", boolQuery, ScoreMode.None)
        }
    }
}

class SearchAddressDTO(
    val city: String? = null,

    val state: String? = null,

    val street: String? = null,

    val zipcode: String? = null,

    val neighborhood: String? = null,

    val complement: String? = null,

    val number: String? = null,

    val addressType: String? = null
){
    fun toQuery(): QueryBuilder? {
        val queries = listOfNotNull(
            city?.let {
                QueryBuilders.matchQuery("addresses.city", it)
            },
            state?.let {
                QueryBuilders.matchQuery("addresses.state", it)
            },
            street?.let {
                QueryBuilders.matchQuery("addresses.street", it)
            },
            zipcode?.let {
                QueryBuilders.matchQuery("addresses.zipcode", it)
            },
            neighborhood?.let {
                QueryBuilders.matchQuery("addresses.neighborhood", it)
            },
            complement?.let {
                QueryBuilders.matchQuery("addresses.complement", it)
            },
            number?.let {
                QueryBuilders.matchQuery("addresses.number", it)
            },
            addressType?.let {
                QueryBuilders.matchQuery("addresses.addressType", it)
            }
        )

        return if (queries.isEmpty()) null else {
            val boolQuery = QueryBuilders.boolQuery()
            queries.forEach { boolQuery.must(it) }

            QueryBuilders.nestedQuery("addresses", boolQuery, ScoreMode.None)
        }
    }
}

class SearchNaturalnessDTO(
    val cityOfBirth: String? = null,

    val stateOfBirth: String? = null,

    val countryOfBirth: String? = null,

    val nationality: String? = null
){
    fun toQuery(): QueryBuilder? {
        val queries = listOfNotNull(
            cityOfBirth?.let {
                QueryBuilders.matchQuery("naturalness.cityOfBirth", it)
            },
            stateOfBirth?.let {
                QueryBuilders.matchQuery("naturalness.stateOfBirth", it)
            },
            countryOfBirth?.let {
                QueryBuilders.matchQuery("naturalness.countryOfBirth", it)
            },
            nationality?.let {
                QueryBuilders.matchQuery("naturalness.nationality", it)
            }
        )

        return if (queries.isEmpty()) null else {
            val boolQuery = QueryBuilders.boolQuery()
            queries.forEach { boolQuery.must(it) }

            QueryBuilders.nestedQuery("naturalness", boolQuery, ScoreMode.None)
        }
    }
}

data class SearchMainDocumentDTO(
    val mainDocumentType: String? = null,

    val code: String? = null
){
    fun toQuery(): QueryBuilder? {
        val queries = listOfNotNull(
            mainDocumentType?.let {
                QueryBuilders.matchQuery("mainDocument.mainDocumentType", it)
            },
            code?.let {
                QueryBuilders.matchQuery("mainDocument.code", it)
            }
        )

        return if (queries.isEmpty()) null else {
            val boolQuery = QueryBuilders.boolQuery()
            queries.forEach { boolQuery.must(it) }

            QueryBuilders.nestedQuery("mainDocument", boolQuery, ScoreMode.None)
        }
    }
}
