package com.gadsc.customers.searcher.query.dto

import java.time.LocalDate
import java.time.LocalDateTime

class CustomerQueryDTO(
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

    val phone: PhoneQueryDTO? = null,

    val address: AddressQueryDTO? = null,

    val naturalness: NaturalnessQueryDTO? = null,

    val mainDocument: MainDocumentQueryDTO? = null
)
