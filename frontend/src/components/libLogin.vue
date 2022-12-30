<template>
  <div id="base_liblogin">
    <el-form :model="libloginForm" :rules="rules" class="liblogin_container" label-position="left"
             label-width="0px" v-loading="loading" :ref="libloginForm">
      <h3 class="liblogin_title">Library Login</h3>
      <div class="pic">
        <img src="../assets/background/librarian.png" style="height: 40px;width: 40px;margin-top: -100px" alt/>
      </div>
      <el-form-item prop="username">
        <el-input type="text" v-model="libloginForm.username"
                  auto-complete="off" placeholder="username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="libloginForm.password"
                  auto-complete="off" placeholder="password"></el-input>
      </el-form-item>
      <el-form-item prop="userarea">
        <el-radio-group v-model="libloginForm.userarea" @change="userAreaChange">
          <el-radio label="handan" border>邯郸</el-radio>
          <el-radio label="fenglin" border>枫林</el-radio>
          <br>
          <el-radio label="jiangwan" border>江湾</el-radio>
          <el-radio label="zhangjiang" border>张江</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #f8f8a3;border: none;border-radius: 10px;color: black;font-family: Ink Free" v-on:click="liblogin(libloginForm)">Login</el-button>
      </el-form-item>
      <el-form-item style="width: 100%;text-align: center;">
        <label style="color: beige;font-family: Ink Free">超管账号：admin</label>
        <label style="color: beige;font-family: Ink Free">超管密码：password</label>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'LibLogin',
  data () {
    const dataValid = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Can\'t be empty'))
      }

      return callback()
    }
    return {
      libloginForm: {
        username: '',
        password: '',
        userarea: ''
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
        ],
        userarea: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'}]
      },
      loading: false
    }
  },
  methods: {
    userAreaChange () {
    },
    liblogin (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.post('/libLogin', {
            'username': this.libloginForm.username,
            'password': this.libloginForm.password,
            'area': this.libloginForm.userarea
          })
            .then(resp => {
              // 根据后端的返回数据修改
              if (resp.status === 200) {
                // 跳转到login && resp.data.hasOwnProperty('id')
                this.$message.warning('successful login ! welcome back,librarian!')
                this.$store.commit('login', resp.data)
                let redirect = decodeURIComponent(this.$route.query.redirect || '/admin')
                this.$router.push({ path: redirect })
              } else {
                this.$message.warning('login error')
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
  #base_liblogin{
    background: url("../assets/background/libraryback.jpeg") repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  .liblogin_container{
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
  .liblogin_title{
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
    margin-left: 10px;
    margin-bottom: 15px;
    display: inline-block;
    color: #f8f8a3;
    font-size: large;
    font-family: 'Ink Free',serif
  }
</style>
