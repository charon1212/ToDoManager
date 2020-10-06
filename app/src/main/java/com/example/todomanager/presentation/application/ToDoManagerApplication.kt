package com.example.todomanager.presentation.application

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class ToDoManagerApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        // Realmの初期化
        Realm.init(this)

        // Realmの設定
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)

    }

}