<template>
  <div class="navBar" :class="{ fixed: isFixed }">
    <ul>
      <li
        v-for="(item, index) in navData"
        :key="item.name"
        :class="{
          active: activeIndex === index,
        }"
        @click="change(index)"
      >
        <keep-alive>
          <router-link :to="item.path">{{ item.meta.title }}</router-link>
        </keep-alive>
      </li>
    </ul>
    <div class="search">
      <div>
        <input
          v-model="searchInput"
          type="text"
          name="search"
          id=""
          placeholder="请输入搜索信息"
        /><span class="iconfont icon-sousuo" @click="search"></span
        ><a href="javascript:void(0);" @click.prevent="search">搜索</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "navbar",
  data() {
    return {
      searchInput: "",
      activeIndex: 0,
    };
  },
  props: {
    isFixed: {
      type: Boolean,
      data() {
        return false;
      },
    },
  },
  computed: {
    navData: {
      get() {
        return this.$router.options.routes[1].children.slice(3);
      },
    },
  },
  components: {},
  methods: {
    //输入判断
    search() {
      this.$bus.$emit("isLeave", "true");
      sessionStorage.setItem("isLeave", "true");
      if (this.searchInput === "") {
        this.$router.push("/search/" + "default");
      } else {
        this.$router.push("/search/" + this.searchInput);
        this.searchInput = "";
        //这里点击后同样要修改样式
        this.activeIndex = null;
        sessionStorage.setItem("isLeave", "true");
      }
    },
    change(index) {
      this.activeIndex = index;
      sessionStorage.setItem("index", index);
      //每次点击导航栏后设为false
      sessionStorage.setItem("isLeave", "false");
    },
    getIndex() {
      //判断会话中是否包含了isleave以及index
      let flag = sessionStorage.getItem("isLeave");
      if (flag === "true") {
        this.activeIndex = null;
      } else {
        if (sessionStorage.getItem("index")) {
          this.activeIndex = parseInt(sessionStorage.getItem("index"));
        }
      }
    },
  },
  created() {
    this.getIndex();
  },
  mounted() {
    //事件总线接收
    this.$bus.$on("isLeave", (msg) => {
      sessionStorage.setItem("isLeave", "true");
      if (msg === "true") {
        //改变导航栏下标
        this.activeIndex = null;
      }
    });
  },
};
</script>
<style scoped>
.navBar {
  z-index: 9;
  width: 100%;
  height: 0.3125rem;
  background-color: rgba(255, 255, 255, 0.9);
  position: relative;
}
.fixed {
  position: fixed;
  top: 0;
}
.navBar ul {
  position: absolute;
  left: 15%;
  width: 49%;
  height: 100%;
  position: relative;
}
.active {
  background-color: #e9ebea;
}
.navBar ul li {
  float: left;
  text-align: center;
  height: 100%;
  width: 0.5208rem;
  font-size: 0.0885rem;
  position: relative;
  transition: 0.5s;
}

ul li a {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  display: block;
  width: 100%;
  height: 100%;
  padding-top: 0.1823rem;
  color: #777;
}
.navBar ul li:hover {
  cursor: pointer;
}
.navBar ul li:hover a {
  color: #333;
}
.search {
  position: absolute;
  width: 1.8229rem;
  right: 15%;
  top: 50%;
  transform: translateY(-50%);
}
.search input {
  padding-left: 0.0313rem;
  border-radius: 5px;
  width: 1.1458rem;
  height: 0.1563rem;
  float: left;
  border: 1px solid black;
}
.search span {
  display: block;
  position: absolute;
  right: 40%;
  font-size: 0.1302rem;
  width: 0.1563rem;
  height: 0.1563rem;
  text-align: center;
}
.search span:hover {
  color: coral;
}
.search a {
  float: left;
  display: block;
  width: 0.2865rem;
  height: 0.1563rem;
  padding-top: 0.0156rem;
  text-align: center;
  font-size: 0.0938rem;
  margin-left: 0.0365rem;
  background-color: #d9534f;
  border-color: #d43f3a;
  color: #fff;
  border-radius: 0.026rem;
}
.search a:hover {
  cursor: pointer;
  background-color: #c9302c;
  border-color: #ac2925;
}
</style>
