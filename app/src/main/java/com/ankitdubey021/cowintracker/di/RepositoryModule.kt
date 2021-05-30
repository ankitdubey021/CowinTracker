package com.ankitdubey021.cowintracker.di

import com.ankitdubey021.cowintracker.networking.CovidReportApiClient
import com.ankitdubey021.cowintracker.networking.CowinApiClient
import com.ankitdubey021.cowintracker.repository.CovidReportRepository
import com.ankitdubey021.cowintracker.repository.CowinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiClient: CowinApiClient
    ): CowinRepository{
        return CowinRepository(apiClient)
    }


    @Singleton
    @Provides
    fun provideCovidReportRepository(
        apiClient: CovidReportApiClient
    ): CovidReportRepository{
        return CovidReportRepository(apiClient)
    }

}