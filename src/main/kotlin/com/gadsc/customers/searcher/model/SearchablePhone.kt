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
}
