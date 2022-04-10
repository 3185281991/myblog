## 第十四天

上午成功将项目在服务器上运行成功，基本操作都没出现大问题

针对 jar 报错，大问题是配置文件出错，一定要注意

```
mybatis:
  mapper-locations: classpath:Mapper/*Mapper.xml
  type-aliases-package: com.example.myitem.entity
```

本来本地我用的是小写 mapper/*Mapper.xml,这里改成大写 Mapper/*Mapper.xml,解决问题

### #运行 jar 包

1：java -jar 包名 ctrl c 终止
2：nohup java -jar 包名 & 一直运行

服务器需要开放对应的端口号以供外部访问

### 关于外部资源暴露

在 linux 上不同于 window 的是，

```
 registry.addResourceHandler("/myblogImg/**").addResourceLocations("file:/home/blogImage");

```

这里只需要改变实际存放路径，并且在添加图片文件的时候同样是路径，创建文件夹的方式相同 '/home/blogImage/swiper/'

此外在保存图片路径上必须使用服务器的地址 `img = img + "http://124.221.69.31:8888/myblogImg/" + fileName; `
然后通过路径就可以访问到服务器上的图片资源
springboot 项目中的 yml 文件中的 mysql 配置不需要改变，只要改变对应的数据库以及用户登录等信息,记得开方 3306 端口

```
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/blog
    username: root
    password: 147258369lyh.
```

## vue 项目打包

打包后请求数据都是请求服务器地址，必须将 axios 中的基准地址变为服务器地址，端口指向 jar 包中配资的端口 8888，默认前缀路径/myblog/
之后调用时使用对应的请求方法即可

```
   http://124.221.69.31:8888/myblog/
```

## 发现小 bug

之前评论以及回复会重刷页面，非常不友好，回想到没改善，调用事件总线解决，在添加完评论或者回复后的回调函数中触发
此外由于在调用请求所有评论时，我是通过遍历这个数组，依次针对评论信息再获得其回复信息的数组
之前的话是刷新，因此每次请求都会正确，但是我这里不刷新就必须保证每次都拿到回复信息而且拿到的数据必须是顺序的
然而在添加回复时会发生乱序，这里就要通过 peomise.all 方法解决,将每次请求的返回数据全部放入到一个数组中，通过 promise.ALL([]).then()
在其回调函数中遍历，这里拿到的数据就是顺序的，在依次放入到指定的回复数组中即可

```
        let rs = [];
        let data = [];
        for (let i = 0; i < this.comments.length; i++) {
          let comBid = this.comments[i].comBid;
          let cusName = this.comments[i].cusName;
          let email = this.comments[i].email;
          let comValue = this.comments[i].comValue;
          let comTime = this.comments[i].comTime;
          let obj = { comBid, cusName, email, comValue, comTime };
          //将得到的结果保存到一个数组中
          let result = getReviewsByEmail(postData(obj));
          data.push(result);
        }
        //这里拿到的回复必须是顺序的，不改动会乱序，通过peomise.all方法参数为一个数组，这里就会顺序拿到结果
        Promise.all(data).then((itemList) => {
          itemList.forEach((element) => {
            rs.push(element.data);
          });
        });
        this.reviews = rs;
```
