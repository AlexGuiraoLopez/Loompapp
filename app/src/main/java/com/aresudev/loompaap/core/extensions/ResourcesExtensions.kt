package com.aresudev.loompaap.core.extensions

import androidx.annotation.StringRes
import com.aresudev.loompaap.LoompApp

fun getAppString(@StringRes id: Int): String = LoompApp.instance.getString(id)