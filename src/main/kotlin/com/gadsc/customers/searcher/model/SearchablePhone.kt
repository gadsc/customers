package com.gadsc.customers.searcher.model

import com.gadsc.customers.api.dto.PhoneDTO
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

class SearchablePhone(
    val number: String,

    @Field(type = FieldType.Keyword)
    val type: String
) {
    companion object {
        fun from(phoneDTO: PhoneDTO): SearchablePhone = SearchablePhone(
            number = phoneDTO.number, type = phoneDTO.type
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SearchablePhone) return false

        if (number != other.number) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }


}
