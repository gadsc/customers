package com.gadsc.customers.searcher.model

import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

class SearchableMainDocument(
    @Field(type = FieldType.Keyword)
    val mainDocumentType: String,

    val code: String
)
