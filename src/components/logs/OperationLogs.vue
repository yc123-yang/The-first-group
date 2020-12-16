<template>
  <div style="margin-top: 25px">
    <el-button type="warning" size="medium">导出信息</el-button>
    <el-button type="danger" @click="deleteLogs" size="medium">清空日志</el-button>
    <el-table :data="logList" style="width: 100%; margin-top: 15px;" :header-cell-style="{background: '#f5f7fa'}">
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column type="selection"></el-table-column>
      <el-table-column label="日志时间" prop="ol_time"></el-table-column>
      <el-table-column label="日志级别">
        <template slot-scope="scope">
          <el-tag type="warning" v-if="scope.row.ol_level==='WARN'">
            <i class="el-icon-warning"></i>
            {{scope.row.ol_level}}
          </el-tag>
          <el-tag type="primary" v-else-if="scope.row.ol_level==='INFO'">
            <i class="el-icon-info"></i>
            {{scope.row.ol_level}}
          </el-tag>
          <el-tag type="danger" v-else-if="scope.row.ol_level==='ERROR'">
            <i class="el-icon-error"></i>
            {{scope.row.ol_level}}
          </el-tag>
          <el-tag type="success" v-else>
            <i class="el-icon-question"></i>
            {{scope.row.ol_level}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作用户信息">
        <el-table-column label="编号" prop="ol_uid"></el-table-column>
        <el-table-column label="姓名" prop="ol_uname"></el-table-column>
        <el-table-column label="ip地址" prop="ol_ip"></el-table-column>
      </el-table-column>
      <el-table-column label="日志位置" prop="ol_location" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="日志信息" prop="ol_message" :show-overflow-tooltip="true"></el-table-column>
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
  </div>
</template>

<script>
import { stringify } from 'qs'
export default {
  data() {
    return {
      queryInfo: {
        pageSize: 10,
        pageNum: 1
      },
      // 日志列表
      logList: [],
      total: 10
    }
  },
  created() {
    this.getLogList()
  },
  methods: {
    async getLogList() {
      const { data: res } = await this.$http.post('/logs/findAllOperationLogs', stringify(this.queryInfo))
      if( res.status !== '200' ) return this.$message.error('获取操作日志失败')
      console.log(res)
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
    // 清空日志
    async deleteLogs() {
        const res = await this.$confirm('此操作将永久清空日志，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/logs/deleteAllOperationLogs')
      if(res2.status !== '200') return this.$message.error('清空日志失败')
      this.$message.success('清空日志成功')
      this.getLogList()
    }
  }
}
</script>

<style lang="less" scoped>

</style>