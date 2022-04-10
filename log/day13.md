## 第十三天

### 使用插件分析打包文件大小

··· npm install --save-dev webpack-bundle-analyzer //npm 安装···
在配置文件 vue。configjs 中添加配置项

```
chainWebpack:(config)=>{}
    //打包分析工具
    if (process.env.NODE_ENV === "production") {
      if (process.env.npm_config_report) {
        config
          .plugin("webpack-bundle-analyzer")
          .use(require("webpack-bundle-analyzer").BundleAnalyzerPlugin)
          .end();
        config.plugins.delete("prefetch");
      }
    }
```

打包后会在 8888 端口显示对应打包文件分类大小

项目使用 cdn 资源，打包能够减小 60 多 kb

### 在 public 中的 index.html 中引入对应资源

```
  <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/5.4.5/css/swiper.min.css">
  <script src="https://cdn.jsdelivr.net/npm/vue-router@3.5.3/dist/vue-router.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/vue@2.6.10/dist/vue.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/vuex@3.0.1/dist/vuex.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/axios@0.26.1/dist/axios.min.js"></script>
  <script src="https://unpkg.com/element-ui/lib/index.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/echarts@5.3.2/dist/echarts.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/5.4.5/js/swiper.js"></script>
```

然后在 vue.config.js 中添加配置项

```
  configureWebpack: {
    //这里是添加的部分
    externals: {
      vue: "Vue",
      "vue-router": "VueRouter",
      vuex: "Vuex",
      axios: "axios",
      echarts: "echarts",
      Swiper: "Swiper",
    },
  },
```

前面的 key 对应的是模块名，后面的是在项目中引入的名称
进入之前使用了以上模块的文件中，即 store=》index.js,router=>index.js，main=》index.js,network=>index.js 中把里面的 import 导入的模块统统注释，以及 vue.use(),剩下把里面的使用到的模块名全部都改为`配置文件中对应的 value 名`，
`element`之前必须先引入 vue，同样在 main.js 中去掉对应模块引入，这里出现了一个小问题我没把 vue 注释掉导致报错
`swiper和echarts`的话到官网中找到引入方法，然后将封装组件中对应的 import 引入实例对象给注释掉，这里调用的实例名必须是配置中的 value 名

最后最重要的

```
// import Vue from "vue";
// import Vuex from "vuex";
```

在 main.js`中必须引入路由文件对象，否则路由会失效

```
Vue.use(VueRouter);
```

否则页面会空白

## 开启 gzip 压缩

安装压缩依赖,指定 5 版本，不然会报错

```
npm install compression-webpack-plugin@5 --save-dev

```

在 vue.config.js 中配置

```
const CompressionPlugin = require("compression-webpack-plugin");

   configureWebpack: {
       plugins: [
      new CompressionPlugin({
        algorithm: "gzip", // 使用gzip压缩
        test: /\.js$|\.html$|\.css$/, // 匹配文件名
        filename: "[path].gz[query]", // 压缩后的文件名(保持原文件名，后缀加.gz)
        minRatio: 0.8, // 压缩率小于1才会压缩
        threshold: 10240, // 对超过10k的数据压缩
        deleteOriginalAssets: false, // 是否删除未压缩的源文件，谨慎设置，如果希望提供非gzip的资源，可不设置或者设置为false（比如删除打包后的gz后还可以加载到原始资源文件）
      }),
    ],
   }
```

再次打包后完成压缩

### 也可以使用服务器进行压缩 nginx

在配置文件 htpp 中添加

```
 # 开启gzip
    gzip  on;

    # 设置缓冲区大小
    gzip_buffers 4 16k;

    #压缩级别官网建议是6
    gzip_comp_level 6;

    #压缩的类型
    gzip_types text/plain application/javascript text/css application/xml text/javascript application/x-httpd-php;
```

响应头中会携带 gzip 压缩配置，每次请求 xx.js 文件，服务器都会进行实时压缩。

1、webpack 打包，然后直接使用静态的 gz，缺点就是打包后文件体积太大，但是不耗服务器性能；
2、使用 nginx 在线 gzip，缺点就是耗性能，需要实时压缩，但是 vue 打包后的文件体积小。

在有 gz 文件的时候进行静态压缩，不存在 gz 文件的时候进行在线压缩而不是加载源文件

```
gzip on;
gzip_static on;
gzip_comp_level 2;
gzip_types text/plain text/html text/css application/x-javascript text/xml application/xml application/xml+rss text/javascript;
```

响应头的 Content-Edcoding:gzip 表示 gzip 压缩已经生效，而 Etag 中只有简单字符表示静态资源加载，而前面带 W/ 表示启动了在线压缩

## nginx 多端口配置

```
 server {
        listen       81;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
        location /{
                root html/backstage;
                try_files $uri $uri/  @router;
                index index.html index.htm;
        }
        location @router {
                rewrite ^.*$ /index.html last;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
```

只需要配置多个 serve 即可
