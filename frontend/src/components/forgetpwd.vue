<template>
  <div id="base_forget">
    <el-form :model="forgetForm"
             :rules="rules"
             class="forget_container"
             label-position="left"
             label-width="0px"
             v-loading="loading"
             ref="forgetForm">
      <h3 class="forget_title">Forget My Password</h3>
      <div class="pic">
        <img src="../assets/background/书.png" style="height: 40px;width: 40px;margin-top: -100px" alt/>
      </div>
      <el-form-item prop="username" >
        <el-input type="text"
                  v-model="forgetForm.username"
                  auto-complete="off"
                  placeholder="username"></el-input>
      </el-form-item>
      <el-form-item prop="code" >
        <el-input type="text"
                  v-model="forgetForm.code"
                  auto-complete="off"
                  placeholder="confirm code" style="width: 150px;margin-right: 50px"></el-input>
        <el-button type="primary" class="button" v-on:click="getCode">Get Code</el-button>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" class="button" v-on:click="forget('forgetForm')">Confirm</el-button>
        <br>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
export default {
  name: 'Forgrtpwd',
  data () {
    const dataValid = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Can\'t be empty'))
      }

      return callback()
    }
    return {
      forgetForm: {
        username: '',
        code: ''
      },
      rules: {
        username: [{required: true, message: '', trigger: 'blur'}, {validator: dataValid, trigger: 'blur'},
          {pattern: /^[0-9]*$/, message: '请输入学号或者工号'}
        ],
        code: [{required: true, message: '', trigger: 'blur'}]
      },
      loading: false
    }
  },

  methods: {
    getCode () {
      this.$axios.post('/forget', {
        username: this.forgetForm.username
      }).then(resp => {
        if (resp.status === 200) {
          this.$message.success('请到邮箱中获取验证码')
        } else {
          this.$message.warning('username is wrong!')
        }
      })
        .catch(error => {
          this.$message.warning(error)
        })
    },
    forget (formname) {
      if (this.forgetForm.username.length !== 5 && this.forgetForm.username.length !== 11) {
        this.$message.warning('输入学号或者工号')
      } else {
        this.$refs[formname].validate((valid) => {
          if (valid) {
            this.$axios.post('/verify', {
              'username': this.forgetForm.username,
              'input': this.forgetForm.code
            })
              .then(resp => {
                if (resp.status === 200) {
                  this.$message.success('验证成功，请重置密码')
                  this.$router.push({
                    path: '/forgetReset',
                    query: {
                      username: this.forgetForm.username
                    }
                  })
                } else {
                  this.$message.warning('username or code is wrong!')
                }
              })
              .catch(error => {
                this.$message.warning(error)
              })
          } else {
            this.$message.warning('username or code is wrong!')
          }
        })
      }
    }
  }
}
</script>

<style scoped>
  #base_forget{
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
    height: 350px;
    padding: 35px 35px 15px 35px;
    background: transparent;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #f8f8a3;

  }
  .forget_title {
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

</style>
