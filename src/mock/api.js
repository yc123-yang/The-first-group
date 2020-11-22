import Mock from 'mockjs'
var flag = true

// 项目列表
const projectList = Mock.mock({
  'list|5': [{
      project_name: '@cname',
      'project_id|+1': 1, 
      'department_id|0-4': 1,
      'sc_id|0-4': 1,
      'subject_id|0-4': 1,
      'nature_id|0-4': 1,
      'level_id|0-4': 1,
      'status_id|0-1': 1,
      'sd_id|0-4': 1,
      'approval_number|3': /[a-z][A-Z][0-9]/,
      start_time: '@date',
      plan_time: '@date',
      complete_time: '@date',
      'outlay|1-10000': 1,
      'ct_id|0-4': 1
  }]
})

// 归属单位列表
const departmentList = Mock.mock({
  'list|5': [{
    'department_id|+1': 0,
    'department_name|+1': ['单位1', '单位2', '单位3', '单位4', '单位5']
  }]
})
// 学科门类列表
const scList = Mock.mock({
  'list|5': [{
    'sc_id|+1': 0,
    'sc_name|+1': ['学科门类1', '学科门类2', '学科门类3', '学科门类4', '学科门类5']
  }]
})
// 一级学科列表
const subjectList = Mock.mock({
  'list|5': [{
    'subject_id|+1': 0,
    'subject_name|+1': ['一级学科1', '一级学科2', '一级学科3', '一级学科4', '一级学科5']
  }]
})
// 项目性质列表
const natureList = Mock.mock({
  'list|5': [{
    'nature_id|+1': 0,
    'nature_name|+1': ['项目性质1', '项目性质2', '项目性质3', '项目性质4', '项目性质5']
  }]
})
// 项目级别列表
const levelList = Mock.mock({
  'list|5': [{
    'level_id|+1': 0,
    'level_name|+1': ['级别1', '级别2', '级别3', '级别4', '级别5']
  }]
})
// 项目状态列表
const statusList = Mock.mock({
  'list|5': [{
    'status_id|+1': 0,
    'status_name|+1': ['状态1', '状态2', '状态3', '状态4', '状态5']
  }]
})
// 结题形式列表
const ctList = Mock.mock({
  'list|5': [{
    'ct_id|+1': 0,
    'ct_name|+1': ['结题形式1', '结题形式2', '结题形式3', '结题形式4', '结题形式5']
  }]
})


if( flag ){
  // 登录接口
  Mock.mock('http://localhost:8080/user/login', 'post', {
    status: '0',
    msg: '登录成功',
    data: [
      {
        userData: { token:'token' }
      }
    ]
  })

  // 查询所有项目接口
  Mock.mock('http://localhost:8080/project/selectAllProjectByCondition', 'post', {
    status: '0',
    msg: '查询项目信息成功',
    data: [
      { total: projectList.list.length },
      projectList.list
    ]
  })

  // 查询自己项目接口
  Mock.mock('http://localhost:8080/project/selectProjectByCondition', 'post', {
    status: '0',
    msg: '查询项目信息成功',
    data: [
      { total: projectList.list.length },
      projectList.list
    ]
  })

  // 获取所有单位接口
  Mock.mock('http://localhost:8080/department/findAlldepartment', 'post', {
    status: '0',
    msg: '获取单位信息成功',
    data: departmentList.list
  })

  // 获取所有学科门类
  Mock.mock('http://localhost:8080/sc/findAllsc', 'post', {
    status: '0',
    msg: '获取学科门类信息成功',
    data: scList.list
  })
  // 获取所有一级学科
  Mock.mock('http://localhost:8080/subject/findAllsubject', 'post', {
    status: '0',
    msg: '获取一级学科信息成功',
    data: subjectList.list
  })
  // 获取所有项目性质
  Mock.mock('http://localhost:8080/nature/findAllnature', 'post', {
    status: '0',
    msg: '获取项目性质信息成功',
    data: natureList.list
  })
  // 获取所有项目级别
  Mock.mock('http://localhost:8080/level/findAlllevel', 'post', {
    status: '0',
    msg: '获取项目级别信息成功',
    data: levelList.list
  })
  // 获取所有项目状态
  Mock.mock('http://localhost:8080/status/findAllstatus', 'post', {
    status: '0',
    msg: '获取项目状态信息成功',
    data: statusList.list
  })
  // 获取所有结题形式
  Mock.mock('http://localhost:8080/ct/findAllct', 'post', {
    status: '0',
    msg: '获取结题形式信息成功',
    data: ctList.list
  })
}
  
