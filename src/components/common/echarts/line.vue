<template>
  <div class="con" ref="echarts" v-if="lineData !== []"></div>
</template>

<script>
// import * as echarts from "echarts";
export default {
  name: "con",
  data() {
    return {
      echarts: null,
      options: {
        //图表的标题
        title: {
          text: "作者统计",
          top: "7%",
        },
        //鼠标hover的提示信息
        tooltip: {
          //axis对应有类目轴，item为无类目轴
          trigger: "axis",
          position: function (point, params, dom, rect, size) {
            // 鼠标坐标和提示框位置的参考坐标系是：以外层div的左上角那一点为原点，x轴向右，y轴向下
            // 提示框位置
            let x = 0; // x坐标位置
            let y = 0; // y坐标位置

            // 当前鼠标位置
            let pointX = point[0];
            let pointY = point[1];

            // 外层div大小
            // var viewWidth = size.viewSize[0];
            // var viewHeight = size.viewSize[1];

            // 提示框大小
            let boxWidth = size.contentSize[0];
            let boxHeight = size.contentSize[1];

            // boxWidth > pointX 说明鼠标左边放不下提示框
            if (boxWidth > pointX) {
              x = 5;
            } else {
              // 左边放的下
              x = pointX - boxWidth;
            }

            // boxHeight > pointY 说明鼠标上边放不下提示框
            if (boxHeight > pointY) {
              y = 5;
            } else {
              // 上边放得下
              y = pointY - boxHeight;
            }

            return [x, y];
          },
        },
        //工具栏组件
        toolbox: {
          right: "1%",
          top: "8%",
          feature: {
            //保存为图片
            saveAsImage: {},
            magicType: { type: ["bar", "stack"] },
            restore: {},
          },
        },
        legend: {
          type: "scroll",
          left: "8%",
        },
        //图表的位置
        grid: {
          left: "0%",
          botton: "0%",
          right: "0%",
          bottom: "0%",
          containLabel: true,
        },
        //x轴的类目数据
        xAxis: {
          type: "category",
          data: [],
          boundaryGap: true,
        },
        yAxis: {
          type: "value",
        },
        //对应的系列数据
        series: [],
      },
    };
  },
  props: {
    lineData: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  components: {},
  watch: {
    lineData() {
      let xList = ["life", "Knowledge", "work", "About"];
      this.options.xAxis.data = xList;
      for (let i of this.lineData) {
        let obj = { type: "line", name: "", data: [] };
        obj.name = i.author;
        obj.data.push(i.life);
        obj.data.push(i.knowledge);
        obj.data.push(i.work);
        obj.data.push(i.about);
        this.options.series.push(obj);
      }
      this.initData();
    },
  },
  methods: {
    initData() {
      this.echart = echarts.init(this.$refs.echarts);
      this.echart.setOption(this.options);
    },
  },
};
</script>
<style scoped>
.con {
  width: 100%;
  height: 100%;
}
</style>
