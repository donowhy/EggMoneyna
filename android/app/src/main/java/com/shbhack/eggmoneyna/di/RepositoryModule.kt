package com.shbhack.eggmoneyna.di

import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import com.shbhack.eggmoneyna.data.repository.main.MainRepositoryImpl
import com.shbhack.eggmoneyna.data.repository.parent.ParentRepository
import com.shbhack.eggmoneyna.data.repository.parent.ParentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsMainRepository(
        repositoryImpl: MainRepositoryImpl
    ): MainRepository

    @Binds
    abstract fun bindsParentRepository(
        repositoryImpl: ParentRepositoryImpl
    ): ParentRepository

}