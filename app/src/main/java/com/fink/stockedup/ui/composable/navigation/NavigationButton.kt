import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavigationButton(
		text: String = "Nav Button",
		selected: Boolean = false,
		onClick: () -> Unit = { }
) {
		Button(
				onClick = onClick,
				colors = ButtonDefaults.buttonColors(
						containerColor = if (selected) Color(0xFF0366D6) else Color(0xCC282C34)
				),
				shape = RoundedCornerShape(12.dp),
				modifier = Modifier
						.fillMaxWidth()
						.height(56.dp)
						.padding(horizontal = 16.dp, vertical = 8.dp)
						.then(if (selected) Modifier.shadow(8.dp, RoundedCornerShape(12.dp)) else Modifier)
		) {
				Text(
						text,
						style = MaterialTheme.typography.bodyLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
						color = if (selected) Color.White else Color.LightGray
				)
		}
}



// ✅ **Preview for Unselected State (False)**
@Preview(name = "Samsung S Ultra - Unselected", device = "spec:width=1440dp,height=3200dp,dpi=560", showBackground = true)
@Preview(name = "Samsung S Phone - Unselected", device = "spec:width=1080dp,height=2340dp,dpi=440", showBackground = true)
@Preview(name = "Samsung A Phone - Unselected", device = "spec:width=1080dp,height=2400dp,dpi=420", showBackground = true)
@Preview(name = "Samsung X Phone - Unselected", device = "spec:width=1080dp,height=2340dp,dpi=400", showBackground = true)
@Preview(name = "Samsung S Tablet - Unselected", device = "spec:width=2560dp,height=1600dp,dpi=320", showBackground = true)
@Preview(name = "Samsung Active Tablet - Unselected", device = "spec:width=1920dp,height=1200dp,dpi=280", showBackground = true)
@Composable
fun PreviewNavigationButton_Unselected() {
		NavigationButton(text = "Nav Button", selected = false)
}

// ✅ **Preview for Selected State (True)**
@Preview(name = "Samsung S Ultra - Selected", device = "spec:width=1440dp,height=3200dp,dpi=560", showBackground = true)
@Preview(name = "Samsung S Phone - Selected", device = "spec:width=1080dp,height=2340dp,dpi=440", showBackground = true)
@Preview(name = "Samsung A Phone - Selected", device = "spec:width=1080dp,height=2400dp,dpi=420", showBackground = true)
@Preview(name = "Samsung X Phone - Selected", device = "spec:width=1080dp,height=2340dp,dpi=400", showBackground = true)
@Preview(name = "Samsung S Tablet - Selected", device = "spec:width=2560dp,height=1600dp,dpi=320", showBackground = true)
@Preview(name = "Samsung Active Tablet - Selected", device = "spec:width=1920dp,height=1200dp,dpi=280", showBackground = true)
@Composable
fun PreviewNavigationButton_Selected() {
		NavigationButton(text = "Nav Button", selected = true)
}
