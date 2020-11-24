<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>管理角色</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-button type="primary">添加角色</el-button>
      <el-table :data="roleList" style="width: 100%; margin-top: 15px" :header-cell-style="{background: '#f5f7fa'}" border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="role_id" label="角色编号"></el-table-column>
        <el-table-column prop="role_name" label="角色名称"></el-table-column>
        <el-table-column prop="role_discribe" label="角色描述"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
            <el-button type="warning" icon="el-icon-setting" size="mini" @click="showAuthorEditDialog(scope.row)"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 编辑权限对话框 -->
    <el-dialog title="修改角色权限" :visible.sync="AuthorEditDialogVisible" width="50%" @closed="resetAuthTree">
      <el-tree :data="allAuthList" show-checkbox :props="authTreeProps" default-expand-all ref="authTree"
        node-key="id" :default-checked-keys="checkedKeys">
      </el-tree>

      <span slot="footer" class="dialog-footer">
        <el-button @click="AuthorEditDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editAuthor(roleInfo.role_id)">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 角色列表
      roleList: [],
      // 权限编辑对话框显示与隐藏
      AuthorEditDialogVisible: false,
      // 角色信息表单对象
      roleInfo: {},
      // 角色权限列表，树形
      authList: [],
      // 所有权限列表，树形
      allAuthList: [{
        id: 1,
        title: '科研项目',
        resourcePath: 0,
        childrenPermissions: [{
          id: 6,
          title: '科研项目',
          resourcePath: 'projects',
          childrenPermissions: [
              { id: 18, title: '查看科研项目', resourcePath: 'projects/query' },
              { id: 19, title: '管理科研项目', resourcePath: 'projects/manage' }
            ]
          }]
        }, {
          id: 2,
          title: '科研成果',
          resourcePath: 0,
          childrenPermissions: [{
            id: 7,
            title: '论文成果',
            resourcePath: 'papers',
            childrenPermissions: [
              { id: 20, title: '查看论文成果', resourcePath: 'papers/query' },
              { id: 21, title: '管理论文成果', resourcePath: 'papers/manage' }
            ]
          }, {
          id: 8,
          title: '著作成果',
          resourcePath: 'books',
          childrenPermissions: [
            { id: 22, title: '查看著作成果', resourcePath: 'books/query' },
            { id: 23, title: '管理著作成果', resourcePath: 'books/manage' }
          ]
        }, {
          id: 9,
          title: '科研成果获奖',
          resourcePath: 'awards',
          childrenPermissions: [
            { id: 24, title: '查看成果获奖', resourcePath: 'awards/query' },
            { id: 25, title: '管理成果获奖', resourcePath: 'awards/manage' }
          ]
        }, {
          id: 10,
          title: '专利与产品',
          resourcePath: 'patents',
          childrenPermissions: [
            { id: 26, title: '查看专利产品', resourcePath: 'patents/query' },
            { id: 27, title: '管理专利产品', resourcePath: 'patents/manage' }
          ]
        }]
      }, {
        id: 3,
        title: '项目经费',
        resourcePath: 0,
        childrenPermissions: [{
          id: 11,
          title: '管理项目经费',
          resourcePath: 'funding',
          childrenPermissions: [{ id: 28, title: '管理项目经费', resourcePath: 0 }]
        }]
      }, {
        id: 4,
        title: '科研考核',
        resourcePath: 0,
        childrenPermissions: [{
          id: 12,
          title: '考核结果',
          resourcePath: 'assess',
          childrenPermissions: [{ id: 29, title: '查看考核结果', resourcePath: 0 }]
        }]
      }, {
        id: 5,
        title: '审核申请',
        resourcePath: 0,
        childrenPermissions: [{
          id: 13,
          title: '审核项目申请',
          resourcePath: 'checkproject',
          childrenPermissions: [{ id: 30, title: '审核项目申请', resourcePath: 0 }]
        }, {
          id: 14,
          title: '审核论文成果申请',
          resourcePath: 'checkpapers',
          childrenPermissions: [{ id: 31, title: '审核论文成果申请', resourcePath: 0 }]
        }, {
          id: 15,
          title: '审核著作成果申请',
          resourcePath: 'checkbooks',
          childrenPermissions: [{ id: 32, title: '审核著作成果申请', resourcePath: 0 }]
        }, {
          id: 16,
          title: '审核科研获奖申请',
          resourcePath: 'checkawards',
          childrenPermissions: [{ id: 33, title: '审核科研获奖申请', resourcePath: 0 }]
        }, {
          id: 17,
          title: '审核专利产品申请',
          resourcePath: 'checkproducts',
          childrenPermissions: [{ id: 34, title: '审核专利产品申请', resourcePath: 0 }]
        }]
      }, {
        id: 35,
        title: '用户管理',
        resourcePath: 0,
        childrenPermissions: [{
          id: 38,
          title: '编辑用户信息',
          resourcePath: 'userEdit',
          childrenPermissions: [{ id: 42, title: '编辑用户信息', resourcePath: 0 }]
        }]
      }, {
        id: 36,
        title: '权限管理',
        resourcePath: 0,
        childrenPermissions: [{
          id: 39,
          title: '管理角色',
          resourcePath: 'roleEdit',
          childrenPermissions: [{ id: 43, title: '管理角色', resourcePath: 0 }]
        }, {
          id: 40,
          title: '分配角色',
          resourcePath: 'assignRole',
          childrenPermissions: [{ id: 44, title: '分配角色', resourcePath: 0 }]
        }]
      }, {
        id: 37,
        title: '数据管理',
        resourcePath: 0,
        childrenPermissions: [{
          id: 41,
          title: '管理字典数据',
          resourcePath: 'data',
          childrenPermissions: [{ id: 45, title: '管理字典数据', resourcePath: 0 }]
        }]
      }],
      // 权限编辑树形控件属性对象
      authTreeProps: {
        children: 'childrenPermissions',
        label: 'title'
      },
      // 权限树中默认勾选的项
      checkedKeys: []
    }
  },
  created() {
    this.getRoleList()
  },
  methods: {
    // 获取角色列表
    async getRoleList() {
      const { data: res } = await this.$http.post('/role/findAllRole')
      if( res.status !== '0' ) return this.$message.error('获取角色列表失败')
      this.roleList = res.data
    },
    // 点击按钮，显示修改角色权限对话框
    async showAuthorEditDialog(role) {
      const { data: res } = await this.$http.post('/user/queryPermissionByRoleId', this.$qs.stringify({ role_id: role.role_id}))
      this.authList = res.data
      // 获取全部权限数据
      this.allAuthList = this.allAuthList
      this.roleInfo = role
      // 将该角色拥有的权限id读入checkedKeys列表中
      this.checkedKeys = []
      this.authList.forEach(item => {
        item.childrenPermissions.forEach(item2 => {
          item2.childrenPermissions.forEach(item3 => this.checkedKeys.push(item3.id) )
        })
      })
      this.AuthorEditDialogVisible = true
    },
    // 点击确定，更改角色权限
    async editAuthor(val) {
      const res = await this.$confirm('此操作将永久更改角色权限，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      // 更改角色权限
      var alist = this.$refs.authTree.getCheckedKeys()
      this.$refs.authTree.getHalfCheckedKeys().forEach(item => alist.push(item))
      var postForm = { role_id: val, authList: alist }
      console.log(postForm)
      const { data: res1 } = await this.$http.get('/roleAuth/updateAuth?role_id='+val+"&authList="+alist)
      console.log(res1)
      if( res1.status !== '200' ) return this.$message.error('修改角色权限失败')
      this.$message.success('修改角色权限成功')
      this.getRoleList()
      this.AuthorEditDialogVisible = false
    },
    // 重置权限树
    resetAuthTree() {
      this.$refs.authTree.setCheckedKeys([])
    }
  }  
}
</script>

<style lang="less" scoped>

</style>