package org.aarondeveloper.dealerpos

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform