package com.aresudev.loompapp.commons.ui.base

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    abstract fun initViewModel()
    abstract fun initViewModelObservers()

    protected fun showLoadIfIsLoading(isScreenLoading: Boolean, loadingView: View){
        if (isScreenLoading){
            loadingView.visibility = View.VISIBLE
        }else{
            loadingView.visibility = View.GONE
        }
    }
}