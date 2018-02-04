package com.journaler.permission


interface PermissionRequestCallback {

    fun onPermissionGranted(permissions: List<String>)

    fun onPermissionDenied(permissions: List<String>)

}

/*
- this will be the 'callback' that will be fired when permissions are resolved


*/