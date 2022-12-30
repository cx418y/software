<template>
  <div>
    <el-row>
      <el-tooltip
        effect="dark"
        placement="right"
        v-for="item in results.slice((currentPage-1)*pagesize, currentPage*pagesize)"
        :key="item.id">
        <p slot="content">《{{item.title}}》</p>
        <p slot="content">
          <span>作者：{{item.author}}</span>
        </p>
        <p slot="content">
          <span>出版日期：{{item.date}}</span> \
          <span>馆藏地址：{{item.area.name}}</span> \
          <span>数量：{{item.number}}</span>
        </p>
        <p slot="content">
          <span>ISBN：{{item.isbn}}</span>
        </p>
        <p slot="content" style="width: 300px;max-height: 200px;line-height:20px;overflow: hidden;text-overflow: ellipsis;">
          <span style="width: 300px;max-height: 200px;line-height:20px;overflow: hidden;text-overflow: ellipsis;">简介：{{item.intro}}</span>
        </p>
        <el-card class="book" shadow="hover">
          <div class="information" @click="handleDetail(item.id)">
            <div class="cover">
              <img :src="item.cover" alt="封面">
            </div>
            <div class="title">{{item.title}}</div>
            <div class="author">{{item.author}}</div>
          </div>
        </el-card>
      </el-tooltip>
    </el-row>
    <el-row>
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
  name: 'SearchResult',
  data () {
    return {
      results: [],
      currentPage: 1,
      pagesize: 8
    }
  },
  methods: {
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    handleDetail (key) {
      this.$router.push({
        path: '/index/detail',
        query: {
          id: key
        }
      })
    }
  }
}
</script>

<style scoped>
  .el-row{
    background-color: white;
    text-align: left;
    width: 100%;
    padding: 20px 18px;
  }
  .el-card{
    width: 180px;
    height: 250px;
    display: inline-block;
    margin: 20px;
  }
  .cover {
    width: 100%;
    height: 172px;
    margin-bottom: 5px;
    overflow: hidden;
    cursor: pointer;
    display: inline-block;
  }
  img {
    width: 100%;
    height: 172px;
  }

  .title {
    font-size: 14px;
    margin-bottom: 3px;
    text-align: center;
  }

  .author {
    color: #333;
    width: 100%;
    font-size: 13px;
    margin-bottom: 6px;
    display: inline-block;
    text-align: center;
  }
  .el-pagination{
    text-align: center;
  }
</style>
