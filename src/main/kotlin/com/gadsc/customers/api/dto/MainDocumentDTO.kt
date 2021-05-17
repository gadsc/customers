package com.gadsc.customers.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.api.model.MainDocument

class MainDocumentDTO(

    @JsonProperty("main_document_type")
    val mainDocumentType: String,

    @JsonProperty("code")
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
