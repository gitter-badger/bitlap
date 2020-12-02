package org.bitlap.common.utils

import org.bitlap.common.bitmap.BBM
import org.bitlap.common.bitmap.BM
import org.bitlap.common.bitmap.RBM

/**
 * Desc: [BM] Utils
 *
 * Mail: chk19940609@gmail.com
 * Created by IceMimosa
 * Date: 2020/11/21
 */
object BMUtils {

    /**
     * Bitmaps aggregate with [or] function, [T] should be the same implement.
     */
    inline fun <reified T> or(vararg bms: T): T where T : BM = or(bms.toList())
    inline fun <reified T> or(bms: Collection<T>): T where T : BM {
        if (bms.isEmpty()) {
            return T::class.java.newInstance()
        }
        if (bms.size == 1) {
            return bms.first()
        }
        if (bms.size == 2) {
            return or(bms.first(), bms.last())
        }
        return when (T::class.java) {
            RBM::class.java -> RBM.or(*bms.map { it as RBM }.toTypedArray())
            BBM::class.java -> BBM.or(*bms.map { it as BBM }.toTypedArray())
            else -> throw IllegalArgumentException()
        } as T
    }

    /**
     * Bitmaps aggregate with [and] function, [T] should be the same implement.
     */
    inline fun <reified T> and(vararg bms: T): T where T : BM = and(bms.toList())
    inline fun <reified T> and(bms: Collection<T>): T where T : BM {
        if (bms.isEmpty()) {
            return T::class.java.newInstance()
        }
        if (bms.size == 1) {
            return bms.first()
        }
        if (bms.size == 2) {
            return and(bms.first(), bms.last())
        }
        return when (T::class.java) {
            RBM::class.java -> RBM.and(*bms.map { it as RBM }.toTypedArray())
            BBM::class.java -> BBM.and(*bms.map { it as BBM }.toTypedArray())
            else -> throw IllegalArgumentException()
        } as T
    }

    /**
     * Two Bitmap [or] function, [T1] can be different implement
     */
    inline fun <reified T1, reified T2> or(bm1: T1, bm2: T2): T1 where T1 : BM, T2 : BM {
        return when (T1::class.java) {
            RBM::class.java -> RBM.or(bm1 as RBM, bm2.getRBM())
            BBM::class.java -> when (T2::class.java) {
                RBM::class.java -> BBM.or(bm1 as BBM, bm2 as RBM)
                BBM::class.java -> BBM.or(bm1 as BBM, bm2 as BBM)
                else -> throw IllegalArgumentException()
            }
            else -> throw IllegalArgumentException()
        } as T1
    }

    /**
     * Two Bitmap [and] function, [T1] can be different implement
     */
    inline fun <reified T1, reified T2> and(bm1: T1, bm2: T2): T1 where T1 : BM, T2 : BM {
        return when (T1::class.java) {
            RBM::class.java -> RBM.and(bm1 as RBM, bm2.getRBM())
            BBM::class.java -> when (T2::class.java) {
                RBM::class.java -> BBM.and(bm1 as BBM, bm2 as RBM)
                BBM::class.java -> BBM.and(bm1 as BBM, bm2 as BBM)
                else -> throw IllegalArgumentException()
            }
            else -> throw IllegalArgumentException()
        } as T1
    }


    /**
     * Two Bitmap [andNot] function, [T1] can be different implement
     */
    inline fun <reified T1, reified T2> andNot(bm1: T1, bm2: T2): T1 where T1 : BM, T2 : BM {
        return when (T1::class.java) {
            RBM::class.java -> RBM.andNot(bm1 as RBM, bm2.getRBM())
            BBM::class.java -> when (T2::class.java) {
                RBM::class.java -> BBM.andNot(bm1 as BBM, bm2 as RBM)
                BBM::class.java -> BBM.andNot(bm1 as BBM, bm2 as BBM)
                else -> throw IllegalArgumentException()
            }
            else -> throw IllegalArgumentException()
        } as T1
    }

    /**
     * Two Bitmap [xor] function, [T1] can be different implement
     */
    inline fun <reified T1, reified T2> xor(bm1: T1, bm2: T2): T1 where T1 : BM, T2 : BM {
        return when (T1::class.java) {
            RBM::class.java -> RBM.xor(bm1 as RBM, bm2.getRBM())
            BBM::class.java -> when (T2::class.java) {
                RBM::class.java -> BBM.xor(bm1 as BBM, bm2 as RBM)
                BBM::class.java -> BBM.xor(bm1 as BBM, bm2 as BBM)
                else -> throw IllegalArgumentException()
            }
            else -> throw IllegalArgumentException()
        } as T1
    }
}