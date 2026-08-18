# TideBeacon 交互与空间设计规格

> artifactRevision: 11｜Stage 5–11 + DS-CR01..04｜approved visual D1@visual1

## 2. 设计原则

| ID | 断言 | 范围 | 推导 | 检查点 | 冲突优先级 |
|---|---|---|---|---|---|
| P1 | 任一时刻只有一个主焦点；呼吸相位可仅凭几何+中文理解 | interaction/visual | R3、F1 | 首屏/运行状态 | 1 |
| P2 | 声画/倒计时/循环事件只消费同一暂停感知 elapsed，不按帧累加 | data/motion/audio | R7/R11、F2 | Timeline contract/test | 1 |
| P3 | 稳定退出与舒适优先于沉浸连续性 | spatial/safety | QC-RISK、E-P1 | pause/exit/system lifecycle | 1 |
| P4 | 无检测、无评分、无疗效语言；本地记录可跳过 | product/data trust | R2/R13 | copy/data audit | 1 |
| P5 | 空间价值来自稳定方向/距离/尺度，不来自多浮窗 | spatial | F4/OPP-1 | concept/container | 2 |

- 冲突仲裁：安全/退出/统一时间轴/内容边界并列最高；其后可理解性；其后空间美感与装饰。
- 禁止：自动相机、闪烁、帧计时、传感、语音麦克风、评分、强制记录、装饰浮窗。

## 3. 任务/决策模型

| Task | Actor/context | Input evidence | Decision output | Error consequence | Frequency | Dependencies | Time target |
|---|---|---|---|---|---|---|---|
| T1 理解光语 | 初次用户/Shared Space | R3,E-U1 | 说出扩张=吸气、收回=呼气 | 无法跟随 | 首次 | none | ≤10s |
| T2 配置航程 | 用户/选择 | R4–R6,C1–C3 | duration+scene+pattern | 错时长/不适偏好 | 每次 | T1 | 每项≤5s |
| T3 同意入海 | 用户/开始前 | E-P1,QC-WINDOW | 明确确认“开始并进入沉浸场景”或留在选择 | 非预期占用 Full Space | 每次 | T2 | 1 action |
| T4 识别并选择继续 | 用户/Stage | R7–R9；当前相位文字+环形 | 指认当前 inhale/hold/exhale，并决定继续/暂停/退出 | 相位误读或不适时找不到停止 | 连续 | T3 | 每相位 glance |
| T5a 用户暂停/恢复 | 用户/Stage | R10/R11,E-S3 | 冻结或从同一快照继续 | 时间跳变/爆音 | 偶发 | T4 | 下一帧 |
| T5b 用户重开 | 用户/暂停层 | R10 | 确认从 0 开始或取消 | 误丢当前进度 | 偶发 | T5a | confirm |
| T5c 用户退出 | 用户/暂停层或 system back | R10/R12,E-P1 | 确认退出到 Shared Space 或取消 | 误退出/无稳定出口 | 偶发 | T4/T5a | confirm |
| T5d 系统挂起恢复 | 系统生命周期 | R11/R14 | 系统动作：冻结 snapshot；回前台保持 paused 待用户恢复 | 后台推进/音频突发 | 偶发 | T4 | automatic freeze |
| T6 完成去留 | 用户/完成 | R13 | 不记录/本地记录/退出 | 记录压力或误作成绩 | 每次 | T4 | 1 action optional |

- 依赖：T1→T2→T3→T4→T6 串行；T5 可在 T4 任意时刻中断；restart 与 exit 互斥且需确认。
- 竞品功能覆盖：保留环境/节奏配置；有意省略内容库、账户、AI、生物反馈、语音指导、远程操作者。

## 4. 空间价值与 2D 反事实

