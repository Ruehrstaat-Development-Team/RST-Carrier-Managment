import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.skiko.Cursor
import ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    AppTheme() {

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var loading by remember { mutableStateOf(false) }
        var rememberPassword by remember { mutableStateOf(false) }

        Scaffold(
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Card(modifier = Modifier.width(500.dp)) {
                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Image(
                            painter = painterResource("background.png"),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth().height(150.dp).align(Alignment.TopCenter)
                        )
                        Card(modifier = Modifier.align(Alignment.BottomCenter)) {
                            Image(
                                painter = painterResource("ic_logo.png"),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center,
                                modifier = Modifier.size(width = 125.dp, height = 125.dp).padding(10.dp)
                                    .clip(MaterialTheme.shapes.medium)
                            )
                        }
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
                        Text("RST Carrier Management System", style = MaterialTheme.typography.titleLarge)
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = { Text("Username") },
                            modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                            enabled = !loading,
                            maxLines = 1,
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                            enabled = !loading,
                            maxLines = 1,
                            singleLine = true,
                            trailingIcon = {
                                IconButton(
                                    onClick = { passwordVisible = !passwordVisible },
                                    modifier = Modifier.pointerHoverIcon(
                                        PointerIcon(Cursor(Cursor.HAND_CURSOR))
                                    )
                                ) {
                                    if (passwordVisible)
                                        Icon(Icons.Filled.Visibility, contentDescription = null)
                                    else
                                        Icon(Icons.Filled.VisibilityOff, contentDescription = null)
                                }
                            }
                        )
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                            Checkbox(checked = rememberPassword, onCheckedChange = { rememberPassword = it }, enabled = !loading)
                            Text("Passwort speichern")
                        }
                        Button(onClick = { loading = true}, modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(), enabled = !loading) {
                            if(loading)
                                CircularProgressIndicator(modifier = Modifier.size(20.dp))
                            else
                                Text("Login")
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Divider(modifier = Modifier.padding(start = 20.dp).weight(2f))
                            Text("oder", modifier = Modifier.padding(horizontal = 20.dp))
                            Divider(modifier = Modifier.padding(end = 20.dp).weight(2f))
                        }
                        OutlinedButton(onClick = {}, modifier = Modifier.padding(top = 10.dp).fillMaxWidth(), enabled = !loading) {
                            Text("Registrieren")
                        }
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(state = rememberWindowState(placement = WindowPlacement.Maximized), onCloseRequest = ::exitApplication) {
        App()
    }
}
