<template>
  <div class="simple">
    <labeltab v-if="$route.meta.info !== ''" />
    <div v-if="allData !== null"><commonView :AllData="allData" /></div>
    <div class="prompt" v-if="isShow">
      <p>该分类暂时没有记录！</p>
    </div>
  </div>
</template>

<script>
import { getBlogByType } from "@/network/data.js";
import labeltab from "@/components/private/labeltab/labeltab.vue";
import commonView from "@/components/private/commonView/commonView.vue";
export default {
  name: "simple",
  data() {
    return {
      allData: null,
      isShow: false,
    };
  },
  components: { commonView, labeltab },
  computed: {},
  methods: {
    //首页信息获取
    getBlogByType() {
      let category = this.$route.meta.title;
      getBlogByType(category).then((res) => {
        this.allData = res.data;
        if (this.allData.length == 0) {
          this.isShow = true;
        }
      });
    },
  },
  created() {
    this.getBlogByType();
  },
  watch: {
    allData() {
      this.$nextTick(() => {
        let name = this.$route.path.slice(1);
        let data = this.allData;
        let obj = { name: name, data: data };
        this.$store.commit("addData", obj);
        this.$store.commit("getActiveData", name);
      });
    },
  },
  mounted() {
    this.$bus.$emit("pathRecord", "/simple");
  },
};
</script>
<style scoped>
.simple {
  width: 96%;
  margin: 0 auto;
}
.prompt {
  margin-top: 0.0781rem;
  width: 100%;
  height: 0.4167rem;
  background-color: coral;
  display: grid;
}
.prompt p {
  margin: auto;
  display: block;
  font-size: 0.1146rem;
  font-weight: bold;
  text-align: center;
  width: 100%;
}
</style>
