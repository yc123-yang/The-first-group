<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>科研考核</el-breadcrumb-item>
      <el-breadcrumb-item>考核结果</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- 级联选择器选择考核时间 -->
      <span style="margin-right: 15px">考核批次：</span>
      <el-cascader v-model="assessTime" :options="assessTimeOption" :props="{ expandTrigger: 'hover' }" clearable @change="getAssessList">

      </el-cascader>

      <el-table :data="assessList" style="width: 100%; margin-top: 15px;" border  :header-cell-style="{ background: '#f5f7fa' }">
        <el-table-column type="index" label="#" fixed></el-table-column>
        <el-table-column type="selection" fixed></el-table-column>
        <el-table-column prop="name" label="姓名" align="center" width="150px"></el-table-column>
        <el-table-column prop="department_name" label="单位" align="center" width="200px"></el-table-column>
        <el-table-column prop="project_count" label="科研项目" align="center" width="200px"></el-table-column>
        <el-table-column prop="book_count" label="著作成果" align="center" width="200px"></el-table-column>
        <el-table-column prop="paper_count" label="论文成果" align="center" width="200px"></el-table-column>
        <el-table-column prop="award_count" label="成果获奖" align="center" width="200px"></el-table-column>
        <el-table-column prop="patent_count" label="专利成果" align="center" width="200px"></el-table-column>
        <el-table-column prop="total_grade" label="合计" align="center" width="200px"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 考核时间
      assessTime: '',
      // 考核时间选择列表
      assessTimeOption: [],
      // 考核数据列表
      assessList: [],
      // 单位对象列表，name:id 对象
      departmentList: [], departmentObj: {},
      
    }
  },
  async created() {
    await this.getAssessData()
    this.getAssessTimeOption()
    this.getAssessList()
  },
  methods: {
    // 获取字典数据
    async getAssessData() {
      const { data: res1 } = await this.$http.post('/department/findAllDepartment')
      this.departmentList = res1.data
      this.departmentList.forEach(item => this.departmentObj[item.department_id] = item.department_name)
      
    },
    // 获取考核时间选择列表
    getAssessTimeOption() {
      var date = new Date()
      var year = date.getFullYear()
      for(var i = year; i >= 2010; i--){
        this.assessTimeOption.push({
          value: i + '年',
          label: i + '年',
          children: [
            { value: '第1学期', label: '第1学期' },
            { value: '第2学期', label: '第2学期' }
          ]
        })
      }
    },
    // 获取考核数据列表
    async getAssessList() {
      var timeStr = ''
      if(this.assessTime.length > 0) timeStr = this.assessTime[0] + this.assessTime[1]
      const { data: res } = await this.$http.post('/check', this.$qs.stringify({ checkTime: timeStr }))
      if(res.status !== '200') return this.$message.error('获取考核数据失败')
      this.assessList = res.data
      console.log(this.assessList)
      this.assessList.forEach(item => {
        item.department_name = this.departmentObj[item.department_id]
      })
    }
  }
}
</script>

<style lang="less" scoped>

</style>