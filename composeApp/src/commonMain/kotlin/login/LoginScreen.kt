package login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import banka3_app.composeapp.generated.resources.Res
import banka3_app.composeapp.generated.resources.background
import banka3_app.composeapp.generated.resources.logofull
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.collectAsStateMultiplatform
import utils.koinViewModel

@Composable
@Preview
fun LoginScreen(
    loginViewModel: LoginViewModel = koinViewModel()
) {

    val state by loginViewModel.state.collectAsStateMultiplatform()

    LoginScreenContent(
        modifier = Modifier.consumeWindowInsets(
            WindowInsets.systemBars.only(WindowInsetsSides.Vertical)
        ),
        state.email,
        state.password,
        onEmailChange = loginViewModel::setEmail,
        onPasswordChange = loginViewModel::setPassword
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current

    Surface {
        Box(
            modifier = modifier.fillMaxSize()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = { focusManager.clearFocus() }
                )

        ) {
            Image(
                painter = painterResource(Res.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier.matchParentSize()
            )
            Column(
                modifier = modifier.fillMaxSize().padding(top = 10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(Res.drawable.logofull),
                    modifier = Modifier.size(350.dp).padding(start = 30.dp),
                    contentDescription = ""
                )

                LoginForm(
                    modifier = modifier,
                    email = email,
                    password = password,
                    onEmailChange = onEmailChange,
                    onPasswordChange = onPasswordChange,
                    focusManager = focusManager
                )
            }
        }
    }
}


@Composable
@Preview
fun LoginForm(
    modifier: Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    focusManager: FocusManager
) {

    Column(
        modifier = modifier.padding(20.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.width(350.dp),
            value = email,
            placeholder = { Text(text = "Email") },
            maxLines = 1,
            onValueChange = onEmailChange,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = modifier.height(10.dp))

        TextField(
            modifier = Modifier.width(350.dp),
            value = password,
            placeholder = { Text(text = "Password") },
            onValueChange = onPasswordChange,
            maxLines = 1,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send,
                keyboardType = KeyboardType.Password
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF114D7B),
                contentColor = Color.White
            ),
            content = { Text(text = "Login") },
            onClick = {})
    }
}