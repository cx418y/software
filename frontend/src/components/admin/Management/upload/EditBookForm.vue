<template>
  <div class="upload">
      <el-form :model="form" :rules="rules" style="text-align: left" ref="form">
        <el-form-item label="书名" :label-width="'15%'" prop="title">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="作者" :label-width="'15%'" prop="author">
          <el-input v-model="form.author" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="封面" :label-width="'15%'" prop="cover">
          <el-input v-model="form.cover" autocomplete="off" placeholder="图片 URL" disabled></el-input>
          <upload-img @onUpload="uploadImg" ref="imgUpload"></upload-img>
        </el-form-item>
        <el-form-item label="出版日期" :label-width="'15%'" prop="date">
          <el-date-picker
            v-model="form.date"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="ISBN" :label-width="'15%'" prop="isbn">
          <el-input v-model="form.isbn" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="副本添加" :label-width="'15%'" prop="num">
          <div v-for="(item,index) in area" :key="index" style="width: 60%;text-align: right;margin-bottom: 10px">
          {{area[index]}}:
          <el-input-number v-model="form.num[index]" :min="0" :max="10" label="数量" placeholder="0"></el-input-number>
          </div>
        </el-form-item>
        <el-form-item label="馆藏地址" :label-width="'15%'" prop="area.id">
          <el-select v-model="form.area.id" placeholder="请选择馆藏">
            <el-option v-for="(item,index) in area" :key="index+1" :label="item" :value="index+1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格" :label-width="'15%'" prop="price">
          <el-input-number v-model="form.price" :precision="2" :step="0.1" :min="0" :max="100" controls-position="right"></el-input-number>
        </el-form-item>
        <el-form-item label="简介" :label-width="'15%'" prop="intro">
          <el-input
            type="textarea"
            show-word-limit
            style="width: 85%"
            v-model="form.intro"
            autocomplete="off">
          </el-input>
        </el-form-item>
        <el-form-item label="分类" :label-width="'15%'" prop="category.id">
          <el-select v-model="form.category.id" placeholder="请选择分类">
            <el-option label="文学" value="1"></el-option>
            <el-option label="流行" value="2"></el-option>
            <el-option label="文化" value="3"></el-option>
            <el-option label="生活" value="4"></el-option>
            <el-option label="经管" value="5"></el-option>
            <el-option label="科技" value="6"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('form')">重置</el-button>
        <el-button type="primary" @click="onSubmit('form')">确 定</el-button>
      </div>
  </div>
</template>

<script>
import UploadImg from './UploadImg'
export default {
  name: 'EditBookForm',
  components: {UploadImg},
  inject: ['reload'],
  data () {
    const dataValid = (rule, value, callback) => {
      var sum = 0
      for (var i = 0; i < this.form.num.length; i++) {
        sum += this.form.num[i]
      }
      if (sum > 5) {
        return callback(new Error('最多添加五本'))
      }

      return callback()
    }
    return {
      area: [],
      form: {
        title: '',
        author: '',
        date: '',
        isbn: '',
        cover: '',
        intro: '',
        area: {
          id: '',
          name: ''
        },
        num: [],
        category: {
          id: '',
          name: ''
        },
        price: 0
      },
      rules: {
        title: [
          { required: true, message: '请输入书名，不加《》', trigger: 'blur' }
        ],
        author: [
          { required: true, message: '请输入作者', trigger: 'blur' }
        ],
        date: [
          { required: true, message: '请选择日期', trigger: 'blur' }
        ],
        isbn: [
          { required: true, message: '请输入ISBN', trigger: 'blur' },
          {pattern: /^(\d{10}|\d{13})$/, message: '请输入10位或13位数字'}
        ],
        cover: [
          { required: true, message: '请上传图片', trigger: 'blur' }
        ],
        intro: [
          { required: true, message: '请输入简介', trigger: 'blur' }
        ],
        'category.id': [
          { required: true, message: '请选择分类', trigger: 'change' }
        ],
        num: [
          { required: true, trigger: 'blur', validator: dataValid }
        ],
        'area.id': [
          { required: true, message: '请选择馆藏地址', trigger: 'change' }
        ],
        price: [
          {required: true, message: '请输入价格', trigger: 'change' }
        ]
      }
    }
  },
  mounted: function () {
    this.loadArea()
  },
  methods: {
    resetForm (formName) {
      /* for (var i = 0; i < this.form.num.length; i++) {
        this.form.num[i] = 0
      }
      this.$refs[formName].resetFields()
      this.$refs.imgUpload.clear() */
      this.reload()
    },
    loadArea () {
      var _this = this
      this.$axios.get('/getArea').then(resp => {
        if (resp) {
          _this.area = resp.data
          _this.form.num.length = _this.area.length
          for (var i = 0; i < _this.form.num.length; i++) {
            _this.form.num[i] = 0
          }
        }
      })
    },
    onSubmit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post('/addBook', {
              'cover': this.form.cover,
              'title': this.form.title,
              'author': this.form.author,
              'date': this.form.date,
              'isbn': this.form.isbn,
              'intro': this.form.intro,
              'aid': this.form.area.id,
              'num': this.form.num,
              'cid': this.form.category.id,
              'price': this.form.price
            }).then(resp => {
              if (resp && resp.status === 200) {
                this.$emit('onSubmit')
                this.$message.success('图书上传成功')
              }
            }).catch(error => {
              this.$message.warning(error)
            })
          this.resetForm('form')
        } else {
          this.$message.warning('请填写完整')
          return false
        }
      })
    },
    uploadImg () {
      this.form.cover = this.$refs.imgUpload.imageUrl
    }
  }
}
</script>

<style scoped>
.upload {
  display: block;
  background-color: white;
  width: 70%;
  margin-left: 15%;
  margin-top: 40px;
  padding-top: 50px;
  padding-bottom: 50px;
}
.el-input{
  width: 85%;
  position: inherit;
}
  .el-form-item{
    text-align: left;
    padding-left: 15%;
    padding-right: 15%;
  }
  .el-form-item__content{
    display: inline-block;
    width: 85%;
  }
</style>
