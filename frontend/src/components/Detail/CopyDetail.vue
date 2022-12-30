<template>
  <el-tabs type="border-card" class="wrap">
    <el-tab-pane label="图书详情">
      <detail-show ref="detailShow"></detail-show>
    </el-tab-pane>
    <el-tab-pane label="副本情况">
      <copy-table ref="copyTable" @getCopies="loadCopies"></copy-table>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import DetailShow from './DetailShow'
import CopyTable from './CopyTable'
export default {
  inject: ['reload'],
  name: 'CopyDetail',
  components: {CopyTable, DetailShow},
  mounted () {
    this.getDetails()
  },
  methods: {
    getDetails () {
      var id = this.$route.query.id
      this.$axios.get('/getDetails/' + id).then(resp => {
        if (resp) {
          this.$refs.detailShow.detail = resp.data
          this.$refs.detailShow.getCommentList(resp.data.isbn)
        }
      })
    },
    loadCopies () {
      var isbn = this.$route.query.isbn
      this.$axios.get('/getCopies/' + isbn).then(resp => {
        if (resp) {
          this.$refs.copyTable.copies = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>
.wrap {
  display: block;
  background-color: white;
  width: 60%;
  margin-left: 20%;
  margin-top: 40px;
  padding-bottom: 50px;
}
</style>
