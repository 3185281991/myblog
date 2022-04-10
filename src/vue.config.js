const path = require("path"); //引入path模块
function resolve(dir) {
  return path.join(__dirname, dir); //path.join(__dirname)设置绝对路径
}
module.exports = {
  chainWebpack: (config) => {
    config.resolve.alias
      //set第一个参数：设置的别名，第二个参数：设置的路径
      .set("@", resolve("./src"))

      .set("components", resolve("./src/components"))

      .set("assets", resolve("./src/assets"))

      .set("views", resolve("./src/views"))

      .set("img", resolve("./src/assets/img"))

      .set("store", resolve("./src/store"))

      .set("network", resolve("./src/network"));
  },
  css: {
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
  configureWebpack: {
    //这里是添加的部分
    externals: {
      vue: "Vue",
      "vue-router": "VueRouter",
      "element-ui": "ElementUI",
      vuex: "Vuex",
      axios: "axios",
      echarts: "echarts",
      Swiper: "Swiper",
    },
  },
};
