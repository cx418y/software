<template>
  <div class="comment-box" style="text-align: center">
    <div class="comment" v-for="item in commentList.slice((currentPage-1)*pagesize, currentPage*pagesize)" :key="item.id">
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
        <div class="item" v-for="reply in item.children" :key="reply.id">
          <div class="reply-content">
              <span class="from-name">{{reply.userName}}</span>
            <span>回复</span>
            <span class="to-name">{{reply.replyUserName}}</span>
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
          <div class="input-wrapper" v-if="showItemId === item.id" style="padding: 10px">
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
</template>

<script>
export default {
  name: 'Comment',
  data () {
    return {
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
    getCommentList () {
      this.$emit('getCommentList')
    },
    commitComment () {
      this.$emit('commitComment')
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    showCommentInput (item, reply, type) {
      if (reply) {
        if (type === 'major') {
          this.replyId = item.id
          this.inputLabel = ''
        }
        if (type === 'child') {
          this.inputLabel = '回复' + reply.userName + ' '
          this.replyId = reply.id
        }
        this.parentId = item.id
        this.showItemId = item.id
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
