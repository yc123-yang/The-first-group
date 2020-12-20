<template>
  <div style="height: 100%">
    <el-container class="homeContainer">
      <!-- 头部容器 -->
      <el-header>
        <!-- Home组件 -->
        <div style="height: 100%;">
          <img class="logoImg" src="../assets/image/sicnu.jpg" alt="" style="height: 80%;">
          <span class="logoTextSpan">高校科研管理系统</span>
        </div>
        <span style="font">
          <span>欢迎，{{userInfo.role_name}}：</span>
          <el-dropdown trigger="click" @command="handleCommand">
            <el-link type="primary">{{userInfo.user_name}}</el-link>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-edit" command="updateUser">更改信息</el-dropdown-item>
              <el-dropdown-item icon="el-icon-switch-button" command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
        
        <!-- <el-button type="info" @click="logout">退出</el-button> -->
      </el-header>

      <el-container>
        <!-- 侧边导航栏容器 -->
        <el-aside :width="isCollapse ? '64px' : '200px'">
          <div class="toggle-button" @click="isCollapse = !isCollapse">|||</div>
          <el-menu background-color="#34495e" text-color="#fff" active-text-color="#409bff"
            unique-opened :router="true" :collapse="isCollapse" :collapse-transition="false"
            :default-active="activeNav">
            <!-- 一级菜单 -->
            <el-submenu :index="item.id+''" v-for="item in menulist" :key="item.id">
                <template slot="title">
                  <!-- 图标 -->
                  <el-badge :is-dot="item.id === 5 && checkTotal > 0" style="margin: 0">
                    <i :class="iconsObj[item.id]"></i>
                  </el-badge>
                  <!-- 文本 -->
                  <span>{{ item.title }}</span>
                  
                </template>
              <el-menu-item v-for="subItem in item.childrenPermissions" :index="subItem.resourcePath" :key="subItem.id"
                @click="saveNavState(subItem.resourcePath)">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <el-badge v-if="checkId.indexOf(subItem.id) !== -1 && checkNum[subItem.id] > 0" :value="checkNum[subItem.id]" :max="99">
                    <span>{{subItem.title}}</span>
                  </el-badge>
                  <span v-else>{{subItem.title}}</span>
                </template>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-container>
          <!-- 页面内容 -->
          <el-main>
            <router-view></router-view>
          </el-main>
          <el-footer style="text-align: center">
            <div>
              <i class="el-icon-edit" style="color: gray"></i>
              <span style="color: gray">2018级4班第1小组</span>
            </div>
            <div style="color: gray">
              <span style="vertical-align:  middle;">项目地址：</span>
              <img src="https://img.icons8.com/windows/32/000000/github.png" @click="toGitHub"
                style="cursor: pointer;width: 22px;height: 22px;vertical-align:  middle;"/>
            </div>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>


    <el-dialog title="修改用户信息" :visible.sync="updateUserDialogVisible" width="30%" @closed="updateUserDialogClosed">
      <el-form :model="editUserForm" :rules="editUserFormRules" ref="editUserFormRef" label-width="100px">
        <el-form-item label="电子邮箱" prop="user_email">
          <el-input v-model="editUserForm.user_email"></el-input>
        </el-form-item>
        <el-form-item label="单位" prop="department_id">
          <el-select v-model="editUserForm.department_id" style="width: 100%">
            <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id"
              :key="item.department_id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateUserDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { stringify } from 'qs'
