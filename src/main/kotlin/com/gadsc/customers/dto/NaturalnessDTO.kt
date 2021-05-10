package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty

class NaturalnessDTO(
    @JsonProperty( "city_of_birth")
    val cityOfBirth: String?,
    @JsonProperty( "state_of_birth")
    val stateOfBirth: String?,
    @JsonProperty( "country_of_birth")
    val countryOfBirth: String?,
    @JsonProperty( "nationality")
    val nationality: String
)
