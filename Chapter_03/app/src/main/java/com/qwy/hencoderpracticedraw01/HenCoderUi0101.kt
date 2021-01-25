package com.qwy.hencoderpracticedraw01

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

//This type has a constructor, and thus must be initialized here
class HenCoderUi0101 : View {


    private val paint: Paint = Paint()

    //Can be joined with assignment
    private val rectFOval: RectF = RectF(400f, 50f, 700f, 200f)


    private val path: Path = Path()


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        /**
         * kotlin作用域函数
         * https://www.kotlincn.net/docs/reference/scope-functions.html
         */


        /**
         * Canvas.drawColor(@ColorInt int color) 颜色填充
         */
//        canvas?.drawColor(Color.BLACK)
//        canvas?.drawColor(Color.parseColor("#88880000"))
//        canvas?.drawRGB(100, 200, 100);
        canvas?.drawARGB(100, 100, 200, 100)


        /**
         * 插播一： Paint.setColor(int color)
         */
        paint.color = Color.RED // 设置为红色

        /**
         * 插播二： Paint.setStyle(Paint.Style style)
         *
         * Style 具体来说有三种：
         * FILL, STROKE 和 FILL_AND_STROKE 。
         * FILL 是填充模式，
         * STROKE 是画线模式（即勾边模式），
         * FILL_AND_STROKE 是两种模式一并使用：既画线又填充。它的默认值是 FILL，填充模式
         */
        paint.style = Paint.Style.FILL_AND_STROKE// Style 修改为画线模式


        /**
         * 插播三： Paint.setStrokeWidth(float width)
         */

//        paint.strokeWidth = 20F// 线条宽度为 20 像素


        /**
         * 插播四： 抗锯齿
         * 启抗锯齿来让图形和文字的边缘更加平滑。
         * 开启抗锯齿很简单，只要在 new Paint() 的时候加上一个 ANTI_ALIAS_FLAG 参数就行,
         * 另外，你也可以使用 Paint.setAntiAlias(boolean aa) 来动态开关抗锯齿。
         */
        paint.isAntiAlias = true


        /**
         * drawCircle(float centerX, float centerY, float radius, Paint paint) 画圆
         *
         * 独有信息？就是只有它有，别人没有的信息
         * 公有信息。
         *
         */
//        canvas?.drawCircle(300F, 300F, 200F, paint)


        /**
         * drawRect(float left, float top, float right, float bottom, Paint paint) 画矩形
         *
         * left, top, right, bottom 是矩形四条边的坐标。
         *
         * 另外，它还有两个重载方法 drawRect(RectF rect, Paint paint)
         * 和 drawRect(Rect rect, Paint paint) ，
         * 让你可以直接填写 RectF 或 Rect 对象来绘制矩形。
         *
         */

//        paint.style = Paint.Style.FILL
//        canvas?.drawRect(100F, 100F, 500F, 500F, paint)
//
//        paint.style = Paint.Style.STROKE
//        canvas?.drawRect(600F, 100F, 1000F, 500F, paint)


        /**
         * drawPoint(float x, float y, Paint paint) 画点
         *
         * x和y是点的坐标。
         * 点的大小可以通过paint.setStrokeWidth(width)来设置;
         * 点的形状可以通过paint.setStrokeCap(cap)来设置 ;
         * ROUND画出来是圆形的点，
         * SQUARE或BUTT画出来是方形的点。（点还有形状？是的，反正Google是这么说的）
         *
         *
         * 好像有点像 FILL 模式下的 drawCircle() 和 drawRect() ？
         * 事实上确实是这样的，它们和 drawPoint() 的绘制效果没有区别。
         * 各位在使用的时候按个人习惯和实际场景来吧，哪个方便和顺手用哪个。
         */

//        paint.strokeWidth = 20f
//        paint.strokeCap = Paint.Cap.ROUND
//        canvas?.drawPoint(50f, 50f, paint)
//
//
//
//        paint.strokeWidth = 50f
//        paint.strokeCap = Paint.Cap.SQUARE
//        canvas?.drawPoint(100f, 100f, paint)


