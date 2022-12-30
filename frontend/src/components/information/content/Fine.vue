<template>
  <el-container>
    <el-header style="height: auto;background-color: transparent ;border-radius:10px;text-align: right; font-size: 12px;margin-bottom: 10px;width: auto">
      <h1 style="float:left;color: beige;font-size: 30px;font-family: 华光细圆_CNKI;margin-top: 5px">罚款记录</h1>
    </el-header>

    <el-main style="padding: 0px">
      <div>
        <el-table
          :data="fineData"
          ref="multipleTable"
          @selection-change="handleSelectionChange"
          style="border-radius: 10px;color: #122d40;height: 500px;background-color:whitesmoke;padding: 20px;overflow: auto;">
          <el-table-column type="selection" width="50" :selectable="selectDisableRoom"></el-table-column>
          <el-table-column style="font-family: Ink Free" prop="time" label="时间" width="180">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="isbn" label="副本标识" width="160">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="price" label="罚款金额" width="90">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="adminName" label="管理员" width="90">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="area.name" label="所在分馆" width="90">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="reason" label="原因" width="160">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="paid" label="是否缴纳">
          </el-table-column>
        </el-table>
        <div style="margin-top: 20px">
          <el-button @click="handleClick()" style="background-color: #f8f8a3;color: black;border-radius: 10px;font-family: 华光细圆_CNKI">缴纳罚款</el-button>
          <el-button @click="toggleSelection()" style="background-color: #f8f8a3;color: black;border-radius: 10px;font-family: 华光细圆_CNKI">取消选择</el-button>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import store from '../../../store'
export default {
  name: 'Fine',
  inject: ['reload'],
  data () {
    return {
      multipleSelection1: [],
      multipleSelection2: [],
      fineData: [],
      amount: 0 // 准备缴纳的罚款
    }
  },
  mounted () {
    this.getFineRecords()
  },
  methods: {
    getFineRecords () {
      this.$axios.get('/userFineRecord/' + store.state.username).then(resp => {
        if (resp.status === 200) {
          this.fineData = resp.data
        }
      })
    },
    toggleSelection () {
      this.$refs.multipleTable.clearSelection()
    },
    handleSelectionChange (val) {
      var arr = []
      var arr2 = []
      val.forEach((item, index) => {
        this.amount = this.amount + item.price
        arr.push(item.time)
        arr2.push(item.isbn)
      })
      this.multipleSelection1 = arr
      this.multipleSelection2 = arr2
    },
    selectDisableRoom (row) {
      if (row.paid === 'NO') {
        return true
      }
    },
    handleClick () {
      this.$axios
        .post('/payFine', {
          'time': this.multipleSelection1,
          'isbn': this.multipleSelection2,
          'userName': this.$route.query.id
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.$message.success('支付成功！')
            this.reload()
          }
        }).catch(error => {
          this.$message.warning(error)
        })
    }
  }
}
</script>

<style scoped>

</style>
