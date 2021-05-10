package com.gadsc.customers.model

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
class Phone(
    @Column(name = "number", nullable = false)
    val number: String,
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: CustomerPhoneType
)

enum class CustomerPhoneType {
    RESIDENTIAL, CELLPHONE, PROFESSIONAL
}
