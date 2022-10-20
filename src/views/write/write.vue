<template>
  <div class="write">
    <el-form
      :model="blog"
      :rules="rules"
      ref="blog"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="文章标题" prop="title">
        <el-input v-model="blog.title"></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="blog.author"></el-input>
      </el-form-item>
      <el-form-item label="标签" prop="label">
        <el-input v-model="blog.label"></el-input>
      </el-form-item>
      <el-form-item label="文章分类" prop="type">
        <el-radio-group v-model="blog.type">
          <el-radio label="生活日常"></el-radio>
          <el-radio label="技术知识"></el-radio>
          <el-radio label="工作总结"></el-radio>
          <el-radio label="个人经历"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="文章置顶" prop="top">
        <el-switch v-model="blog.top"></el-switch>
      </el-form-item>

      <el-form-item class="editor" label="文章内容" prop="content">
        <!-- <el-input
          @keyup.enter.native="addBr"
          class="eltext"
          rows="12"
          type="textarea"
          style="overflow-y: scroll"
          v-model="blog.content"
          ref="text"
        ></el-input> -->
        <quilEditor @textBlur="getContent" />
      </el-form-item>
      <el-form-item class="upload" label="上传图片">
        <el-upload
          ref="imgForm"
          action=""
          :limit="3"
          multiple
          :auto-upload="false"
          list-type="picture-card"
          :on-change="handleChange"
          :on-exceed="handleExceed"
          :http-request="imgSubmit"
        >
          <i slot="default" class="el-icon-plus"></i>
          <div slot="file" slot-scope="{ file }" v-if="disabled">
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url"
              width="100%"
              height="100%"
              alt=""
            />
            <span class="el-upload-list__item-actions">
              <span
                class="el-upload-list__item-delete"
                @click="handleRemove(file)"
              >
                <i class="el-icon-delete"></i>
              </span>
            </span>
          </div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('blog')"
          >添加文章</el-button
        >
        <el-button @click="resetForm('blog')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addBlog } from "@/network/data.js";
import { throttle } from "@/utils/utils.js";
import quilEditor from "@/components/common/editor/quilEditor";
export default {
  name: "write",
  data() {
    return {
      content: "",
      blog: {
        title: "",
        author: "",
        label: "",
        type: "",
        content: "",
        fileList: null,
        top: false,
      },
      rules: {
        title: [
          { required: true, message: "请输入标题", trigger: "blur" },
          { min: 1, max: 25, message: "标题字数1~25", trigger: "blur" },
        ],
        author: [
          { required: true, message: "请输入作者", trigger: "blur" },
          { min: 1, max: 9, message: "作者名称字数2~9", trigger: "blur" },
        ],
        label: [
          { required: true, message: "请输入标签", trigger: "blur" },
          { min: 2, max: 9, message: "标签字数2~9", trigger: "blur" },
        ],
        type: [{ required: true, message: "未选择类型", trigger: "blur" }],
      },
      disabled: false,
      formData: "",
    };
  },
  components: {
    quilEditor,
  },
  methods: {
    //上传信息
    addBlog(data) {
      addBlog(data).then((res) => {
        let flag = res.data;
        if (flag == 1) {
          this.$message.success("您发布了一篇文章");
          setTimeout(() => {
            location.reload();
          }, 1500);
        } else {
          this.$message.error("添加失败，服务器错误");
        }
      });
    },
    //移除预览的文件
    handleRemove() {
      this.disabled = !this.disabled;
    },
    //文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    handleChange(file, fileList) {
      this.blog.fileList = fileList;
    },
    //文件数量超出范围调用
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    //提交文件
    imgSubmit(file) {
      this.formData.append("file", file.file);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.content.length <= 200) {
            this.$message.warning("最少输入两百字。");
            return false;
          }
          let time = Math.floor(new Date().getTime() / 1000);
          this.formData = new FormData();
          this.formData.append("author", this.blog.author);
          this.formData.append("label", this.blog.label);
          this.formData.append("type", this.blog.type);
          this.formData.append("title", this.blog.title);
          this.formData.append("content", this.content);

          this.$refs.imgForm.submit();
          let top = "";
          if (this.blog.top === true) {
            top = "置顶";
          } else {
            top = "不置顶";
          }
          this.formData.append("top", top);
          this.formData.append("time", time);
          //开始添加博客,限制节流，指定秒数内只提交一次
          let data = this.formData;
          throttle(this.addBlog(data), 1000);
          this.resetForm("blog");
          this.$refs.imgForm.clearFiles();
          this.blog.fileList = null;
        } else {
          this.$message.warning("请输入必要信息！");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    getContent(content) {
      this.content = content;
    },
  },
};
</script>
<style scoped>
.write {
  width: 100%;
  background-color: #fff;
}
.el-form {
  padding-top: 0.1042rem;
  width: 80%;
  padding-bottom: 0.0781rem;
}
.el-input {
  width: 1.8229rem;
}
.editor {
  width: 70vw;
}
.upload {
  margin-top: 0.5208rem;
}
.el-upload-list__item-thumbnail {
  margin-top: 20px;
  width: 100%;
  height: 1.0156rem;
}
</style>
