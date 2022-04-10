## 第十一天

今天圆满完成所有任务

## elementui 上传栏

预览图片的显示直接决定上传该文件，此外针对这个

```
    //提交文件
    imgSubmit(file) {
      this.formData.append("file", file.file);
    },
```

当存在多张图片是我们提交表单的话也是将数据放入 formdata 中，而通过调用```以上方法会自动将全部图片上传到一个fiel中`
在后台顶一个 mutipart[] file 对应字段即可获得

```
this.$refs.imgForm.submit();

```

·上传成功后清空预览·

```
         //只清除了upload中的图片，但是data中的并未清除，因此还要清空data中的filelist
        this.$refs.imgForm.clearFiles();
        this.fileList = null;
```

## 关于首页轮播图

首页轮播图和添加博客存储的路径是一样的，只不过添加一个子目录即可
动态添加轮播图时，必须要等所有图片加载完才加载对应的 dom，使用 v-if 即：

```
      <swiper v-if="getLength">
        <div
          class="swiper-slide"
          v-for="(item, index) in swiperImg"
          :key="index"
        >
          <img :src="item.imgPath" />
        </div>
      </swiper>
```

自有这样设置的循环轮播才会生效

## 操作页

操作页遇到的问题在于添加数据时中间的对应数据显示栏必须更新，
这里使用事件总线触发，

```
this.$bus.$emit()
```

在操作页中的 mounted 中接受就可以完成数据更新

#### 中间数据栏的删除更新

必须在定义的删除数据方法中调用完成相应的 res 回调函数后再次使用获取数据的方法
eg

```
deAdvertiseById(id) {
      deAdvertiseById(postData({ id })).then((res) => {
        this.$message.success(res.data);
        this.getAdvertise();
      });
    },
```

#### 子组件实时获取父组件的数据

使用 watch 方法监听数据即可

# 总结

1：在实际调用的过程中遇到数据更新的问题时，就在调用完对应的 api 成功的回到函数中再一次调用数据请求方法就可以解决

2：事件总线一般在 mounted 中接收

```
  mounted() {
    this.$bus.$on("impassion", () => {
      this.getImpassion();
    });
    this.$bus.$on("advertise", () => {
      this.getAdvertise();
    });
  },
```

3: 使用轮播图动态渲染图片时，必须先让所有图片对应的数据加载完成后才能加载这个 Dom
对封装的组件进行 v-if=“getImglength”判断进行是否显示 dom 即可
