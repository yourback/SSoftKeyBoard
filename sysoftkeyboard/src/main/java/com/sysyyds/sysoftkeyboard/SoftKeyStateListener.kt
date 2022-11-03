package com.sysyyds.sysoftkeyboard

interface SoftKeyStateListener {
    fun softKeyShowEvent(softKeyHeight: Int)
    fun softKeyHideEvent()
}