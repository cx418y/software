<template>
  <div class="adminRefresh-wrap">
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 15px;line-height: 80px;width: 40%;float:left">
      <el-breadcrumb-item :to="{ path: '/admin/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/admin/Adminrefresh'}">重置信用</el-breadcrumb-item>
    </el-breadcrumb>
    <div  style="display: flex;justify-content: center;align-items: center;width:50%;float: right;line-height: 80px">
      <el-input placeholder="查询用户" @keyup.enter.native="handleSearch" prefix-icon="el-icon-search" v-model="input">
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="handleSearch" style="margin-left: 15px;">搜索</el-button>
    </div>
    <el-table class="adminRefresh" >
      <el-table-column prop="name" label="用户名" width="160">
      </el-table-column>
      <el-table-column prop="credit" label="信用分" width="160"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="refresh">重置信用分</el-button>
          <el-dialog title="重置信用分" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
            <el-input-number v-model="num" :min="0" :max="100" label="分数" placeholder="0" style="margin-left: 25%;width: 50%"></el-input-number>
            <span slot="footer" class="dialog-footer">
              <el-button @click="reset">重置</el-button>
              <el-button type="primary" @click="change">确 定</el-button>
              </span>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'Adminrefresh',
  data () {
    return {
      num: 0,
      name: '',
      credit: '',
      input: '',
      dialogVisible: false

    }
  },
  mounted () {
    this.getCreditRecords()
  },
  methods: {
    getCreditRecords () {
      this.$axios.get('/Adminrefresh').then(resp => {
        if (resp && resp.status === 200) {
          this.credit = resp.data
        }
      })
    },
    handleSearch () {
      if (this.input.length !== 5 && this.input.length !== 11) {
        this.$message.warning('请输入学号/工号')
      } else {
        this.$axios.get('/Userrefresh/' + this.input).then(resp => {
          if (resp && resp.status === 200) {
            this.$router.push({
              path: '/Userrefresh',
              query: {
                username: this.input
              }
            })
          }
        }).catch(error => {
          this.$message.warning(error.message)
        })
      }
    }
  }
}
</script>

<style scoped>
  .adminRefresh-wrap {
    width: 80%;
    margin-left: 10%;
  }
  .adminRefresh{
    min-height: 500px;
    padding: 20px 50px 50px;
  }
</style>
