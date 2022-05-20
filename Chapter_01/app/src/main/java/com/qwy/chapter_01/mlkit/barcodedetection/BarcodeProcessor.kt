/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qwy.chapter_01.mlkit.barcodedetection

import android.util.Log
import androidx.annotation.MainThread
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.qwy.chapter_01.mlkit.InputInfo
import com.qwy.chapter_01.mlkit.camera.FrameProcessorBase
import com.qwy.chapter_01.mlkit.camera.GraphicOverlay
import com.qwy.chapter_01.mlkit.camera.WorkflowModel
import com.qwy.chapter_01.mlkit.settings.PreferenceUtils
import java.io.IOException

/** A processor to run the barcode detector.  */
class BarcodeProcessor(private val workflowModel: WorkflowModel) :
    FrameProcessorBase<List<Barcode>>() {

    private val scanner = BarcodeScanning.getClient()

    override fun detectInImage(image: InputImage): Task<List<Barcode>> =
        scanner.process(image)

    @MainThread
    override fun onSuccess(
        inputInfo: InputInfo,
        results: List<Barcode>,
        graphicOverlay: GraphicOverlay
    ) {

        if (!workflowModel.isCameraLive) return

        Log.e("QwyOnSuccess", "Barcode result size: ${results.size}")
        Log.e("QwyOnSuccess", "results : " + Gson().toJson(results))


        // Picks the barcode, if exists, that covers the center of graphic overlay.

        val barcodeInCenter = results.firstOrNull { barcode ->
            val boundingBox = barcode.boundingBox ?: return@firstOrNull false
            val box = graphicOverlay.translateRect(boundingBox)
            box.contains(graphicOverlay.width / 2f, graphicOverlay.height / 2f)
        }
        Log.e("QwyOnSuccess", "barcodeInCenter == null : " + (barcodeInCenter == null))

        graphicOverlay.clear()
        if (barcodeInCenter == null) {
            graphicOverlay.add(BarcodeGraphicBase(graphicOverlay))
            workflowModel.setWorkflowState(WorkflowModel.WorkflowState.DETECTING)
        } else {
            val sizeProgress = PreferenceUtils.getProgressToMeetBarcodeSizeRequirement(
                graphicOverlay,
                barcodeInCenter
            )
            Log.e("QwyOnSuccess", "sizeProgress : $sizeProgress")

            if (sizeProgress < 1) {
                // Barcode in the camera view is too small, so prompt user to move camera closer.
                workflowModel.setWorkflowState(WorkflowModel.WorkflowState.CONFIRMING)
            } else {
                // Barcode size in the camera view is sufficient.
                if (PreferenceUtils.shouldDelayLoadingBarcodeResult(graphicOverlay.context)) {
                    workflowModel.setWorkflowState(WorkflowModel.WorkflowState.SEARCHED)
                    workflowModel.detectedBarcode.setValue(barcodeInCenter)

                } else {
                    workflowModel.setWorkflowState(WorkflowModel.WorkflowState.DETECTED)
                    workflowModel.detectedBarcode.setValue(barcodeInCenter)
                }
            }
        }
        graphicOverlay.invalidate()
    }

    override fun onFailure(e: Exception) {
        Log.e(TAG, "Barcode detection failed!", e)
    }

    override fun stop() {
        super.stop()
        try {
            scanner.close()
        } catch (e: IOException) {
            Log.e(TAG, "Failed to close barcode detector!", e)
        }
    }

    companion object {
        private const val TAG = "QwyBarcodeProcessor"
    }
}
