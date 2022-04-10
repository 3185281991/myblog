<template>
  <div class="toast" v-show="isShow">
    <span>{{ message }}</span>
  </div>
</template>

<script>
export default {
  name: "toast",
  data() {
    return {
      message: "",
      isShow: false,
    };
  },
  // props: {
  //   message: { type: String, default: "" },
  //   isShow: { type: Boolean, default: false },
  // },
  methods: {
    show(message = "", duration = 2000) {
      this.isShow = true;
      this.message = message;
      setTimeout(() => {
        this.isShow = false;
        this.message = "";
      }, duration);
    },
  },
  components: {},
};
</script>
<style scoped>
.toast {
  z-index: 15;
  position: fixed;
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: 15px;
  width: 11%;
  left: 40%;
  top: 30%;
  height: 0.2083rem;
  transform: translate(-50%, -50%);
}

span {
  display: block;
  width: 80%;
  height: 30px;
  font-size: 14px;
  color: rgb(80, 243, 255);
  line-height: 30px;
  text-align: center;
  margin: 5px auto;
}
</style>
