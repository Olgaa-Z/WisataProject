package com.lauwba.session

import android.content.Context

object SessionManager {

    class Builder(context: Context) {
        private var prefsName = context.applicationContext.packageName
        private var mode: Int? = Context.MODE_PRIVATE
        private var mUseDefault = false

        fun setSessionFileName(name: String): Builder {
            this.prefsName = name
            return this
        }

        fun setMode(mode: Int = Context.MODE_PRIVATE): Builder {
            this.mode = mode
            return this
        }

        fun setUseDefault(used: Boolean): Builder {
            mUseDefault = used
            return this
        }

        fun build() {

        }
    }
}