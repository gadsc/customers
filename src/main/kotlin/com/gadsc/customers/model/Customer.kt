package com.gadsc.customers.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "customers")
class Customer(
    @Id
    @GeneratedValue
    val id: UUID? = null,
    @Column(name = "name")
    val name: String,
    @Column(name = "email", nullable = false)
    val email: String,
    @Column(name = "job_title")
    var jobTitle: String,
    @Column(name = "civil_status")
    @Enumerated(EnumType.STRING)
    val civilStatus: CivilStatus,
    @Column(name = "birthdate")
    val birthdate: LocalDate,
    @Column(name = "mother_full_name")
    var motherFullName: String?,
    @Column(name = "father_full_name")
    var fatherFullName: String?,
    @Column(name = "politically_exposed")
    val politicallyExposed: Boolean?,
    @Column(name = "created_at")
    val createdAt: LocalDate = LocalDate.now(),
    @Column(name = "updated_at")
    var updatedAt: LocalDate = LocalDate.now(),
    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null,
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "customer_phones", joinColumns = [JoinColumn(name = "customer_id")])
    val phones: MutableSet<Phone>,
    @Embedded
    val address: Address,
    @Embedded
    var naturalness: Naturalness?,
    @Embedded
    val identificationDocument: IdentificationDocument
)
