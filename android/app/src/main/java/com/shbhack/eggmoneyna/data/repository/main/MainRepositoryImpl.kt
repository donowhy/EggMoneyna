package com.shbhack.eggmoneyna.data.repository.main

import com.shbhack.eggmoneyna.data.remote.ApiService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository {

}