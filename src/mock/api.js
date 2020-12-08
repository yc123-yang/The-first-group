import Mock from 'mockjs'
var flag = false

// 菜单列表+权限列表
const menulist = [{
  id: 1,
  title: '科研项目',
  resourcePath: 0,
  childrenPermissions: [{
    id: 6,
    title: '科研项目',
    resourcePath: '/projects',
    childrenPermissions: [
        { id: 18, title: '查看科研项目', resourcePath: '/projects/query' },
        { id: 19, title: '管理科研项目', resourcePath: '/projects/manage' }
      ]
    }]
  }, {
    id: 2,
    title: '科研成果',
    resourcePath: 0,
    childrenPermissions: [{
      id: 7,
      title: '论文成果',
      resourcePath: '/papers',
      childrenPermissions: [
        { id: 20, title: '查看论文成果', resourcePath: '/papers/query' },
        { id: 21, title: '管理论文成果', resourcePath: '/papers/manage' }
      ]
    }, {
    id: 8,
    title: '著作成果',
    resourcePath: '/books',
    childrenPermissions: [
      { id: 22, title: '查看著作成果', resourcePath: '/books/query' },
      { id: 23, title: '管理著作成果', resourcePath: '/books/manage' }
    ]
  }, {
    id: 9,
    title: '科研成果获奖',
    resourcePath: '/awards',
    childrenPermissions: [
      { id: 24, title: '查看成果获奖', resourcePath: '/awards/query' },
      { id: 25, title: '管理成果获奖', resourcePath: '/awards/manage' }
    ]
  }, {
    id: 10,
    title: '专利与产品',
    resourcePath: '/patents',
    childrenPermissions: [
      { id: 26, title: '查看专利产品', resourcePath: '/patents/query' },
      { id: 27, title: '管理专利产品', resourcePath: '/patents/manage' }
    ]
  }]
}, {
  id: 3,
  title: '项目经费',
  resourcePath: 0,
  childrenPermissions: [{
    id: 11,
    title: '管理项目经费',
    resourcePath: '/funding',
    childrenPermissions: [{ id: 28, title: '管理项目经费', resourcePath: 0 }]
  }]
}, {
  id: 4,
  title: '科研考核',
  resourcePath: 0,
  childrenPermissions: [{
    id: 12,
    title: '考核结果',
    resourcePath: '/assess',
    childrenPermissions: [{ id: 29, title: '查看考核结果', resourcePath: 0 }]
  }]
}, {
  id: 5,
  title: '审核申请',
  resourcePath: 0,
  childrenPermissions: [{
    id: 13,
    title: '审核项目申请',
    resourcePath: '/checkproject',
    childrenPermissions: [{ id: 30, title: '审核项目申请', resourcePath: 0 }]
  }, {
    id: 14,
    title: '审核论文成果申请',
    resourcePath: '/checkpapers',
    childrenPermissions: [{ id: 31, title: '审核论文成果申请', resourcePath: 0 }]
  }, {
    id: 15,
    title: '审核著作成果申请',
    resourcePath: 'checkbooks',
    childrenPermissions: [{ id: 32, title: '审核著作成果申请', resourcePath: 0 }]
  }, {
    id: 16,
    title: '审核科研获奖申请',
    resourcePath: '/checkawards',
    childrenPermissions: [{ id: 33, title: '审核科研获奖申请', resourcePath: 0 }]
  }, {
    id: 17,
    title: '审核专利产品申请',
    resourcePath: '/checkproducts',
    childrenPermissions: [{ id: 34, title: '审核专利产品申请', resourcePath: 0 }]
  }]
}, {
  id: 35,
  title: '用户管理',
  resourcePath: 0,
  childrenPermissions: [{
    id: 38,
    title: '编辑用户信息',
    resourcePath: '/userEdit',
    childrenPermissions: [{ id: 42, title: '编辑用户信息', resourcePath: 0 }]
  }]
}, {
  id: 36,
  title: '权限管理',
  resourcePath: 0,
  childrenPermissions: [{
    id: 39,
    title: '管理角色',
    resourcePath: '/roleEdit',
    childrenPermissions: [{ id: 43, title: '管理角色', resourcePath: 0 }]
  }, {
    id: 40,
    title: '分配角色',
    resourcePath: '/assignRole',
    childrenPermissions: [{ id: 44, title: '分配角色', resourcePath: 0 }]
  }]
}, {
  id: 37,
  title: '数据管理',
  resourcePath: 0,
  childrenPermissions: [{
    id: 41,
    title: '管理字典数据',
    resourcePath: '/data',
    childrenPermissions: [{ id: 45, title: '管理字典数据', resourcePath: 0 }]
  }]
}, {
  id: 46,
  title: '安全日志',
  resourcePath: 0,
  childrenPermissions: [{
    id: 47,
    title: '查看安全日志',
    resourcePath: '/logs',
    childrenPermissions: [{ id: 48, title: '查看安全日志', resourcePath: 0 }]
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

// 论文列表
const papersList = Mock.mock({
  'list|5': [{
    paper_name: '@cname',
    'paper_id|+1': 1,
    'pt_id|0-4': 1,
    'periodical_id|0-4': 1,
    publish_time: '@date',
    'include_number|3': /[a-z][A-Z][0-9]/,
    'sc_id|0-4': 1,
    'subject_id|0-4': 1,
    'aod_id|0-4': 1,
    'sd_id|0-4': 1,
    remark: '备注'
  }]
})
// 项目列表
const projectList = Mock.mock({
  'list|10': [{
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
    'ct_id|0-4': 1,
    'leader_id|0-4': 1
  }]
})
// 著作列表
const booksList = Mock.mock({
  'list|5': [{
    'book_id|+1': 1,
    // leader_id
    book_name: '@cname',
    press: '出版社',
    'pl_id|0-4': 1,
    'bt_id|0-4': 1,
    'pp_id|0-4': 1,
    'isbn|3': /[a-z][A-Z][0-9]/,
    'word_number|3': /[1-9][0-9][0-9]/,
    trans: '是',
    'language_id|0-4': 1,
    'sc_id|0-4': 1,
    'subject_id|0-4': 1,
    publish_time: '@date',
    'aod_id|0-4': 1,
    'sd_id|0-4': 1,
    'rt_id|0-4': 1

  }]
})
// 成果获奖列表
const awardsList = Mock.mock({
  'list|5': [{
    'award_id|+1': 1,
    leader_id: '',
    achievement_name: '成果名字',
    award_name: '获奖名字',
    award_time: '@date',
    'issuing_authority|3': /[a-z][A-Z][0-9]/,
    'approval_number|3': /[a-z][A-Z][0-9]/,
    'aod_id|0-4': 1,
    'dr_id|0-10': 1,
    'ar_id|0-4': 1,
    'al_id|0-4': 1,
    'sc_id|0-4': 1,
    'subject_id|0-4': 1,
    'sd_id|0-4': 1,
    'at_id|0-4': 1

  }]
})

// 专利列表
const patentsList = Mock.mock({
  'list|5': [{
    'patent_id|+1': 1,
    leader_id: '',
    patent_name: '专利名字',
    'ptz_id|0-4': 1,
    'pr_id|0-4': 1,
    'ps_id|0-4': 1,
    'aod_id|0-4': 1,
    'application_number|3': /[a-z][A-Z][0-9]/,
    application_time: '@date',
    'public_number|3': /[a-z][A-Z][0-9]/,
    public_time: '@date',
    'authorization_number|3': /[a-z][A-Z][0-9]/,
    authorization_time: '@date',

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
// 角色列表
const roleList = Mock.mock({
  'list|5': [{
    'role_id|+1': 1,
    'role_name|+1': ['角色1', '角色2', '角色3', '角色4', '角色5'],
    role_discribe: '@string(20)'
  }]
})
// 论文类型列表
const ptList = Mock.mock({
  'list|5': [{
    'pt_id|+1': 0,
    'pt_name|+1': ['论文类型1', '论文类型2', '论文类型3', '论文类型4', '论文类型5']
  }]
})
// 期刊列表
const periodicalList = Mock.mock({
  'list|5': [{
    'periodical_id|+1': 0,
    'periodical_name|+1': ['期刊1', '期刊2', '期刊3', '期刊4', '期刊5']
  }]
})
// 出版社列表
const pressList = Mock.mock({
  'list|5': [{
    'press_id|+1': 0,
    'press_name|+1': ['出版社1', '出版社2', '出版社3', '出版社4', '出版社5']
  }]
})
// 出版社等级
const plList = Mock.mock({
  'list|5': [{
    'pl_id|+1': 0,
    'pl_name|+1': ['出版社等级1', '出版社等级2', '出版社等级3', '出版社等级4', '出版社等级5']
  }]
})
// 著作类型列表
const btList = Mock.mock({
  'list|5': [{
    'bt_id|+1': 0,
    'bt_name|+1': ['著作类型1', '著作类型2', '著作类型3', '著作类型4', '著作类型5']
  }]
})
// 出版地列表
const ppList = Mock.mock({
  'list|5': [{
    'pp_id|+1': 0,
    'pp_name|+1': ['出版地1', '出版地2', '出版地3', '出版地4', '出版地5']
  }]
})
// 语种列表 language
const languageList = Mock.mock({
  'list|5': [{
    'language_id|+1': 0,
    'language_name|+1': ['汉语', '英语', '俄语', '日语', '意大利语']
  }]
})
// 研究类型列表
const rtList = Mock.mock({
  'list|5': [{
    'rt_id|+1': 0,
    'rt_name|+1': ['研究类型1', '研究类型2', '研究类型3', '研究类型4', '研究类型5']
  }]
})
// 获奖级别列表
const arList = Mock.mock({
  'list|5': [{
    'ar_id|+1': 0,
    'ar_name|+1': ['获奖级别1', '获奖级别2', '获奖级别3', '获奖级别4', '获奖级别5']
  }]
})
// 获奖等级列表
const alList = Mock.mock({
  'list|5': [{
    'al_id|+1': 0,
    'al_name|+1': ['获奖等级1', '获奖等级2', '获奖等级3', '获奖等级4', '获奖等级5']
  }]
})
// 成果形式列表
const atList = Mock.mock({
  'list|5': [{
    'at_id|+1': 0,
    'at_name|+1': ['成果形式1', '成果形式2', '成果形式3', '成果形式4', '成果形式5']
  }]
})
// 专利类型列表
const ptzList = Mock.mock({
  'list|5': [{
    'ptz_id|+1': 0,
    'ptz_name|+1': ['专利类型1', '专利类型2', '专利类型3', '专利类型4', '专利类型5']
  }]
})
// 专利范围列表
const prList = Mock.mock({
  'list|5': [{
    'pr_id|+1': 0,
    'pr_name|+1': ['专利范围1', '专利范围2', '专利范围3', '专利范围4', '专利范围5']
  }]
})
// 专利状态列表
const psList = Mock.mock({
  'list|5': [{
    'ps_id|+1': 0,
    'ps_name|+1': ['专利状态1', '专利状态2', '专利状态3', '专利状态4', '专利状态5']
  }]
})


if( flag ){
  // 登录接口
  Mock.mock('http://localhost:8080/user/login', 'post', {
    status: '200',
    msg: '登录成功',
    data: [
      {
        userData: { token:'token' }
      }
    ]
  })

  // 获取菜单数据接口
  Mock.mock('http://localhost:8080/user/getUserMenu', 'post', {
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

  // 查询所有论文成果接口
  Mock.mock('http://localhost:8080/paper/selectAllPaperByCondition', 'post', {
    status: '0',
    msg: '查询论文信息成功',
    data: [
      { total: papersList.list.length },
      papersList.list
    ]
  })
  // 查询所有著作成果接口
  Mock.mock('http://localhost:8080/book/selectAllBookByCondition', 'post', {
    status: '0',
    msg: '查询著作信息成功',
    data: [
      { total: booksList.list.length },
      booksList.list
    ]
  })
  // 查询所有成果获奖接口
  Mock.mock('http://localhost:8080/award/selectAllAwardByCondition', 'post', {
    status: '0',
    msg: '查询成果获奖信息成功',
    data: [
      { total: awardsList.list.length },
      awardsList.list
    ]
  })
  // 查询所有专利接口
  Mock.mock('http://localhost:8080/patent/selectAllPatentByCondition', 'post', {
    status: '0',
    msg: '查询专利信息成功',
    data: [
      { total: patentsList.list.length },
      patentsList.list
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
  // 查询自己论文接口
  Mock.mock('http://localhost:8080/paper/selectPaperByCondition', 'post', {
    status: '0',
    msg: '查询论文信息成功',
    data: [
      { total: papersList.list.length },
      papersList.list
    ]
  })
  // 查询自己著作接口
  Mock.mock('http://localhost:8080/book/selectBookByCondition', 'post', {
    status: '0',
    msg: '查询著作信息成功',
    data: [
      { total: booksList.list.length },
      booksList.list
    ]
  })
  // 查询自己成果获奖接口
  Mock.mock('http://localhost:8080/award/selectAwardByCondition', 'post', {
    status: '0',
    msg: '查询成果获奖信息成功',
    data: [
      { total: awardsList.list.length },
      awardsList.list
    ]
  })
  // 查询自己专利接口
  Mock.mock('http://localhost:8080/patent/selectPatentByCondition', 'post', {
    status: '0',
    msg: '查询专利信息成功',
    data: [
      { total: patentsList.list.length },
      patentsList.list
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
    data: roleList.list
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
  // 响应用户-角色列表请求
  Mock.mock('http://localhost:8080/getUserRoleList', 'post', {
    status: '0',
    msg: '请求用户-角色列表成功',
    'data|5': [{
      'user_id|+1': 1,
      user_act: '@string(10)',
      user_name: '@cname',
      role_id: 1,
      role_name: roleList.list[0].role_name
    }]
  })
  // 根据用户id获取角色及部分用户信息
  Mock.mock('http://localhost:8080/getUserRoleById', 'post', {
    status: '0',
    msg: '获取用户角色数据成功',
    data: {
      'user_id|1-5': 1,
      user_act: '@string(10)',
      user_name: '@cname',
      role_id: 1,
      role_name: '角色1'
    }
  })
  // 设置用户角色
  Mock.mock('http://localhost:8080/setRole', 'post', {
    status: '0',
    msg: '修改用户角色成功',
    data: null
  })



  // 获取所有论文类型形式
  Mock.mock('http://localhost:8080/pt/findAllpt', 'post', {
    status: '0',
    msg: '获取论文类型信息成功',
    data: ptList.list
  })

  // 获取所有期刊
  Mock.mock('http://localhost:8080/periodical/findAllperiodical', 'post', {
    status: '0',
    msg: '获取期刊信息成功',
    data: periodicalList.list
  })
  // 获取所有出版社
  Mock.mock('http://localhost:8080/press/findAllpress', 'post', {
    status: '0',
    msg: '获取出版社信息成功',
    data: pressList.list
  })
  // 获取所有出版社等级
  Mock.mock('http://localhost:8080/pl/findAllpl', 'post', {
    status: '0',
    msg: '获取出版社等级信息成功',
    data: plList.list
  })
  // 获取著作类型
  Mock.mock('http://localhost:8080/bt/findAllbt', 'post', {
    status: '0',
    msg: '获取著作类型信息成功',
    data: btList.list
  })
  // 获取出版地
  Mock.mock('http://localhost:8080/pp/findAllpp', 'post', {
    status: '0',
    msg: '获取出版地信息成功',
    data: ppList.list
  })
  // 获取研究类型
  Mock.mock('http://localhost:8080/rt/findAllrt', 'post', {
    status: '0',
    msg: '获取研究类型信息成功',
    data: rtList.list
  })
  // 获取语种列表
  Mock.mock('http://localhost:8080/language/findAlllanguage', 'post', {
    status: '0',
    msg: '获取语种信息成功',
    data: languageList.list
  })
  // 获取获奖级别列表
  Mock.mock('http://localhost:8080/ar/findAllar', 'post', {
    status: '0',
    msg: '获取获奖级别信息成功',
    data: arList.list
  })
  // 获取获奖等级列表
  Mock.mock('http://localhost:8080/al/findAllal', 'post', {
    status: '0',
    msg: '获取获奖等级信息成功',
    data: alList.list
  })
  // 获取成果形式列表
  Mock.mock('http://localhost:8080/at/findAllat', 'post', {
    status: '0',
    msg: '获取成果形式信息成功',
    data: atList.list
  })
  // 获取专利类型列表
  Mock.mock('http://localhost:8080/ptz/findAllptz', 'post', {
    status: '0',
    msg: '获取专利类型信息成功',
    data: ptzList.list
  })
  // 获取专利范围列表
  Mock.mock('http://localhost:8080/pr/findAllpr', 'post', {
    status: '0',
    msg: '获取专利范围信息成功',
    data: prList.list
  })

  // 获取专利状态列表
  Mock.mock('http://localhost:8080/ps/findAllps', 'post', {
    status: '0',
    msg: '获取专利状态信息成功',
    data: psList.list
  })

  // 获取待审核项目列表
  Mock.mock('http://localhost:8080/check/project/findAllProjectByCondition', 'post', {
    status: '200',
    msg: '查询项目信息成功',
    data: [
      { total: projectList.list.length },
      projectList.list
    ]
  }) 
  // 根据待审核项目id获取待审核项目详细信息
  Mock.mock('http://localhost:8080/check/project/findProjectById', 'post', {
    status: '200',
    msg: '获取待审核项目详细信息成功',
    data: {
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
      'ct_id|0-4': 1,
      'leader_id|0-4': 1
    }
  })
  // 根据用户id获取用户信息
  Mock.mock('http://localhost:8080/user/findUserById','post', {
    status: '200',
    msg: '获取用户信息成功',
    data: {
      user_name: '测试用户',
      user_email: '123@456.789'
    }
  })
  // 批准项目申请
  Mock.mock('http://localhost:8080/check/project/approve', 'post', {
    status: '200',
    msg: '成功通过了项目审核',
    data: null
  })
  // 驳回项目申请
  Mock.mock('http://localhost:8080/check/project/deny', 'post', {
    status: '200',
    msg: '驳回项目申请成功',
    data: null
  })
}
  
