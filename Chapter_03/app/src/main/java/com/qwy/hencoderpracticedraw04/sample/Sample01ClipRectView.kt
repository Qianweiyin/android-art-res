package com.qwy.hencoderpracticedraw04.sample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.qwy.chapter_03.R


class Sample01ClipRectView : View {


    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        /**
         * 1 范围裁切
         *  范围裁切有两个方法: clipRect() 和 clipPath()。
         *  裁切方法之后的绘制代码,都会被限制在裁切范围内。
         *
         *  1.1 clipRect()
         *  记得要加上Canvas.save()和Canvas.restore()来及时恢复绘制范围。
         *
         *  1.2 clipPath()
         *  其实和 clipPath()用法完全一样,只是把参数换成了Path,所以裁切的形状更多了一些。
         *
         * 2 几何变换
         *   几何变换的使用大概分为三类:
         *   1.使用Canvas来做常见的二维变换;
         *   2.使用Matrix来做常见和不常见的二维变换;
         *   3.使用Camera来做三维变换。
         *
         *
         *   2.1 使用Canvas来做常见的二维变换:
         *
         *   2.1.1  Canvas.translate(float dx,float dy)平移
         *          参数里的dx和dy表示横向和纵向的位移。
         *
         *   2.1.2  Canvas.rotate(float degrees,float px,float py)旋转
         *          参数里的degrees是旋转角度,单位是度(也就是一周有360° 的那个单位),方向是顺时针为正向;
         *          px和py是轴心的位置。
         *
         *   2.1.3  Canvas.scale(float sx,float sy,float px,float px)缩放
         *          sx、sy是横向和纵向的放缩倍数；
         *          px、py是放缩的轴心。
         *
         *   2.1.4  skew(float sx,float sy) 错切
         *          sx、sy是x方向和y方向的错切系数。
         *
         *   2.2 使用Matrix来做变换
         *   2.2.1 使用Matrix来做常见变换
         *         Matrix做常见变换的方式：
         *         1.创建Matrix对象;
         *         2.调用Matrix的pre/postTranslate/Rotate/Scale/Skew()方法来设置几何变换；
         *         3.使用Canvas.setMatrix(matrix)或Canvas.concat(matrix)来把几何变换应用到Canvas。
         *
         *         效果和Canvas是一样的。
         *         把Matrix应用到Canvas有两个方法:
         *         Canvas.setMatrix(matrix): 用Matrix直接替换Canvas当前的变换矩阵，即抛弃Canvas当前的变换,改用Matrix的变换
         *         Canvas.concat(matrix):    用Canvas当前的变换矩阵和Matrix相乘,即基于Canvas当前的变换,叠加上Matrix中的变换。
         *
         *   2.2.2 使用Matrix来做自定义变换
         *         Matrix的自定义变换使用的是setPolyToPoly()方法。
         *
         *         2.2.2.1  Matrix.setPolyToPoly(float[] src,
         *                                       int srcIndex,
         *                                       float[] dst,
         *                                       int dstIndex,
         *                                       int pointCount)
         *                  用点对点映射的方式设置变换
         *
         *         poly就是「多」的意思。
         *         setPolyToPoly()的作用是通过多点的映射的方式来直接设置变换。
         *         「多点映射」的意思就是把指定的点移动到给出的位置,从而发生形变。
         *         例如:(0,0) ->(100,100) 表示把(0,0)位置的像素移动(100,100)的位置,这个是单点的映射。
         *         单点映射可以实现平移。
         *         而多点的映射,就可以让绘制内容任意地扭曲。
         *
         *         src 和 dst 是源点集合目标点集;
         *         srcIndex 和 dstIndex 是第一个点的偏移;
         *         pointCount是采集的点的个数(个数不能大于4,因为大于4个点就无法计算变换了)
         *
         *
         *   2.3 使用Camera来做三维变换
         *   Camera 的三维变换有三类: 旋转、平移、移动相机
         *
         *  2.3.1 Camera.rotate*() 三维旋转
         *
         *
         *
         *
         *
         *
         *
         *
         */


        /**
         * 在绘制完之后,还需要恢复绘制范围,要不你后面的所有绘制就会都被裁切了,
         * 恢复的方式在裁切之前,用Canvas.save()这个方法,把Canvas的状态保存,
         * 然后在绘制之后,用Canvas.restore()把绘制范围恢复，你的裁切就被去掉了。( 也就是这之后的绘制，就不会被裁切了。)
         */


        /**
         * 几何变换也得配合save()和restore()来使用，
         * 在绘制之前用save()来保存状态，在绘制之后用restore()来恢复状态
         * 这样你的几何变换才不会对之后的绘制造成影响
         */
        canvas?.save()
        canvas?.clipRect(left, top, right, bottom)
        bitmap?.let { canvas?.drawBitmap(it, x, y, paint) }
        canvas?.restore()



        Log.e("QwyClipRect", "width : $width  bitmap : ${bitmap!!.width}")
        Log.e("QwyClipRect", "height : $height  bitmap : ${bitmap!!.height}")

        val left = (width - bitmap!!.width) / 2f
        val top = (height - bitmap!!.height) / 2f
        Log.e("QwyClipRect", "left : $left  top : $top")
        canvas?.let {
            it.clipRect(left + 50, top + 50, left + 300, top + 200)
            bitmap?.let { it1 ->
                it.drawBitmap(it1, left, top, paint)
            }
        }

    }

}