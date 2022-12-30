<template>
  <div class="searchBar-container">
    <div style="display: flex;justify-content: center;align-items: center;margin-top:30px;margin-bottom: auto">
      <el-input
        @keyup.enter.native="handleClick"
        placeholder="请输入学号/工号"
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
  name: 'UserSearchBar',
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
    margin: 50px auto 70px;
    width: 70%;
    height: 100px;
    padding: 35px 35px 35px 35px;
    background: white;
    border: 1px solid #eaeaea;
  }
</style>
