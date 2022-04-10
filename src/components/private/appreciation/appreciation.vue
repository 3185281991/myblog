<template>
  <div class="appreciation">
    <div class="atop"><span>赞赏(感谢)</span></div>
    <div class="aun">
      <p>欢迎大家访问我的博客工作站，喜欢的话也可以简单赞赏一下。</p>
      <img src="@/assets/img/pay.jpg" alt="" />
    </div>
  </div>
</template>

<script>
export default {
  name: "appreciation",
  data() {
    return {};
  },
  components: {},
  methods: {},
};
</script>
<style scoped>
.appreciation {
  width: 100%;
  height: 1.875rem;
  background-color: #fff;
}
.atop {
  width: 100%;
  height: 16%;
}
.atop span {
  display: block;
  font-size: 0.1146rem;
  margin-left: 0.1042rem;
  padding-top: 0.0781rem;
}
.aun {
  border-top: #ffbcb5 0.0104rem solid;
  width: 100%;
  height: 84%;
}
.aun p {
  display: block;
  margin: 0.0521rem auto;
  font-size: 0.1042rem;
  width: 90%;
  line-height: 1.5em;
}
.aun img {
  display: block;
  width: 1.0417rem;
  height: 1.0417rem;
  margin-left: 29%;
}
</style>