| Task | 空间维度 | 空间理由 | 2D 反事实 | 竞品参考 | Rating |
|---|---|---|---|---|---|
| T1 | perspective only | 灯塔远距是窗口内透视表达 | 2D 圆扩缩足够，故留在窗口 | C1–C3 仅机会层 | Low |
| T2 | none | 纯选择比较无 3D 必要 | 2D 三组选择更高效，禁止用 Stage | C1/C2 内容选择负担 | Low |
| T3 | agency, not net spatial value | 显式动作满足 Full Space agency | 2D 同样能明确开始；这是平台/安全前提而非 Stage 价值 | E-P1 | Low |
| T4 | world-locked direction,distance,depth,scale,time | 灯塔保持世界锁定参照，环境纵深与环尺度共担相位；用户视线短暂离开局部中心仍保留参照 | 2D 屏幕+耳机也能表达定向声画。Stage 的候选净增益是世界锁定参照和环境纵深；是否降低理解/注意负担属于待真人/真机验证假设，不作已证事实 | OPP-1/2,G-S1 | High-provisional |
| T5a–d | time; control plane | 暂停层只说明控制优先级，非空间净增益 | 2D dialog 足够；Stage 内采用单平面控制 | E-S3 | Low |
| T6 | none | 完成是短决策 | 2D 完成页更清楚；关闭 Stage 返回窗口 | R13 | Low |

身体/协作：不要求身体移动、无协作；simulation 只模拟光/音节奏，不模拟真实呼吸。

## 5. 三个实质不同假设

| Hypothesis | 信息模型 | 空间化 | 容器 | 路径/主交互 | 风险/成本 |
|---|---|---|---|---|---|
| A「远航灯标」 | 首屏教映射→单页配置→Stage 单焦点→完成 | 仅核心跟随高度空间化 | Shared Planar + explicit Full Stage | 手柄选择；观看固定灯塔/环 | 中；统一时间轴和音频关键 |
| B「静态环形仪表舱」 | 配置以一个可转向静态弧带分区；运行时收起为单一相位弧 | 高空间化但维持单焦点 | Full Stage，单一弧带（非多浮窗） | 手柄在弧带就地选择，无自动移动 | 仍比 Planar 配置费眼手；工程/可访问成本高 |
| C「地标序列」 | 配置、练习、完成是同一固定视点前的三个深度地标，状态切换只 crossfade | 位置/深度承担流程，但相机固定 | Full Stage only，地标互斥可见 | 手柄确认切换地标，不移动用户 | 初次即 Full Space、状态空间隐喻学习成本高 |

## 6. 选择矩阵（每项 1–5）

| Hypothesis | Efficiency | Spatial | Comfort | Domain | Safety | Access | Feasible | Unique | Total | Verdict |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|
维度锚点：Efficiency 1=长/不可达，3=基础路径可行未测，5=有实测达标；Spatial 1=2D等价，3=候选净增益未测，5=证据确认净增益；Comfort 1=违禁，3=无违禁但未真机，5=真机达标；Domain 1=漏核心，3=覆盖核心，5=覆盖且测试语义闭环；Safety 1=违约，3=有控制待验证，5=证据确认；Access 1=单输入/色彩，3=完整规格待真机，5=多样本验证；Feasible 1=未知高风险，3=可实现有关键未知，5=成熟证据；Unique 1=模板化，3=机会层差异，5=已验证市场差异。

| Hypothesis | Eff | Spatial | Comfort | Domain | Safety | Access | Feasible | Unique | Total | 逐格 evidenceRefs / Verdict |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|
| A | 3p | 3p | 3p | 4 | 4p | 3p | 3p | 3p | 26 | Eff:R3/R4/G-U1；Spatial:T4/G-S1；Comfort:E-S1/G-S1；Domain:R6–R14；Safety:QC-RISK/E-S3；Access:E-P2/A1；Feasible:G-P1；Unique:E-M1/OPP-1..5（opportunity非gap）/ Selected |
| B | 2p | 3p | 3p | 3 | 3p | 2p | 2p | 3p | 21 | Eff:T2；Spatial:P5；Comfort:G-S1；Domain:R4–R14；Safety:QC-WINDOW；Access:E-P2；Feasible:G-P1；Unique:C1/C2 opportunity / Rejected |
| C | 2p | 3p | 3p | 3 | 3p | 2p | 2p | 3p | 21 | Eff:T1/T2；Spatial:T4；Comfort:G-S1；Domain:R3–R14；Safety:E-P1；Access:E-P2；Feasible:G-P1；Unique:OPP-1 / Rejected |

