package ng.joseph.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class CheatViewModel(private val state: SavedStateHandle): ViewModel() {
    var isCheater = false

}