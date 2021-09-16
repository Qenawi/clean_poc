package com.qm.cleanmodule.data.remote

import com.qm.cleanmodule.ui.fragment.home.model.HomeResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ninja on 6/24/2020.
 **/

interface ApiService {

  @GET("character")
//  fun getChars(@Query("page") page: Int): Flowable<Resource<HomeResponse>>
  fun getChars(@Query("page") page: Int): Flowable<HomeResponse>
}