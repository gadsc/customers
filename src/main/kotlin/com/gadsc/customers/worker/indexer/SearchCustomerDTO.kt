package com.gadsc.customers.worker.indexer

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
)

class SearchPhoneDTO(
    val number: String? = null,

    val type: String? = null
)

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
