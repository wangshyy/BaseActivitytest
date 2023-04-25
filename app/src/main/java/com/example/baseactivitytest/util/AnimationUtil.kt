package com.example.baseactivitytest.util

import android.view.View
import android.view.animation.*
import androidx.compose.animation.core.RepeatMode

/**
 *  author : wsy
 *  date   : 2023/1/11
 *  desc   : 动画工具类
 */
class AnimationUtil private constructor() {
    //单例，双重校验锁式(Double Check Lock)
    companion object {
        val instance: AnimationUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AnimationUtil()
        }
    }

    /**     补间动画    **/

    private var alphaAnimation: AlphaAnimation? = null

    /**
     * 开始alpha动画
     * @param view View             动画对象
     * @param interpolator          Interpolator 用来控制动画的变化速度，可以理解成动画渲染器
     *                              1.LinearInterpolator：动画以均匀的速度改变。
     *                              2.AccelerateInterpolator：在动画开始的地方改变速度较慢，然后开始加速。
     *                              3.AccelerateDecelerateInterpolator：在动画开始、结束的地方改变速度较慢，中间时加速。
     *                              4.CycleInterpolator：动画循环播放特定次数，变化速度按正弦曲线改变 Math.sin(2 * mCycles * Math.PI * input)。
     *                              5.DecelerateInterpolator：在动画开始的地方改变速度较快，然后开始减速。
     *                              6.AnticipateInterpolator：反向，先向相反方向改变一段再加速播放。
     *                              7.AnticipateOvershootInterpolator：开始的时候向后然后向前甩一定值后返回最后的值。
     *                              8.BounceInterpolator： 跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100。
     *                              9.OvershootInterpolator：回弹，最后超出目的值然后缓慢改变到目的值。
     * @param fromAlpha Float       起始透明度
     * @param toAlpha Float         终止透明度
     * @param duration Long         动画的持续时间，单位为ms
     * @param fillAfter Boolean     动画结束时view的位置，true表示停留在结束位置，false表示变回起始位置
     * @param repeatCount Int       动画的重复次数，默认为0，即不重复，-1为无限
     * @param repeatMode RepeatMode 重复模式,Animation.RESTART:从头开始，Animation.REVERSE:逆序
     */
    fun startAlphaAnimation(
        view: View,
        interpolator: Interpolator,
        fromAlpha: Float,
        toAlpha: Float,
        duration: Long,
        fillAfter: Boolean = true,
        repeatCount: Int = 0,
        repeatMode: Int = Animation.RESTART
    ) {
        alphaAnimation = AlphaAnimation(fromAlpha, toAlpha).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.fillAfter = fillAfter
            this.repeatCount = repeatCount
            this.repeatMode = repeatMode
        }
        view.startAnimation(alphaAnimation)
    }

    private var translateAnimation: TranslateAnimation? = null

    /**
     * 开始平移动画
     * @param view View             动画对象
     * @param fromX Float           起始X坐标
     * @param toX Float             终止X坐标
     * @param fromY Float           起始Y坐标
     * @param toY Float             起始Y坐标
     * @param duration Long         动画的持续时间，单位为ms
     * @param fillAfter Boolean     动画结束时view的位置，true表示停留在结束位置，false表示变回起始位置
     * @param repeatCount Int       动画的重复次数，默认为0，即不重复，-1为无限
     * @param repeatMode RepeatMode 重复模式,Animation.RESTART:从头开始，Animation.REVERSE:逆序
     */
    fun startTranslateAnimation(
        view: View,
        fromX: Float,
        toX: Float,
        fromY: Float,
        toY: Float,
        duration: Long,
        fillAfter: Boolean = true,
        repeatCount: Int = 0,
        repeatMode: Int = Animation.RESTART
    ) {
        translateAnimation = TranslateAnimation(fromX, toX, fromY, toY).apply {
            this.duration = duration
            this.fillAfter = fillAfter
            this.repeatCount = repeatCount
            this.repeatMode = repeatMode
        }
        view.startAnimation(translateAnimation)
    }


    private var scaleAnimation: ScaleAnimation? = null

    /**
     * 开始缩放动画
     * @param view View             动画对象
     * @param fromX Float           x轴初始缩放比例
     * @param toX Float             x轴结束缩放比例
     * @param fromY Float           y轴初始缩放比例
     * @param toY Float             y轴结束缩放比例
     * @param pivotXType Int        x轴点取值类型
     * @param pivotXValue Float     x轴点取值
     * @param pivotYType Int        y轴点取值类型
     * @param pivotYValue Float     y轴点取值
     *                              pivotType 共三种类型
     *                              1、Animation.ABSOLUTE 像素值
     *                              2、Animation.RELATIVE_TO_SELF 相对自身百分比
     *                              3、Animation.RELATIVE_TO_PARENT 相对父布局百分比
     * @param duration Long         动画的持续时间，单位为ms
     * @param fillAfter Boolean     动画结束时view的位置，true表示停留在结束位置，false表示变回起始位置
     * @param repeatCount Int       动画的重复次数，默认为0，即不重复，-1为无限
     * @param repeatMode RepeatMode 重复模式,Animation.RESTART:从头开始，Animation.REVERSE:逆序
     */
    fun startScaleAnimation(
        view: View,
        fromX: Float,
        toX: Float,
        fromY: Float,
        toY: Float,
        pivotXType: Int,
        pivotXValue: Float,
        pivotYType: Int,
        pivotYValue: Float,
        duration: Long,
        fillAfter: Boolean = true,
        repeatCount: Int = 0,
        repeatMode: Int = Animation.RESTART
    ) {
        scaleAnimation = ScaleAnimation(
            fromX,
            toX,
            fromY,
            toY,
            pivotXType,
            pivotXValue,
            pivotYType,
            pivotYValue
        ).apply {
            this.duration = duration
            this.fillAfter = fillAfter
            this.repeatCount = repeatCount
            this.repeatMode = repeatMode
        }
        view.startAnimation(scaleAnimation)
    }

    private var rotateAnimation: RotateAnimation? = null

    /**
     * 开始旋转动画
     * @param view View             动画对象
     * @param fromDegrees Float     初始旋转角度
     * @param toDegrees Float       结束旋转角度
     * @param pivotXType Int        x轴点取值类型
     * @param pivotXValue Float     x轴点取值
     * @param pivotYType Int        y轴点取值类型
     * @param pivotYValue Float     y轴点取值
     *                              pivotType 共三种类型
     *                              1、Animation.ABSOLUTE 像素值
     *                              2、Animation.RELATIVE_TO_SELF 相对自身百分比
     *                              3、Animation.RELATIVE_TO_PARENT 相对父布局百分比
     * @param duration Long         动画的持续时间，单位为ms
     * @param fillAfter Boolean     动画结束时view的位置，true表示停留在结束位置，false表示变回起始位置
     * @param repeatCount Int       动画的重复次数，默认为0，即不重复，-1为无限
     * @param repeatMode RepeatMode 重复模式,Animation.RESTART:从头开始，Animation.REVERSE:逆序
     */
    fun startRotateAnimation(
        view: View,
        fromDegrees: Float,
        toDegrees: Float,
        pivotXType: Int,
        pivotXValue: Float,
        pivotYType: Int,
        pivotYValue: Float,
        duration: Long,
        fillAfter: Boolean = true,
        repeatCount: Int = 0,
        repeatMode: Int = Animation.RESTART
    ) {
        rotateAnimation = RotateAnimation(
            fromDegrees,
            toDegrees,
            pivotXType,
            pivotXValue,
            pivotYType,
            pivotYValue
        ).apply {
            this.duration = duration
            this.fillAfter = fillAfter
            this.repeatCount = repeatCount
            this.repeatMode = repeatMode
        }
        view.startAnimation(rotateAnimation)
    }

}
