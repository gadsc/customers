package com.gadsc.customers.worker.indexer

import com.fasterxml.jackson.annotation.JsonFormat
import com.gadsc.customers.model.*
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

object CustomerIndex {
    const val INDEX_NAME = "customer_index_2"
}

@Document(indexName = CustomerIndex.INDEX_NAME)
class CustomerSearch(
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

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute)
    val deletedAt: LocalDateTime? = null,

    @Field(type = FieldType.Nested)
    val phones: Set<PhoneSearch> = emptySet(),

    val addresses: Set<AddressSearch> = emptySet(),

    val naturalness: NaturalnessSearch?,

    val mainDocument: MainDocumentSearch
)

class PhoneSearch(
    val number: String,

    @Field(type = FieldType.Keyword)
    val type: String
)

class AddressSearch(

    val city: String,

    val state: String,

    val street: String,

    val zipcode: String,

    val neighborhood: String,

    val complement: String?,

    val number: String,

    @Field(type = FieldType.Keyword)
    val addressType: String
)

class NaturalnessSearch(

    val cityOfBirth: String?,

    val stateOfBirth: String?,

    val countryOfBirth: String?,

    @Field(type = FieldType.Keyword)
    val nationality: String
)

class MainDocumentSearch(
    @Field(type = FieldType.Keyword)
    val mainDocumentType: String,


    val code: String
)
