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

package com.sybotan.android.core.comparators

import java.io.File
import java.util.Comparator

/**
 * 文件排序器
 */
class FileComparator {
    /**
     * 名称升序
     */
    class NameAsc : Comparator<File> {
        override fun compare(file1: File, file2: File): Int {
            return if (file1.name > file2.name) {
                1
            } else {
                -1
            }
        }
    } // LastModifiedAsc

    /**
     * 名称降序
     */
    class NameDesc : Comparator<File> {
        override fun compare(file1: File, file2: File): Int {
            return if (file1.name < file2.name) {
                1
            } else {
                -1
            }
        }
    } // NameDesc

    /**
     * 创建时间升序
     */
    class LastModifiedAsc : Comparator<File> {
        override fun compare(file1: File, file2: File): Int {
            return if (file1.lastModified() > file2.lastModified()) {
                1
            } else {
                -1
            }
        }
    } // LastModifiedAsc

    /**
     * 创建时间降序
     */
    class LastModifiedDesc : Comparator<File> {
        override fun compare(file1: File, file2: File): Int {
            return if (file1.lastModified() < file2.lastModified()) {
                1
            } else {
                -1
            }
        }
    } // LastModifiedDesc
} // Class FileComparator