package com.kdnt.notecute.ads

//import android.content.Context
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.MobileAds
//import com.google.android.gms.ads.RequestConfiguration
//
//object AdmobInitializeHelper {
//    /**
//     * It's recommended to call this method from Application.onCreate().
//     * Otherwise you can call it from all Activity.onCreate()
//     * methods for Activities that contain ads.
//     *
//     * @param context Application or Activity.
//     */
//    internal fun initialize(context: Context, testDeviceIds: ArrayList<String>) {
//        MobileAds.initialize(context) {
//
//        }
//
//        testDeviceIds.add(AdRequest.DEVICE_ID_EMULATOR)
//        val configuration =
//            RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
//        MobileAds.setRequestConfiguration(configuration)
//        MobileAds.setAppMuted(true)
//    }
//}