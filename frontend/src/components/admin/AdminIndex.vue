<template>
  <el-container id="app-wrapper">
      <el-aside width="20%" class="adminMenu">
        <admin-menu></admin-menu>
      </el-aside>
      <el-container id="main-wrapper">
        <el-header style="width: 100%">
          <Header class="header"></Header>
        </el-header>
        <el-main>
          <router-view/>
        </el-main>
        <el-footer>
          <about></about>
        </el-footer>
      </el-container>
  </el-container>
</template>

<script>
import Header from './common/Header'
import AdminMenu from './common/AdminMenu'
import About from '../common/About'
export default {
  name: 'AdminIndex',
  components: {About, AdminMenu, Header}
}
</script>

<style>
.el-header {
  padding: 0;
}
.header {
  background-color: white;
  height: 60px;
  text-align: left;
  border-bottom: 1px solid grey;
  position: relative;
  width: 100%;
}
.adminMenu {
  width: 20%;
  background-color: white;
}
#app-wrapper {
  min-height: 100%;
}
#main-wrapper {
  min-height: 100%;
  margin-left: 20%;
  width: 80%;
}
.adminMenu {
  position: fixed;
  min-height: 800px;
}
</style>
