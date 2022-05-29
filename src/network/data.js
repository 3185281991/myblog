import request from "./index";

//进入首页是显示的博客信息
export function getAllBlog() {
  return request({ url: "/getAllBlog" });
}
//搜索页的默认的数据
export function SearchInfo() {
  return request({ url: "/SearchInfo" });
}
//标签查询
export function getLabel() {
  return request({ url: "/getLabel" });
}
//侧边栏推荐
export function getRecommend() {
  return request({ url: "/Recommend" });
}
//侧边栏名言
export function getImpassion() {
  return request({ url: "/getImpassion" });
}
//侧边栏介绍
export function getAdvertise() {
  return request({ url: "/getAdvertise" });
}
//侧边栏随机轮播图
export function randomImg() {
  return request({ url: "/randomImg", method: "post" });
}
//模糊查询
export function likeFind(str) {
  return request({
    url: "/likeFind",
    params: {
      str,
    },
  });
}

//获得不同类型的博客
export function getBlogByType(type) {
  return request({
    url: "/getBlogByType",
    params: {
      type,
    },
  });
}

//文章具体信息的获得,留言板是1
export function getContent(bid) {
  return request({
    url: "/getById",
    params: {
      bid,
    },
  });
}

//获得回复信息
export function getReviewsByEmail(customer) {
  return request({
    method: "post",
    url: "/getReviewsByEmail",
    data: customer,
  });
}
//获取评论信息
export function getCommentByBid(bid) {
  return request({
    url: "/getCommentByBid",
    params: {
      bid,
    },
  });
}

//添加用户及评论信息
export function addComments(Customer) {
  return request({
    method: "Post",
    url: "/addComment",
    data: Customer,
  });
}

//判断当前用户是否已经针对该文章点过赞
export function isLaud(obj) {
  return request({
    method: "post",
    url: "/isLaud",
    data: obj,
  });
}
//点赞+1
export function addLaud(bid) {
  return request({
    method: "post",
    url: "/addLaud",
    data: bid,
  });
}
//观看数加1
export function addView(bid) {
  return request({
    method: "post",
    url: "/addView",
    data: bid,
  });
}
//博客的评论数+1
export function addCommentNum(bid) {
  return request({
    method: "post",
    url: "/addCommentNum",
    data: bid,
  });
}
//修改对应的点赞记录
export function checkLaud(obj) {
  return request({
    method: "post",
    url: "/checkLaud",
    data: obj,
  });
}

//添加用户和博客文章记录
export function insertLauds(lauds) {
  return request({
    url: "/insertLauds",
    method: "post",
    data: lauds,
  });
}

//添加用户回复
export function addReview(review) {
  return request({
    method: "post",
    url: "/addReview",
    data: review,
  });
}
