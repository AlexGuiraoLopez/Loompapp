package com.aresudev.loompapp.features.filter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aresudev.loompapp.commons.business.usecase.GetAllLoompasUseCase
import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.core.extensions.default
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterFragmentViewModel @Inject constructor(private val getAllLoompasUseCase: GetAllLoompasUseCase) : ViewModel() {

    companion object {
        private const val INITIAL_PAGE = 1
    }

    private val _loompaList = MutableLiveData<List<LoompaModel>>()
    val loompaList: LiveData<List<LoompaModel>> get() = _loompaList
    private val _currentPage = MutableLiveData<Int>().default(INITIAL_PAGE)
    val currentPage: LiveData<Int> get() = _currentPage

    fun loadScreen() {
        getLoompaList()
    }

    private fun getLoompaList() {
        //ToDo: Control errors.
        viewModelScope.launch {
            val result = if(_currentPage.value != null){
                getAllLoompasUseCase(_currentPage.value!!)
            }else{
                getAllLoompasUseCase()
            }

            _loompaList.postValue(result.loompaList)
        }
    }

    //ToDo: Check last available page to stop.
    fun nextPage() {
        _currentPage.postValue(currentPage.value!! + 1)
    }

}