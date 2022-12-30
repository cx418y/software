import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: sessionStorage.getItem('token') || null,
    username: sessionStorage.getItem('username') || null,
    aid: sessionStorage.getItem('aid') || null,
    email: sessionStorage.getItem('email') || null,
    authority: sessionStorage.getItem('authority') || null,
    mostBorrow: sessionStorage.getItem('mostBorrow') || null,
    borrowOvertime: sessionStorage.getItem('borrowOvertime') || null,
    reserveOvertime: sessionStorage.getItem('reserveOvertime') || null,
    credit: sessionStorage.getItem('credit') || null
  },
  mutations: {
    login (state, data) {
      sessionStorage.setItem('token', data.token)
      sessionStorage.setItem('username', data.username)
      sessionStorage.setItem('aid', data.aid)
      sessionStorage.setItem('email', data.email)
      sessionStorage.setItem('authority', data.authority.authority)
      sessionStorage.setItem('mostBorrow', data.authority.mostBorrow)
      sessionStorage.setItem('borrowOvertime', data.authority.borrowOvertime)
      sessionStorage.setItem('reserveOvertime', data.authority.reserveOvertime)
      sessionStorage.setItem('credit', data.credit)
      state.username = data.username
      state.aid = data.aid
      state.token = data.token
      state.email = data.email
      state.authority = data.authority.authority
      state.mostBorrow = data.authority.mostBorrow
      state.borrowOvertime = data.authority.borrowOvertime
      state.reserveOvertime = data.authority.reserveOvertime
      state.credit = data.credit
    },
    logout (state) {
      // 移除token
      sessionStorage.removeItem('token')
      sessionStorage.removeItem('username')
      sessionStorage.removeItem('aid')
      sessionStorage.removeItem('email')
      sessionStorage.removeItem('authority')
      sessionStorage.removeItem('mostBorrow')
      sessionStorage.removeItem('borrowOvertime')
      sessionStorage.removeItem('reserveOvertime')
      sessionStorage.removeItem('credit')
      state.username = null
      state.aid = null
      state.token = null
      state.email = null
      state.authority = null
      state.mostBorrow = null
      state.borrowOvertime = null
      state.reserveOvertime = null
      state.credit = null
    }
  },
  actions: {
  }
})
