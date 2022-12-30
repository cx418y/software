<template>
  <div id="base_register">
    <el-form :model="registerForm" :rules="rules" class="register_container" label-position="left"
             label-width="0px" v-loading="loading" :ref="registerForm">
      <h3 class="register_title">Register</h3>
      <div class="pic">
        <img src="../assets/background/register.png" style="height: 40px;width: 40px;margin-top: -100px" alt/>
      </div>
      <el-form-item prop="username">
        <el-input type="text" v-model="registerForm.username" style="width: 350px"
                  auto-complete="off" placeholder="username" @change="Email"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="registerForm.password" style="width: 350px"
                  auto-complete="off" placeholder="password"></el-input>
      </el-form-item>
      <el-form-item prop="email">
        <el-input type="text" v-model="registerForm.email" style="width: 350px"
                  auto-complete="off" placeholder="email" disabled></el-input>
      </el-form-item>
      <el-form-item prop="code" >
        <el-input type="text"
                  v-model="registerForm.code"
                  auto-complete="off"
                  placeholder="confirm code" style="width:200px"></el-input>
        <el-button type="primary" class="button" v-on:click="getCode">Get Code</el-button>
      </el-form-item>
      <br>
      <el-form-item prop="usertype">
        <el-radio-group v-model="registerForm.usertype">
          <el-radio label="Teacher" border>教师</el-radio>
          <el-radio label="Graduate" border>研究生</el-radio>
          <el-radio label="underGraduate" border>本科生</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" class="button" v-on:click="register(registerForm)">register</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data () {
    const dataValid = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Can\'t be empty'))
      }

      return callback()
    }
    return {
      registerForm: {
        username: '',
        password: '',
        email: '',
        usertype: ''
      },
      rules: {
        // blur 失去鼠标焦点时触发验证
        username: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'},
          {pattern: /^[0-9]*$/, message: '请输入学号或者工号'}
        ],
        password: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'},
          {min: 6, max: 32, message: '密码6到32位', trigger: 'blur'},
          // eslint-disable-next-line standard/object-curly-even-spacing
          {pattern: /^(?![\d]+$)(?![a-zA-Z]+$)(?![-_.,]+$)[\da-zA-Z-_.,]{6,32}$/, message: '字⺟，数字或者特殊字符（-_）⾄少包含两种' }
        ],
        code: [{required: true, message: '', trigger: 'blur'}],
        usertype: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}]
      },
      loading: false
    }
  },
  computed: {
  },
  methods: {
    Email () {
      this.registerForm.email = this.registerForm.username + '@fudan.edu.cn'
    },
    getCode () {
      this.$axios.post('/registerVerify', {
        'userName': this.registerForm.username
      }).then(resp => {
        if (resp.status === 200) {
          this.$message.success('请到邮箱中获取验证码')
        } else {
          this.$message.warning('email is wrong!')
        }
      })
        .catch(error => {
          this.$message.warning(error)
        })
    },
    register (formName) {
      if (this.registerForm.username.length !== 5 && this.registerForm.username.length !== 11) {
        this.$message.warning('输入学号或者工号')
      } else if (this.registerForm.password.includes(this.registerForm.username)) {
        this.$message.warning('the password cannot contain your username')
      } else {
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.$axios.post('/register', {
              'userName': this.registerForm.username,
              'password': this.registerForm.password,
              'email': this.registerForm.email,
              'input': this.registerForm.code,
              'authority': this.registerForm.usertype
            })
              .then(resp => {
                // 根据后端的返回数据修改
                if (resp.status === 200) {
                  // 跳转到login && resp.data.hasOwnProperty('id')
                  this.$message.success('successful registration')
                  this.$router.push({path: '/login'})
                  this.$router.go(0)
                } else {
                  this.$message.warning('register error')
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
}
</script>

<style scoped>
  #base_register{
    background: url("../assets/background/libraryback.jpeg") repeat;
    background-position: center;
    height: 150%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  .register_container{
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 400px;
    height: 550px;
    padding: 35px 35px 15px 35px;
    background: transparent;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .register_title{
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
    border-radius: 10px;
    background-color: aliceblue;
  }
  .el-radio{
    width: 100px;
    color: #f8f8a3;
    font-size: large;
    font-family: 'Ink Free',serif
  }
  .button{
    width: 150px;
    background: #f8f8a3;
    border: none;
    border-radius: 20px ;
    font-family: Ink Free,serif;
    height: 35px;
    text-align: center;
    color:black;
    padding: 0;
    margin-bottom: 10px;
  }
</style>
