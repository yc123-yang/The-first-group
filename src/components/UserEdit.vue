<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>编辑用户信息</el-breadcrumb-item>
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
      <el-table :header-cell-style="{background: '#f5f7fa'}" :data="userList" style="width: 100%;margin-top: 15px" border :loading="isLoading">
        <el-table-column type="index" label="#" align="center"></el-table-column>
        <el-table-column prop="user_act" label="用户名" align="center"></el-table-column>
        <el-table-column prop="user_name" label="姓名" align="center"></el-table-column>
        <el-table-column label="单位" align="center" prop="department_name"></el-table-column>
        <el-table-column prop="user_email" label="邮箱" align="center"></el-table-column>
        <el-table-column label="状态" align="center">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.user_state" active-color="#13ce66" inactive-color="#ff4949"
              :active-value="0" :inactive-value="1" @change="changeUserState(scope.row.user_id, scope.row.user_state)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini">修改信息</el-button>
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


  </div>
</template>

<script>
import { stringify } from 'qs'
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
      // 符合条件数据总条数
      total: 10,
      // 单位列表
      departmentList: [],
      // 用户列表
      userList: [],
      isLoading: false
    }
  },
  created() {
    this.getDepeartmentList()
    this.getUserList()
  },
  methods: {
    // 获取单位列表
    async getDepeartmentList() {
      const { data: res } = await this.$http.post('/department/findAllDepartment')
      if( res.status !== '200' ) return this.$message.error('获取单位列表失败')
      this.departmentList = res.data
    },
    // 获取用户列表
    async getUserList() {
      this.isLoading = true
      const { data: res } = await this.$http.post('/user/selectUserByCondition', this.queryInfo)
      if( res.status !== '200' ) return this.$message.error('获取用户列表失败')
      this.userList = res.data.userList
      this.total = res.data.total
      this.isLoading = false
    },
    // 页面大小发生变化
    handleSizeChange(val) {
      this.queryInfo.pageSize = val
      this.queryInfo.pageNum = 1
      this.getUserList()
    },
    // 页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getUserList()
    },
    // 改变用户状态
    async changeUserState(user_id, user_state) {
      const { data: res } = await this.$http.post('/user/changeStatus', stringify({ user_id: user_id, user_state: user_state }))
      if( res.status !== '200' ) return this.$message.error('更改用户状态失败')
      this.getUserList()
    },
    // 根据条件搜索用户
    searchUserList() {
      this.queryInfo.pageNum = 1
      this.getUserList()
    }
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