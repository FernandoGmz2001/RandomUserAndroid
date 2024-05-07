package com.example.randomuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.randomuser.data.RetrofitServiceFactory
import com.example.randomuser.data.model.RandomUserResult
import com.example.randomuser.data.model.Result
import com.example.randomuser.ui.theme.RandomUserTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val userResult = service.getRandomUser()
            val user = userResult.results[0]

            setContent {
                RandomUserTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        LazyColumn {
                            item {
                                UserCard(user)
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                Text(text = "Numero de control : 19100196", fontSize = 20.sp)
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RandomUserTheme {
        Greeting("Android")
    }
}

@Composable
fun UserCard(user: Result) {
    Card(modifier = Modifier.padding(16.dp)) {
        Column {
            Text(text = "Name: ${user.name.title} ${user.name.first} ${user.name.last}")
            Text(text = "Email: ${user.email}")
            Text(text = "Username: ${user.login.username}")
            Text(text = "Date of Birth: ${user.dob.date}")
            Text(text = "Phone: ${user.phone}")
            Text(text = "Cell: ${user.cell}")
            val imagePainter = rememberImagePainter(data = user.picture.medium)
            Image(
                painter = imagePainter,
                contentDescription = "User picture",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}