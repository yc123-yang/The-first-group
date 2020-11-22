<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>科研成果</el-breadcrumb-item>
      <el-breadcrumb-item>著作成果</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图 -->
    <el-card>
      <el-row>
        <el-button
          type="primary"
          size="medium"
          @click="addBooksDialogVisible = true"
          >录入著作</el-button
        >
        <el-button
          type="danger"
          size="medium"
          :disabled="selection.length === 0"
          >删除著作</el-button
        >
        <el-button
          type="warning"
          size="medium"
          :disabled="selection.length === 0"
          >导出信息</el-button
        >
      </el-row>
      <el-row>
        <el-table
          :data="paperList"
          style="width: 100%"
          max-height="250"
          border
          @selection-change="selectionChange"
          :header-cell-style="{ background: '#f5f7fa' }"
        >
          <!-- 序号列 -->
          <el-table-column
            type="index"
            label="#"
            align="center"
            fixed
          ></el-table-column>
          <!-- 多选列 -->
          <el-table-column
            type="selection"
            align="center"
            fixed
          ></el-table-column>
          <el-table-column
            prop="title"
            label="著作题目"
            width="300px"
            align="center"
            fixed
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">著作题目</div>
              <el-input
                class="columnInput"
                size="mini"
                clearable
                v-model="queryInfo.title"
                placeholder="请输入"
              >
              </el-input>
            </template>
          </el-table-column>
          <el-table-column
            prop="authorName"
            label="著作作者"
            width="150px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">著作作者</div>
              <el-input
                class="columnInput"
                size="mini"
                clearable
                v-model="queryInfo.authorName"
                placeholder="请输入"
              >
              </el-input>
            </template>
          </el-table-column>
          <el-table-column
            prop="publishUnit"
            label="出版单位"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">出版单位</div>
              <el-input
                class="columnInput"
                size="mini"
                clearable
                v-model="queryInfo.publishUnit"
                placeholder="请输入"
              >
              </el-input>
            </template>
          </el-table-column>
          <el-table-column
            prop="date"
            label="发表日期"
            width="230px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">发表日期</div>
              <el-date-picker
                class="columnInput"
                style="width: 200px; padding-right: 0"
                size="mini"
                v-model="queryInfo.date"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </template>
          </el-table-column>

          <el-table-column
            prop="publishLevel"
            label="出版社级别"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">出版社级别</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.publishLevel"
                size="mini"
                placeholder="请选择"
              >
                <el-option
                  v-for="item in publishLevelList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column
            prop="booktype"
            label="著作类别"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">著作类别</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.booktype"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in booktypeList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column
            prop="pubplace"
            label="出版地"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">出版地</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.pubplace"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in pubplaceList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column
            prop="iSBN"
            label="ISBN号"
            width="150px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">ISBN号</div>
              <el-input
                class="columnInput"
                size="mini"
                clearable
                v-model="queryInfo.iSBN"
                placeholder="请输入"
              >
              </el-input>
            </template>
          </el-table-column>

          <el-table-column
            prop="allLeters"
            label="总字数"
            width="150px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">总字数</div>
              <el-input
                class="columnInput"
                size="mini"
                clearable
                v-model="queryInfo.allLeters"
                placeholder="请输入"
              >
              </el-input>
            </template>
          </el-table-column>

          <el-table-column
            prop="translaTion"
            label="是否翻译"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">是否翻译</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.translaTion"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in translaTionList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column
            prop="language"
            label="翻译语种"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">翻译语种</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.language"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in languageList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column
            prop="subjectCate"
            label="学科门类"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">学科门类</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.subjectCate"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in subjectCateList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column
            prop="subject"
            label="一级学科"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">一级学科</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.subject"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in subjectList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column
            prop="attribution"
            label="成果归属"
            width="200px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">成果归属</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.attribution"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in attributionList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column
            prop="source"
            label="项目来源"
            width="250px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">项目来源</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.source"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in sourceList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>

          <el-table-column
            prop="proType"
            label="研究类别"
            width="250px"
            align="center"
          >
            <template slot="header" slot-scope="scope"
              >{{ scope.haha }}
              <div style="line-height: 14px">研究类别</div>
              <el-select
                class="columnInput"
                v-model="queryInfo.proType"
                multiple
                size="mini"
                collapse-tags
                placeholder="请选择"
              >
                <el-option
                  v-for="item in proTypeList"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <!-- 操作列 -->
          <el-table-column
            label="操作"
            align="center"
            width="120"
            fixed="right"
          >
            <template slot-scope="scope"
              >{{ scope.haha }}
              <el-button
                type="primary"
                icon="el-icon-edit"
                size="mini"
              ></el-button>
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>

      <!-- 分页组件 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>

      <!-- 录入著作成果对话框 -->
      <el-dialog
        title="录入著作成果"
        :visible.sync="addBooksDialogVisible"
        width="60%"
        @close="addBooksDialogClosed"
      >
        <el-form
          :model="addForm"
          :rules="addFormRules"
          ref="addFormRef"
          label-width="100px"
          size="mini"
        >
          <el-row :gutter="20">
            <!-- 表单左侧 -->
            <el-col >
              <!-- 著作题目 -->
              <el-form-item label="著作题目:" prop="title">
                <el-input v-model="addForm.title" size="mini"></el-input>
              </el-form-item>
              <!-- 出版单位、出版社级别 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="出版单位:" prop="publishUnit">
                    <el-input
                      v-model="addForm.publishUnit"
                      size="mini"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="出版社级别:" prop="publishLevel">
                    <el-select
                      v-model="addForm.publishLevel"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in publishLevelList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- 著作类别，出版地 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="著作类别:" prop="booktype">
                    <el-select
                      v-model="addForm.booktype"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in booktypeList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="出版地:" prop="pubplace">
                    <el-select
                      v-model="addForm.pubplace"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in pubplaceList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- ISBN、总字数 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="ISBN号:" prop="iSBN">
                    <el-input v-model="addForm.iSBN" size="mini"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="7">
                  <el-form-item label="总字数:" prop="allLeters">
                    <el-input
                      v-model="addForm.allLeters"
                      size="mini"
                    ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <!-- 是否翻译，翻译语种 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="是否翻译:" prop="translaTion">
                    <el-select
                      v-model="addForm.translaTion"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in translaTionList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="翻译语种:" prop="language">
                    <el-select
                      v-model="addForm.language"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in languageList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- 学科门类，发表时间 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="学科门类:" prop="subjectCate">
                    <el-select
                      v-model="addForm.subjectCate"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in subjectCateList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="发表时间:" prop="date">
                    <el-date-picker
                      type="date"
                      placeholder="请选择日期"
                      v-model="addForm.date"
                      style="width: 100%"
                      size="mini"
                    >
                    </el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>

              <!-- 一级学科,成果归属 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="一级学科:" prop="subject">
                    <el-select
                      v-model="addForm.subject"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in subjectList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="归属单位:" prop="attribution">
                    <el-select
                      v-model="addForm.attribution"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in attributionList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <!-- 项目来源,研究类别 -->
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="项目来源:" prop="source">
                    <el-select
                      v-model="addForm.source"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in sourceList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="研究类别:" prop="proType">
                    <el-select
                      v-model="addForm.proType"
                      placeholder="请选择"
                      size="mini"
                      style="width: 100%"
                    >
                      <el-option
                        v-for="item in proTypeList"
                        :label="item"
                        :value="item"
                        :key="item"
                      >
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <!-- 备注 -->
              <el-form-item label="备注:" prop="remark">
                <el-input v-model="addForm.remark" size="mini"></el-input>
              </el-form-item>
              <el-form-item label="作者:" prop="author" size="mini">
                <el-button type="primary" size="mini">添加作者</el-button>
                <el-button
                  type="danger"
                  size="mini"
                  :disabled="authorSelectionList.length === 0"
                >
                  批量删除
                </el-button>
              </el-form-item>
              <el-table
                :data="addForm.authorList"
                style="width: 100%"
                ref="authorTableRef"
                @selection-change="addAuthorSelectionChange"
                size="mini"
                border
                height="250px"
                :header-cell-style="{ background: '#f5f7fa' }"
              >
                <!-- 序号列 -->
                <el-table-column
                  type="index"
                  label="#"
                  align="center"
                ></el-table-column>
                <!-- 多选列 -->
                <el-table-column
                  type="selection"
                  align="center"
                ></el-table-column>
                <el-table-column
                  prop="authorName"
                  label="作者姓名"
                  width="120px"
                  align="center"
                >
                  <template slot="header" slot-scope="scope"
                    >{{ scope.haha }}
                    <div style="line-height: 14px">作者姓名</div>
                    <el-input
                      class="columnInput"
                      size="mini"
                      clearable
                      v-model="authorQueryInfo.authorName"
                      placeholder="请输入"
                    >
                    </el-input>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="authorType"
                  label="成员类型"
                  width="180px"
                  align="center"
                >
                  <template slot="header" slot-scope="scope"
                    >{{ scope.haha }}
                    <div style="line-height: 14px">成员类型</div>
                    <el-select
                      class="columnInput"
                      v-model="authorQueryInfo.authorType"
                      multiple
                      size="mini"
                      collapse-tags
                      placeholder="请选择"
                    >
                      <el-option
                        v-for="item in authorTypeList"
                        :key="item"
                        :label="item"
                        :value="item"
                      >
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="employer"
                  label="归属单位"
                  width="200px"
                  align="center"
                >
                  <template slot="header" slot-scope="scope"
                    >{{ scope.haha }}
                    <div style="line-height: 14px">归属单位</div>
                    <el-select
                      class="columnInput"
                      v-model="authorQueryInfo.employer"
                      multiple
                      size="mini"
                      collapse-tags
                      placeholder="请选择"
                    >
                      <el-option
                        v-for="item in attributionList"
                        :key="item"
                        :label="item"
                        :value="item"
                      >
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="rank"
                  label="排名"
                  align="center"
                ></el-table-column>
                <el-table-column
                  prop="rate"
                  label="贡献率"
                  align="center"
                ></el-table-column>
                <!-- 操作列 -->
                <el-table-column
                  label="操作"
                  align="center"
                  width="120"
                  fixed="right"
                >
                  <template slot-scope="scope"
                    >{{ scope.haha }}
                    <el-button
                      type="primary"
                      icon="el-icon-edit"
                      size="mini"
                    ></el-button>
                    <el-button
                      type="danger"
                      icon="el-icon-delete"
                      size="mini"
                    ></el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-col>
          </el-row>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="addBooksDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addBooks">确 定</el-button>
        </span>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 搜索条件表单
      queryInfo: {
        // 著作题目
        title: "",
        // 著作作者
        authorName: "",
        // 出版单位
        publishUnit: "",
        // 出版社级别
        publishLevel: [],
        // 著作类别
        booktype: [],
        // 出版地
        pubplace: [],
        // ISBN号
        iSBN: "",
        // 总字数
        allLeters: "",
        // 是否翻译
        translaTion: [],
        //翻译语种
        language: [],
        //学科门类
        subjectCate: [],
        //一级学科
        subject: [],
        //出版日期
        date: "",
        // 成果归属 归属单位
        attribution: [],
        // 项目来源
        source: [],
        //研究类别
        proType: [],

        //    // 著作题目
        //     title: "",
        //     // 著作作者
        //     authorName: "",
        //     // 发表期刊数组
        //     periodical: "",
        //     // 发表日期区间
        //     date: "",
        //     // 论文类型数组
        //     type: [],
        //     // 收录号
        //     accessionNumber: "",
        //     // 学科门类
        //     subjectCate: [],
        //     // 一级学科
        //     subject: [],
        //     // 归属单位
        //     attribution: [],
        //     // 项目来源
        //     source: [],
        //     // 备注
        //     remark: "",

        // 当前页码
        pagenum: 1,
        // 当前页大小
        pagesize: 2,
      },
      // 论文信息列表
      paperList: [
        {
          // title: "著作题目",
          // authorName: "著作作者",
          // periodical: "发表期刊",
          // date: "2016-05-02",
          // type: "论文类型",
          // accessionNumber: "收录号",
          // subjectCate: "学科门类",
          // subject: "一级学科",
          // attribution: "归属单位",
          // source: "项目来源",
          // remark: "备注",
          // 著作题目
          title: "著作题目",
          authorName: "著作作者",
          publishUnit: "出版单位",
          publishLevel: "出版社级别",
          booktype: "著作类别",
          pubplace: "出版地",
          iSBN: "ISBN号",
          allLeters: "总字数",
          translaTion: "是否翻译",
          language: "翻译语种",
          subjectCate: "学科门类",
          subject: "一级学科",
          date: "出版日期",
          attribution: "归属单位",
          source: "项目来源",
          proType: "研究类别",
        },
        {
          title: "著作题目",
          authorName: "著作作者",
          publishUnit: "出版单位",
          publishLevel: "出版社级别",
          booktype: "著作类别",
          pubplace: "出版地",
          iSBN: "ISBN号",
          allLeters: "总字数",
          translaTion: "是否翻译",
          language: "翻译语种",
          subjectCate: "学科门类",
          subject: "一级学科",
          date: "出版日期",
          attribution: "归属单位",
          source: "项目来源",
          proType: "研究类别",
        },
      ],
      // 出版社级别
      publishLevelList: ["级别1", "级别2"],
      // 著作类型列表
      booktypeList: ["类别1", "类别2"],
      //出版地列表
      pubplaceList: ["地区1", "地区2"],
      //是否翻译
      translaTionList: ["是", "否"],
      //翻译语种列表
      languageList: ["中文简体", "English"],

      // 学科门类列表
      subjectCateList: ["学科门类1", "学科门类2"],
      // 一级学科列表
      subjectList: ["一级学科1", "一级学科2"],
      // 归属单位列表
      attributionList: ["单位1", "单位2"],
      // 项目来源列表
      sourceList: ["项目来源1", "项目来源2"],
      //研究类别列表
      proTypeList: ["类别1", "类别2"],
      // 总共数据条数
      total: 2,
      // 表格多选记录
      selection: [],
      // 控制录入著作成果对话框显示与隐藏变量
      addBooksDialogVisible: false,
      // 录入著作数据表单
      addForm: {
        title: "",
        authorName: "",
        publishUnit: "",
        publishLevel: "",
        booktype: "",
        pubplace: "",
        iSBN: "",
        allLeters: "",
        translaTion: "",
        language: "",
        subjectCate: "",
        subject: "",
        date: "",
        attribution: "",
        source: "",
        proType: "",
        authorList: [
          {
            authorName: "谢承洋",
            authorType: "老师",
            employer: "计算机科学学院",
            rank: 1,
            rate: "100%",
          },
        ],
        periodicalSelectionList: [],
      },
      // 录入、修改论文数据表单验证对象
      addFormRules: {
        title: [
          {
            required: true,
            message: "请输入著作题目",
            trigger: "blur",
          },
        ],
        authorName: [
          {
            required: true,
            message: "请输入著作作者",
            trigger: "blur",
          },
        ],
        source: [
          {
            required: true,
            message: "请选择项目来源",
            trigger: "change",
          },
        ],
        proType: [
          {
            required: true,
            message: "请选择研究类别",
            trigger: "change",
          },
        ],
        publishUnit: [
          {
            required: true,
            message: "请输入出版单位",
            trigger: "blur",
          },
        ],
        allLeters: [
          {
            required: true,
            message: "请输入总字数",
            trigger: "blur",
          },
        ],
        translaTion: [
          {
            required: true,
            message: "请选择是否翻译",
            trigger: "change",
          },
        ],
        language: [
          {
            required: true,
            message: "请选择翻译语种",
            trigger: "change",
          },
        ],
        publishLevel: [
          {
            required: true,
            message: "请选择出版社等级",
            trigger: "change",
          },
        ],
        booktype: [
          {
            required: true,
            message: "请选择著作类型",
            trigger: "change",
          },
        ],
        date: [
          {
            type: "date",
            required: true,
            message: "请选择发表日期",
            trigger: "change",
          },
        ],
        pubplace: [
          {
            required: true,
            message: "请选择出版地",
            trigger: "change",
          },
        ],
        iSBN: [
          {
            required: true,
            message: "请输入ISBN号",
            trigger: "blur",
          },
        ],
        subjectCate: [
          {
            required: true,
            message: "请选择学科门类",
            trigger: "change",
          },
        ],
        subject: [
          {
            required: true,
            message: "请选择一级学科",
            trigger: "change",
          },
        ],
        attribution: [
          {
            required: true,
            message: "请选择成果归属单位",
            trigger: "change",
          },
        ],
      },
      // 作者列表查询表单
      authorQueryInfo: {
        authorName: "",
        authorType: [],
        employer: [],
      },
      // 选中的作者列表
      authorSelectionList: [],
      // 成员类型列表
      authorTypeList: ["成员类型1", "成员类型2"],
    };
  },
  created() {
    this.getBooksList();
  },
  methods: {
    // 获取论文成果列表
    getBooksList () {},
    // 表格多选选中项改变
    selectionChange (val) {
      this.selection = val;
    },
    // 分页大小发生变化
    handleSizeChange (val) {
      this.queryInfo.pagesize = val;
      this.queryInfo.pagenum = 1;
      this.getBooksList ();
    },
    // 页数发生变化
    handleCurrentChange (val) {
      this.queryInfo.pagenum = val;
      this.getBooksList();
    },
    // 录入著作成果对话框中作者表格选中项改变
    addAuthorSelectionChange (val) {
      this.authorSelectionList = val;
    },
    // 选择著作收录列表发生变化
    periodicalSelectionChange(val) {
      this.addForm.periodicalSelectionList = val;
    },
    // 关闭录入著作对话框，清空相应数据
    addBooksDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.authorTableRef.clearSelection();
      this.$refs.periodicalTableRef.clearSelection();
    },
    // 点击确定，录入著作
    addBooks() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return this.$message.error("请正确填写著作成果信息");
        this.$message.success("收录著作成果成功");
      });
    },
  },
};
</script>

<style lang="less" scoped>
.el-table {
  margin-top: 15px;
}

.columnInput {
  width: 100%;
  line-height: 24px;
  margin-top: 10px;
}
</style>