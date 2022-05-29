package `in`.nadeerm.app.webandcraft.view.ui.base

import android.view.animation.Animation
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {


    protected abstract fun setUp()
    protected abstract fun showProgress()
    protected abstract fun hideProgress()

    protected abstract fun showBaseView()
    protected abstract fun hideBaseView()


}