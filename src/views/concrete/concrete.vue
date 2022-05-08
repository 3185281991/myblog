<template>
  <div class="concrete">
    <div v-if="item !== null">
      <div class="main">
        <span class="title">{{ item.title }}</span>
        <div class="icon"><iconlist :item="item" /></div>
        <div class="cen ql-snow">
          <div class="cenimg"><img :src="item.img | getFirstImg" alt="" /></div>
          <div class="ql-editor" v-html="item.content"></div>
          <div
            class="cenimg"
            v-for="(item, index) in getOther(item.img)"
            :key="index"
          >
            <img :src="item" alt="" />
          </div>
        </div>
        <div class="bon">
          <span>#{{ item.label }}</span>
          <span @click="clickLauds(item.bid)"
            ><i class="iconfont icon-dianzan"></i> 赞 {{ this.lauds }}</span
          >
        </div>
      </div>
      <div class="change">
        <span @click="prevText">上一篇</span
        ><span @click="nextText">下一篇</span>
      </div>
      <div class="state">
        <p>
          转载请说明：<strong> {{ item.title }} </strong> - My Station Blog
        </p>
      </div>
    </div>
    <div class="un" ref="botton" v-if="item !== null">
      <div class="reviewx">
        <review
          :comments="comments"
          :reviews="reviews"
          @reply="reply"
          ref="reviewx"
        />
      </div>
      <div class="comment">
        <comment
          :bid="this.bid"
          :commentsInfo="replyObj"
          :isReply="isReply"
          @replyChange="changeAlt"
        />
      </div>
    </div>
  </div>
</template>

<script>
import postData from "@/utils/qs.js";
import { comRev } from "@/mixins/mixi.js";
import { isLaud, addLaud, insertLauds, addView } from "@/network/data.js";
import iconlist from "@/components/private/iconlist/iconlist.vue";
export default {
  name: "concrete",
  data() {
    return {
      bid: null,
      lauds: null,
      flag: null,
      //提交评论
    };
  },
  mixins: [comRev],
  components: { iconlist },
  computed: {
    getIndex() {
      return this.$store.getters.getIndex;
    },
    getData() {
      return this.$store.getters.getCateData;
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
  methods: {
    //拿到图片路径之后的图片
    getOther(imgs) {
      if (imgs) {
        let arr = imgs.split(",");
        let imgList = arr.slice(1);
        return imgList;
      }
    },
    //点赞实现
    laud(obj, bid) {
      //是否点赞
      isLaud(postData(obj)).then((res) => {
        this.flag = res.data;
        if (this.flag === 1) {
          this.$toast.show("您已经点过赞了。", 2000);
        }
        //判断是否存在该用户与博客的关系，不存在这里添加,同时也要对文章进行添加操作
        if (this.flag === "") {
          insertLauds(postData(obj)).then();
          addLaud(postData({ bid })).then((res) => {
            this.lauds = this.lauds + 1;
            //更新iconlist信息
            this.$bus.$emit("updateIconList", bid);
            this.$toast.show(res.data, 2000);
          });
        }
      });
    },

    //点赞加1
    clickLauds(bid) {
      //先判断是否存在账号，没有就是过默认账号
      let username1 = localStorage.getItem("username1");
      let email1 = localStorage.getItem("email1");
      let username = localStorage.getItem("username");
      let email = localStorage.getItem("email");
      if (username && email) {
        let obj1 = { bid, email };
        this.laud(obj1, bid);
      } else if (username1 && email1) {
        let email = email1;
        let obj2 = { bid, email };
        this.laud(obj2, bid);
      } else {
        this.$toast.show("操作异常，建议先进行评论", 1500);
      }
    },
    //文章切换
    prevText() {
      let ActiveIndex = this.getIndex;
      let data = this.getData;
      if (ActiveIndex == 0) {
        alert("当前为第一篇");
      } else {
        this.$store.commit("getAcitiveIndex", ActiveIndex - 1);
        this.$router.push("/concrete/" + data[ActiveIndex - 1].bid);
      }
      let bid = this.bid;
      addView(postData({ bid })).then();
    },
    nextText() {
      let ActiveIndex = this.getIndex;
      let data = this.getData;
      let end = data.length - 1;
      if (ActiveIndex == end) {
        alert("当前为最后一篇");
      } else {
        this.$store.commit("getAcitiveIndex", ActiveIndex + 1);
        this.$router.push("/concrete/" + data[ActiveIndex + 1].bid);
      }
      let bid = this.bid;
      addView(postData({ bid })).then();
    },
  },

  created() {
    this.bid = this.$route.params.bid;
    this.getItem(this.bid);
    this.getComment(this.bid);
  },
};
</script>
<style scoped>
.concrete {
  width: 96%;
  height: 100%;
  background-color: #fff;
}
.concrete .main {
  width: 98%;
  margin: 0 auto;
}
.main .title {
  display: block;
  width: 30%;
  margin: 0 auto;
  padding-top: 0.0781rem;
  padding-bottom: 0.0521rem;
  text-align: center;
  font-size: 0.1198rem;
  font-weight: bold;
}
.main .icon {
  width: 100%;
}
.main .cen {
  width: 100%;
}
.cen .cenimg {
  width: 100%;
  max-height: 2.8646rem;
  margin-bottom: 0.0521rem;
  overflow: hidden;
  text-align: center;
}
.cenimg img {
  width: 100%;
  max-height: 100%;
  display: block;
  margin: 0 auto;
}
.bon {
  width: 100%;
  height: 0.3125rem;
  position: relative;
}
.bon span {
  display: block;
  position: absolute;
}
.bon span:first-child {
  top: 50%;
  transform: translateY(-50%);
  margin-left: 5%;
  width: 12%;
  height: 28%;
  line-height: 100%;
  text-align: center;
  font-size: 0.0885rem;
  background-color: #bbb;
}
.bon span:last-child i {
  font-size: 0.1302rem;
}
.bon span:last-child {
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 0.5208rem;
  height: 45%;
  background-color: coral;
  text-align: center;
  font-size: 0.1302rem;
  line-height: 100%;
  border-radius: 0.026rem;
}

.bon span:last-child:hover {
  cursor: pointer;
}
.change {
  width: 90%;
  height: 0.1823rem;
  margin: 0.1042rem auto;
  border-radius: 0.0521rem;
  position: relative;
}
.change span {
  font-size: 0.1042rem;
  display: block;
  text-align: center;
  padding-top: 0.026rem;
  width: 0.5208rem;
  height: 0.1563rem;
  background-color: #aaa;
  position: absolute;
  border-radius: 0.0781rem;
}
.change span:hover {
  color: coral;
  cursor: pointer;
}
.change span:first-child {
  left: 25%;
}
.change span:last-child {
  right: 25%;
}
.state {
  width: 95%;
  margin: 0.1042rem auto;
  height: 0.2083rem;
  border-left: 0.0521rem solid red;
  border-top: 0.0156rem solid red;
  border-right: 0.0156rem solid red;
  border-bottom: 0.0156rem solid red;
  border-radius: 0.026rem;
}
.state p {
  text-indent: 1em;
  font-size: 0.0938rem;
  padding-top: 0.0469rem;
}
.reviewx {
  margin: 0.1042rem auto;
  width: 95%;
}
.comment {
  margin: 0.0521rem auto;
  width: 97%;
  height: 2.6042rem;
}
</style>
