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
 *           .UB  OOGDM. MK,                                          Copyright (c) 2015-2018.  北京力成恒通科技有限公司
 *              ,  XMW  ..
 *                  r                                                                     All rights reserved.
 *
 * ********************************************************************************************************************
 */

package com.sybotan.android.syrobot.fragments.Plen2

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.SeekBar
import com.sybotan.android.syrobot.R
import kotlinx.android.synthetic.main.robot_littlestar_joint_settings.view.*

/**
 * 关节设置界面
 *
 * @author  Andy
 */
class JointSettingsFragment(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs), View.OnClickListener {
    companion object {
        private val TAG = JointSettingsFragment::class.java.name
    }
    var mJointPosList = intArrayOf(0, 150, 350, -100, -100, -50, 500, 300, -50, 0, 0, 0,
                                   0, -150, -350, 100, 100, -50, -500, -300, 50, 0, 0, 0)
    private var jointId = 1
    private var step = 10
    private var currentJointButton: RadioButton

    // 初始化
    init {
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.robot_littlestar_joint_settings, this)
        currentJointButton = uiJointButton1
        // 设置UI
        setupUi()
    } // init

    /**
     * 设置UI
     */
    private fun setupUi() {
        currentJointButton.isChecked = true

        // 回中按钮
        uiHomeSettingButton.setOnClickListener {

        } // uiHomeSettingButton

        // 最大按钮
        uiMaxButton.setOnClickListener {
            uiSeekBar.progress = uiSeekBar.max //setProgress(1600, true)
        } // uiMaxButton

        // 最小按钮
        uiMinButton.setOnClickListener {
            uiSeekBar.progress = 0
        } // uiMinButton

        // 增量按钮
        uiStepButton.text = "增量$step"
        uiStepButton.setOnClickListener {
            step = when(step) {
                1 -> 5
                5 -> 10
                10 -> 20
                else -> 1
            }
            uiStepButton.text = "增量$step"
        } // uiStepButton

        // 加大按钮
        uiUpButton.setOnClickListener {
            uiSeekBar.progress += step
        } // uiUpButton

        // 减小按钮
        uiDownutton.setOnClickListener {
            uiSeekBar.progress -= step
        } // uiDownutton

        uiJointButton1.setOnClickListener(this)
        uiJointButton2.setOnClickListener(this)
        uiJointButton3.setOnClickListener(this)
        uiJointButton4.setOnClickListener(this)
        uiJointButton5.setOnClickListener(this)
        uiJointButton6.setOnClickListener(this)
        uiJointButton7.setOnClickListener(this)
        uiJointButton8.setOnClickListener(this)
        uiJointButton9.setOnClickListener(this)
        uiJointButton10.setOnClickListener(this)
        uiJointButton11.setOnClickListener(this)
        uiJointButton12.setOnClickListener(this)
        uiJointButton13.setOnClickListener(this)
        uiJointButton14.setOnClickListener(this)
        uiJointButton15.setOnClickListener(this)
        uiJointButton16.setOnClickListener(this)
        uiJointButton17.setOnClickListener(this)
        uiJointButton18.setOnClickListener(this)

        // 滑块设置
        uiSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            /**
             * 滑动滑动
             *
             * @param   seekBar     滑块
             * @param   progress    滑动位置
             * @param   fromUser    是否用户手动滑动
             */
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val pos = progress - 800
                uiJointValue.text = "$pos"
                //cmd.setPos(jointId, pos)
                mJointPosList[jointId] = pos
                return
            } // Function onProgressChanged()

            /**
             * 开始滑动
             *
             * @param   seekBar     滑块
             */
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // DO NOTHING
                return
            } // Function onStartTrackingTouch()

            /**
             * 滑动结束
             *
             * @param   seekBar     滑块
             */
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // DO NOTHING
                return
            } // Function onStopTrackingTouch()
        })
        return
    } // Function setupUi()

    /**
     * 处理uiJointButton1-uiJointButton18的点击操作
     */
    override fun onClick(view: View) {
        val jointButton = view as RadioButton
        currentJointButton.isChecked = false

        jointId = jointButton.contentDescription.toString().toInt()
        uiSeekBar.progress = mJointPosList[jointId] + 800
        Log.d(TAG, "jointId = $jointId      progress=${uiSeekBar.progress}")

        currentJointButton = jointButton
        currentJointButton.isChecked = true
        return
    } // Function onClick()
} // Class JointSettingsFragment