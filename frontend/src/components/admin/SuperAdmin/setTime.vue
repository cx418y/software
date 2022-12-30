<template>
  <div class="setTime">
    <div id="teacher">
      <el-form :model="teacherForm" :rules="rules" class="container" label-position="left"
           v-loading="loading" :ref="teacherForm">
        <h3 class="title">教师身份</h3>
        <el-form-item prop="reserveTime1">
        <label class="setLabel">预约规定时间</label>
      <el-input-number v-model="teacherForm.reserveTime[0]" controls-position="right" :min="0" :max="30" size="mini"></el-input-number>
      <span style="color: beige">天</span>
      <el-input-number v-model="teacherForm.reserveTime[1]" controls-position="right" :min="0" :max="23" size="mini"></el-input-number>
      <span style="color: beige">小时</span>
      <br>
      <el-input-number v-model="teacherForm.reserveTime[2]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
      <span style="color: beige">分钟</span>
      <el-input-number v-model="teacherForm.reserveTime[3]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
      <span style="color: beige">秒</span>
    </el-form-item>
    <el-form-item prop="borrowTime1">
      <label class="setLabel">借阅规定时间</label>
      <el-input-number v-model="teacherForm.borrowTime[0]" controls-position="right" :min="0" :max="30" size="mini"></el-input-number>
      <span style="color: beige">天</span>
      <el-input-number v-model="teacherForm.borrowTime[1]" controls-position="right" :min="0" :max="23" size="mini"></el-input-number>
      <span style="color: beige">小时</span>
      <br>
      <el-input-number v-model="teacherForm.borrowTime[2]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
      <span style="color: beige">分钟</span>
      <el-input-number v-model="teacherForm.borrowTime[3]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
      <span style="color: beige">秒</span>
    </el-form-item>
    <el-form-item prop="num1">
      <label class="setLabel">最大借阅副本数量</label>
      <el-input-number v-model="teacherForm.num" :min="1" :max="10"></el-input-number>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #f8f8a3;border: none;border-radius: 10px;color: black;font-family: Ink Free" v-on:click="setTeacher(teacherForm)">设置</el-button>
    </el-form-item>
  </el-form>
</div>
  <div id="graduate" >
    <el-form :model="graduateForm" :rules="rules" class="container" label-position="left"
             v-loading="loading" :ref="graduateForm">
      <h3 class="title">研究生身份</h3>
      <el-form-item prop="reserveTime2">
        <label class="setLabel">预约规定时间</label>
        <el-input-number v-model="graduateForm.reserveTime[0]" controls-position="right" :min="0" :max="30" size="mini"></el-input-number>
        <span style="color: beige">天</span>
        <el-input-number v-model="graduateForm.reserveTime[1]" controls-position="right" :min="0" :max="23" size="mini"></el-input-number>
        <span style="color: beige">小时</span>
        <br>
        <el-input-number v-model="graduateForm.reserveTime[2]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
        <span style="color: beige">分钟</span>
        <el-input-number v-model="graduateForm.reserveTime[3]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
        <span style="color: beige">秒</span>
      </el-form-item>
      <el-form-item prop="borrowTime2">
        <label class="setLabel">借阅规定时间</label>
        <el-input-number v-model="graduateForm.borrowTime[0]" controls-position="right" :min="0" :max="30" size="mini"></el-input-number>
        <span style="color: beige">天</span>
        <el-input-number v-model="graduateForm.borrowTime[1]" controls-position="right" :min="0" :max="23" size="mini"></el-input-number>
        <span style="color: beige">小时</span>
        <br>
        <el-input-number v-model="graduateForm.borrowTime[2]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
        <span style="color: beige">分钟</span>
        <el-input-number v-model="graduateForm.borrowTime[3]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
        <span style="color: beige">秒</span>
      </el-form-item>
      <el-form-item prop="num2">
        <label class="setLabel">最大借阅副本数量</label>
        <el-input-number v-model="graduateForm.num" :min="1" :max="10"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #f8f8a3;border: none;border-radius: 10px;color: black;font-family: Ink Free" v-on:click="setgraduate(graduateForm)">设置</el-button>
      </el-form-item>
    </el-form>
  </div>

  <div id="undergraduate">
    <el-form :model="undergraduateForm" :rules="rules" class="container" label-position="left"
             v-loading="loading" :ref="undergraduateForm">
      <h3 class="title">本科生身份</h3>
      <el-form-item prop="reserveTime3">
        <label class="setLabel">预约规定时间</label>
        <el-input-number v-model="undergraduateForm.reserveTime[0]" controls-position="right" :min="0" :max="30" size="mini"></el-input-number>
        <span style="color: beige">天</span>
        <el-input-number v-model="undergraduateForm.reserveTime[1]" controls-position="right" :min="0" :max="23" size="mini"></el-input-number>
        <span style="color: beige">小时</span>
        <br>
        <el-input-number v-model="undergraduateForm.reserveTime[2]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
        <span style="color: beige">分钟</span>
        <el-input-number v-model="undergraduateForm.reserveTime[3]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
        <span style="color: beige">秒</span>
      </el-form-item>
      <el-form-item prop="borrowTime3">
        <label class="setLabel">借阅规定时间</label>
        <el-input-number v-model="undergraduateForm.borrowTime[0]" controls-position="right" :min="0" :max="30" size="mini"></el-input-number>
        <span style="color: beige">天</span>
        <el-input-number v-model="undergraduateForm.borrowTime[1]" controls-position="right" :min="0" :max="23" size="mini"></el-input-number>
        <span style="color: beige">小时</span>
        <br>
        <el-input-number v-model="undergraduateForm.borrowTime[2]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
        <span style="color: beige">分钟</span>
        <el-input-number v-model="undergraduateForm.borrowTime[3]" controls-position="right" :min="0" :max="59" size="mini"></el-input-number>
        <span style="color: beige">秒</span>
      </el-form-item>
      <el-form-item prop="num3">
        <label class="setLabel">最大借阅副本数量</label>
        <el-input-number v-model="undergraduateForm.num" :min="1" :max="10"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #f8f8a3;border: none;border-radius: 10px;color: black;font-family: Ink Free" v-on:click="setundergraduate(undergraduateForm)">设置</el-button>
      </el-form-item>
    </el-form>
  </div>
  </div>
