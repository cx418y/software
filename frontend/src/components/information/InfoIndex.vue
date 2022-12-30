<template>
  <el-container class="body_wrap">
  <el-aside style="width: 200px;padding-top: 40px;text-align: left;">
    <user-menu></user-menu>
  </el-aside>
    <el-main id="info-main">
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
import UserMenu from './UserMenu'
export default {
  name: 'InfoIndex',
  components: {UserMenu}
}
</script>

<style scoped>
#info-main {
  min-height: 100%;
  margin-left: 5%;
  width: 90%;
}
</style>
