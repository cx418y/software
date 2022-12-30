<template>
  <el-upload
    class="avatar-uploader"
    ref="upload"
    action="http://106.15.194.40:8080/api/covers"
    with-credentials
    :show-file-list="false"
    :on-success="handleSuccess"
    :on-preview="handlePreview"
    :before-upload="beforeUpload"
    :on-change="handleChange"
    :on-progress="handleProgress"
    :on-error="handleError"
  >
    <img v-if="imageUrl" :src="imageUrl" class="avatar">
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2MB</div>
  </el-upload>
</template>

<script>
export default {
  name: 'UploadImg',
  data () {
    return {
      imageUrl: ''
    }
  },
  methods: {
    handlePreview (file) {
      this.imageUrl = file.url
    },
    handleSuccess (response, file) {
      console.log('success')
      console.log(response)
      console.log(file)
      this.imageUrl = response
      this.$emit('onUpload')
      this.$message.warning('上传成功')
    },
    clear () {
      this.$refs.upload.clearFiles()
      this.imageUrl = ''
    },
    beforeUpload (file) {
      const isvalid = (file.type === 'image/jpeg') || (file.type === 'image/png')
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isvalid) {
        this.$message.error('上传头像图片只能是 jpg 或 png 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isvalid && isLt2M
    },
    handleChange (file) {
      console.log('change')
      console.log(file)
    },
    handleError (error, file) {
      console.log('error')
      console.log(error)
      console.log(file)
    },
    handleProgress (event, file) {
      console.log('progress')
      console.log(event)
      console.log(file)
    }
  }
}
</script>

<style scoped>
  .avatar-uploader{
    margin-top: 20px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader-icon:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
