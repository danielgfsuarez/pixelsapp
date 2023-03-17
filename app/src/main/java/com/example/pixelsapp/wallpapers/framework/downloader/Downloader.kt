package com.example.pixelsapp.wallpapers.framework.downloader

interface Downloader {
    fun downloadFile(url: String, description: String): Long
}