## springboot 搭建好后台访问 mysql 数据

基本的 entity，mapper，service controller 还有一个 xml 写 sql 语句

此外最最重要的应该是 `@CrossOrigin(origins = "\*", maxAge = 3600)`允许跨域访问端口

axios 拿到的数据貌似是通过使用 ResponseEntity，必须在请求点返回，通常在 spring rest 中实现。ResponseEntity 是通用类型，因此可以使用任意类型作为响应体：`

` return new ResponseEntity<>(result, HttpStatus.OK);`
直接 res.data 拿到

## 今天搞好了博客 index 页的布局以及基本数据测试，

做了一些基本数据测试，都是 ok 的，同时返回顶部，分页两个小部分也还是可以的，

本来想弄动态路由，数据放到 vuex 中，但是导航分类貌似会出现问题，还有分类目录文字说明也会因此出现毛病，
跳转二级路由时刷新的话，动态路由也会有毛病，直接空白，可能添加路由时机不对
首页 home 下 created 这里开始加的动态路由，能加，但刷新有毛病

导航分类以及分类文字的显示都是直接拿 router 进行操作

```
this.$router.options.routes[1].children.slice(3);
```

分页功能比较简单，只有上一页下一页两个按钮，也是通过页数和固定的每页显示的博客数量来控制

```
pageData.slice(start,end)
```
