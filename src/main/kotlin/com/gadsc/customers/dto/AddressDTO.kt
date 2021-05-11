package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.model.Address

class AddressDTO(
    @JsonProperty("city")
    val city: String,
    @JsonProperty("state")
    val state: String,
    @JsonProperty("street")
    val street: String,
    @JsonProperty("zipcode")
    val zipcode: String,
    @JsonProperty("neighborhood")
    val neighborhood: String,
    @JsonProperty("complement")
    val complement: String?,
    @JsonProperty("number")
    val number: String,
    @JsonProperty("address_type")
    val addressType: String
) {
    companion object {
        fun fromDomain(address: Address): AddressDTO = AddressDTO(
            city = address.city,
            state = address.state,
            street = address.street,
            zipcode = address.zipcode,
            neighborhood = address.neighborhood,
            complement = address.complement,
            number = address.number,
            addressType = address.addressType
        )
    }

    fun toDomain(): Address = Address(
        city = this.city,
        state = this.state,
        street = this.street,
        zipcode = this.zipcode,
        neighborhood = this.neighborhood,
        complement = this.complement,
        number = this.number,
        addressType = this.addressType
    )
}
