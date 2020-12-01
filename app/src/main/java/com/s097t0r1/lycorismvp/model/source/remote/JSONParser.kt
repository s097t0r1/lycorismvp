package com.s097t0r1.lycorismvp.model.source.remote

import org.json.JSONArray
import org.json.JSONObject

const val ID_TAG = "id"
const val DESCRIPTION_TAG = "description"
const val IMAGE_URL_TAG = "urls"
const val IMAGE_SIZE_TAG = "regular"

class JSONParser() {
    companion object {
        fun parse(JSONArray: JSONArray): List<RemotePhoto> {

            val resultList: MutableList<RemotePhoto> = ArrayList()

            for (i in 0 until JSONArray.length()) {
                val obj = JSONArray.getJSONObject(i)
                resultList.add(
                    RemotePhoto(
                        id = obj.getString(ID_TAG),
                        imageUrl = obj.getJSONObject(IMAGE_URL_TAG).getString(IMAGE_SIZE_TAG),
                        description = obj.getString(DESCRIPTION_TAG) ?: ""
                    )
                )
            }

            return resultList
        }

        fun parse(JSONObject: JSONObject): RemotePhoto {
            return RemotePhoto(
                id = JSONObject.getString(ID_TAG),
                imageUrl = JSONObject.getJSONObject(IMAGE_URL_TAG).getString(IMAGE_SIZE_TAG),
                description = JSONObject.getString(DESCRIPTION_TAG) ?: ""
            )
        }
    }
}