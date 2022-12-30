<template>
  <el-table
    :data="copies"
    height="450"
    style="width: 80%;margin-left: 10%;">
    <el-table-column
      prop="isbn"
      label="编号"
      width="150">
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
      width="100"
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
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button size="medium" :disabled="!(scope.row.bookState.name === 'available')" @click="handleReserve(scope.row.isbn)">预约</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import store from '../../store'
export default {
  name: 'CopyTable',
  inject: ['reload'],
  data () {
    return {
      copies: []
    }
  },
  mounted () {
    this.getCopies()
  },
  methods: {
    getCopies () {
      this.$emit('getCopies')
    },
    handleReserve (isbn) {
      if (store.state.credit < 50) {
        this.$message.warning('信用分低于50，无法预约！')
      } else {
        this.$axios
          .post('/reserveBook', {
            'isbn': isbn,
            'username': store.state.username
          }).then(resp => {
            if (resp) {
              this.$message.success('预约成功')
              this.reload()
            }
          })
          .catch(error => {
            this.$message.warning(error.message)
          })
      }
    }
  }
}
</script>

<style>
.el-button {
  background-color: #409EFF;
  color: white;
}
.el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
  border-color: #C0C4CC;
}
</style>
