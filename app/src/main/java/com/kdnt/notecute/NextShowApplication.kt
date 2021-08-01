package com.kdnt.notecute

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.kdnt.notecute.core.logger.initLogger
import com.kdnt.notecute.di.networkModule
import com.kdnt.notecute.di.persistenceModule
import com.kdnt.notecute.di.repositoryModule
import com.kdnt.notecute.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class NextShowApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {

        }
        this.initLogger(BuildConfig.DEBUG)
        startKoin {
            androidContext(this@NextShowApplication)
            androidLogger(
                if (BuildConfig.DEBUG) Level.DEBUG else Level.ERROR
            )
            modules(networkModule)
            modules(persistenceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
        Kotpref.init(this)

//        AdmobInitializeHelper.initialize(this, AdConfig.appID, AdConfig.DEVICES)
//        AudienceNetworkInitializeHelper.initialize(this)
//        AnalyticsHelper.initAnalytics(this)
    }
}