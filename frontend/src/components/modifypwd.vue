<template>
  <div id="base_modify">
    <el-form :model="modifyForm" :rules="rules" class="modify_container" label-position="left"
             label-width="0px" v-loading="loading" :ref="modifyForm">
      <h3 class="modify_title">Modify Password </h3>
      <div class="pic">
        <img src="../assets/background/password.png" style="height: 40px;width: 40px;margin-top: -100px" alt/>
      </div>
      <el-form-item prop="username">
        <el-input type="text" v-model="modifyForm.username"
                  auto-complete="off" placeholder="username" disabled></el-input>
      </el-form-item>
      <el-form-item prop="oldPass">
        <el-input type="password" v-model="modifyForm.oldPass"
                  auto-complete="off" placeholder="old password"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="modifyForm.password"
                  auto-complete="off" placeholder="new password"></el-input>
      </el-form-item>
      <el-form-item prop="repassword">
        <el-input type="password" v-model="modifyForm.repassword"
                  auto-complete="off" placeholder="confirm password"></el-input>
      </el-form-item>
      <br>

      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #f8f8a3;border: none;border-radius: 10px;color: black;font-family: Ink Free" v-on:click=" modifypwd(modifyForm)">Modifypwd</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import store from '../store'
export default {
  name: 'modifypwd',
  data () {
    var dataValid = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.modifyForm.password) {
        callback(new Error('两次输入密码不一致!'))
        // eslint-disable-next-line eqeqeq
      } else {
        callback()
      }
    }
    return {
      modifyForm: {
        username: store.state.username,
        password: '',
        repassword: '',
        oldPass: ''
      },
      rules: {
        // blur 失去鼠标焦点时触发验证
        password: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'},
          {min: 6, max: 32, message: '密码6到32位', trigger: 'blur'},
          // eslint-disable-next-line standard/object-curly-even-spacing
          {pattern: /^(?![\d]+$)(?![a-zA-Z]+$)(?![-_.,]+$)[\da-zA-Z-_.,]{6,32}$/, message: '字⺟，数字或者特殊字符（-_）⾄少包含两种' }
        ],
        repassword: [{required: true, message: '', trigger: 'blur'}, {validator: validatePass2, trigger: 'blur'},
          {min: 6, max: 32, message: '密码6到32位', trigger: 'blur'},
          // eslint-disable-next-line standard/object-curly-even-spacing
          {pattern: /^(?![\d]+$)(?![a-zA-Z]+$)(?![-_.,]+$)[\da-zA-Z-_.,]{6,32}$/, message: '确保密码一致且字⺟，数字或者特殊字符（-_）⾄少包含两种' }
        ],
        oldPass: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'},
          {min: 6, max: 32, message: '密码6到32位', trigger: 'blur'},
          // eslint-disable-next-line standard/object-curly-even-spacing
          {pattern: /^(?![\d]+$)(?![a-zA-Z]+$)(?![-_.,]+$)[\da-zA-Z-_.,]{6,32}$/, message: '确保密码一致且字⺟，数字或者特殊字符（-_）⾄少包含两种' }
        ]
      },
      loading: false
    }
  },
  methods: {
    modifypwd (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.post('/reset', {
            'username': this.modifyForm.username,
            'oldPass': this.modifyForm.oldPass,
            'newPass': this.modifyForm.password
          })
            .then(resp => {
              // 根据后端的返回数据修改
              if (resp.status === 200) {
                // 跳转到login
                this.$message.success('successful modification')
                this.$store.commit('logout')
                this.$router.replace('/login')
              } else {
                this.$message.warning('modify error')
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
  #base_modify{
    background: url("../assets/background/libraryback.jpeg") repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  .modify_container{
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 400px;
    height: 450px;
    padding: 35px 35px 15px 35px;
    background: transparent;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .modify_title{
    margin: 0px auto 40px auto;
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
