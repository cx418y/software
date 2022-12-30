<template>
  <div class="searchBar-container">
  <div style="display: flex;justify-content: center;align-items: center;margin-top:30px;margin-bottom: auto">
    <el-input
      @keyup.enter.native="handleClick"
      placeholder="书名/作者/ISBN"
      prefix-icon="el-icon-search"
      style="width: 60%;margin-right: 10px"
      v-model="keywords">
    </el-input>
    <el-button type="primary" icon="el-icon-search" @click="handleClick">搜索</el-button>
  </div>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  data () {
    return {
      keywords: ''
    }
  },
  methods: {
    handleClick () {
      this.$emit('searchClick')
    }
  }
}
</script>

<style scoped>
.searchBar-container {
  display: block;
  border-radius: 10px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 70%;
  height: 100px;
  padding: 35px 35px 35px 35px;
  background: white;
  border: 1px solid #eaeaea;
}
</style>
