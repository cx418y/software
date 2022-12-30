<template>
  <el-container>
    <el-header style="height: auto;background-color: transparent ;border-radius:10px;text-align: right; font-size: 12px;margin-bottom: 10px;width: auto">
      <h1 style="float:left;color: beige;font-size: 30px;font-family: 华光细圆_CNKI;margin-top: 5px">已读书籍</h1>
    </el-header>

    <el-main style="padding: 0px">
      <div v-if="books.length === 0" style="border-radius: 10px;color: #122d40;min-height: 400px;background-color:whitesmoke;overflow: auto;width: 90%;text-align:center;padding:25px">
        <div class="noData-wrapper">
          <div class="noData"></div>
          <p>没有匹配的书籍哦!</p>
        </div>
      </div>
      <div v-else style="border-radius: 10px;color: #122d40;min-height: 500px;background-color:whitesmoke;overflow: auto;width: 90%;text-align:left;padding:25px">
        <el-card
          v-for="item in books.slice((currentPage-1)*pagesize, currentPage*pagesize)"
          :key="item.bookId"
          style="display: block;background-color: white;width: 100%;margin-bottom:20px;min-height:280px"
        >
          <div @click="handleDetail(item.bookId,item.isbn)" style="display:inline-block;">
          <img :src="item.cover" alt="封面" id="pic">
          </div>
          <div style="display: inline-block;margin-left: 50px;font-size: 15px">
            <span>书名：{{item.title}}</span>
            <br><br>
            <span>作者：{{item.author}}</span>
            <br><br>
            <span>ISBN：{{item.isbn}}</span>
            <br><br>
            <span>最近借阅时间：{{item.reverseTime}}</span>
            <br><br>
            <span v-show="item.rate" class="comment">书评：{{item.comment}}</span>
            <br><br>
          </div>
          <div style="display: inline-block;margin-left: 60%">
            <el-button disabled style="background-color: white;color: black;border-radius: 10px;font-family: 华光细圆_CNKI;width: 100px" v-if="(item.delete)">已删除</el-button>
            <el-button @click="Delete(item.isbn,item.commentId)" v-else-if="((!item.delete) && (item.commentId !== -1))" style="background-color: #f8f8a3;color: black;border-radius: 10px;font-family: 华光细圆_CNKI;width: 100px">删除评论</el-button>
            <el-button disabled style="background-color: white;color: black;border-radius: 10px;font-family: 华光细圆_CNKI;width: 100px" v-if="item.rate"><i class="el-icon-star-off"></i>已评价</el-button>
            <el-button @click="dialogVisible = true" v-else style="background-color: #f8f8a3;color: black;border-radius: 10px;font-family: 华光细圆_CNKI;width: 100px"><i class="el-icon-star-off"></i>评价</el-button>
          <br>
            <el-dialog
            title="评价"
            :visible.sync="dialogVisible"
            width="60%"
            style="margin-top: 150px"
            :before-close="handleClose">
            <div>
              <span style="font-size: 16px">评分：</span>
              <br><br>
              <el-rate v-model="value"></el-rate>
            </div>
            <br>
            <div>
              <span style="font-size: 16px">请输入书评：</span>
              <br><br>
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 5}"
                placeholder=""
                v-model="textarea"
              >
              </el-input>
            </div>
            <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="handleClick(item.bookId,item.isbn)">确 定</el-button>
              </span>
          </el-dialog>
          </div>
        </el-card>
        <div class="pagedivide">
          <el-pagination
            style="text-align: center"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pagesize"
            :total="books.length">
          </el-pagination>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import store from '../../../store'
export default {
  name: 'UserRead',
  inject: ['reload'],
  data () {
    return {
      books: [],
      currentPage: 1,
      pagesize: 5,
      dialogVisible: false,
      textarea: '',
      value: 5
    }
  },
  mounted: function () {
    this.loadBooks()
  },
  methods: {
    loadBooks () {
      this.$axios.get('/readAllBooks/' + store.state.username).then(resp => {
        if (resp && resp.status === 200) {
          this.books = resp.data
        }
      }).catch(error => {
        console.log(error)
      })
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    handleDetail (key, isbn) {
      this.$router.push({
        path: '/index/detail',
        query: {
          id: key,
          isbn: isbn
        }
      })
    },
    Delete (isbn, commentId) {
      this.$axios.post('/deleteComment', {
        'commentId': commentId,
        'userName': store.state.username
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$message.success('删除评论成功！')
          this.reload()
        } else {
          this.$message.error('删除评论失败')
        }
      })
    },
    handleClick (id, isbn) {
      this.$axios
        .post('/evaluation', {
          'rate': this.value,
          'evaluation': this.textarea,
          'isbn': isbn,
          'userName': store.state.username
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.$message.success('提交评价成功！')
            this.dialogVisible = false
            this.handleDetail(id, isbn)
          }
        }).catch(error => {
          this.$message.warning(error)
        })
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    }
  }
}
</script>

<style scoped>
  #pic {
    width: 175px;
    height: 220px;
    display: inline-block;
  }
  .noData-wrapper {
    display: inline-block;
    margin-top: 75px;
    color: #909399;
  }
  .noData {
    width: 160px;
    height: 160px;
    background: url(../../../assets/noData.png) no-repeat;
    background-size: contain;
    opacity: 0.3;
  }
</style>
<style>
  .pagedivide .el-pagination .el-pager li:not(.disabled) {
    background-color: white;
    color: black;
  }
  .pagedivide .el-pagination .el-pager li:not(.disabled).active {
    background-color: #f8f8a3;
    color: black;
  }
  .pagedivide .el-pagination .el-pager li.active {
    color: black;
    cursor: default;
  }
  .pagedivide .el-pagination .el-pager li:hover {
    background-color: #f8f8a3;
    color: black;
  }
  .pagedivide .el-pagination .el-pager li:not(.disabled):hover {
    background-color: #f8f8a3;
    color: black;
  }
  .pagedivide .el-pagination .el-pager li:not(.disabled).active:hover {
    background-color: #f8f8a3;
    color: black;
  }
</style>
