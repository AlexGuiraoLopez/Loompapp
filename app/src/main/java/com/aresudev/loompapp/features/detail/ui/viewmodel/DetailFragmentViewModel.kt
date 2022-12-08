package com.aresudev.loompapp.features.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aresudev.loompapp.commons.business.usecase.GetLoompaByIdUseCase
import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.core.error.ExceptionHandler
import com.aresudev.loompapp.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(private val getLoompaByIdUseCase: GetLoompaByIdUseCase) : ViewModel() {

    private val _isScreenLoading = MutableLiveData<Boolean>()
    val isScreenLoading: LiveData<Boolean> get() = _isScreenLoading
    private val _currentLoompa = MutableLiveData<LoompaModel>()
    val currentLoompa: LiveData<LoompaModel> get() = _currentLoompa
    private val _isFavoriteModeActive = MutableLiveData<Boolean>()
    val isFavoriteModeActive: LiveData<Boolean> get() = _isFavoriteModeActive
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun loadScreen(idLoompa: Int) {
        _isFavoriteModeActive.postValue(false)
        getLoompaInfo(idLoompa)
    }

    private fun getLoompaInfo(idLoompa: Int) {
        viewModelScope.launch {
            _isScreenLoading.value = true
            val result = async { getLoompaByIdUseCase(idLoompa) }
            try {
                when (result.await()) {
                    is Resource.Success -> {
                        result.await().data?.let { _currentLoompa.postValue(it) }
                    }
                    is Resource.Error -> {
                        result.await().errorMessage?.let { _errorMessage.postValue(it) }
                    }
                }
            }catch (ex:Exception){
                _errorMessage.postValue(ExceptionHandler.sendExceptionFeedbackMessage(ex))
            }finally {
                _isScreenLoading.value = false
            }
        }
    }

    fun changeFavoriteMode() {
        _isFavoriteModeActive.value?.let { inFavoriteMode ->
            if (inFavoriteMode) {
                _isFavoriteModeActive.postValue(false)
            } else {
                _isFavoriteModeActive.postValue(true)
            }
        } ?: _isFavoriteModeActive.postValue(true)
    }
}