package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.model.MainDocument

class MainDocumentDTO(

    @JsonProperty("main_document_type")
    val mainDocumentType: String,

    @JsonProperty("main_document_type")
    val code: String
) {
    companion object {
        fun fromDomain(mainDocument: MainDocument): MainDocumentDTO = MainDocumentDTO(
            mainDocumentType = mainDocument.mainDocumentType,
            code = mainDocument.code
        )
    }

    fun toDomain(): MainDocument = MainDocument(
        mainDocumentType = this.mainDocumentType,
        code = this.code
    )
}
