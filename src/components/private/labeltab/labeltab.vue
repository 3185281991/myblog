<template>
  <div class="labeltab">
    <div class="content">
      <span>{{ $route.meta.info }}</span>
      <p>{{ $route.meta.label }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "labeltab",
  data() {
    return {
      label: "",
      category: "",
    };
  },
  components: {},
  methods: {},
};
</script>
<style scoped>
.labeltab {
  width: 100%;
  height: 0.7813rem;
  background-color: #fff;
}
.content {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  position: relative;
}
span {
  display: block;
  font-size: 0.1458rem;
  font-weight: bold;
  position: absolute;
  top: 20%;
  left: 2%;
}
p {
  display: block;
  font-size: 0.1042rem;
  position: absolute;
  top: 60%;
  left: 2%;
}
</style>
