package com.raq.motori.android.customerapp.auth.domain.model

import ae.sdg.libraryuaepass.business.Environment
import ae.sdg.libraryuaepass.business.Language
import ae.sdg.libraryuaepass.business.authentication.model.UAEPassAccessTokenRequestModel
import ae.sdg.libraryuaepass.business.documentsigning.model.DocumentSigningRequestParams
import ae.sdg.libraryuaepass.business.documentsigning.model.UAEPassDocumentDownloadRequestModel
import ae.sdg.libraryuaepass.business.documentsigning.model.UAEPassDocumentSigningRequestModel
import ae.sdg.libraryuaepass.business.profile.model.UAEPassProfileRequestModel
import ae.sdg.libraryuaepass.utils.Utils.generateRandomString
import android.content.Context
import android.content.pm.PackageManager
import com.raq.motori.android.customerapp.BuildConfig
import java.io.File
import java.util.Objects

/**
 * Created by Farooq Arshed on 12/11/18.
 */
object UAEPassRequestModels {
    //UAE PASS START -- ADD BELOW FIELDS
    private const val UAE_PASS_CLIENT_ID = BuildConfig.CLIENT_ID
    private const val UAE_PASS_CLIENT_SECRET = BuildConfig.CLIENT_SECRET
    private const val REDIRECT_URL = BuildConfig.REDIRECT_URL

    //UAE PASS END -- ADD BELOW FIELDS
    private const val DOCUMENT_SIGNING_SCOPE = "urn:safelayer:eidas:sign:process:document"
    private const val RESPONSE_TYPE = "code"
    private const val SCOPE = "urn:uae:digitalid:profile"
    private const val ACR_VALUES_MOBILE = "urn:digitalid:authentication:flow:mobileondevice"
    private const val ACR_VALUES_WEB = "urn:safelayer:tws:policies:authentication:level:low"
    private const val UAE_PASS_PACKAGE_ID = "ae.uaepass.mainapp"
    private const val UAE_PASS_QA_PACKAGE_ID = "ae.uaepass.mainapp.qa"
    private const val UAE_PASS_STG_PACKAGE_ID = "ae.uaepass.mainapp.stg"
    private const val SCHEME = BuildConfig.URI_SCHEME
    private const val FAILURE_HOST = BuildConfig.URI_HOST_FAILURE
    private const val SUCCESS_HOST = BuildConfig.URI_HOST_SUCCESS
    private val STATE = generateRandomString(24)
    val UAE_PASS_ENVIRONMENT: Environment
        get() = when (BuildConfig.ENVIRONMENT) {
            0 -> {
                Environment.QA
            }
            1 -> {
                Environment.STAGING
            }
            else -> {
                Environment.PRODUCTION
            }
        }

    private fun isPackageInstalled(packageManager: PackageManager): Boolean {
        val packageName = when (UAE_PASS_ENVIRONMENT) {
            is Environment.STAGING -> {
                UAE_PASS_STG_PACKAGE_ID
            }
            is Environment.QA -> {
                UAE_PASS_QA_PACKAGE_ID
            }
            is Environment.PRODUCTION -> {
                UAE_PASS_PACKAGE_ID
            }
            else -> {
                UAE_PASS_PACKAGE_ID
            }
        }
        var found = true
        try {
            packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            found = false
        }
        return found
    }

    fun getAuthenticationRequestModel(context: Context): UAEPassAccessTokenRequestModel {
        val ACR_VALUE = if (isPackageInstalled(context.packageManager)) {
            ACR_VALUES_MOBILE
        } else {
            ACR_VALUES_WEB
        }
        return UAEPassAccessTokenRequestModel(
            UAE_PASS_ENVIRONMENT,
            UAE_PASS_CLIENT_ID,
            UAE_PASS_CLIENT_SECRET,
            SCHEME,
            FAILURE_HOST,
            SUCCESS_HOST,
            REDIRECT_URL,
            SCOPE,
            RESPONSE_TYPE,
            ACR_VALUE,
            STATE,
            Language.EN  //Todo: change as per selected Locale
        )
    }

    fun getDocumentRequestModel(
        file: File?,
        documentSigningParams: DocumentSigningRequestParams
    ): UAEPassDocumentSigningRequestModel {
        return UAEPassDocumentSigningRequestModel(
            UAE_PASS_ENVIRONMENT,
            UAE_PASS_CLIENT_ID,
            UAE_PASS_CLIENT_SECRET,
            SCHEME,
            FAILURE_HOST,
            SUCCESS_HOST,
            Objects.requireNonNull(documentSigningParams.finishCallbackUrl),
            DOCUMENT_SIGNING_SCOPE,
            file!!,
            documentSigningParams
        )
    }

    fun getDocumentDownloadRequestModel(
        documentName: String?,
        documentURL: String?
    ): UAEPassDocumentDownloadRequestModel {
        return UAEPassDocumentDownloadRequestModel(
            UAE_PASS_ENVIRONMENT,
            UAE_PASS_CLIENT_ID,
            UAE_PASS_CLIENT_SECRET,
            DOCUMENT_SIGNING_SCOPE,
            documentName!!,
            documentURL!!
        )
    }

    fun getProfileRequestModel(context: Context): UAEPassProfileRequestModel {
        val ACR_VALUE = if (isPackageInstalled(context.packageManager)) {
            ACR_VALUES_MOBILE
        } else {
            ACR_VALUES_WEB
        }
        return UAEPassProfileRequestModel(
            UAE_PASS_ENVIRONMENT,
            UAE_PASS_CLIENT_ID,
            UAE_PASS_CLIENT_SECRET,
            SCHEME,
            FAILURE_HOST,
            SUCCESS_HOST,
            REDIRECT_URL,
            SCOPE,
            RESPONSE_TYPE,
            ACR_VALUE,
            STATE,
            Language.EN  //Todo: change as per selected Locale
        )
    }
}