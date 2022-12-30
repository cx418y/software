<template>
  <el-table
    :data="copies"
    border
    height="300"
    :cell-style="cellStyle"
    style="width: 80%;margin-left: 10%;">
    <el-table-column
      label="编号"
      width="180"
    >
      <template slot-scope="scope">
        <div @click="handleCopy(scope.row.isbn)">{{scope.row.isbn}}</div>
      </template>
    </el-table-column>
    <el-table-column
      label="状态"
      width="120">
      <template slot-scope="scope">
        {{scope.row.bookState.name}}
      </template>
    </el-table-column>
    <el-table-column
      label="馆藏地址"
      width="120"
    >
      <template slot-scope="scope">
        {{scope.row.areaName}}
      </template>
    </el-table-column>
    <el-table-column
      prop="adminName"
      label="管理员"
      width="120">
    </el-table-column>
    <el-table-column
      prop="userName"
      label="借出人">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: 'ShowCopy',
  data () {
    return {
      copies: []
    }
  },
  methods: {
    handleCopy (isbn) {
      this.$router.push({
        path: '/admin/copy-record',
        query: {
          isbn: isbn
        }
      })
    },
    cellStyle (row, column, rowIndex, columnIndex) {
      if (row.column.label === '编号') {
        return 'color:#409EFF'
      }
    }
  }
}
</script>

<style scoped>

</style>
