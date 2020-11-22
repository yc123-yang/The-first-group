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
      menulist: [
        {
          id: 101,
          name: '科研项目',
          path: null,
          children: [
            {
              id: 211,
              name: '科研项目',
              path: 'projects'
            }
          ]
        }, {
          id: 102,
          name: '科研成果',
          path: null,
          children: [
            {
              id: 221,
              name: '论文成果',
              path: 'papers'
            }, {
              id: 222,
              name: '著作成果',
              path: 'books'
            }, {
              id: 223,
              name: '科研成果获奖',
              path: 'awards'
            }, {
              id: 224,
              name: '专利与产品',
              path: 'products'
            }
          ]
        }, {
          id: 103,
          name: '项目经费',
          path: null,
          children: [
            {
              id: 231,
              name: '管理项目经费',
              path: 'funding'
            }
          ]
        }, {
          id: 104,
          name: '科研考核',
          path: null,
          children: [
            {
              id: 241,
              name: '考核结果',
              path: 'assess'
            }
          ]
        }, {
          id: 105,
          name: '审核申请',
          path: null,
          children: [
            {
              id: 251,
              name: '审核项目申请',
              path: 'check/project'
            }, {
              id: 252,
              name: '审核论文成果申请',
              path: 'check/papers'
            }, {
              id: 253,
              name: '审核著作成果申请',
              path: 'check/books'
            }, {
              id: 254,
              name: '审核科研获奖申请',
              path: 'check/awards'
            }, {
              id: 255,
              name: '审核专利产品申请',
              path: 'check/products'
            }
          ]
        }
      ],
      // 图标转换对象
      iconsObj: {
        101: 'el-icon-tickets',
        102: 'el-icon-s-opportunity',
        103: 'el-icon-money',
        104: 'el-icon-s-data',
        105: 'el-icon-s-comment'
      },
      // 是否折叠菜单
      isCollapse: false,
      // 激活的菜单项
      activeNav: ''
    }
  },
  created () {
    this.activeNav = window.sessionStorage.getItem('activeNav')
  },
  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$message('退出登录')
      this.$router.push('/login')
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
