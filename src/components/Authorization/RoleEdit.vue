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
    <el-dialog title="修改角色权限" :visible.sync="AuthorEditDialogVisible" width="50%">
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
      allAuthList: [],
      // 权限编辑树形控件属性对象
      authTreeProps: {
        children: 'children',
        label: 'name'
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
      const { data: res } = await this.$http.post('/getrolelist')
      if( res.status !== '0' ) return this.$message.error('获取角色列表失败')
      this.roleList = res.data
    },
    // 点击按钮，显示修改角色权限对话框
    async showAuthorEditDialog(role) {
      const { data: res } = await this.$http.post('/getAuthlistById', role.role_id)
      this.authList = res.data
      const { data: res2 } = await this.$http.post('/getmenulist');
      this.allAuthList = res2.data
      this.roleInfo = role
      // 将该角色拥有的权限id读入checkedKeys列表中
      this.checkedKeys = []
      this.authList.forEach(item => {
        item.children.forEach(item2 => {
          item2.children.forEach(item3 => this.checkedKeys.push(item3.id) )
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
      console.log(alist)
      const { data: res1 } = await this.$http.post('/role/editAuthById', {
        role_id: val,
        authList: alist
      })
      console.log(res1)
      if( res1.status !== '0' ) return this.$message.error('修改角色权限失败')
      this.$message.success('修改角色权限成功')
      this.getRoleList()
      this.AuthorEditDialogVisible = false
    }
  }  
}
</script>

<style lang="less" scoped>

</style>