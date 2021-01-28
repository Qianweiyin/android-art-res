package com.qwy.hencoderpracticedraw02

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.qwy.chapter_03.R


class HenCoderUi0102 : View {


    /**
     * LinearGradient 线性渐变
     *
     * x0 y0 x1 y1：渐变的两个端点的位置
     * color0 color1 是端点的颜色
     * tile：端点范围之外的着色规则，类型是 TileMode。
     * TileMode 一共有 3 个值可选： CLAMP, MIRROR 和 REPEAT。
     *
     * CLAMP （夹子模式？？？算了这个词我不会翻）会在端点之外延续端点处的颜色；
     * MIRROR 是镜像模式；
     * REPEAT 是重复模式。具体的看一下例子就明白。
     *
     *
     */
//    private val shader: Shader = LinearGradient(
//        100f, 100f, 200f, 200f,
//        Color.BLACK,
//        Color.RED,
//        Shader.TileMode.MIRROR
//    )


    /**
     * RadialGradient 辐射渐变
     *
     * 射渐变很好理解，就是从中心向周围辐射状的渐变
     *
     * centerX centerY：辐射中心的坐标
     * radius：辐射半径
     * centerColor：辐射中心的颜色
     * edgeColor：辐射边缘的颜色
     * tileMode：辐射范围之外的着色模式。
     */
//    private val shader = RadialGradient(
//        400f, 400f, 100f,
//        Color.BLACK,
//        Color.RED,
//        Shader.TileMode.REPEAT
//    )


    /**
     * SweepGradient 扫描渐变
     *
     * cx cy ：扫描的中心
     * color0：扫描的起始颜色
     * color1：扫描的终止颜色
     *
     */

//    private val shader = SweepGradient(
//        400f, 400f,
//        Color.BLACK,
//        Color.RED
//    )


    /**
     * BitmapShader
     *
     * 用Bitmap来着色(终于不是渐变了)。
     * 其实也就是用Bitmap的像素来作为图形或文字的填充。
     *
     * bitmap：用来做模板的 Bitmap 对象
     * tileX：横向的 TileMode
     * tileY：纵向的 TileMode
     *
     */
//    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.bianfuxia)
//    private val shader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)


