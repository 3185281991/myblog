// import Vue from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
Vue.use(VueRouter);
//全局插件
import toast from "components/common/toast/index";
Vue.use(toast);

//事件总线
Vue.prototype.$bus = new Vue({});

Vue.config.productionTip = false;
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
import "amfe-flexible";

import "swiper/css/swiper.min.css";
import "@/assets/css/iconfont.css";
