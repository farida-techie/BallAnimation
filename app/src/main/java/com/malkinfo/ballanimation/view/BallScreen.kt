package com.malkinfo.ballanimation.view


import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malkinfo.ballanimation.R
import com.malkinfo.ballanimation.ui.theme.BlackColor


enum class BallPosition{
    Start,Finish
}

@Preview
@Composable
fun PreviewBallAnimation(){
  Surface(
      modifier = Modifier.fillMaxSize(),
      color = BlackColor
  ) {
       BallAnimation()
    }
}

@Composable
fun BallAnimation() {
    var ballState by remember { mutableStateOf(BallPosition.Start)}
    val infiniteTransition = rememberInfiniteTransition()
    val dy by infiniteTransition.animateFloat(
        initialValue =0f ,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000,easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val travelDistance = with(LocalDensity.current){320.dp.toPx()}
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) 
    {
        Image(painter = painterResource(id = R.drawable.ball) ,
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .graphicsLayer {
                    if (ballState == BallPosition.Finish) {
                        translationY = dy * travelDistance
                    }
                }
        )
        Button(onClick = {
            ballState = when(ballState){
                BallPosition.Start -> BallPosition.Finish
                BallPosition.Finish -> BallPosition.Start
            }
        },
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(
                    align = Alignment.Center
                )
        )
        {
            Text(text = "Jumping BAll")
        }
        
    }
    

}
