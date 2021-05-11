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

    @Column(name = "type")
    val type: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Phone) return false

        if (number != other.number) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}
