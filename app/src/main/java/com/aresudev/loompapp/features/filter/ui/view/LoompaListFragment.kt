package com.aresudev.loompapp.features.filter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aresudev.loompapp.R
import com.aresudev.loompapp.commons.callbacks.FragmentNavigator
import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.databinding.FragmentLoompaListBinding
import com.aresudev.loompapp.commons.ui.base.BaseFragment
import com.aresudev.loompapp.core.extensions.showToast
import com.aresudev.loompapp.core.utils.ui.Space
import com.aresudev.loompapp.features.filter.ui.adapter.LoompaRvAdapter
import com.aresudev.loompapp.features.filter.ui.viewmodel.LoompaListFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoompaListFragment : BaseFragment() {

    companion object {
        private const val RV_ITEM_SEPARATION = 10
    }

    private var _viewBinding: FragmentLoompaListBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: LoompaListFragmentViewModel by activityViewModels()

    @Inject
    lateinit var loompaAdapter: LoompaRvAdapter

    lateinit var navigator: FragmentNavigator

    override fun initViewModel() {
        viewModel.loadScreen()
    }

    override fun initViewModelObservers() {
        with(viewModel) {
            loompaList.observe(viewLifecycleOwner) { loompaList -> loompaAdapter.setCollection(loompaList) }
            genderKeyList.observe(viewLifecycleOwner) { genderKeys -> initGenderFilters(genderKeys) }
            professionKeyList.observe(viewLifecycleOwner) { professionKeys -> initProfessionFilters(professionKeys) }
            currentPage.observe(viewLifecycleOwner) { setPageNumber(it); loadScreen() }
            errorMessage.observe(viewLifecycleOwner) { errorMessage -> requireActivity().showToast(errorMessage) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = FragmentLoompaListBinding.inflate(inflater, container, false)
        initLists()
        initEvents()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelObservers()
        initViewModel()
    }

    private fun initGenderFilters(genderKeys: List<String>) {
        with(viewBinding) {
            val genderSpinnerItems = resources.getStringArray(R.array.gender_string_array).toMutableList()
            genderKeys.onEach { genderSpinnerItems.add(it) }
            val genderSpinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, genderSpinnerItems)

            spnFilterGender.adapter = genderSpinnerAdapter
        }
    }

    private fun initProfessionFilters(professionKeys: List<String>) {
        with(viewBinding) {
            val professionSpinnerItems = resources.getStringArray(R.array.profession_string_array).toMutableList()
            professionKeys.onEach { professionSpinnerItems.add(it) }
            val professionSpinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, professionSpinnerItems)

            spnFilterProfession.adapter = professionSpinnerAdapter
        }
    }


    private fun initLists() {
        with(viewBinding) {
            rvLoompaList.adapter = loompaAdapter
            rvLoompaList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvLoompaList.addItemDecoration(Space(RV_ITEM_SEPARATION))
            loompaAdapter.setOnLoompaClickListener(object : LoompaRvAdapter.LoompaItemClickListener {
                override fun onLoompaClick(loompa: LoompaModel) {
                    navigator.navigateToDetailFragment(loompa.id)
                }
            })
        }
    }

    private fun initEvents() {
        with(viewBinding) {
            ivNextPage.setOnClickListener {
                viewModel.nextPage()
            }
            ivPreviousPage.setOnClickListener {
                viewModel.previousPage()
            }
        }
    }

    private fun setPageNumber(pageNumber: Int) {
        with(viewBinding) {
            tvPageNumber.text = getString(R.string.page) + ": " + pageNumber.toString()
        }
    }

    override fun onDetach() {
        _viewBinding = null
        super.onDetach()
    }
}