package com.qm.cleanmodule.ui.fragment.home.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.qm.cleanmodule.R
import com.qm.cleanmodule.base.network.HandleResponseData
import com.qm.cleanmodule.base.viewmodel.AndroidBaseViewModel
import com.qm.cleanmodule.ui.fragment.home.HomeAdapter
import com.qm.cleanmodule.ui.fragment.home.domain.HomeRepository
import com.qm.cleanmodule.ui.fragment.home.model.HomeResponse
import com.qm.cleanmodule.ui.fragment.home.model.HomeUIItem
import com.qm.cleanmodule.util.AppUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// HomeViewModel @Docs
@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val homeRepository: HomeRepository
) : AndroidBaseViewModel(app) {

    private var usersLivedata = MediatorLiveData<HandleResponseData<HomeResponse>>()

    val adapter = HomeAdapter(::onItemClick)

    private fun onItemClick(homeItem: HomeUIItem) {
        setValue(homeItem)
    }

    fun loadDataOnAdapter(results: List<HomeUIItem?>?)
    {
        results?.let {
            adapter.setList(it)
        }
    }

    init {
        getData()
    }

    private fun getData() {
        if (!AppUtil.isNetworkAvailable(app)) {
            usersLivedata.postValue(HandleResponseData.error(msg = app.getString(R.string.network_error)))
            return
        }

        usersLivedata = homeRepository.getUsers(1)
        usersLivedata.postValue(HandleResponseData.loading())
    }

    fun observeUsers(): LiveData<HandleResponseData<HomeResponse>> = usersLivedata
}