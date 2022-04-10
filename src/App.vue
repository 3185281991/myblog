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
    this.$store.commit("defaultInfo");
  },
};
</script>

<style>
@import "assets/css/base.css";
</style>
