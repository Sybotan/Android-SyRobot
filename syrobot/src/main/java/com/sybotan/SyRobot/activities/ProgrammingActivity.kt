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

package com.sybotan.SyRobot.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.sybotan.SyRobot.R
import com.sybotan.SyRobot.SyRobot
import com.sybotan.SyRobot.preferences.Opts
import com.sybotan.SyRobot.views.MotionListView
import com.sybotan.SyRobot.views.adapters.MotionCategoryAdapter
import kotlinx.android.synthetic.main.activity_programming.*

/**
 * 编程界面
 *
 * @author  Andy
 */
class ProgrammingActivity : AppCompatActivity() {
    companion object {
        private val TAG = ProgrammingActivity::class.java.name
        val PROGRAM_NAME_PARM = "programName"
    }

    val programPath = "${SyRobot.appStorePath}/${Opts.robot}"
    var program: String = ""
    /**
     * 创建时调用
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programming)
        updateAppbar()

        // 取得传入的应用名称
        program = intent.getStringExtra(PROGRAM_NAME_PARM)
        uiProgramName.text = program
        uiProgram.openProgram("$programPath/$program.prg")

        uiMotionCategoryPager.adapter = object : MotionCategoryAdapter() {
            /**
             * 实例化列表项视图
             *
             * @param   container   容器对象
             * @param   position    数据项索引
             * @return  列表项视图
             */
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val view = MotionListView(getMotionList(position)!!, baseContext)
                container.addView(view)
                return view
            } // Function instantiateItem()
        }
        return
    } // Function onCreate()

    /**
     * 退出时保存程序
     */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()----------$programPath/$program.prg")
        uiProgram.saveProgram("$programPath/$program.prg")
        return
    } // Function onPause()

    /**
     * 更新顶部条
     */
    private fun updateAppbar() {
        uiAppbar.setTitle(R.string.title_activity_programming)
        setSupportActionBar(uiAppbar)
        // 标题栏显示返回，点击返回上一页
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // 点击返回
        uiAppbar.setNavigationOnClickListener{ finish() }
        return
    } // Function updateAppBar()

} // Class ProgrammingActivity
