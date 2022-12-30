<template>
  <el-container>
    <user-search-bar @searchClick="loadResults" ref="searchBar" class="searchBar"></user-search-bar>
    <div v-show="show1" class="show">
      <user-search-result @reserveClick="handleReserve" ref="searchResult"></user-search-result>
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
import UserSearchResult from './UserSearchResult'
import UserSearchBar from './UserSearchBar'
import store from '../../../../store'
export default {
  name: 'GetReserve',
  components: {UserSearchResult, UserSearchBar},
  inject: ['reload'],
  data () {
    return {
      show1: false,
      show2: true
    }
  },
  methods: {
    loadResults () {
      if (this.$refs.searchBar.keywords === '') {
        this.$message.warning('内容不能为空')
      } else {
        this.$axios.get('/search-user/' + this.$refs.searchBar.keywords).then(resp => {
          if (resp && resp.status === 200) {
            this.$refs.searchResult.results = resp.data
            if (resp.data.length === 0) {
              this.$message.warning('找不到该用户或该用户暂无预约书籍')
              this.show2 = true
              this.show1 = false
            } else {
              this.show1 = true
              this.show2 = false
            }
          } else {
            this.$message.warning('找不到该用户')
          }
        }).catch(error => {
          this.$message.warning(error)
        })
      }
    },
    handleReserve () {
      if (this.$refs.searchResult.multipleSelection.length === 0) {
        this.$message.warning('请选择书籍')
      } else {
        this.$axios
          .post('/getReserve', {
            'isbn': this.$refs.searchResult.multipleSelection,
            'userName': this.$refs.searchBar.keywords,
            'adminName': store.state.username,
            'aid': store.state.aid
          }).then(resp => {
            if (resp && resp.status === 200) {
              this.$message.success('取书成功')
              this.reload()
            } else {
              this.$message.warning('取书失败')
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
