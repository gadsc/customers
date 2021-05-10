package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.model.CustomerPhoneType

class Phone(
    @JsonProperty("number")
    val number: String,
    @JsonProperty("type")
    val type: CustomerPhoneType
)
