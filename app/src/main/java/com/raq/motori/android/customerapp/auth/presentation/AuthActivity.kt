package com.raq.motori.android.customerapp.auth.presentation

import ae.sdg.libraryuaepass.UAEPassAccessCodeCallback
import ae.sdg.libraryuaepass.UAEPassAccessTokenCallback
import ae.sdg.libraryuaepass.UAEPassController
import ae.sdg.libraryuaepass.UAEPassProfileCallback
import ae.sdg.libraryuaepass.business.profile.model.ProfileModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.google.firebase.analytics.FirebaseAnalytics
import com.raq.motori.android.customerapp.MainActivity
import com.raq.motori.android.customerapp.R
import com.raq.motori.android.customerapp.auth.domain.model.UAEPassRequestModels
import com.raq.motori.android.customerapp.auth.presentation.viewmodel.AuthViewModel
import com.raq.motori.android.customerapp.base.presentation.BaseActivity
import com.raq.motori.android.customerapp.hellocard.HelloCard
import com.raq.motori.android.customerapp.ui.theme.ArchitectureTheme
import com.raq.motori.android.customerapp.utility.AnalyticsEventHandler
import com.raq.motori.android.customerapp.utility.EventType
import com.raq.motori.android.customerapp.utility.components.ImageBuilder
import com.raq.motori.android.customerapp.utility.components.RMButton
import com.raq.motori.android.customerapp.utility.components.RMLoader
import com.raq.motori.android.customerapp.utility.components.RMTextLarge
import com.raq.motori.android.customerapp.utility.components.RMTextSmall
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivity : BaseActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val isLoading by remember { mutableStateOf(false) }

                    RMLoader(isShowingDialog = isLoading)
                    AuthContent(onLogin = {

                        //hitting api to get the Access Code
                        code

                        AnalyticsEventHandler.logEvent(
                            EventType.LOGIN,
                            Bundle().apply {
                                putString(FirebaseAnalytics.Param.METHOD, "Login by UAE Pass")
                            }
                        )
                    })
                }
            }
        }
    }

    /**
     * Login with UAE Pass and get the access Code.
     */
    private val code: Unit
        private get() {
            lifecycleScope.launch {
                val requestModel =
                    UAEPassRequestModels.getAuthenticationRequestModel(this@AuthActivity)
                UAEPassController.getAccessCode(
                    this@AuthActivity,
                    requestModel,
                    object : UAEPassAccessCodeCallback {
                        override fun getAccessCode(code: String?, error: String?) {
                            if (error != null) {
                                Toast.makeText(
                                    this@AuthActivity,
                                    "Error while getting access token",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this@AuthActivity,
                                    "Access Code Received",
                                    Toast.LENGTH_SHORT
                                ).show()
                                login()
                            }
                        }
                    })
            }
        }

    /**
     * Login with UAE Pass and get the access token.
     */
    private fun login() {
        val requestModel = UAEPassRequestModels.getAuthenticationRequestModel(this@AuthActivity)
        UAEPassController.getAccessToken(
            this@AuthActivity,
            requestModel,
            object : UAEPassAccessTokenCallback {
                override fun getToken(accessToken: String?, state: String, error: String?) {
                    if (error != null) {
                        Toast.makeText(
                            this@AuthActivity,
                            "Error while getting access token",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@AuthActivity,
                            "Access Token Received",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        //getting profile
                        profile
                    }
                }
            })
    }

    /**
     * Get User Profile from UAE Pass.
     */
    private val profile: Unit
        private get() {
            val requestModel = UAEPassRequestModels.getProfileRequestModel(this@AuthActivity)
            UAEPassController.getUserProfile(
                this@AuthActivity,
                requestModel,
                object : UAEPassProfileCallback {
                    override fun getProfile(
                        profileModel: ProfileModel?,
                        state: String,
                        error: String?
                    ) {
                        if (error != null) {
                            Toast.makeText(
                                this@AuthActivity,
                                "Error while getting access token",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val name = profileModel?.firstnameEN + " " + profileModel?.lastnameEN
                            Toast.makeText(this@AuthActivity, "Welcome $name", Toast.LENGTH_SHORT)
                                .show()

                            AnalyticsEventHandler.logEvent(
                                EventType.LOGIN,
                                Bundle().apply {

                                }
                            )
                            startActivity(
                                Intent(this@AuthActivity, MainActivity::class.java)
                            )
                        }
                    }
                })
        }
}

@Composable
fun AuthContent(onLogin: () -> Unit) {
    var emailValue by remember {
        mutableStateOf(value = "")
    }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                ImageBuilder(image = R.drawable.uae_pass_icon)
            }
            RMTextLarge(
                text = stringResource(id = R.string.uae_pass_text),
                modifier = Modifier.padding(bottom = 10.dp)
            )

//        OutlinedTextField(
//            value = emailValue,
//            onValueChange = { emailValue = it },
//            label = { Text(text = "Emirates ID,Email or Phone") })
//
//        CheckboxComponent()
            RMButton(
                onClick = { onLogin() }
            ) {
                Text(text = stringResource(id = R.string.login_text))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
            ) {
                RMTextSmall(
                    text = stringResource(id = R.string.forgot_passowrd_text),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                RMTextSmall(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(id = R.string.create_new_account_text),
                    color = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun AuthContentPreview() {
    HelloCard()
  //  AuthContent({})
}

///FixMe : Temporary place : move it to component
@Composable
fun CheckboxComponent() {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
        Text(text = "Remember Me")
    }
}

