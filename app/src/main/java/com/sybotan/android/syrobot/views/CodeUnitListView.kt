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
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.sybotan.android.syrobot.R
import com.sybotan.android.syrobot.entities.CodeUnit
import com.sybotan.android.syrobot.entities.Motion
import kotlinx.android.synthetic.main.view_codeunit_list_item.view.*
import java.util.*

/**
 * 代码列表
 *
 * @author  Andy
 */
class CodeUnitListView(context: Context, attrs: AttributeSet? = null) : RecyclerView(context, attrs) {
    companion object {
        private val TAG = CodeUnitListView::class.java.simpleName
    }

    // 代码列表
    var codeUnitList = ArrayList<CodeUnit>()
    val mainAdapter = CodeUnitListAdapter()

    // 初始化
    init {
        layoutManager = LinearLayoutManager(context)
        adapter = mainAdapter
        // 添加分隔线
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        ItemTouchHelper(ItemTouchHelperCallback()).attachToRecyclerView(this)
        codeUnitList.add(CodeUnit(Motion(1, "test1"),1))
        codeUnitList.add(CodeUnit(Motion(2, "test2"),2))
        codeUnitList.add(CodeUnit(Motion(3, "test3"),3))
        codeUnitList.add(CodeUnit(Motion(4, "test4"),4))
        codeUnitList.add(CodeUnit(Motion(5, "test5"),5))
        codeUnitList.add(CodeUnit(Motion(6, "test6"),6))
        codeUnitList.add(CodeUnit(Motion(7, "test7"),7))
        codeUnitList.add(CodeUnit(Motion(8, "test8"),8))
        codeUnitList.add(CodeUnit(Motion(9, "test9"),9))
        codeUnitList.add(CodeUnit(Motion(10, "test10"),10))
    } // init

    inner class CodeUnitListAdapter : Adapter<CodeUnitListAdapter.CodeUnitListViewHolder>(){
        /**
         * 创建视图holder
         *
         * @param   parent      窗口视图
         * @param   viewType    视图类型
         * @return  视图图holder
         */
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CodeUnitListViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.view_codeunit_list_item, parent, false)
            return CodeUnitListViewHolder(view)
        } // Function onCreateViewHolder()

        /**
         * 绑定数据与视图
         *
         * @param   holder      保存数据holder
         * @param   position    数据索引
         */
        override fun onBindViewHolder(holder: CodeUnitListViewHolder?, position: Int) {
            holder!!.bind(codeUnitList[position])
            return
        } // Function onBindViewHolder()

        /**
         * 获得数据项数量
         *
         * @return  数据项数量
         */
        override fun getItemCount(): Int = codeUnitList.size

        /**
         * 视图Holder
         */
        inner class CodeUnitListViewHolder(val view: View) : ViewHolder(view) {
            fun bind(codeUnit: CodeUnit) {
                Log.v(CodeUnitListView.TAG, "file:///android_asset/littlestar/${codeUnit.motion.iconPath}")
                Picasso.with(context).load("file:///android_asset/littlestar/${codeUnit.motion.iconPath}").into(view.uiMotionIcon)
                view.uiMotionName.text = codeUnit.motion.name
                view.uiLoopCount.text = SpannableStringBuilder(codeUnit.loopCount.toString())
            } // Function bind()
        }
    } // Class MotionAdapter()

    /**
     * 拖拽回调操作类
     *
     * @author  Andy
     */
    inner class ItemTouchHelperCallback : ItemTouchHelper.Callback() {
        /**
         * 获得拖拽删除标志
         *
         * @param   recyclerView    RecyclerView对象
         * @param   viewHolder      列表项（可以基于每一项进行设置，比如第一项不能删除与拖拽）
         */
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
            // 上下滑拖拽
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            // 左右滑删除
            val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            return makeMovementFlags(dragFlags, swipeFlags)
        } // Function getMovementFlags()

        /**
         * 在拖拽时调用
         *
         * @param   recyclerView    RecyclerView对象
         * @param   src             源对象
         * @param   target          目标对象
         */
        override fun onMove(recyclerView: RecyclerView, src: ViewHolder, target: ViewHolder): Boolean {
            val srcPos = src.adapterPosition
            val targetPos = target.adapterPosition
            Collections.swap(codeUnitList, srcPos, targetPos)
            mainAdapter.notifyItemMoved(srcPos, targetPos)
            return true
        } // Function onMove()

        /**
         * 在删除时调用
         *
         * @param   viewHolder      被删除的对象
         * @param   direction       删除方向（例如：是左移删除还是右移删除）
         */
        override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
            val pos = viewHolder.adapterPosition
            codeUnitList.removeAt(pos)
            mainAdapter.notifyItemRemoved(pos)
            return
        } // Function onSwiped()

    } // Class ItemTouchHelperCallback()
} // Class CodeUnitListView