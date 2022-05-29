<template>
  <div class="com">
    <span class="info">发表评论</span>
    <span class="change" @click="exchange">{{ text }}</span>
    <form action="">
      <textarea
        v-model="value"
        ref="con"
        name=""
        id=""
        placeholder="欢迎您的阅读，有什么想表达的吗？"
      ></textarea>
      <div>
        <span class="iconfont icon-yonghu"></span
        ><input
          v-model="username"
          type="text"
          id="username"
          placeholder="昵称"
          autocomplete="on"
        />
      </div>
      <div>
        <span class="iconfont icon-youxiang"></span
        ><input
          v-model="email"
          type="email"
          id="email"
          placeholder="电子邮箱"
          autocomplete="on"
        />
      </div>
      <input
        v-model="submitext"
        class="last"
        type="submit"
        @click.prevent="submit"
      />
    </form>
  </div>
</template>

<script>
import { addComments, addReview, addCommentNum } from "@/network/data.js";
import postData from "@/utils/qs.js";
export default {
  name: "comment",
  data() {
    return {
      username: "",
      email: "",
      value: "",
      submitext: "发表评论",
      text: "",
    };
  },
  props: {
    bid: {
      type: String,
      default() {
        return "";
      },
    },
    isReply: {
      type: Boolean,
      default() {
        return false;
      },
    },
    commentsInfo: {
      type: Object,
      default() {
        return {};
      },
    },
  },
  components: {},
  methods: {
    //判断是否评论过，拿到之前的数据
    judgeLocal() {
      if (localStorage.getItem("username") && localStorage.getItem("email")) {
        this.username = localStorage.getItem("username");
        this.email = localStorage.getItem("email");
      }
    },
    //提交评论或回复信息
    submit() {
      let reg = /[\w]+(\.[\w]+)*@[\w]+(\.[\w])+/;
      if (this.value == "" || this.username == "" || this.email == "") {
        this.$toast.show("您还有选项未填完。", 2000);
      } else {
        if (!reg.test(this.email)) {
          this.$toast.show("请输入正确的邮箱格式。", 2000);
          this.email = "";
        } else if (this.username.length > 10) {
          this.$toast.show("用户名请勿超过10个字。", 2000);
        } else {
          //默认false为评论事件,否则就是根据传过来的回复事件
          if (this.isReply === false) {
            let comTime = Math.floor(new Date().getTime() / 1000);
            let comBid = this.bid;
            let cusName = this.username;
            let email = this.email;
            let comValue = this.value;
            let Customer = { comBid, cusName, email, comValue, comTime };
            let bid = this.bid;
            addComments(postData(Customer)).then((res) => {
              this.$bus.$emit("updateComment", bid);
              localStorage.setItem("username", this.username);
              localStorage.setItem("email", this.email);
              this.$toast.show(res.data, 1500);
            });
            addCommentNum(postData({ bid })).then();
            //这里不需要刷新，通知父组件重新获取一下数据即可
            this.value = "";
          } else {
            let rBid = this.commentsInfo.comBid;
            let cusName = this.commentsInfo.cusName;
            let cusEmail = this.commentsInfo.email;
            let comValue = this.commentsInfo.comValue;
            let comTime = this.commentsInfo.comTime;
            let reName = this.username;
            let reEmail = this.email;
            let reContent = this.value;
            let reTime = Math.floor(new Date().getTime() / 1000);
            let review = {
              rBid,
              cusName,
              cusEmail,
              reName,
              reEmail,
              reContent,
              reTime,
              comValue,
              comTime,
            };
            let bid = this.bid;
            addReview(postData(review)).then((res) => {
              this.$bus.$emit("updateComment", bid);
              this.$toast.show(res.data, 1500);
            });
            //这里不需要刷新，通知父组件重新获取一下数据即可
            this.value = "";
            addCommentNum(postData({ bid })).then();
            this.$emit("replyChange");
          }
        }
      }
    },
    //失去焦点
    exchange() {
      if (this.isReply === true) {
        this.$emit("replyChange");
      }
    },
  },
  created() {
    this.judgeLocal();
  },
  watch: {
    //监听变化后获得焦点
    isReply() {
      this.$nextTick(() => {
        this.$refs.con.focus();
        if (this.isReply === true) {
          this.submitext = "发表回复";
          this.text = "发表评论";
        } else {
          this.submitext = "发表评论";
          this.text = "";
        }
      });
    },
  },
};
</script>
<style scoped>
.com {
  width: 100%;
  height: 100%;
  position: relative;
}
.com .info {
  user-select: none;
  display: block;
  font-size: 0.1042rem;
  font-weight: bold;
  padding-left: 0.1563rem;
  padding-top: 0.0521rem;
}
.com .change {
  top: 2%;
  right: 4%;
  display: block;
  position: absolute;
  font-size: 0.1042rem;
  font-weight: bold;
}
.change:hover {
  cursor: pointer;
  color: coral;
}
.com form {
  width: 100%;
  margin-top: 0.0781rem;
  position: relative;
}
form textarea {
  margin-top: 0.0781rem;
  width: 100%;
  height: 1.0417rem;
  border: 1px solid #aaa;
  border-radius: 0.1042rem;
  font-size: 0.0938rem;
  padding-top: 0.026rem;
  text-indent: 0.0521rem;
}
form div {
  margin-top: 0.0521rem;
  width: 100%;
}
form div span {
  font-size: 0.1563rem;
}
form div input {
  border: 0.0026rem solid black;
  margin-left: 0.026rem;
  text-indent: 0.5em;
  font-size: 0.0938rem;
  width: 96%;
  height: 0.2083rem;
  margin-top: 0.1042rem;
}
form input.last {
  margin-top: 0.1302rem;
  width: 100%;
  height: 0.2083rem;
  text-align: center;
  color: white;
  font-size: 0.1302rem;
  background-color: #d9534f;
}
input.last:hover {
  background-color: #c9302c;
}
</style>
