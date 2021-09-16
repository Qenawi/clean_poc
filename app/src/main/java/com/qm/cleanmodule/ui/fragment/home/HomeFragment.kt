package com.qm.cleanmodule.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.qm.cleanmodule.base.network.Status.*
import com.qm.cleanmodule.base.view.BaseFragment
import com.qm.cleanmodule.databinding.FragmentHomeBinding
import com.qm.cleanmodule.ui.fragment.home.viewmodel.HomeViewModel
import com.qm.cleanmodule.util.DialogsExtensions.showErrorDialog
import com.qm.cleanmodule.util.observe
import dagger.hilt.android.AndroidEntryPoint

// HomeFragment  @Docs
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun pageTitle(): String = ""

    override val mViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            //observe on ui to let base handle loading / errors  if any
            observe(observeUsers())
            {
                when (it?.status) {
                    SUCCESS -> {
                        showProgress(false)
                        loadDataOnAdapter(it.data?.results?.mapNotNull { a -> a?.toHomeItemUI() })
                    }
                    MESSAGE -> {
                        showProgress(false)
                        activity?.showErrorDialog(it.message)
                    }
                    LOADING -> {
                        showProgress()
                    }
                }
            }
        }
    }
}