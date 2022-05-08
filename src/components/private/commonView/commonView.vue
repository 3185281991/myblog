<template>
  <div class="content">
    <div class="item" v-for="(item, index) in pageData" :key="item.time">
      <div class="image" @click="linkClick(index, item.bid)">
        <img :src="item.img | getFirstImg" alt="" title="查看原文" />
      </div>
      <div class="text">
        <div class="title">
          <span>{{ item.title }}</span
          ><span v-show="item.top === '置顶'">{{ item.top }}</span>
        </div>
        <div class="info">
          <iconlist :item="item" />
        </div>
        <div class="contentt">
          <p>{{ getRandomContent(item.content) }}</p>
        </div>
        <div class="link">
          <span>#{{ item.label }}</span
          ><span @click="linkClick(index, item.bid)" title="文章详情"
            >阅读全文 》</span
          >
        </div>
      </div>
    </div>
    <pagination
      :pageNum="pageNum"
      @prev="prev"
      @next="next"
      @first="first"
      @last="last"
      v-if="pageData.length !== 0"
    />
  </div>
</template>

<script>
import { scrollToTop } from "@/utils/scroll.js";
import { addView } from "@/network/data.js";
import postData from "@/utils/qs.js";
import pagination from "@/components/private/pagination/pagination";
import iconlist from "@/components/private/iconlist/iconlist.vue";

export default {
  name: "commonView",
  data() {
    return {
      pageData: null,
      pageNum: 1,
    };
  },
  components: { pagination, iconlist },
  props: {
    AllData: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  filters: {
    getFirstImg(imgs) {
      if (imgs) {
        let arr = imgs.split(",");
        if (arr.length == 1) {
          return arr;
        } else {
          return arr[0];
        }
      }
    },
  },
  computed: {
    //随机截取一段内容
    getRandomContent() {
      return function (str) {
        //将html文本中的所有<>标签全部替换掉
        let rs = str.replace(/(<[^>]+>)/g, "");
        let random = Math.ceil(Math.random() * 50 + 150);
        return rs.slice(0, random) + "......";
      };
    },
  },
  methods: {
    //默认每页显示2份
    sliceData(num = 1) {
      //开始
      let start = (num - 1) * 6;
      //结束
      let end = num * 6;
      //最终的分页数据
      this.pageData = this.AllData.slice(start, end);
    },

    prev() {
      if (this.pageNum == 1) {
        this.$toast.show("这是第一页 !", 1500);
      } else {
        this.pageNum = this.pageNum - 1;
        this.sliceData(this.pageNum);
        scrollToTop();
      }
    },
    next() {
      let end = Math.ceil(this.AllData.length / 6);
      if (this.pageNum == end) {
        this.$toast.show("这是最后一页了!", 1500);
      } else {
        this.pageNum = this.pageNum + 1;
        this.sliceData(this.pageNum);
        scrollToTop();
      }
    },
    first() {
      if (this.pageNum == 1) {
        this.$toast.show("这是第一页 !", 1500);
      } else {
        this.sliceData(1);
        this.pageNum = 1;
        scrollToTop();
      }
    },
    last() {
      let end = Math.ceil(this.AllData.length / 6);
      if (this.pageNum == end) {
        this.$toast.show("这是最后一页了!", 1500);
      } else {
        this.sliceData(end);
        this.pageNum = end;
        scrollToTop();
      }
    },
    linkClick(index, bid) {
      //导航部分这时候应该不显示变化
      //采用事件总线
      this.$bus.$emit("isLeave", "true");
      addView(postData({ bid })).then();
      //拿到具体在alldata中当前bid的位置下标
      let activeIndex = (this.pageNum - 1) * 6 + index * this.pageNum;
      this.$store.commit("getAcitiveIndex", activeIndex);
      this.$router.push("/concrete/" + bid);
      this.$bus.$emit("pathRecord", "/concrete");
    },
  },
  created() {
    this.sliceData(this.pageNum);
  },
};
</script>
<style scoped>
.content {
  margin-top: 0.0781rem;
}
.item {
  width: 100%;
  background-color: #fff;
  margin-bottom: 0.1302rem;
  min-height: 0.9792rem;
}
.image {
  width: 100%;
  overflow: hidden;
  margin-bottom: 2%;
  max-height: 2.8646rem;
  text-align: center;
}

.image img {
  width: 100%;
  height: 100%;
  transition: 0.3s;
  display: block;
  margin: auto;
}
.image img:hover {
  filter: saturate(1.3);
  transform: scale(1.1);
}
.info ul {
  margin: 0 auto;
  width: 94%;
  height: 100%;
}
.info ul li {
  text-align: center;
  float: left;
  width: 15%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  position: relative;
}
ul li i:nth-child(1) {
  font-size: 0.1458rem;
}
.text {
  width: 100%;
}
.text .title {
  margin: 0 auto;
  width: 100%;
  position: relative;
  height: 0.3125rem;
}
.title span {
  display: block;
  float: left;
  font-size: 0.0885rem;
}
.title span:first-child {
  font-size: 0.1458rem;
  margin-left: 3%;
}
.title span:nth-child(2) {
  background-color: #337ab7;
  color: white;
  margin-left: 2%;
  margin-top: 1%;
}

.text .info {
  width: 100%;
  height: 0.2083rem;
}
.text .contentt {
  user-select: none;
  width: 100%;
  /* 指定文本行省略 */
  overflow: hidden;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
}
.text .contentt p {
  margin-left: 0.1823rem;
  font-size: 0.0938rem;
  line-height: 2em;
  word-wrap: break-word;
}
.text .link {
  margin-top: 0.0781rem;
  width: 100%;
  height: 0.3125rem;
}
.link span {
  float: left;
  display: block;
  width: 16%;
  background-color: #bbb;
  text-align: center;
  font-size: 0.0885rem;
  margin-left: 3%;
}
.link span:first-child {
  height: 0.1302rem;
  line-height: 0.1302rem;
}
.link span:last-child {
  float: right;
  display: block;
  color: coral;
  font-size: 0.0885rem;
  background-color: white;
  margin-right: 3%;
  height: 0.1563rem;
  line-height: 0.1563rem;
}
.link span:last-child:hover {
  cursor: pointer;
}
</style>
