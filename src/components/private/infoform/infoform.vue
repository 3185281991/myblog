<template>
  <div class="infoform">
    <el-col :span="8">
      <el-card class="box-card" shadow="hover" :body-style="{ height: '90%' }">
        <slot name="top">信息</slot>
        <el-form :model="form" :rules="rule" ref="form">
          <el-form-item prop="content">
            <el-input
              type="textarea"
              rows="8"
              style="overflow-y: scroll"
              v-model="form.content"
              placeholder="请输入需要添加的信息"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('form')"
              >立即添加</el-button
            >
            <el-button @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
  </div>
</template>

<script>
import {
  updateMessageBoard,
  addAdvertise,
  addImpassion,
} from "@/network/data.js";
import postData from "@/utils/qs.js";
export default {
  name: "inform",
  data() {
    return {
      form: { content: "" },
      rules1: {
        content: [
          { required: true, message: "请输入替换信息", trigger: "blur" },
          { min: 200, message: "最低200个字", trigger: "blur" },
        ],
      },
      rules2: {
        content: [
          { required: true, message: "请输入对应信息", trigger: "blur" },
          {
            min: 40,
            max: 140,
            message: "字符在40~~140之间。",
            trigger: "blur",
          },
        ],
      },
    };
  },
  props: {
    formType: {
      type: String,
      default() {
        return "";
      },
    },
  },
  computed: {
    rule() {
      if (this.formType !== "") {
        if (this.formType === "messageBoard") {
          return this.rules1;
        }
      }
      return this.rules2;
    },
  },
  components: {},
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.formType === "messageBoard") {
            let content = this.form.content;
            updateMessageBoard(postData({ content })).then((res) => {
              this.$message.success(res.data);
            });
          }
          if (this.formType === "impassion") {
            let text = this.form.content;
            addImpassion(postData({ text })).then((res) => {
              if (res.data === 1) {
                this.$message.success("成功添加每日一句。");
                this.$bus.$emit("impassion");
              } else {
                this.$message.warning("服务器错误。");
              }
            });
          }
          if (this.formType === "advertise") {
            let text = this.form.content;
            addAdvertise(postData({ text })).then((res) => {
              if (res.data === 1) {
                this.$message.success("成功添加介绍栏信息。");
                this.$bus.$emit("advertise");
              } else {
                this.$message.warning("服务器错误。");
              }
            });
          }
          this.resetForm(formName);
        } else {
          this.$message.warning("不满足条件。");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
<style scoped>
.infoform {
  width: 100%;
}
.box-card {
  height: 2.1354rem;
}

.box-card .el-form {
  width: 100%;
  margin-top: 0.1042rem;
  height: 1.8229rem;
}
.el-form .textarea {
  margin: 0.1042rem auto;
  height: 1.25rem;
  width: 100%;
}
::-webkit-scrollbar {
  display: none;
}
.el-form .el-button {
  margin-left: 20%;
}
</style>
