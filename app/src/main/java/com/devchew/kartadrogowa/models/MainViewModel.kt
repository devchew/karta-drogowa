package com.devchew.kartadrogowa.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devchew.kartadrogowa.database.Card
import com.devchew.kartadrogowa.database.CardDao
import com.devchew.kartadrogowa.database.Panel
import com.devchew.kartadrogowa.database.PanelDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val cardDao: CardDao,
    private val panelDao: PanelDao,
): ViewModel() {
    private var _card = MutableStateFlow(Card(""))
    val card = _card.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Card(""))

    private var _cardList = MutableStateFlow(emptyList<Card>())
    val cardList = _cardList.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private var _panels = MutableStateFlow(emptyList<PanelModel>())
    val panels = _panels.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun loadCard(id: Int) {
        viewModelScope.launch {
            _card.value = cardDao.getById(id)
            _panels.value = panelDao.getPanels(id).map {
                val model = PanelModel(it)
                model.changeCallback = { panel ->
                    viewModelScope.launch {
                        panelDao.upsert(panel)
                    }
                }
                model
            }
        }
    }

    fun onAddPanel(panel: Panel, callback: (Int) -> Unit) {
        viewModelScope.launch {
            val newPanel = panelDao.upsert(panel)
            callback(newPanel.toInt())
        }
    }

    fun createCard(card: Card, callback: (Int) -> Unit) {
        viewModelScope.launch {
            val newCard = cardDao.upsert(card)
            callback(newCard.toInt())
        }
    }
    fun updateCards() {
        viewModelScope.launch {
            _cardList.value = cardDao.getAll()
        }
    }
}