package com.gadsc.customers.dto

import com.gadsc.customers.objectmothers.dto.MainDocumentDTOObjectMother
import com.gadsc.customers.objectmothers.model.MainDocumentObjectMother
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainDocumentDTOTest {
    @Nested
    inner class `from domain` {
        @Test
        fun `should create MainDocumentDTO from domain MainDocument with complement`() {
            val expected = MainDocumentObjectMother.build()
            val subject = MainDocumentDTO.fromDomain(expected)

            assertEquals(expected.mainDocumentType, subject.mainDocumentType)
            assertEquals(expected.code, subject.code)
        }
    }

    @Nested
    inner class `to domain` {
        @Test
        fun `should create domain MainDocument from MainDocumentDTO with complement`() {
            val expected = MainDocumentDTOObjectMother.build()
            val subject = expected.toDomain()

            assertEquals(expected.mainDocumentType, subject.mainDocumentType)
            assertEquals(expected.code, subject.code)
        }
    }
}
