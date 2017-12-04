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
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.sybotan.android.syrobot.R
import com.sybotan.android.syrobot.views.adapters.MotionCategoryAdapter
import kotlinx.android.synthetic.main.view_program_list.view.*

/**
 * 编程界面
 *
 * @author  Andy
 */
class ProgramListView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    private val TAG = ProgramListView::class.java.simpleName

    // 初始化
    init {
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_program_list, this)
        //uiMotionCategoryPager.adapter = object : MotionCategoryAdapter() {
            /**
             * 实例化列表项视图
             *
             * @param   container   容器对象
             * @param   position    数据项索引
             * @return  列表项视图
             */
            //override fun instantiateItem(container: ViewGroup, position: Int): Any {
                //val view = MotionListView(getMotionList(position)!!, context)
                //val view = CodeUnitListView(context)
                //container.addView(view)
                //return
            //} // Function instantiateItem()
        //}
    } // init

} // Class JoystickView