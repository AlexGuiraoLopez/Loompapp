package com.aresudev.loompapp.features.filter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aresudev.loompapp.R
import com.aresudev.loompapp.commons.business.usecase.GetAllLoompasUseCase
import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.core.extensions.default
import com.aresudev.loompapp.core.extensions.getAppString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoompaListFragmentViewModel @Inject constructor(private val getAllLoompasUseCase: GetAllLoompasUseCase) : ViewModel() {

    companion object {
        private const val INITIAL_PAGE = 1
        private const val LAST_PAGE = 20
    }

    private val _loompaList = MutableLiveData<List<LoompaModel>>()
    val loompaList: LiveData<List<LoompaModel>> get() = _loompaList
    private val _currentPage = MutableLiveData<Int>().default(INITIAL_PAGE)
    val currentPage: LiveData<Int> get() = _currentPage
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    fun loadScreen() {
        getLoompaList()
    }

    private fun getLoompaList() {
        //ToDo: Control errors.
        viewModelScope.launch {
            val result = if (_currentPage.value != null) {
                getAllLoompasUseCase(_currentPage.value!!)
            } else {
                getAllLoompasUseCase()
            }

            _loompaList.postValue(result.loompaList)
        }
    }

    fun nextPage() {
        currentPage.value?.let { currentPage ->
            if (currentPage < LAST_PAGE){
                _currentPage.postValue(currentPage + 1)
            }else{
                _errorMessage.postValue(getAppString(R.string.last_page_warning))
            }
        }
    }

    fun previousPage() {
        currentPage.value?.let { currentPage ->
            if (currentPage > INITIAL_PAGE){
                _currentPage.postValue(currentPage - 1)
            }else{
                _errorMessage.postValue(getAppString(R.string.first_page_warning))
            }
        }
    }

}