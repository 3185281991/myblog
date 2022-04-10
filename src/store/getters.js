export default {
  //当在进入文章详情页是进行刷新操作，vuex中的数据时会空值
  //这时候应该将之前保存好的会话信息取出来赋值给对应vuex信息
  //取值必须转化为对象JSON.parse()
  getIndex(state) {
    if (state.conData == null) {
      let index = parseInt(sessionStorage.getItem("activeIndex"));
      state.activeIndex = index;
      return index;
    }
    return state.activeIndex;
  },
  getCateData(state) {
    let cateData = JSON.parse(sessionStorage.getItem("cateData"));
    let conData = JSON.parse(sessionStorage.getItem("conData"));
    if (state.conData == null) {
      state.conData = conData;
      state.cateData = cateData;
      return conData;
    }
    return state.conData;
  },
};
