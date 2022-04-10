<template>
  <div class="infotable" v-if="pagiData !== null">
    <el-table class="eltab" :data="pagiData" border style="width: 100%" stripe>
      <el-table-column prop="id" label="id" width="120" align="center">
      </el-table-column>
      <el-table-column prop="text" label="具体信息"> </el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button @click="dele(scope.row)" type="text" size="small"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      class="pager"
      layout="prev, pager, next, jumper"
      :total="getLength"
      :current-page.sync="pageNum"
      @current-change="changePage"
      :page-size="3"
    />
  </div>
</template>

<script>
export default {
  name: "infotable",
  data() {
    return {
      pagiData: null,
      pageNum: 1,
    };
  },
  components: {},
  props: {
    allData: {
      type: Array,
      default() {
        return [];
      },
    },
    tableType: {
      type: String,
      default() {
        return "";
      },
    },
  },
  computed: {
    getLength() {
      if (this.allData) {
        return this.allData.length;
      }
      return 0;
    },
  },
  watch: {
    allData() {
      this.changePage();
    },
  },
  methods: {
    dele(row) {
      //删除数据需要进行数据更新，向父组件发送时间让其再次请求数据
      let id = row.id;
      let name = this.tableType;
      this.$emit("dele", { name, id });
    }, //分页改变
    changePage() {
      this.$nextTick(() => {
        //这里绑定在分页上的pageNum会自动改变获得第n页，无需手动改变
        let start = (this.pageNum - 1) * 3;
        let length = this.getLength;
        let end = this.pageNum * 3;
        if (end > length) {
          end = length + 1;
        }
        this.pagiData = this.allData.slice(start, end);
      });
    },
  },
};
</script>
<style scoped>
.infotable {
  width: 100%;
  height: 100%;
}
.eltab {
  margin: 0.0521rem auto;
}
.pager {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
</style>
