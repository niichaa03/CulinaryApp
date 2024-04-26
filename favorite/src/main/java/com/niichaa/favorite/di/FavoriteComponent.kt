package com.niichaa.favorite.di

import android.content.Context
import com.niichaa.culinaryapp.di.FavoriteDependencies
import com.niichaa.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteDependencies::class])
interface FavoriteComponent {

    fun inject(favoriteFragment: FavoriteFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteDependencies: FavoriteDependencies): Builder
        fun build(): FavoriteComponent
    }

}