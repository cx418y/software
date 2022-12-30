<template>
  <el-main>
    <el-table :data="libData " style="border-radius: 10px;color: #122d40;width:450px;height: auto;background-color:whitesmoke;padding: 20px;overflow: auto">
      <el-table-column style="font-family: Ink Free" prop="libname" label="管理员姓名" width="200">
      </el-table-column>
      <el-table-column style="font-family: Ink Free" prop="libarea" label="分馆" width="200">
      </el-table-column>
    </el-table>
    <br>
    <br>
    <router-link to="addLibrarian">
      <el-button type="primary" icon="el-icon-plus" class="button" circle></el-button>
    </router-link>
  </el-main>
</template>

<script>
export default {
  name: 'adminList',
  data () {
    return {
      libData: []
    }
  },
  mounted () {
    this.getLib()
    // this.getUser()
  },
  methods: {
    handleChange (val) {
      console.log(val)
    },
    getLib () {
      this.$axios.get('/getlibrarian/').then(resp => {
        if (resp && resp.status === 200) {
          this.LibData = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
