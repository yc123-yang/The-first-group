<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
     <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>科研成果</el-breadcrumb-item>
      <el-breadcrumb-item>专利与产品</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <el-menu mode="horizontal"  active-text-color="#409EFF" :default-active="activeTab" router>
        <el-menu-item v-for="item in tabList" :index="item.auth_resource" :key="item.auth_id">
          <template slot="title">
            <span>{{item.auth_name}}</span>
          </template>
        </el-menu-item>
      </el-menu>
      <router-view></router-view>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return{
      activeTab: '/patents/query',
      // 上方标签导航栏
      tabList: []
    }
  },
  async created() {
    console.log('haha')
    this.getTabList()
    this.activeTab = window.sessionStorage.getItem('activeTab')
  },
  methods: {
    async getTabList() {
      const { data: res } = await this.$http.post('/auth/findAuthByPid', this.$qs.stringify({ auth_pid: 10 }))
      this.tabList = res.data
    }
  }
}
</script>

<style lang="less" scoped>

</style>