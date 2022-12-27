package com.example.sepiaapp.hilt

import android.content.Context
import com.example.sepiaapp.helper.GsonHelper
import com.example.sepiaapp.repo.PetListRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PetListModule {
    @Provides
    @Singleton
    fun providePetRepository(helper: GsonHelper): PetListRepository =
        PetListRepository(helper)

    @Provides
    @Singleton
    fun provideGsonHelper(@ApplicationContext context: Context, gson: Gson): GsonHelper =
        GsonHelper(context, gson)

    @Provides
    @Singleton
    fun provideGson(): Gson =
       Gson()
}