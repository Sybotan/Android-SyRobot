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

package com.sybotan.android.syrobot.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.sybotan.android.syrobot.R
import com.sybotan.android.syrobot.SyRobot
import com.sybotan.android.syrobot.activities.ProgrammingActivity
import com.sybotan.android.syrobot.preferences.Opts
import com.sybotan.android.core.comparators.FileComparator
import kotlinx.android.synthetic.main.fragment_program_list.view.*
import java.io.File
import java.io.IOException
import java.util.*

/**
 * 编程界面
 *
 * @author  Andy
 */
class ProgramListFragment(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    companion object {
        private val TAG = ProgramListFragment::class.java.name
    }

    val programPath = "${SyRobot.appStorePath}/${Opts.robot}"
    var fileList: List<File>? = null

    // 初始化
    init {
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.fragment_program_list, this)

        // 初始化路径
        initPath()

        // 设置UI
        setupUi()
    } // init

    /**
     * 初始化机器人程序路径。如果不存在，则创建
     */
    private fun initPath() {
        // 如果程序文件夹不存，则创建
        val path = File(programPath)
        if (!path.exists()) {
            Log.d(TAG, "mkdir = $programPath            ${path.mkdirs()}")
            path.mkdirs()
        }

        try {
            fileList = path.listFiles()!!.asList()
            Collections.sort(fileList, FileComparator.LastModifiedDesc())
            for (file in fileList!!) {
                Log.d(TAG, "name = ${file.name}  size=${file.lastModified()}")
            }
        } catch (e: Exception) {
            // DO NOTHING
        }

        return
    } // Function initPath()

    /**
     * 设置UI
     */
    private fun setupUi() {
        initFileList()

        // 新建按钮
        uiNewButton.setOnClickListener {
            val pargramNameEdit = EditText(context)
            AlertDialog.Builder(context).setTitle("请输入程序名").setIcon(
                    android.R.drawable.ic_dialog_info).setView(pargramNameEdit)
                    .setPositiveButton("确定") { dialog, which ->
                        val programName = pargramNameEdit.text.toString().trim { it <= ' ' }
                        newProgram(programName)
                    }
                    .setNegativeButton("取消", null).show()
        } // uiNewButton

        // 删除按钮
        uiDeleteButton.setOnClickListener {

        } // uiDeleteButton

        return
    } // Function setupUi()

    /**
     * 初始化文件列表
     */
    private fun initFileList() {
        uiProgramList.layoutManager = LinearLayoutManager(context)
        // 添加默认分隔线
        uiProgramList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        uiProgramList.adapter = FileAdapter()
        ItemTouchHelper(ItemTouchHelperCallback()).attachToRecyclerView(uiProgramList)
        return
    } // Function initFileList()

    /**
     * 新建程序
     */
    private fun newProgram(program: String) {
        try {
            File("$programPath/$program").createNewFile()

            var intent = Intent(context, ProgrammingActivity::class.java)
            intent.putExtra(ProgrammingActivity.PROGRAM_NAME_PARM, program)
            context.startActivity(intent)
        } catch (e: IOException) {
            // DO NOTHING
        }

        return
    } // Function newProgram()


    inner class FileAdapter : RecyclerView.Adapter<ProgramListFragment.FileAdapter.FileViewHolder>(){
        /**
         * 创建视图holder
         *
         * @param   parent      窗口视图
         * @param   viewType    视图类型
         * @return  视图图holder
         */
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FileViewHolder {
            val view = TextView(parent!!.context)
            return FileViewHolder(view)
        } // Function onCreateViewHolder()

        /**
         * 绑定数据与视图
         *
         * @param   holder      保存数据holder
         * @param   position    数据索引
         */
        override fun onBindViewHolder(holder: FileViewHolder?, position: Int) {
            holder!!.bind(fileList!![position])
            return
        } // Function onBindViewHolder()

        /**
         * 获得数据项数量
         *
         * @return  数据项数量
         */
        override fun getItemCount(): Int = if (null != fileList) fileList!!.size else 0

        /**
         * 视图Holder
         */
        inner class FileViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            fun bind(file: File) {
                (view as TextView).text = file.name
                return
            } // Function bind()
        } // Class FileViewHolder

    } // Class FileAdapter

    /**
     * 拖拽回调操作类
     *
     * @author  Andy
     */
    inner class ItemTouchHelperCallback : ItemTouchHelper.Callback(){
        /**
         *
         */
        override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
            // 左右滑删除
            val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            return makeMovementFlags(0, swipeFlags)
        } // Function getMovementFlags()

        /**
         *
         */
        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
            // DO NOTHING
            return true
        } // Function onMove()

        /**
         *
         */
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        } // Function onSwiped()

    } // Class ItemTouchHelperCallback
} // Class JoystickView