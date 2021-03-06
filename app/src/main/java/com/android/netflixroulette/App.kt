package com.android.netflixroulette

import android.app.Application
import com.android.netflixroulette.data.database.MovieDatabase
import com.android.netflixroulette.network.NetworkDataSource
import com.android.netflixroulette.network.NetworkDataSourceImpl
import com.android.netflixroulette.network.TheMovieDBApiService
import com.android.netflixroulette.network.repository.Repository
import com.android.netflixroulette.network.repository.RepositoryImpl
import com.android.netflixroulette.ui.view_model.SharedViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { MovieDatabase(instance()) }
        bind() from singleton { instance<MovieDatabase>().movieDao() }
        bind() from singleton { TheMovieDBApiService() }
        bind<NetworkDataSource>() with singleton { NetworkDataSourceImpl(instance()) }
        bind<Repository>() with singleton { RepositoryImpl(instance(), instance()) }
        bind() from provider { SharedViewModelFactory(instance()) }
    }
}