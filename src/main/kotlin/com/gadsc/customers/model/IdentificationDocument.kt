package com.gadsc.customers.model

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
class IdentificationDocument(

    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    val type: DocumentType,

    @Column(name = "document_number")
    val number: String,

    @Column(name = "document_issue_date")
    val issueDate: LocalDate? = null,

    @Column(name = "document_issuer")
    val issuer: String? = null
)

enum class DocumentType {
    CPF;
}
