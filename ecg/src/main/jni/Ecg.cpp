//
// Created by madroid on 15-8-12.
//

#include "com_hm_madroid_ecg_EcgFilter.h"
#include "android_log_print.h"


/*
 * Class:     com_hm_madroid_ecg_EcgFilter
 * Method:    getVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hm_madroid_ecg_EcgFilter_getVersion
        (JNIEnv *env, jobject obj){

    LOGI("get version" ) ;
    return (*env).NewStringUTF("version: 1") ;
};

/*
 * Class:     com_hm_madroid_ecg_EcgFilter
 * Method:    processEcg
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_hm_madroid_ecg_EcgFilter_processEcg__I
        (JNIEnv *env, jobject obj, jint rawEcg) {

    LOGI("get Java_com_hm_madroid_ecg_EcgFilter_processEcg__I" ) ;
    return rawEcg/2 ;
};

