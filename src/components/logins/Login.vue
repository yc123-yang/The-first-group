<template>
  <!-- 登录框表单区域 -->
  <div class="loginBoxContent">
    <el-form ref="loginFormRef" label-width="0px" class="loginForm" :model="loginForm" :rules="loginFormRules">
      <p>登录</p>
      <!-- 用户名 -->
      <label class="formLabel">用户名</label>
      <el-form-item prop="user_act">
        <el-input placeholder="请输入用户名" prefix-icon="el-icon-user" v-model="loginForm.user_act"></el-input>
      </el-form-item>
      <!-- 密码 -->
      <label class="formLabel">密码</label>
      <el-form-item prop="user_pwd">
        <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" v-model="loginForm.user_pwd" type="password"></el-input>
      </el-form-item>
      <!-- 按钮区域 -->
      <div class="btns">
        <el-form-item>
          <el-button type="primary" class="loginBtn" @click="login">登录</el-button>
          <el-button type="text" class="registerBtn" @click="registerDialogVisible = true">创建用户</el-button>
          <el-button type="text" class="forgetPwdBtn">忘记密码?</el-button>
        </el-form-item>
      </div>
    </el-form>
    <!-- 注册 -->
    <el-dialog append-to-body :visible.sync="registerDialogVisible" width="25%" @close="registerDialogClosed" title="注册">
      <el-form :model="registerForm" :rules="registerFormRules" ref="registerFormRef" label-width="0px" size="mini">
        <el-form-item prop="user_act">
          <el-input class="input" placeholder="请输入用户名" prefix-icon="el-icon-user" v-model="registerForm.user_act" auto-complete="new-password"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="user_pwd">
          <el-input class="input" placeholder="请输入密码" prefix-icon="el-icon-lock" show-password v-model="registerForm.user_pwd" type="password"
            auto-complete="new-password">
          </el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="user_pwd2">
          <el-input class="input" placeholder="请再次输入密码" prefix-icon="el-icon-lock" show-password v-model="registerForm.user_pwd2" type="password"
            auto-complete="new-password">
          </el-input>
        </el-form-item>
        <!-- 身份 -->
        <el-form-item prop="role_id">
          <span>职位：</span>
          <el-radio-group   v-model="registerForm.role_id">
            <el-radio  border label="4">学生</el-radio>
            <el-radio border label="5">教师</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- 学号/职工号 -->
        <el-form-item prop="user_number">
          <el-input class="input" prefix-icon="el-icon-s-custom" placeholder="请输入学号/职工号" v-model="registerForm.user_number"></el-input>
        </el-form-item>
        <!-- 姓名 -->
        <el-form-item prop="user_name">
          <el-input class="input" prefix-icon="el-icon-postcard" placeholder="请输入姓名" v-model="registerForm.user_name"></el-input>
        </el-form-item>
        <!-- 部门 -->
        <el-form-item prop="department_id">
          <el-select prefix-icon="el-icon-s-custom" v-model="registerForm.department_id" placeholder="请选择部门" size="mini" style="width: 100% ">
            <el-option v-for="item in departmentList" :label="item.department_name" :value="item.department_id" :key="item.department_id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item prop="user_email">
          <el-input class="input" placeholder="请输入邮箱" prefix-icon="el-icon-message" v-model="registerForm.user_email"></el-input>
        </el-form-item>
        <el-row>
          <el-form-item prop="yzm">
            <el-col :span="16">
              <el-input class="input2" placeholder="请输入邮箱验证码" prefix-icon="el-icon-lock" v-model="registerForm.yzm"></el-input>
            </el-col>
            <el-col :span="8">
              <el-button type="primary" @click="sendyzm" style="float: right">点击获取</el-button>
            </el-col>
          </el-form-item>
        </el-row>
        <el-form-item>
          <el-button type="primary" class="registerBtn2" @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { stringify } from 'qs';
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.registerForm.user_pwd) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    var validateCheckCode = async (rule, value, callback) => {
      if( value === '' ) {
        callback(new Error('请输入验证码'))
      } else {
        const { data: res } = await this.$http.post('/user/checkCode', stringify({ check_code: value }))
        if(res.status !== '200') {
          callback(new Error('请输入正确的验证码'))
        } else {
          callback()
        }
      }
    }
    return {
      registerForm: {
        user_act: "",
        user_pwd: "",
        user_pwd2: "",
        user_email: "",
        role_id: "",
        user_number: "",
        user_name: '',
        yzm: "",
        department_id: "",
        user_state: 0
      },
      // 表单验证规则对象
      registerFormRules: {
        user_act: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 2, max: 11, message: "长度在 2 到 11 个字符" },
        ],
        user_pwd: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 5, max: 25, message: "长度在 5 到 25个字符" },
          {
            pattern: /^(\w){5,25}$/,
            message: "只能输入5-25个字母、数字、下划线",
          },
        ],
        user_pwd2: [
          { required: true, message: "请再次输入密码", trigger: "blur" },
          { min: 5, max: 25, message: "长度在 5 到 25个字符" },
          { validator: validatePass, trigger: "blur" },
        ],
        user_email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message: "邮箱格式错误",
          },
        ],
        user_number: [
          { required: true, message: "请输入学号或者职工号", trigger: "blur" },
        ],
        user_name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        role_id: [
          { required: true, message: "请选择身份", trigger: "change" },
        ],
        yzm: [{ validator: validateCheckCode, trigger: "blur" }],
        department_id: [{ required: true, message: "请选择部门", trigger: "change" }],
      },
      registerDialogVisible: false,
      // 表单绑定对象
      loginForm: {
        user_act: "admin",
        user_pwd: "123456",
      },
      // 表单验证规则对象
      loginFormRules: {
        user_act: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        user_pwd: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
      // 单位列表
      departmentList: []
    }
  },
  created() {
    this.getData()
  },
  methods: {
    // 获取需要的字典数据
    async getData() {
      const { data: res } = await this.$http.post('/department/findAllDepartment')
      if( res.status !== '200' ) return this.$message.error('获取单位列表数据失败')
      this.departmentList = res.data
    },
    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return false;
        const { data: res } = await this.$http.post(
          "/user/login",
          this.$qs.stringify(this.loginForm)
        );
        console.log(res);
        // 如果登录失败，显示失败信息
        if (res.status !== "200") return this.$message.error(res.msg);
        // 登录成功，跳转到主页
        this.$message.success(res.msg);
        console.log(res.data);
        window.sessionStorage.setItem("token", res.data.user_token)
        window.sessionStorage.setItem('role_name', res.data.role_name)
        window.sessionStorage.setItem("user_name", res.data.user_name)
        window.sessionStorage.setItem('user_id', res.data.user_id)
        this.$router.push("/home");
      });
    },

    register() {
      this.$refs.registerFormRef.validate(async (valid) => {
        const { data: res } = await this.$http.post('/user/register', stringify(this.registerForm))
        console.log(res)
        if( res.status !== '200' ) return this.$message.error(res.msg)
        this.$message.success('注册账号成功')
        this.registerDialogVisible = false
      });
    },
    resetForm() {
      this.$refs.registerFormRef.resetFields();
    },
    registerDialogClosed() {
      this.$refs.registerFormRef.resetFields();
    },
    async sendyzm() {
      var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      if(!reg.test(this.registerForm.user_email)) return
      const { data: res } = await this.$http.post('/user/addCode', stringify({ user_email: this.registerForm.user_email }))
      if(res.status !== '200') return this.$message.error('邮箱验证码发送失败')
      this.$message.success('邮箱验证码发送成功')
    },
  },
};
</script>

<style lang="less" scoped>
.loginBoxContent {
  display: inline-block;
  height: 100%;
  width: 400px;
  padding: 0 50px 0 50px;
  vertical-align: top;

  p {
    font-size: 32px;
    font-family: Arial;
    margin: 0 0 32px 0;
  }
}
.formLabel {
  font-size: 16px;
  font-family: Arial;
  display: block;
  margin: 8px 0;
}
.title {
  font-size: 35px;
  color: black;
}

.registerBtn2 {
  font-size: 14px;
  width: 100%;
}

.btns {
  padding: 10px 0 0 0;

  .loginBtn {
    font-size: 16px;
    width: 100%;
  }
  .el-button {
    margin: 0;
  }
  .forgetPwdBtn {
    color: #aeaeae;
    float: right;
  }

  .registerBtn {
    font-size: 16px;
    width: 16%;
  }
}
</style>
