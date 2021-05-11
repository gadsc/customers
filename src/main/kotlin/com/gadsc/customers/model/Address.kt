package com.gadsc.customers.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "customer_addresses")
class Address(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(name = "city")
    val city: String,
    @Column(name = "state")
    val state: String,
    @Column(name = "street")
    val street: String,
    @Column(name = "zipcode")
    val zipcode: String,
    @Column(name = "neighborhood")
    val neighborhood: String,
    @Column(name = "complement")
    val complement: String?,
    @Column(name = "number")
    val number: String,
    @Column(name = "address_type")
    val addressType: String
)
