package com.kober.feature.userrepos.navigation.util

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

const val UTF_8 = "UTF-8"

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { value ->
            val decodedValue = URLDecoder.decode(value, UTF_8)
            json.decodeFromString(decodedValue)
        }

    override fun parseValue(value: String): T {
        val decodedValue = URLDecoder.decode(value, UTF_8)
        return json.decodeFromString(decodedValue)
    }

    override fun serializeAsValue(value: T): String {
        val jsonString = json.encodeToString(value)
        return URLEncoder.encode(jsonString, UTF_8)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        val encodedValue = URLEncoder.encode(json.encodeToString(value), UTF_8)
        bundle.putString(key, encodedValue)
    }
}
