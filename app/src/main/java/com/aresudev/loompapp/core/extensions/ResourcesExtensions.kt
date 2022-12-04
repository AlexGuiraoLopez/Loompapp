package com.aresudev.loompapp.core.extensions

import androidx.annotation.StringRes
import com.aresudev.loompapp.LoompApp

fun getAppString(@StringRes id: Int): String = LoompApp.instance.getString(id)