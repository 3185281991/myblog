import request from "./index";

export function AutoLogin() {
  return request({
    method: "get",
    url: "/protect/AutoLogin",
  });
}
export function RootLogin(root) {
  return request({
    method: "post",
    url: "/protect/RootLogin",
    data: root,
  });
}
