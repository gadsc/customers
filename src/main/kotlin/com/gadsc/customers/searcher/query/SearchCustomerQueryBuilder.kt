package com.gadsc.customers.searcher.query

import org.apache.lucene.search.join.ScoreMode
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
        if (searchQuery.hasClauses()) nestedPath?.let { QueryBuilders.nestedQuery(it, searchQuery, ScoreMode.None) } ?: searchQuery
        else null


    fun withField(field: Any?, name: String): SearchCustomerQueryBuilder {
        field?.let {
            searchQuery.must(QueryBuilders.matchQuery(name, it))
        }

        return this
    }

    fun withCustomQuery(field: String?, matchQueryBuilder: MatchQueryBuilder): SearchCustomerQueryBuilder {
        field?.let{
            searchQuery.must(matchQueryBuilder)
        }

        return this
    }
}
