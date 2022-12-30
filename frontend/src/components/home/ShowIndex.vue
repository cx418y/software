<template>
    <el-container class="body_wrap">
      <el-aside style="width: 200px;padding-top: 40px;text-align: left;">
        <side-menu @indexSelect="listByCategory" ref="sideMenu"></side-menu>
      </el-aside>
      <el-main>
        <library class="library" ref="booksArea"></library>
      </el-main>
    </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import Library from './Library'
export default {
  name: 'ShowIndex',
  components: {Library, SideMenu},
  methods: {
    listByCategory () {
      var _this = this
      var cid = this.$refs.sideMenu.cid
      var url = 'categories/' + cid + '/books'
      this.$axios.get(url).then(resp => {
        if (resp) {
          _this.$refs.booksArea.books = resp.data
          _this.$refs.booksArea.currentPage = 1
        }
      })
    }
  }
}
</script>

<style>
  .body_wrap{
    width: 80%;
    margin-left: auto;
    margin-right: auto;
  }
.el-main{
  padding-top: 40px;
}
  .library{
    width: 900px;
    height: auto;
    margin-left: auto;
    margin-right: auto;
  }
</style>
