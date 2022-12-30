<template>
  <el-container>
    <el-header style="height: auto;background-color: transparent ;border-radius:10px;text-align: right; font-size: 12px;margin-bottom: 10px;width: auto">
      <h1 style="float:left;color: beige;font-size: 30px;font-family: 华光细圆_CNKI;margin-top: 5px">预约待取</h1>
    </el-header>

    <el-main style="padding: 0px">
      <div>
        <el-table
          :data="reserveData"
          style="border-radius: 10px;color: #122d40;height: 500px;background-color:whitesmoke;padding: 20px;overflow: auto;">
          <el-table-column style="font-family: Ink Free" prop="isbn" label="ISBN" width="160">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="title" label="书本名称" width="100">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="area.name" label="所在分馆" width="100">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="time" label="预约时间" width="200">
          </el-table-column>
          <el-table-column style="font-family: Ink Free" prop="expireTime" label="到期时间">
          </el-table-column>
        </el-table>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import store from '../../../store'
export default {
  name: 'UserReserve',
  data () {
    return {
      reserveData: []
    }
  },
  mounted () {
    this.getRecords()
  },
  methods: {
    getRecords () {
      this.$axios.get('/information/getReserve/' + store.state.username).then(resp => {
        if (resp.status === 200) {
          this.reserveData = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
