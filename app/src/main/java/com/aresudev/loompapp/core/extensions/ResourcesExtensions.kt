package com.aresudev.loompapp.core.extensions

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.aresudev.loompapp.LoompApp

fun getAppString(@StringRes id: Int): String = LoompApp.instance.getString(id)

fun getAppString(@StringRes id: Int, param: String): String = LoompApp.instance.getString(id, param)

fun getAppDrawable(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(LoompApp.instance, id)