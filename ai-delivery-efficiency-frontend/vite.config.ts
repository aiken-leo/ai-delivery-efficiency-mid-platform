import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  // 新增：开发服务器代理，解决跨站Cookie丢失
  server: {
    proxy: {
      // 所有 /api 开头的请求，转发到后端服务器
      '/api': {
        target: 'http://127.0.0.1:8088/api',
        changeOrigin: true,
        secure: false,
      }
    }
  }
})
