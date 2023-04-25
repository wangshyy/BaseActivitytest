package com.example.baseactivitytest

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import com.example.baseactivitytest.base.BaseVMActivity
import com.example.baseactivitytest.databinding.ActivitySecondBinding
import com.example.baseactivitytest.util.AnimationUtil

/**
 *  author : wsy
 *  date   : 2022/12/30
 *  desc   :
 */
class SecondActivity : BaseVMActivity(), View.OnClickListener {
    private val binding by binding<ActivitySecondBinding>(R.layout.activity_second)
    override fun initView() {
        binding.apply {
            onClickListener = this@SecondActivity
        }

    }

    override fun initData() {
    }

    override fun startObserve() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> {
//                AnimationUtil.instance.startAlphaAnimation(
//                    binding.image, LinearInterpolator(), 0F, .2F, 1000,false)
//                AnimationUtil.instance.startTranslateAnimation(
//                    binding.image, 0F, 200F, 0F, 200F, 2000
//                )
//                AnimationUtil.instance.startScaleAnimation(
//                    binding.image,
//                    0F,
//                    1F,
//                    0F,
//                    1F,
//                    Animation.ABSOLUTE,
//                    100F,
//                    Animation.ABSOLUTE,
//                    200F,
//                    1000
//                )
                AnimationUtil.instance.startRotateAnimation(
                    binding.image,
                    0F,
                    360F,
                    Animation.RELATIVE_TO_SELF,
                    .5F,
                    Animation.RELATIVE_TO_SELF,
                    .5F,
                    1000
                )
            }
        }
    }
}