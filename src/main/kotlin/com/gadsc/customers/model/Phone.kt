package com.gadsc.customers.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "customer_phones")
class Phone(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(name = "number")
    val number: String,

    @Column(name = "phone_type")
    val phoneType: String
)

enum class CustomerPhoneType {
    RESIDENTIAL, CELLPHONE, PROFESSIONAL
}
