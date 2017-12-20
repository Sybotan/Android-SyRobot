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

import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.sybotan.android.syrobot.entities.Motion
import com.sybotan.android.syrobot.entities.MotionCategory
import com.sybotan.android.core.utils.GsonUtil
import java.io.InputStream

/**
 * 动作分类适配器
 *
 * @author  Andy
 */
open class MotionCategoryAdapter : PagerAdapter() {
    companion object {
        private val TAG = MotionCategoryAdapter::class.java.name
        lateinit var motionCategoryList: List<MotionCategory>

        fun loadMotionFile(input: InputStream) {
            motionCategoryList = GsonUtil.parseJsonArray(input, MotionCategory::class.java)
            Log.d(TAG, motionCategoryList.toString())
            return
        } // Function loadMotionFile()
    } // companion object

    /**
     * 判断view与obj是否为同一对象
     *
     * @param   view
     * @param   obj
     */
    override fun isViewFromObject(view: View?, obj: Any?): Boolean {
        // 注意判断两个对象是否为同一对象，一定要用===
        return view === obj
    } // Function isViewFromObject()

    /**
     * 获得列表项数量
     *
     * @return  列表项数量
     */
    override fun getCount(): Int {
        return motionCategoryList.size
    } // Function getCount()

    /**
     * 获得页标题
     *
     * @param   position    页面索引
     * @return  页面标题
     */
    override fun getPageTitle(position: Int): String {
        return "${motionCategoryList[position].name}"
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
     * 获得指定分类的动作列表
     *
     * @param   position    分类索引
     * @return  动作列表。
     */
    fun getMotionList(position: Int): List<Motion>? {
        return try {
            motionCategoryList[position].motionList
        } catch (e: Exception) {
            null
        }
    } // Function getMotionList()
} // Class MotionCategoryAdapter