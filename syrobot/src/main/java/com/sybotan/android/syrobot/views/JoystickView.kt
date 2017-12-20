/*
 * ********************************************************************************************************************
 *
 *               iFHS7.
 *              ;BBMBMBMc                  rZMBMBR              BMB
 *              MBEr:;PBM,               7MBMMEOBB:             BBB                       RBW
 *     XK:      BO     SB.     :SZ       MBM.       c;;     ir  BBM :FFr       :SSF:    ;xBMB:r   iuGXv.    i:. iF2;
 *     DBBM0r.  :D     S7   ;XMBMB       GMBMu.     MBM:   BMB  MBMBBBMBMS   WMBMBMBBK  MBMBMBM  BMBRBMBW  .MBMBMBMBB
 *      :JMRMMD  ..    ,  1MMRM1;         ;MBMBBR:   MBM  ;MB:  BMB:   MBM. RMBr   sBMH   BM0         UMB,  BMB.  KMBv
 *     ;.   XOW  B1; :uM: 1RE,   i           .2BMBs  rMB. MBO   MBO    JMB; MBB     MBM   BBS    7MBMBOBM:  MBW   :BMc
 *     OBRJ.SEE  MRDOWOR, 3DE:7OBM       .     ;BMB   RMR7BM    BMB    MBB. BMB    ,BMR  .BBZ   MMB   rMB,  BMM   rMB7
 *     :FBRO0D0  RKXSXPR. JOKOOMPi       BMBSSWBMB;    BMBB:    MBMB0ZMBMS  .BMBOXRBMB    MBMDE RBM2;SMBM;  MBB   xBM2
 *         iZGE  O0SHSPO. uGZ7.          sBMBMBDL      :BMO     OZu:BMBK,     rRBMB0;     ,EBMB  xBMBr:ER.  RDU   :OO;
 *     ,BZ, 1D0  RPSFHXR. xWZ .SMr                  . .BBB
 *      :0BMRDG  RESSSKR. 2WOMBW;                   BMBMR
 *         i0BM: SWKHKGO  MBDv
 *           .UB  OOGDM. MK,                                          Copyright (c) 2015-2017.  北京力成恒通科技有限公司
 *              ,  XMW  ..
 *                  r                                                                     All rights reserved.
 *
 * ********************************************************************************************************************
 */

package com.sybotan.android.syrobot.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import com.sybotan.android.syrobot.R
import org.jetbrains.anko.*

/**
 * 方向控制手柄
 *
 * @author  Andy
 */
class JoystickView(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    companion object {
        private val TAG = JoystickView::class.java.name
    } // companion object

    private val uiBackground: View = View(context)
    private val uiStick: View = View(context)
    private val animatorX = ObjectAnimator()
    private val animatorY = ObjectAnimator()
    private var initialized = false
    private var centerX: Int = 0
    private var centerY: Int = 0
    private var stickR: Int = 0
    private var backgroundR: Int = 0

    // 初始化
    init {
        uiBackground.backgroundResource = R.drawable.joystick_background
        uiBackground.elevation = dimen(R.dimen.elevation_low).toFloat()
        addView(uiBackground)

        uiStick.backgroundResource = R.drawable.joystick_stick
        uiStick.elevation = dimen(R.dimen.elevation_high).toFloat()
        addView(uiStick)

        animatorX.propertyName = "x"
        animatorY.propertyName = "y"
        animatorX.target = uiStick
        animatorY.target = uiStick
    } // init

    /**
     * 首次呈现，设置背景与摇柄的位置与大小
     *
     * @param   canvas      画布
     */
    override fun dispatchDraw(canvas: Canvas) {
        // 如果未初始化
        if (!initialized) {
            initialized = true
            centerX = canvas.width / 2
            centerY = canvas.height / 2
            backgroundR = (Math.min(centerX, centerY) * 0.9).toInt()
            uiBackground.layoutParams = LayoutParams(2 * backgroundR,2 * backgroundR)
            uiBackground.x = (centerX - backgroundR).toFloat()
            uiBackground.y = (centerY - backgroundR).toFloat()
            stickR = (backgroundR * 0.5).toInt()
            uiStick.layoutParams = LayoutParams(2 * stickR,2 * stickR)
            uiStick.x = (centerX - stickR).toFloat()
            uiStick.y = (centerY - stickR).toFloat()
        }
        super.dispatchDraw(canvas)
        return
    } // Function dispatchDraw()

    /**
     * 用户触摸操作移动手柄
     *
     * @param   event       保留触摸事件参数
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
            var x = (event.x - centerX).toDouble()
            var y = (event.y - centerY).toDouble()
            val t = Math.atan2(y, x)
            var r = Math.hypot(x, y)
            val rMax = (backgroundR - stickR).toDouble()
            r = Math.min(r, rMax)
            x = centerX - stickR + r * Math.cos(t)
            y = centerY - stickR + r * Math.sin(t)
            if (uiStick.x != x.toInt().toFloat() || uiStick.x != y.toInt().toFloat()) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    animateJoystick(x, y)
                } else {
                    uiStick.setX(x.toInt().toFloat())
                    uiStick.setY(y.toInt().toFloat())
                }
                // stickPosition.set(new StickPosition(r / rMax, t));
            }
        } else {
            animateJoystick((centerX - stickR).toDouble(), (centerY - stickR).toDouble())
            //mStickPosition.set(new StickPosition(0, 0));
        }
        return true
    } // Function onTouchEvent()

    /**
     * 根据坐标设置并启动手柄动画
     *
     * @param   x       x坐标
     * @param   y       y坐标
     */
    private fun animateJoystick(x: Double, y: Double) {
        val r = Math.hypot(uiStick.x - x, uiStick.y - y)
        val duration = r.toInt() / 2

        animatorX.duration = duration.toLong()
        animatorX.setFloatValues(x.toFloat())
        animatorY.duration = duration.toLong()
        animatorY.setFloatValues(y.toFloat())
        animatorX.start()
        animatorY.start()

        return
    } // Function animateStick()
} // Class JoystickView