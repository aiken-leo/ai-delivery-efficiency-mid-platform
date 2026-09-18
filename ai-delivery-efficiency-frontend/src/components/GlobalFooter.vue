<template>
  <a-layout-footer class="footer">
    <div class="footer-content">
      <!-- 第一行：带图标的链接信息 -->
      <div class="footer-links">
        <a
          href="https://www.leo168.cloud"
          target="_blank"
          rel="noopener noreferrer"
          class="footer-link"
        >
          <UserOutlined class="footer-icon" />
          <span>站长：Leo</span>
        </a>
        <span class="link-divider">|</span>
        <span class="footer-link">
          <FileTextOutlined class="footer-icon" />
          <span>AI 交付效率中台</span>
        </span>
      </div>
      <!-- 第二行：版权与备案信息 -->
      <div class="footer-info">
        <span class="info-item">© 2026 AI 交付效率中台</span>
        <span class="info-divider">|</span>
        <a
          href="https://beian.miit.gov.cn"
          target="_blank"
          rel="noopener noreferrer"
          class="info-link"
        >
          陕ICP备xxx号-3
        </a>
      </div>
    </div>
  </a-layout-footer>
</template>

<script setup lang="ts">
import { UserOutlined, FileTextOutlined } from '@ant-design/icons-vue'
</script>

<style scoped>
.footer {
  flex-shrink: 0;
  background: rgba(255, 255, 255, 0.82);
  backdrop-filter: blur(12px);
  text-align: center;
  padding: 12px 20px;
  margin-top: 0;
  border-top: 1px solid rgba(99, 102, 241, 0.12);
}

.footer-content {
  display: flex;
  flex-direction: column;
  gap: 8px;
  align-items: center;
}

.footer-links {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  gap: 12px;
}

.footer-link {
  display: inline-flex;
  align-items: center;
  gap: 4px;
  color: #5a5a5a;
  font-size: 13px;
  text-decoration: none;
  transition: color 0.3s;
}

.footer-link:hover {
  color: #6366f1;
}

.footer-icon {
  font-size: 14px;
}

.link-divider {
  color: #ccc;
  font-size: 12px;
}

.footer-info {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  gap: 8px;
  color: #999;
  font-size: 12px;
}

.info-item {
  color: #999;
}

.info-link {
  color: #999;
  text-decoration: none;
  transition: color 0.3s;
}

.info-link:hover {
  color: #6366f1;
}

.info-divider {
  color: #ccc;
}
</style>
