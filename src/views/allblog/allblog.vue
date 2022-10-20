<template>
  <div class="allBlog">
    <div class="utab">
      <el-table :data="pagiData" border style="width: 100%" stripe>
        <el-table-column prop="bid" label="博客id" width="100" align="center">
        </el-table-column>
        <el-table-column prop="author" label="作者" width="100">
        </el-table-column>
        <el-table-column prop="label" label="标签" width="120">
        </el-table-column>
        <el-table-column prop="type" label="所属类别" width="100">
        </el-table-column>
        <el-table-column prop="title" label="标题"> </el-table-column>
        <el-table-column prop="time" label="发布时间" width="180">
          <template slot-scope="scope">{{
            scope.row.time | formatTime
          }}</template>
        </el-table-column>
        <el-table-column prop="lauds" label="获得赞数" width="80">
        </el-table-column>
        <el-table-column prop="views" label="浏览次数" width="80">
        </el-table-column>
        <el-table-column prop="commentsNum" label="评论数量" width="80">
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template
            slot="header"
            slot-scope="/* eslint-disable vue/no-unused-vars */ scope"
          >
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"
              prefix-icon="el-icon-search"
              @keyup.enter.native="searchInfo"
            />
          </template>
          <template slot-scope="scope">
            <el-button @click="show(scope.row)" type="text" size="small"
              >查看</el-button
            >
            <el-button @click="dele(scope.row)" type="text" size="small"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-pagination
      background
      class="pager"
      layout="prev, pager, next, jumper"
      :total="getLength"
      :current-page.sync="pageNum"
      @current-change="changePage"
      :page-size="10"
    />
  </div>
</template>

<script>
import { getBlogsSequence, likeFind, deleteById } from "@/network/data.js";
import { formatData } from "@/utils/utils.js";
import postData from "@/utils/qs.js";
export default {
  name: "allblog",
  data() {
    return {
      allBlogs: null,
      pagiData: null,
      pageNum: null,
      search: "",
    };
  },
  filters: {
    formatTime(time) {
      let date = new Date(time * 1000);
      return formatData(date, "yyyy-MM-dd hh:mm:ss");
    },
  },
  components: {},
  computed: {
    getLength() {
      if (this.allBlogs) {
        return this.allBlogs.length;
      }
      return 0;
    },
  },
  methods: {
    //顺序拿到博客信息
    getBlogsSequence() {
      getBlogsSequence().then((res) => {
        this.allBlogs = res.data;
        this.changePage();
      });
    },
    //查看
    show(row) {
      let bid = row.bid;
      this.$router.push("/concrete/" + bid);
    },
    //删除
    dele(row) {
      let bid = row.bid;
      if (bid === 1) {
        this.$message.warning("留言板信息不允许删除！");
      } else {
        deleteById(postData({ bid })).then((res) => {
          this.$message.success(res.data);
          this.getBlogsSequence();
        });
      }
    },
    //分页改变
    changePage() {
      this.$nextTick(() => {
        //这里绑定在分页上的pageNum会自动改变获得第n页，无需手动改变
        let start = (this.pageNum - 1) * 10;
        let length = this.getLength;
        let end = this.pageNum * 10;
        if (end > length) {
          end = length + 1;
        }
        this.pagiData = this.allBlogs.slice(start, end);
      });
    },

    //搜索
    searchInfo() {
      let str = this.search;
      if (str === "") {
        this.$message.error("请输入信息");
      } else {
        likeFind(str).then((res) => {
          if (res.data.length === 0) {
            this.$message.warning("暂无结果");
          } else {
            this.$nextTick(() => {
              this.pagiData = res.data;
              this.search = "";
            });
          }
        });
      }
    },
  },
  created() {
    this.getBlogsSequence();
  },
};
</script>
<style scoped>
.allBlog {
  width: 100%;
  height: 92%;
  position: relative;
}

.utab {
  width: 95%;
  margin: 0.0521rem auto;
}

.pager {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
</style>
