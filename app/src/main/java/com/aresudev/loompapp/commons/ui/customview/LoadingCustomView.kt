package com.aresudev.loompapp.commons.ui.customview

import android.content.Context
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.aresudev.loompapp.databinding.CustomViewLoadingBinding

class LoadingCustomView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var _viewBinding: CustomViewLoadingBinding? = null
    private val viewBinding: CustomViewLoadingBinding
        get() = _viewBinding!!

    init {
        _viewBinding = CustomViewLoadingBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setLoadingVisibility(isVisible: Boolean) {
        visibility = when (isVisible) {
            true -> VISIBLE
            false -> GONE
        }
    }
}