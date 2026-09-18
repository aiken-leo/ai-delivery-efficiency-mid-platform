<template>
  <div class="ai-decor" :class="`ai-decor--${variant}`" aria-hidden="true">
    <!-- 呼吸底光 -->
    <div class="ai-decor__base" />
    <div class="ai-decor__grid" />

    <!-- 流动渐变光晕 -->
    <div class="ai-decor__orbs">
      <span class="ai-decor__orb ai-decor__orb--a" />
      <span class="ai-decor__orb ai-decor__orb--b" />
      <span v-if="variant === 'home'" class="ai-decor__orb ai-decor__orb--c" />
    </div>

    <!-- 漂浮粒子 -->
    <div class="ai-decor__particles">
      <span
        v-for="p in particles"
        :key="p.id"
        class="ai-decor__particle"
        :style="p.style"
      />
    </div>

    <!-- 几何图形轻微位移 -->
    <div class="ai-decor__shapes">
      <span class="ai-decor__shape ai-decor__shape--ring" />
      <span class="ai-decor__shape ai-decor__shape--square" />
      <span v-if="variant === 'home'" class="ai-decor__shape ai-decor__shape--tri" />
    </div>

    <div v-if="variant !== 'auth'" class="ai-decor__code-stream">
      <span v-for="(line, i) in codeLines" :key="i" class="ai-decor__code-line" :style="line.style">
        {{ line.text }}
      </span>
    </div>

    <svg class="ai-decor__neural" viewBox="0 0 1200 800" preserveAspectRatio="xMidYMid slice">
      <defs>
        <linearGradient id="neuralStroke" x1="0%" y1="0%" x2="100%" y2="100%">
          <stop offset="0%" stop-color="#3b82f6" stop-opacity="0.28" />
          <stop offset="100%" stop-color="#8b5cf6" stop-opacity="0.28" />
        </linearGradient>
      </defs>
      <g class="ai-decor__edges" stroke="url(#neuralStroke)" stroke-width="1" fill="none">
        <line x1="80" y1="120" x2="220" y2="200" />
        <line x1="220" y1="200" x2="380" y2="140" />
        <line x1="220" y1="200" x2="300" y2="320" />
        <line x1="380" y1="140" x2="520" y2="220" />
        <line x1="300" y1="320" x2="520" y2="220" />
        <line x1="520" y1="220" x2="680" y2="160" />
        <line x1="520" y1="220" x2="640" y2="340" />
        <line x1="900" y1="100" x2="1040" y2="180" />
        <line x1="1040" y1="180" x2="1120" y2="280" />
        <line x1="1040" y1="180" x2="960" y2="300" />
        <line x1="80" y1="620" x2="200" y2="540" />
        <line x1="200" y1="540" x2="340" y2="600" />
        <line x1="980" y1="560" x2="1100" y2="640" />
        <line x1="1100" y1="640" x2="1180" y2="520" />
      </g>
      <g class="ai-decor__nodes">
        <circle cx="80" cy="120" r="3.5" class="node" />
        <circle cx="220" cy="200" r="4.5" class="node node--pulse" />
        <circle cx="380" cy="140" r="3" class="node" />
        <circle cx="300" cy="320" r="3.5" class="node" />
        <circle cx="520" cy="220" r="5" class="node node--pulse" />
        <circle cx="680" cy="160" r="3" class="node" />
        <circle cx="640" cy="340" r="3.5" class="node" />
        <circle cx="900" cy="100" r="3" class="node" />
        <circle cx="1040" cy="180" r="4.5" class="node node--pulse" />
        <circle cx="1120" cy="280" r="3" class="node" />
        <circle cx="960" cy="300" r="3.5" class="node" />
        <circle cx="80" cy="620" r="3" class="node" />
        <circle cx="200" cy="540" r="4" class="node node--pulse" />
        <circle cx="340" cy="600" r="3" class="node" />
        <circle cx="980" cy="560" r="3.5" class="node" />
        <circle cx="1100" cy="640" r="4.5" class="node node--pulse" />
        <circle cx="1180" cy="520" r="3" class="node" />
      </g>
    </svg>

    <div class="ai-decor__chip ai-decor__chip--tl">
      <svg viewBox="0 0 64 64" fill="none">
        <rect x="16" y="16" width="32" height="32" rx="4" stroke="currentColor" stroke-width="1.2" />
        <rect x="24" y="24" width="16" height="16" rx="2" stroke="currentColor" stroke-width="1" />
        <path
          d="M28 8v8M36 8v8M28 48v8M36 48v8M8 28h8M8 36h8M48 28h8M48 36h8"
          stroke="currentColor"
          stroke-width="1.2"
          stroke-linecap="round"
        />
      </svg>
    </div>
    <div class="ai-decor__chip ai-decor__chip--br">
      <svg viewBox="0 0 64 64" fill="none">
        <rect x="16" y="16" width="32" height="32" rx="4" stroke="currentColor" stroke-width="1.2" />
        <rect x="24" y="24" width="16" height="16" rx="2" stroke="currentColor" stroke-width="1" />
        <path
          d="M28 8v8M36 8v8M28 48v8M36 48v8M8 28h8M8 36h8M48 28h8M48 36h8"
          stroke="currentColor"
          stroke-width="1.2"
          stroke-linecap="round"
        />
      </svg>
    </div>

    <div class="ai-decor__data-flow ai-decor__data-flow--1" />
    <div class="ai-decor__data-flow ai-decor__data-flow--2" />
    <div v-if="variant !== 'auth'" class="ai-decor__data-flow ai-decor__data-flow--3" />
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'

