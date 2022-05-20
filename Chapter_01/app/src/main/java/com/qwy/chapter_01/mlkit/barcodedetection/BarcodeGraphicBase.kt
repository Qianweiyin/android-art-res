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

import android.graphics.*
import androidx.core.content.ContextCompat
import com.qwy.chapter_01.R
import com.qwy.chapter_01.mlkit.camera.GraphicOverlay
import com.qwy.chapter_01.mlkit.settings.PreferenceUtils


internal class BarcodeGraphicBase(overlay: GraphicOverlay) :
    GraphicOverlay.Graphic(overlay) {


    private val boxPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.c_90ffffff)
    }

    private val cornerPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.c_ffffff)
    }


    private val scrimPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.c_00000000)
    }

    //扫描线
    private val laserPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.c_419ef7)
    }


    private val boxRect: Rect = PreferenceUtils.getBarcodeReticleBox(overlay)

    override fun draw(canvas: Canvas) {
        // Draws the dark background scrim and leaves the box area clear.
        //绘制模糊区
        canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), scrimPaint)
        //绘制扫描区边框
        drawFrame(canvas, boxRect)
        //绘制边角
        drawCorner(canvas, boxRect)
        //绘制扫描线
        drawLaserScanner(canvas, boxRect)

    }


    // 绘制扫描区边框
    private fun drawFrame(canvas: Canvas, frame: Rect) {
        canvas.drawRect(
            frame.left.toFloat(),
            frame.top.toFloat(),
            frame.right.toFloat(),
            (frame.top + 1).toFloat(),
            boxPaint
        )
        canvas.drawRect(
            frame.left.toFloat(),
            frame.top.toFloat(),
            (frame.left + 1).toFloat(),
            frame.bottom.toFloat(),
            boxPaint
        )
        canvas.drawRect(
            frame.left.toFloat(),
            (frame.bottom - 1).toFloat(),
            frame.right.toFloat(),
            frame.bottom.toFloat(),
            boxPaint
        )
        canvas.drawRect(
            (frame.right - 1).toFloat(),
            frame.top.toFloat(),
            frame.right.toFloat(),
            frame.bottom.toFloat(),
            boxPaint
        )
    }


    //绘制边角
    private fun drawCorner(canvas: Canvas, rect: Rect) {
        //左上
        canvas.drawRect(
            rect.left.toFloat(),
            rect.top.toFloat(),
            (rect.left + CORNER_RECT_WIDTH).toFloat(),
            (rect.top + CORNER_RECT_HEIGHT).toFloat(),
            cornerPaint
        )
        canvas.drawRect(
            rect.left.toFloat(),
            rect.top.toFloat(),
            (rect.left + CORNER_RECT_HEIGHT).toFloat(),
            (rect.top + CORNER_RECT_WIDTH).toFloat(),
            cornerPaint
        )
        //右上
        canvas.drawRect(
            (rect.right - CORNER_RECT_WIDTH).toFloat(),
            rect.top.toFloat(),
            rect.right.toFloat(),
            (rect.top + CORNER_RECT_HEIGHT).toFloat(),
            cornerPaint
        )
        canvas.drawRect(
            (rect.right - CORNER_RECT_HEIGHT).toFloat(),
            rect.top.toFloat(),
            rect.right.toFloat(),
            (rect.top + CORNER_RECT_WIDTH).toFloat(),
            cornerPaint
        )
        //左下
        canvas.drawRect(
            rect.left.toFloat(),
            (rect.bottom - CORNER_RECT_WIDTH).toFloat(),
            (rect.left + CORNER_RECT_HEIGHT).toFloat(),
            rect.bottom.toFloat(),
            cornerPaint
        )
        canvas.drawRect(
            rect.left.toFloat(),
            (rect.bottom - CORNER_RECT_HEIGHT).toFloat(),
            (rect.left + CORNER_RECT_WIDTH).toFloat(),
            rect.bottom.toFloat(),
            cornerPaint
        )
        //右下
        canvas.drawRect(
            (rect.right - CORNER_RECT_WIDTH).toFloat(),
            (rect.bottom - CORNER_RECT_HEIGHT).toFloat(),
            rect.right.toFloat(),
            rect.bottom.toFloat(),
            cornerPaint
        )
        canvas.drawRect(
            (rect.right - CORNER_RECT_HEIGHT).toFloat(),
            (rect.bottom - CORNER_RECT_WIDTH).toFloat(),
            rect.right.toFloat(),
            rect.bottom.toFloat(),
            cornerPaint
        )
    }

    //绘制扫描线
    private fun drawLaserScanner(canvas: Canvas, rect: Rect) {
        laserPaint.isAntiAlias = true
        val radialGradient = RadialGradient(
            (rect.left + rect.width() / 2).toFloat(),
            (scannerStart + SCANNER_LINE_HEIGHT / 2).toFloat(),
            360f,
            ContextCompat.getColor(context, R.color.c_419ef7),
            shadeColor(ContextCompat.getColor(context, R.color.c_419ef7)),
            Shader.TileMode.MIRROR
        )
        laserPaint.shader = radialGradient
        if (scannerStart <= scannerEnd) {
            val rectF = RectF(
                (rect.left + 2 * SCANNER_LINE_HEIGHT).toFloat(),
                scannerStart.toFloat(),
                (rect.right - 2 * SCANNER_LINE_HEIGHT).toFloat(),
                (scannerStart + SCANNER_LINE_HEIGHT).toFloat()
            )
            canvas.drawOval(rectF, laserPaint)
            scannerStart += SCANNER_LINE_MOVE_DISTANCE
        } else {
            scannerStart = rect.top
        }
        laserPaint.shader = null
    }

    //处理颜色模糊
    private fun shadeColor(color: Int): Int {
        val hax = Integer.toHexString(color)
        val result = "20" + hax.substring(2)
        return Integer.valueOf(result, 16)
    }

//    private var scannerStart = 0
//    private var scannerEnd = 0

    companion object {
        //Private property name 'CORNER_RECT_WIDTH' should not contain underscores in the middle or the end

        //扫描区边角的宽
        private const val CORNER_RECT_WIDTH = 12

        //扫描区边角的高
        private const val CORNER_RECT_HEIGHT = 80

        //扫描线宽度
        private const val SCANNER_LINE_HEIGHT = 10

        //扫描线移动距离
        private const val SCANNER_LINE_MOVE_DISTANCE = 5
        private var scannerStart = 0
        private var scannerEnd = 0
    }

}
