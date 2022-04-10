<template>
  <div class="navSide">
    <div class="nav">
      <el-col>
        <span class="title">控制台操作</span>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          @select="change"
        >
          <router-link to="/census">
            <el-menu-item index="1">
              <i class="el-icon-s-data"></i>
              <span slot="title">博客统计</span>
            </el-menu-item> </router-link
          ><router-link to="/allBlog">
            <el-menu-item index="2">
              <i class="el-icon-s-grid"></i>
              <span slot="title">博客记录</span>
            </el-menu-item>
          </router-link>
          <router-link to="/write">
            <el-menu-item index="3">
              <i class="el-icon-circle-plus"></i>
              <span>添加博客</span>
            </el-menu-item>
          </router-link>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span>其他操作</span> </template
            ><router-link to="/operate">
              <el-menu-item-group>
                <router-link to="/operate">
                  <el-menu-item index="4-1">sideBar</el-menu-item>
                </router-link>
                <el-menu-item index="4-3" disabled>other</el-menu-item>
              </el-menu-item-group>
            </router-link>
          </el-submenu>
          <el-menu-item index="5" disabled>
            <i class="el-icon-loading"></i>
            <span>尚未开发</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </div>
  </div>
</template>

<script>
export default {
  name: "navSide",
  data() {
    return {
      activeIndex: "1",
    };
  },
  components: {},
  methods: {
    change(key) {
      //点击时的下标需要在刷新页面时再次获取
      sessionStorage.setItem("activeIndex", key);
    },
  },
  created() {
    //刷新判断下标
    let activeIndex = sessionStorage.getItem("activeIndex");
    if (activeIndex) {
      this.activeIndex = activeIndex;
    }
  },
};
</script>
<style scoped>
.navSide {
  width: 95%;
  height: 100%;
  float: right;
  background-color: #545c64;
}
.title {
  display: block;
  margin-top: 0.1042rem;
  margin-left: 30%;
  font-size: 0.1042rem;
  color: white;
}
.el-menu {
  margin-top: 0.1042rem;
  font-size: 0.0938rem;
}
.nav {
  width: 100%;
  margin: 0 auto;
  height: 100%;
}
</style>
