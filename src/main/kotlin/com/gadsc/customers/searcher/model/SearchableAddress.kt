package com.gadsc.customers.searcher.model

import com.gadsc.customers.api.dto.AddressDTO
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

class SearchableAddress(
    val city: String,

    val state: String,

    val street: String,

    val zipcode: String,

    val neighborhood: String,

    val complement: String?,

    val number: String,

    @Field(type = FieldType.Keyword)
    val addressType: String
) {
    companion object {
        fun from(addressDTO: AddressDTO): SearchableAddress = SearchableAddress(
            city = addressDTO.city,
            state = addressDTO.state,
            street = addressDTO.street,
            zipcode = addressDTO.zipcode,
            neighborhood = addressDTO.neighborhood,
            complement = addressDTO.complement,
            number = addressDTO.number,
            addressType = addressDTO.addressType
        )
    }
}
