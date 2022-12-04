package com.aresudev.loompapp.features.filter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aresudev.loompapp.R
import com.aresudev.loompapp.databinding.FragmentLoompaListBinding
import com.aresudev.loompapp.commons.ui.base.BaseFragment
import com.aresudev.loompapp.core.utils.ui.Space
import com.aresudev.loompapp.features.filter.ui.adapter.LoompaRvAdapter
import com.aresudev.loompapp.features.filter.ui.viewmodel.FilterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FilterFragment : BaseFragment() {

    companion object {
        private const val RV_ITEM_SEPARATION = 10
    }

    private var _viewBinding: FragmentLoompaListBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: FilterFragmentViewModel by activityViewModels()

    @Inject
    lateinit var loompaAdapter: LoompaRvAdapter

    override fun initViewModel() {
        viewModel.loadScreen()
    }

    override fun initViewModelObservers() {
        with(viewModel) {
            loompaList.observe(viewLifecycleOwner) { loompaList -> loompaAdapter.setCollection(loompaList) }
            currentPage.observe(viewLifecycleOwner) { setPageNumber(it); loadScreen() }
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

    private fun initLists() {
        with(viewBinding) {
            rvLoompaList.adapter = loompaAdapter
            rvLoompaList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvLoompaList.addItemDecoration(Space(RV_ITEM_SEPARATION))
        }
    }

    private fun initEvents() {
        with(viewBinding) {
            tvNextPage.setOnClickListener {
                viewModel.nextPage()
            }
        }
    }

    private fun setPageNumber(pageNumber: Int) {
        with(viewBinding) {
            tvPageNumber.text = getString(R.string.page) + ": " + pageNumber.toString()
        }
    }

}