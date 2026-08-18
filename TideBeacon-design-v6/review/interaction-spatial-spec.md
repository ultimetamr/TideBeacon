# TideBeacon 交互与空间规格

> artifactRevision: 2 | active source@1 pm@2 uxr@1 visual@1 | V6 independent run

## 1. 任务/决策模型

| Task | actor/context | input evidence | decision output | failure | frequency/dependency |
|---|---|---|---|---|---|
| T1 读懂光语 | 初次/首屏 | R3,U1 | 确认扩张=吸气、收回=呼气 | 无法跟随 | 首次/none |
| T2 设定练习 | 用户/选择 | R4–6 | duration+scene+pattern | 时长/风格非预期 | 每次/T1 |
| T3 授权进入 | 用户/开始前 | P1,QC | 开始或取消 | 意外进入 Full Space | 每次/T2 |
| T4 跟随相位 | 用户/练习 | R7–9 | 识别当前相位，继续或中断 | 错相/无法停止 | 连续/T3 |
| T5a 暂停/恢复 | 用户/练习 | R10/11 | freeze snapshot 或同快照恢复 | 跳相/爆音 | 偶发/T4 |
| T5b 重开 | 用户/暂停 | R10 | 确认 elapsed=0 或取消 | 误丢进度 | 偶发/T5a |
| T5c 退出 | 用户/暂停 | R10/12 | 确认回选择/关闭，绝不进完成 | 误退出/假完成 | 偶发/T5a |
| T5d 系统挂起 | lifecycle/运行 | R11/14 | 自动冻结；回前台仍 paused | 后台推进/突然出声 | 偶发/T4 |
| T6 处理完成 | 用户/时长到达 | R13 | 不记录/本地记录/退出 | 记录压力 | 每次/T4 |

路径 T1→T2→T3→T4→T6；T5 中断 T4；只有 durationReached 进 T6，exit 不得伪装成完成。内容库、账户、AI、语音和生物反馈因不服务核心任务而有意省略。

## 2. Minimum completeness

Six tasks each include actor, input, decision, failure, frequency and dependencies: pass.

## 3. 空间价值与 2D 反事实

| Task | spatial judgment | rationale | 2D counterfactual | rating/evidence |
|---|---|---|---|---|
| T1 | perspective only | 远灯塔建立方向，但教学不需Stage | 平面光环完全可教映射 | low/R3 |
| T2 | none | 三组比较是平面任务 | 一个 Planar 更快 | low/R4–6 |
| T3 | agency | 显式确认进 Full Space | 2D dialog 也足够，无净空间收益 | low/P1 |
| T4 | direction+distance+scale+depth+time | 灯塔世界锁定，环大小/声场距离同表相位 | 2D 环+耳机也能完成；Stage 净收益待真人验证 | high-provisional/O1–2 |
| T5a | time/control | 暂停是时间语义而非空间价值 | 2D 暂停面板足够 | low/R10/11 |
| T5b | confirmation | 重开是破坏性短决策 | 2D dialog 足够 | low/R10 |
| T5c | stable exit | 退出不应空间化 | 2D dialog 且返回选择 | low/R10/12 |
| T5d | lifecycle/time | 系统事件没有可见空间价值 | 无 UI，仅保存 snapshot | none/R11/14 |
| T6 | none | 完成是短决策 | 2D 完成页最清楚 | low/R13 |

## 4. 三个实质不同的概念

| Concept | information/container | spatialization/navigation | interaction | tradeoff |
|---|---|---|---|---|
| A “灯塔呼吸线” | 教学/选择在单 Planar，练习才 Stage | 固定灯塔+一环；顺序状态 | 手柄聚焦/确认 | 时间轴与音频精度关键 |
| B “环形控制廊” | 配置和练习都在 Full Stage 弧廊 | 多区段围绕固定视点 | 手柄沿弧廊选择 | 低空间价值的配置变慢 |
| C “三个深度岛” | 教学/选择/完成是三个互斥深度地标 | Full Stage only，固定相机 crossfade | 手柄确认地标切换 | 隐喻学习成本高 |

## 5. 概念选择

