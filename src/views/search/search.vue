<template>
  <div class="search">
    <div class="prompt">
      <span class="info"
        >搜索信息: <strong>{{ this.searchInput }} </strong>共有
        <strong> {{ resultNum }}</strong> 条结果</span
      >
      <div>
        <input type="text" v-model="info" /><span
          class="iconfont icon-sousuo"
          title="点击搜索"
          @click="search"
        ></span>
      </div>
    </div>
    <div v-if="allData !== null"><commonView :AllData="allData" /></div>
  </div>
</template>

<script>
import { likeFind, SearchInfo } from "@/network/data.js";
import commonView from "@/components/private/commonView/commonView.vue";
export default {
  name: "search",
  data() {
    return {
      allData: null,
      searchInput: "",
      resultNum: 0,
      info: "",
    };
  },
  props: {
    type: String,
    default() {
      return "";
    },
  },
  components: { commonView },
  methods: {
    search() {
      let input = this.info;
      if (input === "") {
        this.$router.push("/search/" + "default");
      } else {
        this.$router.push("/search/" + input);
        this.info = "";
      }
    },
  },
  created() {
    this.$bus.$emit("pathRecord", "/search");
    let str = this.$route.params.search;
    this.searchInput = str;
    if (str === "default") {
      SearchInfo().then((res1) => {
        this.allData = res1.data;
        this.resultNum = this.allData.length;
      });
    } else {
      likeFind(str).then((res) => {
        //判断是否显示默认数据
        this.allData = res.data;
        this.resultNum = this.allData.length;
      });
    }
  },
  mounted() {
    this.$bus.$emit("pathRecord", "/search");
  },
};
</script>
<style scoped>
.search {
  width: 96%;
  height: 100%;
  margin: 0 auto;
}
.prompt {
  width: 100%;
  height: 1.0417rem;
  margin: 0 auto;
  position: relative;
  background-color: #fff;
}
.prompt span.info {
  display: block;
  position: absolute;
  font-size: 0.1302rem;
  top: 25%;
  left: 3%;
}
.prompt div {
  width: 94%;
  height: 0.3125rem;
  background-color: #e7e7e7;
  position: absolute;
  bottom: 20%;
  left: 3%;
  border-radius: 0.0104rem;
}
.prompt div input {
  height: 0.1823rem;
  width: 25%;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  left: 0.0781rem;
  border-radius: 0.026rem;
  padding-left: 0.1042rem;
  font-size: 0.0938rem;
}
.prompt div span {
  font-size: 0.1823rem;
  display: block;
  width: 0.3125rem;
  height: 35px;
  text-align: center;
  position: absolute;
  border-radius: 0.026rem;
  left: 29%;
  top: 18%;
  text-align: center;
  background-color: #d9534f;
  border-color: #d43f3a;
  color: #fff;
}
.prompt div span:hover {
  cursor: pointer;
  background-color: #c9302c;
  border-color: #ac2925;
}
</style>
