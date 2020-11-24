<template>
    <el-container class="homeContainer">
      <!-- 头部容器 -->
      <el-header>
        <!-- Home组件 -->
        <div style="height: 100%;">
          <img class="logoImg" src="../assets/image/sicnu.jpg" alt="" style="height: 80%;">
          <span class="logoTextSpan">高校科研管理系统</span>
        </div>
        <el-button type="info" @click="logout">退出</el-button>
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
                <i :class="iconsObj[item.id]"></i>
                <!-- 文本 -->
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item v-for="subItem in item.children" :index="'/' + subItem.path" :key="subItem.id"
                @click="saveNavState('/'+subItem.path)">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>{{subItem.name}}</span>
                </template>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>

        <!-- 页面内容 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
</template>

<script>
export default {
  data () {
    return {
      // 左侧菜单数据
      menulist: [],
      // 图标转换对象
      iconsObj: {
        101: 'el-icon-tickets',
        102: 'el-icon-s-opportunity',
        103: 'el-icon-money',
        104: 'el-icon-s-data',
        105: 'el-icon-s-comment',
        106: 'el-icon-user-solid',
        107: 'el-icon-circle-check',
        108: 'el-icon-document'
      },
      // 是否折叠菜单
      isCollapse: false,
      // 激活的菜单项
      activeNav: ''
    }
  },
  created () {
    this.getMenuList()
    this.activeNav = window.sessionStorage.getItem('activeNav')
  },
  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$message('退出登录')
      this.$router.push('/login')
    },
    async getMenuList() {
      const { data: res } = await this.$http.post('/getmenulist')
      this.menulist = res.data
    },
    saveNavState (activeNav) {
      window.sessionStorage.setItem('activeNav', activeNav)
      this.activeNav = activeNav
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
  font-size: 30px;
  font-family: "华文行楷";
  > div {
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
</style>
