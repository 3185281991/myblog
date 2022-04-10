export default {
  //添加管理路由
  addRoute(state, router) {
    let routes = [];
    let route = [
      {
        name: "write",
        path: "/write",
        meta: {
          title: "我的写作",
        },
      },
      {
        name: "operate",
        path: "/operate",
        meta: {
          title: "博客操作",
        },
      },
      {
        name: "allblog",
        path: "/allblog",
        meta: {
          title: "博客清单",
        },
      },
      {
        name: "concrete",
        path: "/concrete/:bid",
        meta: {
          title: "文章信息",
        },
      },
    ];
    route.forEach((item) => {
      item.component = () =>
        import("@/views/" + item.name + "/" + item.name + ".vue");
      routes.push(item);
    });
    routes.forEach((item) => {
      router.addRoute("backStage", item);
    });
    state.rootRoute = routes;
    //这里component无法加入
    sessionStorage.setItem("routes", JSON.stringify(routes));
  },
};
