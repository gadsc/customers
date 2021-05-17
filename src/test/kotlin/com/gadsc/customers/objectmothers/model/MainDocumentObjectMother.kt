package com.gadsc.customers.objectmothers.model

import com.gadsc.customers.api.model.MainDocument

object MainDocumentObjectMother {
    fun build(
        mainDocumentType: String = "CPF",
        code: String = "33807889027"
    ): MainDocument = MainDocument(
        mainDocumentType = mainDocumentType,
        code = code
    )
}
