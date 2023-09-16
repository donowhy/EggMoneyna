package com.shbhack.eggmoneyna.di

import com.shbhack.eggmoneyna.data.repository.comment.CommentRepository
import com.shbhack.eggmoneyna.data.repository.comment.CommentRepositoryImpl
import com.shbhack.eggmoneyna.data.repository.expense.ExpenseRepository
import com.shbhack.eggmoneyna.data.repository.expense.ExpenseRepositoryImpl
import com.shbhack.eggmoneyna.data.repository.main.MainRepository
import com.shbhack.eggmoneyna.data.repository.main.MainRepositoryImpl
import com.shbhack.eggmoneyna.data.repository.monster.MonsterRepository
import com.shbhack.eggmoneyna.data.repository.monster.MonsterRepositoryImpl
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

    @Binds
    abstract fun bindsMonsterRepository(
        repositoryImpl: MonsterRepositoryImpl
    ): MonsterRepository

    @Binds
    abstract fun bindsCommentRepository(
        repositoryImpl: CommentRepositoryImpl
    ): CommentRepository

    @Binds
    abstract fun bindsExpenseRepository(
        repositoryImpl: ExpenseRepositoryImpl
    ): ExpenseRepository
}