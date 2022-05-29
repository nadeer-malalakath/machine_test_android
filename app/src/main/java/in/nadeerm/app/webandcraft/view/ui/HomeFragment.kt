package `in`.nadeerm.app.webandcraft.view.ui

import `in`.nadeerm.app.webandcraft.Utils.Status
import `in`.nadeerm.app.webandcraft.databinding.FragmentHomeBinding
import `in`.nadeerm.app.webandcraft.view.adapter.*
import `in`.nadeerm.app.webandcraft.view.ui.base.BaseFragment
import `in`.nadeerm.app.webandcraft.viewmodel.HomeDataViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment(), BannerAdapter.BannerListener,
    CategoryAdapter.CategoryListener, ProductsAdapter.ProductsListener {

    private val homeDataViewModel : HomeDataViewModel by viewModel()
    private val adapter2: BannerAdapter by inject()
    private val adapter: CategoryAdapter by inject()
    private val adapter3: ProductsAdapter by inject()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
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
        listenBannerResponse()
        listenCategoryResponse()
        listenProductsResponse()
        homeDataViewModel.getBanners()
        homeDataViewModel.getCategory()
        homeDataViewModel.getProducts()
        binding?.rvBanner?.adapter = adapter2
        binding?.rvCategory?.adapter = adapter
        binding?.rvProducts?.adapter = adapter3
        adapter2.setBannerListener(this)
        adapter.setCategoryListener(this)
        adapter3.setProductsListener(this)
    }

    private fun listenBannerResponse() {
        homeDataViewModel.getResponseBanners().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                    it.data?.let { response ->
                        showBaseView()
                        adapter2.addItems(response.homeData[1].values)
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
                }
            }
        })
    }

    private fun listenCategoryResponse() {
        homeDataViewModel.getResponseCategory().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                    it.data?.let { response ->
                        showBaseView()
                        adapter.addItems(response.homeData[0].values)

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
                }
            }
        })
    }

    private fun listenProductsResponse() {
        homeDataViewModel.getResponseProducts().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                    it.data?.let { response ->
                        showBaseView()
                        adapter3.addItems(response.homeData[2].values)


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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}