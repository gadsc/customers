package com.gadsc.customers.searcher.model

import com.gadsc.customers.api.dto.NaturalnessDTO
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

class SearchableNaturalness(
    val cityOfBirth: String?,

    val stateOfBirth: String?,

    val countryOfBirth: String?,

    @Field(type = FieldType.Keyword)
    val nationality: String
) {
    companion object {
        fun from(naturalnessDTO: NaturalnessDTO): SearchableNaturalness = SearchableNaturalness(
            cityOfBirth = naturalnessDTO.cityOfBirth,
            stateOfBirth = naturalnessDTO.stateOfBirth,
            countryOfBirth = naturalnessDTO.countryOfBirth,
            nationality = naturalnessDTO.nationality
        )
    }
}
