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

package com.sybotan.SyRobot.robots

import android.util.Log
import com.sybotan.android.core.utils.StringUtil
import com.sybotan.SyRobot.services.VibratorService
import org.jetbrains.anko.doAsync
import java.io.IOException
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket

/**
 * 机器人 小星
 *
 * @author  Andy
 */
class LittleStar : Robot() {
    companion object {
        // 日志标签
        private val TAG = LittleStar::class.java.name
    } // companion object

    private lateinit var socket: Socket

    init {
        doAsync {
            socket = Socket()
            socket.oobInline = true
            socket.keepAlive = true
            reconnect()
        }
    }

    /**
     * 播放指定动作
     *
     * @param   id      动作ID
     */
    override fun playMotion(id: Int) {
        val cmd = String.format("\$PM%02X", id)
        sendCommand(cmd)
        return
    } // Function playMotion()

    /**
     * 停止当前动作
     */
    override fun stopMotion() {
        sendCommand("\$SM")
        return
    } // Function stopMotion()

    /**
     * 设置位置
     *
     * @param   id      关节id
     * @param   pos     位置
     */
    override fun setPos(id: Int, pos: Int) {
        val cmd = String.format("\$AN%02X%s", id, StringUtil.intToHex(pos, 3))
        sendCommand(cmd)
        return
    } // Function setPos()

    /**
     * 设置关节零点位置
     *
     * @param   id      关节id
     * @param   pos     位置
     */
    override fun setHome(id: Int, pos: Int) {
        val cmd = String.format("\$HO%02X%s", id, StringUtil.intToHex(pos, 3))
        sendCommand(cmd)
        return
    } // Function setHome()

    /**
     * 发送控制命令
     *
     * @param   cmd     控制命令
     */
    override fun sendCommand(cmd: String) {
        Log.d(TAG, "sendCommand = $cmd")
        // 发送指令，启动震动
        VibratorService.vibrate(50)

        doAsync {
            try {
                if (!socket.isConnected) {
                    reconnect()
                }

                val out = socket.getOutputStream()
                out.write(cmd.toByteArray())
                out.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return
    } // Function sendCommand

    private fun reconnect() {
        Log.d(TAG, "reconnect-------------------------")
        socket.connect(InetSocketAddress("192.168.4.1",23))
        return
    } // Function reconnect()

} // Class RobotLittleStarTcp