- **Selected**：A「远航灯标」——把 2D 擅长的选择留在单窗口，只把稳定方向/距离/尺度/空间音场有净价值的跟随阶段放进 Full Space。
- **Market differentiation**：定位为“无检测、无评分的极简同步节奏”；依据 E-M1、E-D2、E-S3、OPP-1..5。吸收环境选择/可配置指导机会，避开内容库负担、生物反馈、远程操作者与健康效益语言。差异是可审计时间语义与空间锚点，不是竞品 UI。
- **拒绝 B**：即使收敛为单静态弧带，仍把低空间价值 T2 放入 Stage，眼手与文本缩放成本高。**拒绝 C**：即使固定相机/crossfade，也让低空间价值 T1/T2 强制进入 Full Space，agency 与效率弱。

## 7. 体验与容器架构

- **理解/选择层**：Shared Space，`BeaconWindow` Planar；首屏仅教学三元素，确认后原位切配置。异常留在窗口。
- **跟随层**：用户按“开始”后进入 Full Space，`TideStage` immersion=Full(100)，固定相机、程序化天/海/灯塔；只承载 T4。无 hand pose/anchor/plane permission。
- **中断层**：Full Space 内 `PausePanel` Planar，背景/音频/计时冻结；关闭 Stage 稳定回 Shared Space。
- 容器合法性：Shared 只 `BeaconWindow`；Stage 开启时整体 Full Space。默认 1 主窗口。Stage entry value=T4 世界锁定参照；explicit action=`user.startConfirmed`; exit=`closeStage→BeaconWindow`。

## 8. 附件决策矩阵

| Need | placement | Selected | Host | Role/persistence/frequency | Rationale | Rejected |
|---|---|---|---|---|---|---|
| 三项配置 | in-window | InlineControl | BeaconWindow | 当前步骤/每次 | 就地三选一最短 | None遗漏任务；TabBar页面语义错误；Toolbar非工具 |
| 开始 | in-window | InlineControl | BeaconWindow | 关键动作/每次 | 与配置同域 | None无入口；Coachmark非操作 |
| 退出/重开确认 | in-window overlay | AlertDialog | PausePanel | modal/偶发 | 必须回应且防误触 | InlineControl确认强度不足；None危险；Sheet过重 |
| 运行控制 | in-window | InlineControl | PausePanel | 暂停后/偶发 | 单控制层集中 | Toolbar会持续遮挡；None无恢复 |
| 空间装饰 | none | None | — | — | 灯塔/环属于 Stage 主内容非附件 | Augment制造伪空间；InlineControl无任务 |

附件无重复内容；无 TabBar/Toolbar/Subwindow/Popup/Augment/Coachmark。设备验证：手柄逐项、Dialog焦点陷阱与 Back。

## 9. Window sizing

| Window | form/unit | tier/baseline | content/view | FOV/readability | candidates | selected | min/max | policy |
|---|---|---|---|---|---|---|---|---|
| BeaconWindow | Planar dp, depth 640 | productivity; baseline1280×720; legal range | 1.75m, seated/standing, worldScale=Dynamic, ≤30s | angular design occupancy default≈58×36°, min≈46×33°, max≈64×40° (design-calibrated, device verify); content area default1216×736,min896×656,max1376×836; 56dp/12dp | 960×720 cramped;1280×800 balanced;1440×900 max sweet spot |1280×800|960×720 /1440×900|free 4:3–16:10;ContentSize|
| PausePanel | Planar dp, depth 640 | auxiliary calibrated | Stage center ~1.5m, worldScale=Dynamic, brief | default≈34×24°,min≈30×22°,max≈40×28°; content default656×416,min576×376,max776×496; targets64,text18 |640×440 cramped;720×480 balanced;840×560 max|720×480|640×440/840×560|3:2 flexible;ContentSize|

