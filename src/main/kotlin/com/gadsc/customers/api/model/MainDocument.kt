package com.gadsc.customers.api.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class MainDocument(
    @Column(name = "main_document_type")
    val mainDocumentType: String,

    @Column(name = "code")
    val code: String
)
