import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
// 导入全局样式表
import './assets/css/global.css'
// 导入axios
import axios from 'axios'
// 导入qs
import qs from 'qs'
// 导入一下mock
require('./mock/api')
import JsonExcel from 'vue-json-excel'
Vue.component('downloadExcel', JsonExcel)

// 配置请求的根路径
// baseURL里面是网站的根目录
axios.defaults.baseURL = 'http://localhost:8080/'
axios.defaults.withCredentials = true
axios.interceptors.request.use(config => {
  // console.log(config)
  config.headers.token = window.sessionStorage.getItem('token')
  // 最后必须return config
  return config
})
axios.interceptors.response.use(result => {
  const data = result.data
  if (data.status === '404') {
    window.sessionStorage.clear()
    router.push('/login')
    Vue.prototype.$message.error(data.msg)
  }
  return result
})
Vue.prototype.$http = axios
// 配置qs
Vue.prototype.$qs = qs

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
