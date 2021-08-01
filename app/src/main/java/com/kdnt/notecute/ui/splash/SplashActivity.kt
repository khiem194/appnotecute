package com.kdnt.notecute.ui.splash

import android.os.Bundle
import com.kdnt.notecute.R
import com.kdnt.notecute.core.base.BaseActivity
import com.kdnt.notecute.databinding.ActivitySplashBinding
import com.kdnt.notecute.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun getLayoutResId(): Int = R.layout.activity_splash
    override val mViewModel: SplashViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gotoHome()

        try {
            val versionName: String = packageManager.getPackageInfo(packageName, 0).versionName
            "v$versionName".also { mViewBinding.appVersionName.text = it }
        } catch (e: Exception) {
            Timber.e(e)
        }

    }

    private fun gotoHome() {
        startActivity(HomeActivity.openActivity(this))
    }

    override fun onBackPressed() {}


}