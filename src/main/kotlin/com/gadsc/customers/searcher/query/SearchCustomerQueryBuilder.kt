package com.gadsc.customers.searcher.query

import org.apache.lucene.search.join.ScoreMode
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.MatchQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders

class SearchCustomerQueryBuilder private constructor(private val nestedPath: String?) {
    companion object Builder {
        fun instance(nestedPath: String? = null): SearchCustomerQueryBuilder =
            SearchCustomerQueryBuilder(nestedPath = nestedPath)
    }

    private val searchQuery = QueryBuilders.boolQuery()

    fun build(): QueryBuilder? =
        if (searchQuery.hasClauses()) nestedPath?.let { QueryBuilders.nestedQuery(nestedPath, searchQuery, ScoreMode.None) } ?: searchQuery
        else null


    fun withField(field: Any?, name: String): SearchCustomerQueryBuilder {
        field?.let {
            searchQuery.must(QueryBuilders.matchQuery(name, it))
        }

        return this
    }

    fun withBooleanField(field: Boolean?, name: String): SearchCustomerQueryBuilder {
        field?.let {
            searchQuery.must(QueryBuilders.matchQuery(name, it))
        }

        return this
    }

    fun withFuzzinessQuery(field: String?, name: String): SearchCustomerQueryBuilder {
        field?.let{
            searchQuery.must(QueryBuilders.matchQuery(name, it)
                .fuzziness(Fuzziness.ONE)
                .minimumShouldMatch("65%")
                .prefixLength(3))
        }

        return this
    }
}
