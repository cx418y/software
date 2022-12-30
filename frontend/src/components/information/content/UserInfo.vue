<template>
  <el-container>
    <el-header style="height: auto;background-color: transparent ;border-radius:10px;text-align: right; font-size: 12px;margin-bottom: 10px;width: auto">
      <h1 style="float:left;color: beige;font-size: 30px;font-family: 华光细圆_CNKI;margin-top: 5px">个人信息</h1>
    </el-header>

    <el-main style="padding: 0px">
      <div style="border-radius: 10px;color: #122d40;min-height: 500px;background-color:whitesmoke;overflow: auto;width: 90%;text-align: left;padding-left:50px;padding-top: 25px;padding-bottom: 25px">
        <p>
        <label>用户名： {{userData.username}}</label>
          <el-button @click="handleModify" style="background-color: #f8f8a3;color: black;border-radius: 10px;font-family: 华光细圆_CNKI;margin-left:20px">修改密码</el-button>
        </p><br>
        <p>
        <label>邮箱：{{userData.email}}</label>
        </p><br>
        <p>
        <label>身份：{{userData.authority}}</label>
        </p><br>
        <p>
        <label>最大借阅数量：{{userData.mostBorrow}}</label>
        </p><br>
        <p>
        <label>借阅超期时间：{{handleBorrow}}</label>
        </p><br>
        <p>
        <label>预约超期时间：{{handleReserve}}</label>
        </p><br>
        <p>
        <label>信用分：{{userData.credit}}</label>
        </p><br>
        <p>
        <label>罚款：{{userData.fine}}</label>
        </p>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import store from '../../../store'
export default {
  name: 'UserInfo',
  data () {
    return {
      userData: {
        username: store.state.username,
        email: store.state.email,
        authority: store.state.authority,
        mostBorrow: store.state.mostBorrow,
        borrowOvertime: store.state.borrowOvertime,
        reserveOvertime: store.state.reserveOvertime,
        credit: 100,
        fine: 0
      },
      dialogVisible: false,
      textarea: ''
    }
  },
  computed: {
    handleBorrow: function () {
      let day = parseInt(String(this.userData.borrowOvertime / 86400))
      let hour = parseInt(String(((this.userData.borrowOvertime % 86400) / 3600)))
      let minute = parseInt(String((((this.userData.borrowOvertime % 86400) % 3600) / 60)))
      let second = parseInt(String((((this.userData.borrowOvertime % 86400) % 3600) % 60)))
      return day + '天' + hour + '小时' + minute + '分钟' + second + '秒'
    },
    handleReserve: function () {
      let day = parseInt(String(this.userData.reserveOvertime / 86400))
      let hour = parseInt(String(((this.userData.reserveOvertime % 86400) / 3600)))
      let minute = parseInt(String((((this.userData.reserveOvertime % 86400) % 3600) / 60)))
      let second = parseInt(String((((this.userData.reserveOvertime % 86400) % 3600) % 60)))
      return day + '天' + hour + '小时' + minute + '分钟' + second + '秒'
    }
  },
  mounted () {
    this.getUser()
  },
  methods: {
    getUser () {
      this.$axios.get('/userInfo/' + store.state.username).then(resp => {
        if (resp) {
          this.userData = resp.data
        }
      })
    },
    handleModify () {
      this.$router.replace('/modifypwd')
    }
  }
}
</script>

<style scoped>

</style>
