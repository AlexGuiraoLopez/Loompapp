package com.aresudev.loompapp.core.utils.ui.dialog

import android.content.Context
import com.aresudev.loompapp.R
import com.aresudev.loompapp.commons.business.ui.customview.AlertDialogCustomView
import com.aresudev.loompapp.core.extensions.getAppString

object DialogUtils {

    fun showWarningDialog(
        context: Context,
        dialogTitleResourceId: Int,
        dialogDescriptionResourceId: Int? = null,
        positiveButtonTitleResourceId: Int = R.string.accept,
        positiveButtonOnClickListener: () -> Unit
    ) {
        AlertDialogCustomView(context).apply {
            setTitle(getAppString(dialogTitleResourceId))
            dialogDescriptionResourceId?.let { setDescription(getAppString(dialogDescriptionResourceId)) }
            addButton(getAppString(positiveButtonTitleResourceId)) { positiveButtonOnClickListener(); dismiss() }
            show()
        }
    }

    fun showWarningDialog(
        context: Context,
        dialogTitleResourceId: Int,
        dialogDescriptionResourceId: Int? = null,
        positiveButtonTitleResourceId: Int = R.string.accept,
        positiveButtonOnClickListener: () -> Unit,
        negativeButtonTitleResourceId: Int = R.string.cancel,
        negativeButtonOnClickListener: () -> Unit
    ) {
        AlertDialogCustomView(context).apply {
            setTitle(getAppString(dialogTitleResourceId))
            dialogDescriptionResourceId?.let { setDescription(getAppString(dialogDescriptionResourceId)) }
            addButton(getAppString(positiveButtonTitleResourceId)) { positiveButtonOnClickListener(); dismiss() }
            addButton(getAppString(negativeButtonTitleResourceId)) { negativeButtonOnClickListener(); dismiss() }
            show()
        }
    }

}