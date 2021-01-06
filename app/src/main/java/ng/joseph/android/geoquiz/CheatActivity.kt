package ng.joseph.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels

const val EXTRA_ANSWER_SHOWN = "ng.joseph.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "ng.joseph.android.geoquiz.answer_is_true"
private const val KEY_ANSWER_SHOWN = "answerShown"
private const val TAG = "CheatActivity"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private val cheatViewModel: CheatViewModel by viewModels()

    private var isAnswerShown = false
    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        isAnswerShown = cheatViewModel.isCheater
       /* isAnswerShown = savedInstanceState?.getBoolean(KEY_ANSWER_SHOWN,false) ?: false
        cheatViewModel.isCheater = isAnswerShown*/
        Log.d("CheatActivity ", "isAnswerShown is ${savedInstanceState?.getBoolean(KEY_ANSWER_SHOWN, false)} in onCreate Bundle")

        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            isAnswerShown = true
            cheatViewModel.isCheater = isAnswerShown
            Log.d(TAG, "OnClickListener ${isAnswerShown}")
            cheatViewModel.isCheater = isAnswerShown
            setAnswerShownResult(true)
            Log.d(TAG, "isAnswerShown is ${isAnswerShown} in showAnswerButton Click")
        }
        Log.d(TAG, "onCreate: quizViewModel.isCheater is ${cheatViewModel.isCheater}")

        setAnswerShownResult(cheatViewModel.isCheater)
    }

    override fun onSaveInstanceState(outstate: Bundle) {
        outstate.putBoolean(KEY_ANSWER_SHOWN, isAnswerShown)

        super.onSaveInstanceState(outstate)

    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply{
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }



    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}