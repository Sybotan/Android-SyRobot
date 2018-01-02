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

package com.sybotan.android.syrobot.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.sybotan.android.syrobot.R
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.imageView
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent

/**
 * 退出App界面
 *
 * @author  Andy
 */
class ExitActivity : AppCompatActivity() {
    companion object {
        private val TAG = ExitActivity::class.java.name
        val REBOOT_PARAM = "reboot"
    }

    val handler = Handler()

    // 定时任务
    val runnable = object : Runnable {
        override fun run() {
            finish()
            if (intent.getBooleanExtra(REBOOT_PARAM,false)) {
                reboot()
            }
            System.exit(0)
            return
        } // Function run()
    } // Object Runnable()

    /**
     * 创建Activity时调用
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createViews()

        // 两秒后关闭
        handler.postDelayed(runnable, 2000)

        return
    } // Function onCreate()

    /**
     * 在退出窗口，屏蔽用户按返回键
     */
    override fun onBackPressed() {
        // DO NOTHING
        return
    } // Function onBackPressed()

    /**
     * 重启应用
     */
    private fun reboot() {
        Log.d(TAG, "Reboot App")
        val startActivity = packageManager.getLaunchIntentForPackage(packageName)
        val pendingIntent = PendingIntent.getActivity(this, 120, startActivity, PendingIntent.FLAG_CANCEL_CURRENT)
        val mgr = getSystemService(ALARM_SERVICE) as AlarmManager
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent)
        return
    } // Function reboot()

    /**
     * 创建Activi中的对象
     */
    private fun createViews() {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            imageView {
                imageResource = R.drawable.app_exit
                scaleType = ImageView.ScaleType.FIT_XY
            }.lparams(width = matchParent, height = matchParent)
        }
        return
    } // Function create()
} // Class ExitActivity