        /**
         * drawPoints(float[] pts, int offset, int count, Paint paint)
         * / drawPoints(float[] pts, Paint paint) 画点（批量）
         *
         * 同样是画点，它和drawPoint()的区别是可以画多个点。
         * pts这个数组是点的坐标，每两个成一对;
         * offset表示跳过数组的前几个数再开始记坐标;
         * count表示一共要绘制几个点。
         *
         */
//        paint.strokeWidth = 20f
//        paint.strokeCap = Paint.Cap.ROUND
//        val points: FloatArray =
//            floatArrayOf(
//                0f, 0f,
//                50f, 50f,
//                50f, 100f,
//                100f, 50f,
//                100f, 100f,
//                150f, 50f,
//                150f, 100f
//            )
//
//        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
//        canvas?.drawPoints(
//            points, 2 /* 跳过两个数，即前两个 0 */,
//            8 /* 一共绘制 8 个数（4 个点）*/, paint
//        )

//        canvas?.drawPoints(
//            points, 0 /* 跳过两个数，即前两个 0 */,
//            14 /* 一共绘制 8 个数（4 个点）*/, paint
//        )


        /**
         * drawOval(float left, float top, float right, float bottom, Paint paint) 画椭圆
         *
         */
//        paint.style = Paint.Style.FILL
//        canvas?.drawOval(50f, 50f, 350f, 200f, paint)

//        paint.style = Paint.Style.STROKE
        // Avoid object allocations during draw/layout operations (preallocate and reuse instead)
        //不要在view绘制和做布局操作的时候实例化数据,
        //即不要在自定义View的onMeasure、onLayout、onDraw等方法里面做new对象的操作
        //因为实例化对象是会耗性能的，而这几个方法会被多次调用，
        // 所以需要将对象作为属性，在初始化的时候就实例化好对象，在这些方法里面直接用就行了！
        // canvas?.drawOval(400f, 50f, 700f, 200f, paint)
//        canvas?.drawOval(rectFOval, paint)


        /**
         * drawLine(float startX, float startY, float stopX, float stopY, Paint paint) 画线
         *
         * startX,startY,stopX,stopY 分别是线的起点和终点坐标
         *
         * 由于直线不是封闭图形，所以 setStyle(style) 对直线没有影响。
         */

//        canvas?.drawLine(200f, 200f, 800f, 500f, paint)


        /**
         * drawLines(float[] pts, int offset, int count, Paint paint)
         * / drawLines(float[] pts, Paint paint) 画线（批量）
         *
         * drawsLines()是drawLine()的复数版
         */
//        val points: FloatArray = floatArrayOf(
//            20f, 20f,
//            120f, 20f,
//            70f, 20f,
//            70f, 120f,
//            20f, 120f,
//            120f, 120f,
//            150f, 20f,
//            250f, 20f,
//            150f, 20f,
//            150f, 120f,
//            250f, 20f,
//            250f, 120f,
//            150f, 120f,
//            250f, 120f
//        )
//        //数组是点的坐标，每两个成一对
//        canvas?.drawLines(points, paint)


        /**
         * Rect、Rectangle
         *
         * drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) 画圆角矩形
         *
         * left、top、right、bottom是四条边的坐标，
         * rx和ry是圆角的横向半径和纵向半径
         */
        //Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type Canvas?
//        canvas?.drawRoundRect(100f, 100f, 500f, 300f, 50f, 50f, paint)


        /**
         * drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) 绘制弧形或扇形
         *
         * drawArc() 是使用一个椭圆来描述弧形的
         *
         *
         * left,top,right,bottom 描述的是这个弧形所在的椭圆;
         * startAngle是弧形的起始角度（x轴的正向，即正右的方向，是0度的位置;顺时针为正角度，逆时针为负角度）
         * sweepAngle是弧形划过的角度;
         * useCenter表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心就是扇形
         *
         */

//        paint.style = Paint.Style.FILL
//        canvas?.drawArc(200f, 100f, 800f, 500f, -110f, 110f, true, paint)
//        canvas?.drawArc(200f, 100f, 800f, 500f, 20f, 140f, false, paint)// 绘制弧形
//        paint.style = Paint.Style.STROKE// 画线模式
//        canvas?.drawArc(200F, 100F, 800F, 500F, 180F, 60F, false, paint)// 绘制不封口的弧形


