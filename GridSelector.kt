import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <T> GridSelector(
    columnCount: Int = 2,
    list: List<T>,
    itemModifier: Modifier = Modifier,
    callback: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(Modifier.verticalScroll(scrollState)) {
        val itemCount = list.size
        val rows = (itemCount / columnCount) + (if (itemCount % columnCount == 0) 0 else 1)
        Column {
            var createdItems = 0
            for (i in 0 until rows) {
                Row(
                    Modifier.fillMaxWidth(
                        if (i == rows - 1) {
                            ((itemCount - createdItems).toFloat() / columnCount.toFloat())
                        } else 1f
                    )
                ) {
                    for (ii in 0 until columnCount) {
                        if (createdItems + 1 <= itemCount) {
                            GridItem(
                                modifier = itemModifier.weight(1f),
                                itemNumber = createdItems,
                                onValueChange = { callback(it) },
                                dataModel = list[createdItems]
                            )
                            createdItems += 1
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun <T> GridItem(
    modifier: Modifier,
    itemNumber: Int,
    dataModel: T,
    onValueChange: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onValueChange(itemNumber) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        /** implement your dataModel **/
        Text(text = itemNumber.toString())
    }
}

/** Example Code
 // =============
@Preview
@Composable
fun GridSelectorPreview() {
    YourAppTheme {
        var lastClicked by remember {
            mutableStateOf(-1)
        }
        Column(Modifier.background(Color.LightGray)) {
            Text(text = lastClicked.toString())
            GridSelector(
                columnCount = 2,
                list = listOf(1, 2, 3),
                callback = { lastClicked = it }
            )
        }
    }
}
 **/

