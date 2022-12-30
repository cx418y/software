<template>
  <div class="adminRecord-wrap">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 15px;line-height: 80px;width: 40%;float:left">
      <el-breadcrumb-item :to="{ path: '/admin/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/admin/record'}">操作记录</el-breadcrumb-item>
    </el-breadcrumb>
    <div  style="display: flex;justify-content: center;align-items: center;width:50%;float: right;line-height: 80px">
    <el-input
      placeholder="查询用户系统记录"
      @keyup.enter.native="handleSearch"
      prefix-icon="el-icon-search"
      v-model="input">
    </el-input>
      <el-button type="primary" icon="el-icon-search" @click="handleSearch" style="margin-left: 15px;">搜索</el-button>
    </div>
    <el-table
      :data="results.slice((currentPage-1)*pagesize, currentPage*pagesize)"
      class="adminRecord"
    >
      <el-table-column
        prop="time"
        label="时间"
        width="160"
      >
      </el-table-column>
      <el-table-column
        prop="isbn"
        label="副本标识"
        width="160"
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
        width="100">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="用户"
        width="150">
      </el-table-column>
      <el-table-column
        prop="area.name"
        label="所在分馆"
        width="150">
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
  name: 'AdminRecord',
  data () {
    return {
      results: [],
      input: '',
      currentPage: 1,
      pagesize: 8
    }
  },
  mounted () {
    this.getRecords()
  },
  methods: {
    getRecords () {
      this.$axios.get('/adminRecord').then(resp => {
        if (resp && resp.status === 200) {
          this.results = resp.data
        }
      })
    },
    handleSearch () {
      if (this.input.length !== 5 && this.input.length !== 11) {
        this.$message.warning('请输入学号/工号')
      } else {
        this.$axios.get('/userRecord/' + this.input).then(resp => {
          if (resp && resp.status === 200) {
            this.$router.push({
              path: '/admin/record/user-record',
              query: {
                username: this.input
              }
            })
          }
        }).catch(error => {
          this.$message.warning(error.message)
        })
      }
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    }
  }
}
</script>

<style scoped>
  .adminRecord-wrap {
    width: 80%;
    margin-left: 10%;
  }
  .adminRecord {
    min-height: 500px;
    padding: 20px 50px 50px;
  }
  .page{
    padding: 10px;
    background-color: white;
  }
</style>
