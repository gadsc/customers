package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.model.Phone

class PhoneDTO(
    @JsonProperty("number")
    val number: String,
    @JsonProperty("type")
    val type: String
) {
    companion object {
        fun fromDomain(phone: Phone): PhoneDTO = PhoneDTO(
            number = phone.number,
            type = phone.type
        )
    }
    fun toDomain(): Phone = Phone(
        number = this.number,
        type = this.type
    )
}
