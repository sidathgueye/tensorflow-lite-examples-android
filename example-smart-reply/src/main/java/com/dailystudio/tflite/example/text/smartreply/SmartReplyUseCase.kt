package com.dailystudio.tflite.example.text.smartreply

import android.content.Context
import com.dailystudio.tensorflow.litex.InferenceInfo
import org.tensorflow.lite.examples.smartreply.SmartReply
import org.tensorflow.lite.examples.smartreply.SmartReplyClient
import org.tensorflow.lite.support.model.Model
import com.dailystudio.tensorflow.litex.LiteModel
import com.dailystudio.tensorflow.litex.LiteUseCase
import com.dailystudio.tensorflow.litex.ui.InferenceSettingsPrefs

class SmartReplyUseCase: LiteUseCase<String, Array<SmartReply>, InferenceInfo>() {

    companion object {
        const val UC_NAME = "smartreply"
    }

    override fun createModels(
        context: Context,
        device: Model.Device,
        numOfThreads: Int,
        useXNNPack: Boolean,
        settings: InferenceSettingsPrefs
    ): Array<LiteModel> {
        return arrayOf(
            SmartReplyClient(context)
        )
    }

    override fun createInferenceInfo(): InferenceInfo {
        return InferenceInfo()
    }

    override fun runInference(input: String, info: InferenceInfo): Array<SmartReply>? {
        return (defaultModel as? SmartReplyClient)?.predict(arrayOf(input))
    }
}