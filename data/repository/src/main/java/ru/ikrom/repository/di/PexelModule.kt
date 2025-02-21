package ru.ikrom.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ikrom.repository.BuildConfig
import ru.ikrom.repository.PexelRepository
import ru.ikrom.repository.remote_datasource.PexelApi
import ru.ikrom.repository.remote_datasource.PexelDataSource
import ru.ikrom.video_usecase.IRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class PexelModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", BuildConfig.PEXEL_API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.pexels.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePexelApi(
       retrofit: Retrofit
    ) = retrofit.create(PexelApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(
        dataSource: PexelDataSource
    ): IRepository = PexelRepository(dataSource)
}