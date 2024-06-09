package login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import banka3_app.composeapp.generated.resources.Res
import banka3_app.composeapp.generated.resources.background
import banka3_app.composeapp.generated.resources.logofull
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LoginScreen() {
    LoginScreenContent(
        modifier = Modifier.consumeWindowInsets(
            WindowInsets.systemBars.only(WindowInsetsSides.Vertical)
        )
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface {
        Box(
            modifier = modifier.fillMaxSize()
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
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it }
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
            onValueChange = onEmailChange
        )

        Spacer(modifier = modifier.height(10.dp))

        TextField(
            modifier = Modifier.width(350.dp),
            value = password,
            placeholder = { Text(text = "Password") },
            onValueChange = onPasswordChange
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