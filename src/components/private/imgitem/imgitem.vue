<template>
  <div class="imgitem">
    <div><img :src="item.imgPath" alt="" /></div>
    <div class="info1">
      <span>编号: {{ item.id }}</span>
    </div>
    <div class="info2">
      <span>路径: {{ item.imgPath }}</span>
    </div>
    <span class="del" @click="delImg(item.id)">删除</span>
  </div>
</template>

<script>
export default {
  name: "imgitem",
  data() {
    return {};
  },
  props: {
    item: {
      type: Object,
      default() {
        return {};
      },
    },
  },
  components: {},
  methods: {
    delImg(id) {
      this.$emit("deleteImg", id);
    },
  },
};
</script>
<style scoped>
.imgitem {
  width: 58%;
  margin: 0 auto;
  background-color: #f3f4f7;
  height: 1.3021rem;
  position: relative;
}
.imgitem div:first-child {
  width: 100%;
  height: 100%;
}
.imgitem div:first-child img {
  min-width: 100%;
  max-height: 100%;
}
.imgitem .info1 {
  width: 20%;
  position: absolute;
  top: 2%;
  left: 2%;
  color: white;
}
.imgitem .info2 {
  width: 95%;
  position: absolute;
  top: 15%;
  left: 2%;
  color: white;
}
span.del {
  position: absolute;
  background-color: rgba(255, 255, 255, 0.1);
  top: 70%;
  width: 40%;
  text-align: center;
  display: block;
  margin-left: 30%;
  margin-top: 0.0521rem;
  height: 0.1563rem;
  font-size: 0.1042rem;
  line-height: 0.1563rem;
  color: white;
}
span.del:hover {
  cursor: pointer;
  color: coral;
  background-color: rgba(255, 255, 255, 0.5);
}
</style>
