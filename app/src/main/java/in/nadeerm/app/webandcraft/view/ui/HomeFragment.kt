package `in`.nadeerm.app.webandcraft.view.ui

import `in`.nadeerm.app.webandcraft.Utils.Status
import `in`.nadeerm.app.webandcraft.databinding.FragmentHomeBinding
import `in`.nadeerm.app.webandcraft.view.callback.MainCallBackListener
import `in`.nadeerm.app.webandcraft.view.ui.base.BaseFragment
import `in`.nadeerm.app.webandcraft.viewmodel.HomeDataViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment:BaseFragment() {

    private var _binding: FragmentHomeBinding ?= null
    private val binding get() = _binding
    private val homeDataViewModel: HomeDataViewModel by viewModel()
    private var listener : MainCallBackListener? = null

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    override fun onPause() {
        super.onPause()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.homeDataViewModel = homeDataViewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }


    override fun setUp() {
        listenResponse()
     homeDataViewModel.getBanners()

    }


    private fun listenResponse() {
        homeDataViewModel.getResponse().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                    it.data?.let { response ->
                        showBaseView()
                       println("=======${response.homeData[0].values[0].id}=====")

                        //binding?.userName1?.text = response.name
                        //Picasso.get().load(response.image).into(binding?.profileImage)
                        //binding?.designationHome?.text = response.designation
                        //binding?.departmentHome?.text = response.department

                    }
                }
                Status.LOADING -> {
                    showProgress()
                }
                Status.ERROR -> {
                    //Handle Error
                    hideProgress()
                    showBaseView()
                }
                Status.EXCEPTION -> {
                    hideProgress()
                    showBaseView()
                    //binding?.rvMaterials?.showErrorToast(it.message!!)
                }
            }
        })
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showBaseView() {

    }

    override fun hideBaseView() {

    }
}