- Reflow：Large 3-column choices；Compact 3 rows；Constrained internal scroll, action fixed bottom；不整体缩放。Pause large adds descriptions, compact icon+label, constrained scroll actions。
- Shared Space 仅 BeaconWindow，无邻窗；不遮挡多应用。Stage 无 dp 尺寸。

## 10. 状态图

| State | Task/decision | Focus/container/layout/components | Data | Entry → exit | Exception/return |
|---|---|---|---|---|---|
| S0 FirstBeacon | T1 understand | halo/BeaconWindow/center/BeaconIntro | firstRun | launch→ack | Back exit app |
| S1 ChooseVoyage | T2 config | scene preview/BeaconWindow/3 groups/C2 ScenePicker+C3 DurationPatternPicker | config draft | ack→start request | invalid uses fallback/default |
| S2 EnterConfirm | T3 consent | C7 ConsentDialog overlay/BeaconWindow | frozen config | start→S3 | cancel→S1 |
| S3 Running | T4 identify/continue | C4 BreathHalo/TideStage/center; `halo.countdown` is a C4 subcomponent | timeline snapshot | openStage→complete/pause | lifecycle→S4 |
| S4 Paused | T5a | PausePanel center/control | frozen snapshot | pause/system pause→resume | back→S5 |
| S5 ExitConfirm | T5c | C7 ConsentDialog overlay/PausePanel | exit intent | exit request→S7 | cancel→S4 |
| S6 RestartConfirm | T5b | C7 ConsentDialog overlay/PausePanel | restart intent | restart request→S3 elapsed0 | cancel→S4 |
| S7 Complete | T6 | C6 CompletionMarker/BeaconWindow | optional record status | closeStage or duration→exit | write fail shows “未记录” then remains |

| Transition ID | From→To | Trigger | Action | Confirm |
|---|---|---|---|---|
| X01 | S0→S1 | user.ackMapping | revealChooser | no |
| X02 | S1→S2 | user.requestStart | freezeConfig | no |
| X03 | S2→S3 | user.startConfirmed | openStage,startTimeline | yes |
| X04 | S3→S4 | user.pause/system.onPause | snapshotElapsed,muteNow | no |
| X05 | S4→S3 | user.resume | resumeClock,audioFadeIn400 | no |
| X06 | S4→S5 | user.requestExit/system.back | openExitDialog | no |
| X07 | S5→S7 | user.confirmExit | closeStage,discardActive | yes |
| X08 | S4→S6 | user.requestRestart | openRestartDialog | no |
| X09 | S6→S3 | user.confirmRestart | resetElapsed0,resumeFade | yes |
| X10 | S3→S7 | timeline.durationReached | closeStage,showComplete | no |
| X11 | S7→S7 | user.toggleLocalRecord | writeOrRemoveLocal | no |
| X12 | S7→closed | user.exitComplete | closeWindowOrApp | no |

## 11. Flow

S0→S1→S2(confirm)→S3↔S4→(S5 exit / S6 restart / X10 complete)→S7。系统挂起总是 S4；回前台不自动播放。稳定退出 S4→S5→S7/Shared。

## 12. 眼手与手柄

- 所有目标支持 gaze+pinch；focused=2dp亮轮廓+文字，不只改色。拖拽/缩放不用于核心任务。
- Controller：ray hover；trigger=activate；thumbstick/D-pad=焦点邻接；A=确认；B/System Back：S3→S4，S4→S5，Dialog→cancel。全部菜单无手势可完成。
- exit/restart/start Stage 使用 Dialog 明确确认；错误恢复保持配置/快照，不自动继续。

## 13. Motion / Audio / Timeline

