package com.example.pixabay_adanian

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton

class MainScreen() : Screen<MainScreen>() {
    val mainRecycler = KView{withId(R.id.mainRecyclerView)}
    val searchVw = KView{withId(R.id.searchView)}
}