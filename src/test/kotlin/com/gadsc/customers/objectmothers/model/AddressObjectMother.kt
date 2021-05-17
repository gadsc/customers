package com.gadsc.customers.objectmothers.model

import com.gadsc.customers.api.model.Address
import java.util.UUID

object AddressObjectMother {
    fun build(
        id: UUID? = null,
        city: String = "Customer City",
        state: String = "Customer State",
        street: String = "Customer Street",
        zipcode: String = "11111111",
        neighborhood: String = "Customer Neighborhood",
        complement: String? = "Customer with Complement",
        number: String = "123",
        addressType: String = "HOME"
    ): Address = Address(
        id = id,
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
