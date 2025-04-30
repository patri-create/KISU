package com.kisu.test.extensions

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.*

class LocaleExtension : BeforeEachCallback, AfterEachCallback {

    private lateinit var originalLocale: Locale

    override fun beforeEach(context: ExtensionContext?) {
        originalLocale = Locale.getDefault()
        Locale.setDefault(Locale.US)
    }

    override fun afterEach(context: ExtensionContext?) {
        Locale.setDefault(originalLocale)
    }
}