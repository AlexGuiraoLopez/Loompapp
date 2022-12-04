package com.aresudev.loompapp.features.filter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.aresudev.loompapp.R
import com.aresudev.loompapp.commons.callbacks.FragmentNavigator
import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.ui.base.BaseFragment
import com.aresudev.loompapp.core.extensions.getAppDrawable
import com.aresudev.loompapp.core.extensions.getAppString
import com.aresudev.loompapp.core.utils.GenderConverter
import com.aresudev.loompapp.core.utils.filemanagement.FileManager
import com.aresudev.loompapp.databinding.FragmentLoompaInfoBinding
import com.aresudev.loompapp.features.filter.ui.viewmodel.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment(private val idLoompa: Int) : BaseFragment() {

    private var _viewBinding: FragmentLoompaInfoBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: DetailFragmentViewModel by activityViewModels()

    lateinit var navigator: FragmentNavigator

    override fun initViewModel() {
        viewModel.loadScreen(idLoompa)
    }

    override fun initViewModelObservers() {
        with(viewModel) {
            currentLoompa.observe(viewLifecycleOwner) { loompa -> loompa?.let { populateLoompaInfo(it) } }
            isFavoriteModeOn.observe(viewLifecycleOwner) { isFavoriteModeOn -> changeFavoriteViews(isFavoriteModeOn)}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = FragmentLoompaInfoBinding.inflate(inflater, container, false)
        initEvents()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelObservers()
        initViewModel()
    }

    private fun initEvents(){
        with(viewBinding){
            ivBackArrow.setOnClickListener { navigator.navigateToFilterFragment() }
            ivFavoriteStar.setOnClickListener { viewModel.changeFavoriteMode() }
        }
    }

    private fun populateLoompaInfo(loompa: LoompaModel) {
        val loompaFullName = loompa.firstName + " " + loompa.lastName
        with(viewBinding){
            FileManager.importImageFromUrl(requireContext(),loompa.image,ivMainImage)
            tvLoompaName.text = loompaFullName
            tvAge.text = loompa.age
            tvCountry.text = loompa.country
            tvEmail.text = loompa.email
            tvGender.text = GenderConverter.convertGenderByLetterValue(loompa.gender)
            tvProfession.text = loompa.profession
            tvQuoteHeader.text = getAppString(R.string.loompa_think,loompaFullName)
            tvQuote.text = loompa.quota
            tvDescription.text = loompa.description
            tvFavoriteColor.text = loompa.favorite.color
            tvFavoriteFood.text = loompa.favorite.food
            tvFavoriteSong.text = loompa.favorite.song
        }
    }

    private fun changeFavoriteViews(favoriteModeOn: Boolean?) {
        fun showFavoriteViews(){
            with(viewBinding){
                ivColorIcon.visibility = View.VISIBLE
                ivFoodIcon.visibility = View.VISIBLE
                ivSongIcon.visibility = View.VISIBLE
                tvFavoriteSong.visibility = View.VISIBLE
                tvFavoriteFood.visibility = View.VISIBLE
                tvFavoriteColor.visibility = View.VISIBLE
                tvLoompaName.visibility = View.GONE
                ivMainImage.visibility = View.GONE
                ivBlur.visibility = View.GONE
                ivFavoriteStar.setImageDrawable(getAppDrawable(R.drawable.ic_close))
            }
        }

        fun hideFavoriteViews(){
            with(viewBinding) {
                ivColorIcon.visibility = View.GONE
                ivFoodIcon.visibility = View.GONE
                ivSongIcon.visibility = View.GONE
                tvFavoriteSong.visibility = View.GONE
                tvFavoriteFood.visibility = View.GONE
                tvFavoriteColor.visibility = View.GONE
                tvLoompaName.visibility = View.VISIBLE
                ivMainImage.visibility = View.VISIBLE
                ivBlur.visibility = View.VISIBLE
                ivFavoriteStar.setImageDrawable(getAppDrawable(R.drawable.ic_star))
            }
        }

        favoriteModeOn?.let { isFavoriteModeOn ->
            if(isFavoriteModeOn){
                showFavoriteViews()
            }else{
                hideFavoriteViews()
            }
        }
    }

    override fun onDetach() {
        _viewBinding = null
        super.onDetach()
    }
}