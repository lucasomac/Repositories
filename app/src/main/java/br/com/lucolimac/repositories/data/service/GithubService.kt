package br.com.lucolimac.repositories.data.service

import br.com.lucolimac.repositories.data.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): List<Repository>
}