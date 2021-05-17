package com.gadsc.customers.searcher.model

import com.gadsc.customers.api.dto.MainDocumentDTO
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

class SearchableMainDocument(
    @Field(type = FieldType.Keyword)
    val mainDocumentType: String,

    val code: String
) {
    companion object {
        fun from(mainDocumentDTO: MainDocumentDTO): SearchableMainDocument = SearchableMainDocument(
            mainDocumentType = mainDocumentDTO.mainDocumentType,
            code = mainDocumentDTO.code
        )
    }
}
