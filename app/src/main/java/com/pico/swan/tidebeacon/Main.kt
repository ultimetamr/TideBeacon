package com.pico.swan.tidebeacon

import com.pico.spatial.ui.design.PicoTheme
import com.pico.spatial.ui.foundation.dsl.DefaultStage
import com.pico.spatial.ui.foundation.dsl.SpatialAppScope
import com.pico.swan.tidebeacon.ui.tidebeacon.TideBeaconScreen

fun mainApp(scope: SpatialAppScope) =
    with(scope) {
        DefaultStage {
            PicoTheme {
                TideBeaconScreen()
            }
        }
    }
