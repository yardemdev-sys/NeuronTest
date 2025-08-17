package com.example.neurontest.ui.screens.purchases

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.neurontest.R
import com.example.neurontest.ui.components.ReturnButton
import com.example.neurontest.ui.screens.purchases.components.SectionsList
import com.example.neurontest.ui.screens.purchases.models.PurchasesEffect
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun Purchases(
    onBack: () -> Unit,
    vm: PurchasesViewModel = koinViewModel()
) {
    val state by vm.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.uiEffect.collect { effect ->
            when (effect) {
                PurchasesEffect.NavigateBack -> onBack()
                PurchasesEffect.ShowLoadingError -> {
                    Toast.makeText(
                        context,
                        R.string.load_error,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ReturnButton { onBack() }

        if (state.isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            SectionsList(state.sections)
        }
    }
}
