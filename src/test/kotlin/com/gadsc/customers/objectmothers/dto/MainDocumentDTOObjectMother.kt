package com.gadsc.customers.objectmothers.dto

import com.gadsc.customers.dto.MainDocumentDTO

object MainDocumentDTOObjectMother {
    fun build(
        mainDocumentType: String = "CPF",
        code: String = "33807889027"
    ): MainDocumentDTO = MainDocumentDTO(
        mainDocumentType = mainDocumentType,
        code = code
    )
}
