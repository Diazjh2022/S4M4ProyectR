package com.example.s4m4proyectr.repository.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NoticiasAPIService {

    @Headers(
        value = [
            "X-Api-Key: c62c620ddec44bafb7d11f4ccc5616c0",
            "content-type: application/json; charset=utf-8"
        ]
    )
    @GET("everything?q=chile")
    fun obtenerNoticias(@Query("language") languages: String): Call<Noticias>

}