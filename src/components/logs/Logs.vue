<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>安全日志</el-breadcrumb-item>
      <el-breadcrumb-item>查看安全日志</el-breadcrumb-item>
    </el-breadcrumb>

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
import { stringify } from 'qs'
export default {
  data() {
    return {
      // 页面标签栏列表
      tabList: [],
      // 激活标签
      activeTab: ''
    }
  },
  async created() {
    await this.getTabList()
    var tlist = []
    this.tabList.forEach(item => tlist.push(item.auth_resource))
    if(tlist.indexOf(this.$route.path) === -1) this.$router.push(this.tabList[0].auth_resource)
    this.activeTab = window.sessionStorage.getItem('activeTab')
  },
  mounted() {
    
  },
  methods: {
    async getTabList() {
      const { data: res } = await this.$http.post('/auth/findAuthByPid', stringify({ auth_pid: 47 }))
      if( res.status !== '200' ) return this.$message.error('获取标签栏数据失败')
      this.tabList = res.data
    }
  }
}
</script>

<style lang="less" scoped>

</style>