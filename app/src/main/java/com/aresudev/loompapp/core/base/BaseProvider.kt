package com.aresudev.loompapp.core.base

import com.aresudev.loompapp.core.error.LoompaNotFoundException

abstract class BaseProvider<T>(private val memoryExpirationTime: Long? = null) {

    var memoryData: T? = null

    private var lastUpdate: Long = 0

    open fun hasData(): Boolean =
        if (memoryExpirationTime != null) {
            memoryData != null && System.currentTimeMillis() - lastUpdate < memoryExpirationTime
        } else {
            memoryData != null
        }

    open fun getData(): T {
        if (hasData()) return memoryData!! else throw LoompaNotFoundException(this::class.java.simpleName + "- getData()")
    }

    open fun saveData(data: T): T {
        lastUpdate = System.currentTimeMillis()
        this.memoryData = data
        return data
    }

    open fun invalidateData() {
        memoryData = null
        lastUpdate = 0
    }
}