export default {
  data () {
    return {
      // 用户数据
      userInfo: {},
      // 左侧菜单数据
      menulist: [],
      // 图标转换对象
      iconsObj: {
        1: 'el-icon-tickets',
        2: 'el-icon-s-opportunity',
        3: 'el-icon-money',
        4: 'el-icon-s-data',
        5: 'el-icon-s-comment',
        35: 'el-icon-user-solid',
        36: 'el-icon-circle-check',
        37: 'el-icon-document',
        46: 'el-icon-warning'
      },
      // 是否折叠菜单
      isCollapse: false,
      // 激活的菜单项
      activeNav: '',
      checkId: [13, 14, 15, 16, 17],
      checkNum: {13: 0, 14: 0, 15: 0, 16: 0, 17: 0},
      checkTotal: 0,
      updateUserDialogVisible: false,
      editUserForm: {},
      editUserFormRules: {
        user_email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message: "邮箱格式错误",
          },
        ],
        department_id: [{ required: true, message: '请选择单位', trigger: 'change' }]
      },
      departmentList: []
    }
  },
  created () {
    this.getMenuList()
    this.activeNav = window.sessionStorage.getItem('activeNav')
    this.userInfo.user_name = window.sessionStorage.getItem('user_name')
    this.userInfo.user_id = window.sessionStorage.getItem('user_id')
    this.userInfo.role_name = window.sessionStorage.getItem('role_name')
    this.getDepartmentList()
  },
  methods: {
    // 获取单位列表，构造单位 id:name 对象
    async getDepartmentList() {
      return new Promise((resolve, reject) => {
        this.$http.post('/department/findAllDepartment').then(resp => {
          if(resp.data.status !== '200') reject(this.$message.error('获取单位数据失败')) 
          this.departmentList = resp.data.data
          resolve()
        })
      })
    },
    logout () {
      window.sessionStorage.clear()
      this.$message('退出登录')
      this.$router.push('/login')
    },
    async getMenuList() {
      const { data: res } = await this.$http.post('/user/getUserMenu')
      this.menulist = res.data
      res.data.forEach(item => {
        if(item.id === 5){
          const timer = setInterval(() => {
            setTimeout(this.getCheckNum(), 0)
          }, 10000)
          this.$once('hook:beforeDestroy', () => {
            clearInterval(timer)
          })
        }
      })
    },
    saveNavState (activeNav) {
      window.sessionStorage.setItem('activeNav', activeNav)
      this.activeNav = activeNav
    },
    toGitHub() {
      window.open("https://github.com/yc123-yang/The-first-group")
    },
    getCheckNum() {
      this.$http.post('/count/check').then(resp => {
        if( resp.data.status !== '200' ) return
        this.checkTotal = 0
        for(var i = 0; i < 5; i++) {
          this.checkNum[this.checkId[i]] = resp.data.data[i]
          this.checkTotal += resp.data.data[i]
        }
      })
    },
    async showUpdateUserDialog(user_id) {
      const { data: res } = await this.$http.post('/user/findUserById', stringify({ user_id: user_id }))
      if( res.status !== '200' ) return this.$message.error('获取用户信息失败')
      this.editUserForm = res.data
      this.updateUserDialogVisible = true
    },
    handleCommand(command) {
      if(command === 'logout') this.logout()
      if(command === 'updateUser') this.showUpdateUserDialog(window.sessionStorage.getItem('user_id'))
    },
    updateUserDialogClosed() { this.$refs.editUserFormRef.resetFields() },
    async updateUser() {
      const { data: res } = await this.$http.post('/user/updateUserMessage', stringify(this.editUserForm))
      if( res.status !== '200' ) return this.$message.error('更新用户信息失败')
      this.$message.success('更新用户信息成功')
      this.updateUserDialogVisible = false 
    }
  }
}
</script>

<style lang="less" scoped>
.el-header {
  background-color: #ffffff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  > div {
    font-size: 30px;
    font-family: "华文行楷";
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
.el-aside {
  background-color: #34495e;
  .el-menu {
    border-right: 0;
  }
}
.el-main {
  background-color: #f2f2f2;
}
.el-footer {
  background-color: #f2f2f2;
}
.homeContainer {
  height: 100%;
}
.toggle-button {
  background-color: #34495e;
  color: #fff;
  font-size: 10px;
  line-height: 24px;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}

::-webkit-scrollbar {
  display: none;
}
</style>
