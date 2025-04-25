package com.seraphim.pokemon

import android.app.Application
import com.seraphim.pokemon.di.pokemonModule
import com.seraphim.shared.di.sharedCommonModule
import com.seraphim.shared.di.sharedAndroidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PokemonApplication)
            modules(sharedCommonModule + sharedAndroidModule + pokemonModule)
        }
    }
}