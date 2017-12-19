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

package com.sybotan.android.syrobot.activities

import android.content.Intent
import android.net.Uri
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.NavigationView
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import com.sybotan.android.syrobot.R
import com.sybotan.android.syrobot.preferences.Opts
import com.sybotan.android.syrobot.fragments.JoystickFragment
import com.sybotan.android.syrobot.fragments.ProgramListFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 主Activity
 *
 * @author  Andy
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private val TAG = MainActivity::class.java.name
    }
    val APP_ID = "10410170"
    val API_KEY = "RkSClKjZcclmDPBnn6c7RV7M"
    val SECRET_KEY = "tg41qnImYk7ScZ3foHgQXwkR0nOoTq8d"

    var joystickFragment: JoystickFragment? = null
    var programListFragment: ProgramListFragment? = null
    /**
     * 创建Activity时调用
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化控制与编程界面
        joystickFragment = JoystickFragment(this)
        programListFragment = ProgramListFragment(this)

        // 导航切换
        val toggle = ActionBarDrawerToggle(
                this, uiDrawerLayout, uiAppbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        uiDrawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        // 设置侧边导航项目选择监听器
        uiNavView.setNavigationItemSelectedListener(this)

        updateAppbar()
        updateFragment()

        return
    } // Function onCreate()

    /**
     * 用户按返回键时调用
     */
    override fun onBackPressed() {
        if (uiDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            uiDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
        return
    } // Function onBackPressed()

    /**
     * 选择导航菜单时触发
     *
     * @param item  选择的菜单项
     * @return
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent : Intent = when(item.itemId) {
            R.id.uiNavTaobao -> Intent(Intent.ACTION_VIEW, Uri.parse("https://shop155631146.taobao.com"))
            R.id.uiNavJointSettings -> Intent(this, JointSettingsActivity::class.java)
            R.id.uiNavHelp -> Intent(this, HelpActivity::class.java)
            else -> Intent(this, AboutActivity::class.java)
        }

        startActivity(intent)
        return true
    } // Function onNavigationItemSelected()

    /**
     * 更新顶部条
     */
    private fun updateAppbar() {
        uiAppbar.setTitle(R.string.app_name)
        // 加载菜单
        uiAppbar.inflateMenu(R.menu.menu_app)

        uiAppbar.setOnMenuItemClickListener({ item ->
            val id = item.getItemId()
            if (id == R.id.menu_joystick) {
                Opts.joystickVisibility = true
                updateFragment()
            } else if (id == R.id.menu_program) {
                Opts.joystickVisibility = false
                updateFragment()
            }
            true
        })
        return
    } // Function updateToolbar()

    /**
     * 更新fragment
     */
    private fun updateFragment() {
        uiContainer.removeAllViews()

        if (Opts.joystickVisibility) {
            uiContainer.addView(joystickFragment)
        } else {
            uiContainer.addView(programListFragment)
        }

        //fragmentManager.beginTransaction().replace(R.id.uiContainer, fragment).commit()
        return
    } // Function updateFragment()
} // Class MainActivity