        /**
         * 到此为止，以上就是Canvas所有的简单图形的绘制。
         * 除了简单图形的绘制，Canvas还可以使用drawPath(Path path)来绘制自定义图形。
         *
         * drawPath(Path path, Paint paint) 画自定义图形
         *
         * 前面的这些方法，都是绘制某个给定的图形，而drawPath()可以绘制自定义图形。
         * 当你要绘制的图形比较特殊，使用前面的那些方法做不到的时候，就可以使用drawPath()来绘制。
         *
         * drawPath(path)这个方法是通过描述路径的方式来绘制图形的，
         * 它的path参数就是用来描述图形路径的对象。
         * path的类型是Path。
         *
         * Path可以描述直线、二次曲线、三次曲线、圆、椭圆、弧形、矩形、圆角矩形。
         * 把这些图形结合起来，就可以描述出很多复杂的图形。
         *
         * Path有两类方法，
         * 一是直接描述路径的，另一类是辅助的设置或计算。
         */

        /**
         * Path 方法第一类：直接描述路径。
         *
         * 这一类方法还可以细分为两组：添加子图形或画线（直线或曲线）
         *
         * 第一组：addXxx() ———— 添加子图形
         *
         * addCircle(float x, float y, float radius, Direction dir) 添加圆
         *
         * x,y,radius 这三个参数就是圆的基本信息，最后一个参数die是画圆的路径的方向。
         *
         *路径方向有两种：
         * 顺时针（CW clockwise）和逆时针（CCW counter-clockwise）。
         * 对于普通情况，这参数填CW还是CCW没有影响。
         *
         * 它只是在需要填充的图形（Paint.Style为FILL或FILL_AND_STROKE）,
         * 并且图形出现自相交时，用于判断填充范围的。
         *
         */
//        path.addCircle(300f, 300f, 200f, Path.Direction.CW)
//        canvas?.drawPath(path, paint)


        /**
         * 第二组：xxxTo() ————— 画线（直线或曲线）
         *
         * 这一组和第一组addXxx()方法的区别在于，
         * 第一组是添加的完整封闭图形(除了addPath()),而这一组添加的只是一条线。
         *
         * lineTo(float x, float y) / rLineTo(float x, float y) 画直线
         *
         * 从当前位置向目标位置画一条直线，x和y是目标位置的坐标。
         * 这两个方法的区别是，lineTo(x,y)的参数是绝对坐标，
         * 而rLineTo(x,y)的参数是相对当前位置的相对坐标（前缀r指的就是relatively「相对地」)
         *
         * 当前位置：
         * 所谓当前位置，即最后一次调用画Path的方法的终点位置。
         * 初始值为原点(0,0)
         */
//        paint.style = Paint.Style.STROKE
//        path.lineTo(100f, 100f) // 由当前位置 (0, 0) 向 (100, 100) 画一条直线
//        path.rLineTo(100f, 0f) // 由当前位置 (100, 100) 向正右方 100 像素的位置画一条直线
//        canvas?.drawPath(path, paint)


        /**
         * quadTo(float x1, float y1, float x2, float y2)
         * / rQuadTo(float dx1, float dy1, float dx2, float dy2) 画二次贝塞尔曲线
         *
         * 这条二次贝塞尔曲线的起点就是当前位置，
         * 而参数中的x1,y1和x2,y2则分别是控制点和终点的坐标。
         * 和rLineTo(x,y)同理，rQuadTo(dx1,dy1,dx2,dy2)的参数也是相对坐标。
         *
         * 贝塞尔曲线：
         * 贝塞尔曲线是几何上的一种曲线。
         * 它通过起点、控制点和终点来描述一条曲线，主要用于计算机图形学。
         * 概念总是说着容易听着难，总之使用它可以绘制很多圆润又好看的图形，
         * 但要把它熟练掌握、灵活使用却是不容易的。
         * 不过还好的是，一般情况下，贝塞尔曲线并没有什么用处，
         * 只在少数场景绘制一些特殊图形的时候才会用到，
         * 所以如果你还没掌握自定义绘制，可以先把贝塞尔曲线放一放，稍后再学也完全没问题。
         * 至于怎么学，贝塞尔曲线的只是网上一搜一大把，这里就不讲了。
         *
         */


