import Vue from 'vue'
import Router from 'vue-router'
import main from '../view/main/main.vue';

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}



export default new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: resolve => {
        require(['../view/login/login.vue'], resolve);
      }
    },
    {
      path: '/main',
      name: 'main',
      meta: {
        icon: 'ios-settings',
        title: '系统首页',
        hideInMenu: true,
        requireAuth: false //表示当前响应的请求是否需要进行登录拦截验证【true：需要；false：不需要】
      },
      component: resolve => {
        require(['../view/main/main.vue'], resolve);
      }
    },
    {
      path: '/sys',
      name: 'sys',
      component: main,
      meta: {
        icon: 'ios-construct',
        title: '系统管理',
        code:'system-manage',
        requireAuth: false //表示当前响应的请求是否需要进行登录拦截验证【true：需要；false：不需要】
      },
      children:[
        {
          path: 'bookList',
          name: 'bookList',
          meta: {
            icon: 'ios-paper',
            title: '书籍维护',
            code:'system-manage-book',
            requireAuth: false //表示当前响应的请求是否需要进行登录拦截验证【true：需要；false：不需要】
          },
          component: resolve => {
            require(['../view/sys/book/bookList.vue'], resolve);
          }
        },
        {
          path: 'treeList',
          name: 'treeList',
          meta: {
            icon: 'md-git-network',
            title: '菜单管理',
            code:'system-manage-tree',
            requireAuth: true //表示当前响应的请求是否需要进行登录拦截验证【true：需要；false：不需要】
          },
          component: resolve => {
            require(['../view/sys/tree/treeList.vue'], resolve);
          }
        }

      ]
    }
  ]
})
