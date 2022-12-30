<template>
<div id="base_add">
  <el-form :model="addForm" :rules="rules" class="add_container" label-position="left"
           label-width="0px" v-loading="loading" :ref="addForm">
    <h3 class="add_title">Add Librarian</h3>
    <div class="pic">
      <img src="../../../assets/background/librarian.png" style="height: 40px;width: 40px;margin-top: -100px" alt/>
    </div>
    <el-form-item prop="username" style="margin-top: 22px">
      <el-input type="text" v-model="addForm.username"
                auto-complete="off" placeholder="username"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="addForm.password"
                auto-complete="off" placeholder="password"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="background: #f8f8a3;border: none;border-radius: 10px;color: black;font-family: Ink Free" v-on:click="addLibrarian(addForm)">Add</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
export default {
  name: 'addLibrarian',
  data () {
    const dataValid = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Can\'t be empty'))
      }

      return callback()
    }
    return {
      addForm: {
        username: '',
        password: ''
      },
      rules: {
        // blur 失去鼠标焦点时触发验证
        username: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'},
          {min: 5, max: 32, message: '用户名5到32位', trigger: 'blur'},
          {pattern: /^[a-zA-Z_][a-zA-Z0-9_-]*$/, message: '只能包含字⺟，数字或两种特殊字符（-_）且只能以字⺟或-开头'}
        ],
        password: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'},
          {min: 6, max: 32, message: '密码6到32位', trigger: 'blur'},
          // eslint-disable-next-line standard/object-curly-even-spacing
          {pattern: /^(?![\d]+$)(?![a-zA-Z]+$)(?![-_.,]+$)[\da-zA-Z-_.,]{6,32}$/, message: '字⺟，数字或者特殊字符（-_）⾄少包含两种' }
        ]
      },
      loading: false
    }
  },
  methods: {
    addLibrarian (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.post('/addLibrarian', {
            username: this.addForm.username,
            password: this.addForm.password
          })
            .then(resp => {
              // 根据后端的返回数据修改
              if (resp.status === 200) {
                // 跳转到login   && resp.data.hasOwnProperty('id') DELETE
                this.$message.warning('successful ')
                this.$router.replace('/login')
              } else {
                this.$message.warning('adding error')
              }
            })
            .catch(error => {
              this.$message.warning(error.message)
            })
        } else {
          this.$message.warning('wrong submit')
        }
      })
    }
  }
}
</script>

<style scoped>
  #base_add{
    width: 100%;
    display: block;
  }
  .add_container{
    border-radius: 15px;
    background-clip: padding-box;
    margin-top: 50px;
    margin-left: 30%;
    width: 35%;
    min-height: 400px;
    padding: 35px 35px 15px 35px;
    background: transparent;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .add_title{
    text-align: center;
    color: #f8f8a3;
    font-family: "Ink Free",serif;
    font-size: xx-large;
  }
  .el-form-item /deep/ .el-form-item__error {
    color: #f8f8a3;
    font-family: "Ink Free",serif;
  }
  .el-form-item /deep/ .el-form-item__error{
    color: #f8f8a3;
    font-family: "Ink Free",serif;
  }
  .el-form-item /deep/ .el-input__inner{
    color: #000000;
    width: 350px;
    border-radius: 10px;
    background-color: aliceblue;
  }
  .el-radio{
    width: 100px;
    color: #f8f8a3;
    font-size: large;
    font-family: 'Ink Free',serif
  }
</style>
