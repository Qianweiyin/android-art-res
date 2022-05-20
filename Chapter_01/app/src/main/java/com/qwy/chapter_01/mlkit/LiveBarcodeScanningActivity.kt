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

package com.qwy.chapter_01.mlkit

import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.common.base.Objects
import com.qwy.chapter_01.R
import com.qwy.chapter_01.mlkit.barcodedetection.BarcodeField
import com.qwy.chapter_01.mlkit.barcodedetection.BarcodeProcessor
import com.qwy.chapter_01.mlkit.camera.CameraSource
import com.qwy.chapter_01.mlkit.camera.CameraSourcePreview
import com.qwy.chapter_01.mlkit.camera.GraphicOverlay
import com.qwy.chapter_01.mlkit.camera.WorkflowModel
import java.io.IOException
import java.util.ArrayList

/** Demonstrates the barcode scanning workflow using camera preview.  */
class LiveBarcodeScanningActivity : AppCompatActivity(), OnClickListener {

    private var cameraSource: CameraSource? = null
    private var cameraSourcePreview: CameraSourcePreview? = null
    private var graphicOverlay: GraphicOverlay? = null
    private var settingsButton: View? = null
    private var flashButton: View? = null
    private var workflowModel: WorkflowModel? = null
    private var currentWorkflowState: WorkflowModel.WorkflowState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_live_barcode)
        cameraSourcePreview = findViewById(R.id.camera_preview)
        graphicOverlay = findViewById<GraphicOverlay>(R.id.camera_preview_graphic_overlay).apply {
            Log.e(TAG, "graphicOverlay  setOnClickListener ")

            setOnClickListener(this@LiveBarcodeScanningActivity)
            cameraSource = CameraSource(this)
        }



        findViewById<View>(R.id.close_button).setOnClickListener(this)
        flashButton = findViewById<View>(R.id.flash_button).apply {
            setOnClickListener(this@LiveBarcodeScanningActivity)
        }
        settingsButton = findViewById<View>(R.id.settings_button).apply {
            setOnClickListener(this@LiveBarcodeScanningActivity)
        }

        setUpWorkflowModel()
    }

    override fun onResume() {
        super.onResume()

        workflowModel?.markCameraFrozen()
        settingsButton?.isEnabled = true
        currentWorkflowState = WorkflowModel.WorkflowState.NOT_STARTED
        cameraSource?.setFrameProcessor(BarcodeProcessor(workflowModel!!))
        workflowModel?.setWorkflowState(WorkflowModel.WorkflowState.DETECTING)
    }

    override fun onPostResume() {
        super.onPostResume()
//        BarcodeResultFragment.dismiss(supportFragmentManager)
    }

    override fun onPause() {
        super.onPause()
        currentWorkflowState = WorkflowModel.WorkflowState.NOT_STARTED
        stopCameraPreview()
    }

    override fun onDestroy() {
        super.onDestroy()
        //释放摄像头
        cameraSource?.release()
        cameraSource = null
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.close_button -> onBackPressed()
            //闪光灯设置
            R.id.flash_button -> {
                flashButton?.let {
                    if (it.isSelected) {
                        //关闭闪光灯
                        it.isSelected = false
                        cameraSource?.updateFlashMode(Camera.Parameters.FLASH_MODE_OFF)
                    } else {
                        //开启闪光灯
                        it.isSelected = true
                        cameraSource!!.updateFlashMode(Camera.Parameters.FLASH_MODE_TORCH)
                    }
                }
            }
            R.id.settings_button -> {
                settingsButton?.isEnabled = false
//                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
    }

    private fun startCameraPreview() {
        val workflowModel = this.workflowModel ?: return
        val cameraSource = this.cameraSource ?: return
        if (!workflowModel.isCameraLive) {
            try {
                workflowModel.markCameraLive()
                cameraSourcePreview?.start(cameraSource)
            } catch (e: IOException) {
                Log.e(TAG, "Failed to start camera preview!", e)
                cameraSource.release()
                this.cameraSource = null
            }
        }
    }

    private fun stopCameraPreview() {
        val workflowModel = this.workflowModel ?: return
        if (workflowModel.isCameraLive) {
            workflowModel.markCameraFrozen()
            flashButton?.isSelected = false
            cameraSourcePreview?.stop()
        }
    }

    private fun setUpWorkflowModel() {
//        workflowModel = ViewModelProviders.of(this).get(WorkflowModel::class.java)
        workflowModel = ViewModelProviders.of(this)[WorkflowModel::class.java]

        // Observes the workflow state changes, if happens, update the overlay view indicators and
        // camera preview state.
        workflowModel!!.workflowState.observe(this, Observer { workflowState ->
            if (workflowState == null || Objects.equal(currentWorkflowState, workflowState)) {
                return@Observer
            }

            currentWorkflowState = workflowState
            Log.d(TAG, "Current workflow state: ${currentWorkflowState!!.name}")


            when (workflowState) {
                WorkflowModel.WorkflowState.DETECTING -> {
                    startCameraPreview()
                }
                WorkflowModel.WorkflowState.CONFIRMING -> {
                    startCameraPreview()
                }
                WorkflowModel.WorkflowState.SEARCHING -> {
                    stopCameraPreview()
                }
                WorkflowModel.WorkflowState.DETECTED, WorkflowModel.WorkflowState.SEARCHED -> {
                    stopCameraPreview()
                }
                else -> {
                }
            }


        })

        workflowModel?.detectedBarcode?.observe(this, Observer { barcode ->
            if (barcode != null) {
                val barcodeFieldList = ArrayList<BarcodeField>()

                Log.e("QwyScan", "rawValue : " + barcode.rawValue)

                Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show()

//                barcodeFieldList.add(BarcodeField("Raw Value", barcode.rawValue ?: ""))
//                BarcodeResultFragment.show(supportFragmentManager, barcodeFieldList)
            }
        })
    }

    companion object {
        private const val TAG = "QwyLiveBarcodeActivity"
    }
}