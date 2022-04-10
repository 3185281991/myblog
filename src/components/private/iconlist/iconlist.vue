<template>
  <div class="iconList">
    <ul>
      <li>
        <i class="iconfont icon-rili"></i
        ><span>{{ item.time | formatTime }}</span>
        <i>|</i>
      </li>
      <li>
        <i class="iconfont icon-fenlei"></i><span>{{ item.label }}</span>
        <i>|</i>
      </li>
      <li>
        <i class="iconfont icon-wode"></i><span>{{ item.author }}</span>
        <i>|</i>
      </li>
      <li>
        <i class="iconfont icon-pinglun"></i><span>{{ item.commentsNum }}</span>
        <i>|</i>
      </li>
      <li>
        <i class="iconfont icon-view"></i><span>{{ item.views }}</span>
        <i>|</i>
      </li>
      <li>
        <i class="iconfont icon-dianzan"></i>
        <span>{{ item.lauds }}</span>
        <i>|</i>
      </li>
    </ul>
  </div>
</template>

<script>
import { formatData } from "@/utils/utils.js";
export default {
  name: "iconlist",
  data() {
    return {};
  },
  props: {
    item: {
      type: Object,
      default() {
        return {};
      },
    },
  },
  components: {},
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
.iconList ul {
  margin: 0 auto;
  width: 94%;
  height: 0.2083rem;
}
.iconList ul li {
  text-align: center;
  float: left;
  width: 10%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  position: relative;
  overflow: hidden;
}
ul li:nth-child(1) {
  width: 22%;
}
ul li:nth-child(2) {
  width: 15%;
}
ul li:nth-child(3) {
  width: 12%;
}
ul li i:nth-child(1) {
  font-size: 0.1458rem;
}
ul li:last-child i:last-child {
  display: none;
}
</style>
