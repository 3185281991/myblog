<template>
  <div class="backStage">
    <header><topinfo /></header>
    <div class="container">
      <aside><navside /></aside>
      <main>
        <keep-alive exclude="census,concrete,operate">
          <router-view />
        </keep-alive>
      </main>
    </div>
  </div>
</template>

<script>
import navside from "@/components/private/navside/navside.vue";
import topinfo from "@/components/private/topinfo/topinfo.vue";
export default {
  name: "backStage",
  data() {
    return {};
  },
  components: { topinfo, navside },
  methods: {},
  created() {},
};
</script>
<style scoped>
.backStage {
  background-color: #f3f4f7;
  width: 100vw;
  height: 100vh;
}
header {
  width: 100%;
  height: 0.4167rem;
  background-color: #333;
  position: fixed;
  top: 0;
  z-index: 10;
}
.container {
  margin-top: 0.4167rem;
  width: 100%;
  height: 100%;
}
aside {
  width: 18%;
  height: 92%;
  float: left;
  position: fixed;
  top: 0.4167rem;
  background-color: #545c64;
}
main {
  width: 81%;
  background-color: #f3f4f7;
  float: right;
}
</style>
