## 第九天

今天成功解决了动态路由问题，应该是在添加信息到会话存储中时，并不会添加`component`
导致刷新判断时，添加的是没有组件的信息路由，因而会报错，此外 ui 组件中每次点击的不同路由来获取其路径
同时也保存在会话中，刷新时跳转到该路径即可，
此外还要做一个

```
  sessionStorage.setItem("active", to.path);
```

## 数据统计

最后一个折线图也封装完毕，整个页面无非就是封装柱状图，饼状图，折线图

## 博客统计

博客记录使用表格组件即可
分页中绑定的 data 页数会根据电机自动变化，不需要手动设置

```<el-pagination
      background
      class="pager"
      layout="prev,pager,next"
      :total="getLength"
      :current-page.sync="pageNum"
      @current-change="changePage"
      :page-size="10"
    />
```

关于表格中使用输入框无法输入，,scope 需要注释即可，

```
 <template
 slot="header"
scope="/* eslint-disable vue/no-unused-vars */ scope"
>
<el-input
v-model="search"
size="mini"
placeholder="输入关键字搜索"
prefix-icon="el-icon-search"
@keyup.enter.native="searchInfo"
/>
</template>
```

## 明天先弄侧边栏操作页，基本后天能结束后台

```

```
