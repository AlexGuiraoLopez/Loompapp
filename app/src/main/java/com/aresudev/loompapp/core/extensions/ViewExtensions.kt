package com.aresudev.loompapp.core.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData

fun Activity.showToast(text: String, length:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,text,length).show()
}

fun Activity.hideKeyboard(){
    val view: View? = this.currentFocus
    if (view != null) {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun FragmentManager.changeCurrentFragment(fragmentContainer: Int, fragment: Fragment) {
    beginTransaction().apply {
        replace(fragmentContainer, fragment)
            .commit()
    }
}

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
