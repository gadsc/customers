package com.gadsc.customers.searcher.model

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
)
