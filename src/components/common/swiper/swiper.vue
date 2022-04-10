<template>
  <div class="swipperItem">
    <div class="swiper-container">
      <div class="swiper-wrapper">
        <slot></slot>
      </div>
      <!-- Add Pagination -->
      <div class="swiper-pagination"></div>
    </div>
  </div>
</template>

<script>
// import Swiper from "swiper";
export default {
  name: "swiper",
  data() {
    return {};
  },
  mounted() {
    new Swiper(".swiper-container", {
      loop: true,
      autoplay: {
        disableOnInteraction: false,
      },
      speed: 1000,
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
        dynamicBullets: true,
        dynamicMainBullets: 3,
      },
      observer: true,
      observeParents: true,
    });
  },
  methods: {},
};
</script>
<style scoped></style>
