import Vue from 'vue'
import VueRouter from 'vue-router'
import NotLogin from '../components/NotLogin.vue'
import Home from '../components/Home.vue'
import axios from 'axios'
import Login from '../components/logins/Login.vue'
import Register from '../components/logins/Register.vue'
import Welcome from '../components/Welcome.vue'
import Papers from '../components/achievement/papers/Papers.vue'
import Books from '../components/achievement/books/Books.vue'
import Projects from '../components/projects/Projects.vue'
import ProjectsQuery from '../components/projects/ProjectsQuery.vue'
import ProjectsManage from '../components/projects/ProjectsManage.vue'
import RoleEdit from '../components/Authorization/RoleEdit.vue'
import AssignRole from '../components/Authorization/AssignRole.vue'
import PapersQuery from '../components/achievement/papers/PapersQuery.vue'
import PapersManage from '../components/achievement/papers/PapersManage.vue'
import BooksQuery from '../components/achievement/books/BooksQuery.vue'
import BooksManage from '../components/achievement/books/BooksManage.vue'
import Awards from '../components/achievement/awards/Awards.vue'
import AwardsQuery from '../components/achievement/awards/AwardsQuery.vue'
import AwardsManage from '../components/achievement/awards/AwardsManage.vue'
import Patents from '../components/achievement/patents/Patents.vue'
import PatentsManage from '../components/achievement/patents/PatentsManage.vue'
import PatentsQuery from '../components/achievement/patents/PatentsQuery.vue'

Vue.use(VueRouter)
axios.defaults.baseURL = 'http://localhost:8080/'

const routes = [
  {
    path: '/notlogin',
    component: NotLogin,
    redirect: '/login',
    children: [
      { path: '/login', component: Login },
      { path: '/register', component: Register }
    ]
  },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      {
        path: '/papers',
        component: Papers,
        redirect: '/papers/query',
        children: [
          { path: '/papers/query', component: PapersQuery },
          { path: '/papers/manage', component: PapersManage }
        ]
      }, {
        path: '/books',
        component: Books,
        redirect: '/books/query',
        children: [
          { path: '/books/query', component: BooksQuery },
          { path: '/books/manage', component: BooksManage }
        ]
      }, {
        path: '/projects',
        component: Projects,
        redirect: '/projects/query',
        children: [
          { path: '/projects/query', component: ProjectsQuery },
          { path: '/projects/manage', component: ProjectsManage }
        ]
      }, {
        path: '/awards',
        component: Awards,
        redirect: '/awards/query',
        children: [
          { path: '/awards/query', component: AwardsQuery },
          { path: '/awards/manage', component: AwardsManage }
        ]
      }, {
        path: '/patents',
        component: Patents,
        redirect: '/patents/query',
        children: [
          { path: '/patents/query', component: PatentsQuery },
          { path: '/patents/manage', component: PatentsManage }
        ]
      },
      { path: '/roleEdit', component: RoleEdit },
      { path: '/assignRole', component: AssignRole }
    ]
  }
]

const router = new VueRouter({
  routes
})

// 路由导航守卫
router.beforeEach(async (to, from, next) => {
  // 如果目标地址是登录页面，放行
  if (to.path === '/notlogin') return next()
  if (to.path === '/login') return next()
  if (to.path === '/register') return next()
  // 否则验证当前浏览器存储的token
  const tokenStr = window.sessionStorage.getItem('token')
  // 如果当前没有token，直接进入登录页面
  if (!tokenStr) return next('/notlogin')
  // 如果当前有token但path是'/'，进入主页
  if (to.path === '/') return next('/home')
  // 否则直接放行
  var cnt = 0
  for(var i = 0; i < to.path.length; i++)
    if(to.path[i] === '/')
      cnt += 1
  if(cnt === 1) window.sessionStorage.setItem('activeNav', to.path)
  else window.sessionStorage.setItem('activeTab', to.path)
  next()
})

export default router
