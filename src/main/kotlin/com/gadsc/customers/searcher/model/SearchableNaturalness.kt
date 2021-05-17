package com.gadsc.customers.searcher.model

import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

class SearchableNaturalness(
    val cityOfBirth: String?,

    val stateOfBirth: String?,

    val countryOfBirth: String?,

    @Field(type = FieldType.Keyword)
    val nationality: String
)