评分锚点（各维1/3/5）：Efficiency=不可完成/路径可行未测/实测达时长；Spatial=2D更优/有候选净值/已证净值；Comfort=违禁/无违禁未真机/真机达标；Domain=漏核心/覆盖核心/业务+测试闭环；Safety=无出口/有控制未验/设备证实；Access=单输入/手柄+大文字+RM规格/多样本达标；Feasible=关键方案未定/规格可实现有未知/实现验证；Distinct=通用模板/有 O1–5 机会差异/用户验证差异。2/4仅在同维有独立相邻锚点证据时插值；未验最高4。

| C | Efficiency | Spatial | Comfort | Domain | Safety | Access | Feasible | Distinct | Total/evidence |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---|
| A | 3 | 3 | 3 | 3 | 3 | 3 | 3 | 3 | 24; Eff:T1–6路径可行未测; Spatial:§3 T4 provisional; Comfort:固定相机+RM; Domain:R3–14覆盖; Safety:稳定退出/confirm; Access:controller+textScaling+RM规格; Feasible:A3 SDK gap; Distinct:O1–5; selected |
| B | 1 | 1 | 3 | 1 | 3 | 1 | 1 | 3 | 14; Eff:T2放Stage增路径; Spatial:T2=none; Comfort:固定弧廊无相机移动; Domain:漏T5/T6; Safety:有confirm但未验; Access:仅手柄; Feasible:弧廊SDK未定; Distinct:O1机会; reject |
| C | 1 | 3 | 3 | 1 | 3 | 1 | 1 | 3 | 16; Eff:T1/T2强制Stage; Spatial:T4候选净值; Comfort:固定视点/crossfade; Domain:漏T5; Safety:互斥地标; Access:仅手柄; Feasible:地标切换未验; Distinct:O1; reject |

选 A：它把 2D 更高效的 T1/T2/T5/T6 留在平面，只让 T4 使用 Stage。市场定位为“无检测、无评分、可审计时间语义的极简空间节拍”，依据 M1/D1/O1–5。仅吸收环境选择/节奏可见/稳定退出需求，不用竞品缺口决定布局或视觉。

## 6. 体验/容器/附件

- Shared Space: `HarborWindow` Planar 承担 FirstLight、Chooser、Complete，默认只有 1 个主窗口。
- Full Space: 用户在开始确认后打开 `BeaconStage` Full(100)，固定相机，关闭 Stage 回 Shared。价值只是 T4 的方向/距离/尺度；不请求锨点/平面权限。
- `PauseWindow` 是 Full Space 内唯一控制平面，不与练习形成并列主焦点。

| Need | placement/selected/host | rationale | None/Inline comparison | device validation |
|---|---|---|---|---|
| scene/duration/pattern/start | in-window InlineControl / HarborWindow | 就地三选一 | None漏任务；Inline最短 | controller focus graph |
| enter/restart/exit | in-window Dialog / owning window | 高风险短确认 | None风险高；Inline普通按钮强度不足 | B/cancel/focus trap |
| first-view advance | existing halo focus state / HarborWindow | 不增第4对象 | None无手柄进路；新Inline控件违R3 | ray+confirm on halo |
| decoration | None | 灯塔/环是主内容 | Inline/Augment都无净值 | visual check |

## 7. Window sizing

| Window | form/baseline/view | candidates | selected/min/max/content | FOV/reflow |
|---|---|---|---|---|
| HarborWindow | Planar dp depth640; productivity baseline1280×720; ~1.75m Dynamic | 960×720 cramped;1280×800 balanced;1440×900 upper | 1280×800 / 960×720 / 1440×900; inset32 =>1216×736/896×656/1376×836 | design angles ~58×36°,46×33°,64×40°; 3cols→rows→scroll+pinned action |
| PauseWindow | Planar dp depth640; auxiliary; ~1.5m Dynamic | 640×440;720×480;840×560 | 720×480 /640×440/840×560; content656×416/576×376/776×496 | ~34×24° default; horizontal→vertical→scroll |

FOV formula `2*atan((dp/baselineDp)*tan(baselineAngle/2))`; core≥65×40° clear-zone limit, hit target≥56dp, body≥12dp. Aspect ratio flexible 4:3–16:10; resize ContentSize; no global scale. No docked attachment footprint.

## 8. State/transition graph