const props = withDefaults(
  defineProps<{
    variant?: 'home' | 'auth'
  }>(),
  {
    variant: 'home',
  },
)

const codeSnippets = [
  'const agent = await createAgent({ model: "gpt" })',
  'pipeline.run({ prompt, stream: true })',
  'fn deploy(app_id: Uuid) -> Result<Url>',
  'POST /api/v1/apps  { initPrompt }',
  'await llm.generate(tokens)',
  'graph.add_edge(node_a, node_b)',
]

const codeLines = codeSnippets.map((text, i) => ({
  text,
  style: {
    top: `${10 + ((i * 13) % 78)}%`,
    left: `${(i % 2 === 0 ? 3 : 58) + (i % 3) * 2}%`,
    animationDelay: `${i * 1.6}s`,
    opacity: String(0.028 + (i % 3) * 0.01),
  },
}))

/** 确定性布局，避免每次渲染抖动 */
/** [left%, top%, sizePx, durationSec, delaySec] */
const particleSeeds = [
  [12, 18, 4, 9, 0],
  [28, 42, 3.2, 11, 1.2],
  [48, 14, 4.5, 8, 2.4],
  [66, 36, 3, 12, 0.6],
  [82, 22, 3.8, 10, 1.8],
  [18, 68, 3.4, 13, 3],
  [38, 78, 4.2, 9.5, 1.5],
  [58, 58, 3.2, 11.5, 3.6],
  [74, 72, 3.6, 8.5, 0.9],
  [88, 48, 3, 12.5, 2.7],
  [8, 52, 3.5, 10.5, 2.1],
  [42, 28, 4, 9, 0.4],
  [62, 12, 3.2, 11, 3.3],
  [92, 66, 3.8, 10, 1.4],
] as const

const particles = computed(() => {
  const count = props.variant === 'auth' ? 8 : 14
  return particleSeeds.slice(0, count).map((seed, id) => {
    const [left, top, size, duration, delay] = seed
    return {
      id,
      style: {
        left: `${left}%`,
        top: `${top}%`,
        width: `${size}px`,
        height: `${size}px`,
        animationDuration: `${duration}s`,
        animationDelay: `${delay}s`,
      },
    }
  })
})
</script>

<style scoped>
.ai-decor {
  position: absolute;
  inset: 0;
  overflow: hidden;
  pointer-events: none;
  z-index: 1;
  contain: layout style paint;
}

.ai-decor__base {
  position: absolute;
  inset: 0;
  background:
    radial-gradient(ellipse 70% 50% at 15% 20%, rgba(59, 130, 246, 0.16), transparent 55%),
    radial-gradient(ellipse 60% 45% at 85% 75%, rgba(139, 92, 246, 0.14), transparent 55%);
  animation: breath 8s ease-in-out infinite;
}

.ai-decor__grid {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(59, 130, 246, 0.045) 1px, transparent 1px),
    linear-gradient(90deg, rgba(59, 130, 246, 0.045) 1px, transparent 1px),
    linear-gradient(rgba(139, 92, 246, 0.025) 1px, transparent 1px),
    linear-gradient(90deg, rgba(139, 92, 246, 0.025) 1px, transparent 1px);
  background-size:
    96px 96px,
    96px 96px,
    24px 24px,
    24px 24px;
  animation: gridDrift 28s ease-in-out infinite;
}

