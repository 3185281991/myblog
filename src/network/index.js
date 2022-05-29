// import axios from "axios";
export default function request(config) {
  const instance = axios.create({
    baseURL: "http://124.221.69.31:8888/myBlog",
    timeout: 4000,
  });
  instance.interceptors.request.use(
    (config) => {
      let token = localStorage.getItem("token");
      if (token) {
        config.headers.token = token;
      }
      return config;
    },
    (err) => {
      console.log(err);
    }
  );
  instance.interceptors.response.use(
    (res) => {
      return res;
    },
    (err) => {
      console.log(err);
    }
  );
  return instance(config);
}
