<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
     <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>科研成果</el-breadcrumb-item>
      <el-breadcrumb-item>论文成果</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <el-menu mode="horizontal"  active-text-color="#409EFF" :default-active="activeTab" router>
        <el-menu-item index="/papers/query">查询论文成果</el-menu-item>
        <el-menu-item index="/papers/manage">管理论文成果</el-menu-item>
      </el-menu>
      <router-view></router-view>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return{
      activeTab: '/papers/query',
    }
  },
  created() {
    this.activeTab = window.sessionStorage.getItem('activeTab')
  },
  methods: {
  }
}
</script>

<style lang="less" scoped>

</style>