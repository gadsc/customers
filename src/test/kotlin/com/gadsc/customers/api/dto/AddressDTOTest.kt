package com.gadsc.customers.api.dto

import com.gadsc.customers.objectmothers.dto.AddressDTOObjectMother
import com.gadsc.customers.objectmothers.model.AddressObjectMother
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AddressDTOTest {
    @Nested
    inner class `from domain` {
        @Test
        fun `should create AddressDTO from domain Address with complement`() {
            val expected = AddressObjectMother.build()
            val subject = AddressDTO.fromDomain(expected)

            assertEquals(expected.city, subject.city)
            assertEquals(expected.state, subject.state)
            assertEquals(expected.street, subject.street)
            assertEquals(expected.zipcode, subject.zipcode)
            assertEquals(expected.neighborhood, subject.neighborhood)
            assertNotNull(subject.complement)
            assertEquals(expected.complement, subject.complement)
            assertEquals(expected.number, subject.number)
            assertEquals(expected.addressType, subject.addressType)
        }

        @Test
        fun `should create AddressDTO from domain Address without complement`() {
            val expected = AddressObjectMother.build(
                complement = null
            )
            val subject = AddressDTO.fromDomain(expected)

            assertEquals(expected.city, subject.city)
            assertEquals(expected.state, subject.state)
            assertEquals(expected.street, subject.street)
            assertEquals(expected.zipcode, subject.zipcode)
            assertEquals(expected.neighborhood, subject.neighborhood)
            assertNull(subject.complement)
            assertEquals(expected.number, subject.number)
            assertEquals(expected.addressType, subject.addressType)
        }
    }

    @Nested
    inner class `to domain` {
        @Test
        fun `should create domain Address from AddressDTO with complement`() {
            val expected = AddressDTOObjectMother.build()
            val subject = expected.toDomain()

            assertEquals(expected.city, subject.city)
            assertEquals(expected.state, subject.state)
            assertEquals(expected.street, subject.street)
            assertEquals(expected.zipcode, subject.zipcode)
            assertEquals(expected.neighborhood, subject.neighborhood)
            assertNotNull(subject.complement)
            assertEquals(expected.complement, subject.complement)
            assertEquals(expected.number, subject.number)
            assertEquals(expected.addressType, subject.addressType)
        }

        @Test
        fun `should create domain Address from AddressDTO without complement`() {
            val expected = AddressDTOObjectMother.build(
                complement = null
            )
            val subject = expected.toDomain()

            assertEquals(expected.city, subject.city)
            assertEquals(expected.state, subject.state)
            assertEquals(expected.street, subject.street)
            assertEquals(expected.zipcode, subject.zipcode)
            assertEquals(expected.neighborhood, subject.neighborhood)
            assertNull(subject.complement)
            assertEquals(expected.number, subject.number)
            assertEquals(expected.addressType, subject.addressType)
        }
    }
}
