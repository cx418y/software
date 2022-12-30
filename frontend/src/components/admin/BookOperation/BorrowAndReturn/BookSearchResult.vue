<template>
  <div class="article">
    <div style="margin-bottom: 20px">
      <el-radio-group v-model="radio" @change="toggleSelection()">
        <el-radio-button label="借书"></el-radio-button>
        <el-radio-button label="还书"></el-radio-button>
      </el-radio-group>
    </div>
    <div>
      <el-table
        ref="multipleTable"
        :data="result"
        tooltip-effect="dark"
        height="500"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="45"
          :selectable="selectDisableRoom">
        </el-table-column>
        <el-table-column
          prop="title"
          label="书名"
          width="80"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="isbn"
          label="ISBN"
          width="160">
        </el-table-column>
        <el-table-column
          prop="bookState.name"
          label="状态"
          width="100"
        >
        </el-table-column>
        <el-table-column
          prop="area.name"
          label="馆藏地址"
          width="100"
        >
        </el-table-column>
        <el-table-column label="归还状态">
          <template slot-scope="scope">
            <el-radio-group v-model="returnState[scope.$index]" :disabled="!((radio === '还书') && (scope.row.bookState.name === 'borrowed'))">
              <el-radio label="good">完好</el-radio>
              <el-radio label="damaged">损坏</el-radio>
              <el-radio label="lost">遗失</el-radio>
            </el-radio-group>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 20px">
        <el-button size="medium" @click="borrowShow()" :disabled="!(radio === '借书')">借书</el-button>
        <el-button size="medium" @click="returnShow()" :disabled="!(radio === '还书')">还书</el-button>
        <el-button @click="toggleSelection()">取消选择</el-button>
      </div>
    </div>
    <el-dialog
      title="借书"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">
      <div style="width: 80%;margin-left:10%;text-align: left;margin-bottom: 10px">
        <span>学号/工号：</span>
        <el-input v-model="userName" style="margin-left: 10px;width: 80%;"></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="borrowCopies()">确 定</el-button>
              </span>
    </el-dialog>
    <el-dialog
      title="还书"
      :visible.sync="dialogVisible2"
      width="40%"
      :before-close="handleClose">
      <div style="width: 80%;margin-left:10%;text-align: left;margin-bottom: 10px">
        学号/工号：
        <el-input v-model="userName" style="margin-left: 10px;width: 80%;"></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="returnCopies()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'BookSearchResult',
  data () {
    return {
      radio: '借书',
      result: [],
      multipleSelection: [],
      returnState: [],
      returnSelection: [],
      userName: '',
      dialogVisible: false,
      dialogVisible2: false
    }
  },
  methods: {
    borrowShow () {
      this.dialogVisible = true
    },
    returnShow () {
      this.dialogVisible2 = true
    },
    borrowCopies () {
      this.$emit('borrowCopies')
    },
    returnCopies () {
      var arr = []
      for (var i = 0; i < this.returnSelection.length; i++) {
        var index = this.returnSelection[i]
        arr.push(this.returnState[index])
      }
      this.returnSelection = arr
      this.$emit('returnCopies')
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    toggleSelection () {
      this.$refs.multipleTable.clearSelection()
    },
    handleSelectionChange (val) {
      var arr = []
      var arr2 = []
      val.forEach((item, index) => {
        arr.push(item.isbn)
        arr2.push(index)
      })
      this.multipleSelection = arr
      this.returnSelection = arr2
    },
    selectDisableRoom (row) {
      if (this.radio === '借书' && row.bookState.name === 'available') {
        return true
      } else if (this.radio === '还书' && row.bookState.name === 'borrowed') {
        return true
      }
    }
  }
}
</script>

<style>
  .el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
    border-color: #C0C4CC;
  }
  .el-button {
    background-color: #409EFF;
    color: white;
  }
  .article {
    text-align: left;
    margin-left: 10%;
    margin-right: 10%;
  }
  .indent {
    display: flex;
    font-size: 14px;
    line-height: 20px;
  }
  #info {
    display: inline-block;
  }
</style>
