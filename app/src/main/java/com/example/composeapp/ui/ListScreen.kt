package com.example.composeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.composeapp.R
import com.example.composeapp.Utils.CommonMethods.getColorByStatus
import com.example.composeapp.data.model.Characters
import com.example.composeapp.extension.pagingLoadStateItem
import com.example.composeapp.viewmodel.CharacterViewModel

@Composable
fun CharacterList(modifier: Modifier= Modifier){
    val viewModel = hiltViewModel<CharacterViewModel>()
    val response = viewModel.dataState.collectAsLazyPagingItems()

    when(response.loadState.refresh){
        is LoadState.Error -> {
            CommonContainer(
                modifier = Modifier.fillMaxSize()){
                Text(text = (response.loadState.refresh as LoadState.Error).error.message ?: "Something went wrong")
            }
        }
        LoadState.Loading -> {
            CommonContainer(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        is LoadState.NotLoading -> {
            LazyColumn {
                pagingLoadStateItem(
                    loadState = response.loadState.prepend,
                    keySuffix = "prepend",
                    loading = {
                              CommonContainer(
                                  modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(top = 4.dp, bottom = 16.dp)) {
                                  CircularProgressIndicator()
                              }
                    },
                    error = {
                        CommonContainer(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Error in paging load")
                        }
                    }
                )
                items(response.itemCount){ item ->
                    CharacterItem(modifier = modifier, response[item])
                }
                pagingLoadStateItem(
                    loadState = response.loadState.append,
                    keySuffix = "append",
                    loading = {
                        CommonContainer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, bottom = 16.dp)) {
                            CircularProgressIndicator()
                        }
                    },
                    error = {
                        CommonContainer(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Error in paging load")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CharacterItem(modifier: Modifier = Modifier, item: Characters.Result? = null){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF3C3E44),
            contentColor = Color.Transparent
        )
    ) {
        Row {
            AsyncImage(
                item?.image,
                contentDescription = "Character Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 125.dp, height = 125.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.error))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)) {
                Text(
                    text = item?.name?:"",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color = getColorByStatus(item?.status)))
                    Text(
                        text = item?.status.plus(" - ").plus(item?.species?:""),
                        fontSize = 12.sp, color = Color.White,
                        modifier = Modifier.padding(start = 4.dp))
                }
                ItemCommonInfo("Last known Location:",item?.location?.name?:"")
//                ItemCommonInfo("First seen in:",item?.episode?.getOrNull(0)?:"Unknown")
            }
        }
    }
}

@Composable
fun ItemCommonInfo(title:String = "Last known Location:",data:String="Earth"){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp)) {
        Text(text = title, color = Color.LightGray, fontSize = 12.sp)
        Text(text = data, color = Color.White, fontSize = 14.sp)
    }
}
@Composable
fun CommonContainer(modifier: Modifier,
                      contentAlignment:Alignment = Alignment.Center,
                      composable:(@Composable BoxScope.() -> Unit)){
    Box(modifier = modifier,contentAlignment = contentAlignment){
        composable.invoke(this)
    }
}

@Preview(showBackground = true, name = "CharacterList")
@Composable
fun CharacterListPreview(){
    CharacterList()
}

@Preview(showBackground = true, name = "CharacterItem")
@Composable
fun CharacterItemPreview(){
    CharacterItem()
}

@Preview(showBackground = true, name = "ItemCommonInfo")
@Composable
fun ItemCommonInfoPreview(){
    ItemCommonInfo()
}