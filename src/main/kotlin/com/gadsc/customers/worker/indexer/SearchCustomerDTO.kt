package com.gadsc.customers.worker.indexer

import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.Field
import org.springframework.data.elasticsearch.core.query.SimpleField
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

    val address: AddressSearch? = null,

    val naturalness: NaturalnessSearch? = null,

    val mainDocument: MainDocumentSearch? = null
) {
    fun toCriteria(): List<Criteria> = listOfNotNull(
        name?.let {
            Criteria("name").contains(it)
        },
        email?.let {
            Criteria("email").contains(it)
        },
        jobTitle?.let {
            Criteria("jobTitle").`is`(it)
        },
        civilStatus?.let {
            Criteria("civilStatus").`is`(it)
        },
        birthdate?.let {
            Criteria("birthdate").`is`(it)
        },
        motherFullName?.let {
            Criteria("motherFullName").contains(it)
        },
        fatherFullName?.let {
            Criteria("fatherFullName").contains(it)
        },
        politicallyExposed?.let {
            Criteria("politicallyExposed").`is`(it)
        },
        createdAt?.let {
            Criteria("createdAt").`is`(it)
        },
        updatedAt?.let {
            Criteria("updatedAt").`is`(it)
        },
        deletedAt?.let {
            Criteria("deletedAt").`is`(it)
        }
    )// + (phone?.toCriteria() ?: emptyList())
}

class SearchPhoneDTO(
    val number: String? = null,

    val type: String? = null
) {
    fun toCriteria(): List<Criteria> = listOfNotNull(
        number?.let {
            Criteria("number").`is`(it)
        },
        type?.let {
            Criteria("type").`is`(it)
        }
    )
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
)

class SearchNaturalnessDTO(
    val cityOfBirth: String? = null,

    val stateOfBirth: String? = null,

    val countryOfBirth: String? = null,

    val nationality: String? = null
)

data class SearchMainDocumentDTO(
    val mainDocumentType: String? = null,

    val code: String? = null
)
