# Android TV 开发框架


## 整体目录结构

*AndroidTvWidet 目录是TV开发框架的 库文件.

*demo 目录是 例子.

*doc 目录是存放文档的.

*other_libs 目录是 AndroidTvWidget开发框架 依赖的一些库文件.

*Tool 目录 是 屏幕像素转换工具.

## AndroidTVwidget库目录结构

![框架目录结构](http://git.oschina.net/uploads/images/2016/0505/155151_939fc32a_111902.png "在这里输入图片标题")

com.open.androidtvwidget.adapter : 标题栏的adapter基类.

com.open......bridge : MainUpView的依赖类，比如移动的动画都在这里实现的

com.open.....cache: 缓存

com.open....keyboard: 键盘

com.open....menu : 菜单

com.open.android...recycle : recyclerview 的支持（gridview)

com.open.android...utils:一些常用函数封装

com.open...android..view:一些TV改造的控件.(下面我们将接受这里面的控件使用方法)


## 版本更新

v1.4.0 

*添加recyclerview的支持

*修复gridviewTv 更新数据崩溃

*修改GridViewTV demo

*修复键盘DEMO 崩溃

*倒影控件添加间距接口

*整理移动动画的函数