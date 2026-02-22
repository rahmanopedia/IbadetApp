package com.ibadetapp

import android.app.Application
import com.ibadetapp.data.repository.IbadetDatabase

class IbadetApplication : Application() {

    val database by lazy { IbadetDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
    }
}
