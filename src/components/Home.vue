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
                <span>{{ item.title }}</span>
              </template>
              <el-menu-item v-for="subItem in item.childrenPermissions" :index="subItem.resourcePath" :key="subItem.id"
                @click="saveNavState(subItem.resourcePath)">
                <template slot="title">
                  <i class="el-icon-menu"></i>
                  <span>{{subItem.title}}</span>
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
</template>

<script>
export default {
  data () {
    return {
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
      const { data: res } = await this.$http.post('/user/getUserMenu')
      console.log(res)
      this.menulist = res.data
    },
    saveNavState (activeNav) {
      window.sessionStorage.setItem('activeNav', activeNav)
      this.activeNav = activeNav
    },
    toGitHub() {
      window.open("https://github.com/yc123-yang/The-first-group")
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
</style>
