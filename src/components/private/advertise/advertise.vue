<template>
  <div class="advertise">
    <div class="adtop">
      <span>简单介绍</span>
    </div>
    <div class="adcon">
      <p>{{ getInfo }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "advertise",
  data() {
    return {};
  },
  props: {
    adverInfo: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  computed: {
    getInfo: function () {
      if (this.adverInfo) {
        if (this.adverInfo === []) {
          return " ";
        } else {
          return this.adverInfo[0].text;
        }
      }
      return "";
    },
  },
  components: {},
  methods: {},
  mounted() {},
  created() {},
};
</script>
<style scoped>
.advertise {
  width: 100%;
  height: 1.4583rem;
  background-color: #fff;
}
.adtop {
  width: 100%;
  height: 18%;
}
.adtop span {
  font-size: 0.1146rem;
  display: block;
  margin-left: 0.1042rem;
  padding-top: 0.0625rem;
}
.adcon {
  border-top: #ffbcb5 0.0104rem solid;
  width: 100%;
  height: 80%;
}
.adcon p {
  display: block;
  width: 90%;
  margin: 0.0781rem auto;
  font-size: 0.0938rem;
  line-height: 2em;
  height: 1.0417rem;
  overflow: hidden;
}
</style>
