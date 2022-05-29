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

  data() {
    return {};
  },
  components: {},
  created() {
    let time = Date.now();
    let expires = localStorage.getItem("basicTime");
    if (time - expires > 2 * 60 * 60 * 24) {
      localStorage.removeItem("useanme");
      localStorage.removeItem("email");
    }
    this.$store.commit("defaultInfo");
  },
};
</script>

<style>
@import "assets/css/base.css";
</style>
