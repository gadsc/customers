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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SearchableAddress) return false

        if (city != other.city) return false
        if (state != other.state) return false
        if (street != other.street) return false
        if (zipcode != other.zipcode) return false
        if (neighborhood != other.neighborhood) return false
        if (complement != other.complement) return false
        if (number != other.number) return false
        if (addressType != other.addressType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = city.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + street.hashCode()
        result = 31 * result + zipcode.hashCode()
        result = 31 * result + neighborhood.hashCode()
        result = 31 * result + (complement?.hashCode() ?: 0)
        result = 31 * result + number.hashCode()
        result = 31 * result + addressType.hashCode()
        return result
    }
}
