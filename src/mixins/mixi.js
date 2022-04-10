import {
  getContent,
  getCommentByBid,
  getReviewsByEmail,
} from "@/network/data.js";
import postData from "@/utils/qs.js";
import review from "@/components/private/review/review.vue";
import comment from "@/components/private/comment/comment.vue";
export const comRev = {
  data() {
    return {
      comments: null,
      reviews: null,
      isReply: false,
      replyObj: {},
      item: null,
    };
  },
  components: { comment, review },
  methods: {
    //文章内容的接口
    getItem(bid) {
      getContent(bid).then((res) => {
        this.item = res.data[0];
        this.lauds = this.item.lauds;
      });
    },
    //获取评论信息
    getComment(bid) {
      getCommentByBid(bid).then((res) => {
        this.comments = res.data;
        //遍历拿到回复信息
        let rs = [];
        let data = [];
        for (let i = 0; i < this.comments.length; i++) {
          let comBid = this.comments[i].comBid;
          let cusName = this.comments[i].cusName;
          let email = this.comments[i].email;
          let comValue = this.comments[i].comValue;
          let comTime = this.comments[i].comTime;
          let obj = { comBid, cusName, email, comValue, comTime };
          //将得到的结果保存到一个数组中
          let result = getReviewsByEmail(postData(obj));
          data.push(result);
        }
        //这里拿到的回复必须是顺序的，不改动会乱序，通过peomise.all方法参数为一个数组，这里就会顺序拿到结果
        Promise.all(data).then((itemList) => {
          itemList.forEach((element) => {
            rs.push(element.data);
          });
        });
        this.reviews = rs;
      });
    }, //触发回复
    reply(item) {
      this.replyObj = item;
      this.isReply = true;
    },
    changeAlt() {
      this.isReply = false;
    },
  },
  mounted() {
    this.$bus.$on("updateComment", (bid) => {
      this.$bus.$emit("updateIconList", bid);
      this.getComment(bid);
    });
    this.$bus.$on("updateIconList", (bid) => {
      this.getItem(bid);
    });
  },
};
