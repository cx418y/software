<template>
  <div>
    <el-table
      ref="multipleTable"
      :data="results"
      tooltip-effect="dark"
      height="500"
      style="width: 80%;margin-left: 10%"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="45">
      </el-table-column>
      <el-table-column
        prop="book.title"
        label="书名"
        width="80"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="book.author"
        label="作者"
        width="80"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="book.isbn"
        label="ISBN"
        width="160">
      </el-table-column>
      <el-table-column
        prop="book.area.name"
        label="馆藏地址"
        width="100"
      >
      </el-table-column>
      <el-table-column
        prop="startTime"
        label="预约时间"
        width="100">
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px">
      <el-button @click="handleClick()" style="background-color: #409EFF;color: white">取书</el-button>
      <el-button @click="toggleSelection()">取消选择</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserSearchResult',
  data () {
    return {
      results: [],
      multipleSelection: []
    }
  },
  methods: {
    toggleSelection () {
      this.$refs.multipleTable.clearSelection()
    },
    handleClick () {
      this.$emit('reserveClick')
    },
    handleSelectionChange (val) {
      var arr = []
      val.forEach((item) => {
        arr.push(item.book.isbn)
      })
      this.multipleSelection = arr
    }
  }
}
</script>

<style scoped>
</style>
