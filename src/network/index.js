// import axios from "axios";
export default function request(config) {
  const instance = axios.create({
    baseURL: "http://124.221.69.31:8888/myBlog",
    timeout: 4000,
  });
  instance.interceptors.request.use(
    (config) => {
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
