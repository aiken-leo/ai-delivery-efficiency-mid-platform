# AI 交付效率中台

> 个人作品 · 基于自然语言提示词生成前端应用代码，探索 AI 辅助研发提效的工程化落地。

后端以 Spring Boot 3 + LangChain4j 完成 AI 代码生成与用户权限体系，前端以 Vue 3 + TypeScript 提供管理端交互。当前已打通「提示词 → 模型生成 → 解析落盘」主链路，业务页面与开放 API 仍在迭代中。

**作者**：[Leo](https://github.com/aiken-leo)  
**仓库**：[ai-delivery-efficiency-mid-platform](https://github.com/aiken-leo/ai-delivery-efficiency-mid-platform)  
**演示**：暂无（后续补充）

---

## 项目亮点

- **AI 代码生成**：基于 LangChain4j 对接 DeepSeek（OpenAI 兼容协议），支持原生 HTML 与多文件（HTML/CSS/JS）两种生成模式。
- **同步 + 流式输出**：同步结构化结果与 Reactor `Flux` 流式生成并存，流式结束后再统一解析并保存。
- **可扩展生成管线**：门面（Facade）统一入口；解析器 / 保存器采用执行器 + 模板方法，便于扩展新的生成类型。
- **权限与后台基础**：Session 登录 + AOP `@AuthCheck` 角色校验，具备用户注册登录与管理员用户管理。
- **前后端分离**：Vue 3 + Ant Design Vue + Pinia；OpenAPI → TypeScript 客户端生成，接口契约可维护。

---

## 功能概览

### 已实现

| 模块 | 说明 |
|------|------|
| 用户体系 | 注册、登录、登出、当前用户信息 |
| 权限控制 | 基于注解的管理员校验（AOP） |
| 用户管理 | 管理员分页查询 / 增删改 |
| AI 生成核心 | HTML 单文件、多文件模式；同步与流式 |
| 代码落盘 | 解析模型输出并保存至本地目录 |
| 接口文档 | Knife4j（OpenAPI 3） |

### 规划中

见下方 [后续计划](#后续计划)。

---

## 技术栈

**后端**

- Java 21 · Spring Boot 3.5
- LangChain4j 1.1（含 Reactor 流式）
- MyBatis-Flex · MySQL · HikariCP
- Knife4j · Hutool · Lombok

**前端**

- Vue 3.5 · TypeScript · Vite 7
- Ant Design Vue 4 · Pinia · Vue Router
- Axios · `@umijs/openapi` 代码生成

**模型**

- DeepSeek Chat（可通过配置替换为其他 OpenAI 兼容服务）

---

## 架构说明（简要）

```text
用户提示词
    │
    ▼
AiCodeGeneratorFacade          ← 统一入口（按生成类型分发）
    │
    ├─ AiCodeGeneratorService  ← LangChain4j AI Service + System Prompt
    │
    ├─ CodeParserExecutor      ← HTML / 多文件解析
    │
    └─ CodeFileSaverExecutor   ← 模板方法落盘（tmp/code_output）
```

前端目前覆盖登录注册与用户管理；首页与「应用生成」交互页待接入上述能力。

---

## 快速开始

### 环境要求

- JDK 21+
- Node.js 22+（建议）与 pnpm / npm
- MySQL 8+

### 后端

1. 创建数据库（默认名可与配置一致）：`ai_delivery_efficiency`
2. 修改 `src/main/resources/application.yml` 中的数据源与模型配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ai_delivery_efficiency
    username: root
    password: <Your Password>

langchain4j:
  open-ai:
    chat-model:
      api-key: <Your API Key>
    streaming-chat-model:
      api-key: <Your API Key>
```

3. 启动：

```bash
mvn spring-boot:run
```

默认服务：`http://localhost:8088/api`  
文档（启用 Knife4j 后）：`http://localhost:8088/api/doc.html`

### 前端

```bash
cd ai-delivery-efficiency-frontend
pnpm install   # 或 npm install
pnpm dev       # 或 npm run dev
```

---

## 项目结构

```text
ai-delivery-efficiency-mid-platform/
├── src/main/java/com/leo/ai/delivery/
│   ├── ai/                 # LangChain4j AI Service 与结果模型
│   ├── core/               # 生成门面、解析器、保存器
│   ├── controller/         # 用户、健康检查等接口
│   ├── service/            # 业务服务
│   ├── aop/                # 权限拦截
│   └── ...
├── src/main/resources/
│   ├── prompt/             # 系统提示词
│   └── application*.yml
├── ai-delivery-efficiency-frontend/   # Vue 3 前端
└── README.md
```

---

## 后续计划

> 以下条目可按实际进度增删，便于简历与面试同步更新。

- [ ] 对外暴露应用 / 代码生成相关 API，并与前端生成页联调
- [ ] 应用工作台：提示词输入、生成类型选择、流式预览与结果下载
- [ ] 生成历史、应用管理与权限隔离
- [ ] 更多生成类型或工作流编排（按需扩展）
- [ ] 演示环境部署与 README 演示链接补充

---

## License

个人作品，仅供学习与简历展示。未经授权请勿用于商业用途。
