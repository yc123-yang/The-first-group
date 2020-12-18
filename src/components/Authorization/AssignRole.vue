<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>分配角色</el-breadcrumb-item>
    </el-breadcrumb>
    
    <el-card>
      <el-form inline :model="queryInfo" class="query-user-form" size="mini">
        <el-form-item label="用户名：">
          <el-input v-model="queryInfo.user_act" clearable @change="searchUserList"></el-input>
        </el-form-item>
        <el-form-item label="姓名：">
          <el-input v-model="queryInfo.user_name" clearable @change="searchUserList"></el-input>
        </el-form-item>
        <el-form-item label="单位：">
          <el-select v-model="queryInfo.department_id" clearable @change="searchUserList">
            <el-option v-for="item in departmentList" :key="item.department_id" :value="item.department_id" :label="item.department_name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="searchUserList">搜索</el-button>
      </el-form>
      <el-table :data="userRoleList" style="width: 100%;" :header-cell-style="{background: '#f5f7fa'}" border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="user_act" label="用户名"></el-table-column>
        <el-table-column prop="user_name" label="姓名"></el-table-column>
        <el-table-column prop="user_email" label="邮箱"></el-table-column>
        <el-table-column prop="department_name" label="单位"></el-table-column>
        <el-table-column prop="role_name" label="角色"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="warning" icon="el-icon-setting" size="mini" @click="showAssignRoleDialog(scope.row)">分配角色</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-card>

    <!-- 分配角色对话框 -->

    <el-dialog title="分配角色" :visible.sync="AssignRoleDialogVisible" width="30%" style="padding-right: 0">
      <el-form class="dialog">
        <el-form-item label="用户编号："><span>{{ userRoleInfo.user_id }}</span></el-form-item>
        <el-form-item label="用户名："><span>{{ userRoleInfo.user_act }}</span></el-form-item>
        <el-form-item label="名称："><span>{{ userRoleInfo.user_name }}</span></el-form-item>
        <el-form-item label="角色：">
          <el-select v-model="userRoleInfo.role_id" placeholder="请选择活动区域" style="margin: 0">
            <el-option v-for="item in roleList" :key="item.role_id" :value="item.role_id" :label="item.role_name"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="AssignRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="assignRole">确 定</el-button>
      </span>
    </el-dialog>
  </div>
    

    
</template>

<script>
export default {
  data() {
    return {
      // 查询用户列表对象
      queryInfo: {
        user_name: '',
        user_act: '',
        department_id: '',
        pageNum: 1,
        pageSize: 10
      },
      total: 10,
      // 用户-角色列表
      userRoleList: [],
      // 控制分配角色对话框的显示与隐藏
      AssignRoleDialogVisible: false,
      // 角色列表
      roleList: [],
      // 用户角色表单对象
      userRoleInfo: {},
      // 角色id名称转换object
      roleObj: {},
      departmentList: []
    }
  },
  created() {
    this.getDepeartmentList()
    this.getUserRoleList()
  },
  methods: {
    // 获取单位列表
    async getDepeartmentList() {
      const { data: res } = await this.$http.post('/department/findAllDepartment')
      if( res.status !== '200' ) return this.$message.error('获取单位列表失败')
      this.departmentList = res.data
    },
    // 获取用户-角色列表
    async getUserRoleList() {
      const { data: res0 } = await this.$http.post('/role/findAllRole')
      this.roleList = res0.data
      this.roleList.forEach(item => this.roleObj[item.role_id] = item.role_name)
      const { data: res } = await this.$http.post("/user/selectUserByCondition", this.queryInfo)
      this.userRoleList = res.data.userList
      this.total = res.data.total
      this.userRoleList.forEach(item => item.role_name = this.roleObj[item.role_id])
    },
    // 根据条件搜索用户
    searchUserList() {
      this.queryInfo.pageNum = 1
      this.getUserRoleList()
    },
    // 点击按钮，显示分配角色对话框
    async showAssignRoleDialog(userRole){
      this.userRoleInfo = userRole
      const { data: res } = await this.$http.post('/role/findAllRole')
      this.roleList = res.data
      this.userRoleInfo = userRole
      const { data: res2 } = await this.$http.post('/user/findUserById', this.$qs.stringify({ user_id: this.userRoleInfo.user_id }))
      this.userRoleInfo = res2.data
      console.log(res2)
      this.AssignRoleDialogVisible = true
    },
    async assignRole() {
      const res = await this.$confirm('此操作将永久修改用户角色，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      // 更改用户角色
      const { data: res1 } = await this.$http.post('/user/updateUserRole', this.$qs.stringify({
        user_id: this.userRoleInfo.user_id,
        role_id: this.userRoleInfo.role_id
      }))
      if(res1.status !== '200') return this.$message.error('修改用户角色失败')
      this.$message.success('修改用户角色成功')
      this.getUserRoleList()
      this.AssignRoleDialogVisible = false
    },
    // 页面大小发生变化
    handleSizeChange(val) {
      this.queryInfo.pageSize = val
      this.queryInfo.pageNum = 1
      this.getUserRoleList()
    },
    // 页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getUserRoleList()
    },
  }
}
</script>

<style lang="less" scoped>
.query-user-form {
  .el-form-item {
    margin-right: 30px;
  }
}
</style>