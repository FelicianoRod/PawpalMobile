package com.example.pawpal.ui.components

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Spline(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                domStorageEnabled = true
                builtInZoomControls = true
                displayZoomControls = false
                allowFileAccess = true
                allowContentAccess = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                cacheMode = WebSettings.LOAD_DEFAULT
//                setAppCacheEnabled(true)
//                setAppCachePath(context.cacheDir.path)
            }
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.loadUrl("javascript:(function() { " +
                            "var meta = document.createElement('meta'); " +
                            "meta.name = 'viewport'; " +
                            "meta.content = 'width=device-width, initial-scale=1.0'; " +
                            "document.getElementsByTagName('head')[0].appendChild(meta);" +
                            "})()")
                }
            }
            webChromeClient = WebChromeClient()  // Añade esta línea para manejar mejor el contenido interactivo
            loadUrl(url)
        }
    }, modifier = Modifier.fillMaxSize())
}