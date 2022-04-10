import request from "./index";
//所有博客顺序bid
export function getBlogsSequence() {
  return request({ url: "/getBlogsSequence" });
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
//删除博客
export function deleteById(bid) {
  return request({
    method: "post",
    url: "/deleteById",
    data: bid,
  });
}
//添加博客
export function addBlog(blog) {
  return request({
    method: "post",
    url: "/addBlog",
    data: blog,
  });
}
//分类数量
export function groupByType() {
  return request({
    method: "post",
    url: "/groupByType",
  });
}
//统计总数
export function getAllSum() {
  return request({
    method: "post",
    url: "/getAllSum",
  });
}
//最新8条博客
export function getNew() {
  return request({
    url: "/getNew",
  });
}
//点赞，评论，浏览排行
export function laudsPx() {
  return request({
    url: "/laudsPx",
  });
}
export function viewsPx() {
  return request({
    url: "/viewsPx",
  });
}
export function commentsPx() {
  return request({
    url: "/commentsPx",
  });
}
//作者发篇数量
export function authorText() {
  return request({
    method: "post",
    url: "/authorText",
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

//修改留言板内容
export function updateMessageBoard(content) {
  return request({
    method: "post",
    url: "/updateMessageBoard",
    data: content,
  });
}
//添加每日一句
export function addImpassion(text) {
  return request({
    method: "post",
    url: "/addImpassion",
    data: text,
  });
}
//添加广告部分信息
export function addAdvertise(text) {
  return request({
    method: "post",
    url: "/addAdvertise",
    data: text,
  });
}
//impassion,advertise信息
export function getAllIAdvertise() {
  return request({
    url: "/getAllIAdvertise",
  });
}
export function getAllImpassion() {
  return request({
    url: "/getAllImpassion",
  });
}
//根据id删除impassion,advertise信息
export function deAdvertiseById(id) {
  return request({
    url: "/deAdvertiseById",
    method: "post",
    data: id,
  });
}
export function deImpassionById(id) {
  return request({
    url: "/deImpassionById",
    method: "post",
    data: id,
  });
}
//添加一张轮播图片
export function addSwiperImg(file) {
  return request({
    method: "post",
    url: "/addSwiperImg",
    data: file,
  });
}
//所有轮播图片路径
export function getAllImg() {
  return request({
    method: "post",
    url: "/getAllImg",
  });
}
//根据id删除图片路径
export function deleteImgById(id) {
  return request({
    method: "post",
    url: "/deleteImgById",
    data: id,
  });
}
