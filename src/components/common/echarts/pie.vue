<template>
  <div class="pie" ref="echarts" v-if="pieData !== []"></div>
</template>

<script>
// import * as echarts from "echarts";
export default {
  name: "pie",
  data() {
    return {
      echarts: null,
      options: {
        title: {
          text: "Ranking of Blog",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
          top: "10%",
        },
        toolbox: {
          feature: {
            //保存为图片
            saveAsImage: {},
            restore: {},
          },
          right: "2%",
        },
        series: [
          {
            name: "Ranking",
            type: "pie",
            radius: "50%",
            center: ["65%", "55%"],
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      },
    };
  },
  props: {
    pieData: {
      type: Array,
      default() {
        return [];
      },
    },
    type: {
      type: String,
      default() {
        return "";
      },
    },
  },
  components: {},
  watch: {
    pieData() {
      let seriesData = [];
      for (let i of this.pieData) {
        let name = i.title;
        let value = i[this.type];
        let obj = { value, name };
        seriesData.push(obj);
      }
      this.options.series[0].data = seriesData;
      this.initData();
    },
  },
  methods: {
    initData() {
      if (this.echart == null) {
        // 如果不存在，就进行初始化
        this.echart = echarts.init(this.$refs.echarts);
      }
      this.echart.setOption(this.options);
    },
  },
};
</script>
<style scoped>
.pie {
  width: 100%;
  height: 100%;
}
</style>
