package com.kdnt.notecute.ads

//import android.content.Context
//import com.facebook.ads.AdSettings
//import com.facebook.ads.AudienceNetworkAds
//import timber.log.Timber
//
//class AudienceNetworkInitializeHelper : AudienceNetworkAds.InitListener {
//
//    override fun onInitialized(result: AudienceNetworkAds.InitResult) {
//        Timber.tag(AudienceNetworkAds.TAG).d(result.message)
//    }
//
//    companion object {
//
//        /**
//         * It's recommended to call this method from Application.onCreate().
//         * Otherwise you can call it from all Activity.onCreate()
//         * methods for Activities that contain ads.
//         *
//         * @param context Application or Activity.
//         */
//        internal fun initialize(context: Context) {
//            if (!AudienceNetworkAds.isInitialized(context)) {
//
//                AdSettings.turnOnSDKDebugger(context)
//                AdSettings.addTestDevice("eae660ba-4730-4641-92b2-ea5558eb5663")
//                AdSettings.addTestDevice("b0bf6730-4d26-4efd-80f3-028e6bef4e0d")
//                AdSettings.addTestDevice("bc7173a7-f08b-4ec5-9314-22381e01608a")
//                AdSettings.addTestDevice("5bf23bc4-84a9-4c6b-9935-06144692003e")
//
//                AudienceNetworkAds
//                    .buildInitSettings(context)
//                    .withInitListener(AudienceNetworkInitializeHelper())
//                    .initialize()
//
//            }
//        }
//    }
//}