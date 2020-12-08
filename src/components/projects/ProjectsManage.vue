<template>
  <div style="margin-top: 25px">
    <el-button type="primary" size="medium" @click="addProjectDialogVisible = true">录入项目</el-button>
    <el-button type="danger" size="medium" :disabled="selectionList.length === 0">删除项目</el-button>
    <download-excel :data="selectionList" :fields="excelFields" style="display: inline; margin-left: 10px;">
      <el-button type="warning" size="medium" :disabled="selectionList.length === 0">导出信息</el-button>
    </download-excel>

    <el-table :data="projectsList" style="width: 100%; margin-top: 15px;" border
      @selection-change="selectionChange" v-loading="isLoading"
      :header-cell-style="{background: '#f5f7fa'}">
      <!-- 序号列 -->
      <el-table-column type="index" label="#" align="center" fixed></el-table-column>
      <!-- 多选列 -->
      <el-table-column type="selection" align="center" fixed></el-table-column>
      <el-table-column prop="project_name" label="项目名称" width="300px" align="center" fixed>
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目名称</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.project_name"
            placeholder="请输入" @change="QueryProjectList">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="project_id" label="项目编号" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目编号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.project_id"
            placeholder="请输入" @change="QueryProjectList">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="department_name" label="归属单位" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">归属单位</div>
          <el-select class="columnInput" v-model="queryInfo.department_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
              :value="item.department_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="sc_name" label="学科门类" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">学科门类</div>
          <el-select class="columnInput" v-model="queryInfo.sc_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name" :value="item.sc_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="subject_name" label="一级学科" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">一级学科</div>
          <el-select class="columnInput" v-model="queryInfo.subject_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name"
              :value="item.subject_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="nature_name" label="项目性质" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目性质</div>
          <el-select class="columnInput" v-model="queryInfo.nature_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in natureList" :key="item.nature_id" :label="item.nature_name"
              :value="item.nature_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="level_name" label="项目级别" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目级别</div>
          <el-select class="columnInput" v-model="queryInfo.level_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in levelList" :key="item.level_id" :label="item.level_name"
              :value="item.level_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="status_name" label="项目状态" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">项目状态</div>
          <el-select class="columnInput" v-model="queryInfo.status_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in statusList" :key="item.status_id" :label="item.status_name"
              :value="item.status_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="sd_name" label="来源单位" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">来源单位</div>
          <el-select class="columnInput" v-model="queryInfo.sd_id"  size="mini" @change="QueryProjectList"
            collapse-tags placeholder="请选择" clearable>
            <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
              :value="item.department_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="approval_number" label="批准文号" width="200px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">批准文号</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.approval_number"
            placeholder="请输入" @change="QueryProjectList">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="start_time" label="立项日期" width="230px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">立项日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini"
            v-model="start_time" type="daterange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" value-format="yyyy-MM-dd" @change="QueryProjectList">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column prop="plan_time" label="计划完成日期" width="230px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">计划完成日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini"
            v-model="plan_time" type="daterange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" value-format="yyyy-MM-dd" @change="QueryProjectList">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column prop="complete_time" label="结项日期" width="230px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">结项日期</div>
          <el-date-picker class="columnInput" style="width: 200px; padding-right: 0;" size="mini"
            v-model="complete_time" type="daterange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期" value-format="yyyy-MM-dd" @change="QueryProjectList">
          </el-date-picker>
        </template>
      </el-table-column>
      <el-table-column prop="outlay" label="合同经费" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">合同经费</div>
          <el-input class="columnInput" size="mini" clearable v-model="queryInfo.outlay"
            placeholder="请输入" @change="QueryProjectList">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="ct_name" label="结题形式" width="150px" align="center">
        <template slot="header" slot-scope="scope">{{scope.haha}}
          <div style="line-height: 14px;">结题形式</div>
          <el-select class="columnInput" v-model="queryInfo.ct_id"  size="mini"
            collapse-tags placeholder="请选择" clearable @change="QueryProjectList">
            <el-option v-for="item in ctList" :key="item.ct_id" :label="item.ct_name" :value="item.ct_id">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="120" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditProjectDialog(scope.row.project_id)"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteProjectById(scope.row.project_id)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pageNum"
      :page-sizes="[1, 2, 5, 10]"
      :page-size="queryInfo.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>

    <!-- 录入项目对话框 -->
    <el-dialog title="提交项目申请" :visible.sync="addProjectDialogVisible" width="60%" @closed="addProjectDialogClosed">
      <el-form label-width="130px" :model="addForm" :rules="addFormRules" ref="addFormRef" size="mini">
        <el-row>
          <el-form-item label="项目名称：" prop="project_name">
            <el-input v-model="addForm.project_name"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="项目编号：" prop="project_id">
            <el-input v-model="addForm.project_id"></el-input>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="所在单位：" prop="department_id">
            <el-select v-model="addForm.department_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
                :value="item.department_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="负责人：" prop="leader_id">
            <el-select v-model="addForm.leader_id" remote placeholder="请输入负责人姓名" :remote-method="getLeaderList" filterable
              :loading="loadingLeaderList" style="width: 100%;">
              <el-option v-for="item in leaderList" :key="item.user_id" :value="item.user_id"
                :label="item.user_name">
              </el-option>
            </el-select>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="成果归属单位：" prop="aod_id">
            <el-select v-model="addForm.aod_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
                :value="item.department_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="学科门类：" prop="sc_id">
            <el-select v-model="addForm.sc_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name"
                :value="item.sc_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="一级学科：" prop="subject_id">
            <el-select v-model="addForm.subject_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name"
                :value="item.subject_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="项目性质：" prop="nature_id">
            <el-select v-model="addForm.nature_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in natureList" :key="item.nature_id" :label="item.nature_name"
                :value="item.nature_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="项目级别：" prop="level_id">
            <el-select v-model="addForm.level_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in levelList" :key="item.level_id" :label="item.level_name"
                :value="item.level_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="项目状态：" prop="status_id">
            <el-select v-model="addForm.status_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in statusList" :key="item.status_id" :label="item.status_name"
                :value="item.status_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="来源单位：" prop="sd_id">
            <el-select v-model="addForm.sd_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
                :value="item.department_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="成果形式：" prop="at_id">
            <el-select v-model="addForm.at_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in atList" :key="item.at_id" :label="item.at_name"
                :value="item.at_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="批准文号：" prop="approval_number">
            <el-input v-model="addForm.approval_number"></el-input>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="立项日期：" prop="start_time">
            <el-date-picker v-model="addForm.start_time" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
              style="width: 100%"></el-date-picker>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="计划完成日期：" prop="plan_time">
            <el-date-picker v-model="addForm.plan_time" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
              style="width: 100%"></el-date-picker>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="结项日期：" prop="complete_time">
            <el-date-picker v-model="addForm.complete_time" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
              style="width: 100%"></el-date-picker>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="合同经费：" prop="outlay">
            <el-input v-model="addForm.outlay"></el-input>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="结题形式：" prop="ct_id">
            <el-select v-model="addForm.ct_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in ctList" :key="item.ct_id" :label="item.ct_name"
                :value="item.ct_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-form-item label="团队成员：" prop="members">
            <el-button type="primary" size="mini" icon="el-icon-plus" @click="addMemberDialogVisible = true">添加成员</el-button>
          </el-form-item>
        </el-row>
        <el-row>
          <el-table :data="memberList" style="width: 100%; margin-top: 15px;" :header-cell-style="{background: '#f5f7fa'}"
            size="mini" border>
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column prop="member_info.user_name" label="成员姓名"></el-table-column>
            <el-table-column prop="member_type" label="成员类型"></el-table-column>
            <el-table-column prop="member_role" label="成员角色"></el-table-column>
            <el-table-column prop="member_department" label="归属单位"></el-table-column>
            <el-table-column label="操作" width="180px">
              <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditMemberDialog(scope)">编辑</el-button>
                <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteMember(scope)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addProject">确 定</el-button>
        <el-button @click="addProjectDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <!-- 添加项目成员对话框 -->
    <el-dialog title="添加团队成员" :visible.sync="addMemberDialogVisible" width="40%" @closed="addMemberDialogClosed">
      <el-form :model="addMemberForm" :rules="addMemberFormRules" ref="addMemberFormRef" label-width="100px">
        <el-form-item label="成员姓名：" prop="member_info">
          <el-select v-model="addMemberForm.member_info" remote placeholder="请输入成员姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="addMemberSelectChanged">
            <el-option v-for="item in leaderList" :key="item.user_id" :value="JSON.stringify(item)" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="成员类型：" prop="member_type">
          <el-input v-model="addMemberForm.member_type" disabled></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="member_department">
          <el-input v-model="addMemberForm.member_department" disabled></el-input>
        </el-form-item>
        <el-form-item label="成员角色：" prop="member_role">
          <el-select v-model="addMemberForm.member_role" placeholder="请选择成员角色" style="width: 100%;">
            <el-option value="负责人"></el-option>
            <el-option value="成员"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addMemberDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addMember">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑团队成员信息对话框 -->
    <el-dialog title="编辑团队成员" :visible.sync="editMemberDialogVisible" width="40%">
      <el-form :model="editMemberForm" :rules="editMemberFormRules" ref="editMemberFormRef" label-width="100px">
        <el-form-item label="成员姓名：" prop="member_info">
          <el-select v-model="editMemberForm.member_info" remote placeholder="请输入成员姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="editMemberSelectChanged">
            <el-option v-for="item in leaderList" :key="item.user_id" :value="JSON.stringify(item)" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="成员类型：" prop="member_type">
          <el-input v-model="editMemberForm.member_type" disabled></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="member_department">
          <el-input v-model="editMemberForm.member_department" disabled></el-input>
        </el-form-item>
        <el-form-item label="成员角色：" prop="member_role">
          <el-select v-model="editMemberForm.member_role" placeholder="请选择成员角色" style="width: 100%;">
            <el-option value="负责人"></el-option>
            <el-option value="成员"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editMemberDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editMember">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑项目对话框 -->
    <el-dialog title="编辑项目信息" :visible.sync="editProjectDialogVisible" width="60%" @closed="editProjectDialogClosed">
      <el-form label-width="130px" :model="editForm" :rules="addFormRules" ref="editFormRef" size="mini">
        <el-row>
          <el-form-item label="项目名称：" prop="project_name">
            <el-input v-model="editForm.project_name"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="项目编号：" prop="project_id">
            <el-input v-model="editForm.project_id"></el-input>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="所在单位：" prop="department_id">
            <el-select v-model="editForm.department_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
                :value="item.department_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="负责人：" prop="leader_id">
            <el-select v-model="editForm.leader_id" remote placeholder="请输入负责人姓名" :remote-method="getLeaderList" filterable
              :loading="loadingLeaderList" style="width: 100%;">
              <el-option v-for="item in leaderList" :key="item.user_id" :value="item.user_id"
                :label="item.user_name">
              </el-option>
            </el-select>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="成果归属单位：" prop="aod_id">
            <el-select v-model="editForm.aod_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
                :value="item.department_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="学科门类：" prop="sc_id">
            <el-select v-model="editForm.sc_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in scList" :key="item.sc_id" :label="item.sc_name"
                :value="item.sc_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="一级学科：" prop="subject_id">
            <el-select v-model="editForm.subject_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in subjectList" :key="item.subject_id" :label="item.subject_name"
                :value="item.subject_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="项目性质：" prop="nature_id">
            <el-select v-model="editForm.nature_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in natureList" :key="item.nature_id" :label="item.nature_name"
                :value="item.nature_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="项目级别：" prop="level_id">
            <el-select v-model="editForm.level_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in levelList" :key="item.level_id" :label="item.level_name"
                :value="item.level_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="项目状态：" prop="status_id">
            <el-select v-model="editForm.status_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in statusList" :key="item.status_id" :label="item.status_name"
                :value="item.status_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="来源单位：" prop="sd_id">
            <el-select v-model="editForm.sd_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in departmentList" :key="item.department_id" :label="item.department_name"
                :value="item.department_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="成果形式：" prop="at_id">
            <el-select v-model="editForm.at_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in atList" :key="item.at_id" :label="item.at_name"
                :value="item.at_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="批准文号：" prop="approval_number">
            <el-input v-model="editForm.approval_number"></el-input>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="立项日期：" prop="start_time">
            <el-date-picker v-model="editForm.start_time" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
              style="width: 100%"></el-date-picker>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="计划完成日期：" prop="plan_time">
            <el-date-picker v-model="editForm.plan_time" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
              style="width: 100%"></el-date-picker>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="结项日期：" prop="complete_time">
            <el-date-picker v-model="editForm.complete_time" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
              style="width: 100%"></el-date-picker>
          </el-form-item></el-col>
          <el-col :span="12"><el-form-item label="合同经费：" prop="outlay">
            <el-input v-model="editForm.outlay"></el-input>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-form-item label="结题形式：" prop="ct_id">
            <el-select v-model="editForm.ct_id" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in ctList" :key="item.ct_id" :label="item.ct_name"
                :value="item.ct_id">
              </el-option>
            </el-select>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-form-item label="团队成员：" prop="members">
            <el-button type="primary" size="mini" icon="el-icon-plus" @click="addMemberDialogVisible = true">添加成员</el-button>
          </el-form-item>
        </el-row>
        <el-row>
          <el-table :data="editMemberList" style="width: 100%; margin-top: 15px;" :header-cell-style="{background: '#f5f7fa'}"
            size="mini" border>
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column prop="name" label="成员姓名"></el-table-column>
            <el-table-column prop="role_name" label="成员类型"></el-table-column>
            <el-table-column prop="team_role" label="成员角色"></el-table-column>
            <el-table-column prop="department_name" label="归属单位"></el-table-column>
            <el-table-column label="操作" width="180px">
              <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditMemberDialog(scope)">编辑</el-button>
                <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteEditMember(scope)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editProjectDialogVisible = false">确 定</el-button>
        <el-button @click="editProjectDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>

    <!-- 编辑项目对话框中添加成员对话框 -->
    <el-dialog title="添加团队成员" :visible.sync="addMemberDialogVisible" width="40%" @closed="addMemberDialogClosed">
      <el-form :model="addMemberForm" :rules="addMemberFormRules" ref="addMemberFormRef" label-width="100px">
        <el-form-item label="成员姓名：" prop="member_info">
          <el-select v-model="addMemberForm.member_info" remote placeholder="请输入成员姓名" :remote-method="getLeaderList" filterable
            :loading="loadingLeaderList" style="width: 100%;" @change="addMemberSelectChanged">
            <el-option v-for="item in leaderList" :key="item.user_id" :value="JSON.stringify(item)" :label="item.user_name">
              <span style="float: left">{{ item.user_name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.user_id }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="成员类型：" prop="member_type">
          <el-input v-model="addMemberForm.member_type" disabled></el-input>
        </el-form-item>
        <el-form-item label="归属单位：" prop="member_department">
          <el-input v-model="addMemberForm.member_department" disabled></el-input>
        </el-form-item>
        <el-form-item label="成员角色：" prop="member_role">
          <el-select v-model="addMemberForm.member_role" placeholder="请选择成员角色" style="width: 100%;">
            <el-option value="负责人"></el-option>
            <el-option value="成员"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addMemberDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addEditMember">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { parse } from 'qs'
export default {
  data() {
    return{
      // 标签页默认打开
      tabActiveName: 'first',
      // 科研项目信息列表对象
      projectsList: [],
      // 查询表单
      queryInfo: {
        project_name: '',
        project_id: '',
        department_id: '',
        sc_id: '',
        subject_id: '',
        nature_id: '',
        level_id: '',
        status_id: '',
        sd_id: '',
        approval_number: '',
        start_time_start: '',
        start_time_end: '',
        plan_time_start: '',
        plan_time_end: '',
        complete_time_start: '',
        complete_time_end: '',
        outlay: '',
        ct_id: '',
        pageNum: 1,
        pageSize: 5,
      },
      // 总的数据条数
      total: 2,
      // 查询条件：立项日期
      start_time: null,
      // 查询条件：计划完成日期
      plan_time: null,
      // 查询条件：结项日期
      complete_time: null,
      // 单位 id:name 对象
      departmentObj: {},
      // 单位对象列表
      departmentList: [],
      // 学科门类 id:name 对象
      scObj: {},
      // 学科门类列表
      scList: [],
      // 一级学科 id:name 对象
      subjectObj: {},
      // 一级学科列表
      subjectList: [],
      // 项目性质 id:name 对象
      natureObj: {},
      // 项目性质列表
      natureList: [],
      // 项目级别 id:name 对象
      levelObj: {},
      // 项目级别列表
      levelList: [],
      // 项目状态 id:name 对象
      statusObj: {},
      // 项目状态列表
      statusList: [],
      // 结题形式 id:name 对象
      ctObj: {},
      // 结题形式列表
      ctList: [],
      // 成果形式列表
      atList: [],
      // 表格是否正在加载
      isLoading: false,
      // 控制录入项目对话框显示与隐藏变量
      addProjectDialogVisible: false,
      // 录入项目表单
      addForm: {
        project_name: '',
        project_id: '',
        department_id: '',
        leader_id: '',
        aod_id: '',
        sc_id: '',
        subject_id: '',
        nature_id: '',
        level_id: '',
        status_id: '',
        sd_id: '',
        at_id: '',
        approval_number: '',
        start_time: '',
        plan_time: '',
        complete_time: '',
        outlay: '',
        ct_id: ''
      },
      // 录入项目表单验证规则对象
      addFormRules: {
        project_name: [{ required: true, message: '请输入项目名称', trigger: 'blur' }],
        project_id: [{ required: true, message: '请输入项目编号', trigger: 'blur' }],
        department_id: [{ required: true, message: '请选择所在单位', trigger: 'change' }],
        leader_id: [{ required: true, message: '请输入负责人姓名', trigger: 'change' }],
        aod_id: [{ required: true, message: '请选择成果归属单位', trigger: 'change' }],
        sc_id: [{ required: true, message: '请选择学科门类', trigger: 'change' }],
        subject_id: [{ required: true, message: '请选择一级学科', trigger: 'change' }],
        nature_id: [{ required: true, message: '请选择项目性质', trigger: 'change' }],
        level_id: [{ required: true, message: '请选择项目级别', trigger: 'change' }],
        status_id: [{ required: true, message: '请选择项目状态', trigger: 'change' }],
        sd_id: [{ required: true, message: '请选择来源单位', trigger: 'change' }],
        at_id: [{ required: true, message: '请选择成果形式', trigger: 'change' }],
        approval_number: [{ required: true, message: '请输入批准文号', trigger: 'blur' }],
        start_time: [{ required: true, message: '请选择立项日期', trigger: 'change' }],
        plan_time: [{ required: true, message: '请选择计划完成日期', trigger: 'change' }],
        outlay: [{ required: true, message: '请输入合同经费', trigger: 'blur' }],
        ct_id: [{ required: true, message: '请选择结题形式', trigger: 'change' }],
      },
      // 是否正在加载负责人列表
      loadingLeaderList: false,
      // 待选负责人信息列表
      leaderList: [],
      // 团队成员列表
      memberList: [],
      // 添加团队成员对话框显示与隐藏变量
      addMemberDialogVisible: false,
      // 添加团队成员信息表单
      addMemberForm: {
        member_info: '',
        member_type: '',
        member_role: '',
        member_department: ''
      },
      // 添加团队成员信息表单验证对象
      addMemberFormRules: {
        member_info: [{ required: true, message: '请输入成员姓名', trigger: 'change'}],
        member_role: [{ required: true, message: '请选择成员角色', trigger: 'change' }]
      },
      // 编辑团队成员对话框显示与隐藏控制变量
      editMemberDialogVisible: false,
      // 编辑团队成员信息表单
      editMemberForm: {},
      // 编辑团队成员信息表单验证对象
      editMemberFormRules: {
        member_info: [{ required: true, message: '请输入成员姓名', trigger: 'change'}],
        member_type: [{ required: true, message: '请选择成员类型', trigger: 'change' }],
        member_role: [{ required: true, message: '请选择成员角色', trigger: 'change' }],
        member_department: [{ required: true, message: '请选择归属单位', trigger: 'change' }]
      },
      // 当前正在被编辑的团队成员信息在列表中所处的位置
      editMemberIndex: '',
      // 输出为excel的格式
      excelFields: {
        '项目名称': 'project_name',
        '项目编号': 'project_id',
        '归属单位': 'department_name',
        '学科门类': 'sc_name',
        '一级学科': 'subject_name',
        '项目性质': 'nature_name',
        '项目级别': 'level_name',
        '项目状态': 'status_name',
        '来源单位': 'sd_name',
        '批准文号': 'approval_number',
        '立项日期': 'start_time',
        '计划完成日期': 'plan_time',
        '结项日期': 'complete_time',
        '合同经费': 'outlay',
        '结题形式': 'ct_name'
      },
      // 多选选项列表
      selectionList: [],
      // 编辑项目信息对话框显示与隐藏控制变量
      editProjectDialogVisible: false,
      // 编辑项目信息表单对象
      editForm: {},
      // 编辑项目表单中的成员列表
      editMemberList: [],
      addEditMemberDialogVisible: false
    }
  },
  async created() {
    await this.getProjectData()
    this.getProjectList()
  },
  methods: {
    // 获取科研项目页面所需的全部数据项，构造id:name字典
    async getProjectData() {
      this.isLoading = true
      // 获取单位列表
      const { data: res1 } = await this.$http.post('/department/findAllDepartment')
      if(res1.status !== '200') return this.$message.error('获取单位数据失败')
      this.departmentList = res1.data
      // 构造单位 id:name 对象
      this.departmentList.forEach(item => this.departmentObj[item.department_id] = item.department_name)
      // 获取学科门类列表
      const { data: res2 } = await this.$http.post('/category/findAllSubjectCategory')
      if(res2.status !== '200') return this.$message.error('获取学科门类数据失败')
      this.scList = res2.data
      // 构造学科门类 id:name 对象
      this.scList.forEach(item => this.scObj[item.sc_id] = item.sc_name)
      // 获取一级学科列表
      const { data: res3 } = await this.$http.post('/subject/findAllSubject')
      if(res3.status !== '200') return this.$message.error('获取一级学科列表失败')
      this.subjectList = res3.data
      // 构造一级学科 id:name 对象
      this.subjectList.forEach(item => this.subjectObj[item.subject_id] = item.subject_name)
      // 获取项目性质列表
      const { data: res4 } = await this.$http.post('/nature/findAllNature')
      if(res4.status !== '200') return this.$message.error('获取项目性质列表失败')
      this.natureList = res4.data
      // 构造项目性质 id:name 对象
      this.natureList.forEach(item => this.natureObj[item.nature_id] = item.nature_name)
      // 获取项目级别列表
      const { data: res5 } = await this.$http.post('/level/findAllLevel')
      if(res5.status !== '200') return this.$message.error('获取项目级别列表失败')
      this.levelList = res5.data
      // 构造项目级别 id:name 对象
      this.levelList.forEach(item => this.levelObj[item.level_id] = item.level_name)
      // 获取项目状态列表
      const { data: res6 } = await this.$http.post('/status/findAllStatus')
      if(res6.status !== '200') return this.$message.error('获取项目状态列表失败')
      this.statusList = res6.data
      // 构造项目状态 id:name 对象
      this.statusList.forEach(item => this.statusObj[item.status_id] = item.status_name)
      // 获取结题形式列表
      const { data: res7 } = await this.$http.post('/conclusionType/findAllConclusionType')
      console.log(res7)
      if(res7.status !== '200') return this.$message.error('获取结题形式列表失败')
      this.ctList = res7.data
      console.log(this.ctList)
      // 构造结题形式 id:name 对象
      this.ctList.forEach(item => this.ctObj[item.ct_id]=item.ct_name)
      // 获取成果形式列表
      const { data: res8 } = await this.$http.post('/achievementType/findAllAchievementType')
      if(res8.status !== '200') return this.$message.error('获取成果形式列表失败')
      this.atList = res8.data
      this.isLoading = false
    },
    // 获取科研项目列表
    async getProjectList() {
      this.isLoading = true
      // 通过 post 请求获取科研项目列表
      const { data: res } = await this.$http.post('/project/selectProjectByCondition', this.$qs.stringify(this.queryInfo))
      if( res.status === '404' ) {
        return this.$router.push('/home')
      }
      this.total=res.data[0].total
      this.projectsList = res.data[1]
      // 通过 id:name 对象处理科研项目列表，为其加上各name属性，截取时间日期
      this.projectsList.forEach(item => {
        item.department_name = this.departmentObj[item.department_id]
        item.sc_name = this.scObj[item.sc_id]
        item.subject_name = this.subjectObj[item.subject_id]
        item.nature_name = this.natureObj[item.nature_id]
        item.level_name = this.levelObj[item.level_id]
        item.status_name = this.statusObj[item.status_id]
        item.sd_name = this.departmentObj[item.sd_id]
        item.start_time = item.start_time.substring(0, 10)
        item.plan_time = item.plan_time.substring(0, 10)
        item.complete_time = item.complete_time.substring(0, 10)
        item.ct_name = this.ctObj[item.ct_id]
      })
      console.log(this.projectsList)
      console.log(this.subjectObj)
      console.log(this.scObj)
      this.isLoading = false
    },
    // 多选框条件发生变化
    selectionChange(val) {
      this.selectionList = val
      console.log('selection change!!!')
    },
    // 根据查询条件获取项目列表
    QueryProjectList() {
      if( this.start_time !== null ) {
        this.queryInfo.start_time_start = this.start_time[0] + ' 00:00:00'
        this.queryInfo.start_time_end = this.start_time[1] + ' 00:00:00'
      } else this.queryInfo.start_time_start = this.queryInfo.start_time_end = ''
      if( this.plan_time !== null ) {
        this.queryInfo.plan_time_start = this.plan_time[0] + ' 00:00:00'
        this.queryInfo.plan_time_end = this.plan_time[1] + ' 00:00:00'
      } else this.queryInfo.plan_time_start = this.queryInfo.plan_time_end = ''
      if( this.complete_time !== null ) {
        this.queryInfo.complete_time_start = this.complete_time[0] + ' 00:00:00'
        this.queryInfo.complete_time_end = this.complete_time[1] + ' 00:00:00'
      } else this.queryInfo.complete_time_start = this.queryInfo.complete_time_end = ''
      console.log(this.queryInfo)
      this.queryInfo.pageNum = 1
      this.getProjectList()
    },
    // 列表页面大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val
      this.queryInfo.pageNum = 1
      this.getProjectList()
    },
    // 列表页数发生变化
    handleCurrentChange(val) {
      this.queryInfo.pageNum = val
      this.getProjectList()
    },
    // 点击按钮，删除项目
    async deleteProjectById(projectId) {
      const res = await this.$confirm('此操作将永久删除该项目，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      const { data: res2 } = await this.$http.post('/project/delProject', this.$qs.stringify({ project_id: projectId }))
      if(res2.status !== '200') return this.$message.error('删除项目失败')
      this.$message.success('删除项目成功')
      this.getProjectList()
    },
    // 根据用户输入，模糊匹配负责人列表
    async getLeaderList(query) {
      if(query === null || query === '') return
      this.loadingLeaderList = true
      const { data: res } = await this.$http.post('/user/findNameId', this.$qs.stringify({ user_name: query }))
      this.leaderList = res.data
      this.loadingLeaderList = false
    },
    // 添加成员选择发生变化
    async addMemberSelectChanged() {
      this.addMemberForm.member_info = JSON.parse(this.addMemberForm.member_info)
      this.addMemberForm.member_department = this.departmentObj[this.addMemberForm.member_info.department_id]
      const { data: res } = await this.$http.post('/role/selectRoleName', this.$qs.stringify({ role_id: this.addMemberForm.member_info.role_id }))
      if( res.status !== '200' ) return this.$message.error('获取成员类型失败')
      console.log(res)
      this.addMemberForm.member_type = res.data
      console.log(this.addMemberForm)
    },
    // 点击确定，添加团队成员
    addMember() {
      this.$refs.addMemberFormRef.validate(valid => {
        if(!valid) return
        var newMember = JSON.parse(JSON.stringify(this.addMemberForm))
        this.memberList.push(newMember)
        this.$message.success('成功添加团队成员')
        console.log(this.memberList)
        this.addMemberDialogVisible = false
      })
    },
    // 关闭添加成员对话框，清空表单
    addMemberDialogClosed() {
      console.log('clear')
      this.addMemberForm.member_type = this.addMemberForm.member_role = ''
      this.addMemberForm.member_info = this.addMemberForm.member_department = {}
      this.$refs.addMemberFormRef.resetFields() 
      console.log(this.addMemberForm.member_info)
    },
    // 点击删除按钮，删除团队成员
    async deleteMember(scope) {
      const res = await this.$confirm('此操作将永久删除该成员，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      this.memberList.splice(scope.$index, 1)
      this.$message.success('成功删除了团队成员')
    },
    // 关闭录入项目对话框，清空表单和团队成员列表
    addProjectDialogClosed() {
      this.$refs.addFormRef.resetFields()
      this.memberList = []
    },
    // 编辑团队成员时，选择的成员发生变化
    async editMemberSelectChanged() {
      this.editMemberForm.member_info = JSON.parse(this.editMemberForm.member_info)
      this.editMemberForm.member_department = this.departmentObj[this.editMemberForm.member_info.department_id]
      const { data: res } = await this.$http.post('/role/selectRoleName', this.$qs.stringify({ role_id: this.editMemberForm.member_info.role_id }))
      if( res.status !== '200' ) return this.$message.error('获取成员类型失败')
      this.editMemberForm.member_type = res.data
    },
    // 点击编辑按钮，显示编辑团队成员对话框
    showEditMemberDialog(scope) {
      this.editMemberIndex = JSON.parse(JSON.stringify(scope.$index))
      this.editMemberForm = JSON.parse(JSON.stringify(scope.row))
      console.log(this.editMemberForm)
      this.editMemberDialogVisible = true
    },
    // 点击确定，编辑团队成员信息
    editMember() {
      this.$refs.editMemberFormRef.validate(valid => {
        if(!valid) return
        this.memberList[this.editMemberIndex] = JSON.parse(JSON.stringify(this.editMemberForm))
        this.memberList = JSON.parse(JSON.stringify(this.memberList))
        this.$message.success('编辑团队成员成功')
        this.editMemberDialogVisible = false
      })
    },
    // 点击确定，添加项目到待审核项目列表中
    addProject() {
      this.$refs.addFormRef.validate(async valid => {
        if(!valid) return
        var postObj = JSON.parse(JSON.stringify(this.addForm))
        postObj.user_id = []
        postObj.team_user = []
        this.memberList.forEach(item => {
          postObj.user_id.push(item.member_info.user_id)
          postObj.team_user.push(JSON.parse(JSON.stringify(item.member_role)))
        })
        console.log(this.memberList)
        console.log(postObj)
        const { data: res } = await this.$http.get(`/projectExamine/addProjectExamine?${this.$qs.stringify(this.addForm)}&user_id=${postObj.user_id}&team_user=${postObj.team_user}`)
        if( res.status !== '200' ) return this.$message.error('申请项目失败')
        this.$message.success('申请项目成功')
        this.QueryProjectList()
        this.addProjectDialogVisible = false
      })
    },
    // 获取编辑项目信息中成员名单的角色名称
    async getEditMemberListRoleName() {
      for(var i = 0; i < this.editMemberList.length; i++) {
        const { data: res } = await this.$http.post('/role/selectRoleName', this.$qs.stringify({ role_id: this.editMemberList[i].role_id }))
        if( res.status !== '200' ) return this.$message.error('获取角色名称失败')
        this.editMemberList[i].role_name = res.data
      }
    },
    // 获取编辑项目信息中的成员名单
    async getEditMemberList(project_id) {
      const { data: res2 } = await this.$http.post('/team/selectProjectTeam', this.$qs.stringify({ project_id: project_id }))
      if( res2.status !== '200' ) return this.$message.error('获取成员信息失败')
      this.editMemberList = res2.data
      this.editMemberList.forEach(item => item.department_name = this.departmentObj[item.department_id])
      await this.getEditMemberListRoleName()
    },
    // 点击编辑按钮，显示编辑项目信息对话框
    async showEditProjectDialog(project_id) { 
      const { data: res } = await this.$http.post('/project/findProjectById', this.$qs.stringify({ project_id: project_id }))
      if( res.status !== '200' ) return this.$message.error('获取项目信息失败')
      this.editForm = res.data
      await this.getEditMemberList(project_id)
      this.editProjectDialogVisible = true
    },
    // 关闭编辑项目信息对话框，情况编辑项目信息表单对象
    editProjectDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    // 点击确定，添加成员
    async addEditMember() {
      const { data: res } = await this.$http.post('/team/addTeamUser', this.$qs.stringify({
        project_id: this.editForm.project_id,
        user_id: this.addMemberForm.member_info.user_id,
        team_role: this.addMemberForm.member_role
      }))
      if(res.status !== '200') return this.$message.error('添加团队成员失败')
      console.log(res)
      this.$message.success('添加团队成员成功')
      await this.getEditMemberList(this.editForm.project_id)
      this.editMemberList = JSON.parse(JSON.stringify(this.editMemberList))
      this.addMemberDialogVisible = false
    },
    // 点击确定，删除成员
    async deleteEditMember(scope) {
      const res = await this.$confirm('此操作将永久删除该成员，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(res === 'cancel') return this.$message.info('取消了本次操作')
      console.log(scope)
    }
  }
}
</script>

<style lang="less" scoped>

</style>