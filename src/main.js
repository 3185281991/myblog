// import Vue from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
// import VueRouter from "vue-router";
Vue.use(VueRouter);

// import ElementUI from "element-ui";
// Vue.use(ELEMENT);

//事件总线
Vue.prototype.$bus = new Vue({});

Vue.config.productionTip = false;
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
import "amfe-flexible";
// import "swiper/css/swiper.min.css";
import "@/assets/css/iconfont.css";