.ai-decor--auth .ai-decor__grid {
  opacity: 0.7;
}

.ai-decor--auth .ai-decor__neural {
  opacity: 0.32;
}

.ai-decor--auth .ai-decor__chip {
  opacity: 0.7;
}

.ai-decor--auth .ai-decor__orbs {
  opacity: 0.75;
}

.ai-decor--auth .ai-decor__particles {
  opacity: 0.7;
}

/* —— 流动光晕 —— */
.ai-decor__orbs {
  position: absolute;
  inset: 0;
}

.ai-decor__orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(36px);
  will-change: transform, opacity;
}

.ai-decor__orb--a {
  width: min(42vw, 380px);
  height: min(42vw, 380px);
  top: 8%;
  left: -6%;
  background: radial-gradient(circle, rgba(59, 130, 246, 0.38) 0%, transparent 70%);
  animation: orbDriftA 12s ease-in-out infinite;
}

.ai-decor__orb--b {
  width: min(36vw, 320px);
  height: min(36vw, 320px);
  right: -4%;
  bottom: 12%;
  background: radial-gradient(circle, rgba(139, 92, 246, 0.34) 0%, transparent 70%);
  animation: orbDriftB 14s ease-in-out infinite;
}

.ai-decor__orb--c {
  width: min(28vw, 260px);
  height: min(28vw, 260px);
  top: 42%;
  left: 38%;
  background: radial-gradient(circle, rgba(99, 102, 241, 0.28) 0%, transparent 70%);
  animation: orbDriftC 11s ease-in-out infinite;
}

/* —— 粒子 —— */
.ai-decor__particles {
  position: absolute;
  inset: 0;
}

.ai-decor__particle {
  position: absolute;
  border-radius: 50%;
  background: rgba(99, 102, 241, 0.7);
  box-shadow: 0 0 10px rgba(99, 102, 241, 0.45);
  animation-name: particleFloat;
  animation-timing-function: ease-in-out;
  animation-iteration-count: infinite;
  will-change: transform, opacity;
}

.ai-decor__particle:nth-child(odd) {
  background: rgba(59, 130, 246, 0.72);
  box-shadow: 0 0 10px rgba(59, 130, 246, 0.4);
}

.ai-decor__particle:nth-child(3n) {
  background: rgba(139, 92, 246, 0.68);
  box-shadow: 0 0 10px rgba(139, 92, 246, 0.4);
}

/* —— 几何形 —— */
.ai-decor__shapes {
  position: absolute;
  inset: 0;
}

.ai-decor__shape {
  position: absolute;
  opacity: 0.42;
  will-change: transform;
}

.ai-decor__shape--ring {
  top: 22%;
  right: 12%;
  width: 64px;
  height: 64px;
  border: 2px solid rgba(59, 130, 246, 0.65);
  border-radius: 50%;
  animation: shapeDrift 10s ease-in-out infinite;
}

.ai-decor__shape--square {
  bottom: 24%;
  left: 10%;
  width: 40px;
  height: 40px;
  border: 2px solid rgba(139, 92, 246, 0.6);
  border-radius: 6px;
  animation: shapeDrift 12s ease-in-out infinite reverse;
  animation-delay: -2s;
}

.ai-decor__shape--tri {
  top: 58%;
  right: 22%;
  width: 0;
  height: 0;
  border-left: 16px solid transparent;
  border-right: 16px solid transparent;
  border-bottom: 28px solid rgba(99, 102, 241, 0.5);
  border-radius: 0;
  opacity: 0.4;
  animation: shapeDrift 13s ease-in-out infinite;
  animation-delay: -4s;
}

.ai-decor__code-stream {
  position: absolute;
  inset: 0;
  font-family: ui-monospace, SFMono-Regular, Menlo, Consolas, monospace;
  font-size: 11px;
  color: #64748b;
  line-height: 1.6;
  user-select: none;
}

.ai-decor__code-line {
  position: absolute;
  white-space: nowrap;
  animation: codeGlow 9s ease-in-out infinite;
}

.ai-decor__neural {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  opacity: 0.55;
  animation: breath 9s ease-in-out infinite;
}

.ai-decor__nodes .node {
  fill: #3b82f6;
  opacity: 0.5;
}

