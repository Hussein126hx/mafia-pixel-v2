package com.hussain.mafiapixel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MafiaPixelApp()
        }
    }
}

@Composable
fun MafiaPixelApp() {
    var gamePhase by remember { mutableStateOf("MAIN_MENU") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF121212)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MAFIA PIXEL",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD32F2F)
            )
            Text(
                text = "Local Network & AI Edition",
                fontSize = 14.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            when (gamePhase) {
                "MAIN_MENU" -> MainMenuScreen(
                    onCreateGame = { gamePhase = "LOBBY" },
                    onJoinGame = { gamePhase = "LOBBY" }
                )
                "LOBBY" -> LobbyScreen(onStartGame = { gamePhase = "GAME" })
                "GAME" -> GameScreen()
            }
        }
    }
}

@Composable
fun MainMenuScreen(onCreateGame: () -> Unit, onJoinGame: () -> Unit) {
    Button(
        onClick = onCreateGame,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text("إنشاء غرفة (HOST)", color = Color.White, fontSize = 18.sp)
    }

    Button(
        onClick = onJoinGame,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF424242)),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text("الانضمام عبر الهوتسبوت (JOIN)", color = Color.White, fontSize = 18.sp)
    }
}

@Composable
fun LobbyScreen(onStartGame: () -> Unit) {
    Text("قائمة الانتظار المحلية...", color = Color.White, fontSize = 20.sp)
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = onStartGame,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
    ) {
        Text("بدء اللعبة وتفعيل الـ AI Bots", color = Color.White)
    }
}

@Composable
fun GameScreen() {
    Text("اللعبة بدأت! (الليل يحيم على المدينة)", color = Color(0xFFE53935), fontSize = 22.sp)
}
