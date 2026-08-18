# TideBeacon 研究与领域模型

V6 Stage02 verifiedAt 2026-08-12T22:35:19+08:00 from source/current evidence.

> role: `research_analyst` | artifactRevision: 1 | observed: 2026-08-12 | active source@1 pm@1 | V6 independent run

## 1. 五类证据台账

| ID/type | 声明 | 来源/类型 | 范围/置信 | 验证 |
|---|---|---|---|---|
| M1 market | 沉浸环境、可配内容是相邻产品可观察能力；不证明用户偏好 | 下方 C1–C3，external | 相邻品类/medium | 真人研究 |
| U1 user | 初次只有三对象、手柄全操作 | source U1-L4/5/14, user_supplied | 本产品/high | 首次任务+手柄走查 |
| D1 domain | 四相、循环完成、会话时长、暂停快照是必需的时间语义 | U1-L8–16, user_supplied+分析建模 | 本时间轴/high/medium | 纯函数测试 |
| P1 platform | Stage 与 Shared/Full Space、Planar 深度640dp、手柄兼容是容器决策约束 | `official-rules.json@2.2.0`, official | PICO Spatial design/high | 锁定 SDK 后真机 |
| S1 safety | 无自动相机/强闪，暂停全冻结，无健康声明 | U1-L3/9/11/13, user_supplied | 本产品/high | motion/copy/audio audit |
| U-GAP user | 坐站姿、视力/听力多样性、频率未有样本 | none, assumption | 本产品/low | 5–8人包含坐/站与大文字 |
| P-GAP platform | 具体手柄 API、音频包络和刷新率支持未在设计包验证 | none, assumption | 目标SDK/low | 下游文档+真机 |

## 2. 竞品四维基线（仅机会层）

| 样本 | 功能 | 交互 | 视觉 | 空间能力 | 吸收/避免/缺口 |
|---|---|---|---|---|---|
| C1 TRIPP | 观察：帮助页 Tips 集合含 pause/quit/realign | 观察：有运行控制；具体设备输入缺口 | 观察：官方产品素材是沉浸环境；可读性未独立测 | 观察：XR 环境；方向/距离净增益缺口 | 吸收稳定退出需求；避免内容库负担。https://help.tripp.com/en/collections/3029765-tips-for-using-tripp |
| C2 Guided VR | 观察：公开页面向引导场景的产品定位 | 缺口：平板/手柄/路径缺少精确页内证据 | 观察：公开素材有沉浸场景；字体/密度缺口 | 缺口：远程操作、方向/距离净值均未证实 | 仅吸收“环境可选”需求机会；不吸收未证实交互。https://guidedvr.com/ |
| C3 Flowborne VR | 观察：产品页描述呼吸节奏与沉浸旅程 | 观察：用户跟随；暂停/退出/手柄详情缺口 | 观察：环境与节奏引导；不证明无闪烁 | 推断：空间沉浸承载注意；净收益待验 | 吸收节奏可见需求；避免疗效和呼吸检测暗示。https://flowborne.com/ |

### Claim-level provenance

| Competitor | Locator | type/confidence/date | Observation / inference / gap boundary |
|---|---|---|---|
| C1 | TRIPP Help “Tips for using TRIPP” collection: https://help.tripp.com/en/collections/3029765-tips-for-using-tripp | external / medium / accessed 2026-08-12 | 只观察集合页列出 pause/quit/realign 条目；输入、视觉可读性、空间净收益均为缺口 |
| C2 | GuidedVR product landing: https://guidedvr.com/ | external / low / accessed 2026-08-12 | 只观察面向引导场景的产品描述；平板操作者、手柄、深度净值缺少精确页内证据，不作事实吸收 |
| C3 | Flowborne public product landing: https://flowborne.com/ | external / low / accessed 2026-08-12 | 只观察公开页的呼吸/沉浸旅程定位；暂停、退出、手柄、闪烁和空间净收益全部标为缺口/待验证推断 |

**机会 O1–O5**：O1 以世界锁定灯塔稳定方向；O2 以一个 elapsed 锁定声画计时；O3 用“无检测/无评分”构成数据信任；O4 首屏只三对象；O5 系统挂起后保持暂停。这些只影响需求与概念比较，不复制任何布局、组件、状态序列、色彩或动效。

## 3. 领域模型

| Entity | Fields | Timeliness/trust | Rule |
|---|---|---|---|
| SessionConfig | durationSec, scene, pattern | 开始时冻结 | 只有合法三选值 |
| BreathPattern | inhaleMs,hold1Ms,exhaleMs,hold2Ms,label | 配置版本 | 四段皆>0，无疗效标签 |
| TimelineSnapshot | elapsed,phase,progress,remaining,cycleIndex,paused | 每次渲染纯派生 | 时钟错误→冻结+安全退出 |
| LocalRecord | completedAt,duration,scene,pattern | 用户选择后 | 仅此设备；写失败不阻完成 |
| PendingIntent | enter,restart,exit,null,error | 短期 modal | null/error 不渲染对话，安全回 host |

心智模型：光环是呼吸节拍器，灯塔是稳定方向，不是呼吸检测器。成熟模式可吸收环境选择、可见节奏和安全退出；反模式是内容库、多面板、评分、连胜、生理反馈、语音指导和高动感。

## 4. Persona / journey / safety

Persona: “第一次打开、用手柄、不想读说明的中文用户”，是任务范围而非统计人群声明。Journey: 看三对象→以不新增可见对象的手柄机制推进（目标由交互阶段决定）→三组选择→显式进入→跟随/中断→完成。目标：映射≤10s，单项选择≤5s，进入练习≤30s；均待真人验证。

安全：无相机位移、无闪烁、粒子低密度、Reduce Motion、大文字重排、手柄路径、B/System Back 稳定退出；这些是设计要求，不是设备验证结论。

## 5. Minimum completeness

Five evidence categories: pass; competitors 3×4: pass; observation/inference/gap separated: pass; domain/persona/journey/duration/safety: pass.
