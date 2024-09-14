package com.example.example

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST(value = "footscray/auth")
    
    @GET(value = "art")
    fun getData(): Call<List<Entity>>


}