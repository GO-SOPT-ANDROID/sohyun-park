package org.android.go.sopt.data

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import org.android.go.sopt.BuildConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initFlipper()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).apply {
                addPlugin(InspectorFlipperPlugin(this@App, DescriptorMapping.withDefaults()))
                addPlugin(networkFlipperPlugin)
                addPlugin(LeakCanary2FlipperPlugin())
                addPlugin(SharedPreferencesFlipperPlugin(applicationContext, "MY_ID"))
            }.start()
        }
    }

    companion object {
        val networkFlipperPlugin = NetworkFlipperPlugin()
    }
}