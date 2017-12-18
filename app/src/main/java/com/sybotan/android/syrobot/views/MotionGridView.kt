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

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.sybotan.android.syrobot.R
import com.sybotan.android.syrobot.entities.Motion
import kotlinx.android.synthetic.main.view_motion_grid_item.view.*

/**
 * 横向三行滑动式Motion视图控件
 *
 * @author  Andy
 */
class MotionGridView(motionList: List<Motion>, context: Context, attrs: AttributeSet? = null) : RecyclerView(context, attrs) {
    companion object {
        private val TAG = MotionGridView::class.java.simpleName
    }

    val motionList: List<Motion>

    // 初始化
    init {
        layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
        this.motionList = motionList
        adapter = MotionAdapter()
    } // init

    /**
     *
     */
    inner class MotionAdapter : RecyclerView.Adapter<MotionAdapter.MotionViewHolder>(){
        /**
         * 创建视图holder
         *
         * @param   parent      窗口视图
         * @param   viewType    视图类型
         * @return  视图图holder
         */
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MotionViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.view_motion_grid_item, parent, false)
            return MotionViewHolder(view)
        } // Function onCreateViewHolder()

        /**
         * 绑定数据与视图
         *
         * @param   holder      保存数据holder
         * @param   position    数据索引
         */
        override fun onBindViewHolder(holder: MotionViewHolder?, position: Int) {
            holder!!.bind(motionList[position])
            return
        } // Function onBindViewHolder()

        /**
         * 获得数据项数量
         *
         * @return  数据项数量
         */
        override fun getItemCount(): Int = motionList.size

        /**
         * 视图Holder
         */
        inner class MotionViewHolder(val view: View) : ViewHolder(view) {
            fun bind(motion: Motion) {
                Log.v(TAG, "file:///android_asset/littlestar/${motion.iconPath}")
                Picasso.with(context).load("file:///android_asset/littlestar/${motion.iconPath}").into(view.uiMotionIcon)
                view.uiMotionName.text = motion.name
            } // Function bind()
        }
    } // Class MotionAdapter()
} // Class MotionGridView