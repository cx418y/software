<template>
  <div>
    <div class="block">
    <el-popover
      placement="bottom"
      width="100"
      v-model="visible"
      trigger="click"
    >
      <el-button size="mini" type="text" @click="modify" style="font-size: 15px;background-color: white;color: black;margin-bottom: 10px">修改密码</el-button>
      <el-button size="mini" type="text" @click="logOut" style="font-size: 15px;background-color: white;color: red;margin-left: 0">退出登录</el-button>
  <el-avatar :size="40" :src="circleUrl" style="margin-top: 10px" slot="reference"></el-avatar>
    </el-popover>
    </div>
  </div>
</template>

<script>
import store from '../../../store'
export default {
  name: 'Header',
  data () {
    return {
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      visible: false,
      adminName: store.state.username
    }
  },
  methods: {
    logOut () {
      this.$store.commit('logout')
      this.$message.success('退出登录成功')
      this.$router.push({path: '/index'})
    },
    modify () {
      if (this.adminName === 'admin') {
        this.$message.warning('超级管理员无法修改密码！')
      } else {
        this.$router.push({path: '/modifypwd'})
      }
    }
  }
}
</script>

<style scoped>
.block {
  width: 50px;
  height: 50px;
  margin-left: 92%;
}
</style>
