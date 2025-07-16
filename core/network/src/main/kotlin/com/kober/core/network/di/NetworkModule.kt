package com.kober.core.network.di

import android.content.Context
import coil.ImageLoader
import coil.util.DebugLogger
import com.kober.core.network.BuildConfig
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory {
        val githubToken: String? = BuildConfig.GITHUB_TOKEN.takeIf { it.isNotBlank() }
        return OkHttpClient.Builder()
            .apply {
                if (githubToken?.isNotBlank() == true) {
                    addInterceptor(Interceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder()
                                .header("Authorization", "token $githubToken")
                                .build()
                        )
                    })
                }
            }
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()
    }

    @Provides
    @Singleton
    fun imageLoader(
        // We specifically request dagger.Lazy here, so that it's not instantiated from Dagger.
        okHttpCallFactory: Lazy<Call.Factory>,
        @ApplicationContext application: Context,
    ): ImageLoader = ImageLoader.Builder(application)
        .callFactory { okHttpCallFactory.get() }
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }
        .build()

}
