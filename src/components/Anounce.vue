<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>系统公告</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-button type="primary" icon="el-icon-plus" size="medium" @click="addDialogVisible = true">添加公告</el-button>
      <el-table :header-cell-style="{background: '#f5f7fa'}" :data="anounceList" style="width: 100%;margin-top: 15px">
        <el-table-column type="expand">
          <template slot-scope="scope">
            {{scope.row.anounce_text}}
          </template>
        </el-table-column>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="公告标题" prop="anounce_title" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column label="发表时间" prop="anounce_date"></el-table-column>
        <el-table-column label="作者" prop="user_name"></el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.anounce_id)"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteAnounce(scope.row.anounce_id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-card>

    <el-dialog title="添加公告" :visible.sync="addDialogVisible" width="60%" @closed="addDialogClosed">
      <el-form :model="addForm" :rules="formRules" ref="addFormRef" label-width="100px" label-position="top">
        <el-form-item label="公告标题" prop="anounce_title">
          <el-input v-model="addForm.anounce_title"></el-input>
        </el-form-item>
        <el-form-item label="公告内容" prop="anounce_text">
          <el-input v-model="addForm.anounce_text" type="textarea" :rows="5"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAnounce">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改公告" :visible.sync="editDialogVisible" width="60%" @closed="editDialogClosed">
      <el-form :model="editForm" :rules="formRules" ref="editFormRef" label-width="100px" label-position="top">
        <el-form-item label="公告标题" prop="anounce_title">
          <el-input v-model="editForm.anounce_title"></el-input>
        </el-form-item>
        <el-form-item label="公告内容" prop="anounce_text">
          <el-input v-model="editForm.anounce_text" type="textarea" :rows="5"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editAnounce">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import { stringify } from 'qs'
export default {
  data() {
    return{
      // 系统公告列表
      anounceList: [],
      queryInfo: {
        pageNum: 1,
        pageSize: 10
      },
      total: 10,
      // 添加表单
      addForm: {
        anounce_title: '',
        anounce_text: ''
      },
      formRules: {
        anounce_title: [{ required: true, message: '请输入公告标题', trigger: 'blur' }]
      },
      addDialogVisible: false,
      editDialogVisible: false,
      editForm: {}
    }
  },
  created() {
    this.getAnounceList()
  },
  methods: {
    async getAnounceList() {
      const { data: res } = await this.$http.post('/anounce/selectAllAnounce', this.queryInfo)
      if( res.status !== '200' ) return this.$message.error('获取公告列表失败')
      this.anounceList = res.data.anounceList
      this.total = res.data.total
    },
    // 页面大小发生变化
    handleSizeChange(val) {
      this.queryInfo.pageSize = val
      this.queryInfo.pageNum = 1
      this.getAnounceList()
    },
    // 页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getAnounceList()
    },
    // 关闭添加公告对话框，清空表单
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    // 点击确定，添加公告
    addAnounce() {
      this.$refs.addFormRef.validate(async valid => {
        if(!valid) return
        const { data: res } = await this.$http.post('/anounce/addAnounce', stringify(this.addForm))
        if( res.status !== '200' ) return this.$message.error('添加公告失败')
        this.$message.success('添加公告成功')
        this.getAnounceList()
        this.addDialogVisible = false
      })
    },
    async deleteAnounce(anounce_id) {
      const res = await this.$confirm('此操作将永久删除该公告，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/anounce/delAnounceById', this.$qs.stringify({ anounce_id: anounce_id }))
      if(res2.status !== '200') return this.$message.error('删除公告失败')
      this.$message.success('删除公告成功')
      this.getAnounceList()
    },
    async showEditDialog(anounce_id) {
      const { data: res } = await this.$http.post('/anounce/selectAnounceById', stringify({ anounce_id: anounce_id }))
      if( res.status !== '200' ) return this.$message.error('获取公告详细信息失败')
      this.editForm = res.data
      this.editDialogVisible = true
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    editAnounce() {
      this.$refs.editFormRef.validate(async valid => {
        if(!valid) return
        const { data: res } = await this.$http.post('/anounce/updateAnounce', stringify(this.editForm))
        if( res.status !== '200' ) return this.$message.error('编辑公告失败')
        this.$message.success('编辑公告成功')
        this.getAnounceList()
        this.editDialogVisible = false
      })
    }
  }
}
</script>

<style lang="less" scoped>

</style>