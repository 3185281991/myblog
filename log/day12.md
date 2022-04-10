## 第十二天

今天发现了一些小 bug，比如页面上的图片显示问题，
但没什么大碍，把大概的功能都测了一遍，都还好
显示的内容直接绑定 v-html，这样方便换行对其以及样式改变等，
关于缩进的话就要通过文本框下标位置进行插入数据即可

```
      if (e.ctrlKey && e.keyCode == 13) {
        //用户点击了ctrl+enter触发,拿到光标位置
        let cursorIndex = e.srcElement.selectionStart;
        let s1 = "";
        let s2 = "";
        if (this.blog.content.length < cursorIndex) {
          this.blog.content =
            this.blog.content + "</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        } else {
          s1 = this.blog.content.toString();
          s2 = this.blog.content.toString();
          let pre =
            s1.substring(0, cursorIndex) +
            "</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
          let end = s2.substring(cursorIndex, this.blog.content.length);
          this.blog.content = pre + end;

```

## 关于服务器

今天把服务器的 mysql 以及 ngnix 都配好了，以及云链接数据库也行了

## 明天

明天把首屏进行优化，好像有什么 cdn 引入，gzip 压缩，以及插件的需求引入
还要把数据库统统配好即可，项目上线服务器
