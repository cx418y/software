<template>
  <div class="userRefresh-wrap">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 15px;line-height: 80px;width: 70%;float:left">
      <el-breadcrumb-item :to="{ path: '/admin/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/admin/Adminrefresh'}">重置信用</el-breadcrumb-item>
      <el-breadcrumb-item>用户信用分</el-breadcrumb-item>
      <el-breadcrumb-item>{{this.$route.query.username}}</el-breadcrumb-item>
    </el-breadcrumb>
    <div  style="display: flex;justify-content: center;align-items: center;width:10%;float: right;line-height: 80px">
      <el-button type="text" style="margin-left: 15px;color: beige;background-color: transparent;height: 80px">信用分</el-button>
    </div>
    <el-table class="userRefresh">
      <el-table-column prop="name" label="用户名" width="350">
      </el-table-column>
      <el-table-column prop="credit" label="信用分" width="350"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="change">重置信用分</el-button>

        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import store from '../../../../store'
export default {
  name: 'userrefresh',
  data () {
    return {
      name: '',
      credit: '',
      dialogVisible: false

    }
  },
  mounted () {
    this.getCreditRecords()
  },
  methods: {
    getCreditRecords () {
      this.$axios.get('/Userefresh' + this.$route.query.username).then(resp => {
        if (resp && resp.status === 200) {
          this.credit = resp.data
        }
      })
    },
    change () {
      this.$axios.post('/Refreshcredit', {
        'adminname': store.state.username,
        'name': this.name
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$message.success('重置成功')
          this.dialogVisible = false
          history.go(0)
        }
      })
    },
    reset () {
      this.num = 0
      this.dialogVisible = false
      this.refresh()
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    }
  }
}
</script>

<style scoped>
  .userRefresh-wrap {
    width: 80%;
    margin-left: 10%;
  }
  .userRefresh{
    min-height: 500px;
    padding: 20px 50px 50px;
  }
</style>
