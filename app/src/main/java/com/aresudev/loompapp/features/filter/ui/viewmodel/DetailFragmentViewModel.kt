package com.aresudev.loompapp.features.filter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aresudev.loompapp.commons.business.usecase.GetLoompaByIdUseCase
import com.aresudev.loompapp.commons.data.model.LoompaModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(private val getLoompaByIdUseCase: GetLoompaByIdUseCase) : ViewModel() {

    private val _currentLoompa = MutableLiveData<LoompaModel>()
    val currentLoompa: LiveData<LoompaModel> get() = _currentLoompa
    private val _isFavoriteModeOn = MutableLiveData<Boolean>()
    val isFavoriteModeOn: LiveData<Boolean> get() = _isFavoriteModeOn

    fun loadScreen(idLoompa: Int) {
        _isFavoriteModeOn.postValue(false)
        getLoompaInfo(idLoompa)
    }

    private fun getLoompaInfo(idLoompa: Int) {
        viewModelScope.launch {
            val result = getLoompaByIdUseCase(idLoompa)
            _currentLoompa.postValue(result)
        }
    }

    fun changeFavoriteMode() {
        _isFavoriteModeOn.value?.let { inFavoriteMode ->
            if (inFavoriteMode) {
                _isFavoriteModeOn.postValue(false)
            } else {
                _isFavoriteModeOn.postValue(true)
            }
        } ?: _isFavoriteModeOn.postValue(true)

    }
}