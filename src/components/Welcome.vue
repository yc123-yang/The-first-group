<template>
  <div>
    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item>
        <img src="../assets/image/homeImg.jpg"/>
      </el-carousel-item>
      <el-carousel-item>
        <img src="../assets/image/homeImg2.jpg"/>
      </el-carousel-item>
      <el-carousel-item>
        <img src="../assets/image/homeImg3.jpeg"/>
      </el-carousel-item>
    </el-carousel>
    
    <el-card>
    <el-row :gutter="53">
      <el-col :span="9">
        <div id="main" style="width: 400px;height: 455px;"></div>
      </el-col>
      <el-col :span="15" style="border-left: 1px solid gray; height: 400px">
          <h1>公告</h1>
          <el-table :data="anounceList" :show-header="false" size="large" :row-style="{height: '62px'}"
            style="margin-top: 15px" height="312px" ref="anounceTableRef">
            <el-table-column label="公告标题" prop="anounce_title" :show-overflow-tooltip="true">
              <template slot-scope="scope">
                <el-link @click="showAnounceInfoDialog(scope.row.anounce_id)">
                  {{scope.row.anounce_title}}
                </el-link>
              </template>
            </el-table-column>
            <el-table-column label="发表时间" prop="anounce_date" width="170px"></el-table-column>
            <el-table-column label="作者" prop="user_name" width="150px"  :show-overflow-tooltip="true">
              <template slot-scope="scope">
                <span>by {{scope.row.user_name}}</span>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination style="float: right"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.pageNum"
            :page-size="queryInfo.pageSize"
            background
            layout="prev, pager, next"
            :total="total">
          </el-pagination>
      </el-col>
    </el-row>
    </el-card>

    <el-dialog :title="anounceInfo.anounce_title" :visible.sync="anounceInfoDialogVisible" width="50%">
      <div style="height: 300px;border-bottom: 1px solid gray;">
        <span>{{anounceInfo.anounce_text}}</span>
      </div>
      <div>
        <span style="float: right">作者：{{anounceInfo.user_name}} 时间：{{anounceInfo.anounce_date}}</span>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="anounceInfoDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="anounceInfoDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
  
</template>

<script>
import * as echarts from "echarts";
import { stringify } from 'qs';

export default {
  data() {
    return {
      // 科研相关数据统计列表
      countList: [],
      option: {
        title: {
          text: '相关数据统计'
        },
        tooltip: {},
        legend: {
          data:['数量']
        },
        xAxis: {
          data: ["科研项目","论文成果","著作成果","成果获奖","专利产品"]
        },
        yAxis: {},
        series: [{
          name: '数量',
          type: 'bar',
          data: []
        }]
      },
      queryInfo: {
        pageNum: 1,
        pageSize: 5,
      },
      total: 5,
      anounceList: [],
      anounceInfo: {},
      anounceInfoDialogVisible: false
    }
  },
  created() {
    this.getAnounceList()
    
  },
  mounted() {
    this.getCountList()
  },
  methods: {
    async getCountList() {
      var myChart = echarts.init(document.getElementById('main'));
      const { data: res } = await this.$http.post('/count/all')
      if( res.status !== '200' ) return this.$message.error('获取科研相干统计数据失败')
      this.option.series[0].data = res.data
      myChart.setOption(this.option)
    },
    async getAnounceList() {
      const { data: res } = await this.$http.post('/anounce/selectAllAnounce', this.queryInfo)
      if(res.status !== '200') return this.$message.error('获取公告列表失败')
      console.log(res)
      this.anounceList = res.data.anounceList
      this.total = res.data.total
    },
    // 页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getAnounceList()
    },
    async showAnounceInfoDialog(anounce_id) {
      const { data: res } = await this.$http.post('/anounce/selectAnounceById', stringify({ anounce_id: anounce_id }))
      if( res.status !== '200' ) return this.$message.error('获取公告详细信息失败') 
      this.anounceInfo = res.data
      this.anounceInfoDialogVisible = true
    }
  }

}
</script>

<style lang="less" scoped>
.el-carousel__item{
  border-radius: 30px;
  img{
    width: 100%;
    height: 100%;
  }
}
.is-always-shadow{
  margin: 0 !important;
}
.el-table__row:hover > td {
    background-color: #ffffff !important;

}

.el-table__row--striped:hover > td {
    background-color: #ffffff !important;

}
</style>
