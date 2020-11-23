import Mock from 'mockjs'
var flag = true

// 菜单列表+权限列表
const menulist = [{
  id: 101,
  name: '科研项目',
  path: null,
  children: [{
    id: 211,
    name: '科研项目',
    path: 'projects',
    children: [
      { id: 3111, name: '查看科研项目', path: 'projects/query' },
      { id: 3112, name: '管理科研项目', path: 'projects/manage' }
    ]
  }]
}, {
  id: 102,
  name: '科研成果',
  path: null,
  children: [{
    id: 221,
    name: '论文成果',
    path: 'papers',
    children: [
      { id: 3211, name: '查看论文成果', path: 'papers/query' },
      { id: 3212, name: '管理论文成果', path: 'papers/manage' }
    ]
  }, {
    id: 222,
    name: '著作成果',
    path: 'books',
    children: [
      { id: 3221, name: '查看著作成果', path: 'books/query' },
      { id: 3222, name: '管理著作成果', path: 'books/manage' }
    ]
  }, {
    id: 223,
    name: '科研成果获奖',
    path: 'awards',
    children: [
      { id: 3231, name: '查看成果获奖', path: 'awards/query' },
      { id: 3232, name: '管理成果获奖', path: 'awards/manage' }
    ]
  }, {
    id: 224,
    name: '专利与产品',
    path: 'products',
    children: [
      { id: 3241, name: '查看专利产品', path: 'products/query' },
      { id: 3242, name: '管理专利产品', path: 'products/manage' }
    ]
  }]
}, {
  id: 103,
  name: '项目经费',
  path: null,
  children: [{
    id: 231,
    name: '管理项目经费',
    path: 'funding',
    children: [{ id: 3311, name: '管理项目经费', path: null }]
  }]
}, {
  id: 104,
  name: '科研考核',
  path: null,
  children: [{
    id: 241,
    name: '考核结果',
    path: 'assess',
    children: [{ id: 3411, name: '查看考核结果', path: null }]
  }]
}, {
  id: 105,
  name: '审核申请',
  path: null,
  children: [{
    id: 251,
    name: '审核项目申请',
    path: 'checkproject',
    children: [{ id: 3511, name: '审核项目申请', path: null }]
  }, {
    id: 252,
    name: '审核论文成果申请',
    path: 'checkpapers',
    children: [{ id: 3521, name: '审核论文成果申请', path: null }]
  }, {
    id: 253,
    name: '审核著作成果申请',
    path: 'checkbooks',
    children: [{ id: 3531, name: '审核著作成果申请', path: null }]
  }, {
    id: 254,
    name: '审核科研获奖申请',
    path: 'checkawards',
    children: [{ id: 3541, name: '审核科研获奖申请', path: null }]
  }, {
    id: 255,
    name: '审核专利产品申请',
    path: 'checkproducts',
    children: [{ id: 3551, name: '审核主力产品申请', path: null }]
  }]
}, {
  id: 106,
  name: '用户管理',
  path: null,
  children: [{
    id: 261,
    name: '编辑用户信息',
    path: 'userEdit',
    children: [{ id: 3611, name: '编辑用户信息', path: null }]
  }]
}, {
  id: 107,
  name: '权限管理',
  path: null,
  children: [{
    id: 271,
    name: '管理角色',
    path: 'roleEdit',
    children: [{ id: 3711, name: '管理角色', path: null }]
  }, {
    id: 272,
    name: '分配角色',
    path: 'userRole',
    children: [{ id: 3712, name: '分配角色', path: null }]
  }]
}, {
  id: 108,
  name: '数据管理',
  path: null,
  children: [{
    id: 281,
    name: '管理字典数据',
    path: 'data',
    children: [{ id: 3811, name: '管理字典数据', path: null }]
  }]
}]
// 部分权限（测试用）
const authlist = [{
  id: 101,
  name: '科研项目',
  path: null,
  children: [{
    id: 211,
    name: '科研项目',
    path: 'projects',
    children: [
      { id: 3111, name: '查看科研项目', path: 'projects/query' }
    ]
  }]
}, {
  id: 102,
  name: '科研成果',
  path: null,
  children: [{
    id: 221,
    name: '论文成果',
    path: 'papers',
    children: [
      { id: 3211, name: '查看论文成果', path: 'papers/query' }
    ]
  }, {
    id: 222,
    name: '著作成果',
    path: 'books',
    children: [
      { id: 3221, name: '查看著作成果', path: 'books/query' }
    ]
  }, {
    id: 223,
    name: '科研成果获奖',
    path: 'awards',
    children: [
      { id: 3231, name: '查看成果获奖', path: 'awards/query' }
    ]
  }, {
    id: 224,
    name: '专利与产品',
    path: 'products',
    children: [
      { id: 3241, name: '查看专利产品', path: 'products/query' }
    ]
  }]
}]

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

  // 获取菜单数据接口
  Mock.mock('http://localhost:8080/getmenulist', 'post', {
    status: '0',
    msg: '获取菜单数据成功',
    data: menulist
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
  

  // 获取角色列表
  Mock.mock('http://localhost:8080/getrolelist', 'post', {
    status: '0',
    msg: '获取角色列表成功',
    'data|5': [{
      'role_id|+1': 1,
      role_name: '@cname',
      role_discribe: '@string(20)'
    }]
  })
  // 根绝角色id获取树状权限
  Mock.mock('http://localhost:8080/getAuthlistById', 'post', {
    status: '0',
    msg: '获取权限列表成功',
    data: authlist
  })
  // 根据角色id更改权限
  Mock.mock('http://localhost:8080/role/editAuthById', 'post', {
    status: '0',
    msg: '修改角色权限成功',
    data: null
  })
}
  
