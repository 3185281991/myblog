//格式化日期
export function formatData(data, fmt) {
  // RegExp.$1表示 /y+/.test(fmt)  匹配到的结果
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(
      RegExp.$1,
      (data.getFullYear() + "").substring(4 - RegExp.$1.length)
    );
  }
  let o = {
    "M+": data.getMonth() + 1,
    "d+": data.getDate(),
    "h+": data.getHours(),
    "m+": data.getMinutes(),
    "s+": data.getSeconds(),
  };
  for (let k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      let str = o[k] + "";
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length === 1 ? str : padLeftZero(str)
      );
    }
  }
  return fmt;
}
function padLeftZero(str) {
  return ("00" + str).substring(str.length);
}
