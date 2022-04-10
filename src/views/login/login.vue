<template>
  <div class="login">
    <header><topinfo /></header>
    <div class="form">
      <el-form
        :model="form"
        status-icon
        :rules="rules"
        ref="form"
        label-width="login-container"
        label-position="right"
      >
        <h3 class="login_title">管理员登录</h3>
        <el-form-item
          label="用户名"
          label-width="80px"
          prop="username"
          class="username"
        >
          <el-input
            type="input"
            v-model="form.username"
            autocomplete="off"
            placeholder="请输入账号"
          ></el-input
        ></el-form-item>
        <el-form-item
          label="密码"
          label-width="80px"
          prop="password"
          class="password"
        >
          <el-input
            type="password"
            v-model="form.password"
            autocomplete="off"
            placeholder="请输入密码"
          ></el-input
        ></el-form-item>
        <el-form-item class="login_in">
          <el-button class="login_x" type="primary" @click="login"
            >LOGIN</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { RootLogin } from "@/network/root.js";
import postData from "@/utils/qs.js";
import topinfo from "@/components/private/topinfo/topinfo.vue";
export default {
  name: "login",
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
      },
    };
  },
  components: { topinfo },
  methods: {
    login() {
      let username = this.form.username;
      let password = this.form.password;
      if (username === "" || password === "") {
        this.$message.warning("请输入信息");
      } else {
        let root = { username, password };
        RootLogin(postData(root)).then((res) => {
          let obj = res.data;
          if (obj.length === 0) {
            this.$message.warning("登陆失败！");
            this.form.username = "";
            this.form.password = "";
          } else {
            this.form.username = "";
            this.form.password = "";
            //用户存在，开始动态添加路由，并跳转页面
            this.$nextTick(() => {
              this.$store.commit("addRoute", this.$router);
              this.$router.push("/census");
            });
          }
        });
      }
    },
  },
};
</script>
<style scoped>
header {
  position: relative;
  width: 100%;
  height: 0.4167rem;
  background-color: #333;
}
.form {
  border-radius: 0.0781rem;
  width: 2.8646rem;
  height: 2.0833rem;
  margin: 1.0417rem auto;
  background-color: #fff;
  border: 0.0104rem solid #eaeaea;
  box-shadow: 0 0 0.1302rem #cac6c6;
}

.el-form {
  position: relative;
  margin: 0 auto;
}
.login_title {
  margin: 0.1042rem auto;
  text-align: center;
  color: #505458;
  height: 0.2083rem;
  font-size: 0.1042rem;
}
.el-form-item {
  width: 90%;
  margin: 0.2083rem auto;
  font-size: 0.099rem;
}
.el-input {
  width: 90%;
}
.login_x {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
</style>
