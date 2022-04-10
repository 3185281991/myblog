## 第六天，

今天把留言板简单做了一下，基本就是将评论回复部分的组件复用一下，
同时这个留言板的信息是绑定在博客信息的 bid=1 的信息中，可能比较简陋，这种情况下也涉及大量的方法变量的重复，就是用混入`mixins`

---

此外还把导航栏中点击是以及点击其他页面时关于样式是否改变，、
使用事件总线来解决，就是在改变的时候提交事件并传递一个参数，之后在传递给导航栏中事件接受

```
  //事件总线
 Vue.prototype.$bus = new Vue({});

 mounted() {
    //事件总线接收
    this.$bus.$on("isLeave", (msg) => {
      if (msg === "true") {
        //改变导航栏下标
        this.activeIndex = null;
      }
    });
  },
}
```

```
     this.$bus.$emit("isLeave", "true");
      sessionStorage.setItem("isLeave", "true");
```

这种方式比放在 vuex 中方便多了

## 关于提交评价回复跳转到回复第一个

试了一下，好像搞不出来本来是封装一个回滚函数，
但是发现不同浏览器一刷新页面就回到了顶部

```
网页可见区域宽： document.body.clientWidth
网页可见区域高： document.body.clientHeight
网页可见区域宽： document.body.offsetWidth (包括边线的宽)
网页可见区域高： document.body.offsetHeight (包括边线的高)
网页正文全文宽： document.body.scrollWidth
网页正文全文高： document.body.scrollHeight
网页被卷去的高： document.body.scrollTop
网页被卷去的左： document.body.scrollLeft
网页正文部分上： window.screenTop
网页正文部分左： window.screenLeft
屏幕分辨率的高： window.screen.height
屏幕分辨率的宽： window.screen.width
屏幕可用工作区高度： window.screen.availHeight
屏幕可用工作区宽度： window.screen.availWidth
```

而且好像一旦添加了回复，重刷高度之后，我那个或得到的值还是没有变化
