<template>
  <div class="home">
    <header><textinfo /></header>
    <aside></aside>
    <backtop v-show="isShow" />
    <navbar ref="navbar" :isFixed="navFixed" />

    <main>
      <div class="content" ref="content">
        <div class="center" ref="center">
          <router-view :key="$route.path" />
        </div>
        <div class="right" ref="right" :class="{ rfixed: rightFix }">
          <right />
        </div>
      </div>
    </main>
    <footer>
      <div>
        <p>
          Copyright © 2022-3-25 个人博客 | | Theme By
          <a
            href="http://9iphp.com"
            title=""
            target="_blank"
            data-original-title="Specs' Bolg"
            >Specs</a
          >
        </p>
      </div>
    </footer>
  </div>
</template>

<script>
import textinfo from "@/components/private/textinfo/textinfo.vue";
import navbar from "@/components/private/navbar/navbar.vue";
import backtop from "@/components/common/backtop/backtop.vue";
import right from "@/views/home/right/right.vue";
export default {
  name: "home",
  data() {
    return {
      isShow: false,
      navFixed: false,
      rightFix: false,
      rightHeight: 0,
      rfixed: 0,
    };
  },
  components: { textinfo, navbar, backtop, right },
  methods: {
    judgeScroll() {
      //滚动的距离
      let scroll =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      //原始64，滚动之后就为0了
      let fixedHeight = this.$refs.navbar.$el.offsetTop;
      //固定导航栏和侧边栏
      if (scroll > fixedHeight) {
        this.navFixed = true;
        this.rightFix = true;
      }
      //默认的64
      if (scroll < 64) {
        this.navFixed = false;
        this.rightFix = false;
      }
      //返回顶部是否显示
      if (scroll > 250) {
        this.isShow = true;
      } else {
        this.isShow = false;
      }

      //侧边栏固定
      //先拿到固定之后的侧边栏高度，在拿到中间盒子的高度以及该盒子与滚动条的差
      //当差开始小于侧边栏高度时就让让侧边栏向上移动，距离是负数，根大小为192px，因此是侧边栏高度减去差除以192
      //还要减去之前固定侧边栏设置的顶部距离0.3229rem，若不小于，就设为之前的0.3229高度即可
      if (scroll > this.$refs.right.offsetTop) {
        this.rightHeight = this.$refs.right.offsetHeight;
        let content = this.$refs.content.offsetHeight;
        let bottom = content - scroll;
        if (bottom <= this.rightHeight) {
          let diff = this.rightHeight - bottom;
          //开始让侧边栏滚动
          this.$refs.right.style.top = -(diff / 192) - 0.3229 + "rem";
        } else {
          this.$refs.right.style.top = 0.3229 + "rem";
        }
      }
    },
  },
  mounted() {
    window.addEventListener("scroll", this.judgeScroll);
  },
};
</script>
<style scoped>
.home {
  width: 100%;
  background-color: #e9ebea;
}
header {
  position: relative;
  width: 100%;
  height: 0.4167rem;
  background-color: #333;
}
aside {
  top: 50%;
  transform: translateY(-50%);
  position: fixed;
  width: 1.0417rem;
  height: 2.3438rem;
}

main {
  width: 100%;
}
.content {
  margin: 0.1563rem auto;
  width: 7.8125rem;
  display: flex;
}
.center {
  width: 70%;
  height: 100%;
  min-height: 6.1198rem;
}
.right {
  width: 30%;
}
.rfixed {
  position: fixed;
  top: 0.3229rem;
  width: 23.5%;
  right: 10.85%;
}
footer {
  width: 100%;
  height: 0.5208rem;
  background-color: #111111;
  position: relative;
}
footer div {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  width: 80%;
  height: 25%;
}
footer div p {
  text-align: center;
  font-size: 0.0938rem;
  color: white;
}
footer div p a {
  color: white;
}
footer div p a:hover {
  color: coral;
}
</style>
