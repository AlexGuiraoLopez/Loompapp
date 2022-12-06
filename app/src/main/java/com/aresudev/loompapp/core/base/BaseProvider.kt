package com.aresudev.loompapp.core.base

import com.aresudev.loompapp.core.error.LoompaNotFoundException

abstract class BaseProvider<T>(private val memoryExpirationTime: Long? = null) {

    var memoryData: MutableList<T> = mutableListOf()

    private var lastUpdate: Long = 0

    open fun hasData(): Boolean =
        if (memoryExpirationTime != null) {
            memoryData.isNotEmpty() && System.currentTimeMillis() - lastUpdate < memoryExpirationTime
        } else {
            memoryData.isNotEmpty()
        }

    open fun getData(): MutableList<T> = if (hasData()) memoryData else mutableListOf()

    open fun saveData(data: T): T {
        lastUpdate = System.currentTimeMillis()
        this.memoryData.add(data)
        return data
    }

    open fun invalidateData() {
        memoryData =  mutableListOf()
        lastUpdate = 0
    }
}