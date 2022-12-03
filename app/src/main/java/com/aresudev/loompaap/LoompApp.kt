package com.aresudev.loompaap

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LoompApp : Application(){
    companion object{
        lateinit var instance: LoompApp
    }

    init {
        instance = this
    }
}