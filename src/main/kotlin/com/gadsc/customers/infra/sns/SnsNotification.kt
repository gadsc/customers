package com.gadsc.customers.infra.sns

data class SnsNotification(
    val topicName: String,
    val message: Any
)
