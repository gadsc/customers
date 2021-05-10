package com.gadsc.customers.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Naturalness(
    @Column(name = "city_of_birth")
    val cityOfBirth: String?,
    @Column(name = "state_of_birth")
    val stateOfBirth: String?,
    @Column(name = "country_of_birth")
    val countryOfBirth: String?,
    @Column(name = "nationality")
    val nationality: String
)
