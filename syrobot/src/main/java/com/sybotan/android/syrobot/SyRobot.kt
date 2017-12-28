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
import android.content.Intent
import android.os.Environment
import android.os.Vibrator
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.sybotan.android.syrobot.activities.ExitActivity
import com.sybotan.android.syrobot.preferences.Opts
import com.sybotan.android.syrobot.robots.Robot
import com.sybotan.android.syrobot.services.VibratorService
import com.sybotan.android.syrobot.views.adapters.MotionCategoryAdapter

/**
 * 应用入口
 *
 * @author  Andy
 */
class SyRobot : Application() {
    companion object {
        private val TAG = SyRobot::class.java.name

        val appStorePath = "${Environment.getExternalStorageDirectory().absolutePath}/com.sybotan.syrobot"

        var robot: Robot? = null

        /**
         * 根据名称，创建机器人
         *
         * @param   name        机器人名称
         * @return  机器人控制类
         */
        fun loadRobot(name: String): Robot? {
            val clazz = Class.forName("com.sybotan.android.syrobot.robots.$name") ?: return null
            return clazz.newInstance() as Robot
        } // Function LittleStar

        /**
         * 退出应用
         *
         * @param   context     上下文信息
         */
        fun exitApp(context: Context) {
            var intent = Intent(context, ExitActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(ExitActivity.REBOOT_PARAM, false)
            context.startActivity(intent)
            return
        } // Function exitApp()

        /**
         * 重启应用
         *
         * @param   context     上下文信息
         */
        fun rebootApp(context: Context) {
            var intent = Intent(context, ExitActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(ExitActivity.REBOOT_PARAM, true)
            context.startActivity(intent)
            return
        } // Function rebootApp()
    }

    /**
     * 创建应用时调用
     */
    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, BuildConfig.buildTime)
        // 初始化MainPreferences对象
        Opts.pref = getSharedPreferences(TAG, Context.MODE_PRIVATE)
        // 初始化震动器
        VibratorService.vibrator = getSystemService(Service.VIBRATOR_SERVICE) as Vibrator

        Log.d(TAG, "Robot '${Opts.robot}' start!!!!!!!!!")

        // 加载机器人
        robot = loadRobot(Opts.robot)

        // 加载动作分类列表文件
        MotionCategoryAdapter.loadMotionFile(applicationContext.assets.open("${Opts.robot.toLowerCase()}/motions.json"))
        return
    } // Function onCreate()
} // Object  SyRobot
