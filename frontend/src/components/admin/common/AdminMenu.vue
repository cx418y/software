<template>
  <div>
    <div class="title-wrap">
    <img src="../../../assets/logo.png" style="height: 60px;width: 60px;margin-left: 40px;margin-right:20px;top: -2px;position: relative;display: inline-block">
    <h1 class="title">图书馆管理系统</h1>
    </div>
    <el-menu
      :default-active="this.$route.path"
      router
    >
      <el-menu-item index="/admin/index"><i class="el-icon-s-home"></i>首页</el-menu-item>
      <el-submenu index="2">
        <template slot="title" >
          <i class="el-icon-s-tools"></i>
          管理中心
        </template>
        <el-menu-item index="/admin/upload"><i class="el-icon-upload"></i>上传图书</el-menu-item>
        <el-menu-item index="/admin/record"><i class="el-icon-s-operation"></i>系统记录</el-menu-item>
        <el-menu-item index="/Adminrefresh"><i class="el-icon-refresh"></i>重置信用</el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title" >
          <i class="el-icon-s-management"></i>图书借阅
        </template>
        <el-menu-item index="/admin/borrow-and-return"><i class="el-icon-collection"></i>借还图书</el-menu-item>
        <el-menu-item index="/admin/reserve"><i class="el-icon-reading"></i>现场取书</el-menu-item>
      </el-submenu>
      <el-submenu index="4" :disabled="!(this.adminName === 'admin')">
        <template slot="title" >
          <i class="el-icon-user-solid"></i>超管操作
        </template>
        <!--<el-menu-item index="/adminList"><i class="el-icon-s-custom"></i>管理员列表</el-menu-item>-->
        <el-menu-item index="/addLibrarian"><i class="el-icon-plus"></i>添加管理员</el-menu-item>
        <el-menu-item index="/admin/setTime"><i class="el-icon-setting"></i>设置</el-menu-item>
        <el-menu-item index="/remind"><i class="el-icon-bell"></i>批量提醒</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import store from '../../../store'
export default {
  name: 'AdminMenu',
  data () {
    return {
      message: '',
      adminName: store.state.username
    }
  }
}
</script>

<style scoped>
.title-wrap {
  display: flex;

}
</style>
