<template>
  <el-container>
    <el-header style="height: auto;background-color: transparent ;border-radius:10px;text-align: right; font-size: 12px;margin-bottom: 10px;width: auto">
      <h1 style="float:left;color: beige;font-size: 30px;font-family: 华光细圆_CNKI;margin-top: 5px">信用记录</h1>
    </el-header>

    <el-main style="padding: 0px">
      <div>
        <el-table
          :data="creditData"
          style="border-radius: 10px;color: #122d40;height: 500px;background-color:whitesmoke;padding: 20px;overflow: auto;">
          <el-table-column style="font-family: Ink Free" prop="time" label="时间" width="200">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="changes" label="信用变化" width="150">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="credit" label="剩余信用" width="150">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="adminName" label="管理员" width="150">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="reason" label="原因">
          </el-table-column>
        </el-table>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import store from '../../../store'
export default {
  name: 'Credit',
  inject: ['reload'],
  data () {
    return {
      creditData: [],
      dialogVisible: false,
      textarea: ''
    }
  },
  mounted () {
    this.getCreditRecords()
  },
  methods: {
    getCreditRecords () {
      this.$axios.get('/creditRecord/' + store.state.username).then(resp => {
        if (resp.status === 200) {
          this.creditData = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
