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

    //mock Timer, now + 1min
    private val _closestTimer = MutableStateFlow(getCurrentTime().toMs() + 70000L)
    val closestTimer = _closestTimer.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0)

    fun loadCard(id: Int) {
        viewModelScope.launch {
            _card.value = cardDao.getById(id)
            _panels.value = panelDao.getPanels(id).map {
                val model = PanelModel(it)
                model.changeCallback = { panel ->
                    viewModelScope.launch {
                        panelDao.upsert(panel)
                    }
                    findClosestTimer()
                }
                model
            }
        }
    }

    fun findClosestTimer() {
        viewModelScope.launch {
            val currentTime = getCurrentTime().toMs()
            val closest = panels.value.map { it.estimatedTime.value.toMs() }
                .filter { it > currentTime }
                .minOrNull()
            _closestTimer.value = closest ?: 0
        }
    }

    fun onAddPanel(panel: Panel, callback: (Int) -> Unit) {
        viewModelScope.launch {
            val newPanel = panelDao.upsert(panel)
            callback(newPanel.toInt())
        }
    }

    fun removePanel(panelId: Int, callback: () -> Unit) {
        viewModelScope.launch {
            panelDao.delete(panelId)
            callback()
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

    fun removeCard(cardId: Int, callback: () -> Unit) {
        viewModelScope.launch {
            cardDao.delete(cardId)
            panelDao.deleteByCardId(cardId)
            callback()
        }
    }
}