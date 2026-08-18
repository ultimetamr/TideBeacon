package com.pico.swan.tidebeacon.platform

import android.app.Application
import com.pico.spatial.ui.foundation.dsl.launch
import com.pico.swan.tidebeacon.mainApp

class SpatialApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        launch(::mainApp)
    }
}
