package com.dailystudio.tflite.example.image.classification

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.dailystudio.devbricksx.development.Logger
import com.dailystudio.tflite.example.common.AbsExampleActivity
import com.dailystudio.tflite.example.common.AbsExampleFragment
import com.dailystudio.tflite.example.image.classification.fragment.ImageClassificationFragment
import org.tensorflow.lite.examples.classification.tflite.Classifier
import kotlin.math.min

class ExampleActivity : AbsExampleActivity<List<Classifier.Recognition>>() {

    companion object {
        const val REPRESENTED_ITEMS_COUNT = 3
    }

    private var detectItemViews: Array<TextView?> =
        Array(REPRESENTED_ITEMS_COUNT) {null}
    private var detectItemValueViews: Array<TextView?> =
        Array(REPRESENTED_ITEMS_COUNT) {null}

    override fun createExampleFragment(): AbsExampleFragment<List<Classifier.Recognition>> {
        return ImageClassificationFragment()
    }

    override fun createResultsView(): View? {
        val resultsView = LayoutInflater.from(this).inflate(
            R.layout.layout_results, null)

        resultsView?.let {
            setupResultView(it)
        }

        return resultsView
    }

    private fun setupResultView(resultsView: View) {
        for (i in 0 until REPRESENTED_ITEMS_COUNT) {
            detectItemViews[i] = resultsView.findViewById(
                resources.getIdentifier("detected_item${i + 1}", "id", packageName)
            )

            detectItemValueViews[i] = resultsView.findViewById(
                resources.getIdentifier("detected_item${i + 1}_value", "id", packageName)
            )

            Logger.debug("detectItemViews[${i + 1}] = ${detectItemViews[i]}")
            Logger.debug("detectItemValueViews[${i + 1}] = ${detectItemValueViews[i]}")

        }
    }

    override fun onResultsUpdated(resultView: View, result: List<Classifier.Recognition>) {
        val itemCount = min(result.size, REPRESENTED_ITEMS_COUNT)

        for (i in 0 until itemCount) {
            detectItemViews[i]?.text = result[i].title
            detectItemValueViews[i]?.text = result[i].confidence.toString()
        }
    }

}
