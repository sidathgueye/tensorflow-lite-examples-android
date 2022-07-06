package com.dailystudio.tflite.example.reinforcementlearning

import android.content.Context
import com.dailystudio.tflite.example.common.AbsTFLiteModelRunner
import com.dailystudio.tflite.example.common.InferenceInfo
import com.dailystudio.tflite.example.common.ui.InferenceSettingsPrefs
import org.tensorflow.lite.examples.reinforcementlearning.*
import org.tensorflow.lite.support.model.Model
import org.tensorflow.litex.LiteModel
import org.tensorflow.litex.LiteUseCase

class ReinforcementLearningUseCase
    : LiteUseCase<Array<Array<BoardCellStatus>>, Int, InferenceInfo>() {

    companion object {
        const val UC_NAME = "reinforcement"
    }

    override fun createInferenceInfo(): InferenceInfo {
        return InferenceInfo()
    }

    override fun createModels(
        context: Context,
        device: Model.Device,
        numOfThreads: Int,
        useXNNPack: Boolean,
        settings: InferenceSettingsPrefs
    ): Array<LiteModel> {
        return arrayOf(
            if (Constants.USE_MODEL_FROM_TF) {
                RLAgent(context, device, numOfThreads, useXNNPack)
            } else {
                RLAgentFromTFAgents(context, device, numOfThreads, useXNNPack)
            }
        )
    }

    override fun runInference(input: Array<Array<BoardCellStatus>>, info: InferenceInfo): Int? {
        return (defaultModel as? PlaneStrikeAgent)?.predictNextMove(input)
    }
}
