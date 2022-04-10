import Toast from "./toast";
const obj = {};
obj.install = function (Vue) {
  //创建组件构造器
  const totalContrustor = Vue.extend(Toast);
  //new方式，根据组件构造器，创建一个组件对象
  const toast = new totalContrustor();
  //将组建手动挂载到元素上
  toast.$mount(document.createElement("div"));
  //此时的toast.$el就是div
  document.body.appendChild(toast.$el);

  Vue.prototype.$toast = toast;
};
export default obj;
