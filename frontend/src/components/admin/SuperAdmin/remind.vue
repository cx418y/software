<template>
  <el-main>
    <el-table
      ref="multipleTable"
      :data="remindData "
      @selection-change="handleSelectionChange"
      style="border-radius: 10px;color: #122d40;height: 500px;background-color:whitesmoke;padding: 20px;overflow: auto;width:80%;margin-left: 10%">
      <el-table-column
        type="selection"
        width="45">
      </el-table-column>
      <el-table-column style="font-family: Ink Free" prop="userName" label="用户名" width="150">
      </el-table-column>
      <el-table-column style="font-family: Ink Free" prop="reserveOvertime" label="预约超期" width="200" show-overflow-tooltip>
      </el-table-column>
      <el-table-column style="font-family: Ink Free" prop="borrowOvertime" label="借阅超期" width="200" show-overflow-tooltip>
      </el-table-column>
      <el-table-column style="font-family: Ink Free" prop="unpaid" label="未缴纳罚款" show-overflow-tooltip>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px">
      <el-button @click="handleClick()" style="background-color: #f8f8a3;color: black;border-radius: 10px;font-family: 华光细圆_CNKI">批量提醒</el-button>
      <el-button @click="toggleSelection()" style="background-color: #f8f8a3;color: black;border-radius: 10px;font-family: 华光细圆_CNKI">取消选择</el-button>
    </div>
  </el-main>
</template>

<script>
export default {
  name: 'remind',
  data () {
    return {
      remindData: [],
      multipleSelection: []
    }
  },
  mounted () {
    this.getRemind()
  },

  methods: {
    getRemind () {
      this.$axios.get('/remind').then(resp => {
        if (resp.status === 200) {
          this.remindData = resp.data
        }
      })
    },
    toggleSelection () {
      this.$refs.multipleTable.clearSelection()
    },
    handleSelectionChange (val) {
      var arr = []
      val.forEach((item) => {
        arr.push(item.userName)
      })
      this.multipleSelection = arr
    },
    handleClick () {
      this.$axios.post('/sendRemind', {
        'userNames': this.multipleSelection
      }).then(resp => {
        if (resp.status === 200) {
          this.$message.success('成功发送提醒')
        } else {
          this.$message.warning('错误情况')
        }
      })
        .catch(error => {
          this.$message.warning(error)
        })
    }
  }
}
</script>

<style scoped>

</style>
