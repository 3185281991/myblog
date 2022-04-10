// import Vue from "vue";
// import VueRouter from "vue-router";
// Vue.use(VueRouter);
import { scrollToTop } from "@/utils/scroll.js";
const originalPush = VueRouter.prototype.push;

//解决重定向报错
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

const routes = [
  { path: "", redirect: "/home" },
  {
    path: "/home",
    component: () => import("@/views/home/home.vue"),
    meta: {
      title: "个人博客首页",
    },
    children: [
      { path: "", redirect: "/index" },
      {
        name: "search",
        path: "/search/:search",
        component: () => import("@/views/search/search.vue"),
        meta: {
          title: "搜索结果",
        },
      },
      {
        name: "concrete",
        path: "/concrete/:bid",
        component: () => import("@/views/concrete/concrete.vue"),
        meta: {
          title: "文章信息",
        },
      },
      {
        name: "index",
        path: "/index",
        component: () => import("@/views/index/index.vue"),
        meta: {
          title: "首页",
        },
      },

      {
        name: "commonlife",
        path: "/commonlife",
        component: () => import("@/views/simple/simple.vue"),
        meta: {
          title: "生活日常",
          info: "分类名称: 生活日常",
          label: "这里包含了一些管我日常生活中发生的各类事情。",
        },
      },
      {
        name: "knowledge",
        path: "/knowledge",
        component: () => import("@/views/simple/simple.vue"),
        meta: {
          title: "技术知识",
          info: "分类名称: 技术知识",
          label: "记录接触各类新知识，以及我的想法。",
        },
      },
      {
        name: "work",
        path: "/work",
        component: () => import("@/views/simple/simple.vue"),
        meta: {
          title: "工作总结",
          info: "分类名称: 工作总结",
          label: "有关工作之中许许多多的事项和个人总结。",
        },
      },
      {
        name: "about",
        path: "/about",
        component: () => import("@/views/simple/simple.vue"),
        meta: {
          title: "个人经历",
          info: "分类名称: 经历感悟",
          label: "我们都将成长，关于自身经历的感悟。",
        },
      },
      {
        name: "messageBoard",
        path: "/messageBoard",
        component: () => import("@/views/messageboard/messageboard.vue"),
        meta: {
          title: "留言小板",
          info: "分类名称: 我的留言小板",
          label: "要是大家有什么表达的，就记录在这里吧。",
        },
      },
    ],
  },
];

const router = new VueRouter({
  routes,
  mode: "history",
});
//全局导航守卫更改标题显示
router.beforeEach((to, from, next) => {
  document.title = to.meta.title;
  return next();
});
//全局后置导航守卫，每次跳转后返回顶部
router.afterEach(() => {
  scrollToTop();
});

export default router;
