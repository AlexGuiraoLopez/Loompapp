package com.aresudev.loompapp.commons.ui.view

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aresudev.loompapp.R
import com.aresudev.loompapp.databinding.ActivityMainBinding
import com.aresudev.loompapp.commons.callbacks.FragmentNavigator
import com.aresudev.loompapp.core.extensions.changeCurrentFragment
import com.aresudev.loompapp.features.filter.ui.view.FilterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentNavigator {

    private lateinit var viewBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.changeCurrentFragment(R.id.fcvMainFragmentContainer, FilterFragment())
    }

    override fun navigateToFilterFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToDetailFragment(loompaId: Int) {
        TODO("Not yet implemented")
    }
}