package com.gadsc.customers.searcher.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

object CustomerIndex {
    const val INDEX_NAME = "customer_index_3"
}

@Document(indexName = CustomerIndex.INDEX_NAME)
class SearchableCustomer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: String? = null,

    val name: String,

    val email: String,

    @Field(type = FieldType.Keyword)
    val jobTitle: String?,

    @Field(type = FieldType.Keyword)
    val civilStatus: String,

    @Field(type = FieldType.Date, format = DateFormat.date)
    val birthdate: LocalDate,

    val motherFullName: String?,

    val fatherFullName: String?,

    val politicallyExposed: Boolean,

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute)
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Field(type = FieldType.Nested)
    val phones: Set<SearchablePhone> = emptySet(),

    @Field(type = FieldType.Nested)
    val addresses: Set<SearchableAddress> = emptySet(),

    @Field(type = FieldType.Nested)
    val naturalness: SearchableNaturalness?,

    @Field(type = FieldType.Nested)
    val mainDocument: SearchableMainDocument
)
