package br.com.lucolimac.repositories.data.di

import android.util.Log
import br.com.lucolimac.repositories.data.repository.RepositoryRepository
import br.com.lucolimac.repositories.data.repository.RepositoryRepositoryImpl
import br.com.lucolimac.repositories.data.service.GithubService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    private const val OK_HTTP = "OkHttp"
    fun load() {
        loadKoinModules(networkModule() + repositoriesModule())
    }

    private fun networkModule(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.i(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            }
            single { GsonConverterFactory.create(GsonBuilder().create()) }
            single {
                createService<GithubService>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<RepositoryRepository> { RepositoryRepositoryImpl(get()) }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient, factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder().baseUrl("https://api.github.com/").client(client)
            .addConverterFactory(factory).build().create(T::class.java)
    }
}