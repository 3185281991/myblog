export function scrollToTop() {
  //点击换页，返回顶部
  clearInterval(timeTop);
  const timeTop = setInterval(() => {
    let step =
      (window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop) / 10;
    let scrollTop =
      window.pageYOffset ||
      document.documentElement.scrollTop ||
      document.body.scrollTop;
    if (scrollTop <= 2) {
      clearInterval(timeTop);
    }
    window.scrollTo(0, scrollTop - step);
  }, 12);
}
