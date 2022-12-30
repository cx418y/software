<template>
  <div id="base_login">
    <el-form :model="loginForm"
             :rules="rules"
             class="forget_container"
             label-position="left"
             label-width="0px"
             v-loading="loading"
             ref="loginForm">
      <h3 class="login_title">Login</h3>
      <div class="pic">
     <img src="../assets/background/书.png" style="height: 40px;width: 40px;margin-top: -100px" alt/>
      </div>
      <el-form-item prop="username" >
        <el-input type="text"
                  v-model="loginForm.username"
                  auto-complete="off"
                   placeholder="username"></el-input>
      </el-form-item>
      <el-form-item prop="password"  >
        <el-input type="password" v-model="loginForm.password" auto-complete="off"
                  placeholder="password"  ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" class="button" v-on:click="login('loginForm')">login</el-button>
        <br>
        <router-link to="register">
          <el-button type="primary" class="button">register</el-button>
        </router-link>
      </el-form-item>
      <div class="forget">
        <el-button size="mini" type="text" @click="forget" style="font-family: 'Ink Free';color: ghostwhite;font-size: large;background-color: transparent;">Forget Password</el-button>
      </div>

      <div class="lib">
        <el-button size="mini" type="text" @click="liblogin" style="font-family: 'Ink Free';color: ghostwhite;font-size: large;background-color: transparent">I am A Librarian</el-button>
      </div>

    </el-form>
  </div>

</template>

<script>
export default {
  name: 'Login',
  inject: ['reload'],
  data () {
    const dataValid = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Can\'t be empty'))
      }

      return callback()
    }
    return {
      loginForm: {
        username: '',
        password: '',
        input4: ''
      },
      rules: {
        username: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'},
          {pattern: /^[0-9]*$/, message: '请输入学号或者工号'}
        ],
        password: [{required: true, message: '', trigger: 'blur'}]
      },
      loading: false
    }
  },

  methods: {
    login (formname) {
      if (this.loginForm.username.length !== 5 && this.loginForm.username.length !== 11) {
        this.$message.warning('输入学号或者工号')
      } else {
        this.$refs[formname].validate((valid) => {
          if (valid) {
            this.$axios.post('/login', {
              username: this.loginForm.username,
              password: this.loginForm.password
            })
              .then(resp => {
                if (resp.status === 200) {
                  // && resp.data.hasOwnProperty('token')(delete了）
                  this.$store.commit('login', resp.data)
                  let redirect = decodeURIComponent(this.$route.query.redirect || '/index')
                  this.$router.push({ path: redirect })
                } else {
                  this.$message.warning('username or password is wrong!')
                }
              })
              .catch(error => {
                this.$message.warning(error)
              })
          } else {
            this.$message.warning('username or password is wrong!')
          }
        })
      }
    },
    forget () {
      this.$router.push('/forgetpwd')
    },
    liblogin () {
      this.$router.push('/libLogin')
    }
  }
}
</script>

<style scoped>
  #base_login{
    background: url("../assets/background/libraryback.jpeg") repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
    padding: 0px;
  }
  .forget_container{
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    height: 400px;
    padding: 35px 35px 15px 35px;
    background: transparent;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #f8f8a3;

  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #f8f8a3;
    font-size: xx-large;
    font-family: "Ink Free",serif;
  }
  .el-input__inner {
    width: 350px;
    border-radius: 100px;
  }
  .el-form-item /deep/ .el-form-item__error{
    color: #f8f8a3;
    font-family: "Ink Free",serif;
  }
  .el-form-item /deep/ .el-input__inner{
    color: #000000;
   width: 350px;
    border-radius: 10px;
    background-color: #e1f8f3;
  }
  .button{
    width: 40%;
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
  .forget{
    float: left;
  }
  .lib{
    float: right;
  }

</style>
