<template>
  <div class="con" ref="echarts" v-if="barData !== []"></div>
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
          text: "分类统计",
        },
        //鼠标hover的提示信息
        tooltip: {
          //axis对应有类目轴，item为无类目轴
          trigger: "axis",
        },
        //工具栏组件
        toolbox: {
          right: "2%",
          feature: {
            //保存为图片
            saveAsImage: {},
            magicType: { type: ["line"] },
            restore: {},
          },
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
        series: [
          {
            type: "bar",
            data: [],
            itemStyle: {
              //通常情况下：
              //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
              color: function (params) {
                var colorList = [
                  "#dd526b",
                  "#9462e5",
                  "#a6a6a6",
                  "#e1bb22",
                  "#39c362",
                  "#3ed1cf",
                ];
                return colorList[params.dataIndex];
              },
              //鼠标悬停时：
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0, 0, 0, 0.5)",
            },
          },
        ],
      },
    };
  },
  props: {
    barData: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  components: {},
  watch: {
    barData() {
      let xList = [];
      let yData = [];
      for (let i of this.barData) {
        xList.push(i.type);
        yData.push(i.num);
      }
      this.options.xAxis.data = xList;
      this.options.series[0].data = yData;
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
