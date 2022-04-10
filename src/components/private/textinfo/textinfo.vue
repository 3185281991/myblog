<template>
  <div class="text">
    <a href="javascript:void(0);">
      <span>A Station For My Blog </span>
      <span>一个简单记录我的个人成长的工作站</span>
    </a>
  </div>
</template>

<script>
export default {
  name: "textinfo",
  data() {
    return {};
  },
  components: {},
  methods: {},
};
</script>
<style scoped>
.text {
  width: 3.3854rem;
  height: 0.3125rem;
  position: absolute;
  left: 1.0417rem;
  top: 50%;
  transform: translateY(-50%);
}
a {
  display: block;
  width: 100%;
  height: 100%;
}
a:hover {
  cursor: pointer;
}
span:first-child {
  float: left;
  font-size: 0.125rem;
  line-height: 100%;
  display: block;
  padding-top: 0.0885rem;
  color: white;
}
span:first-child:hover {
  color: coral;
}
span:last-child {
  font-size: 0.0885rem;
  color: #dfdfdf;
  float: left;
  display: block;
  padding-top: 0.1094rem;
  margin-left: 0.1302rem;
}
</style>
