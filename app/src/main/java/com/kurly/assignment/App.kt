package com.kurly.assignment

import android.app.Application
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
                add(SvgDecoder.Factory())
            }
            .logger(DebugLogger())
            .crossfade(true)                  //## 이미지를 부드럽게 전환
            .memoryCachePolicy(CachePolicy.ENABLED) //## 메모리 캐시 활성화
            .diskCachePolicy(CachePolicy.ENABLED)   //## 디스크 캐시 활성화
            .build()
    }

}