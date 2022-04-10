<template>
  <div class="introduce">
    <div class="intop">
      <span>Impassioned</span>
    </div>
    <div class="incon">
      <p>{{ getText }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "introduce",
  data() {
    return {};
  },
  props: {
    impassion: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  computed: {
    getText: function () {
      if (this.impassion) {
        if (this.impassion === []) {
          return " ";
        } else {
          return this.impassion[0].text;
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
.introduce {
  width: 100%;
  height: 1.7708rem;
  background-color: #fff;
}
.intop {
  width: 100%;
  height: 18%;
}
.intop span {
  font-size: 0.1146rem;
  display: block;
  margin-left: 0.1042rem;
  padding-top: 0.0781rem;
}
.incon {
  border-top: #ffbcb5 0.0104rem solid;
  width: 100%;
  height: 80%;
}
.incon p {
  display: block;
  width: 90%;
  margin: 0.0781rem auto;
  font-size: 0.0938rem;
  line-height: 2em;
  height: 1.1719rem;
  overflow: hidden;
}
</style>
