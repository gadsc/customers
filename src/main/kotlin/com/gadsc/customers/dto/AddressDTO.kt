package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty

class AddressDTO(
    @JsonProperty("state")
    val state: String,

    @JsonProperty("zipcode")
    val zipcode: String,

    @JsonProperty("city")
    val city: String,

    @JsonProperty("complement")
    val complement: String?,

    @JsonProperty("neighborhood")
    val neighborhood: String,

    @JsonProperty("number")
    val number: String,

    @JsonProperty("address")
    val address: String
)
