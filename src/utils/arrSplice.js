export function getRandomArray(arr, count) {
  if (arr !== null) {
    let m = arr.length,
      t,
      i;
    while (m) {
      i = Math.floor(Math.random() * m--);
      t = arr[m];
      arr[m] = arr[i];
      arr[i] = t;
    }
    if (count > arr.length) {
      return arr;
    }
    return arr.slice(0, count);
  }
}
