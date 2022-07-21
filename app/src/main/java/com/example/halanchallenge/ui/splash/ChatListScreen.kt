package com.example.halanchallenge.ui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.halanchallenge.R
import com.example.halanchallenge.ui.theme.DarkBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ChatItemView(
    modifier: Modifier,
    chat: ChatModel,
    onClick: (chatId: Int, chatName: String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(bottom = 10.dp)
            .clickable {
                onClick.invoke(1, chat.userData?.userName ?: chat.messageGroupData?.groupName ?: "")
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if (chat.messageGroupData != null) {
            Image(
                Icons.Default.Send,
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(30.dp)
            )
        } else {
            AsyncImage(
                model = chat.userData?.userProfilePicture
                    ?: "https://randomuser.me/api/portraits/lego/4.jpg",
                contentDescription = "userImage",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(30.dp)
                    .clip(CircleShape)
            )
        }
        Column(
            Modifier
                .padding(start = 10.dp, end = 10.dp)
                .weight(2F)
        ) {
            Text(
                text = if (chat.messageGroupData != null) chat.messageGroupData.groupName else chat.userData?.userName
                    ?: "",
                fontFamily = FontFamily.Cursive,
                fontWeight = if (chat.unseenCount > 0) FontWeight.Bold else FontWeight.Normal,
                fontSize = TextUnit(if (chat.unseenCount > 0) 16F else 14F, TextUnitType.Sp),
                color = Color.Black
            )

            Text(
                text = chat.lastMessageText ?: "",
                fontFamily = FontFamily.Cursive,
                fontWeight = if (chat.unseenCount > 0) FontWeight.Bold else FontWeight.Normal,
                fontSize = TextUnit(if (chat.unseenCount > 0) 16F else 14F, TextUnitType.Sp),
                color = Color.Black
            )
        }
        if (chat.unseenCount > 0) {
            Text(
                text = "${chat.unseenCount}",
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(20.dp)
                    .background(color = Color.Blue, shape = RoundedCornerShape(10.dp))
            )
        }
    }
}

data class IncomingMessage(
    var voteUp: Int? = 0,
    var voteDown: Int? = 0,
    var voteType: Int? = 0,
    val isLoading: Boolean? = false,
    val canPin: Boolean? = null,
    var commentsNumber: Int? = 0,
    val isPinned: Boolean? = null,
    val isSeen: Boolean? = null,
    val isMine: Boolean = true,
    val messageCollectionData: MessageCollectionData? = null,
    val messageContent: String? = null,
    val messageCreated: String? = null,
    val messageGroupData: MessageGroupData? = null,
    val messageId: Int = 0,
    val messageTitle: String? = null,
    val receiversCount: Int? = null,
    val messageUserData: MessageUserData? = null,
    val unSeenComments: Boolean? = null,
    var canVote: Boolean? = false,
    var canShowStatus: Boolean? = false
) {
    fun getLikeCountString() = if (voteUp == 0) "" else voteUp.toString()
    fun getDisLikeCountString() = if (voteDown == 0) "" else voteDown.toString()
    var isLikeLoading: Boolean = false


    data class MessageCollectionData(
        val abbreviation: Any? = null,
        val collectionArabicName: Any? = null,
        val collectionId: Any? = null,
        val collectionName: Any? = null
    )


    data class MessageGroupData(
        val abbreviation: String? = null,
        val groupArabicName: String? = null,
        val groupId: Int? = null,
        val groupName: String = ""
    )


    data class MessageUserData(

        val messageCreatorId: String? = null,
        val messageCreatorName: String? = null,
        val userName: String = "",
        val userProfilePicture: String? = null
    )
}


data class ChatModel(

    val lastMessageDate: String? = null, // 2021-01-30T17:45:43.39

    val messageGroupData: IncomingMessage.MessageGroupData? = null,

    var unseenCount: Int = 0, // 2

    val lastMessageText: String? = null,

    val userData: IncomingMessage.MessageUserData? = null // null
)

private fun loadChats() = arrayListOf(
    ChatModel(
        lastMessageText = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
        lastMessageDate = "2022-06-29T12:48:05.115",
        unseenCount = 15,
        messageGroupData = IncomingMessage.MessageGroupData(
            groupName = "Adidas",
            groupId = 58,
            abbreviation = "AGA"
        )
    ),
    ChatModel(
        lastMessageText = "♘\uD83C\uDF88  \uD835\uDCD0ⓢđ  αⓈ\uD835\uDCB6ⓢ\uD835\uDC1D  ൠ\uD83C\uDF53",
        lastMessageDate = "2022-06-28T12:02:58.14",
        unseenCount = 0,
        userData = IncomingMessage.MessageUserData(
            messageCreatorId = "abc-123-hij-123",
            messageCreatorName = "Ahmed Morse",
            userName = "Morse Junior 1",
            userProfilePicture = "https://randomuser.me/api/portraits/men/49.jpg"
        )
    ),
    ChatModel(
        lastMessageText = "Hello there. Thanks for the follow. Did you notice, that I am an egg? A talking egg? Damn!",
        lastMessageDate = "2022-06-22T11:29:31.22",
        unseenCount = 2,
        userData = IncomingMessage.MessageUserData(
            messageCreatorId = "abc-def-123-123",
            messageCreatorName = "Esraa Morse",
            userName = "Morse Junior 2",
            userProfilePicture = "https://randomuser.me/api/portraits/women/19.jpg"
        )
    ),
    ChatModel(
        lastMessageText = "ios text messenger right\n" +
                "Yeah that is crazy, but people can change their own picture and build their own Twitter conversation with this generator, so it does not matter that you are an egg.",
        lastMessageDate = "2022-06-14T16:05:01.98",
        unseenCount = 1,
        userData = IncomingMessage.MessageUserData(
            messageCreatorId = "abc-def-789-123",
            messageCreatorName = "Mohammed Morse",
            userName = "Morse Junior 3",
            userProfilePicture = "https://randomuser.me/api/portraits/men/29.jpg"
        )
    ),
    ChatModel(
        lastMessageText = "Thanks mate! Feel way better now. Oh, and guys, these messages will be removed once your add your own :-)",
        lastMessageDate = "2022-06-14T13:27:13.373",
        unseenCount = 0,
        userData = IncomingMessage.MessageUserData(
            messageCreatorId = "abc-456-hij-123",
            messageCreatorName = "Salma Morse",
            userName = "Morse Junior 4",
            userProfilePicture = "https://randomuser.me/api/portraits/women/49.jpg"

        )
    ),
    ChatModel(
        lastMessageText = "If satisfied, download the chat and share with all your close friends and relatives, and note their reactions.",
        lastMessageDate = "2022-06-14T09:51:54.007",
        unseenCount = 21,
        userData = IncomingMessage.MessageUserData(
            messageCreatorId = "123-def-hij-123",
            messageCreatorName = "Ahmed Morse",
            userName = "Morse Junior 5",
            userProfilePicture = "https://randomuser.me/api/portraits/men/39.jpg"

        )
    )
)

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ConnectChatList(navHostController: NavHostController) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(
            title = { Text(text = "Connect", fontFamily = FontFamily.Cursive) },
            navigationIcon = {
                Icon(
                  Icons.Default.Send /* rememberAsyncImagePainter("https://randomuser.me/api/portraits/men/42.jpg")*/,
                    contentDescription = null
                )
            },
            contentColor = Color.Red,
            actions = {
                IconButton(onClick = { coroutineScope.launch { scrollState.scrollToItem(0) } }) {
                    Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                }

                IconButton(onClick = { coroutineScope.launch { scrollState.scrollToItem(0) } }) {
                    Icon(Icons.Default.Settings, contentDescription = null, tint = Color.White)
                }
            })
    }) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            val (messages, compose) = createRefs()
            val chats = loadChats()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .constrainAs(messages) {

                    }, state = scrollState
            ) {
                items(chats) {
                    ChatItemView(
                        modifier = Modifier
                            .clickable { }
                            .fillMaxWidth(),
                        chat = it
                    ) { chatId, chatName ->
                        navHostController.navigate(route = "Conversation/$chatId/$chatName")
                    }
                }
            }
            ExtendedFloatingActionButton(
                text = {
                    Text(text = "Compose", fontFamily = FontFamily.Cursive, color = Color.White)
                },
                modifier = Modifier.constrainAs(compose) {
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end, 10.dp)
                },
                backgroundColor = Color(0XF8, 0X7F, 0X0F),
                icon = {
                    Icon(
                        Icons.Default.Send,
                        contentDescription = "Choose Contact",
                        tint = Color.White
                    )
                },
                onClick = { /*TODO*/ })
        }

    }
}