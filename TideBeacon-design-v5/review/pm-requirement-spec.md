# TideBeacon 产品与质量契约

> role: `product_strategist` | artifactRevision: 4 | active source@3 uxr@3

## 1. 意图

- 领域：非医疗的沉浸式呼吸节奏引导；中文；纯视觉+空间音频。
- 目标用户：使用 PICO 设备与手柄的中文用户；尤其是首次用户。
- 单次时长：120/240/360s；使用频率未知。
- 任务：光环映射确认→时长/场景/节奏选择→开始→跟随四相→暂停/恢复/重开/退出→完成/可选本地记录。
- 数据边界：不检测呼吸，不读取麦克风/生理数据，不评分，不需网络或 AI；本地记录可跳过。
- 风险：时间轴错相、恢复爆音、闪烁/相机移动不适、手柄无出口、疗效暗示。

## 2. 首次视图冻结契约

`FirstLight` 只有三个可见对象：远处灯塔、光环、“光扩张时吸气，光收回时呼气。”。无标题、卡片、进度或“继续”。推进必须由手柄且不得新增第四个可见对象；具体激活目标在交互阶段评估。

## 3. 可验证假设

| ID | 假设 | 置信 | 影响 | 验证/Owner | 失败路径 |
|---|---|---|---|---|---|
| A1 | 坐姿或原地站姿能完成全流程 | medium | 舒适区布局 | 真机可用性/UX | 收缩至正前焦点 |
| A2 | 4-1-6-1 适作非医疗默认节奏 | medium | 循环数/理解 | 首次样本/UX | 换默认，保留可配 |
| A3 | 手柄射线能聚焦世界锁定光环 | medium | 首屏推进 | SDK/真机实现 | 扩大光环可交互范围 |
| A4 | 到达会话时长立即完成，不等待整循环 | medium | 结束边界 | timeline 测试 | 需求变更则重评审 |
| A5 | 恢复音量包络目标 400ms，且正常/降级不分叉 | medium | 爆音/迟滞 | audio owner；包络单测+真机波形；通过=300–500ms内单调无突变 | 保持同窗口重调目标，不改为直切 |

## 4. 质量契约

1. 十秒内能说出扩张=吸气、收回=呼气；首屏可见对象精确为 3。
2. 手柄独立完成全部菜单、确认和稳定退出。
3. 同一 pause-aware monotonic elapsed 纯派生 phase/progress/remaining/audio/cycleIndex，禁止逐帧累加。
4. 暂停冻结所有消费者；恢复时由 0 到目标增益统一 400ms（正常/降级同值）。
5. 粒子只由 `cycleCompleted` 触发，每循环最多 3，Reduce Motion 为 0；相机不动，无闪烁。
6. 120.000s 逻辑时刻进完成；60/72/90/120Hz 合成采样相位误差<1ms，显示±1帧。
7. 完成仅显示“完成一次练习”、本地记录选项和安全退出；无分数/连胜/疗效语。
8. 后续核心 UI 组件每个须有8段完整结构；具体名称由 Stage11 形成。
9. 预览覆盖选择/运行截图状态、完整状态机、组合状态和响应式/Reduce Motion。

- **PICO/空间硬约束**：容器须在 Shared/Full 中合法；Stage 须显式进入且可关闭回窗口；Planar 深度 640dp；核心触点≥56dp、正文≥12dp；default/min/max、FOV、重排、Reduce Motion、controller fallback 必须可追踪。来源 `official-rules.json@2.2.0`。
- **领域证据要求**：SessionConfig/BreathPattern/TimelineSnapshot/LocalRecord/PendingIntent 每个实体都必须在组件 binding 或“有意不呈现+理由”中落地；时钟错误必须冻结且保留退出。
- **原创性契约**：设计须通过 UXR O1–O5 表现灯塔方向、统一 elapsed、无检测信任、三对象首屏和挂起保持；只吸收环境选择/节奏可见/稳定退出等需求，禁止复制竞品布局、状态图、组件或视觉。

## 5. 需求追踪

| R | 需求 | 源 | 验收 |
|---|---|---|---|
| R1 | 名称/包名 | U1-L1 | 交付索引一致 |
| R2 | 非医疗/无感知/无评分 | U1-L2/3 | 文案/权限审计 |
| R3 | 首屏三对象 | U1-L4/5 | DOM/manifest 精确计数 |
| R4 | 2/4/6 分钟 | U1-L6 | 三值+120s |
| R5 | 海/云/沙丘程序化低模 | U1-L7/17 | 三变体 |
| R6 | 四相可配 | U1-L8 | 相位边界 |
| R7 | 统一时间轴 | U1-L9 | 多刷新率测试 |
| R8 | 整循环弱粒子 | U1-L10 | 事件计数 |
| R9 | 无快相机/强闪 | U1-L11 | motion audit |
| R10 | 暂停/退出/重开/节奏 | U1-L12 | controller flow |
| R11 | 冻结+400ms恢复 | U1-L13 | lifecycle/waveform |
| R12 | 手柄全操作 | U1-L14 | no-touch walk-through |
| R13 | 完成文案/可选记录 | U1-L15 | copy/state |
| R14 | timeline/state tests | U1-L16 | synthetic matrix |
| R15 | 两张截图 | U1-L19 | selection/running evidence |

## 6. Minimum completeness

Intent, assumptions, quality contract and R1–R15: pass. This is a design package; no Android/runtime/device evidence is claimed.

Stage03 initial verification occurred at 2026-08-12T21:51:10+08:00 against uxr@1. P-01 reconciliation verifiedAt 2026-08-12T21:58:41+08:00: the complete contract was rechecked against active source@3/uxr@3; competitor provenance changes do not alter O1–O5 or product outcomes.
