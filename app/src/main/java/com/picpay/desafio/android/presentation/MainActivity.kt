package com.picpay.desafio.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.components.UserItem
import com.picpay.desafio.android.presentation.ui.theme.DesafioandroidTheme
import com.picpay.desafio.android.presentation.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            val state = viewModel.state.value
            val scaffoldState = rememberScaffoldState()
            DesafioandroidTheme {
                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when (event) {
                            is UiEvent.ShowSnackBar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
                        if (state.isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center),
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colors.onPrimary
                                )
                            }
                        } else {
                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.title),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(state.users.size) { i ->
                                        val user = state.users[i]
                                        if (i > 0) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                        UserItem(user)
                                        if (i < state.users.size - 1){
                                            Divider()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DesafioandroidTheme {
        UserItem(
            user = User(
                "",
                "Arthur D",
                1,
                "arthur_damous"
            )
        )
    }
}