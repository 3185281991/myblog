export default {
  defaultInfo() {
    if (!localStorage.getItem("username") && !localStorage.getItem("email")) {
      localStorage.setItem("username1", "default");
      localStorage.setItem("email1", "default@qq.com");
    }
  },
  getAcitiveIndex(state, index) {
    //存放对应数据中的实际下标
    state.activeIndex = index;
    //每次点击的时候都把下标保存到会话中
    sessionStorage.setItem("activeIndex", state.activeIndex);
  },

  addData(state, data) {
    //每次添加时，先判断是否已经存在了
    let array = state.cateData;
    if (
      !array.some((ele) => {
        return ele.name == data.name;
      })
    ) {
      state.cateData.push(data);
      //同时将数据保存到会话中,以字符串形式
      sessionStorage.setItem("cateData", JSON.stringify(state.cateData));
    }
    // }
  },
  getActiveData(state, str) {
    if (!state.cateData == null) {
      for (let i = 0; i < state.cateData.length; i++) {
        if (state.cateData[i].name === str) {
          state.conData = state.cateData[i].data;
          sessionStorage.setItem("conData", JSON.stringify(state.conData));
        }
      }
    } else {
      let cateData = JSON.parse(sessionStorage.getItem("cateData"));
      for (let i = 0; i < cateData.length; i++) {
        if (cateData[i].name === str) {
          state.conData = cateData[i].data;
          sessionStorage.setItem("conData", JSON.stringify(cateData[i].data));
        }
      }
    }
  },
};
