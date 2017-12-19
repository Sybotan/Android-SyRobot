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

import android.content.ClipData
import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.sybotan.android.syrobot.R
import com.sybotan.android.syrobot.entities.CodeUnit
import com.sybotan.android.syrobot.entities.Motion
import com.sybotan.android.syrobot.services.VibratorService
import kotlinx.android.synthetic.main.item_motion_list_item.view.*

/**
 * 竖向单列滑动式Motion视图控件
 *
 * @author  Andy
 */
class MotionListView(motionList: List<Motion>, context: Context, attrs: AttributeSet? = null) : RecyclerView(context, attrs) {
    companion object {
        private val TAG = MotionListView::class.java.name
    }

    val motionList: List<Motion>

    // 初始化
    init {
        layoutManager = LinearLayoutManager(context)
        this.motionList = motionList
        adapter = MotionAdapter()
        // 添加默认分隔线
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        ItemTouchHelper(ItemTouchHelperCallback()).attachToRecyclerView(this)
    } // init

    inner class MotionAdapter : Adapter<MotionAdapter.MotionViewHolder>(){
        /**
         * 创建视图holder
         *
         * @param   parent      窗口视图
         * @param   viewType    视图类型
         * @return  视图图holder
         */
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MotionViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_motion_list_item, parent, false)
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
                //view.uiMotionNumber.text = String.format("%02X", motion.id)
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
         * 在长按选中对象时调用
         *
         * @param   viewHolder      选中的对象
         * @param   actionState     状态
         */
        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                //viewHolder!!.itemView.background = Drawable(Color.GRAY)
                val pressedView = viewHolder!!.itemView
                val bg = pressedView.background
                pressedView.setBackgroundResource(R.drawable.shape_drag_effect)
                pressedView.isPressed = true
                val data = ClipData.newPlainText(TAG, CodeUnit(Motion(11, "test11"),11).toString())
                pressedView.startDrag(data, DragShadowBuilder(pressedView),null, 0)
                pressedView.isPressed = false
                pressedView.background = bg
                VibratorService.vibrate(50)
            }
            // 不掉用父类onSelectedChanged，屏蔽移动原来的对象
            //super.onSelectedChanged(viewHolder, actionState)
            return
        } // Function onSelectedChanged()

        /**
         * 获得拖拽删除标志
         *
         * @param   recyclerView    RecyclerView对象
         * @param   viewHolder      列表项（可以基于每一项进行设置，比如第一项不能删除与拖拽）
         */
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            return makeMovementFlags(dragFlags, 0)
        } // Function getMovementFlags()

        /**
         * 在拖拽时调用
         *
         * @param   recyclerView    RecyclerView对象
         * @param   src             源对象
         * @param   target          目标对象
         */
        override fun onMove(recyclerView: RecyclerView, src: ViewHolder, target: ViewHolder): Boolean {
            // DO NOTHING
            return true
        } // Function onMove()

        /**
         * 在删除时调用
         *
         * @param   viewHolder      被删除的对象
         * @param   direction       删除方向（例如：是左移删除还是右移删除）
         */
        override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
            // DO NOTHING
            return
        } // Function onSwiped()

    } // Class ItemTouchHelperCallback()
} // Class MotionGridView
