package com.gadsc.customers.worker.indexer

import org.apache.lucene.search.join.ScoreMode
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.query.QueryBuilders.*
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service


@Service
class CustomerSearchService(
    private val customerSearchRepository: CustomerSearchRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) {
    fun createProduct(product: CustomerSearch) = customerSearchRepository.save(product)

    fun findByName(name: String): List<CustomerSearch> = customerSearchRepository.findByName(name)
    fun findByNameOrCategory(query: String): List<CustomerSearch> = customerSearchRepository.findByNameOrEmail(query, query)

    fun findBy(searchCustomerDTO: SearchCustomerDTO): List<CustomerSearch> {
        val builder: QueryBuilder = QueryBuilders.nestedQuery("phones",
            QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("phones.type", "CELLPHONE")),
            ScoreMode.None)

        val searchQuery: NativeSearchQuery = NativeSearchQueryBuilder().withQuery(builder).build()
        val users: SearchHits<CustomerSearch> = elasticsearchOperations.search(searchQuery, CustomerSearch::class.java)
//        val criterias = searchCustomerDTO.toCriteria()
//        val phoneCriteria = searchCustomerDTO.phone?.toCriteria()?.reduce { acc, criteria -> acc.and(criteria) }
//
//        val searchCriteria = criterias.reduce { acc, criteria -> acc.and(criteria) }
//
//        val searchQuery = CriteriaQuery(searchCriteria)
//        val productHits: SearchHits<CustomerSearch> = elasticsearchOperations
//            .search(searchQuery, CustomerSearch::class.java,
//                IndexCoordinates.of(CustomerIndex.INDEX_NAME))
//
        val productMatches: MutableList<CustomerSearch> = ArrayList()
        users.forEach { srchHit -> productMatches.add(srchHit.content) }
//
        return productMatches
    }

    fun findByNameContaining(name: String): List<CustomerSearch> = customerSearchRepository.findByNameContaining(name)

//    fun findByManufacturerAndCategory(manufacturer: String, category: String): List<CustomerSearch> =
//        customerSearchRepository.findByManufacturerAndCategory(manufacturer, category)
}
