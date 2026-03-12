package com.example.littledropwelcomeui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RegisterScreen(regresar: () -> Unit) {

    var user by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    val nombreValido = user.matches(Regex("[a-zA-Z ]+$"))
    val telefonoValido = phone.matches(Regex("^\\d{10}$"))
    val correoValido = email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))
    val passwordsIguales = password.isNotEmpty() && password == confirm


    val formularioValido =
        nombreValido && telefonoValido && correoValido && passwordsIguales

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF5B4BDB)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Register",
                color = Color.White,
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        CampoTexto("User Name", user) { user = it }
        if (!nombreValido && user.isNotEmpty()){
            Text(
                text = "El nombre solo debe contener letras",
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        CampoTexto("Phone Number", phone) { phone = it }
        CampoTexto("Email Address", email) { email = it }
        CampoTexto("Password", password) { password = it }
        CampoTexto("Confirm Password", confirm) { confirm = it }

        Spacer(modifier = Modifier.height(30.dp))

        Row {

            Button(
                onClick = { },
                enabled = formularioValido,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B4BDB)
                ),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Ok")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = { regresar() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B4BDB)
                ),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Cancel")
            }

        }

    }
}

@Composable
fun CampoTexto(label: String, value: String, onChange: (String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(bottom = 15.dp)
    ) {

        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(5.dp))

        BasicTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .border(
                    2.dp,
                    Color(0xFF5B4BDB),
                    RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        )
    }
}