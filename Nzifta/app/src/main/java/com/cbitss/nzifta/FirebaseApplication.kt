package com.cbitss.nzifta

import android.app.Application
import com.cbitss.nzifta.data.firebase.FirebaseFiles
import com.cbitss.nzifta.data.repositries.Repositry
import com.cbitss.nzifta.modelfactries.*

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
class FirebaseApplication : Application() ,KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplication))

        bind() from singleton { FirebaseFiles() }
        bind() from singleton { Repositry(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { RegisterViewModelFactory(instance()) }
        bind() from provider { CategoryViewModelFactory(instance()) }
        bind() from provider { DetailScreenModelFactory(instance()) }
        bind() from provider { ApplyScreenModelFactory(instance()) }

    }
}