| Motion | Trigger/purpose | Duration/range | Reduce Motion | Performance fallback |
|---|---|---|---|---|
| Halo phase | timeline.phase/provide rhythm | exact BreathPattern; diameter 1.2↔2.5m, cubic ease-in-out | diameter 1.6↔1.9m + text | 32-segment ring→static outline |
| Hold | phase hold/show pause | configured ms; size locked | same | same |
| Cycle dust | cycleCompleted/subtle accumulation | 800ms fade, ≤3 particles/cycle, cap24 | no particles | disable particles |
| Stage enter/exit | X03/X07/10 | 500/350ms fade, no camera move | 250ms fade | instant opacity with audio ramp |
| Resume audio | X05/avoid pop | 400ms gain 0→target; position unchanged | same | 600ms linear |
| Hover | focus | 120ms scale≤1.03 | outline only | outline only |

Accessibility: reduceMotion on; controllerFallback complete; phase color+shape+label; textScaling 1.0–1.5 causes reflow; stableExit via B→pause→confirm. No camera animation/flashing.

### 13.4 UnifiedTimeline contract / tests

`elapsed=max(0, monotonicNow-start-pausedAccum)`; phase=`elapsed mod cycleDuration`; visual/audio/countdown/cycleIndex all pure-derived. onPause stores elapsed+cycleIndex, audio gain→0; onResume remains paused until user X05, rebase start then 400ms gain ramp. Tests: synthesize dt=1000/60, /72, /90, /120 plus irregular [8,33,120]ms; at timestamps 0..duration assert phase/progress/remaining/cycleIndex identical within 1ms and completion exact. Assert long frame crossing N boundaries emits N ordered complete-cycle events, never partial. Lifecycle test pause at 37.250s, wall advances 10s, resume snapshot still 37.250s; audio first sample gain0 and monotonic ramp; repeated pause idempotent.

## 14. 布局合成与几何

| Layout/state | Derivation | Single focus/regions | Density | Large/Compact/Constrained | Rejected |
|---|---|---|---|---|---|
| L0 S0 | T1单信息、一个继续动作 | focus=Halo；背景灯塔、下方一句；intro.ack 最低位 | 1句/1按钮 | halo 40%/48%/56%宽 | 卡片教学会超载 |
| L1 S1 | T2三串行变量、频率均每次 | focus=SceneTriptych；时长、节奏次级，开始底部 | ≤3组×3项 | 3列/纵向3组/滚动+固定开始 | 环绕菜单费眼手 |
| L3 S3 | T4相位高频、计时低频 | focus=BreathHalo世界中心；Countdown下方；环境最远 | 1相位+1计时 | Stage自适应；secondary保持中心±20° | HUD仪表削弱单焦点 |
| L4 S4 | T5决策互斥 | focus=恢复；重开/退出次级 | 3 actions | 横排/纵排/滚动 | Toolbar常驻遮挡 |
| L7 S7 | T6一句+可选记录+退出 | focus=完成文案；record/exit次级 | 1句+1 toggle+1 exit | 居中/纵向/滚动 | 统计卡/连胜被禁 |

几何：BeaconWindow center x0 y0 1280×800 z20；content inset32。Stage lighthouse x0 y-0.15 z-8m；halo同中心直径1.2–2.5m visual-only；Countdown x0 y-0.65 z-3m；PausePanel x0 y0 720×480 z80。所有主内容在 central FOV。

FOV formula: `θ=2·atan((dp/baselineDp)·tan(θbaseline/2))`; baseline=1280×720→58°×33° under Dynamic worldScale. Thus Beacon default1280×800≈58°×36°, min960×720≈46°×33°, max1440×900≈64°×40°. Pause auxiliary baseline720×480≈34°×24°. These are reproducible logical design angles, not physical device evidence.

## 15. 最低完整性门（当前）

| Check | Evidence | Verdict |
|---|---|---|
| 原则与任务 | §2–§3 | pass |
| 空间价值与概念 | §4–§6 | pass |
| Container/attachment/sizing/state | §7–§11 | pass |
| Implementation specs | Stage 10–11 pending | pending |
| Implementation specs | §12–§14 | pass |

| minimumCompletenessGate | pass |
