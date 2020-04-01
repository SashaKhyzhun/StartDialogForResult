package com.sashakhyzhun.example

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by Alexander Khyzhun on 18 March 2020.
 * Copyright (c) 2020 Sphere. All rights reserved.
 */
class ExampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

}