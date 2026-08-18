package com.pico.swan.tidebeacon.platform

import com.pico.spatial.ui.platform.stub.SpatialLaunchActivity

class LaunchActivity : SpatialLaunchActivity() {
    companion object {
        @Volatile var previewScreen: String? = null
        @Volatile var previewScene: String? = null
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        previewScreen = intent.getStringExtra("preview_screen")
            ?: android.provider.Settings.Global.getString(contentResolver, "tidebeacon_preview")
        previewScene = intent.getStringExtra("preview_scene")
            ?: android.provider.Settings.Global.getString(contentResolver, "tidebeacon_scene")
        super.onCreate(savedInstanceState)
    }
}
