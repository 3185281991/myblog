## pc 适配

1、首先安装两个插件：postcss-px2rem，amfe-flexible

2、在 vue.config.js 中

```css: {
    loaderOptions: {
      css: {},
      postcss: {
        plugins: [
          require("postcss-px2rem")({
            remUnit: 192, //根据设计图
            //375的图就给37.5，也就是1rem=37.5px
          }),
        ],
      },
    },
  },
```

3、然后在 main.js 中引入 amfe-flexible
