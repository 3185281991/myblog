<template>
  <div class="backTop" @click="scrollTOTop">
    <span class="iconfont icon-huidaodingbu" title="返回顶部"></span>
  </div>
</template>

<script>
import { scrollToTop } from "@/utils/scroll.js";
export default {
  name: "backtop",
  data() {
    return {};
  },
  components: {},
  methods: {
    scrollTOTop() {
      scrollToTop();
    },
  },
};
</script>
<style scoped>
.backTop {
  z-index: 10;
  width: 0.3125rem;
  height: 0.3125rem;
  position: fixed;
  right: 0.4167rem;
  bottom: 0.7292rem;
}
span {
  display: block;
  font-size: 0.3125rem;
  color: #aaa;
}
span:hover {
  color: rgb(87, 73, 68);
}
</style>
