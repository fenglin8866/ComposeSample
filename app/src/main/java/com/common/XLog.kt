package com.common

import android.util.Log

object XLog {
    private const val TAG = "SampleDemo"
    private var debug = true

    fun debug(enable: Boolean) {
        debug = enable
    }

    fun d(tag: String, msg: String?) {
        if (debug) {
            Log.d(TAG, getMsg(tag, msg))
        }
    }

    fun i(tag: String, msg: String?) {
        Log.i(TAG, getMsg(tag, msg))
    }

    fun iDebug(tag: String, msg: String?) {
        if (debug) {
            Log.i(TAG, getMsg(tag, msg))
        }
    }

    fun w(tag: String, msg: String?) {
        Log.w(TAG, getMsg(tag, msg))
    }

    fun e(tag: String, msg: String?) {
        Log.e(TAG, getMsg(tag, msg))
    }

    private fun getMsg(tag: String, msg: String?): String {
        return "[$tag] $msg"
    }
}