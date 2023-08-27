package com.example.movieapplication.base

import retrofit2.Response

inline fun <T> Response<T>.handleResponseV2(
    onSuccess: (result: T?) -> Unit,
    onFailed: (result: String) -> Unit,
) {
    try {
        if (this.code() == 200) {
            onSuccess.invoke(this.body())
        } else {
            onFailed.invoke("${this.code().toString()}")
        }
    } catch (e: Exception) {
        onFailed.invoke("${e.localizedMessage}")
    }

}
