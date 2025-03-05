package fr.eni.ecole.eni_shop.ui.common


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EniShopTopBar(){
    TopAppBar(title = { EniShopTopBarTitle() } )
}

@Composable
fun EniShopTopBarTitle(modifier : Modifier = Modifier){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "a shopping cart",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "ENI-SHOP",
            color = Color(0xFF8E536D),
            fontSize = 40.sp,
        )
    }

}