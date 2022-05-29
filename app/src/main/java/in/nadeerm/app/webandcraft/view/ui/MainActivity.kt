package `in`.nadeerm.app.webandcraft.view.ui

import `in`.nadeerm.app.webandcraft.R
import `in`.nadeerm.app.webandcraft.databinding.ActivityMainBinding
import `in`.nadeerm.app.webandcraft.view.ui.base.BaseActivity
import `in`.nadeerm.app.webandcraft.viewmodel.MainViewModel
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity()  {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var manager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
        setUp()
    }

    override fun setUp() {
        manager = supportFragmentManager
        loadDashboard()
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> loadBottomItems(HomeFragment.newInstance())


            }
            true
        }

    }

    private fun loadDashboard() {
        val transaction = manager.beginTransaction()
        transaction.add(R.id.main_container, HomeFragment.newInstance())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    private fun loadBottomItems(fragment: Fragment) {
        val count = manager.backStackEntryCount
        for (i in 0 until count) {
            manager.popBackStack()
        }

        val transaction = manager.beginTransaction()
        transaction.add(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
    }


}