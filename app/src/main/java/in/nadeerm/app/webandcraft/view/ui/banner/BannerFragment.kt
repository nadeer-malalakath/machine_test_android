package `in`.nadeerm.app.webandcraft.view.ui.banner

import `in`.nadeerm.app.webandcraft.Utils.Status
import `in`.nadeerm.app.webandcraft.databinding.FragmentBannerBinding
import `in`.nadeerm.app.webandcraft.view.adapter.BannerTableViewAdapter
import `in`.nadeerm.app.webandcraft.view.adapter.CategoryTableViewAdapter
import `in`.nadeerm.app.webandcraft.view.adapter.ProductsTableViewAdapter
import `in`.nadeerm.app.webandcraft.view.ui.base.BaseFragment
import `in`.nadeerm.app.webandcraft.viewmodel.BannerViewModel
import android.app.DownloadManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class BannerFragment : BaseFragment(), BannerTableViewAdapter.BannerListener,
    CategoryTableViewAdapter.CategoryListener, ProductsTableViewAdapter.ProductsListener {

    private val bannerViewModel : BannerViewModel by viewModel()
    private val adapter: BannerTableViewAdapter by inject()
    private val adapter2: CategoryTableViewAdapter by inject()
    private val adapter3: ProductsTableViewAdapter by inject()
    private var _binding: FragmentBannerBinding? = null
    private val binding get() = _binding


    companion object {
        @JvmStatic
        fun newInstance() =
            BannerFragment().apply {
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
        _binding = FragmentBannerBinding.inflate(inflater, container, false)
        binding?.bannerViewModel = bannerViewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun setUp() {
        listenResponse()
        listenResponse2()
        listenResponse3()
        bannerViewModel.getBanners()
        bannerViewModel.getCategory()
        bannerViewModel.getProducts()
        binding?.rvBanner?.adapter = adapter
        binding?.bannerRv?.adapter = adapter2
        binding?.rvProducts?.adapter = adapter3
        adapter.setBannerListener(this)
        adapter2.setCategoryListener(this)
        adapter3.setProductsListener(this)
    }

    private fun listenResponse() {
        bannerViewModel.getResponse().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                    it.data?.let { response ->
                        showBaseView()
                        adapter.addItems(response.homeData[0].values)
                        //println("===hhhh====${response.homeData[0].values[1].banner_url}=====")
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

    private fun listenResponse2() {
        bannerViewModel.getResponseCategory().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                    it.data?.let { response ->
                        showBaseView()
                        adapter2.addItems(response.homeData[1].values)
                        //println("===hhhh====${response.homeData[0].values[1].banner_url}=====")
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

    private fun listenResponse3() {
        bannerViewModel.getResponseProducts().observe(viewLifecycleOwner, {
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