<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>安全日志</el-breadcrumb-item>
      <el-breadcrumb-item>查看安全日志</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-button type="warning">导出信息</el-button>
      <el-button type="danger" @click="deleteAllLogs">清空日志</el-button>

      <el-table :data="logList" style="width: 100%; margin-top: 15px;" :header-cell-style="{background: '#f5f7fa'}">
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="log_date" label="日志时间"></el-table-column>
        <el-table-column label="日志级别">
          <template slot-scope="scope">

            <el-tag type="warning" v-if="scope.row.log_level==='WARN'">
              <i class="el-icon-warning"></i>
              {{scope.row.log_level}}
            </el-tag>
            <el-tag type="primary" v-else-if="scope.row.log_level==='INFO'">
              <i class="el-icon-info"></i>
              {{scope.row.log_level}}
            </el-tag>
            <el-tag type="danger" v-else-if="scope.row.log_level==='ERROR'">
              <i class="el-icon-error"></i>
              {{scope.row.log_level}}
            </el-tag>
            <el-tag type="success" v-else>
              <i class="el-icon-question"></i>
              {{scope.row.log_level}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="日志信息" prop="log_msg"></el-table-column>
      </el-table>
      <!-- 分页组件 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 10, 20]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
      
    </el-card>

  </div>
</template>

<script>
export default {
  data() {
    return {
      // 安全日志列表
      logList: [],
      // 安全日志列表请求表单
      queryInfo: {
        pageSize: 10,
        pageNum: 1
      },
      // 安全日志列表总条数
      total: 5
    }
  },
  created() {
    this.getLogList()
  },
  methods: {
    // 获取安全日志列表
    async getLogList() {
      const { data: res } = await this.$http.post('/logs/findAllLogs', this.$qs.stringify(this.queryInfo))
      if( res.status !== '200' ) return this.$message.error('获取日志列表失败')
      this.logList = res.data.logsList
      this.total = res.data.total
    },
    // 列表大小变动
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.queryInfo.pageNum = 1
      this.getLogList()
    },
    // 列表页数变动
    handleCurrentChange(newNum) {
      this.queryInfo.pageNum = newNum
      this.getLogList()
    },
    // 点击按钮，清空日志
    async deleteAllLogs() {
        const res = await this.$confirm('此操作将永久清空日志，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('logs/deleteAllLogs')
      if(res2.status !== '200') return this.$message.error('清空日志失败')
      this.$message.success('清空日志成功')
      this.getLogList()
    }
  }
}
</script>

<style lang="less" scoped>

</style>