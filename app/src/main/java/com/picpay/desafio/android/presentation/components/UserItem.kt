package com.picpay.desafio.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.User

@Composable
fun UserItem(user: User) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(
                        data = user.img,
                        builder = {
                            crossfade(true)
                            error(R.drawable.ic_round_account_circle)
                            fallback(R.drawable.ic_round_account_circle)
                        }),
                    contentDescription = user.username,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(100.dp)),
                    alignment = Alignment.Center
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(
                    text = user.username.trimStart(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(horizontal = 4.dp),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = user.name.trimStart(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(horizontal = 4.dp),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}