package com.cbitss.nziftaadmin

import android.app.Application
import com.cbitss.nziftaadmin.data.firebase.FirebaseSource
import com.cbitss.nziftaadmin.data.repositries.Repositry
import com.cbitss.nziftaadmin.modelfactories.AppliedUserViewModelFactory
import com.cbitss.nziftaadmin.modelfactories.ContentViewModelFactory
import com.cbitss.nziftaadmin.modelfactories.InsertUsrtypeModelFactory
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

        bind() from singleton { FirebaseSource() }
        bind() from singleton { Repositry(instance()) }
        bind() from provider { InsertUsrtypeModelFactory(instance()) }
        bind() from provider { ContentViewModelFactory(instance()) }
        bind() from provider { AppliedUserViewModelFactory(instance()) }
    }
}
