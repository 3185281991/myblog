<template>
  <div class="editor">
    <quill-editor
      v-model="content"
      ref="myQuillEditor"
      :options="editorOption"
      @blur="onEditorBlur($event)"
    >
    </quill-editor>
  </div>
</template>

<script>
import { quillEditor } from "vue-quill-editor";
export default {
  name: "quilEditor",
  data() {
    return {
      content: "",
      // toolbar标题
      titleConfig: [
        { Choice: ".ql-insertMetric", title: "跳转配置" },
        { Choice: ".ql-bold", title: "加粗" },
        { Choice: ".ql-italic", title: "斜体" },
        { Choice: ".ql-underline", title: "下划线" },
        { Choice: ".ql-header", title: "段落格式" },
        { Choice: ".ql-strike", title: "删除线" },
        { Choice: ".ql-blockquote", title: "块引用" },
        { Choice: ".ql-code", title: "插入代码" },
        { Choice: ".ql-code-block", title: "插入代码段" },
        { Choice: ".ql-font", title: "字体" },
        { Choice: ".ql-size", title: "字体大小" },
        { Choice: '.ql-list[value="ordered"]', title: "编号列表" },
        { Choice: '.ql-list[value="bullet"]', title: "项目列表" },
        { Choice: ".ql-direction", title: "文本方向" },
        { Choice: '.ql-header[value="1"]', title: "h1" },
        { Choice: '.ql-header[value="2"]', title: "h2" },
        { Choice: ".ql-align", title: "对齐方式" },
        { Choice: ".ql-color", title: "字体颜色" },
        { Choice: ".ql-background", title: "背景颜色" },
        { Choice: ".ql-image", title: "图像" },
        { Choice: ".ql-video", title: "视频" },
        { Choice: ".ql-link", title: "添加链接" },
        { Choice: ".ql-formula", title: "插入公式" },
        { Choice: ".ql-clean", title: "清除字体格式" },
        { Choice: '.ql-script[value="sub"]', title: "下标" },
        { Choice: '.ql-script[value="super"]', title: "上标" },
        { Choice: '.ql-indent[value="-1"]', title: "向左缩进" },
        { Choice: '.ql-indent[value="+1"]', title: "向右缩进" },
        { Choice: ".ql-header .ql-picker-label", title: "标题大小" },
        {
          Choice: '.ql-header .ql-picker-item[data-value="1"]',
          title: "标题一",
        },
        {
          Choice: '.ql-header .ql-picker-item[data-value="2"]',
          title: "标题二",
        },
        {
          Choice: '.ql-header .ql-picker-item[data-value="3"]',
          title: "标题三",
        },
        {
          Choice: '.ql-header .ql-picker-item[data-value="4"]',
          title: "标题四",
        },
        {
          Choice: '.ql-header .ql-picker-item[data-value="5"]',
          title: "标题五",
        },
        {
          Choice: '.ql-header .ql-picker-item[data-value="6"]',
          title: "标题六",
        },
        { Choice: ".ql-header .ql-picker-item:last-child", title: "标准" },
        {
          Choice: '.ql-size .ql-picker-item[data-value="small"]',
          title: "小号",
        },
        {
          Choice: '.ql-size .ql-picker-item[data-value="large"]',
          title: "大号",
        },
        {
          Choice: '.ql-size .ql-picker-item[data-value="huge"]',
          title: "超大号",
        },
        { Choice: ".ql-size .ql-picker-item:nth-child(2)", title: "标准" },
        { Choice: ".ql-align .ql-picker-item:first-child", title: "居左对齐" },
        {
          Choice: '.ql-align .ql-picker-item[data-value="center"]',
          title: "居中对齐",
        },
        {
          Choice: '.ql-align .ql-picker-item[data-value="right"]',
          title: "居右对齐",
        },
        {
          Choice: '.ql-align .ql-picker-item[data-value="justify"]',
          title: "两端对齐",
        },
      ],
      editorOption: {
        //主题
        theme: "snow",
        placeholder: "请输入正文",
        modules: {
          toolbar: [
            ["bold", "italic", "underline", "strike"], //加粗，斜体，下划线，删除线
            ["blockquote", "code-block"], //引用，代码块
            [{ header: 1 }, { header: 2 }], // 标题，键值对的形式；1、2表示字体大小
            [{ list: "ordered" }, { list: "bullet" }], //列表
            [{ script: "sub" }, { script: "super" }], // 上下标
            [{ indent: "-1" }, { indent: "+1" }], // 缩进
            [{ direction: "rtl" }], // 文本方向
            [{ size: ["12px", "14px", "16px", "18px", "20px"] }], // 字体大小
            [{ header: [1, 2, 3, 4, 5, 6, false] }], //几级标题
            [{ color: [] }, { background: [] }], // 字体颜色，字体背景颜色
            [{ font: [] }], //字体
            [{ align: [] }], //对齐方式
            ["clean"], //清除字体样式
          ],
        },
      },
    };
  },
  components: { quillEditor },
  methods: {
    getInfo() {
      for (let item of this.titleConfig) {
        let tip = document.querySelector(".quill-editor " + item.Choice);
        if (!tip) continue;
        tip.setAttribute("title", item.title);
      }
    },
    onEditorBlur() {
      //失去焦点事件
      this.$emit("textBlur", this.content);
    },
    // onEditorFocus() {
    //   //获得焦点事件
    //   console.log(1);
    // },
    // onEditorChange() {
    //   //内容改变事件
    //   console.log(2);
    // },
  },
  mounted() {
    this.getInfo();
  },
};
</script>
<style scoped>
.editor {
  width: 70vw;
  height: 300px;
}
.quill-editor {
  width: 100%;
  height: 100%;
}
</style>
