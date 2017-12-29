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

package com.sybotan.android.syrobot.views.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.sybotan.android.core.utils.GsonUtil
import com.sybotan.android.syrobot.entities.Robot
import java.io.InputStream

/**
 * 机器人选择适配器
 *
 * @author  Andy
 */
class RobotPagerAdapter(val context: Context) : PagerAdapter() {
    companion object {
        private val TAG = RobotPagerAdapter::class.java.name

        // 机器人列表
        lateinit var robots: List<Robot>

        /**
         * 从输入流加载机器人列表
         *
         * @param   input       输入流
         */
        fun loadRobots(input: InputStream) {
            robots = GsonUtil.parseJsonArray(input, Robot::class.java)
            Log.d(TAG, "loadrobots count=${robots.size}")
            return
        } // Function loadMotionFile()

        /**
         * 根据机器人ID获得机器人对象
         *
         * @param   ID      机器人ID
         */
        fun robotById(id: String) : Robot? {
            for (robot in robots) {
                if (id == robot.id) {
                    return robot
                }
            }
            return null
        }
    } // companion object

    /**
     * 判断view与obj是否为同一对象
     *
     * @param   view
     * @param   obj
     */
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    } // Function

    /**
     * 返回支持的机器人数量
     */
    override fun getCount(): Int = robots.size

    /**
     * 获得页标题
     *
     * @param   position    页面索引
     * @return  页面标题
     */
    override fun getPageTitle(position: Int): String {
        return robots[position].name
    } // Function getPageTitle()

    /**
     * 释放对象
     *
     * @param   container   容器
     * @param   position    被释放对象在容器中的索引
     * @param   obj         被释放对象
     */
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
        return
    } // Function destroyItem()

    /**
     * 实例化列表项视图
     *
     * @param   container   容器对象
     * @param   position    数据项索引
     * @return  列表项视图
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = ImageView(context)
        view.scaleType = ImageView.ScaleType.FIT_XY
        Picasso.with(context).load("file:///android_asset/${robots[position].id.toLowerCase()}/robot.png").into(view)
        container.addView(view)

        return view
    } // Function instantiateItem()
} // Class RobotPagerAdapter