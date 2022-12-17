package com.example.mysmarthome.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysmarthome.R

@Composable
fun DropDownMenu(options: Array<String>, optionSelected: String): String {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = options
    var selectedText by remember { mutableStateOf(optionSelected) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            text = selectedText,
            maxLines = 2,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(end = 20.dp)
        )
        Icon(icon, "contentDescription",
            Modifier
                .clickable { expanded = !expanded })
        Box(modifier = Modifier.padding(top = 50.dp)) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText = label
                        expanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }
    return selectedText
}

@Composable
fun DropDownMenuOutlined(modifier1: Modifier, modifier2: Modifier, options: Array<String>): String {
    var expanded by remember { mutableStateOf(false) }
    val select: String = stringResource(id = com.example.mysmarthome.R.string.select)
    var selectedText by remember { mutableStateOf(select) }
    val suggestions = options

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    OutlinedTextField(
        value = selectedText,
        onValueChange = { selectedText = it },
        modifier = modifier1,
        trailingIcon = {
            Icon(icon, "contentDescription",
                Modifier.clickable { expanded = !expanded })
        }
    )
    Box(modifier2) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(200.dp)
        ) {

            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }

            }
        }
    }
    return selectedText
}

@Composable
fun SimpleTextField(modifier: Modifier, placeholder: String, label: String): String {
    var field by remember {
        mutableStateOf(TextFieldValue(""))
    }
    TextField(colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Blue,
        unfocusedIndicatorColor = Color.Blue,
        disabledIndicatorColor = Color.Blue
    ),
        modifier = modifier,
        value = field,
        shape = RoundedCornerShape(7.dp),
        onValueChange = { field = it },
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) })
    return field.text
}

@Composable
fun SimplePasswordTextField(modifier: Modifier, placeholder: String, label: String): String {
    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    val icon = if (passwordVisibility)
        painterResource(id = android.R.drawable.ic_partial_secure)
    else
        painterResource(id = android.R.drawable.ic_secure)

    TextField(
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue,
            disabledIndicatorColor = Color.Blue
        ),
        modifier = modifier,
        value = password,
        onValueChange = { password = it },
        shape = RoundedCornerShape(7.dp),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(painter = icon, contentDescription = "")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    )
    return password.text
}

@Composable
fun SimpleNumberTextField(modifier: Modifier, title: String, placeholder: String, label: String): String {
    var field by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Text(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
        fontWeight = FontWeight.Bold,
        text = title
    )

    TextField(
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue,
            disabledIndicatorColor = Color.Blue
        ),
        modifier = modifier,
        value = field,
        shape = RoundedCornerShape(7.dp),
        onValueChange = { field = it },
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
    return field.text
}

@Composable
fun FormPasswordTextField(title: String, placeholder: String, label: String): String {
    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    val icon = if (passwordVisibility)
        painterResource(id = android.R.drawable.ic_partial_secure)
    else
        painterResource(id = android.R.drawable.ic_secure)

    Text(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
        fontWeight = FontWeight.Bold,
        text = title
    )

    TextField(
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue,
            disabledIndicatorColor = Color.Blue
        ),
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
            .fillMaxWidth(),
        value = password,
        onValueChange = { password = it },
        shape = RoundedCornerShape(7.dp),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(painter = icon, contentDescription = "")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    )
    return password.text
}

@Composable
fun FormNumberTextField(title: String, placeholder: String, label: String): String {
    var field by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Text(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
        fontWeight = FontWeight.Bold,
        text = title
    )

    TextField(
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue,
            disabledIndicatorColor = Color.Blue
        ),
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
            .fillMaxWidth(),
        value = field,
        shape = RoundedCornerShape(7.dp),
        onValueChange = { field = it },
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
    return field.text
}

@Composable
fun FormStringTextField(title: String, placeholder: String, label: String): String {

    var field by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Text(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
        fontWeight = FontWeight.Bold,
        text = title
    )

    TextField(colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Blue,
        unfocusedIndicatorColor = Color.Blue,
        disabledIndicatorColor = Color.Blue
    ),
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
            .fillMaxWidth(),
        value = field,
        shape = RoundedCornerShape(7.dp),
        onValueChange = {
            field = it
        },
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) }
    )
    return field.text
}
