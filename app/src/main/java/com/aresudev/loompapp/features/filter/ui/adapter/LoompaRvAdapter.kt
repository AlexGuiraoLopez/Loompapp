package com.aresudev.loompapp.features.filter.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aresudev.loompapp.databinding.ItemLoompaCardBinding
import com.aresudev.loompapp.R
import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.core.utils.filemanagement.FileManager
import javax.inject.Inject

class LoompaRvAdapter @Inject constructor():
    RecyclerView.Adapter<LoompaRvAdapter.LoompaViewHolder>() {

    private var loompaList: List<LoompaModel> = emptyList()

    private var loompaItemClickListener: LoompaItemClickListener? = null

    interface LoompaItemClickListener{
        fun onLoompaClick(loompa: LoompaModel)
    }

    fun setOnLoompaClickListener(loompaItemClickListener: LoompaItemClickListener){
        this.loompaItemClickListener = loompaItemClickListener
    }

    class LoompaViewHolder(view: View): RecyclerView.ViewHolder(view){
        val viewBinding = ItemLoompaCardBinding.bind(view)

        fun render(loompaModel: LoompaModel){
            with(viewBinding){
                tvLoompaName.text = loompaModel.firstName
                FileManager.importImageFromUrl(ivLoompaImage.context,loompaModel.image,ivLoompaImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoompaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loompa_card,parent,false)
        return LoompaViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoompaViewHolder, position: Int) {
        val item = loompaList[position]
        holder.render(item)
        holder.viewBinding.parent.setOnClickListener {
            loompaItemClickListener?.onLoompaClick(item)
        }
    }

    override fun getItemCount(): Int = loompaList.size

    fun setCollection(loompaList: List<LoompaModel>){
        this.loompaList = loompaList
        notifyDataSetChanged() //FixMe: Refactor to improve performance.
    }
}