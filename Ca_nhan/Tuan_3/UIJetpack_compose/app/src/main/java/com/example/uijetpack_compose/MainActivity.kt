package com.example.uijetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uijetpack_compose.ui.theme.JetpackUITheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackUITheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "man_hinh_1") {
                    composable("man_hinh_1") { ManHinh1(navController) }
                    composable("man_hinh_2") { ManHinh2(navController) }
                    composable("man_hinh_3") { ManHinh3(navController) }
                    composable("man_hinh_image") { ManHinhImage(navController) }
                    composable("man_hinh_textfield") { ManHinhTextField(navController) }
                    composable("man_hinh_rowlayout") { ManHinhRowLayout(navController) }
                    composable("man_hinh_columnlayout") { ManHinhColumnLayout(navController) }
                }
            }
        }
    }
}

@Composable
fun ManHinh1(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Góc trên bên phải
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Lê Viết Thiện",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "040205029653",
                fontSize = 14.sp,
                color = Color.Black
            )
        }

        // Phần giữa
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.compose_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Jetpack Compose",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        // Nút dưới cùng
        Button(
            onClick = { navController.navigate("man_hinh_2") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008CFF))
        ) {
            Text(text = "I’m ready", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun ManHinh2(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "UI Components List",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF008CFF),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        // --- Display ---
        Text("Display", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        UIItem("Text", "Displays text") { navController.navigate("man_hinh_3") }
        UIItem("Image", "Displays an image") { navController.navigate("man_hinh_image") }

        // --- Input ---
        Text(
            text = "Input",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 12.dp)
        )
        UIItem("TextField", "Input field for text") { navController.navigate("man_hinh_textfield") }
        UIItem("PasswordField", "Input field for passwords")

        // --- Layout ---
        Text(
            text = "Layout",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 12.dp)
        )
        UIItem("Column", "Arranges elements vertically") { navController.navigate("man_hinh_columnlayout") }
        UIItem("Row", "Arranges elements horizontally") { navController.navigate("man_hinh_rowlayout") }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút tự tìm hiểu
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB3B3)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Tự tìm hiểu", fontWeight = FontWeight.Bold, color = Color.Black)
                Text(
                    text = "Tìm ra tất cả các thành phần UI Cơ bản",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun UIItem(title: String, description: String, onClick: (() -> Unit)? = null) {
    Button(
        onClick = onClick ?: {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = description, fontSize = 12.sp, color = Color.Black)
        }
    }
}


@Composable
fun ManHinh3(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Thanh tiêu đề trên cùng
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color(0xFF008CFF),
                    modifier = Modifier.size(28.dp) // icon mảnh nhẹ giống iOS
                )
            }

            Text(
                text = "Text Detail",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF008CFF),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 48.dp),
                textAlign = TextAlign.Center
            )
        }

        // Đường viền mảnh phía dưới thanh tiêu đề (giống iOS)
        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

        // Nội dung chính
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                buildAnnotatedString {
                    append("The ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick ")
                    }
                    withStyle(SpanStyle(color = Color(0xFFB36B00), fontWeight = FontWeight.Bold)) {
                        append("Brown\n")
                    }
                    append("fox ")
                    withStyle(SpanStyle(letterSpacing = 10.sp)) {
                        append("jumps ")
                    }
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over ")
                    }
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the ")
                    }
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("lazy ")
                    }
                    append("dog.")
                },
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }
    }
}
@Composable
fun ManHinhImage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // 🔹 Thanh tiêu đề trên cùng có nút Back
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color(0xFF008CFF),
                    modifier = Modifier.size(28.dp) // icon mảnh giống iOS
                )
            }

            Text(
                text = "Images",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF008CFF),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 48.dp),
                textAlign = TextAlign.Center
            )
        }

        // 🔸 Đường kẻ mảnh phía dưới tiêu đề
        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

        // 🖼 First image uth1.jpg
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.uth1),
                contentDescription = "uth1",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "uth1.jpg",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        // 🖼 Second image uth2.jpg
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.uth2),
                contentDescription = "uth2",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "uth2.jpg",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun ManHinhTextField(navController: NavHostController) {
    // ✅ tạo state
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color(0xFF008CFF),
                    modifier = Modifier.size(28.dp)
                )
            }
            Text(
                text = "TextField",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF008CFF),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 48.dp),
                textAlign = TextAlign.Center
            )
        }

        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it }, // ✅ cập nhật state khi nhập
                modifier = Modifier.fillMaxWidth(0.8f),
                label = { Text("Nhập nội dung") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (text.isEmpty()) "Tự động cập nhật dữ liệu theo textfield" else text,
                color = Color.Red,
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun ManHinhRowLayout(navController: NavHostController) {
    val lightBlue = Color(0xFFBBDEFB)
    val darkBlue = Color(0xFF42A5F5)
    val layoutData = listOf(
        listOf(lightBlue, darkBlue, lightBlue),
        listOf(lightBlue, darkBlue, lightBlue),
        listOf(lightBlue, darkBlue, lightBlue),
        listOf(lightBlue, darkBlue, lightBlue)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
            Text(
                text = "Row Layout",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 28.dp),
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            layoutData.forEachIndexed { rowIndex, rowColors ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowColors.forEach { boxColor ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(60.dp)
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(boxColor)
                        ) {}
                    }
                }
                if (rowIndex < layoutData.size - 1) {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun ManHinhColumnLayout(navController: NavHostController) {

    // Define the colors used in the image
    val lightGreen = Color(0xFFC8E6C9) // Faintest green (top and bottom card)
    val mediumGreen = Color(0xFFA5D6A7) // Medium green (middle card)
    val appBlue = Color(0xFF008CFF) // Blue tint for the icon and title

    // List of colors for the three boxes to match the image
    val boxColors = listOf(
        lightGreen,
        mediumGreen,
        lightGreen
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // --- Top Bar ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // Standard AppBar height
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    // The back button looks black/dark gray in the image, not blue
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
            // Title Text - Centered
            Text(
                text = "Colum Layout",
                fontSize = 17.sp, // Looks slightly smaller than 20.sp
                fontWeight = FontWeight.SemiBold, // Matches the image's title weight
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    // The original code had padding to offset the back button;
                    // this removes it to center the text better
                    .padding(end = 28.dp),
                textAlign = TextAlign.Center
            )
        }

        // The original image has a 'Components Detail' title *above* the top bar,
        // which suggests it's a detail screen nested in a navigator.
        // I will add a placeholder for that, but keep the 'Colum Layout' as the screen title.

        // --- Content Area ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                // A bit less top padding to bring the content up, as in the image
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            boxColors.forEachIndexed { index, color ->
                // The cards have a subtle white space between them, and also around the group.
                // The main content area in the image looks like a white card itself.

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f) // Slightly wider to match the image ratio
                        .height(80.dp) // Taller to match the image
                        .clip(RoundedCornerShape(12.dp))
                        .background(color) // Use the specific color for this box
                ) {}

                // Add a Spacer after each box, but not the last one
                if (index < boxColors.size - 1) {
                    Spacer(modifier = Modifier.height(12.dp)) // Reduced spacing
                }
            }
        }
    }
}