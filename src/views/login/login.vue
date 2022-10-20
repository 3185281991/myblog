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
          <el-button type="primary" @click="login">LOGIN</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { RootLogin, AutoLogin } from "@/network/root.js";
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
  created() {
    this.autoLogin();
  },
  methods: {
    autoLogin() {
      let token = localStorage.getItem("token") || "";
      let expiresTime = localStorage.getItem("expiresToken") || "";
      let nowTime = Date.now();
      //判断是否过期
      if (expiresTime) {
        if (nowTime - expiresTime > 3 * 60 * 60 * 24 * 1000 - 10) {
          localStorage.removeItem("token");
          localStorage.removeItem("expiresToken");
          this.$message.error("身份信息已过期，请登录！");
        } else {
          if (token) {
            AutoLogin().then((res) => {
              this.$message.success(`${res.data}`);
              this.$store.commit("addRoute", this.$router);
              this.$router.push("/census");
            });
          }
        }
      }
    },
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
            //用户存在，保存token，开始动态添加路由，并跳转页面,默认有效七天
            let time = Date.now();
            localStorage.setItem("token", obj[1]);
            localStorage.setItem("expiresToken", time);
            this.$store.commit("addRoute", this.$router);
            this.$router.push("/census");
          }
        });
      }
    },
  },
};
</script>
<style scoped>
.login {
  background: url("~@/assets/img/bgc.jpg") center center;
  background-size: cover;
  width: 100%;
  height: 100vh;
}
header {
  position: relative;
  width: 100%;
  height: 0.4167rem;
  background-color: #333;
}
.form {
  position: absolute;
  top: 50%;
  left: 50%;
  border-radius: 0.0781rem;
  min-width: 2.6042rem;
  min-height: 1.8229rem;
  transform: translate(-50%, -50%);
  background-color: #fff;
  border: 0.0104rem solid #eaeaea;
  box-shadow: 0 0 0.1302rem #cac6c6;
  opacity: 0.9;
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
.el-form-item:nth-child(4) {
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-input {
  width: 90%;
}
</style>
