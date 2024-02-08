import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raq.motori.android.customerapp.navigation.AppScreens
import com.raq.motori.android.customerapp.navigation.ArgumentKeys
import com.raq.motori.android.customerapp.screens.ForgotPasswordScreen
import com.raq.motori.android.customerapp.screens.LoginScreen
import com.raq.motori.android.customerapp.screens.RegistrationScreen

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.REGISTRATION.name) {
        composable(
            route = AppScreens.REGISTRATION.name) {
            RegistrationScreen{
                val userName = "msain51s"
                navController.navigate("${AppScreens.LOGIN.name}/${userName}")
            }
        }
        composable(
            route = "${AppScreens.LOGIN.name}/{${ArgumentKeys.USER_NAME.name}}",
            arguments = listOf(   //Defining arguments
                navArgument(name = ArgumentKeys.USER_NAME.name){
                    type = NavType.StringType
                }
            )
            ) {
            val userName = it?.arguments?.getString(ArgumentKeys.USER_NAME.name)?: "notValid userName" //getting arguments
            LoginScreen(userName){
                navController.navigate(AppScreens.FORGOT_PASSWORD.name)
            }
        }
        composable(route = AppScreens.FORGOT_PASSWORD.name) {
            ForgotPasswordScreen{
                navController.navigate(AppScreens.REGISTRATION.name)
            }
        }
    }
}