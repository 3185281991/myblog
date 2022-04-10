<template>
  <div class="reviews">
    <div class="review" v-if="comments !== null">
      <span class="info">本文共{{ comments.length }}个{{ info }}</span>
      <div class="item" v-for="(item2, index) in comments" :key="index">
        <div class="xxx">
          <span
            ><strong>{{ item2.cusName }}</strong> 说：</span
          >
          <span>{{ item2.comTime | formatTime }}</span>
          <span>{{ item2.comValue }}</span>
        </div>
        <div class="reviewInfo" v-if="reviews[index] !== []">
          <div
            class="reviewitem"
            v-for="ritem in reviews[index]"
            :key="ritem.reTime"
          >
            <span
              ><strong>{{ ritem.reName }}</strong> 回复说:</span
            >
            <span>{{ ritem.reTime | formatTime }}</span>
            <span>{{ ritem.reContent }}</span>
          </div>
        </div>
        <span class="last" @click="reply(item2)">回复</span>
      </div>
    </div>
  </div>
</template>

<script>
import { formatData } from "@/utils/utils.js";
export default {
  name: "review",
  data() {
    return {};
  },
  props: {
    comments: {
      type: Array,
      default() {
        return [];
      },
    },
    reviews: {
      type: Array,
      default() {
        return [];
      },
    },
    info: {
      type: String,
      default() {
        return "评论";
      },
    },
  },
  components: {},
  methods: {
    //回复
    reply(item2) {
      //让表单得到焦点，进行输入
      this.$emit("reply", item2);
    },
  },
  filters: {
    //时间转换
    formatTime(time) {
      let date = new Date(time * 1000);
      return formatData(date, "yyyy-MM-dd hh:mm:ss");
    },
  },
};
</script>
<style scoped>
.review {
  margin: 0.1042rem auto;
  width: 100%;
}
.review .info {
  display: block;
  font-size: 0.1042rem;
  font-weight: bold;
  padding-left: 0.1042rem;
  padding-top: 0.0521rem;
}
.review .item {
  margin-top: 0.0521rem;
  width: 100%;
  background-color: #fff;
  position: relative;
  border-bottom: 1px solid #aaa;
}
.xxx {
  width: 100%;
  height: 0.435rem;
  position: relative;
}
.xxx span {
  display: block;
  position: absolute;
}
.xxx span:nth-child(1) {
  left: 2%;
  top: 35%;
  transform: translateY(-50%);
  font-size: 0.0938rem;
  width: 0.625rem;
  height: 0.1042rem;
  line-height: 100%;
  text-align: center;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.xxx span:nth-child(2) {
  left: 14%;
  top: 13%;
  width: 40%;
  font-size: 0.0885rem;
}
.xxx span:nth-child(3) {
  left: 14%;
  top: 38%;
  width: 75%;
  height: 0.2583rem;
  font-size: 0.0938rem;
  overflow: hidden;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
}
.item span.last {
  display: block;
  position: absolute;
  width: 0.3125rem;
  top: 20%;
  right: 2%;
  font-size: 0.0938rem;
  text-align: center;
  background-color: rgb(248, 117, 94);
}
.last:hover {
  cursor: pointer;
  color: white;
}
.reviewInfo {
  width: 64%;
  margin-left: 11%;
  margin-bottom: 0.1042rem;
}
.reviewitem {
  position: relative;
  height: 0.4167rem;
}
.reviewitem span {
  display: block;
  position: absolute;
}
.reviewitem span:nth-child(1) {
  left: 2%;
  top: 63%;
  transform: translateY(-50%);
  width: 0.5208rem;
  height: 0.1042rem;
  line-height: 100%;
  font-size: 0.0833rem;
  text-align: center;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.reviewitem span:nth-child(2) {
  left: 19%;
  top: 14%;
  width: 53%;
  font-size: 0.0781rem;
}
.reviewitem span:nth-child(3) {
  left: 19%;
  top: 48%;
  width: 80%;
  overflow: hidden;
  font-size: 0.0833rem;
}
</style>
