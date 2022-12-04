package com.aresudev.loompaap.commons.business.ui.customview

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.aresudev.loompaap.databinding.CustomViewAlertDialogBinding
import com.aresudev.loompaap.databinding.CustomViewAlertDialogButtonBinding

class AlertDialogCustomView(context: Context) : Dialog(context) {

    private var _viewBinding: CustomViewAlertDialogBinding? = null
    private val viewBinding: CustomViewAlertDialogBinding
        get() = _viewBinding!!

    private var dialogButtons: MutableList<Pair<String, Dialog.() -> Unit>>

    init {
        _viewBinding = CustomViewAlertDialogBinding.inflate(LayoutInflater.from(context))
        dialogButtons = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun setTitle(title: String) {
        viewBinding.tvAlertTitle.text = title
    }

    fun setDescription(description: String) {
        viewBinding.tvAlertDescription.text = description
    }

    //ToDo: Añadir color dinámico a los botones.
    fun addButton(label: String, onClickListener: Dialog.() -> Unit) = apply { this.dialogButtons.add(Pair(label,onClickListener)) }

    override fun show() {
        populateButtonList()
        super.show()
    }

    private fun populateButtonList() {
        dialogButtons.forEach { (label, onClickListener) ->
            val buttonViewBinding = CustomViewAlertDialogButtonBinding.inflate(LayoutInflater.from(context))
            with(buttonViewBinding.root) {
                text = label
                setOnClickListener { onClickListener() }
            }

            viewBinding.llButtonsContainer.addView(buttonViewBinding.root)
        }
    }
}