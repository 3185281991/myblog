<template>
  <div class="census">
    <el-row :gutter="20">
      <el-col :span="10"
        ><el-card
          class="box-card"
          shadow="hover"
          :body-style="{ height: '90%' }"
          ><bar :barData="typeList"
        /></el-card>
      </el-col>
      <el-col :span="8"
        ><el-card class="box-card" :body-style="{ height: '90%' }">
          <span class="near">近期发布</span>
          <div
            class="list"
            v-for="item in this.newBlog"
            :key="item.time"
            @click="textInfo(item.bid)"
          >
            <span>{{ item.author }}</span
            ><span>{{ item.title }}</span
            ><span>{{ item.time | format }}</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6"
        ><el-card
          shadow="never"
          class="box-card"
          :body-style="{ height: '90%' }"
        >
          <span class="near">数据统计</span>
          <div class="titem">
            <span>博客总数</span><span>{{ Nowtotal.allNum }}</span>
          </div>
          <div class="titem">
            <span>总点赞数</span><span>{{ Nowtotal.allLauds }}</span>
          </div>
          <div class="titem">
            <span>浏览总数</span><span>{{ Nowtotal.allViews }}</span>
          </div>
          <div class="titem">
            <span>评论总数</span><span>{{ Nowtotal.allComment }}</span>
          </div>
        </el-card></el-col
      >
    </el-row>
    <el-row :gutter="20">
      <el-col :span="10"
        ><el-card
          class="box-card"
          shadow="hover"
          :body-style="{ height: '90%' }"
        >
          <div class="untop">
            <span :class="{ isActive: index === 1 }" @click="change(1)"
              >点赞排行</span
            ><span :class="{ isActive: index === 2 }" @click="change(2)"
              >浏览排行</span
            ><span :class="{ isActive: index === 3 }" @click="change(3)"
              >评论排行</span
            >
          </div>
          <div class="unun">
            <div v-if="index == 1">
              <pie :pieData="lauds" type="lauds" />
            </div>
            <div v-if="index == 2">
              <pie :pieData="views" type="views" />
            </div>
            <div v-if="index == 3">
              <pie :pieData="comments" type="commentsNum" />
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12" :offset="1">
        <el-card class="box-card" :body-style="{ height: '90%' }">
          <eline :lineData="authorText"
        /></el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  groupByType,
  getNew,
  getAllSum,
  laudsPx,
  viewsPx,
  commentsPx,
  authorText,
} from "@/network/data.js";
import { formatData } from "@/utils/utils.js";
import bar from "@/components/common/echarts/bar.vue";
import pie from "@/components/common/echarts/pie.vue";
import eline from "@/components/common/echarts/line.vue";
export default {
  name: "census",
  data() {
    return {
      typeList: null,
      newBlog: null,
      total: null,
      lauds: null,
      views: null,
      comments: null,
      authorText: null,
      index: 1,
    };
  },
  components: { bar, pie, eline },
  filters: {
    format(time) {
      let date = new Date(time * 1000);
      return formatData(date, "yyyyy-MM-dd hh:mm:ss");
    },
  },
  computed: {
    Nowtotal() {
      if (this.total) {
        return this.total[0];
      }
      return "";
    },
  },
  methods: {
    change(index) {
      this.index = index;
    },
    textInfo(bid) {
      this.$router.push("/concrete/" + bid);
    },
  },
  created() {
    groupByType().then((res) => {
      this.typeList = res.data;
    });
    getNew().then((res) => {
      this.newBlog = res.data;
    });
    laudsPx().then((res) => {
      this.lauds = res.data;
    });
    viewsPx().then((res) => {
      this.views = res.data;
    });
    commentsPx().then((res) => {
      this.comments = res.data;
    });
    getAllSum().then((res) => {
      this.total = res.data;
    });
    authorText().then((res) => {
      this.authorText = res.data;
    });
  },
};
</script>
<style scoped>
.census {
  width: 100%;
}
.el-row {
  margin-top: 0.1042rem;
  height: 2.0833rem;
}
.el-row:nth-child(2) {
  height: 2.3438rem;
}
.box-card {
  height: 1.9792rem;
}
.el-row:nth-child(2) .el-col {
  height: 2.1875rem;
}
.el-row:nth-child(2) .el-col .box-card {
  height: 100%;
}
.box-card .near {
  display: block;
  font-size: 0.1146rem;
  margin-left: 40%;
  margin-top: -4%;
  font-weight: bold;
}
.box-card .list {
  width: 100%;
  height: 12%;
  margin-top: 0.0104rem;
  font-size: 0.1146rem;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.box-card .list:hover {
  cursor: pointer;
  color: coral;
}
.box-card .list span {
  display: block;
  font-size: 0.0938rem;
  text-align: center;
  overflow: hidden;
  height: 0.099rem;
}
.list span:nth-child(1) {
  width: 25%;
}
.list span:nth-child(2) {
  width: 25%;
}
.list span:nth-child(3) {
  width: 45%;
}
.titem {
  margin-top: 0.1042rem;
  width: 100%;
  height: 15%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
.titem span {
  display: block;
  width: 35%;
  font-size: 0.1042rem;
  text-align: center;
  overflow: hidden;
}
.titem span:first-child {
  font-weight: bold;
}
.titem span:first-child:hover {
  color: coral;
  cursor: pointer;
}
.untop {
  width: 100%;
  height: 12%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
.untop span {
  font-size: 0.0938rem;
  width: 25%;
  text-align: center;
}
.untop span:hover {
  cursor: pointer;
  color: coral;
}
.unun {
  width: 100%;
  margin-top: 2%;
  height: 85%;
}
.unun div {
  width: 100%;
  height: 100%;
}
.isActive {
  color: coral;
}
</style>