        /**
         * cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
         * / rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) 画三次贝塞尔曲线
         *
         *
         * 和上面整个quadTo() rQuadTo()的二次贝塞尔曲线同理，
         * cubicTo()和rCubicTo()是三次贝塞尔曲线
         *
         */


        /**
         * moveTo(float x, float y)
         * / rMoveTo(float x,float y)移动到目标位置
         *
         * 不论是直线还是贝塞尔曲线，都是以当前位置作为起点，而不能指定起点。
         * 但你可以通过moveTo(x,y)或rMoveTo()来改变当前位置，从而间接地设置这些方法的起点。
         *
         */
//        paint.style = Paint.Style.STROKE
//        path.lineTo(100f, 100f) // 画斜线
//        path.moveTo(200f, 100f) // 我移~~
//        path.lineTo(200f, 0f)// 画竖线
//        canvas?.drawPath(path, paint)

        /**
         * moveTo(x,y)虽然不添加圆形，但它会设置圆形的起点，
         * 所以它是非常重要的一个辅助方法。
         */


        /**
         * 另外，第二组还有两个特殊的方法：arcTo() 和 addArc()。
         *
         * 它们也是用来画线的，但并不使用当前位置作为弧线的起点。
         *
         * arcTo(RectF oval, float startAngle, float sweepAngle,boolean forceMoveTo)/
         * arcTo(float left, float top, float right, float bottom, float startAngle,float sweepAngle, boolean forceMoveTo)
         * arcTo(RectF oval, float startAngle, float sweepAngle)
         * 画弧形
         *
         * 这个方法和Canvas.drawArc()比起来，
         * 少了一个参数useCenter,而多了一个参数forceMoveTo
         * 少了userCenter,是因为arcTo()只用来画弧形而不画扇形，所以不再需要useCenter参数;
         * 而多出来的这个forceMoveTo参数的意思是，
         * 绘制是要「抬一下笔移动过去」，还是「直接拖着笔过去」，
         * 区别在于是否留下移动的痕迹。
         *
         */
//        paint.style = Paint.Style.STROKE
//        path.lineTo(100f, 100f)
//        path.arcTo(100f, 100f, 300f, 300f, -90f, 90f, true) // 强制移动到弧形起点（无痕迹）
//        canvas?.drawPath(path, paint)

//        paint.style = Paint.Style.STROKE
//        path.lineTo(100f, 100f)
//        path.arcTo(100f, 100f, 300f, 300f, -90f, 90f, false) // 直接连线连到弧形起点（有痕迹）
//        canvas?.drawPath(path, paint)


        /**
         * addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle)
         * / addArc(RectF oval, float startAngle, float sweepAngle)
         *
         * 又是一个弧形的方法。
         * 一个叫arcTo(), 一个叫addArc(),
         * 都是弧形，区别在哪里？
         * 其实很简单：addArc()只是一个直接使用了forceMoveTo = true 的简化版的arcTo()。
         *
         */

//        paint.style = Paint.Style.STROKE
//        path.lineTo(100f, 100f)
//        path.addArc(100f, 100f, 300f, 300f, -90f, 90f)
//        canvas?.drawPath(path, paint)


