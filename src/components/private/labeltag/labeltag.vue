<template>
  <div class="tag">
    <div><span class="iconfont icon-biaoqian"></span><span>所有标签</span></div>
    <div>
      <span v-for="item in tags" :key="item" @click="down(item)">{{
        item
      }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "labeltag",
  data() {
    return {};
  },
  props: {
    tags: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  components: {},
  methods: {
    down(item) {
      this.$router.push("/search/" + item);
      this.$bus.$emit("isLeave", "true");
    },
  },
};
</script>
<style scoped>
.tag {
  width: 100%;
  background-color: white;
  overflow: hidden;
}
.tag div:first-child {
  width: 100%;
  height: 0.3125rem;
  float: left;
  position: relative;
}
.tag div:first-child span:first-child {
  position: absolute;
  font-size: 0.1563rem;
  margin-top: 0.0677rem;
  padding-left: 0.0521rem;
}
.tag div:first-child span:last-child {
  position: absolute;
  font-size: 0.1146rem;
  top: 0.0781rem;
  left: 0.2604rem;
}
.tag div:last-child {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  padding: 0.0521rem 0.026rem;
  border-top: #ffbcb5 0.0104rem solid;
}
.tag div:last-child span {
  display: inline-block;
  padding: 0.0104rem 0.026rem;
  margin-left: 0.0104rem;
  margin-bottom: 0.026rem;
  border: 1px solid #e1e1e1;
  font-size: 0.0938rem;
}
.tag div:last-child span:hover {
  cursor: pointer;
  color: #ff4d3a;
}
</style>
