<template>
  <div>
    <img src="../../assets/logo.png" style="height: 60px;width: 60px;margin-left: 40px;margin-right:20px;top: -2px;position: relative;display: inline-block">
  <el-menu
    :default-active="this.$route.path"
    router
    mode="horizontal">
    <el-menu-item
      v-for="(item,i) in navList"
      :key="i"
      :index="item.name"
    class="nav-wrap">
      {{ item.navItem }}
    </el-menu-item>
  </el-menu>
    <div @click="Click" class="center" v-show="show">
      <el-button v-model="message" type="text" style="background-color: white;color: #409EFF">{{message}}</el-button>
    </div>
    <div class="block" v-show="show2">
      <el-popover
        placement="bottom"
        width="100"
        v-model="visible"
        trigger="click"
      >
        <el-button size="mini" type="text" @click="logOut" style="font-size: 14px;background-color: white;color: red;">退出登录</el-button>
        <el-avatar :size="40" :src="circleUrl" style="margin-top: 10px" slot="reference"></el-avatar>
      </el-popover>
    </div>
  </div>
</template>

<script>
import store from '../../store'
export default {
  name: 'NavMenu',
  inject: ['reload'],
  data () {
    return {
      message: '',
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/search', navItem: '搜索'},
        {name: '/information', navItem: '个人中心'}
      ],
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      visible: false,
      show: true,
      show2: false
    }
  },
  mounted: function () {
    this.load()
  },
  methods: {
    load () {
      if (store.state.username !== null) {
        this.show = false
        this.show2 = true
      } else {
        this.message = '登录'
        this.show = true
        this.show2 = false
      }
    },
    Click () {
      this.$router.push({path: '/login'})
    },
    logOut () {
      this.$router.push({path: '/index'})
      this.$store.commit('logout')
      this.$message.success('退出登录成功')
      this.$router.go(0)
    }
  }
}
</script>

<style>
  .el-menu{
    background-color: white;
    width: 300px;
    text-align: center;
    position: relative;
    display: inline-block;
    margin: 0;
    padding-left: 0;
    height:58px;
    line-height: 60px;
  }
  .nav-wrap{
    list-style-type: none;
    display: inline-block;
    height:100%;
    width: 100px;
  }
  .center{
    cursor: pointer;
    position: absolute;
    display: inline-block;
    height:60px;
    width: 100px;
    font-size: 14px;
    text-align: center;
    right: 0;
  }
  .block {
    cursor: pointer;
    position: absolute;
    display: inline-block;
    width: 50px;
    height: 50px;
    margin-right: 20px;
    right: 0;
  }
  .el-popover {
    min-width: 100px;
    text-align: center;
  }
</style>