| State | container/focus | task/data | entry/exit | exception/return |
|---|---|---|---|---|
| S0 FirstLight | HarborWindow; halo | T1/preview | launch→halo confirm | Back→closed |
| S1 Choose | HarborWindow; scene group | T2/config draft | S0→start request | invalid→defaults |
| S2 EnterCheck | Harbor dialog | T3/frozen config | request→confirm/cancel | null/error→S1, dialog not rendered |
| S3 Running | BeaconStage; halo | T4/snapshot | confirm→duration/pause | lifecycle→S4 |
| S4 Paused | PauseWindow; resume | T5/frozen snapshot | pause→resume/restart/exit | error stays frozen; exit reachable |
| S5 RestartCheck | Pause dialog | T5b/restart intent | request→confirm/cancel | null/error→S4 non-rendered |
| S6 ExitCheck | Pause dialog | T5c/exit intent | request→confirm/cancel | null/error→S4 non-rendered |
| S7 Complete | HarborWindow; exact copy | T6/record state | duration→record/exit | write fail→未记录 |

| X | from→to | trigger/action | confirm |
|---|---|---|---|
| X01 | S0→S1 | controller.focusHalo+confirm / revealChooser | no; no visible new control |
| X02 | S1→S2 | requestStart/freezeConfig | no |
| X03 | S2→S3 | confirmEnter/openStage+startTimeline | yes |
| X04 | S3→S4 | pause or onPause/snapshot+mute | no |
| X05 | S4→S3 | resume/rebase+gain400ms | no |
| X06 | S4→S5 | restart/open dialog | no |
| X07 | S5→S3 | confirmRestart/reset0 | yes |
| X08 | S4→S6 | exit or systemBack/open dialog | no |
| X09 | S6→S1 | confirmExit/closeStage | yes |
| X10 | S3→S7 | durationReached/closeStage | no |
| X11 | S7→S7 | toggleRecord/writeOrRemove | no |
| X12 | S7→closed | exitComplete/close | no |

Controller: ray focus; trigger/A activate; stick/D-pad adjacent focus; B/SystemBack S3→S4, S4→S6, dialog→cancel. All menus require no gesture/touch.

## 9. Layout composition / placement

| Layout | derivation | single focus/regions/density | Large/Compact/Constrained | rejected |
|---|---|---|---|---|
| L0 FirstLight | T1 high-frequency mapping; R3 exact | halo focus; lighthouse/halo/copy only; 3 objects | same 3 objects, halo 40/48/56% width; copy wraps2 lines | button/card adds forbidden object |
| L1 Choose | T2 three decision variables | scene primary; duration/pattern support; start footer; max 3×3 options | 3 columns / three rows / internal scroll+pinned start | radial menu raises eye-hand cost |
| L3 Running | T4 continuous phase | halo primary; lighthouse/environment back; phase/countdown are halo subregions | world anchors remain central; labels within ±20° | HUD dashboard splits focus |
| L4 Paused | T5 low-frequency mutually exclusive decisions | resume primary; restart/exit secondary; max3 actions | horizontal / vertical / scroll+pinned resume | persistent toolbar obscures |
| L7 Complete | T6 short choice | exact copy primary; record+exit secondary | center / vertical / scroll | stats/cards violate product |

Placement: HarborWindow center `(0,0,20)`; BeaconStage lighthouse `(0,-.15,-8m)`, halo same direction diameter `1.2–2.5m`, phase/countdown `(0,-.65,-3m)` as halo children; PauseWindow `(0,0,80)`. Modal overlays owning window and traps focus; precedence modal > disabled > pressed > focused > selected > default.

## 10. Timeline test contract

For dt=1000/60,/72,/90,/120 and irregular `[8,33,120]ms`, sample identical absolute timestamps and assert phase/progress/remaining/cycleIndex difference <1ms; durationReached at exactly120000/240000/360000ms logical time and visible within one frame. Long frame crossing N cycle boundaries emits N ordered cycleCompleted events. Pause at37250ms, advance wall10s, snapshot stays37250ms; app resume remains paused until user X05; first audio sample gain0 then monotonic400ms. Repeated pause/onPause is idempotent; restart resets elapsed0; exit never enters S7.
