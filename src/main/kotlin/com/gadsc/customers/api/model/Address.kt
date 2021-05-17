package com.gadsc.customers.api.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "customer_addresses")
class Address(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(name = "city")
    val city: String,
    @Column(name = "state")
    val state: String,
    @Column(name = "street")
    val street: String,
    @Column(name = "zipcode")
    val zipcode: String,
    @Column(name = "neighborhood")
    val neighborhood: String,
    @Column(name = "complement")
    val complement: String?,
    @Column(name = "number")
    val number: String,
    @Column(name = "address_type")
    val addressType: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Address) return false

        if (city != other.city) return false
        if (state != other.state) return false
        if (street != other.street) return false
        if (zipcode != other.zipcode) return false
        if (neighborhood != other.neighborhood) return false
        if (complement != other.complement) return false
        if (number != other.number) return false
        if (addressType != other.addressType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = city.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + street.hashCode()
        result = 31 * result + zipcode.hashCode()
        result = 31 * result + neighborhood.hashCode()
        result = 31 * result + (complement?.hashCode() ?: 0)
        result = 31 * result + number.hashCode()
        result = 31 * result + addressType.hashCode()
        return result
    }
}
