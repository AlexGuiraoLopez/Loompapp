package com.aresudev.loompapp.core.error

import com.aresudev.loompapp.R
import com.aresudev.loompapp.core.extensions.getAppString
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ExceptionHandler {
    companion object{
        fun sendExceptionFeedbackMessage(exception: Exception): String{
            return when(exception){
                is LoompaNotFoundException -> getAppString(R.string.loompa_not_found_exception)
                is SocketTimeoutException -> getAppString(R.string.socket_timeout_exception)
                is UnknownHostException -> getAppString(R.string.unknown_host_exception)
                else -> getAppString(R.string.generic_exception)
            }
        }
    }
}