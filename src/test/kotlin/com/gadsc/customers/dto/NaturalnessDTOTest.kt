package com.gadsc.customers.dto

import com.gadsc.customers.objectmothers.dto.NaturalnessDTOObjectMother
import com.gadsc.customers.objectmothers.model.NaturalnessObjectMother
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NaturalnessDTOTest {
    @Nested
    inner class `from domain` {
        @Test
        fun `should create NaturalnessDTO from domain Naturalness with optional fields`() {
            val expected = NaturalnessObjectMother.build()
            val subject = NaturalnessDTO.fromDomain(expected)

            assertNotNull(subject.cityOfBirth)
            assertEquals(expected.cityOfBirth, subject.cityOfBirth)
            assertNotNull(subject.stateOfBirth)
            assertEquals(expected.stateOfBirth, subject.stateOfBirth)
            assertNotNull(subject.countryOfBirth)
            assertEquals(expected.countryOfBirth, subject.countryOfBirth)
            assertEquals(expected.nationality, subject.nationality)
        }

        @Test
        fun `should create NaturalnessDTO from domain Naturalness without optional fields`() {
            val expected = NaturalnessObjectMother.build(
                cityOfBirth = null,
                stateOfBirth = null,
                countryOfBirth = null
            )
            val subject = NaturalnessDTO.fromDomain(expected)

            assertNull(subject.cityOfBirth)
            assertNull(subject.stateOfBirth)
            assertNull(subject.countryOfBirth)
            assertEquals(expected.nationality, subject.nationality)
        }
    }

    @Nested
    inner class `to domain` {
        @Test
        fun `should create domain Naturalness from NaturalnessDTO with complement`() {
            val expected = NaturalnessDTOObjectMother.build()
            val subject = expected.toDomain()

            assertNotNull(subject.cityOfBirth)
            assertEquals(expected.cityOfBirth, subject.cityOfBirth)
            assertNotNull(subject.stateOfBirth)
            assertEquals(expected.stateOfBirth, subject.stateOfBirth)
            assertNotNull(subject.countryOfBirth)
            assertEquals(expected.countryOfBirth, subject.countryOfBirth)
            assertEquals(expected.nationality, subject.nationality)
        }

        @Test
        fun `should create domain Naturalness from NaturalnessDTO without complement`() {
            val expected = NaturalnessDTOObjectMother.build(
                cityOfBirth = null,
                stateOfBirth = null,
                countryOfBirth = null
            )
            val subject = expected.toDomain()

            assertNull(subject.cityOfBirth)
            assertNull(subject.stateOfBirth)
            assertNull(subject.countryOfBirth)
            assertEquals(expected.nationality, subject.nationality)
        }
    }
}
