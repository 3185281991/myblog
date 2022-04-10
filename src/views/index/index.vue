<template>
  <div class="index">
    <div v-if="allData !== null"><commonView :AllData="allData" /></div>
  </div>
</template>

<script>
import { getAllBlog } from "@/network/data.js";
import commonView from "@/components/private/commonView/commonView.vue";
export default {
  name: "index",
  data() {
    return {
      allData: null,
    };
  },
  components: { commonView },
  computed: {},
  methods: {
    //首页信息获取
    getAllBlog() {
      getAllBlog().then((res) => {
        this.allData = res.data;
      });
    },
  },
  created() {
    this.getAllBlog();
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
    this.$bus.$emit("pathRecord", "/index");
  },
};
</script>
<style scoped>
.index {
  width: 96%;
  margin: 0 auto;
}
</style>