</template>

<script>
export default {
  name: 'setTime',
  data () {
    return {
      teacherForm: {
        reserveTime: [],
        borrowTime: [],
        num: 1
      },
      graduateForm: {
        reserveTime: [],
        borrowTime: [],
        num: 1
      },
      undergraduateForm: {
        reserveTime: [],
        borrowTime: [],
        num: 1
      }
    }
  },
  mounted: function () {
    this.getTeacher()
    this.getGraduate()
    this.getUnderGraduate()
  },
  methods: {
    getTeacher () {
      for (var i = 0; i < 4; i++) {
        this.teacherForm.reserveTime[i] = 0
        this.teacherForm.borrowTime[i] = 0
      }
      this.$axios.get('/getTime/teacher').then(resp => {
        if (resp && resp.status === 200) {
          this.teacherForm = resp.data
        }
      })
    },
    getGraduate () {
      for (var i = 0; i < 4; i++) {
        this.graduateForm.reserveTime[i] = 0
        this.graduateForm.borrowTime[i] = 0
      }
      this.$axios.get('/getTime/graduate').then(resp => {
        if (resp && resp.status === 200) {
          this.graduateForm = resp.data
        }
      })
    },
    getUnderGraduate () {
      for (var i = 0; i < 4; i++) {
        this.undergraduateForm.reserveTime[i] = 0
        this.undergraduateForm.borrowTime[i] = 0
      }
      this.$axios.get('/getTime/underGraduate').then(resp => {
        if (resp && resp.status === 200) {
          this.undergraduateForm = resp.data
        }
      })
    },
    setTeacher (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.post('/setTime/teacher', {
            'reserveTime': this.teacherForm.reserveTime,
            'borrowTime': this.teacherForm.borrowTime,
            'num': this.teacherForm.num
          })
            .then(resp => {
              // 根据后端的返回数据修改
              if (resp.status === 200) {
                // 跳转到login && resp.data.hasOwnProperty('id')
                this.$message.success('successful set')
              } else {
                this.$message.warning('set error')
              }
            })
            .catch(error => {
              this.$message.warning(error.message)
            })
        } else {
          this.$message.warning('wrong submit')
        }
      })
    },
    setgraduate (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.post('/setTime/graduate', {
            'reserveTime': this.graduateForm.reserveTime,
            'borrowTime': this.graduateForm.borrowTime,
            'num': this.graduateForm.num
          })
            .then(resp => {
              // 根据后端的返回数据修改
              if (resp.status === 200) {
                // 跳转到login && resp.data.hasOwnProperty('id')
                this.$message.success('successful set')
              } else {
                this.$message.warning('set error')
              }
            })
            .catch(error => {
              this.$message.warning(error.message)
            })
        } else {
          this.$message.warning('wrong submit')
        }
      })
    },
    setundergraduate (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.post('/setTime/underGraduate', {
            'reserveTime': this.undergraduateForm.reserveTime,
            'borrowTime': this.undergraduateForm.borrowTime,
            'num': this.undergraduateForm.num
          })
            .then(resp => {
              // 根据后端的返回数据修改
              if (resp.status === 200) {
                // 跳转到login && resp.data.hasOwnProperty('id')
                this.$message.success('successful set')
              } else {
                this.$message.warning('set error')
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
  .setTime{
    width: 100%;
    display: block;
  }
  .container{
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
  .title{
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

  .setLabel {
    color: beige;
    font-size: 15px;
    display: block;
    text-align: left;
    margin-left: 50px;
  }
</style>
