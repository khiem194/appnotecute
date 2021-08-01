package com.kdnt.notecute.ads

//import android.app.Activity
//import android.content.Context
//import android.util.DisplayMetrics
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.RatingBar
//import android.widget.TextView
//import com.google.android.gms.ads.AdSize
//import com.google.android.gms.ads.VideoController
//import com.google.android.gms.ads.nativead.NativeAd
//import com.google.android.gms.ads.nativead.NativeAdView
//import com.nhnextsoft.video.R
//
//object AdUtil {
//
//    fun getAdSize(activity: Activity): AdSize? {
//        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
////        val display: Display = context.getWindowManager().getDefaultDisplay()
//        val outMetrics = DisplayMetrics()
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
//            val display = activity.display
//            display?.getRealMetrics(outMetrics)
//        } else {
//            @Suppress("DEPRECATION")
//            val display = activity.windowManager.defaultDisplay
//            @Suppress("DEPRECATION")
//            display.getMetrics(outMetrics)
//        }
//        val widthPixels = outMetrics.widthPixels.toFloat()
//        val density = outMetrics.density
//        val adWidth = (widthPixels / density).toInt()
//
//        // Step 3 - Get adaptive ad size and return for setting on the ad view.
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
//    }
//
//    fun buildViewNativeAdView(context: Context, nativeAd: NativeAd): View {
//        val adView = LayoutInflater.from(context)
//            .inflate(R.layout.admob_nativead_view_ads, null) as NativeAdView
////        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
////        val adViewNativeGoogle = layoutInflater.inflate(
////                R.layout.admob_nativead_view_ads, null
////        )
////        val adView: NativeAdView = adViewNativeGoogle.findViewById(R.id.native_ad_view)
//// Set the media view.
//        adView.mediaView = adView.findViewById(R.id.ad_media)
//
//        // Set other ad assets.
//        adView.headlineView = adView.findViewById(R.id.ad_headline)
//        adView.bodyView = adView.findViewById(R.id.ad_body)
//        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
//        adView.iconView = adView.findViewById(R.id.ad_app_icon)
//        adView.priceView = adView.findViewById(R.id.ad_price)
//        adView.starRatingView = adView.findViewById(R.id.ad_stars)
//        adView.storeView = adView.findViewById(R.id.ad_store)
//        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)
//
//        // The headline and media content are guaranteed to be in every UnifiedNativeAd.
//        (adView.headlineView as TextView).text = nativeAd.headline
//        adView.mediaView.setMediaContent(nativeAd.mediaContent)
//
//        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
//        // check before trying to display them.
//        if (nativeAd.body == null) {
//            adView.bodyView.visibility = View.INVISIBLE
//        } else {
//            adView.bodyView.visibility = View.VISIBLE
//            (adView.bodyView as TextView).text = nativeAd.body
//        }
//
//        if (nativeAd.callToAction == null) {
//            adView.callToActionView.visibility = View.INVISIBLE
//        } else {
//            adView.callToActionView.visibility = View.VISIBLE
//            (adView.callToActionView as Button).text = nativeAd.callToAction
//        }
//
//        if (nativeAd.icon == null) {
//            adView.iconView.visibility = View.GONE
//        } else {
//            (adView.iconView as ImageView).setImageDrawable(
//                nativeAd.icon.drawable
//            )
//            adView.iconView.visibility = View.VISIBLE
//        }
//
//        if (nativeAd.price == null) {
//            adView.priceView.visibility = View.INVISIBLE
//        } else {
//            adView.priceView.visibility = View.VISIBLE
//            (adView.priceView as TextView).text = nativeAd.price
//        }
//
//        if (nativeAd.store == null) {
//            adView.storeView.visibility = View.INVISIBLE
//        } else {
//            adView.storeView.visibility = View.VISIBLE
//            (adView.storeView as TextView).text = nativeAd.store
//        }
//
//        if (nativeAd.starRating == null) {
//            adView.starRatingView.visibility = View.INVISIBLE
//        } else {
//            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
//            adView.starRatingView.visibility = View.VISIBLE
//        }
//
//        if (nativeAd.advertiser == null) {
//            adView.advertiserView.visibility = View.INVISIBLE
//        } else {
//            (adView.advertiserView as TextView).text = nativeAd.advertiser
//            adView.advertiserView.visibility = View.VISIBLE
//        }
//
//        // This method tells the Google Mobile Ads SDK that you have finished populating your
//        // native ad view with this native ad.
//        adView.setNativeAd(nativeAd)
//
//        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
//        // have a video asset.
//        val vc = nativeAd.mediaContent.videoController
//
//        // Updates the UI to say whether or not this ad has a video asset.
//        if (vc.hasVideoContent()) {
//            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
//            // VideoController will call methods on this object when events occur in the video
//            // lifecycle.
//            vc.videoLifecycleCallbacks = object : VideoController.VideoLifecycleCallbacks() {
//            }
//        } else {
//        }
//
//        return adView
//    }
//
//    fun buildViewNativeAdViewNewStyle(context: Context, nativeAd: NativeAd): View {
//        val adView = LayoutInflater.from(context)
//            .inflate(R.layout.admob_nativead_view_ads_style, null) as NativeAdView
//        adView.mediaView = adView.findViewById(R.id.ad_media)
//
//        // Set other ad assets.
//        adView.headlineView = adView.findViewById(R.id.ad_headline)
//        adView.bodyView = adView.findViewById(R.id.ad_body)
//        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
//        adView.iconView = adView.findViewById(R.id.ad_app_icon)
//
////        adView.priceView = adView.findViewById(R.id.ad_price)
////        adView.starRatingView = adView.findViewById(R.id.ad_stars)
////        adView.storeView = adView.findViewById(R.id.ad_store)
////        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)
//
//        // The headline and media content are guaranteed to be in every UnifiedNativeAd.
//        (adView.headlineView as TextView).text = nativeAd.headline
//        adView.mediaView.setMediaContent(nativeAd.mediaContent)
//
//        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
//        // check before trying to display them.
//        if (nativeAd.body == null) {
//            adView.bodyView.visibility = View.INVISIBLE
//        } else {
//            adView.bodyView.visibility = View.VISIBLE
//            (adView.bodyView as TextView).text = nativeAd.body
//        }
//
//        if (nativeAd.callToAction == null) {
//            adView.callToActionView.visibility = View.INVISIBLE
//        } else {
//            adView.callToActionView.visibility = View.VISIBLE
//            (adView.callToActionView as Button).text = nativeAd.callToAction
//        }
//
//        if (nativeAd.icon == null) {
//            adView.iconView.visibility = View.GONE
//        } else {
//            (adView.iconView as ImageView).setImageDrawable(
//                nativeAd.icon.drawable
//            )
//            adView.iconView.visibility = View.VISIBLE
//        }
//
//        var adInfo:String = ""
//
//
//        if (nativeAd.starRating != null) adInfo.plus("|" + nativeAd.starRating + "★")
//        if (nativeAd.price.isNullOrEmpty().not()) adInfo.plus("|" + nativeAd.price)
////        if (nativeAd.store.isNullOrEmpty().not()) adInfo.plus("|" + nativeAd.store)
//        if (nativeAd.advertiser.isNullOrEmpty().not()) adInfo.plus("|" + nativeAd.advertiser)
//
////        if (nativeAd.price == null) {
////            adView.priceView.visibility = View.INVISIBLE
////        } else {
////            adView.priceView.visibility = View.VISIBLE
////            (adView.priceView as TextView).text = nativeAd.price
////        }
////
////        if (nativeAd.store == null) {
////            adView.storeView.visibility = View.INVISIBLE
////        } else {
////            adView.storeView.visibility = View.VISIBLE
////            (adView.storeView as TextView).text = nativeAd.store
////        }
////
////        if (nativeAd.starRating == null) {
////            adView.starRatingView.visibility = View.INVISIBLE
////        } else {
////            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
////            adView.starRatingView.visibility = View.VISIBLE
////        }
////
////        if (nativeAd.advertiser == null) {
////            adView.advertiserView.visibility = View.INVISIBLE
////        } else {
////            (adView.advertiserView as TextView).text = nativeAd.advertiser
////            adView.advertiserView.visibility = View.VISIBLE
////        }
//
//        // This method tells the Google Mobile Ads SDK that you have finished populating your
//        // native ad view with this native ad.
//        adView.setNativeAd(nativeAd)
//
//        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
//        // have a video asset.
//        val vc = nativeAd.mediaContent.videoController
//
//        // Updates the UI to say whether or not this ad has a video asset.
//        if (vc.hasVideoContent()) {
//            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
//            // VideoController will call methods on this object when events occur in the video
//            // lifecycle.
//            vc.videoLifecycleCallbacks = object : VideoController.VideoLifecycleCallbacks() {
//            }
//        } else {
//        }
//
//        return adView
//    }
//}