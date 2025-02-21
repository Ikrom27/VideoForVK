package ru.ikrom.videoforvk.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ikrom.video_usecase.IRepository
import ru.ikrom.video_usecase.VideoUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class VideoModule {
    @Provides
    @Singleton
    fun provideVideoUseCase(
        repository: IRepository
    ) = VideoUseCase(repository)
}