    /**
     * ComposeShader 混合着色器
     *
     * 所谓混合，就是把两个 Shader 一起使用。
     *
     *
     * 下面这段代码中使用了两个BitmapFactory来作为ComposeShader()的参数，
     * 而ComposeShader()在硬件加速下是不支持两个相同类型的Shader的，
     * 所以这里也需要关闭硬件加速才能看到效果。
     *
     * https://developer.android.com/guide/topics/graphics/hardware-accel?hl=zh-cn
     *
     * 构造方法：ComposeShader(Shader shaderA, Shader shaderB, PorterDuff.Mode mode)
     * 参数：
     * shaderA,shaderB : 两个相继使用的Shader
     * mode ： 两个Shader的叠加模式，即shaderA和shaderB 应该怎样共同绘制。
     *         它们的类型是PorterDuff.Mode
     *
     *
     * PorterDuff.Mode
     * PorterDuff.Mode是用来指定两个图像共同绘制时的颜色策略的。
     * 它是一个enum，不同的Mode可以指定不同的策略。
     *「颜色策略」的意思,就是说把源图像绘制到目标图像处时，应该怎么确定二者结合后的颜色，
     * 而对于ComposeShader(shaderA , shaderB, mode)这个具体的方法，
     * 就是指应该怎样把shaderB 绘制在shaderA 上来得到一个结合后的 Shader。
     *
     * 没有听说过PorterDuff.Mode的人，看到这里很可能依然会一头雾水：
     * 「什么怎么结合？就...两个图像一叠加，结合呗？还能怎么结合？」
     * 你还别说，还真的是有很多策略来结合。
     *
     * 最符合直接的结合策略，就是下面代码中使用的Mode:SRC_OVER。
     * 它的算法非常直观：
     * 就像上面图中的那样，把源图像直接铺在目标图像上。
     * 不过，除了这种，其实还有一些其他的结合方式。
     * 例如如果我把上面例子中的参数mode改为PorterDuff.Mode.DST_OUT,就会变成挖空效果。
     *          而如果再把mode改为Porter.Mode.DST_IN,就会变成蒙版抠图效果。
     *  这下明白了吧？
     *  具体来说，Porter.Mode,一共有17个，可以分两类：
     *  1.Alpha合成(Alpha Compositing)
     *  2.混合(Blending)
     *
     *  第一类，Alpha合成，其实就是「PorterDuff」这个词所指代的算法。
     *  「PorterDuff」并不是一个具有实际意义的词组，而是两个人的名字(准确讲是姓)。
     *  这两个人当年共同发表了一篇论文，描述了12种将两个图像共同绘制的操作(即算法)。
     *  而这篇论文所论述的操作，都是关于Alpha通道(也就是我们 通俗理解的「透明度」)的计算的，
     *  后来人们就把这类计算称为Alpha合成(Alpha Compositing)。
     *
     *  https://developer.android.com/reference/android/graphics/PorterDuff.Mode.html
     *
     *  SRC(Source)
     *  DST(Destination)
     *
     *  第二类，混合，也就是Photoshop等制图软件里都有的那些混合模式（multiply darken lighten 之类的)。
     *  这一类操作的是颜色本身，而不是Alpha通道，并不属于Alpha合成，
     *  所以和Porter与Duff这两个人也没什么关系，
     *  不过为了使用的方便，它们同样也被Google加进了PorterDuff.Mode里。
     *
     *  结论：
     *  从效果图可以看出，
     *
     *  Alpha合成类的效果都比较直观，
     *  基本上可以使用简单的口头表述来描述 它们的算法(起码对于不透明的源图像和目标图像来说是可以的)，
     *  例如SRC_OVER表示「二者都绘制，但要源图像放在目标图像的上面」，
     *       DST_IN表示「只绘制目标图像，并且只绘制它和源图像重合的区域」。
     *
     *  而混合类的效果就相对抽象一些，
     *  只从效果图不太能看得出它们的着色算法，更看不出来它们有什么用。
     *  不过没关系，你如果拿着这些名字去问你司的设计师，他们八成都能给你说出个123。
     *
     *  所以对于这些Mode，正确的做法是：
     *  对于Alpha合成类的操作，掌握他们，并在实际开发中灵活运用；
     *  而对于混合类的，你只要把它们的名字记住就好了，这样当某一天设计师告诉你
     *  「我要做这种混合效果」的时候，你可以马上知道自己能不能做，怎么做。
     *
     *  另外，PorterDuff.Mode建议你动手用一下试试，对加深理解有帮助。
     *
     * 好了，  这些就是几个Shader的具体介绍。
     * 除了使用setColor/ARGB() 和 setShader()来设置基本颜色，
     * Paint还可以来设置ColorFilter，来对颜色进行第二层处理。
     *
     */

    //第一个Shader ： 头像的Bitmap
    private val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.batman)
    private val shader1 = BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

    //第二个Shader ：从上到下的线性渐变（由透明到黑色）
    private val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.batman_logo)
    private val shader2 = BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

    //ComposeShader : 结合两个Shader
