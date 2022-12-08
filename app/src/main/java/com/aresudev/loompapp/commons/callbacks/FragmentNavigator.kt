package com.aresudev.loompapp.commons.callbacks

interface FragmentNavigator {
    fun navigateToFilterFragment()
    fun navigateToDetailFragment(loompaId: Int)
}