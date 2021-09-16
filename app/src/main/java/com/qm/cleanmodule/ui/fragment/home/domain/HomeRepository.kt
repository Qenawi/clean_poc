package com.qm.cleanmodule.ui.fragment.home.domain

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.qm.cleanmodule.base.network.HandleResponseData
import com.qm.cleanmodule.base.network.IsRepo
import com.qm.cleanmodule.data.remote.ApiService
import com.qm.cleanmodule.ui.fragment.home.model.HomeResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

// HomeRepository  @Docs
class HomeRepository @Inject constructor(private val apiService: ApiService) : IsRepo {

    fun getUsers(page: Int): MediatorLiveData<HandleResponseData<HomeResponse>> {
        val userLiveData = MediatorLiveData<HandleResponseData<HomeResponse>>()
        val source = LiveDataReactiveStreams.fromPublisher(
            apiService.getChars(page)
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    HomeResponse(code = 401, message = it.localizedMessage)
                }
                .map {
                    if (it.code == 200)
                        return@map HandleResponseData.success(it)
                    else
                        return@map HandleResponseData.error(it.message)
                }
        )
        userLiveData.addSource(source) {
            userLiveData.value = it
            userLiveData.removeSource(source)
        }
        return userLiveData
    }
}
