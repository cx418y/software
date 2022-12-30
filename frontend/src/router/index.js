import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login'
import LibLogin from '@/components/libLogin'
import Register from '@/components/register'
import Home from '@/components/Home'
import modifypwd from '@/components/modifypwd'
import Forgetpwd from '@/components/forgetpwd'
import store from '../store'

Vue.use(Router)
export const router = new Router({
  mode: 'hash',
  base: '/',
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/home',
      component: Home
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'ShowIndex',
          component: () => import('../components/home/ShowIndex')
        },
        {
          path: '/index/detail',
          name: 'CopyDetail',
          component: () => import('../components/Detail/CopyDetail'),
          meta: {
            requireAuth: true
          }
        },

        {
          path: '/search',
          name: 'SearchIndex',
          component: () => import('../components/search/SearchIndex'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/info',
          name: 'Information',
          component: () => import('../components/information/InfoIndex'),
          redirect: '/information',
          children: [
            {
              path: '/information',
              name: 'Information',
              component: () => import('../components/information/content/UserInfo'),
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/information/fine',
              name: 'FineInformation',
              component: () => import('../components/information/content/Fine'),
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/information/record',
              name: 'RecordInformation',
              component: () => import('../components/information/content/UserRecord'),
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/information/read',
              name: 'ReadInformation',
              component: () => import('../components/information/content/UserRead'),
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/information/reserve',
              name: 'ReserveInformation',
              component: () => import('../components/information/content/UserReserve'),
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/information/borrow',
              name: 'BorrowInformation',
              component: () => import('../components/information/content/UserBorrow'),
              meta: {
                requireAuth: true
              }
            },
            {
              path: '/information/credit',
              name: 'CreditInformation',
              component: () => import('../components/information/content/Credit'),
              meta: {
                requireAuth: true
              }
            }
          ]
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/libLogin',
      name: 'LibLogin',
      component: LibLogin
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/forgetpwd',
      name: 'Forgetpwd',
      component: Forgetpwd
    },
    {
      path: '/forgetReset',
      name: 'forgetReset',
      component: () => import('../components/forgetReset')
    },
    {
      path: '/modifypwd',
      name: 'modifypwd',
      component: modifypwd,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import('../components/admin/AdminIndex'),
      redirect: '/admin/index',
      children: [
        {
          path: '/addLibrarian',
          name: 'addLibrarian',
          component: () => import('../components/admin/SuperAdmin/addLibrarian'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/setTime',
          name: 'setTime',
          component: () => import('../components/admin/SuperAdmin/setTime'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/adminList',
          name: 'adminList',
          component: () => import('../components/admin/SuperAdmin/adminList'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/remind',
          name: 'remind',
          component: () => import('../components/admin/SuperAdmin/remind'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/index',
          name: 'AdminHome',
          component: () => import('../components/admin/home/AdminBookShow'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/copy-record',
          name: 'CopyRecord',
          component: () => import('../components/admin/home/CopyRecord'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/upload',
          name: 'UploadIndex',
          component: () => import('../components/admin/Management/upload/UploadIndex'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/record',
          name: 'AdminRecord',
          component: () => import('../components/admin/Management/Record/AdminRecord'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/record/user-record',
          name: 'UserRecord',
          component: () => import('../components/admin/Management/Record/UserRecord'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/record/user-record/fine',
          name: 'UserFineRecord',
          component: () => import('../components/admin/Management/Record/UserFineRecord'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/Adminrefresh',
          name: 'Adminrefresh',
          component: () => import('../components/admin/Management/Refresh/Adminrefresh'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/Userrefresh',
          name: 'Userrefresh',
          component: () => import('../components/admin/Management/Refresh/Userrefresh'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/DetailShowLib',
          name: 'DetailShowLib',
          component: () => import('../components/admin/home/DetailShowLib'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/borrow-and-return',
          name: 'BorrowAndReturn',
          component: () => import('../components/admin/BookOperation/BorrowAndReturn/BorrowAndReturn'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/admin/reserve',
          name: 'GetReserve',
          component: () => import('../components/admin/BookOperation/GetReserve/GetReserve'),
          meta: {
            requireAuth: true
          }
        }
      ]
    }
  ]
})

// 前端登录拦截
router.beforeEach(function (to, from, next) {
  if (to.matched.some(record => record.meta.requireAuth)) {
    if (store.state.username !== null) {
      next()
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath} // 登录成功之后重新跳转到该路由
      })
    }
  } else {
    next()
  }
})
