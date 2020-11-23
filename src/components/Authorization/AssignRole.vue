<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>分配角色</el-breadcrumb-item>
    </el-breadcrumb>
    
    <el-card>
      <el-form :model="userInfo" label-width="100px" label-position="right">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="用户编号：">
              <el-input v-model="userInfo.user_id" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="用户名：">
              <el-input v-model="userInfo.user_act" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="名称：">
              <el-input v-model="userInfo.user_name" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" :offset="1">
            <el-button type="primary" icon="el-icon-search">搜索</el-button>
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="userRoleList" style="width: 100%;" :header-cell-style="{background: '#f5f7fa'}" border>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="user_id" label="用户编号"></el-table-column>
        <el-table-column prop="user_act" label="用户名"></el-table-column>
        <el-table-column prop="user_name" label="名称"></el-table-column>
        <el-table-column prop="role_name" label="角色"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="warning" icon="el-icon-setting" size="mini" @click="showAssignRoleDialog(scope.row)">分配角色</el-button>
          </template>
        </el-table-column>
      </el-table>
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
      // 查询用户对象
      userInfo: {
        user_id: '',
        user_act: '',
        user_name: '',
        pagenum: 1,
        pagesize: 5
      },
      // 用户-角色列表
      userRoleList: [],
      // 控制分配角色对话框的显示与隐藏
      AssignRoleDialogVisible: false,
      // 角色列表
      roleList: [],
      // 用户角色表单对象
      userRoleInfo: {}
    }
  },
  created() {
    this.getUserRoleList()
  },
  methods: {
    // 获取用户-角色列表
    async getUserRoleList() {
      const { data: res } = await this.$http.post("/getUserRoleList", this.userInfo)
      this.userRoleList = res.data
    },
    // 点击按钮，显示分配角色对话框
    async showAssignRoleDialog(userRole){
      this.userRoleInfo = userRole
      const { data: res } = await this.$http.post('/getrolelist')
      this.roleList = res.data
      const { data: res2 } = await this.$http.post('/getUserRoleById', userRole.user_id)
      this.userRoleInfo = res2.data
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
      const { data: res1 } = await this.$http.post('/setRole', {
        user_id: this.userRoleInfo.user_id,
        role_id: this.userRoleInfo.role_id
      })
      if(res1.status !== '0') return this.$message.error('修改用户角色失败')
      this.$message.success('修改用户角色成功')
      this.getUserRoleList()
      this.AssignRoleDialogVisible = false
    }
  }
}
</script>

<style lang="less" scoped>

</style>