![](images/icon.png)

学习如何进行裁剪，以及移动，缩放
matrix   camera的简单操作。

- 注意一下  这2行实现的效果一样，但是参数是反的！！！！！！！！！！！！！！
```
canvas.translate(centerX , centerY);
camera.applyToCanvas(canvas);
canvas.translate(-centerX , -centerY);

matrix.preTranslate(-centerX , -centerY);
matrix.postTranslate(centerX , centerY);
```
HenCoder 绘制 4 练习项目
===

### 这是什么？

这不是一个独立使用的项目，它是 [HenCoder Android 开发进阶：UI 1-4 绘制的辅助](http://hencoder.com/ui-1-4) 的配套练习项目。

### 怎么用？

项目是一个可以直接运行的 Android App 项目，项目运行后，在手机上打开是这样的：

![](images/preview.png)

工程下有一个 `/practice` 目录：

![](images/project_practice.png)

你要做的是就是，在 `/practice` 下的每一个 `PracticeXxxView.java` 文件中写代码，绘制出和页面上半部分相同的效果。例如写 `Practice01ClipRectView.java` 以绘制出经过平移的图形。就像这样：

![](images/preview_after.png)

> 当然，没必要做得和示例一毛一样。这是一个练习，而不是一个超级模仿秀，关键是把技能掌握。

如果做不出来，可以参考 `/sample` 目录下的代码：

![](images/project_sample.png)

练习做完，绘制第四期（绘制的辅助）的内容也就掌握得差不多了。

