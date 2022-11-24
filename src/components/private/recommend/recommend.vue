<template>
  <div class="recommend">
    <div class="ri">
      <span @click="change1" :class="{ isActive: recFlag }">推荐文章</span
      ><span @click="change2" :class="{ isActive: ranFlag }">随机文章</span>
    </div>
    <ul v-show="recFlag">
      <li
        v-for="(item, index) in RecList"
        :key="index"
        @click="con(item.bid)"
        :title="item.title"
      >
        <span class="title">{{ item.title }}</span
        ><span>{{ item.author }}</span
        ><span><i class="iconfont icon-dianzan"></i> {{ item.lauds }}</span>
      </li>
    </ul>
    <ul v-show="ranFlag">
      <li
        v-for="(item, index) in ranData"
        :key="index"
        @click="con(item.bid)"
        :title="item.title"
      >
        <span class="title">{{ item.title }}</span
        ><span>{{ item.author }}</span
        ><span><i class="iconfont icon-dianzan"></i> {{ item.lauds }}</span>
      </li>
    </ul>
  </div>
</template>

<script>
import { randomArr } from "@/utils/utils.js";
export default {
  name: "recommend",
  data() {
    return {
      recFlag: true,
      ranFlag: false,
      ranData: null,
    };
  },
  props: {
    RecList: {
      type: Array,
      default() {
        return [];
      },
    },
    AllBlog: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  methods: {
    con(bid) {
      this.$router.push("/concrete/" + bid);
      this.$bus.$emit("isLeave", "true");
    },
    change1() {
      this.recFlag = true;
      this.ranFlag = false;
      sessionStorage.setItem("ranFlag", "false");
      sessionStorage.setItem("recFlag", "true");
    },
    change2() {
      this.recFlag = false;
      this.ranFlag = true;
      sessionStorage.setItem("ranFlag", "true");
      sessionStorage.setItem("recFlag", "false");
      //随机从所有数据中取出六条
      this.ranData = randomArr(this.AllBlog).slice(0, 6);
      sessionStorage.setItem("ranData", JSON.stringify(this.ranData));
    },
  },
  created() {
    if (sessionStorage.getItem("ranFlag") === "true") {
      this.recFlag = false;
      this.ranFlag = true;
    }
    if (sessionStorage.getItem("ranData") !== null) {
      this.ranData = JSON.parse(sessionStorage.getItem("ranData"));
    } else {
      this.ranData = randomArr(this.AllBlog).slice(0, 6);
    }
  },
};
</script>
<style scoped>
.recommend {
  margin-top: 0.026rem;
  width: 100%;
  height: 2.1875rem;
  background-color: #fff;
}
.ri {
  width: 100%;
  height: 14%;
  user-select: none;
}
.ri span {
  float: left;
  display: block;
  font-size: 0.1302rem;
  margin-left: 0.1042rem;
  padding-top: 0.0781rem;
}
.ri span:hover {
  cursor: pointer;
  color: coral;
}
.isActive {
  color: coral;
}
ul {
  width: 100%;
  height: 86%;
  border-top: #ffbcb5 0.0104rem solid;
}
ul li {
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 100%;
  height: 15%;
  margin-top: 0.026rem;
  border-bottom: 1px solid #aaa;
  color: #555;
}
ul li:last-child {
  border: none;
}
li:hover {
  cursor: pointer;
}
li span {
  display: block;
  font-size: 0.1042rem;
  height: 55%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
li span:nth-child(1) {
  width: 35%;
}
li span:nth-child(2) {
  width: 25%;
  text-align: center;
}
li span:nth-child(3) {
  width: 15%;
  text-align: center;
  background-color: #ff4d3a;
  border-radius: 0.0781rem;
  color: #fff;
}
span i {
  font-size: 0.1042rem;
}
</style>
