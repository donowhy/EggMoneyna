package com.shbhack.eggmoneyna.ui.comment

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shbhack.eggmoneyna.R
import com.shbhack.eggmoneyna.data.local.AppPreferences
import com.shbhack.eggmoneyna.data.model.WriteCommentRequest
import com.shbhack.eggmoneyna.ui.comment.viewmodel.CommentViewModel
import com.shbhack.eggmoneyna.ui.common.top.TopWithBack
import com.shbhack.eggmoneyna.ui.theme.keyColorLight1
import com.shbhack.eggmoneyna.util.DateUtils
import com.shbhack.eggmoneyna.util.MoneyUtils
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

private const val TAG = "CommentScreen_진영"

@SuppressLint("NewApi")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentScreen(navController: NavController, viewModel: CommentViewModel) {
    val selectedItem by viewModel.selectedItem.collectAsState()
    val commentValue by viewModel.commentState.collectAsState()
    var isCommented by remember {
        mutableStateOf(false)
    }
    val localContext = LocalContext.current
    LaunchedEffect(selectedItem, commentValue) {
        // selectedItem이 null이 아닐 때만 로직 실행
        selectedItem?.let {
            if (AppPreferences.isParent()) {
                isCommented = commentValue.parentComment.isNotEmpty()
                val childId = AppPreferences.getChildId() ?: ""
                viewModel.getComment(childId, it.id.toString())
            } else {
                isCommented = commentValue.childComment.isNotEmpty()
                viewModel.getComment(it.id.toString())
            }

            Log.d(TAG, "CommentScreen: $commentValue")
        }

    }


    Scaffold(
        topBar = {
            TopWithBack(
                navController = navController,
                title = stringResource(id = R.string.comment_appbar_title)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        )
        {
            Column(
                modifier = Modifier
            ) {
                selectedItem?.let { item ->
                    var value = MoneyUtils.formatCurrency(input = item.input, output = item.output)
                    if (value != null) {
                        CommentBanner(
                            title = item.brandName,
                            content = value,
                            item.brandImg
                        ) {
                            // 칭찬하기 클릭
                            Toast.makeText(localContext, "자녀를 칭찬하였습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.sdp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CommentDateCategory("일시", DateUtils.formatDateString(selectedItem!!.updateTime))
                    Spacer(modifier = Modifier.size(12.sdp))
                    CommentDateCategory("카테고리", selectedItem!!.smallCategory)
                }


                Divider(
                    thickness = 12.sdp,
                    color = keyColorLight1
                )

                Text(
                    modifier = Modifier.padding(12.sdp),
                    text = "지출 코멘트",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )

                Divider(
                    modifier = Modifier.padding(start = 12.sdp, end = 12.sdp),
                    thickness = 1.sdp
                )
                Spacer(modifier = Modifier.size(12.sdp))
                // 아이의 댓글 여부에 따라서 나타내기
                if (commentValue.childComment.isNotEmpty()) {
                    CommentItemComponent(
                        R.drawable.icon_profile,
                        commentValue.childNickname,
                        commentValue.childComment,
                        DateUtils.dateToString(commentValue.childCommentCreateTime)
                    )
                }
                // 부모의 댓글 여부에 따라서 나타내기
                if (commentValue.parentComment.isNotEmpty()) {
                    CommentItemComponent(
                        R.drawable.icon_profile,
                        commentValue.parentNickname,
                        commentValue.parentComment,
                        DateUtils.dateToString(commentValue.parentCommentCreateTime)
                    )
                }
            }
            // textField
            var text by remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current
            val handleSendAction = {
                keyboardController?.hide()
                if (AppPreferences.isParent()) {
                    AppPreferences.getChildId()
                        ?.let { id ->
                            viewModel.writeComment(
                                id,
                                selectedItem?.id.toString(),
                                WriteCommentRequest(text)
                            )
                        }
                } else {
                    viewModel.writeComment(
                        selectedItem?.id.toString(),
                        WriteCommentRequest(text)
                    )
                }
                text = ""
            }
            TextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.Normal
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    // 보내기 버튼
                    handleSendAction()
                }),
                trailingIcon = {
                    IconButton(onClick = {
                        // 보내기 버튼
                        handleSendAction()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_send),
                            contentDescription = "Trailing icon",
                            modifier = Modifier.fillMaxSize(0.7f)
                        )
                    }
                },
                placeholder = {
                    Text(
                        text = if (isCommented) "오늘은 이미 코멘트를 남겼어요" else "코멘트를 입력하세요",
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                },
                enabled = !isCommented,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.sdp)
                    .clip(RoundedCornerShape(12.sdp))
                    .border(1.sdp, Color.LightGray, RoundedCornerShape(12.sdp))
                    .align(Alignment.BottomCenter),
                shape = ShapeDefaults.Medium,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

//@Preview
//@Composable
//fun CommentScreenPreview() {
//    CommentScreen(navController = rememberNavController(), CommentViewModel())
//}