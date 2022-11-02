package com.sysyyds.sysoftkeyboard

import android.animation.ObjectAnimator
import android.graphics.Rect
import android.view.View
import android.view.animation.DecelerateInterpolator

/**
 *   创建人：sys
 *   创建时间：2022/11/1
 *   功能：
 */

object SoftKeyBoardUtils {

    /**
     * 软键盘弹出，目标视图向上抬起软键盘高度
     * 视觉上 targetView 目标视图方法 跟随软键盘浮动
     */
    fun setTargetViewAlwaysAboveSoftKeyBoard(root: View, targetView: View) {
        root.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            // 获取视图v显示区域
            root.getWindowVisibleDisplayFrame(r)
            // 获取视图v显示出来部分的高度
            val displayInt = r.bottom - r.top
            // 获取视图v本身高度
            val parentInt = root.height
            // 判断软键盘是否弹出（v的高度和显示出来的高度相同，则软键盘没有弹出）
            // 软键盘高度
            val softKeyHeight = parentInt - displayInt

            if (softKeyHeight == 0) {
                // 软键盘没有显示，target view 移动到原有位置
//                Log.e("targetView.y", "当前Y: " + targetView.y)
//                Log.e("targetView.y", "应该移动到的位置Y: " + (parentInt - height))
                ObjectAnimator.ofFloat(
                    targetView,
                    "translationY",
                    0f
                ).apply {
                    duration = 50
                    start()
                }
            } else {
                // 显示软键盘 target view 移动到软键盘之上
//                Log.e("targetView.y", "当前Y: " + targetView.y)
//                Log.e("targetView.y", "应该移动到的位置Y: " + (parentInt - height - softKeyHeight))
                // 软键盘显示之后
                ObjectAnimator.ofFloat(
                    targetView,
                    "translationY",
                    -softKeyHeight.toFloat()
                ).apply {
                    interpolator = DecelerateInterpolator()
                    duration = 150
                    start()
                }
            }
        }
    }

    /**
     * 软键盘相关，显示和消失，之后触发某事件
     * @param root 一般为binding
     * @param softKeyStateListener 显示或隐藏调用的接口
     */
    fun doWhileSoftKeyBoardShowAndHide(
        root: View,
        softKeyStateListener: SoftKeyStateListener,
    ) {
        root.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            // 获取视图v显示区域
            root.getWindowVisibleDisplayFrame(r)
            // 获取视图v显示出来部分的高度
            val displayInt = r.bottom - r.top
            // 获取视图v本身高度
            val parentInt = root.height
            // 判断软键盘是否弹出（v的高度和显示出来的高度相同，则软键盘没有弹出）
            // 软键盘高度
            val softKeyHeight = parentInt - displayInt

            if (softKeyHeight == 0) {
                // 软键盘没有显示
                softKeyStateListener.softKeyHideEvent()
            } else
                softKeyStateListener.softKeyShowEvent(softKeyHeight)
        }
    }

    interface SoftKeyStateListener {
        fun softKeyShowEvent(softKeyHeight: Int)
        fun softKeyHideEvent()
    }
}