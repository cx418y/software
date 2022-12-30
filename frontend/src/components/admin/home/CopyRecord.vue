<template>
<div class="copyRecord-wrap">
  <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 15px;line-height: 80px;">
    <el-breadcrumb-item :to="{ path: '/admin/index' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>副本记录</el-breadcrumb-item>
    <el-breadcrumb-item>{{this.$route.query.isbn}}</el-breadcrumb-item>
  </el-breadcrumb>
  <el-table
    :data="results.slice((currentPage-1)*pagesize, currentPage*pagesize)"
    class="copyRecord"
  >
    <el-table-column
      prop="time"
      label="时间"
      width="180"
    >
    </el-table-column>
    <el-table-column
      prop="operation"
      label="操作"
      width="150"
    >
    </el-table-column>
    <el-table-column
      prop="adminName"
      label="管理员"
      width="180">
    </el-table-column>
    <el-table-column
      prop="userName"
      label="用户"
      width="180">
    </el-table-column>
    <el-table-column
      prop="area.name"
      label="所在分馆"
      width="180">
    </el-table-column>
  </el-table>
  <el-row class="page">
    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="pagesize"
      :total="results.length">
    </el-pagination>
  </el-row>
</div>
</template>

<script>
export default {
  name: 'CopyRecord',
  data () {
    return {
      results: [],
      currentPage: 1,
      pagesize: 8
    }
  },
  mounted () {
    this.getRecords()
  },
  methods: {
    getRecords () {
      this.$axios.get('/copyRecord/' + this.$route.query.isbn).then(resp => {
        if (resp && resp.status === 200) {
          this.results = resp.data
        }
      })
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    }
  }
}
</script>

<style scoped>
  .copyRecord-wrap {
    width: 80%;
    margin-left: 10%;
  }
  .copyRecord {
    min-height: 500px;
    padding: 20px 50px 50px;
  }
  .page{
    padding: 10px;
    background-color: white;
  }
</style>
<style>
  .el-breadcrumb__inner.is-link, .el-breadcrumb__inner a {
    color: beige;
  }
  .el-breadcrumb__inner {
    color: beige;
  }
  .el-breadcrumb__item:last-child .el-breadcrumb__inner, .el-breadcrumb__item:last-child .el-breadcrumb__inner:hover, .el-breadcrumb__item:last-child .el-breadcrumb__inner a, .el-breadcrumb__item:last-child .el-breadcrumb__inner a:hover{
    color: beige;
  }
</style>
