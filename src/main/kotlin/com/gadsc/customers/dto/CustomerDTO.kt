package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.model.Address
import com.gadsc.customers.model.CivilStatus
import com.gadsc.customers.model.Customer
import com.gadsc.customers.model.Phone
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

class CustomerDTO(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("job_title")
    var jobTitle: String,
    @JsonProperty("civil_status")
    @Enumerated(EnumType.STRING)
    val civilStatus: CivilStatus,
    @JsonProperty("birthdate")
    val birthdate: LocalDate,
    @JsonProperty("mother_full_name")
    var motherFullName: String?,
    @JsonProperty("father_full_name")
    var fatherFullName: String?,
    @JsonProperty("politically_exposed")
    val politicallyExposed: Boolean?,
    @JsonProperty("phones")
    val phones: Set<PhoneDTO>,
    @JsonProperty("addresses")
    val addresses: Set<AddressDTO>,
    @JsonProperty("naturalness")
    var naturalness: NaturalnessDTO?,
    @JsonProperty("main_document")
    val mainDocument: MainDocumentDTO
) {
    companion object {
        fun fromDomain(customer: Customer): CustomerDTO = CustomerDTO(
            name = customer.name,
            email = customer.email,
            jobTitle = customer.jobTitle,
            civilStatus = customer.civilStatus,
            birthdate = customer.birthdate,
            motherFullName = customer.motherFullName,
            fatherFullName = customer.fatherFullName,
            politicallyExposed = customer.politicallyExposed,
            phones = customer.phones.map { PhoneDTO.fromDomain(it) }.toSet(),
            addresses = customer.addresses.map { AddressDTO.fromDomain(it) }.toSet(),
            naturalness = if (customer.naturalness != null) NaturalnessDTO.fromDomain(customer.naturalness!!) else null,
            mainDocument = MainDocumentDTO.fromDomain(customer.mainDocument)
        )
    }

    fun toDomain(): Customer = Customer(
        name = this.name,
        email = this.email,
        jobTitle = this.jobTitle,
        civilStatus = this.civilStatus,
        birthdate = this.birthdate,
        motherFullName = this.motherFullName,
        fatherFullName = this.fatherFullName,
        politicallyExposed = this.politicallyExposed,
        phones = this.phones.map { it.toDomain() }.toSet(),
        addresses = this.addresses.map { it.toDomain() }.toSet(),
        naturalness = this.naturalness?.toDomain(),
        mainDocument = this.mainDocument.toDomain()
    )
}
