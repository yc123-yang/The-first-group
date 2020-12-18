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
axios.defaults.baseURL = 'http://127.0.0.1:8080/'
axios.defaults.withCredentials = true
axios.interceptors.request.use(config => {
  // console.log(config)
  config.headers.user_token = window.sessionStorage.getItem('token')
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

var excelFields = {
  project: {
    '项目名称': 'project_name',
    '项目编号': 'project_id',
    '归属单位': 'department_name',
    '学科门类': 'sc_name',
    '一级学科': 'subject_name',
    '项目性质': 'nature_name',
    '项目级别': 'level_name',
    '项目状态': 'status_name',
    '来源单位': 'sd_name',
    '批准文号': 'approval_number',
    '立项日期': 'start_time',
    '计划完成日期': 'plan_time',
    '结项日期': 'complete_time',
    '合同经费': 'outlay',
    '结题形式': 'ct_name'
  },
  paper: {
    '论文题目': 'paper_name',
    '论文作者': 'authorName',
    '发表期刊': 'periodical_name',
    '发表日期': 'publish_time',
    '论文类型': 'pt_name',
    '收录号': 'include_number',
    '学科门类': 'sc_name',
    '一级学科': 'subject_name',
    '归属单位': 'aod_name',
    '来源单位': 'sd_name',
    '备注': 'remark'
  },
  book: {
    '著作题目': 'book_name',
    '著作作者': 'authorName',
    '出版社': 'press',
    '出版社级别': 'pl_name',
    '著作类型': 'bt_name',
    '出版地': 'pp_name',
    '发表日期': 'publish_time',
    'ISBN号': 'isbn',
    '总字数': 'word_number',
    '是否翻译': 'trans',
    '翻译语种': 'language_name',
    '学科门类': 'sc_name',
    '一级学科': 'subject_name',
    '归属单位': 'aod_name',
    '项目来源': 'sd_name',
    '研究类别': 'rt_name'
  },
  award: {
    '成果名字': 'achievement_name',
    '著作作者': 'authorName',
    '获奖名字': 'award_name',
    '获奖日期': 'award_time',
    '发证机关': 'issuing_authority',
    '奖励批准号': 'approval_number',
    '单位排名': 'dr_id',
    '获奖级别': 'ar_name',
    '获奖等级': 'al_name',
    '学科门类': 'sc_name',
    '一级学科': 'subject_name',
    '归属单位': 'aod_name',
    '项目来源': 'sd_name',
    '成果形式': 'at_name'
  },
  patent: {
    '专利名字': 'patent_name',
    '负责人': 'authorName',
    '专利类型': 'pt_name',
    '专利范围': 'pr_name',
    '专利状态': 'ps_name',
    '归属单位': 'aod_name',
    '申请编号': 'application_number',
    '申请日期': 'application_time',
    '公开编号': 'public_number',
    '公开日期': 'public_time',
    '授权编号': 'authorization_number',
    '授权日期': 'authorization_time',
  },
  assess: {
    '考核批次': 'check_time',
    '姓名': 'user_name',
    '单位': 'department_name',
    '科研项目': 'project_count',
    '著作成果': 'book_count',
    '论文成果': 'paper_count',
    '成果获奖': 'award_count',
    '专利成果': 'patent_count',
    '经费合计': 'outlay_sum',
    '合计': 'total_Score'
  },
  operationLog: {
    '日志时间': 'ol_time',
    '日志级别': 'ol_level',
    '用户编号': 'ol_uid',
    '用户姓名': 'ol_uname',
    '用户ip地址': 'ol_ip',
    '日志位置': 'ol_location',
    '日志信息': 'ol_message'
  },
  systemLog: {
    '日志时间': 'sl_time',
    '日志级别': 'sl_level',
    '日志位置': 'sl_location',
    '日志信息': 'sl_message'
  }
}

Vue.prototype.$excelFields = excelFields


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')



