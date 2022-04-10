import request from "./index";
export function RootLogin(root) {
  return request({
    method: "post",
    url: "/RootLogin",
    data: root,
  });
}
