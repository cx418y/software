<template>
  <div class="article">
  <h1>{{detail.title}}</h1>
  <el-row class="indent">
    <img :src="detail.cover" alt="封面" id="pic">
    <div id="info">
      <span>作者：{{detail.author}}</span>
      <br><br>
      <span>ISBN：{{detail.isbn}}</span>
      <br><br>
      <span>出版日期：{{detail.date}}</span>
      <br><br>
      <span>馆藏地址：{{detail.area.name}}</span>
      <br><br>
      <span>副本数量：{{detail.number}}</span>
      <br><br>
      <span>价格：{{detail.price}}</span>
    </div>
    <div style="margin-left:150px;margin-top: 50px;max-height:60px;padding:15px;border-radius:10px;border: 1px solid #dedfe6">
      <label style="font-size: 15px">评分：</label><br><br>
    <el-rate
      v-model="detail.rate"
      disabled
      style="display: inline-block"
    >
    </el-rate>
      <span style="color: #ff9900;display: inline-block">{{detail.rate}}</span>
    </div>
  </el-row>
    <el-row class="intro">
      <h3>内容简介：</h3>
      {{detail.intro}}
    </el-row>
    <el-row class="comment">
      <h3>书评：</h3>
      <el-divider></el-divider>
      <span style="color:#909399;font-size: 14px" v-if="this.commentList.length === 0">暂无书评</span>
      <div class="comment-box" style="text-align: left" v-else>
        <div class="comment" v-for="item in commentList.slice((currentPage-1)*pagesize, currentPage*pagesize)" :key="item.commentId">
          <div class="info" style="display: flex">
            <img
              class="avatar"
              :src="circleUrl"
              width="36"
              height="36"
            />
            <div class="right">
              <div class="right-top">
                <div class="name">{{ item.userName }}</div>
                <div class="rate">
                  <el-rate
                    v-model="item.rate"
                    disabled
                  >
                  </el-rate>
                </div>
              </div>
              <div class="date">{{ item.time }}</div>
            </div>
          </div>
          <div class="content">{{ item.content }}</div>

          <div class="control">
        <span
          class="comment-reply"
          @click="showCommentInput(item, item, 'major')"
        >
            <i class="el-icon-chat-line-square"></i>
            <span>回复</span>
          </span>
          </div>
          <div class="reply">
            <div class="item" v-for="reply in item.replyList" :key="reply.id">
              <div class="reply-content">
                <span class="from-name">{{reply.userName}}</span>
                <span>回复</span>
                <span class="to-name">{{reply.replyUsername}}</span>
                <span>: </span>
                <span>{{ reply.content }}</span>
              </div>
              <div class="reply-bottom" style="margin-top: 5px">
                <span style="font-size: 12px;color:#909399">{{ reply.time }}</span>
                <span
                  class="reply-text"
                  @click="showCommentInput(item, reply, 'child')"
                >
                <i class="el-icon-chat-line-square"></i>
                <span>回复</span>
              </span>
              </div>
            </div>
            <transition name="fade">
              <div class="input-wrapper" v-if="showItemId === item.commentId" style="padding: 10px">
                <el-tag
                  type="info"
                  effect="dark"
                  v-if="inputLabel !== ''"
                  style="background-color: #409eff;border-color: #409eff;"
                >{{ inputLabel }}</el-tag>
                <el-input
                  class="gray-bg-input"
                  v-model="inputComment"
                  type="textarea"
                  :rows="3"
                  autofocus
                  placeholder="写下你的评论"
                  style="padding: 10px 0"
                >
                </el-input>
                <div class="btn-control" style="text-align: right">
                  <el-button
                    type="primary"
                    round
                    @click="commitComment"
                    size="small"
                    style="width: 80px"
                  >确定</el-button>
                  <el-button
                    type="primary"
                    round
                    @click="cancel"
                    size="small"
                    style="width: 80px;margin-left: 20px"
                  >关闭</el-button>
                </div>
              </div>
            </transition>
          </div>
          <el-divider></el-divider>
        </div>
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pagesize"
          :total="commentList.length">
        </el-pagination>
      </div>
    </el-row>
  </div>
</template>

<script>
import store from '../../store'
export default {
  name: 'DetailShow',
  inject: ['reload'],
  data () {
    return {
      detail: {},
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      commentList: [],
      showItemId: '',
      replyId: '',
      parentId: '',
      inputComment: '',
      inputLabel: '',
      currentPage: 1,
      pagesize: 5
    }
  },
  methods: {
    getCommentList (isbn) {
      var _this = this
      this.$axios.get('/getCommentList/' + isbn).then(resp => {
        if (resp && resp.status === 200) {
          _this.commentList = resp.data
        }
      }).catch(error => {
        console.log(error)
      })
    },
    commitComment () {
      if (this.inputComment === '') {
        this.$message.warning('请输入内容')
      } else {
        let data = {
          'isbn': this.detail.isbn,
          'reply': this.inputComment,
          'userName': store.state.username,
          'replyId': this.replyId,
          'parentId': this.parentId
        }
        this.$axios.post('/commitReply', data).then(resp => {
          if (resp && resp.status === 200) {
            this.$message.success('评论成功！')
            this.reload()
          } else {
            this.$message.error('评论失败')
          }
        })
      }
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    showCommentInput (item, reply, type) {
      if (reply) {
        if (type === 'major') {
          this.replyId = 0
          this.inputLabel = ''
        }
        if (type === 'child') {
          this.inputLabel = '回复' + reply.replyUsername + ' '
          this.replyId = reply.id
        }
        this.parentId = item.commentId
        this.showItemId = item.commentId
      } else {
        this.inputLabel = ''
        this.parentId = ''
        this.replyId = ''
        this.showItemId = ''
      }
      this.inputComment = ''
    },
    cancel () {
      this.showItemId = ''
    }
  }
}
</script>

<style scoped>
.article {
  text-align: left;
  margin-left: 10%;
  margin-right: 10%;
  min-height: 430px;
}
.indent {
  display: flex;
  font-size: 14px;
  line-height: 20px;
}
#pic {
  width: 150px;
  height: 190px;
  display: inline-block;
}
#info {
  display: inline-block;
  margin-left: 50px;
}
.right {
  display: flex;
  flex-direction: column;
  margin-left: 10px;
}
.right-top {
  display: flex;
}
.name {
  font-size: 15px;
}
.rate {
  margin-left: 20px;
}
.date {
  font-size: 12px;
  margin-top: 5px;
  color:#909399;
}
.content {
  padding: 10px 0;
}
.control {
  margin-bottom: 10px;
  font-size: 14px;
  color:#909399;
}
.control :hover {
  color: #409eff;
}
.reply {
  border-left: 2px solid #dcdfe6;
  margin: 10px 0;
  background-color: #f5f7fa;
}
.item {
  margin: 0 10px;
  padding: 10px 0;
  border-bottom: 1px dashed #dedfe6;
}
.reply-content {
  font-size: 14px;
}
.from-name {
  color: #409eff;
  margin-right: 5px;
}
.to-name {
  color: #409eff;
  margin-left: 5px;
}
.reply-text {
  font-size: 14px;
  margin-left: 10px;
  color:#909399
}
.reply-text :hover {
  color: #409eff;
}
</style>
