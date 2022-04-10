<template>
  <div class="r">
    <div class="swiperx" v-show="swiperxShow">
      <swiper v-if="getLength">
        <div
          class="swiper-slide"
          v-for="(item, index) in swiperImg"
          :key="index"
        >
          <img :src="item.imgPath" />
        </div>
      </swiper>
    </div>
    <div class="rec">
      <recommend :RecList="RecList" :AllBlog="AllBlog" />
    </div>
    <div class="rintro"><introduce :impassion="impassion" /></div>
    <div class="rad"><advertise :adverInfo="adverInfo" /></div>
    <div class="appre" v-show="appreShow"><appreciation /></div>
    <div class="rtag"><labeltag :tags="labelList" /></div>
  </div>
</template>

<script>
import {
  getLabel,
  getRecommend,
  getAllBlog,
  getImpassion,
  getAdvertise,
  randomImg,
} from "@/network/data.js";
import labeltag from "@/components/private/labeltag/labeltag.vue";
import swiper from "@/components/common/swiper/swiper.vue";
import recommend from "@/components/private/recommend/recommend.vue";
import appreciation from "@/components/private/appreciation/appreciation.vue";
import introduce from "@/components/private/introduce/introduce.vue";
import advertise from "@/components/private/advertise/advertise.vue";
export default {
  name: "right",
  data() {
    return {
      labelList: null,
      RecList: null,
      AllBlog: null,
      impassion: null,
      adverInfo: null,
      swiperImg: null,
      swiperxShow: false,
      appreShow: false,
    };
  },
  components: {
    labeltag,
    swiper,
    recommend,
    appreciation,
    introduce,
    advertise,
  },
  computed: {
    //利用v-if的属性，v-if=showProduct.length，确保异步加载的数据已经返回后，再加载swiper，否则无法循环
    getLength() {
      return this.swiperImg;
    },
  },
  created() {
    getLabel().then((res) => {
      this.labelList = res.data;
    });
    getRecommend().then((res) => {
      this.RecList = res.data;
    });
    getImpassion().then((res) => {
      this.impassion = res.data;
    });
    getAdvertise().then((res) => {
      this.adverInfo = res.data;
    });
    randomImg().then((res) => {
      this.swiperImg = res.data;
    });
    getAllBlog().then((res) => {
      this.AllBlog = res.data;
    });
    //当每次刷新时，也要判断路由,以判断侧边栏显示那一部分
    let path = this.$route.path;
    if (path == "/index") {
      this.swiperxShow = true;
      this.appreShow = false;
    }
    if (path == "/messageBoard") {
      this.appreShow = true;
      this.swiperxShow = false;
    }
    if (
      path == "/commonlife" ||
      path == "/work" ||
      path == "/knowledge" ||
      path == "/about"
    ) {
      this.swiperxShow = false;
      this.appreShow = false;
    }
    if (path.includes("/concrete")) {
      this.swiperxShow = false;
      this.appreShow = false;
    }
    if (path.includes("/search")) {
      this.swiperxShow = false;
      this.appreShow = false;
    }
  },
  mounted() {
    this.$bus.$on("pathRecord", (path) => {
      if (path == "/index") {
        this.swiperxShow = true;
        this.appreShow = false;
      }
      if (path == "/messageBoard") {
        this.appreShow = true;
        this.swiperxShow = false;
      }
      if (path == "/simple") {
        this.swiperxShow = false;
        this.appreShow = false;
      }
      if (path == "/search") {
        this.swiperxShow = false;
        this.appreShow = false;
      }
      if (path == "/concrete") {
        this.swiperxShow = false;
        this.appreShow = false;
      }
    });
  },
};
</script>
<style scoped>
.r {
  width: 99%;
  margin: 0 auto;
}
.swiperx {
  width: 100%;
  height: 1.1979rem;
  overflow: hidden;
  position: relative;
  margin-top: 0.0781rem;
}
.swiper-slide {
  width: 100%;
  height: 100%;
}
.swiper-slide img {
  max-width: 100%;
  height: 100%;
  margin: auto;
}
.rec,
.rtag,
.appre,
.rintro,
.rad {
  margin-top: 0.0781rem;
}
</style>
