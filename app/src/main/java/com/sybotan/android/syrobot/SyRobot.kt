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

package com.sybotan.android.syrobot

import android.app.Application
import android.app.Service
import android.content.Context
import android.os.Environment
import android.os.Vibrator
import com.sybotan.android.syrobot.preferences.Opts
import com.sybotan.android.syrobot.services.VibratorService
import com.sybotan.android.syrobot.views.adapters.MotionCategoryAdapter

/**
 * 应用入口
 *
 * @author  Andy
 */
class SyRobot : Application() {
    companion object {
        private val TAG = SyRobot::class.java.simpleName
        val appStorePath = "${Environment.getExternalStorageDirectory().absolutePath}/com.sybotan.syrobot"
    }

    /**
     * 创建应用时调用
     */
    override fun onCreate() {
        super.onCreate()
        // 初始化MainPreferences对象
        Opts.pref = getSharedPreferences(TAG, Context.MODE_PRIVATE)
        // 初始化震动器
        VibratorService.vibrator = getSystemService(Service.VIBRATOR_SERVICE) as Vibrator

        // 加载动作分类列表文件
        MotionCategoryAdapter.loadMotionFile(applicationContext.assets.open("littlestar/motions.json"))
        return
    } // Function onCreate()

} // Object  SyRobot
