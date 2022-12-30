<template>
  <div class="userRecord-wrap">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 15px;line-height: 80px;width: 70%;float:left">
      <el-breadcrumb-item :to="{ path: '/admin/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/admin/record'}">操作记录</el-breadcrumb-item>
      <el-breadcrumb-item>用户记录</el-breadcrumb-item>
      <el-breadcrumb-item>{{this.$route.query.username}}</el-breadcrumb-item>
    </el-breadcrumb>
    <div  style="display: flex;justify-content: center;align-items: center;width:10%;float: right;line-height: 80px">
      <el-button type="text" @click="handleFine" style="margin-left: 15px;color: beige;background-color: transparent;height: 80px">罚款记录</el-button>
    </div>
    <el-table
      :data="results.slice((currentPage-1)*pagesize, currentPage*pagesize)"
      class="userRecord"
    >
      <el-table-column
        prop="time"
        label="时间"
        width="180"
      >
      </el-table-column>
      <el-table-column
        prop="isbn"
        label="副本标识"
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
  name: 'UserRecord',
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
      this.$axios.get('/userRecord/' + this.$route.query.username).then(resp => {
        if (resp && resp.status === 200) {
          this.results = resp.data
        }
      }).catch(error => {
        this.$message.warning(error.message)
      })
    },
    handleFine () {
      this.$router.push({
        path: '/admin/record/user-record/fine',
        query: {
          username: this.$route.query.username
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
  .userRecord-wrap {
    width: 80%;
    margin-left: 10%;
  }
  .userRecord {
    min-height: 500px;
    padding: 20px 50px 50px;
  }
  .page{
    padding: 10px;
    background-color: white;
  }
</style>
