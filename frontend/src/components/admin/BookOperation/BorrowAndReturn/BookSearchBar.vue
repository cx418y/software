<template>
  <div class="searchBar-container">
    <div style="display: flex;justify-content: center;align-items: center;margin-top:30px;margin-bottom: auto">
      <div style="width: 60%;margin-right: 10px;color: gray">
        <label>请输入要借/还的图书数量：</label>
        <el-input-number v-model="num" :min="1" :max="5" label="数量" placeholder="0"></el-input-number>
      </div>
      <!--<el-input
        @keyup.enter.native="handleClick"
        placeholder="请输入副本标识"
        prefix-icon="el-icon-search"
        style="width: 60%;margin-right: 10px"
        v-model="keywords">
      </el-input>-->
      <el-button type="primary" @click="ShowISBN">确认</el-button>
      <el-dialog
        title="请输入ISBN"
        :visible.sync="show"
        width="30%"
        :before-close="handleClose">
        <div v-for="(item,index) in isbn" :key="index" style="width: 70%;margin-left:10%;text-align: right;margin-bottom: 10px">
          {{index+1}}:
          <el-input v-model="isbn[index]" style="margin-left: 20px;width: 80%"></el-input>
        </div>
        <span slot="footer" class="dialog-footer">
              <el-button type="primary" icon="el-icon-search" @click="handleClick">搜索</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BookSearchBar',
  data () {
    return {
      isbn: [],
      show: false,
      num: 1
    }
  },
  methods: {
    ShowISBN () {
      this.show = true
      this.isbn.length = this.num
      for (var i = 0; i < this.num; i++) {
        this.isbn[i] = ''
      }
    },
    handleClick () {
      this.$emit('searchClick')
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
