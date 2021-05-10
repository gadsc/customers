package com.gadsc.customers.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Address(
    @Column(name = "state")
    val state: String,

    @Column(name = "zipcode")
    val zipcode: String,

    @Column(name = "city")
    val city: String,

    @Column(name = "complement", nullable = true)
    val complement: String?,

    @Column(name = "neighborhood")
    val neighborhood: String,

    @Column(name = "number")
    val number: String,

    @Column(name = "address")
    val address: String
)
