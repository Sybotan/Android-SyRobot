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

package com.sybotan.android.core.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser

import java.io.*
import java.util.ArrayList

/**
 * Gson工具类
 *
 * @author  Andy
 */
object GsonUtil {
    private val TAG = GsonUtil::class.java.simpleName

    fun toJson(obj: Any): String {
        val gson = Gson()
        return gson.toJson(obj)
    } // Function writeToFile()

    fun writeToFile(fileName: String, obj: Any) {
        val gson = Gson()
        val jsonData = gson.toJson(obj)
        Log.d(TAG, jsonData)
        val file = File(fileName)
        val dir = file.parentFile
        if (!dir.exists()) {
            dir.mkdirs()
        }
        try {
            val outputStream = FileOutputStream(file)
            outputStream.write(jsonData.toByteArray())
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return
    } // Function writeToFile()

    /**
     * 将Json数据解析成相应的映射对象
     *
     * @param jsonData  要解析的JSON数据
     * @param type      类型
     * @param <T>       泛型
     * @return          解析结果
    </T> */
    fun <T> parseJson(jsonData: String, type: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(jsonData, type)
    } // Function parseJson()

    /**
     * 将Json数据解析成相应的映射对象
     *
     * @param in        要解析的JSON数据输入源
     * @param type      类型
     * @param <T>       泛型
     * @return          解析结果
    </T> */
    @Throws(IOException::class)
    fun <T> parseJson(`in`: InputStream, type: Class<T>): T {
        val count = `in`.available()
        val buf = ByteArray(count)
        var readCount = 0
        while (readCount < count) {
            readCount += `in`.read(buf, readCount, count - readCount)
        }

        return parseJson(String(buf), type)
    } // Function parseJson()

    /**
     * 将Json数据解析成相应的映射对象
     *
     * @param reader    要解析的JSON数据读取器
     * @param type      类型
     * @param <T>       泛型
     * @return          解析结果
    </T> */
    fun <T> parseJson(reader: Reader, type: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(reader, type)
    } // Function parseJson()

    /**
     * 将Json数据解析成相应的映射对象列表
     *
     * @param jsonData  要解析的JSON数据
     * @param type      类型
     * @param <T>       泛型
     * @return          解析结果
    </T> */
    fun <T> parseJsonArray(jsonData: String, type: Class<T>): List<T> {
        val gson = Gson()
        val result = ArrayList<T>()
        val array = JsonParser().parse(jsonData).asJsonArray
        for (elem in array) {
            result.add(gson.fromJson(elem, type))
        }
        return result
    } // Function parseJsonArray()

    /**
     * 将Json数据解析成相应的映射对象列表
     *
     * @param in        要解析的JSON数据输入源
     * @param type      类型
     * @param <T>       泛型
     * @return          解析结果
    </T> */
    @Throws(IOException::class)
    fun <T> parseJsonArray(`in`: InputStream, type: Class<T>): List<T> {
        val count = `in`.available()
        val buf = ByteArray(count)
        var readCount = 0
        while (readCount < count) {
            readCount += `in`.read(buf, readCount, count - readCount)
        }

        return parseJsonArray(String(buf), type)
    } // Function parseJsonArray()

    /**
     * 将Json数据解析成相应的映射对象列表
     *
     * @param reader    要解析的JSON数据读取器
     * @param type      类型
     * @param <T>       泛型
     * @return          解析结果
    </T> */
    fun <T> parseJsonArray(reader: Reader, type: Class<T>): List<T> {
        val gson = Gson()
        val result = ArrayList<T>()
        val array = JsonParser().parse(reader).asJsonArray
        for (elem in array) {
            result.add(gson.fromJson(elem, type))
        }
        return result
    } // Function parseJsonArray()
} // Class GsonUtil
