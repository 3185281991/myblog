<template>
  <div class="board">
    <div class="main">
      <labeltab v-if="$route.meta.info !== ''" />
      <div class="intro ql-snow" v-if="item != null">
        <div class="ql-editor" v-html="item.content"></div>
      </div>
      <div class="under" v-if="comments != null">
        <div class="reviewx">
          <review
            :comments="comments"
            :reviews="reviews"
            @reply="reply"
            info="留言"
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
  </div>
</template>

<script>
import { comRev } from "@/mixins/mixi.js";
import labeltab from "@/components/private/labeltab/labeltab.vue";
export default {
  name: "messageboard",
  data() {
    return {
      bid: "1",
    };
  },
  mixins: [comRev],
  components: { labeltab },
  methods: {},

  created() {
    this.getItem(this.bid);
    this.getComment(this.bid);
  },
  mounted() {
    this.$bus.$emit("pathRecord", "/messageBoard");
  },
};
</script>
<style scoped>
.board {
  width: 96%;
  height: 100%;
  margin: 0 auto;
}
.board .main {
  width: 99%;
  margin: 0 auto;
}
.intro {
  margin-top: 0.0781rem;
  width: 100%;
  height: 1.8229rem;
  background: linear-gradient(
    110.13149366494508deg,
    rgba(231, 244, 245, 1) 5.533854166666667%,
    rgba(78, 240, 250, 1) 96.67968749999999%
  );
}
.intro p {
  text-indent: 2em;
  width: 95%;
  margin: 0 auto;
  padding-top: 0.026rem;
  display: block;
  font-size: 0.1094rem;
  line-height: 2em;
}
.under {
  background: #fff;
}
.comment {
  width: 96%;
  margin: 0 auto;
}
</style>
