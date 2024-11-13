import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomTopAppBar(
    title: String,
    onNavigationClick: () -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }
        }
    )
}