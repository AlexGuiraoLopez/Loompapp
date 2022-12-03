package com.aresudev.loompaap.core.error

class LoompaNotFoundException(private val location: String): Throwable() {
    override fun toString(): String =
        if (location.isBlank()) {
            super.toString()
        } else {
            "${super.toString()} from $location"
        }
}