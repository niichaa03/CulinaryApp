package com.niichaa.core.di

import android.content.Context
import androidx.room.Room
import com.niichaa.core.data.source.local.room.CulinaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CulinaryDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("niichaa".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            CulinaryDatabase::class.java, "Culinary.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    fun provideCulinaryDao(database: CulinaryDatabase) = database.culinaryDao()

}