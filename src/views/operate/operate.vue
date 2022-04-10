<template>
  <div class="operate">
    <el-row :gutter="20">
      <infoform formType="messageBoard" class="infoform"
        ><span slot="top" style="">留言板信息</span></infoform
      >
      <infoform formType="impassion"
        ><span slot="top" style="">Impassion</span></infoform
      >
      <infoform formType="advertise"
        ><span slot="top" style="">介绍信息</span></infoform
      >
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4"
        ><el-card
          class="box-card"
          shadow="never"
          :body-style="{ height: '90%' }"
        >
          <ul>
            <li :class="{ active: index == 1 }" @click="change(1)">
              Impassion
            </li>
            <li :class="{ active: index == 2 }" @click="change(2)">
              Advertise
            </li>
          </ul>
        </el-card></el-col
      >
      <el-col :span="19"
        ><el-card
          class="box-card itemlist"
          shadow="never"
          :body-style="{ height: '100%' }"
          ><infotable
            :allData="impassionData"
            tableType="impassion"
            @dele="dele"
            v-show="index === 1"
          />
          <infotable
            :allData="advertiseData"
            tableType="advertise"
            @dele="dele"
            v-show="index === 2"
          /> </el-card
      ></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8"
        ><el-card
          class="box-card3"
          shadow="never"
          :body-style="{ height: '90%' }"
        >
          <el-form ref="swiperForm" class="swiperForm">
            <el-form-item>
              <el-upload
                ref="imgForm"
                action=""
                :limit="1"
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
                </div> </el-upload
            ></el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addSwiper('advertise')"
                >添加一张轮播图片</el-button
              >
            </el-form-item>
          </el-form>
        </el-card></el-col
      >
      <el-col :span="15"
        ><el-card
          class="box-card3"
          shadow="never"
          :body-style="{ height: '100%' }"
        >
          <div class="img">
            <swiper v-if="getImgLength">
              <div
                class="swiper-slide"
                v-for="(item, index) in allSwiperImg"
                :key="index"
              >
                <imgitem :item="item" @deleteImg="deleteImg" />
              </div>
            </swiper>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  getAllIAdvertise,
  getAllImpassion,
  addSwiperImg,
  getAllImg,
  deleteImgById,
  deImpassionById,
  deAdvertiseById,
} from "@/network/data.js";
import postData from "@/utils/qs.js";
import imgitem from "@/components/private/imgitem/imgitem.vue";
import infoform from "@/components/private/infoform/infoform.vue";
import infotable from "@/components/private/infotable/infotable.vue";
import swiper from "@/components/common/swiper/swiper.vue";
export default {
  name: "operate",
  data() {
    return {
      impassionData: null,
      advertiseData: null,
      index: 1,
      fileList: null,
      disabled: false,
      formData: "",
      allSwiperImg: null,
    };
  },
  computed: {
    getImgLength() {
      if (this.allSwiperImg) {
        return this.allSwiperImg.length;
      }
      return 0;
    },
  },
  components: { infoform, imgitem, infotable, swiper },
  methods: {
    //所有轮播图片
    getAllImg() {
      getAllImg().then((res) => {
        this.allSwiperImg = res.data;
      });
    },
    //每日一句
    getAllImpassion() {
      getAllImpassion().then((res) => {
        this.impassionData = res.data;
      });
    },
    //广告介绍
    getAllIAdvertise() {
      getAllIAdvertise().then((res) => {
        this.advertiseData = res.data;
      });
    },
    //添加轮播图
    addSwiperImg(data) {
      addSwiperImg(data).then((res) => {
        this.$message.success(res.data);
        //添加之后重刷轮播图
        this.getAllImg();
      });
    },
    deImpassionById(id) {
      deImpassionById(postData({ id })).then((res) => {
        this.$message.success(res.data);
        this.getAllImpassion();
      });
    },
    deAdvertiseById(id) {
      deAdvertiseById(postData({ id })).then((res) => {
        this.$message.success(res.data);
        this.getAllIAdvertise();
      });
    },
    //移除预览的文件
    handleRemove() {
      this.disabled = !this.disabled;
    },
    //文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    handleChange(file, fileList) {
      this.fileList = fileList;
    },
    //文件数量超出范围调用
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    //提交文件
    imgSubmit(file) {
      this.formData.append("file", file.file);
    },
    addSwiper() {
      if (this.fileList == null) {
        this.$message.warning("请选择图片！");
      } else {
        this.formData = new FormData();
        this.$refs.imgForm.submit();
        //这里清空显示的图片
        let data = this.formData;
        this.addSwiperImg(data);
        //只清除了upload中的图片，但是data中的并未清除，因此还要清空data中的filelist
        this.$refs.imgForm.clearFiles();
        this.fileList = null;
      }
    },
    //更换下标
    change(index) {
      this.index = index;
    },
    dele(obj) {
      //删除数据需要进行数据更新，向父组件发送时间让其再次请求数据
      let name = obj.name;
      let id = obj.id;
      //这里必须先执行完删除，才能再次调用加载数据
      if (name === "impassion") {
        this.deImpassionById(id);
      }
      if (name === "advertise") {
        this.deAdvertiseById(id);
      }
    },
    //删除图片
    deleteImg(id) {
      deleteImgById(postData({ id })).then((res) => {
        this.$message.success(res.data);
        this.getAllImg();
      });
    },
  },
  created() {
    this.getAllImpassion();
    this.getAllIAdvertise();
    this.getAllImg();
  },
  mounted() {
    this.$bus.$on("impassion", () => {
      this.getAllImpassion();
    });
    this.$bus.$on("advertise", () => {
      this.getAllIAdvertise();
    });
  },
};
</script>
<style scoped>
.operate {
  width: 100%;
}
.el-row {
  margin-top: 0.1042rem;
}
.infoform span {
  display: block;
  text-align: center;
  font-size: 0.1146rem;
}
.box-card {
  height: 2.1354rem;
}
.box-card3 {
  height: 1.5625rem;
  margin-bottom: 0.2083rem;
}
.el-row:nth-child(2) .box-card {
  min-height: 2.4479rem;
}
ul {
  margin-top: 0.1042rem;
  width: 100%;
}
li {
  margin-top: 0.1042rem;
  width: 100%;
  font-size: 0.1146rem;
  height: 0.2083rem;
  text-align: center;
  line-height: 0.2083rem;
  user-select: none;
}
li:hover {
  background-color: #f3f4f7;
  cursor: pointer;
  color: coral;
}
li.active {
  background-color: #f3f4f7;
  color: coral;
}
.itemlist {
  position: relative;
}

.el-upload-list__item-thumbnail {
  width: 100%;
  height: 1.0156rem;
}
.swiperForm {
  margin: 0 auto;
  width: 90%;
  height: 100%;
}

.swiperForm .el-form-item:first-child {
  margin: 0;
  height: 70%;
}
.swiperForm .el-form-item:last-child {
  margin: 0;
}
.swiperForm .el-form-item:last-child .el-button {
  margin-left: 0.5208rem;
  margin-top: 0.0521rem;
  width: 1.0417rem;
}
</style>
