<template>
  <div class="userFineRecord-wrap">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 15px;line-height: 80px">
      <el-breadcrumb-item :to="{ path: '/admin/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/admin/record'}">操作记录</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/admin/record/user-record',query: {username: this.$route.query.username}}">用户记录</el-breadcrumb-item>
      <el-breadcrumb-item>{{this.$route.query.username}}</el-breadcrumb-item>
      <el-breadcrumb-item>罚款记录</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table
      :data="results.slice((currentPage-1)*pagesize, currentPage*pagesize)"
      class="userFineRecord"
    >
      <el-table-column
        prop="time"
        label="时间"
        width="170"
      >
      </el-table-column>
      <el-table-column
        prop="isbn"
        label="副本标识"
        width="170"
      >
      </el-table-column>
      <el-table-column
        prop="price"
        label="罚款金额"
        width="100"
      >
      </el-table-column>
      <el-table-column
        prop="adminName"
        label="管理员"
        width="100"
      >
      </el-table-column>
      <el-table-column
        prop="area.name"
        label="所在分馆"
        width="100"
      >
      </el-table-column>
      <el-table-column
        prop="reason"
        label="原因"
        width="150">
      </el-table-column>
      <el-table-column
        prop="paid"
        label="是否缴纳"
        width="80">
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
  name: 'UserFineRecord',
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
      this.$axios.get('/userFineRecord/' + this.$route.query.username).then(resp => {
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
  .userFineRecord-wrap {
    width: 80%;
    margin-left: 10%;
  }
  .userFineRecord {
    min-height: 500px;
    padding: 20px 50px 50px;
  }
  .page{
    padding: 10px;
    background-color: white;
  }
</style>
