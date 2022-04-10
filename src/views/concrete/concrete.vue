<template>
  <div class="textInfo">
    <div class="main">
      <div class="ti">
        <span>{{ text.title }}</span>
      </div>
      <div class="basic">
        <ul>
          <li>
            <i class="iconfont icon-yonghu"></i><span>{{ text.author }}</span>
            <i>|</i>
          </li>
          <li>
            <i class="iconfont icon-biaoqian"></i><span>{{ text.label }}</span>
            <i>|</i>
          </li>
          <li>
            <i class="iconfont icon-fenlei"></i><span>{{ text.type }}</span>
            <i>|</i>
          </li>
          <li>
            <i class="iconfont icon-quxiaozhiding"></i
            ><span>{{ text.top }}</span>
            <i>|</i>
          </li>
        </ul>
      </div>
      <div class="content">
        <div class="conimg"><img :src="text.img | getFirstImg" alt="" /></div>
        <p v-html="text.content"></p>
        <div
          class="conimg"
          v-for="(item, index) in getOther(text.img)"
          :key="index"
        >
          <img :src="item" alt="" />
        </div>
        <div class="time">
          <span>{{ text.time | formatTime }}</span>
        </div>
        <ul>
          <li>
            <i class="iconfont icon-dianzan"></i><span>{{ text.lauds }}</span>
          </li>
          <li>
            <i class="iconfont icon-pinglun"></i
            ><span>{{ text.commentsNum }}</span>
          </li>
          <li>
            <i class="iconfont icon-view"></i><span>{{ text.views }}</span>
          </li>
        </ul>
        <div style="clear: both"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { formatData } from "@/utils/utils.js";
import { getContent } from "@/network/data.js";
export default {
  name: "concrete",
  data() {
    return {
      textInfo: null,
    };
  },
  components: {},
  computed: {
    text() {
      if (this.textInfo) {
        return this.textInfo[0];
      }
      return {};
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
    formatTime(time) {
      let date = new Date(time * 1000);
      return formatData(date, "yyyy-MM-dd hh:mm:ss");
    },
  },
  methods: {
    //拿到图片路径之后的图片
    getOther(imgs) {
      if (imgs) {
        let arr = imgs.split(",");
        let imgList = arr.slice(1);
        return imgList;
      }
    },
  },
  created() {
    let bid = this.$route.params.bid;
    getContent(bid).then((res) => {
      this.textInfo = res.data;
    });
  },
};
</script>
<style scoped>
.textInfo {
  width: 100%;
}
.main {
  width: 80%;
  margin: 0 auto;
}
.ti {
  width: 30%;
  height: 0.3125rem;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  top: 4%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.ti span {
  display: block;
  font-size: 0.1146rem;
}
.basic {
  width: 60%;
  height: 0.3125rem;
  background-color: #fff;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  top: 5%;
}
.basic ul {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 13%;
  width: 94%;
  height: 0.2083rem;
}
.basic ul li {
  text-align: center;
  float: left;
  width: 25%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  position: relative;
  overflow: hidden;
  font-size: 0.0938rem;
}
.basic ul li:last-child i:last-child {
  display: none;
}
.content {
  width: 90%;
  background-color: #fff;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  margin-top: 0.0052rem;
}
img {
  max-width: 100%;
  max-height: 80%;
}
.content .conimg {
  width: 100%;
  max-height: 2.8646rem;
  overflow: hidden;
  text-align: center;
}
.conimg img {
  display: block;
  max-height: 100%;
  max-height: 100%;
  margin: auto;
}
.content p {
  display: block;
  width: 95%;
  margin: 0.0521rem auto;
  font-size: 0.0938rem;
  text-indent: 2.1em;
  line-height: 2em;
  letter-spacing: 0.1em;
  word-wrap: break-word;
}
.content .time {
  height: 0.2083rem;
  width: 95%;
  margin: 0 auto;
  font-size: 0.1042rem;
  position: relative;
  right: 0;
}
.time span {
  line-height: 0.2083rem;
  font-size: 0.1042rem;
  float: right;
  margin-right: 0.1042rem;
}
.content div {
  width: 100%;
}
.content ul {
  position: relative;
  float: right;
  width: 30%;
  height: 0.2083rem;
  margin-bottom: 0.0781rem;
}
.content ul li {
  text-align: center;
  float: left;
  width: 33%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  position: relative;
  overflow: hidden;
  font-size: 0.0938rem;
}
</style>
