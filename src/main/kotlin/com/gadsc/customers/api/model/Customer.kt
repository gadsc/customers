package com.gadsc.customers.api.model

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
    val jobTitle: String?,
    @Column(name = "civil_status")
    @Enumerated(EnumType.STRING)
    val civilStatus: CivilStatus,
    @Column(name = "birthdate")
    val birthdate: LocalDate,
    @Column(name = "mother_full_name")
    val motherFullName: String?,
    @Column(name = "father_full_name")
    val fatherFullName: String?,
    @Column(name = "politically_exposed")
    val politicallyExposed: Boolean,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    val phones: Set<Phone> = emptySet(),
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    val addresses: Set<Address> = emptySet(),
    @Embedded
    val naturalness: Naturalness?,
    @Embedded
    val mainDocument: MainDocument
) {
    fun update(customer: Customer): Customer = Customer(
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
        mainDocument = customer.mainDocument
    )

    fun logicalDelete(): Customer = Customer(
        id = this.id,
        name = this.name,
        email = this.email,
        jobTitle = this.jobTitle,
        civilStatus = this.civilStatus,
        birthdate = this.birthdate,
        motherFullName = this.motherFullName,
        fatherFullName = this.fatherFullName,
        politicallyExposed = this.politicallyExposed,
        createdAt = this.createdAt,
        updatedAt = LocalDateTime.now(),
        deletedAt = LocalDateTime.now(),
        phones = this.phones,
        addresses = this.addresses,
        naturalness = this.naturalness,
        mainDocument = this.mainDocument
    )
}
