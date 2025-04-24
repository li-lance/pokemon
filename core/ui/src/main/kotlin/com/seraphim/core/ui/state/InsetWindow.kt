package com.seraphim.core.ui.state

import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Window.edgeToEdgeWindowInsetsControllerCompat(
    isLightStatusBar: Boolean = true,
    isLightNavigationBar: Boolean = true
) {
    // 设置窗口为边到边模式
    WindowCompat.setDecorFitsSystemWindows(this, false)

    // 配置状态栏和导航栏的外观
    val insetsController = WindowInsetsControllerCompat(this, decorView)
    insetsController.isAppearanceLightStatusBars = isLightStatusBar
    insetsController.isAppearanceLightNavigationBars = isLightNavigationBar
}