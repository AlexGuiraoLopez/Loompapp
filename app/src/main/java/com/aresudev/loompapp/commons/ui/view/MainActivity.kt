package com.aresudev.loompapp.commons.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aresudev.loompapp.R
import com.aresudev.loompapp.databinding.ActivityMainBinding
import com.aresudev.loompapp.commons.callbacks.FragmentNavigator
import com.aresudev.loompapp.core.extensions.changeCurrentFragment
import com.aresudev.loompapp.features.filter.ui.view.DetailFragment
import com.aresudev.loompapp.features.filter.ui.view.LoompaListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentNavigator {

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var fLoompaList: LoompaListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initFragment()
    }

    private fun initFragment() {
        fLoompaList = LoompaListFragment()
        fLoompaList.navigator = this
        supportFragmentManager.changeCurrentFragment(R.id.fcvMainFragmentContainer, fLoompaList)
    }

    override fun navigateToFilterFragment() {
        supportFragmentManager.changeCurrentFragment(R.id.fcvMainFragmentContainer, fLoompaList)
    }

    override fun navigateToDetailFragment(loompaId: Int) {
        val fDetail = DetailFragment(loompaId)
        fDetail.navigator = this
        supportFragmentManager.changeCurrentFragment(R.id.fcvMainFragmentContainer, fDetail)
    }

    override fun onBackPressed() {
        when (supportFragmentManager.fragments.last()) {
            is DetailFragment -> fLoompaList?.let { supportFragmentManager.changeCurrentFragment(R.id.fcvMainFragmentContainer, it) }
            else -> super.onBackPressed()
        }
    }
}