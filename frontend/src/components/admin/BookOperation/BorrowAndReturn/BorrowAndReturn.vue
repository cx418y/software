<template>
  <el-container>
    <book-search-bar @searchClick="loadResults" ref="searchBar" class="searchBar"></book-search-bar>
    <div v-show="show1" class="show">
      <book-search-result @borrowCopies="handleBorrow" @returnCopies="handleReturn" ref="searchResult"></book-search-result>
    </div>
    <div v-show="show2" class="show" id="showDefault">
      <div class="noData-wrapper">
        <div class="noData"></div>
        <p>没有匹配的书籍哦!</p>
      </div>
    </div>
  </el-container>
</template>

<script>
import BookSearchResult from './BookSearchResult'
import BookSearchBar from './BookSearchBar'
import store from '../../../../store'
export default {
  name: 'BorrowAndReserve',
  components: {BookSearchResult, BookSearchBar},
  inject: ['reload'],
  data () {
    return {
      show1: false,
      show2: true
    }
  },
  methods: {
    loadResults () {
      let flag = false
      let flag2 = false
      let isbns = []
      for (var i = 0; i < this.$refs.searchBar.isbn.length; i++) {
        isbns[i] = this.$refs.searchBar.isbn[i]
        if (this.$refs.searchBar.isbn[i] === '') {
          flag = true
        }
      }
      for (i = 0; i < isbns.length - 1; i++) {
        for (var j = i + 1; j < isbns.length; j++) {
          if (isbns[i] === isbns[j]) {
            flag2 = true
          }
        }
      }
      if (flag) {
        this.$message.warning('内容不能为空')
      } else if (flag2) {
        this.$message.warning('ISBN不能相同')
      } else {
        this.$axios.get('/searchCopy?keywords=' + isbns).then(resp => {
          if (resp && resp.status === 200) {
            this.$refs.searchResult.result = resp.data
            this.$refs.searchResult.returnState.length = resp.data.length
            for (var i = 0; i < resp.data.length; i++) {
              this.$refs.searchResult.returnState[i] = 'good'
            }
            this.$refs.searchBar.show = false
            if (resp.data.length === 0) {
              this.show2 = true
              this.show1 = false
            } else {
              this.show1 = true
              this.show2 = false
            }
          } else if (resp.status === 404) {
            this.$message.warning('找不到图书')
          }
        }).catch(error => {
          this.$message.warning(error)
        })
      }
    },
    handleBorrow () {
      if (this.$refs.searchResult.multipleSelection.length === 0) {
        this.$message.warning('请选择书籍')
      } else if (this.$refs.searchResult.userName === '') {
        this.$message.warning('学号/工号不能为空')
      } else {
        this.$axios
          .post('/borrowBook', {
            'isbns': this.$refs.searchResult.multipleSelection,
            'userName': this.$refs.searchResult.userName,
            'adminName': store.state.username,
            'aid': store.state.aid
          }).then(resp => {
            if (resp && resp.status === 200) {
              this.$message.success('借书成功')
              this.reload()
            } else {
              this.$message.warning('找不到图书')
            }
          }).catch(error => {
            this.$message.warning(error)
            this.reload()
          })
      }
    },
    handleReturn () {
      if (this.$refs.searchResult.multipleSelection.length === 0) {
        this.$message.warning('请选择书籍')
      } else if (this.$refs.searchResult.userName === '') {
        this.$message.warning('学号/工号不能为空')
      } else {
        this.$axios
          .post('/returnBook', {
            'isbns': this.$refs.searchResult.multipleSelection,
            'states': this.$refs.searchResult.returnSelection,
            'userName': this.$refs.searchResult.userName,
            'adminName': store.state.username,
            'returnAid': store.state.aid
          }).then(resp => {
            if (resp && resp.status === 200) {
              this.$message.success('还书成功')
              this.reload()
            } else {
              this.$message.warning('找不到图书')
            }
          }).catch(error => {
            this.$message.warning(error)
            this.reload()
          })
      }
    }
  }
}
</script>

<style scoped>
  .el-container {
    width: 80%;
    margin-left: auto;
    margin-right: auto;
    display: block;
  }
  .show {
    display: block;
    border-radius: 10px;
    background-color: white;
    width: 75%;
    margin-left: auto;
    margin-right: auto;
    margin-top: 40px;
    padding-top: 50px;
    padding-bottom: 50px;
  }
  #showDefault {
    height: 300px;
  }
  .noData-wrapper {
    display: inline-block;
    margin-top: 55px;
    color: #909399;
  }
  .noData {
    width: 160px;
    height: 160px;
    background: url(../../../../assets/noData.png) no-repeat;
    background-size: contain;
    opacity: 0.3;
  }
</style>
