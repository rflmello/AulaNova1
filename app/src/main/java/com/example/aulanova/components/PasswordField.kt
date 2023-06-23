package com.example.aulanova.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close


@Composable
fun PasswordField(value: String,
                  onChange: (String) -> Unit,
                  label: String = "Senha",
                  modifier: Modifier
) {
    var passwordVisibility by remember{
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = value,
        onValueChange = { s -> onChange(s) },
        label = {
            Text(text = label)
        },
        modifier = modifier,
        visualTransformation = if (passwordVisibility)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            val icone =
                if (passwordVisibility)
                    Icons.Filled.Clear
                else
                    Icons.Filled.Close

            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = icone, contentDescription = "")
            }
        }
    )

}