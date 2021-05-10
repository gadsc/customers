package com.gadsc.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.model.DocumentType
import java.time.LocalDate

class IdentificationDocument(

    @JsonProperty( "document_type")
    val type: DocumentType,

    @JsonProperty( "document_number")
    val number: String,

    @JsonProperty( "document_issue_date")
    val issueDate: LocalDate? = null,

    @JsonProperty( "document_issuer")
    val issuer: String? = null
)
