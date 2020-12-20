package org.bitlap.common.utils

import org.bitlap.common.error.BitlapException

/**
 * Mail: chk19940609@gmail.com
 * Created by IceMimosa
 * Date: 2020/12/20
 */
object PreConditions {

    /**
     * check [str] cannot be null or blank
     */
    fun checkNotBlank(str: String?, key: String = "string", msg: String = "$key cannot be null or blank."): String {
        if (str.isNullOrBlank()) {
            throw BitlapException(msg)
        }
        return str
    }
}