package com.gadsc.customers.searcher.model

import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

class SearchablePhone(
    val number: String,

    @Field(type = FieldType.Keyword)
    val type: String
)
