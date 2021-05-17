package com.gadsc.customers.objectmothers.model

import com.gadsc.customers.api.model.Phone
import java.util.UUID

object PhoneObjectMother {
    fun build(id: UUID? = null, number: String = "+5511999999999", type: String = "CELLPHONE"): Phone =
        Phone(id = id, number = number, type = type)
}
