package com.gadsc.customers.searcher.model

import com.gadsc.customers.api.dto.CustomerDTO
import com.gadsc.customers.api.model.Customer
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
    const val INDEX_NAME = "customer_index"
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
    val mainDocument: SearchableMainDocument,

    val externalId: String
) {
    companion object {
        fun from(customerDTO: CustomerDTO): SearchableCustomer = SearchableCustomer(
            externalId = customerDTO.id!!,
            name = customerDTO.name,
            email = customerDTO.email,
            jobTitle = customerDTO.jobTitle,
            civilStatus = customerDTO.civilStatus.name,
            birthdate = customerDTO.birthdate,
            motherFullName = customerDTO.motherFullName,
            fatherFullName = customerDTO.fatherFullName,
            politicallyExposed = customerDTO.politicallyExposed,
            phones = customerDTO.phones.map { SearchablePhone.from(it) }.toSet(),
            addresses = customerDTO.addresses.map { SearchableAddress.from(it) }.toSet(),
            naturalness = customerDTO.naturalness?.let { SearchableNaturalness.from(it) },
            mainDocument = SearchableMainDocument.from(customerDTO.mainDocument)
        )
    }

    fun update(customer: SearchableCustomer): SearchableCustomer = SearchableCustomer(
        id = this.id,
        name = customer.name,
        email = customer.email,
        jobTitle = customer.jobTitle,
        civilStatus = customer.civilStatus,
        birthdate = customer.birthdate,
        motherFullName = customer.motherFullName,
        fatherFullName = customer.fatherFullName,
        politicallyExposed = customer.politicallyExposed,
        createdAt = this.createdAt,
        updatedAt = LocalDateTime.now(),
        phones = this.phones + customer.phones,
        addresses = this.addresses + customer.addresses,
        naturalness = customer.naturalness,
        mainDocument = customer.mainDocument,
        externalId = this.externalId
    )
}
