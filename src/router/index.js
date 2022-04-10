// import Vue from "vue";
// import VueRouter from "vue-router";
// Vue.use(VueRouter);
const originalPush = VueRouter.prototype.push;

//解决重定向报错
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

const routes = [
  {
    path: "",
    redirect: "/login",
  },
  {
    name: "login",
    path: "/login",
    component: () => import("@/views/login/login.vue"),
    meta: {
      title: "管理员登录",
    },
  },
  {
    name: "backStage",
    path: "/backStage",
    component: () => import("@/views/manage/manage.vue"),
    meta: {
      title: "博客后台",
    },
    children: [
      {
        name: "census",
        path: "/census",
        component: () => import("@/views/census/census.vue"),
        meta: {
          title: "数据统计",
        },
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  routes,
});
//全局导航守卫更改标题显示
router.beforeEach((to, from, next) => {
  document.title = to.meta.title;
  //每次进入路由后，需要保存这个路由信息，以便再刷新的时候跳转到这个路由
  sessionStorage.setItem("active", to.path);
  //当路径变为login时，不能返回，清空数据
  return next();
});

export default router;
