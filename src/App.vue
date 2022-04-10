<template>
  <div id="app">
    <keep-alive>
      <router-view />
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: "app",
  components: {},
  created() {
    if (sessionStorage.getItem("routes")) {
      let routes = JSON.parse(sessionStorage.getItem("routes"));
      routes.forEach((item) => {
        //会话中没有存储component，需要添加
        item.component = () =>
          import("@/views/" + item.name + "/" + item.name + ".vue");
        this.$router.addRoute("backStage", item);
      });
      //这里应该根据当前页刷新的路由
      let active = sessionStorage.getItem("active");
      if (active) {
        this.$router.push(active);
      } else {
        this.$router.push("/census");
      }
    }
  },
};
</script>
<style>
@import "assets/css/base.css";
</style>
