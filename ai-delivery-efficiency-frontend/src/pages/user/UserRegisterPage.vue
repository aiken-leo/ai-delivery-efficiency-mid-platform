<template>
  <div id="userRegisterPage" class="auth-page">
    <AiDecorLayer variant="auth" />
    <div class="auth-shell">
      <div class="auth-card">
        <div class="auth-brand">
          <div class="auth-mark" />
          <p class="auth-product">AI 交付效率中台</p>
        </div>
        <h1 class="auth-title">用户注册</h1>
        <p class="auth-desc">一句话轻松创建网站应用</p>

        <a-form
          class="auth-form"
          :model="formState"
          name="basic"
          autocomplete="off"
          layout="vertical"
          @finish="handleSubmit"
        >
          <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入账号' }]">
            <a-input
              v-model:value="formState.userAccount"
              size="large"
              placeholder="请输入账号"
              class="auth-input"
            />
          </a-form-item>
          <a-form-item
            name="userPassword"
            :rules="[
              { required: true, message: '请输入密码' },
              { min: 8, message: '密码不能小于 8 位' },
            ]"
          >
            <a-input-password
              v-model:value="formState.userPassword"
              size="large"
              placeholder="请输入密码"
              class="auth-input"
            />
          </a-form-item>
          <a-form-item
            name="checkPassword"
            :rules="[
              { required: true, message: '请确认密码' },
              { min: 8, message: '密码不能小于 8 位' },
              { validator: validateCheckPassword },
            ]"
          >
            <a-input-password
              v-model:value="formState.checkPassword"
              size="large"
              placeholder="请确认密码"
              class="auth-input"
            />
          </a-form-item>

          <div class="auth-tips">
            已有账号？
            <RouterLink to="/user/login">去登录</RouterLink>
          </div>

          <a-form-item class="auth-submit-item">
            <a-button type="primary" html-type="submit" size="large" class="auth-submit">
              注册
            </a-button>
          </a-form-item>
        </a-form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { userRegister } from '@/api/userController.ts'
import { message } from 'ant-design-vue'
import { reactive } from 'vue'
import AiDecorLayer from '@/components/AiDecorLayer.vue'

const router = useRouter()

const formState = reactive<API.UserRegisterRequest>({
  userAccount: '',
  userPassword: '',
  checkPassword: '',
})

const validateCheckPassword = (rule: unknown, value: string, callback: (error?: Error) => void) => {
  if (value && value !== formState.userPassword) {
    callback(new Error('两次输入密码不一致'))
  } else {
    callback()
  }
}

const handleSubmit = async (values: API.UserRegisterRequest) => {
  const res = await userRegister(values)
  if (res.data.code === 0) {
    message.success('注册成功')
    router.push({
      path: '/user/login',
      replace: true,
    })
  } else {
    message.error('注册失败，' + res.data.message)
  }
}
</script>

<style scoped>
.auth-page {
  position: relative;
  flex: 1;
  width: 100%;
  min-height: calc(100vh - 140px);
  max-height: calc(100vh - 140px);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 12px 20px;
  background: linear-gradient(165deg, #f8fafc 0%, #eef2ff 48%, #f5f3ff 100%);
  overflow: hidden;
  box-sizing: border-box;
}

.auth-shell {
  position: relative;
  z-index: 2;
  width: 100%;
  max-width: 420px;
}

.auth-card {
  padding: 40px 36px 32px;
  border-radius: 20px;
  background: rgba(255, 255, 255, 0.78);
  border: 1px solid rgba(99, 102, 241, 0.14);
  box-shadow:
    0 24px 60px rgba(79, 70, 229, 0.1),
    0 2px 8px rgba(15, 23, 42, 0.04),
    inset 0 1px 0 rgba(255, 255, 255, 0.85);
  backdrop-filter: blur(22px);
}

.auth-brand {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  margin-bottom: 28px;
}

.auth-mark {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background: linear-gradient(135deg, #3b82f6, #8b5cf6);
  box-shadow: 0 0 0 4px rgba(99, 102, 241, 0.12);
}

.auth-product {
  margin: 0;
  font-size: 13px;
  font-weight: 600;
  letter-spacing: 0.04em;
  color: #64748b;
}

.auth-title {
  margin: 0 0 8px;
  text-align: center;
  font-size: 28px;
  font-weight: 700;
  letter-spacing: -0.03em;
  color: #0f172a;
}

.auth-desc {
  margin: 0 0 28px;
  text-align: center;
  font-size: 14px;
  color: #94a3b8;
}

.auth-form :deep(.ant-form-item) {
  margin-bottom: 18px;
}

.auth-form :deep(.auth-input.ant-input),
.auth-form :deep(.ant-input-affix-wrapper) {
  border-radius: 12px;
  border-color: rgba(148, 163, 184, 0.35);
  background: rgba(255, 255, 255, 0.92);
  box-shadow: none;
  padding-top: 8px;
  padding-bottom: 8px;
}

.auth-form :deep(.auth-input.ant-input:hover),
.auth-form :deep(.ant-input-affix-wrapper:hover) {
  border-color: rgba(99, 102, 241, 0.45);
}

.auth-form :deep(.auth-input.ant-input:focus),
.auth-form :deep(.ant-input-affix-wrapper-focused) {
  border-color: #6366f1;
  box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.12);
}

.auth-tips {
  margin: -4px 0 18px;
  text-align: right;
  font-size: 13px;
  color: #94a3b8;
}

.auth-tips a {
  color: #6366f1;
  font-weight: 500;
}

.auth-submit-item {
  margin-bottom: 0 !important;
}

.auth-submit {
  width: 100%;
  height: 44px;
  border: none;
  border-radius: 12px;
  font-weight: 600;
  letter-spacing: 0.02em;
  background: linear-gradient(135deg, #3b82f6 0%, #6366f1 50%, #8b5cf6 100%);
  box-shadow: 0 10px 24px rgba(99, 102, 241, 0.28);
}

.auth-submit:hover {
  background: linear-gradient(135deg, #2563eb 0%, #4f46e5 50%, #7c3aed 100%) !important;
}

@media (max-width: 480px) {
  .auth-card {
    padding: 32px 22px 26px;
  }

  .auth-title {
    font-size: 24px;
  }
}
</style>
