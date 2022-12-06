package com.aresudev.loompapp.features.filter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aresudev.loompapp.R
import com.aresudev.loompapp.commons.business.usecase.FilterLoompaUseCase
import com.aresudev.loompapp.commons.business.usecase.GetAllLoompasUseCase
import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.core.extensions.default
import com.aresudev.loompapp.core.extensions.getAppString
import com.aresudev.loompapp.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoompaListFragmentViewModel @Inject constructor(
    private val getAllLoompasUseCase: GetAllLoompasUseCase,
    private val filterLoompaUseCase: FilterLoompaUseCase
) : ViewModel() {

    companion object {
        private const val INITIAL_PAGE = 1
        private const val LAST_PAGE = 20
    }

    private val _isScreenLoading = MutableLiveData<Boolean>()
    val isScreenLoading: LiveData<Boolean> get() = _isScreenLoading
    private val _loompaList = MutableLiveData<List<LoompaModel>>()
    val loompaList: LiveData<List<LoompaModel>> get() = _loompaList
    private val _genderKeyList = MutableLiveData<List<String>>()
    val genderKeyList: LiveData<List<String>> get() = _genderKeyList
    private val _professionKeyList = MutableLiveData<List<String>>()
    val professionKeyList: LiveData<List<String>> get() = _professionKeyList
    private val _currentPage = MutableLiveData<Int>().default(INITIAL_PAGE)
    val currentPage: LiveData<Int> get() = _currentPage
    private val _alertMessage = MutableLiveData<String>()
    val alertMessage: LiveData<String> get() = _alertMessage
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    var professionFilter: String? = null
    var genderFilter: String? = null

    fun loadScreen() {
        getLoompaList()
    }

    private fun getLoompaList() {
        fun getGenderKeys(loompaList: List<LoompaModel>): List<String> =
            loompaList.distinctBy { it.gender }.map { it.gender }

        fun getProfessionKeys(loompaList: List<LoompaModel>): List<String> =
            loompaList.distinctBy { it.profession }.map { it.profession }

        viewModelScope.launch {
            _isScreenLoading.value = true
            val result = if (_currentPage.value != null) {
                async { getAllLoompasUseCase(_currentPage.value!!)  }
            } else {
                async { getAllLoompasUseCase() }
            }

            try {
                when (result.await()) {
                    is Resource.Success -> {
                        result.await().data?.let { loompaPageModel ->
                            _loompaList.postValue(loompaPageModel.loompaList)
                            _genderKeyList.postValue(getGenderKeys(loompaPageModel.loompaList))
                            _professionKeyList.postValue(getProfessionKeys(loompaPageModel.loompaList))
                        }
                    }
                    is Resource.Error -> {
                        result.await().errorMessage?.let { _errorMessage.postValue(it) }
                    }
                }
            } catch (e: Exception) {

            } finally {
                _isScreenLoading.value = false
            }
        }
    }

    fun nextPage() {
        currentPage.value?.let { currentPage ->
            if (currentPage < LAST_PAGE) {
                _currentPage.postValue(currentPage + 1)
            } else {
                _alertMessage.postValue(getAppString(R.string.last_page_warning))
            }
        }
    }

    fun previousPage() {
        currentPage.value?.let { currentPage ->
            if (currentPage > INITIAL_PAGE) {
                _currentPage.postValue(currentPage - 1)
            } else {
                _alertMessage.postValue(getAppString(R.string.first_page_warning))
            }
        }
    }

    fun filterLoompas() {
        _currentPage.value?.let { currentPage ->
            viewModelScope.launch {
                _isScreenLoading.value = true
                val result =
                    filterLoompaUseCase(
                        page = currentPage,
                        gender = genderFilter,
                        profession = professionFilter
                    )

                when (result) {
                    is Resource.Success -> {
                        result.data?.let { _loompaList.postValue(it) }
                    }
                    is Resource.Error -> {
                        result.errorMessage?.let { _errorMessage.postValue(it) }
                    }
                }
                _isScreenLoading.value = false
            }
        }
    }
}