        /**
         * close()封闭当前子图形
         *
         * 它的作用是把当前的子图形封闭，
         * 即由当前位置向当前子图形的起点绘制一条直线。
         */
//        paint.style = Paint.Style.STROKE
//        path.moveTo(100f, 100f)
//        path.lineTo(200f, 100f)
//        path.lineTo(150f, 150f)// 子图形未封闭
//        canvas?.drawPath(path, paint)


//        paint.style = Paint.Style.STROKE
//        path.moveTo(100f, 100f)
//        path.lineTo(200f, 100f)
//        path.lineTo(150f, 150f)
//        path.close()
//        canvas?.drawPath(path, paint)
        //使用close()封闭子图形.等价于path.lineTo(100,100)
        //close()和lineTo(起点坐标)是完全等价的。

        /**
         * 「子图形」：
         * 官方文档里叫做contour。
         * 但由于在整个场景下我找不到这个词核实的中文翻译(直译的话叫做「轮廓」),
         * 所以我换了个便于中国人理解的词：「子图形」。
         *
         * 前面说到，第一组方法是「添加子图形」，所谓「子图形」，指的就是一次不间断的连线。
         * 一个Path可以包含多个子图形。
         * 当使用第一组方法，即addCircle() addRect()等方法的时候，
         * 每一次方法调用都是新增了一个独立的子图形;
         * 而如果使用第二组方法，即lineTo() arcTo()等方法的时候，
         * 则是每一次断线（即每一次「抬笔」）,都标志着一个子图形的结束，以及一个新的子图形的开始。
         *
         *另外，不是所有的子图形都需要使用close()来封闭。
         * 当需要填充图形时(即Paint.Style为FILL或FILL_ADN_STROKE),
         * Path会自动封闭子图形。
         */

        paint.style = Paint.Style.FILL
        path.moveTo(100f, 100f)
        path.lineTo(200f, 100f)
        path.lineTo(150f, 150f)
        // 这里只绘制了两条边，但由于 Style 是 FILL ，所以绘制时会自动封口
        canvas?.drawPath(path, paint)
        /**
         * 以上就是Path的第一类方法：直接描述路径的。
         */

        /**
         * Path方法第二类：辅助的设置或计算
         * 这类方法的使用场景比较少，我在这里就不多讲了，只讲其中一个方法：
         * setFillType(FillType fillType)
         *
         * Path.setFillType(Path.FillType ft) 设置填充方式
         * 前面在说dir参数的时候提到，Path.setFillType(fillType)
         * 是用来设置图形自相交时填充算法的。
         *
         * 方法中填入不同的FillType值，就会有不同的填充效果。
         * FillType的取值有四个:
         * EVEN_ODD
         * WINDING(默认值)
         * INVERSE_EVEN_ODD
         * INVERSE_WINDING
         *
         * 其中后面的两个带有INVERSE_ 前缀的，只是前两个的反色版本，
         * 所以只要把前两个，即EVEN_ODD和WINDING,搞明白就可以了。
         *
         * EVEN_ODD和WINDING的原理有点复杂，直接讲出来的话信息量太大，
         * 所以我先给一个简单粗暴版的总结。
         * WINDING是全填充，而EVEN_ODD是交叉填充。
         *
         * 之所以叫「简单粗暴版」,是因为这些只是通常情形下的效果;
         * 而如果要准确了解它们在所有情况下的效果，就得先知道它们的原理，即它们的具体算法。
         *
         */

        /**
         * EVEN_ODD 和 WINDING 的原理
         *
         *xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
         *xxxxxxxxxxxxxxxxxxxxx
         */


        /**
         * 好，花了好长的篇幅来讲drawPath(path) 和 Path,终于讲完了。
         * 同时，Canvas对图形的绘制就也讲完了。
         *
         * 图形简单时，使用drawCircle() drawRect()等方法来绘制;
         * 图形复杂时，使用drawPath()来绘制自定义图形。
         *
         * 除此之外，Canvas还可以绘制Bitmap和文字。
         *
         */


        /**
         * drawBitmap(Bitmap bitmap, float left, float top, Paint paint) 画 Bitmap
         *
         *
         */


        /**
         * drawText(String text, float x, float y, Paint paint) 绘制文字
         */
        paint.textSize = 18f
        canvas?.drawText("qianweiyin", 100f, 25f, paint)

    }

}