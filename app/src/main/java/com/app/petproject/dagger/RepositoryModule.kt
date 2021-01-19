package com.app.petproject.dagger

import com.app.petproject.model.RestApi
import com.app.petproject.repository.IMainRepository
import com.app.petproject.repository.RemoteDataSource
import com.app.petproject.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): IMainRepository {
        return Repository(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideDataSource(restApi: RestApi): RemoteDataSource {
        return RemoteDataSource(restApi)
    }


}