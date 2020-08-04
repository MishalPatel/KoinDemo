package com.mishal.koindemo.data.rest

import com.mishal.koindemo.data.model.Repo
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

interface RepoService {
    @GET("movie/popular")
    fun getRepositories(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Repo>

    @Multipart
    @POST("upload")
    fun getFileUpload(@Part("dec") des: RequestBody, @Part multipart: Multipart)
}