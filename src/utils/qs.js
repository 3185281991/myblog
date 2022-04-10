import qs from "qs";
export default function postData(obj) {
  return qs.stringify(obj);
}
