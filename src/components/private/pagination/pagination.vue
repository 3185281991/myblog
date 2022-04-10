<template>
  <div class="pagination">
    <div class="btn">
      <span @click="first">首页</span><span @click="prev"> 《《</span
      ><span
        >第 <strong>{{ pageNum }} </strong> 页</span
      >
      <span @click="next">》》</span><span @click="last">尾页</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "pagination",
  data() {
    return {};
  },
  props: {
    pageNum: {
      type: Number,
      default() {
        return 1;
      },
    },
  },
  components: {},
  methods: {
    prev() {
      this.$emit("prev");
    },
    next() {
      this.$emit("next");
    },
    first() {
      this.$emit("first");
    },
    last() {
      this.$emit("last");
    },
  },
};
</script>
<style scoped>
.pagination {
  width: 100%;
  height: 0.3125rem;
  text-align: center;
  position: relative;
}
.btn {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 50%;
  height: 60%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  border-radius: 0.0521rem;
  background-color: rgba(255, 255, 255, 0.8);
}
span {
  display: block;
  width: 19%;
  height: 100%;
  font-size: 0.099rem;
  margin-top: 0.0521rem;
}
span:nth-child(3) {
  letter-spacing: 0.1em;
  width: 24%;
}
span:hover {
  cursor: pointer;
  color: coral;
}
</style>
