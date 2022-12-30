<template slot-scope="scope">
    <div>
      <el-table
        :data="books.slice((currentPage-1)*pagesize, currentPage*pagesize)"
        class="bookShow"
      >
        <el-table-column
          prop="title"
          label="书名"
          width="80"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="author"
          label="作者"
          width="80"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="isbn"
          label="ISBN"
          width="150">
        </el-table-column>
        <el-table-column
          prop="price"
          label="价格"
          width="80">
        </el-table-column>
        <el-table-column
          prop="intro"
          label="简介"
          width="150"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="number"
          label="副本数量"
          width="90">
        </el-table-column>
        <el-table-column
          label="馆藏地址"
          width="100"
        >
          <template slot-scope="scope">
            {{scope.row.area.name}}
          </template>
        </el-table-column>
        <el-table-column
          prop="date"
          label="出版日期"
          width="110"
        >
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="showCopies(scope.row.isbn)">查看副本</el-button>
            <el-button size="mini" @click="addCopies(scope.row.isbn)">添加副本</el-button>
            <el-button size="mini" @click="handleDetail(scope.row.id,scope.row.isbn)">查看详情</el-button>
            <el-dialog
              title="查看副本"
              :visible.sync="dialogVisible"
              width="60%"
              style="margin-top: 30px"
              :before-close="handleClose">
              <show-copy ref="Show" class="show" v-if="dialogVisible"></show-copy>
              <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
              </span>
            </el-dialog>
            <el-dialog
              title="添加副本"
              :visible.sync="dialogVisible2"
              width="30%"
              :before-close="handleClose">
              <el-input-number v-model="num" :min="0" :max="10" label="数量" placeholder="0" style="margin-left: 25%;width: 50%"></el-input-number>
              <!--<div v-for="(item,index) in area" :key="index" style="width: 70%;margin-left:10%;text-align: right;margin-bottom: 10px">
                {{area[index]}}:
                <el-input-number v-model="num[index]" :min="0" :max="10" label="数量" placeholder="0" style="margin-left: 20px"></el-input-number>
              </div>-->
              <span slot="footer" class="dialog-footer">
              <el-button @click="reset">重置</el-button>
              <el-button type="primary" @click="add">确 定</el-button>
              </span>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <el-row class="page">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pagesize"
          :total="books.length">
        </el-pagination>
      </el-row>
    </div>
</template>

<script>
import ShowCopy from './ShowCopy'
import store from '../../../store'
export default {
  name: 'AdminBookShow',
  components: {ShowCopy},
  data () {
    return {
      books: [],
      num: 0,
      isbn: '',
      dialogVisible: false,
      dialogVisible2: false,
      currentPage: 1,
      pagesize: 8
    }
  },
  mounted () {
    this.getBooks()
  },
  methods: {
    getBooks () {
      this.$axios.get('/findAllBooks').then(resp => {
        if (resp && resp.status === 200) {
          this.books = resp.data
        }
      })
    },
    showCopies (isbn) {
      this.$axios.get('/getCopies/' + isbn).then(resp => {
        if (resp) {
          this.$refs.Show.copies = resp.data
        }
      })
      this.dialogVisible = true
    },
    addCopies (isbn) {
      this.dialogVisible2 = true
      this.isbn = isbn
    },
    add () {
      this.$axios
        .post('/addCopies', {
          'isbn': this.isbn,
          'num': this.num,
          'aid': store.state.aid
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.$message.success('添加副本成功')
            this.dialogVisible2 = false
            history.go(0)
          }
        })
    },
    reset () {
      this.num = 0
      this.dialogVisible2 = false
      this.addCopies()
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    handleDetail (id, isbn) {
      this.$router.push({
        path: '/DetailShowLib',
        query: {
          id: id,
          isbn: isbn
        }
      })
    }

  }
}
</script>

<style scoped>
.bookShow {
  width: 90%;
  margin-left: 5%;
  margin-top: 30px;
  padding: 20px;
  min-height: 500px;
}
.el-button {
  background-color: #409EFF;
  color: white;
}
.show {
  height: 200px;
}
.page{
  width: 90%;
  margin-left: 5%;
  padding: 10px;
  background-color: white;
}
</style>
<style>
  .el-tooltip__popper{
    max-width: 50%;
  }
</style>