.ai-decor__nodes .node--pulse {
  fill: #8b5cf6;
  opacity: 0.65;
  animation: nodePulse 2.4s ease-in-out infinite;
}

.ai-decor__chip {
  position: absolute;
  width: 56px;
  height: 56px;
  color: rgba(59, 130, 246, 0.28);
  animation: shapeDrift 11s ease-in-out infinite;
}

.ai-decor__chip--tl {
  top: 14%;
  left: 7%;
}

.ai-decor__chip--br {
  bottom: 16%;
  right: 8%;
  color: rgba(139, 92, 246, 0.28);
  animation-direction: reverse;
  animation-duration: 13s;
}

.ai-decor__chip svg {
  width: 100%;
  height: 100%;
}

.ai-decor__data-flow {
  position: absolute;
  height: 2px;
  border-radius: 1px;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(59, 130, 246, 0.55),
    rgba(139, 92, 246, 0.55),
    transparent
  );
  animation: dataFlow 8s linear infinite;
}

.ai-decor__data-flow--1 {
  top: 30%;
  left: -20%;
  width: 38%;
}

.ai-decor__data-flow--2 {
  top: 62%;
  left: -28%;
  width: 46%;
  animation-duration: 10s;
  animation-delay: 1s;
}

.ai-decor__data-flow--3 {
  top: 80%;
  left: -18%;
  width: 32%;
  animation-duration: 9s;
  animation-delay: 2.5s;
}

@keyframes breath {
  0%,
  100% {
    opacity: 0.65;
  }
  50% {
    opacity: 1;
  }
}

@keyframes gridDrift {
  0%,
  100% {
    transform: translate3d(0, 0, 0);
  }
  50% {
    transform: translate3d(6px, 6px, 0);
  }
}

@keyframes orbDriftA {
  0%,
  100% {
    transform: translate3d(0, 0, 0) scale(1);
    opacity: 0.75;
  }
  50% {
    transform: translate3d(8%, 10%, 0) scale(1.15);
    opacity: 1;
  }
}

@keyframes orbDriftB {
  0%,
  100% {
    transform: translate3d(0, 0, 0) scale(1);
    opacity: 0.7;
  }
  50% {
    transform: translate3d(-9%, -7%, 0) scale(1.12);
    opacity: 1;
  }
}

@keyframes orbDriftC {
  0%,
  100% {
    transform: translate3d(0, 0, 0);
    opacity: 0.55;
  }
  33% {
    transform: translate3d(6%, -5%, 0);
    opacity: 0.95;
  }
  66% {
    transform: translate3d(-5%, 7%, 0);
    opacity: 0.75;
  }
}

@keyframes particleFloat {
  0%,
  100% {
    transform: translate3d(0, 0, 0) scale(1);
    opacity: 0.35;
  }
  50% {
    transform: translate3d(6px, -32px, 0) scale(1.25);
    opacity: 0.95;
  }
}

@keyframes shapeDrift {
  0%,
  100% {
    transform: translate3d(0, 0, 0) rotate(0deg);
  }
  50% {
    transform: translate3d(18px, -14px, 0) rotate(12deg);
  }
}

@keyframes codeGlow {
  0%,
  100% {
    filter: brightness(1);
  }
  50% {
    filter: brightness(1.35);
  }
}

@keyframes nodePulse {
  0%,
  100% {
    opacity: 0.32;
  }
  50% {
    opacity: 0.65;
  }
}

@keyframes dataFlow {
  0% {
    transform: translateX(0);
    opacity: 0;
  }
  12% {
    opacity: 1;
  }
  88% {
    opacity: 1;
  }
  100% {
    transform: translateX(280%);
    opacity: 0;
  }
}

@media (max-width: 768px) {
  .ai-decor__code-stream,
  .ai-decor__chip,
  .ai-decor__shapes,
  .ai-decor__orb--c {
    display: none;
  }

  .ai-decor__neural {
    opacity: 0.28;
  }

  .ai-decor__particles .ai-decor__particle:nth-child(n + 7) {
    display: none;
  }
}

@media (prefers-reduced-motion: reduce) {
  .ai-decor__base,
  .ai-decor__grid,
  .ai-decor__orb,
  .ai-decor__particle,
  .ai-decor__shape,
  .ai-decor__chip,
  .ai-decor__neural,
  .ai-decor__code-line,
  .ai-decor__data-flow,
  .ai-decor__nodes .node--pulse {
    animation: none !important;
  }
}
</style>