//    private val shader = ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER)
//    private val shader = ComposeShader(shader1, shader2, PorterDuff.Mode.DST_OUT)
//    private val shader = ComposeShader(shader1, shader2, PorterDuff.Mode.DST_IN)
//    private val shader = ComposeShader(shader1, shader2, PorterDuff.Mode.LIGHTEN)

    /**
     * setColorFilter(ColorFilter colorFilter)
     *
     * ColorFilter这个类，它的名字已经足够解释它的作用：为绘制设置颜色过滤。
     * 颜色过滤的意思，就是为绘制的内容设置一个统一的过滤策略，
     * 然后Canvas.drawXXX()方法会对每个像素都进行过滤后再绘制出来。
     * 举几个现实中比较常见的颜色过滤的例子：
     * 1. 有色光照射；
     * 2. 有色玻璃透视；
     * 3. 胶卷。
     *
     * 在Paint里设置ColorFilter，
     * 使用的是Paint.setColorFilter(ColorFilter filter)方法。
     * ColorFilter并不直接使用，而是使用它的子类。
     *
     * 它共有三个子类：
     * LightingColorFilter
     * PorterDuffColorFilter
     * ColorMatrixColorFilter
     *
     *
     * LightingColorFilter
     * 这个LightingColorFilter是用来模拟简单的光照效果的。
     * LightingColorFilter是的构造方法是 LightingColorFilter(@ColorInt int mul, @ColorInt int add),
     * 参数里的mul和add都是和颜色值格式相同的int值，
     * 其中mul用来和目标像素相乘，
     *    add用来和目标像素相加：
     *
     * PorterDuffColorFilter
     * 这个PorterDuffColorFilter的作用
     * 是使用一个指定的颜色和一种指定的PorterDuff.Mode来与绘制对象进行合成。
     * 它的构造方法是 PorterDuffColorFilter(@ColorInt int color, @NonNull PorterDuff.Mode mode)
     * 其中color参数是指定的颜色，
     *     mode参数是指定的Mode。
     * 同样也是PorterDuff.Mode,不过和ComposeShader不同的是，
     * PorterDuffColorFilter作为一个ColorFilter,只能指定一种颜色作为源，而不是一个Bitmap。
     *
     *
     * ColorMatrixColorFilter
     * 这个就厉害了。
     * ColorMatrixColorFilter使用一个ColorMatrix来对颜色进行处理。
     * ColorMatrix这个类，内部是一个4x5 的矩阵：
     *  [ a, b, c, d, e,
     *    f, g, h, i, j,
     *    k, l, m, n, o,
     *    p, q, r, s, t ]
     *
     * 以上，就是Paint对颜色的第二层处理：
     * 通过setColorFilter(colorFilter)来加工颜色。
     *
     * 除了基本颜色的设置(setColor/ARGB(),setShader())
     * 以及基于原始颜色的过滤(setColorFilter())之外，
     * Paint最后一层处理颜色的方法是setXfermode(Xfermode xfermode),
     * 它处理的是「当颜色遇上View」的问题。
     *
     *
     */

    private val lightingColorFilter = LightingColorFilter(0x00ffff, 0x000000)
    private val porterDuffColorFilter = PorterDuffColorFilter(0x00ffff, PorterDuff.Mode.SRC)


    /**
     * setXfermode(Xfermode xfermode)
     *
     * "Xfermode"其实就是"Transfer mode",
     * 用"X"来代替"Trans"是一些美国人喜欢用的简写方式。
     * 严谨地讲，Xfermode指的是你要绘制的内容和Canvas的目标位置的内容应该怎样结合计算出最终的颜色。
     * 但通俗地说，
     * 其实就是要你以绘制的内容作为源图像，以View中已有的内容作为目标图像，
     * 选取一个PorterDuff.Mode作为绘制内容的颜色处理方案。
     *
     */
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    private val rectBitmap = BitmapFactory.decodeResource(resources, R.drawable.rect_bitmap)
    private val circleBitmap = BitmapFactory.decodeResource(resources, R.drawable.circle_bitmap)


    /**
     *
     * setPathEffect(PathEffect effect)
     *
     * 使用PathEffect来给图形的轮廓设置效果。
     * 对Canvas所有的图形绘制有效，
     * 也就是 drawLine()、drawCircle()、drawPath()这些方法
     *
     * 下面具体说一下Android中的6种PathEffect
     * PathEffect分为两类，
     * 单一效果的CornerPathEffect、DiscretePathEffect、DashPathEffect、PathDashPathEffect
     * 和组合效果的SumPathEffect、ComposePathEffect
     *
     */

    private var pathEffect = DashPathEffect(floatArrayOf(50f, 5f), 0f)


    private val paint: Paint = Paint()


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
//        paint.shader = shader
        paint.isAntiAlias = true

        paint.style = Paint.Style.STROKE
        paint.pathEffect = pathEffect
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


//        canvas?.drawRect(100f, 100f, 900f, 600f, paint)

//        canvas?.drawRect(0f, 0f, 900f, 1400f, paint)
//        canvas?.drawCircle(300f, 300f, 200f, paint)


        // 别忘了用 canvas.saveLayer() 开启 off-screen buffer
//        canvas?.saveLayer(null, null, Canvas.ALL_SAVE_FLAG)
//        setLayerType(LAYER_TYPE_SOFTWARE, paint)
//
//        canvas?.drawBitmap(rectBitmap, 0f, 0f, paint)// 画方
//        //Use of setter method instead of property access syntax
//        paint.xfermode = xfermode// 设置 Xfermode
//        //None of the following functions can be called with the arguments supplied.
//        canvas?.drawBitmap(circleBitmap, 0f, 0f, paint) // 画圆
//        paint.xfermode = null // 用完及时清除 Xfermode


        canvas?.drawCircle(300f, 300f, 200f, paint)

    }
}