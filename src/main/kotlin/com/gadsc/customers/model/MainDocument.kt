package com.gadsc.customers.model

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
class MainDocument(

    @Column(name = "main_document_type")
    val mainDocumentType: String,

    @Column(name = "main_document_type")
    val code: String
)
