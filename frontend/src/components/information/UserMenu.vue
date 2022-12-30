<template>
  <el-menu
    :default-active="this.$route.path"
    id="userMenu"
    background-color="transparent"
    text-color="white"
    active-text-color="beige"
    style="border-right: 0"
    router>
    <el-menu-item index="/information">
      <i class="el-icon-menu"></i>
      <span slot="title">个人信息</span>
    </el-menu-item>
    <el-menu-item index="/information/record">
      <i class="el-icon-menu"></i>
      <span slot="title">借阅情况</span>
    </el-menu-item>
    <el-menu-item index="/information/reserve">
      <i class="el-icon-menu"></i>
      <span slot="title">预约待取</span>
    </el-menu-item>
    <el-menu-item index="/information/borrow">
      <i class="el-icon-menu"></i>
      <span slot="title">借阅待还</span>
    </el-menu-item>
    <el-menu-item index="/information/read">
      <i class="el-icon-menu"></i>
      <span slot="title">已读书籍</span>
    </el-menu-item>
    <el-menu-item index="/information/fine">
      <i class="el-icon-menu"></i>
      <span slot="title">罚款记录</span>
    </el-menu-item>
    <el-menu-item index="/information/credit">
      <i class="el-icon-menu"></i>
      <span slot="title">信用记录</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'UserMenu'
}
</script>

<style scoped>
#userMenu {
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .05);
  position: fixed;
  width: 150px;
  height: auto;
  top: 100px;
  padding-top: 20px;
  padding-bottom: 20px;
}
.el-menu-item {
  font-size: 16px;
}
</style>
