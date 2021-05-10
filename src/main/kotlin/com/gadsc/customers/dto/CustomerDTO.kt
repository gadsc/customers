package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.model.CivilStatus
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
    @JsonProperty("created_at")
    val createdAt: LocalDate = LocalDate.now(),
    @JsonProperty("updated_at")
    var updatedAt: LocalDate = LocalDate.now(),
    @JsonProperty("deleted_at")
    var deletedAt: LocalDateTime? = null,

    val phones: MutableSet<Phone>,

    val addressDTO: AddressDTO,

    var naturalnessDTO: NaturalnessDTO?,

    val identificationDocument: IdentificationDocument
)
