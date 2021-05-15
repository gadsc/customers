package com.gadsc.customers.dto

import com.gadsc.customers.objectmothers.dto.PhoneDTOObjectMother
import com.gadsc.customers.objectmothers.model.PhoneObjectMother
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PhoneDTOTest {
    @Nested
    inner class `from domain` {
        @Test
        fun `should create PhoneDTO from domain Phone`() {
            val expected = PhoneObjectMother.build()
            val subject = PhoneDTO.fromDomain(expected)

            assertEquals(expected.number, subject.number)
            assertEquals(expected.type, subject.type)
        }
    }

    @Nested
    inner class `to domain` {
        @Test
        fun `should create domain Phone from PhoneDTO`() {
            val expected = PhoneDTOObjectMother.build()
            val subject = expected.toDomain()

            assertEquals(expected.number, subject.number)
            assertEquals(expected.type, subject.type)
        }
    }
}
