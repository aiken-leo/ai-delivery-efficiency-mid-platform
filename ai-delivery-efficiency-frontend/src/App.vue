<script setup lang="ts">
import { theme } from 'ant-design-vue'
import BasicLayout from '@/layouts/BasicLayout.vue'

const { defaultAlgorithm } = theme
</script>

<template>
  <a-config-provider
    :theme="{
      algorithm: defaultAlgorithm,
      token: {
        colorPrimary: '#6366f1',
        borderRadius: 10,
        fontFamily:
          '-apple-system, BlinkMacSystemFont, Segoe UI, PingFang SC, Hiragino Sans GB, Microsoft YaHei, sans-serif',
      },
    }"
  >
    <BasicLayout />
  </a-config-provider>
</template>

<style>
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  padding: 0;
  font-family:
    -apple-system, BlinkMacSystemFont, 'Segoe UI', 'PingFang SC', 'Hiragino Sans GB',
    'Microsoft YaHei', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  overflow-x: hidden;
  background: #f8fafc;
  color: #0f172a;
}

html {
  overflow-x: hidden;
}

/* Ant Layout Header 默认 line-height 会把按钮文字顶偏 */
.ant-layout-header {
  line-height: normal !important;
}
</style>
