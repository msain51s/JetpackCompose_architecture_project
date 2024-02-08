package com.raq.motori.android.customerapp.utility

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

/**
 * Created by Manoj Sain on 31/01/24.
 */

enum class EventType(name:String){
    SCREEN_VIEW(name = FirebaseAnalytics.Event.SCREEN_VIEW), // Todo: we need to add more custom events here
    CUSTOM_EVENT(name = "custom_event"),
    LOGIN(name = FirebaseAnalytics.Event.LOGIN)

}

object AnalyticsEventHandler {

    /**
     *  @param: eventType -> FirebaseAnalytics Event Type
     *  @param: params -> values that are adding to the Event Parameter
     */
    fun logEvent(eventType: EventType,params:Bundle? = null){
        try {
            Firebase.analytics.logEvent(
                eventType.name,params
            )
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
 //Todo: reusable function
//    private fun getParamsBuilder(eventType: EventType, vararg values: Any): Bundle {
//        val bundle = Bundle().apply {
//            when (eventType) {
//                EventType.SCREEN_VIEW -> {
//                    for (i in values){
//                        this.putString(FirebaseAnalytics.Param.SCREEN_NAME,values[0] as String)
//                    }
//                }
//            }
//        }
//
//        return bundle
//    }

}