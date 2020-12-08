<template>
  <!-- 登录框表单区域 -->
  <div class="loginBoxContent">
    <el-form
      ref="loginFormRef"
      label-width="0px"
      class="loginForm"
      :model="loginForm"
      :rules="loginFormRules"
    >
      <p>登录</p>
      <!-- 用户名 -->
      <label class="formLabel">用户名</label>
      <el-form-item prop="user_act">
        <el-input
          placeholder="请输入用户名"
          prefix-icon="el-icon-user"
          v-model="loginForm.user_act"
        ></el-input>
      </el-form-item>
      <!-- 密码 -->
      <label class="formLabel">密码</label>
      <el-form-item prop="user_pwd">
        <el-input
          prefix-icon="el-icon-lock"
          placeholder="请输入密码"
          v-model="loginForm.user_pwd"
          type="password"
        ></el-input>
      </el-form-item>
      <!-- 按钮区域 -->
      <div class="btns">
        <el-form-item>
          <el-button type="primary" class="loginBtn" @click="login"
            >登录</el-button
          >
          <el-button
            type="text"
            class="registerBtn"
            @click="registerDialogVisible = true"
            >创建用户</el-button
          >
          <el-button type="text" class="forgetPwdBtn">忘记密码?</el-button>
        </el-form-item>
      </div>
    </el-form>
    <!-- 注册 -->
    <el-dialog
      append-to-body
      :visible.sync="registerDialogVisible"
      width="20%"
      @close="registerDialogClosed"
      title="注册"
    >
      <el-form
        :model="registerForm"
        :rules="registerFormRules"
        ref="registerFormRef"
        label-width="0px"
        size="mini"
      >
        <el-form-item prop="user_act">
          <el-input
            class="input"
            placeholder="请输入用户名"
            prefix-icon="el-icon-user"
            v-model="registerForm.user_act"
            auto-complete="new-password"
          ></el-input>
        </el-form-item>

        <!-- 密码 -->

        <el-form-item prop="user_pwd">
          <el-input
            class="input"
            placeholder="请输入密码"
            prefix-icon="el-icon-lock"
            show-password
            v-model="registerForm.user_pwd"
            type="password"
            auto-complete="new-password"
          ></el-input>
        </el-form-item>

        <!-- 身份 -->
        <el-form-item prop="role_id">
          职位：
          <el-radio-group v-model="registerForm.role_id">
            <el-radio label="4">学生</el-radio>
            <el-radio label="5">教师</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- 学号/职工号 -->
        <el-form-item prop="user_number">
          <el-input
            class="input"
            prefix-icon="el-icon-s-custom"
            placeholder="请输入学号/职工号"
            v-model="registerForm.user_number"
          ></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item prop="user_email">
          <el-input
            class="input"
            placeholder="请输入邮箱"
            prefix-icon="el-icon-message"
            v-model="registerForm.user_email"
          ></el-input>
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
          <el-button type="primary" class="registerBtn2" @click="register"
            >注册</el-button
          >
        </el-form-item>
      </el-form>
      <!--
      <span slot="footer" class="dialog-footer">
        <el-button @click="registerDialogVisible = false">取 消</el-button>
        <el-button @click="resetForm">清 空</el-button>
      </span>
      -->
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      registerForm: {
        user_act: "",
        user_pwd: "",
        user_email: "",
        role_id: "",
        user_number: "",
        yzm: "",
      },
      // 表单验证规则对象
      registerFormRules: {
        user_act: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 2, max: 7, message: "长度在 2 到 7 个字符" },
        ],
        user_pwd: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 5, max: 25, message: "长度在 5 到 25个字符" },
          {
            pattern: /^(\w){5,25}$/,
            message: "只能输入5-25个字母、数字、下划线",
          },
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
        user_identity: [
          { required: true, message: "请选择身份", trigger: "change" },
        ],
        yzm: [{ required: true, message: "请输入邮箱验证码", trigger: "blur" }],
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
    };
  },
  methods: {
    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return false;
        const { data: res } = await this.$http.post(
          "user/login",
          this.$qs.stringify(this.loginForm)
        );
        console.log(res);
        // 如果登录失败，显示失败信息
        if (res.status !== "200") return this.$message.error(res.msg);
        // 登录成功，跳转到主页
        this.$message.success(res.msg);
        console.log(res.data)
        window.sessionStorage.setItem("token", res.data.token);
        this.$router.push("/home");
      });
    },

    register() {
      this.$refs.registerFormRef.validate(async (valid) => {});
    },
    resetForm() {
      this.$refs.registerFormRef.resetFields();
    },
    registerDialogClosed() {
      this.$refs.registerFormRef.resetFields();
    },
    sendyzm() {},
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
