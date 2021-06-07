//package com.example.tlunet.http
//
//import okhttp3.Interceptor
//import okhttp3.Response
//import org.json.JSONObject
//
//class AuthenticateInterceptor : Interceptor {
//    private var namePhone = ""
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        val builder = request.headers().newBuilder()
////        builder.add("X-FIIN-CLIENT-OS", "android")
//
//
////        val authentication = Preferences.getInstance().getToken()
////        if (!TextUtils.isEmpty(authentication)) {
////            if (BuildConfig.DEBUG)
////            {
////            Log.d("hahaToken", "token: "+authentication)
////            }
//
////            builder.add("Authentication", "Bearer $authentication")
////        }
//
////        val headers = builder.build()
////        val newRequest = request.newBuilder().headers(headers).build()
//        val response = chain.proceed(null)
////        val response = chain.proceed(newRequest)
////        val res = response.peekBody(Long.MAX_VALUE)
////        res.let {
////            try {
////                val root = JSONObject(it.string())
////                val code = root.getString("code")
////                val message = root.getString("message")
////                val status = root.getString("status")
////                if (status == "success"){
////                    return response
////                }
////
////                if (status == "error"){
////                    val newResBody: ResponseBody = ResponseBody.create(
////                        MediaType.get(response.header("Content-Type")?:"application/json"),
////                        createBodyErr(root).toString()
////                    )
////                    val newRes: Response = response.newBuilder().body(newResBody).build()
////                    when(code){
////                        "E001"->{RxBus.publish(EventType.SESSION_EXPIRE, message)}
////                        "EL009"->{
////                            Preferences.getInstance().clearToken()
////                            RxBus.publish(EventType.TOKEN_INVALID, message)
////                        }
////                        "E006"->{RxBus.publish(EventType.SESSION_EXPIRE, message)}
////                        "E003"->{RxBus.publish(EventType.SESSION_VERSION, message)}
////                        else->{}
////                    }
////                    return newRes
////                }
////
////            } catch (e: JSONException) {
////                val x = ""
////            }
////        }
//        return response
//    }
//    private fun createBodyErr(json: JSONObject): JSONObject {
//        val bodyErr = JSONObject()
//            .put("code", json.getString("code"))
//            .put("status", json.getString("status"))
//            .put("message", json.getString("message"))
//        //
//        if (json.has("data")){
//            bodyErr.put("dataErr", json.getString("data"))
//        }
//        return bodyErr
//    }
//}