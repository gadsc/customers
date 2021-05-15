package com.gadsc.customers.objectmothers.dto

import com.gadsc.customers.dto.AddressDTO

object AddressDTOObjectMother {
    fun build(
        city: String = "Customer City",
        state: String = "Customer State",
        street: String = "Customer Street",
        zipcode: String = "11111111",
        neighborhood: String = "Customer Neighborhood",
        complement: String = "Customer with Complement",
        number: String = "123",
        addressType: String = "HOME"
    ): AddressDTO = AddressDTO(
        city = city,
        state = state,
        street = street,
        zipcode = zipcode,
        neighborhood = neighborhood,
        complement = complement,
        number = number,
        addressType = addressType
    )
}
