# Preview / QA Test Report · TideBeacon

> Active artifact revision: **4** | Bounded P-04 dispatcher closure | scope: `web_design_validation_only` | sources: interaction r10, visual r6, preview r3. Fresh Stage14 rereview pending.

## 1. Input Readiness Gate

| Input fact | Active evidence | Assertion | Verdict |
|---|---|---|---|
| design-system review | critique Stage12; `dc-gate-confirm-v8-20260813-01` | interaction r10 + visual r6 passed | pass |
| states/transitions | interaction r10 §10 | 10 states, 31 transitions; entry/exit/back/error explicit | pass |
| C1–C7 eight sections | visual r6 §5/§5.1 | seven checklist rows all pass | pass |
| renderSpec | visual r6 C1–C7 | 31 stable IDs, labels, bind, role | pass |
| dataBindings | visual r6 C1–C7 | 51 rows with target/fallback/type | pass |
| variants/states | visual r6 C1–C7 | 29 variants + 72 own states + 7 stacking records | pass |
| responsive/motion | interaction r10 §9; visual r6 §5 | four explicit scenarios | pass |
| visual grammar | visual r6 §3–4 | tokens/materials/semantics conflict-free | pass |

## 2. Preview Coverage Manifest — declared before preview generation

All rows below have `included`; source anchors are active design facts. Expected observable result is explicit, not inferred.

### 2.1 States (10)

| ID | source | trigger/entry | target/visible result | confirm | verdict |
|---|---|---|---|---|---|
| S1 | interaction §10 | launch/TR20 | exact lighthouse+halo+sentence | N/A | included |
| S2 | interaction §10 | TR1/TR13/TR18 | choices + Start | N/A | included |
| S3 | interaction §10 | TR2/TR4/TR6 | active halo/phase/countdown | entry yes | included |
| S4 | interaction §10 | TR3 | frozen user-pause square | no | included |
| S5 | interaction §10 | TR5 | frozen system-pause diamond | no | included |
| S6 | interaction §10 | TR8/TR9/TR19/TR22 | blocking exit Dialog | yes | included |
| S7 | interaction §10 | TR12/TR26 | blocking restart Dialog | yes | included |
| S8 | interaction §10 | TR7 | completion copy + optional save | no | included |
| S9 | interaction §10 | TR15 | saved/未保存 record result | no | included |
| S10 | interaction §10 | TR17 | render-safe recovery | no | included |

### 2.2 Transitions (31)

| ID | source | trigger | target/result | confirm | verdict |
|---|---|---|---|---|---|
| TR1 | interaction §10 | user.continue | S1→S2 | no | included |
| TR2 | interaction §10 | user.start | S2→S3 | yes | included |
| TR3 | interaction §10 | user.pause | S3→S4 freeze | no | included |
| TR4 | interaction §10 | user.resume | S4→S3/ramp | no | included |
| TR5 | interaction §10 | system.backgrounded | S3/S4→S5 | no | included |
| TR6 | interaction §10 | system.foregrounded | S5→origin semantic | no | included |
| TR7 | interaction §10 | durationReached | S3→S8 | no | included |
| TR8 | interaction §10 | exitRequested | S3/S4→S6 | yes | included |
| TR9 | interaction §10 | Back in S4 | S4→S6, never restart | yes | included |
| TR10 | interaction §10 | cancelExit | S6→prior | no | included |
| TR11a | interaction §10 | confirm returnThreshold | S6→S2 | yes | included |
| TR11b | interaction §10 | confirm closeApp | S6→closed | yes | included |
| TR12 | interaction §10 | restartRequested | S3/S4→S7 | yes | included |
| TR13 | interaction §10 | confirmRestart | S7→S2 | yes | included |
| TR14 | interaction §10 | cancelRestart | S7→prior | no | included |
| TR15 | interaction §10 | saveLocal | S8→S9 actual write | no | included |
| TR17 | interaction §10 | invalidOrOverflow | relevant→S10 | no | included |
| TR18 | interaction §10 | returnSelection | S10→S2 | no | included |
| TR19 | interaction §10 | Back in S1 | S1→S6 | yes | included |
| TR20 | interaction §10 | Back in S2 | S2→S1 exact-three | no | included |
| TR21 | interaction §10 | Back in S3 | reveal C4, remain S3 | no | included |
| TR22 | interaction §10 | Back in S5 | S5→S6 frozen | yes | included |
| TR23 | interaction §10 | Back in Dialog | cancel→prior | no | included |
| TR24 | interaction §10 | Back in S8/S9 | safe close/no forced write | no | included |
| TR25 | interaction §10 | Back in S10 | native stable exit | no | included |
| TR26 | interaction §10 | changePattern | S3/S4→S7 | yes | included |
| TR27 | interaction §10 | restartSafe | S10(clock)→S2 | **yes** | included; corrected after QA finding |
| TR29 | interaction §10 | exitSafe | S10→closed | no | included |
| TR31 | interaction §10 | resolveOverflow | S10→S2 | no | included |
| TR32 | interaction §10 | nativeReturnSelection | native S10→S2 | no | included |
| TR33 | interaction §10 | nativeExit | native S10→closed | no | included |

### 2.3 renderSpec elements (31)

| component | id | source | label | bind | show/hide rule |
|---|---|---|---|---|---|
| C1 | c1-lighthouse | visual C1 | 灯塔 | scene.id | S1/S3–S5 |
| C1 | c1-halo | visual C1 | 呼吸光环 | phaseProgress/continue | S1/S3–S5 |
| C1 | c1-instruction | visual C1 | exact instruction | copy.instruction | S1 only |
| C1 | c1-phase | visual C1 | 吸气 | phaseLabel | S3–S5 |
| C1 | c1-countdown | visual C1 | 02:00 | remaining | S3–S5 |
| C1 | c1-cycle-accent | visual C1 | 细微星光 | cycleIndex | boundary only |
| C1 | c1-lifecycle | visual C1 | 系统暂停 | lifecycle | conditional S4/S5 |
| C2 | c2-duration | visual C2 | 时长 | duration | S2 |
| C2 | c2-scene | visual C2 | 场景 | scene | S2 |
| C2 | c2-pattern | visual C2 | 节奏 | pattern | S2 |
| C3 | c3-start | visual C3 | 开始 | canStart | S2 |
| C3 | c3-status | visual C3 | 选择未完成 | validation.message | invalid only |
| C4 | c4-pause | visual C4 | 暂停 | userPaused | summoned |
| C4 | c4-pattern | visual C4 | 节奏 | pattern/change | summoned |
| C4 | c4-restart | visual C4 | 重新开始 | restart | summoned |
| C4 | c4-exit | visual C4 | 退出 | exit | summoned |
| C4 | c4-system-status | visual C4 | 系统暂停 | systemPaused | S5 only |
| C5 | c5-title | visual C5 | 确认退出？ | pending label | S6/S7 |
| C5 | c5-body | visual C5 | consequence | pending detail | S6/S7 |
| C5 | c5-cancel | visual C5 | 取消 | cancel | S6/S7 |
| C5 | c5-confirm | visual C5 | 确认 | confirm | S6/S7 |
| C6 | c6-copy | visual C6 | 完成一次练习 | completion copy | S8/S9 |
| C6 | c6-save | visual C6 | 保存本地记录 | requested | S8/S9 available |
| C6 | c6-result | visual C6 | record result | status | S9 only |
| C6 | c6-spinner | visual C6 | 正在保存 | writeInProgress | writing only |
| C7 | c7-icon | visual C7 | 需要恢复 | error.semantic | S10 |
| C7 | c7-message | visual C7 | 暂时无法继续。 | error.message | S10 |
| C7 | c7-action | visual C7 | 返回选择 | safe/overflow action | class conditional |
| C7 | c7-restart | visual C7 | 重试重新开始 | restartAction | clock only |
| C7 | c7-exit | visual C7 | 安全退出 | exitAction | S10 |
| C7 | c7-overflow | visual C7 | 内容过长，可滚动 | overflow | overflow only |

### 2.4 dataBindings (51)

| component | source path | target | normal sample | fallback/error sample | type |
|---|---|---|---|---|---|
| C1 | scene.id | c1-lighthouse/model | sea procedural | invalid→C7 | display |
| C1 | clock.phaseProgress | c1-halo/pose | .42 | freeze+S10 | semantic |
| C1 | copy.instruction | c1-instruction/text | exact copy | bundled exact | display |
| C1 | clock.phaseLabel | c1-phase/text | 吸气 | 暂停 | semantic |
| C1 | clock.remaining | c1-countdown/text | 01:42 | --:--+S10 | display |
| C1 | clock.cycleIndex | c1-cycle-accent/event | increment | no particle | semantic |
| C1 | lifecycle.semantic | c1-lifecycle | active | freeze+label | semantic |
| C1 | action.continue | c1-halo/onActivate | TR1 | remain S1 | semantic |
| C1 | prefs.reduceMotion | halo/particles | false | capped/off | semantic |
| C1 | prefs.textScale | text/reflow | 1.0 | wrap | semantic |
| C1 | prefs.controllerMode | halo/focus | controller | controller enabled | semantic |
| C2 | selection.duration | duration/selected | 2 分钟 | invalid→C7 | semantic |
| C2 | selection.scene | scene/selected | 海面 | invalid→C7 | semantic |
| C2 | selection.pattern | pattern/selected | 潮汐 | invalid→C7 | semantic |
| C2 | prefs.textScale | groups/reflow | 1.0 | scroll | semantic |
| C2 | prefs.controllerMode | options/focus | controller | complete | semantic |
| C3 | validation.canStart | start/enabled | true | false | semantic |
| C3 | validation.message | status/text | hidden | 选择未完成 | display |
| C3 | prefs.textScale | start/reflow | 1.0 | 96dp wrap | semantic |
| C3 | prefs.controllerMode | start/focus | controller | confirm enabled | semantic |
| C4 | lifecycle.userPaused | pause/label | false/暂停 | true/继续 | semantic |
| C4 | lifecycle.systemPaused | status+enabled | false | frozen/disabled | semantic |
| C4 | selection.patternLabel | pattern/subLabel | 潮汐 | 当前节奏 | display |
| C4 | action.changePattern | pattern/action | TR26 | disabled S5 | semantic |
| C4 | action.restart | restart/action | TR12 | disabled S5 | semantic |
| C4 | action.exit | exit/action | TR8 | stable exit | semantic |
| C4 | prefs.textScale | Grid | 1.0 | 2×2 | semantic |
| C4 | prefs.controllerMode | focus order | ordered | all operable | semantic |
| C5 | pendingAction.label | title | 确认退出？ | 确认操作？ | display |
| C5 | pendingAction.detail | body | consequence | 请确认。 | display |
| C5 | priorState | cancel target | S3/S4 | S10 safe | semantic |
| C5 | prefs.textScale | dialog | 1.0 | vertical/scroll | semantic |
| C5 | prefs.controllerMode | focus trap | cancel first | B cancels | semantic |
| C6 | copy.completion | copy/text | 完成一次练习 | exact bundled | display |
| C6 | record.requested | save/action | true→TR15 | false/Back TR24 | semantic |
| C6 | record.status | result | saved | 未保存记录 | semantic |
| C6 | record.payload | localStorage key | actual payload | no saved claim | semantic |
| C6 | record.writeInProgress | spinner/save | true | visible/disabled | semantic |
| C6 | prefs.textScale | completion | 1.0 | reflow | semantic |
| C6 | prefs.controllerMode | save/Back | controller | B→TR24 | semantic |
| C7 | error.semantic | icon | error triangle | triangle | semantic |
| C7 | error.message | message | classified | bundled copy | display |
| C7 | error.safeAction | action | TR18 | selection return | semantic |
| C7 | error.restartAction | restart | TR27 | hidden | semantic |
| C7 | error.exitAction | exit | TR29 | always stable | semantic |
| C7 | error.overflowAction | action | TR31 | plain-safe | semantic |
| C7 | error.nativeSafeAction | native safe | TR32 | bundled | semantic |
| C7 | error.nativeExitAction | native exit | TR33 | bundled | semantic |
| C7 | overflow | layout | false | scroll | semantic |
| C7 | prefs.textScale | recovery | 1.0 | scroll | semantic |
| C7 | prefs.controllerMode | focus order | safe→exit | complete | semantic |

### 2.5 Variants / own states / stacking (108)

Each row uses visual r6 component block; trigger is the lab component/state selector; expected result is named observable semantic.

| component | item | source | trigger | expected observable | verdict |
|---|---|---|---|---|---|
| C1 | variant threshold | visual C1 | select | exact three | included |
| C1 | variant active | visual C1 | select | phase/countdown | included |
| C1 | variant cycleBoundary | visual C1 | cycle button | particles once | included |
| C1 | variant reduceMotion | visual C1 | Reduce Motion | capped/no particles | included |
| C1 | variant systemPaused | visual C1 | select | diamond/frozen | included |
| C1 | variant completion | visual C1 | select | phase hidden | included |
| C2 | variant threeColumn | visual C2 | Regular/Large | 3 columns | included |
| C2 | variant stacked | visual C2 | Constrained | scroll stack | included |
| C2 | variant recordingLocked | visual C2 | select | disabled | included |
| C3 | variant haloContinuation | visual C3 | S1 | no fourth element | included |
| C3 | variant startButton | visual C3 | S2 | Start visible | included |
| C3 | variant disabledStart | visual C3 | fallback | disabled reason | included |
| C4 | variant activeControls | visual C4 | S3+select | controls | included |
| C4 | variant pausedControls | visual C4 | S4 | continue/status | included |
| C4 | variant systemPausedReadOnly | visual C4 | S5 | disabled/diamond | included |
| C4 | variant recordingLocked | visual C4 | select | hidden/locked | included |
| C5 | variant exit | visual C5 | S6 | exit copy | included |
| C5 | variant restart | visual C5 | S7 | restart copy | included |
| C6 | variant idle | visual C6 | S8 | optional save | included |
| C6 | variant recording | visual C6 | select | spinner | included |
| C6 | variant saved | visual C6 | S9 normal | saved circle | included |
| C6 | variant notSaved | visual C6 | S9 error | 未保存 | included |
| C6 | variant overflow | visual C6 | overflow | wrap | included |
| C7 | variant validation | visual C7 | select | TR18/TR29 | included |
| C7 | variant clock | visual C7 | select | TR27/TR29 | included |
| C7 | variant restart | visual C7 | select | restart/exit | included |
| C7 | variant exit | visual C7 | select | exit | included |
| C7 | variant overflow | visual C7 | select | scroll/TR31 | included |
| C7 | variant nativeFallback | visual C7 | select | native TR32/TR33 | included |
| C1 | state default | visual C1 state default | select C1/default | idle ring | included |
| C1 | state active | visual C1 state active | select C1/active | breathing ring | included |
| C1 | state focused | visual C1 state focused | select C1/focused | focus outline | included |
| C1 | state pressed | visual C1 state pressed | select C1/pressed | press feedback | included |
| C1 | state disabled | visual C1 state disabled | select C1/disabled | inert+reason | included |
| C1 | state paused | visual C1 state paused | select C1/paused | square/frozen | included |
| C1 | state systemPaused | visual C1 state systemPaused | select | diamond/frozen | included |
| C1 | state cycleBoundary | visual C1 state cycleBoundary | cycle button | particles once | included |
| C1 | state reduceMotion | visual C1 state reduceMotion | Reduce Motion | capped/no particles | included |
| C1 | state loading | visual C1 state loading | select | placeholder/正在准备 | included |
| C1 | state empty | visual C1 state empty | select | placeholder+C7 | included |
| C1 | state error | visual C1 state error | select | frozen+C7 | included |
| C1 | state overflow | visual C1 state overflow | select | wrap/clamp | included |
| C2 | state default | visual C2 state default | select | groups | included |
| C2 | state focused | visual C2 state focused | select | outline | included |
| C2 | state selected | visual C2 state selected | select | check | included |
| C2 | state pressed | visual C2 state pressed | select | press | included |
| C2 | state disabled | visual C2 state disabled | select | 不可用 | included |
| C2 | state loading | visual C2 state loading | select | 正在准备 | included |
| C2 | state empty | visual C2 state empty | select | 无可用节奏 | included |
| C2 | state error | visual C2 state error | select | triangle+C7 | included |
| C2 | state overflow | visual C2 state overflow | select | scroll/wrap | included |
| C3 | state default | visual C3 state default | select | solid Start | included |
| C3 | state focused | visual C3 state focused | select | outline | included |
| C3 | state pressed | visual C3 state pressed | select | press | included |
| C3 | state disabled | visual C3 state disabled | select | reason | included |
| C3 | state loading | visual C3 state loading | select | spinner | included |
| C3 | state empty | visual C3 state empty | select | missing choice | included |
| C3 | state error | visual C3 state error | select | C7 | included |
| C3 | state overflow | visual C3 state overflow | select | 96dp wrap | included |
| C4 | state default | visual C4 state default | select | controls | included |
| C4 | state focused | visual C4 state focused | select | outline | included |
| C4 | state pressed | visual C4 state pressed | select | press | included |
| C4 | state paused | visual C4 state paused | S4 | square/继续 | included |
| C4 | state systemPaused | visual C4 state systemPaused | S5 | diamond/disabled | included |
| C4 | state disabled | visual C4 state disabled | select | muted/reason | included |
| C4 | state loading | visual C4 state loading | select | spinner | included |
| C4 | state empty | visual C4 state empty | select | stable exit | included |
| C4 | state error | visual C4 state error | select | C7 | included |
| C4 | state overflow | visual C4 state overflow | select | 2×2 | included |
| C5 | state default | visual C5 state default | S6 | modal | included |
| C5 | state focused | visual C5 state focused | select | trapped outline | included |
| C5 | state pressed | visual C5 state pressed | select | press | included |
| C5 | state disabled | visual C5 state disabled | select | muted | included |
| C5 | state loading | visual C5 state loading | select | spinner | included |
| C5 | state error | visual C5 state error | select | retry/cancel | included |
| C5 | state overflow | visual C5 state overflow | select | stacked/scroll | included |
| C5 | state empty | visual C5 state empty | select | safe C7 | included |
| C6 | state default | visual C6 state default | S8 | copy/save | included |
| C6 | state focused | visual C6 state focused | select | outline | included |
| C6 | state pressed | visual C6 state pressed | select | press | included |
| C6 | state recording | visual C6 state recording | select | spinner | included |
| C6 | state loading | visual C6 state loading | select | 正在准备 | included |
| C6 | state disabled | visual C6 state disabled | select | save muted | included |
| C6 | state saved | visual C6 state saved | S9 normal | circle+saved | included |
| C6 | state empty | visual C6 state empty | select | optional action | included |
| C6 | state error | visual C6 state error | S9 error | 未保存 | included |
| C6 | state permission_denied | visual C6 state permission_denied | select | 未保存 | included |
| C6 | state overflow | visual C6 state overflow | select | wrap | included |
| C7 | state default | visual C7 state default | S10 | recovery | included |
| C7 | state focused | visual C7 state focused | select | outline | included |
| C7 | state pressed | visual C7 state pressed | select | press | included |
| C7 | state loading | visual C7 state loading | select | 正在恢复 | included |
| C7 | state empty | visual C7 state empty | select | native fallback | included |
| C7 | state error | visual C7 state error | select | native plain | included |
| C7 | state overflow | visual C7 state overflow | select | scroll/TR31 | included |
| C7 | state disabled | visual C7 state disabled | select | exit remains | included |
| C7 | state validation | visual C7 state validation | select | TR18/TR29 | included |
| C7 | state clock | visual C7 state clock | select | TR27/TR29 | included |
| C7 | state restart | visual C7 state restart | select | restart+exit | included |
| C7 | state exit | visual C7 state exit | select | safe exit | included |
| C7 | state nativeFallback | visual C7 state nativeFallback | select | TR32/TR33 | included |
| C1 | stacking | visual C1 | combined focus/pause/error | declared precedence | included |
| C2 | stacking | visual C2 | selected+focused/error | declared precedence | included |
| C3 | stacking | visual C3 | loading+disabled/focus | declared precedence | included |
| C4 | stacking | visual C4 | systemPaused+focus | declared precedence | included |
| C5 | stacking | visual C5 | modal+loading/error | declared precedence | included |
| C6 | stacking | visual C6 | recording+focus/error | declared precedence | included |
| C7 | stacking | visual C7 | native/error/overflow | declared precedence | included |

### 2.6 Responsive / motion (4)

| scenario | source | tier/content | trigger | structural result | verdict |
|---|---|---|---|---|---|
| Large | interaction §9 | 1440×900/max | Large button | wide 3-column composition | included |
| Compact | interaction §9 | 960×640/intermediate | Compact button | compact content | included |
| Constrained | interaction §9 | 720×560/min, content656×496 | Constrained button | preview hidden; C2 392 scroll+16+C3 72=480 | included |
| Reduce Motion | visual C1 | N/A | Reduce Motion | halo capped; particles off | included |

## 3. Generation-side Implementation Maps

### 3.1 State / transition → scene

Every §2.1/§2.2 row maps one-to-one: state selector `[data-state="<S-ID>"]`; transition selector `[data-transition="<TR-ID>"]`; trigger click; expected target is the §2 row; actual result/verdict reserved for independent QA. High-risk IDs route through `[role="dialog"]` with `[data-preview-id="c5-confirm"]` and `c5-cancel`.

| denominator | manifest total | stable selector rule | trigger | source | generator status |
|---|---:|---|---|---|---|
| states | 10 | `[data-state=S1..S10]` | state buttons | §2.1 / interaction §10 | mapped item-by-item |
| transitions | 31 | `[data-transition=TR*]` | transition buttons or matching `data-action` | §2.2 / interaction §10 | mapped item-by-item |

### 3.2 renderSpec → DOM

Each of the 31 rows in §2.3 maps to the unique selector `[data-preview-id="<element id>"]`; state/variant is its conditional trigger; hidden means absent from the rendered scene and is reported by `#inspector`. No element ID is merged.

### 3.3 dataBindings → data/fallback

Each of the 51 §2.4 paths maps to `[data-binding~="<source path>"]`; Normal/Fallback/Error buttons set the sample mode, while specific semantic actions use `[data-action]`. Normal and fallback samples are exactly those in §2.4. `record.payload` additionally maps to JS/localStorage semantics and never displays fabricated success.

### 3.4 variants / component states

Each of 29 variants and 72 own states has its own `<option>` in `#componentState` after choosing `#componentSelect`; Apply is the trigger. Each of seven stacking combinations is invoked by selecting its higher-precedence state while the corresponding top-level state supplies the lower state. Stable evidence: `componentStates`, `variants`, `componentDemo`, and `render()`.

### 3.5 Responsive / Reduce Motion

| scenario | selector | trigger | structural/motion evidence | source | generator status |
|---|---|---|---|---|---|
| Large | `[data-tier=large]` | click Large | `viewport[data-tier=large]`; 3 columns | §2.6 | mapped |
| Compact | `[data-tier=compact]` | click Compact | compact viewport, targets unchanged | §2.6 | mapped |
| Constrained | `[data-tier=constrained]` | click Constrained | `.scene-preview` hidden; scroll/stack | §2.6 | mapped |
| Reduce Motion | `#reduceBtn` | click | `.reduced .halo`; `.reduced .particles` | §2.6 | mapped |

## 4. Declarative Generator Checklist

| Check | source denominator | selector/structure | trigger | expected | actual | verdict |
|---|---|---|---|---|---|---|
| Manifest complete | §2 all 235 facts (10+31+31+51+108+4) | Markdown §2 | manual source recount | no gaps/dangling | reserved for QA | pending QA |
| state machine | §2.1–2.2 | `states`,`transitions`,`render()` | buttons/actions | distinct states/targets | reserved | pending QA |
| DOM lookup | §2.3 | `data-preview-id` | state/variant | unique visible/hidden | reserved | pending QA |
| data modes | §2.4 | `data-binding`, mode buttons | Normal/Fallback/Error | bound changes | reserved | pending QA |
| high-risk Dialog | confirmed §2.2 rows | `[role=dialog]` | TR2/TR8/TR9/TR12/TR19/TR22/TR26 | blocks + confirm/cancel | reserved | pending QA |
| responsive/motion | §2.6 | `data-tier`, `.reduced` | four buttons | reflow/fallback | reserved | pending QA |

## 5. Denominator Reconciliation

| type | design-fact | manifest | QA rebuilt | difference | verdict |
|---|---:|---:|---:|---:|---|
| states | 10 | 10 | pending | pending | pending |
| transitions | 31 | 31 | pending | pending | pending |
| renderSpec | 31 | 31 | pending | pending | pending |
| dataBindings | 51 | 51 | pending | pending | pending |
| variants+own states+stacking | 108 | 108 | pending | pending | pending |
| responsive/Reduce Motion | 4 | 4 | pending | pending | pending |

## 6. Requirements / samples / boundary

PM R1–R18 and T1–T9 map through states/components in visual r6 Table B and are triggerable here; generator coverage 18/18, pending independent QA. Normal samples are Chinese human-readable values in §2.4; fallback/error never echoes machine enums. Web tolerance: stable ID relationships and declared token consumption only; excludes screenshot diff, CSS-pixel→PICO physical sizing, device color delta, and Web/PICO parity.

| Device item | status |
|---|---|
| physical distance/readability, occlusion, fatigue, controller precision, PICO runtime/performance/safety | `not_performed`; requires device validation |
| Web logical design-fact coverage | pending independent Preview QA |

## 7. QA Invocation / Hard Gate

| gate | reviewerRole | invocationId | contextPolicy | reviewed revision | rebuilt | verdict |
|---|---|---|---|---|---|---|
| Preview implementation | prototype_qa_reviewer | `prototype-qa-v8-20260813-01` | fresh_context / isolated_subagent | preview r1 / report r1 / interaction r10 / visual r6 | yes | **block** |

| field | value |
|---|---|
| previewImplementationFidelity | block |
| minimumCompletenessGate | block |
| designStatusImpact | invalid |
| deviceValidation.status | not_performed |

## 9. P-04 Per-item Implementation / Independent-QA Worksheet

> This 235-row worksheet supersedes the aggregate §3 mapping prose for Stage 14. Generator columns are source/selector/trigger/expected. Only the fresh independent reviewer may fill Actual/Verdict.

| Item | Source fact | Stable selector | Real trigger path | Expected observable result | Actual result | Verdict |
|---|---|---|---|---|---|---|
| state S1 | interaction §10 | `[data-state="S1"]` | click state S1 | exact lighthouse+halo+sentence | pending independent QA | pending |
| state S2 | interaction §10 | `[data-state="S2"]` | click state S2 | choices + Start | pending independent QA | pending |
| state S3 | interaction §10 | `[data-state="S3"]` | click state S3 | active halo/phase/countdown | pending independent QA | pending |
| state S4 | interaction §10 | `[data-state="S4"]` | click state S4 | frozen user-pause square | pending independent QA | pending |
| state S5 | interaction §10 | `[data-state="S5"]` | click state S5 | frozen system-pause diamond | pending independent QA | pending |
| state S6 | interaction §10 | `[data-state="S6"]` | click state S6 | blocking exit Dialog | pending independent QA | pending |
| state S7 | interaction §10 | `[data-state="S7"]` | click state S7 | blocking restart Dialog | pending independent QA | pending |
| state S8 | interaction §10 | `[data-state="S8"]` | click state S8 | completion copy + optional save | pending independent QA | pending |
| state S9 | interaction §10 | `[data-state="S9"]` | click state S9 | saved/未保存 record result | pending independent QA | pending |
| state S10 | interaction §10 | `[data-state="S10"]` | click state S10 | render-safe recovery | pending independent QA | pending |
| transition TR1 | interaction §10 | `[data-transition="TR1"]` | set source state; click TR1 | S1→S2; confirmation=no | pending independent QA | pending |
| transition TR2 | interaction §10 | `[data-transition="TR2"]` | set source state; click TR2 then confirm [data-confirm-transition] [data-preview-id=c5-confirm] | S2→S3; confirmation=yes | pending independent QA | pending |
| transition TR3 | interaction §10 | `[data-transition="TR3"]` | set source state; click TR3 | S3→S4 freeze; confirmation=no | pending independent QA | pending |
| transition TR4 | interaction §10 | `[data-transition="TR4"]` | set source state; click TR4 | S4→S3/ramp; confirmation=no | pending independent QA | pending |
| transition TR5 | interaction §10 | `[data-transition="TR5"]` | set source state; click TR5 | S3/S4→S5; confirmation=no | pending independent QA | pending |
| transition TR6 | interaction §10 | `[data-transition="TR6"]` | set source state; click TR6 | S5→origin semantic; confirmation=no | pending independent QA | pending |
| transition TR7 | interaction §10 | `[data-transition="TR7"]` | set source state; click TR7 | S3→S8; confirmation=no | pending independent QA | pending |
| transition TR8 | interaction §10 | `[data-transition="TR8"]` | set source state; click TR8 | S3/S4→S6; confirmation=yes | pending independent QA | pending |
| transition TR9 | interaction §10 | `[data-transition="TR9"]` | set source state; click TR9 | S4→S6, never restart; confirmation=yes | pending independent QA | pending |
| transition TR10 | interaction §10 | `[data-transition="TR10"]` | set source state; click TR10 | S6→prior; confirmation=no | pending independent QA | pending |
| transition TR11a | interaction §10 | `[data-transition="TR11a"]` | set source state; click TR11a | S6→S2; confirmation=yes | pending independent QA | pending |
| transition TR11b | interaction §10 | `[data-transition="TR11b"]` | set source state; click TR11b | S6→closed; confirmation=yes | pending independent QA | pending |
| transition TR12 | interaction §10 | `[data-transition="TR12"]` | set source state; click TR12 | S3/S4→S7; confirmation=yes | pending independent QA | pending |
| transition TR13 | interaction §10 | `[data-transition="TR13"]` | set source state; click TR13 | S7→S2; confirmation=yes | pending independent QA | pending |
| transition TR14 | interaction §10 | `[data-transition="TR14"]` | set source state; click TR14 | S7→prior; confirmation=no | pending independent QA | pending |
| transition TR15 | interaction §10 | `[data-transition="TR15"]` | set source state; click TR15 | S8→S9 actual write; confirmation=no | pending independent QA | pending |
| transition TR17 | interaction §10 | `[data-transition="TR17"]` | set source state; click TR17 | relevant→S10; confirmation=no | pending independent QA | pending |
| transition TR18 | interaction §10 | `[data-transition="TR18"]` | set source state; click TR18 | S10→S2; confirmation=no | pending independent QA | pending |
| transition TR19 | interaction §10 | `[data-transition="TR19"]` | set source state; click TR19 | S1→S6; confirmation=yes | pending independent QA | pending |
| transition TR20 | interaction §10 | `[data-transition="TR20"]` | set source state; click TR20 | S2→S1 exact-three; confirmation=no | pending independent QA | pending |
| transition TR21 | interaction §10 | `[data-transition="TR21"]` | set source state; click TR21 | reveal C4, remain S3; confirmation=no | pending independent QA | pending |
| transition TR22 | interaction §10 | `[data-transition="TR22"]` | set source state; click TR22 | S5→S6 frozen; confirmation=yes | pending independent QA | pending |
| transition TR23 | interaction §10 | `[data-transition="TR23"]` | set source state; click TR23 | cancel→prior; confirmation=no | pending independent QA | pending |
| transition TR24 | interaction §10 | `[data-transition="TR24"]` | set source state; click TR24 | safe close/no forced write; confirmation=no | pending independent QA | pending |
| transition TR25 | interaction §10 | `[data-transition="TR25"]` | set source state; click TR25 | native stable exit; confirmation=no | pending independent QA | pending |
| transition TR26 | interaction §10 | `[data-transition="TR26"]` | set source state; click TR26 | S3/S4→S7; confirmation=yes | pending independent QA | pending |
| transition TR27 | interaction §10 | `[data-transition="TR27"]` | set source state; click TR27 then confirm [data-confirm-transition] [data-preview-id=c5-confirm] | S10(clock)→S2; confirmation=**yes** | pending independent QA | pending |
| transition TR29 | interaction §10 | `[data-transition="TR29"]` | set source state; click TR29 | S10→closed; confirmation=no | pending independent QA | pending |
| transition TR31 | interaction §10 | `[data-transition="TR31"]` | set source state; click TR31 | S10→S2; confirmation=no | pending independent QA | pending |
| transition TR32 | interaction §10 | `[data-transition="TR32"]` | set source state; click TR32 | native S10→S2; confirmation=no | pending independent QA | pending |
| transition TR33 | interaction §10 | `[data-transition="TR33"]` | set source state; click TR33 | native S10→closed; confirmation=no | pending independent QA | pending |
| element C1.c1-lighthouse | visual C1 | `[data-preview-id="c1-lighthouse"]` | S1/S3–S5 | label=灯塔; bind=scene.id | pending independent QA | pending |
| element C1.c1-halo | visual C1 | `[data-preview-id="c1-halo"]` | S1/S3–S5 | label=呼吸光环; bind=phaseProgress/continue | pending independent QA | pending |
| element C1.c1-instruction | visual C1 | `[data-preview-id="c1-instruction"]` | S1 only | label=exact instruction; bind=copy.instruction | pending independent QA | pending |
| element C1.c1-phase | visual C1 | `[data-preview-id="c1-phase"]` | S3–S5 | label=吸气; bind=phaseLabel | pending independent QA | pending |
| element C1.c1-countdown | visual C1 | `[data-preview-id="c1-countdown"]` | S3–S5 | label=02:00; bind=remaining | pending independent QA | pending |
| element C1.c1-cycle-accent | visual C1 | `[data-preview-id="c1-cycle-accent"]` | boundary only | label=细微星光; bind=cycleIndex | pending independent QA | pending |
| element C1.c1-lifecycle | visual C1 | `[data-preview-id="c1-lifecycle"]` | conditional S4/S5 | label=系统暂停; bind=lifecycle | pending independent QA | pending |
| element C2.c2-duration | visual C2 | `[data-preview-id="c2-duration"]` | S2 | label=时长; bind=duration | pending independent QA | pending |
| element C2.c2-scene | visual C2 | `[data-preview-id="c2-scene"]` | S2 | label=场景; bind=scene | pending independent QA | pending |
| element C2.c2-pattern | visual C2 | `[data-preview-id="c2-pattern"]` | S2 | label=节奏; bind=pattern | pending independent QA | pending |
| element C3.c3-start | visual C3 | `[data-preview-id="c3-start"]` | S2 | label=开始; bind=canStart | pending independent QA | pending |
| element C3.c3-status | visual C3 | `[data-preview-id="c3-status"]` | invalid only | label=选择未完成; bind=validation.message | pending independent QA | pending |
| element C4.c4-pause | visual C4 | `[data-preview-id="c4-pause"]` | summoned | label=暂停; bind=userPaused | pending independent QA | pending |
| element C4.c4-pattern | visual C4 | `[data-preview-id="c4-pattern"]` | summoned | label=节奏; bind=pattern/change | pending independent QA | pending |
| element C4.c4-restart | visual C4 | `[data-preview-id="c4-restart"]` | summoned | label=重新开始; bind=restart | pending independent QA | pending |
| element C4.c4-exit | visual C4 | `[data-preview-id="c4-exit"]` | summoned | label=退出; bind=exit | pending independent QA | pending |
| element C4.c4-system-status | visual C4 | `[data-preview-id="c4-system-status"]` | S5 only | label=系统暂停; bind=systemPaused | pending independent QA | pending |
| element C5.c5-title | visual C5 | `[data-preview-id="c5-title"]` | S6/S7 | label=确认退出？; bind=pending label | pending independent QA | pending |
| element C5.c5-body | visual C5 | `[data-preview-id="c5-body"]` | S6/S7 | label=consequence; bind=pending detail | pending independent QA | pending |
| element C5.c5-cancel | visual C5 | `[data-preview-id="c5-cancel"]` | S6/S7 | label=取消; bind=cancel | pending independent QA | pending |
| element C5.c5-confirm | visual C5 | `[data-preview-id="c5-confirm"]` | S6/S7 | label=确认; bind=confirm | pending independent QA | pending |
| element C6.c6-copy | visual C6 | `[data-preview-id="c6-copy"]` | S8/S9 | label=完成一次练习; bind=completion copy | pending independent QA | pending |
| element C6.c6-save | visual C6 | `[data-preview-id="c6-save"]` | S8/S9 available | label=保存本地记录; bind=requested | pending independent QA | pending |
| element C6.c6-result | visual C6 | `[data-preview-id="c6-result"]` | S9 only | label=record result; bind=status | pending independent QA | pending |
| element C6.c6-spinner | visual C6 | `[data-preview-id="c6-spinner"]` | writing only | label=正在保存; bind=writeInProgress | pending independent QA | pending |
| element C7.c7-icon | visual C7 | `[data-preview-id="c7-icon"]` | S10 | label=需要恢复; bind=error.semantic | pending independent QA | pending |
| element C7.c7-message | visual C7 | `[data-preview-id="c7-message"]` | S10 | label=暂时无法继续。; bind=error.message | pending independent QA | pending |
| element C7.c7-action | visual C7 | `[data-preview-id="c7-action"]` | class conditional | label=返回选择; bind=safe/overflow action | pending independent QA | pending |
| element C7.c7-restart | visual C7 | `[data-preview-id="c7-restart"]` | clock only | label=重试重新开始; bind=restartAction | pending independent QA | pending |
| element C7.c7-exit | visual C7 | `[data-preview-id="c7-exit"]` | S10 | label=安全退出; bind=exitAction | pending independent QA | pending |
| element C7.c7-overflow | visual C7 | `[data-preview-id="c7-overflow"]` | overflow only | label=内容过长，可滚动; bind=overflow | pending independent QA | pending |
| binding C1:scene.id | visual C1 dataBindings | `[data-binding-id="C1:scene.id"]` | open inspector; Normal then Fallback/Error | normal=sea procedural; fallback=invalid→C7; type=display | pending independent QA | pending |
| binding C1:clock.phaseProgress | visual C1 dataBindings | `[data-binding-id="C1:clock.phaseProgress"]` | open inspector; Normal then Fallback/Error | normal=.42; fallback=freeze+S10; type=semantic | pending independent QA | pending |
| binding C1:copy.instruction | visual C1 dataBindings | `[data-binding-id="C1:copy.instruction"]` | open inspector; Normal then Fallback/Error | normal=exact copy; fallback=bundled exact; type=display | pending independent QA | pending |
| binding C1:clock.phaseLabel | visual C1 dataBindings | `[data-binding-id="C1:clock.phaseLabel"]` | open inspector; Normal then Fallback/Error | normal=吸气; fallback=暂停; type=semantic | pending independent QA | pending |
| binding C1:clock.remaining | visual C1 dataBindings | `[data-binding-id="C1:clock.remaining"]` | open inspector; Normal then Fallback/Error | normal=01:42; fallback=--:--+S10; type=display | pending independent QA | pending |
| binding C1:clock.cycleIndex | visual C1 dataBindings | `[data-binding-id="C1:clock.cycleIndex"]` | open inspector; Normal then Fallback/Error | normal=increment; fallback=no particle; type=semantic | pending independent QA | pending |
| binding C1:lifecycle.semantic | visual C1 dataBindings | `[data-binding-id="C1:lifecycle.semantic"]` | open inspector; Normal then Fallback/Error | normal=active; fallback=freeze+label; type=semantic | pending independent QA | pending |
| binding C1:action.continue | visual C1 dataBindings | `[data-binding-id="C1:action.continue"]` | open inspector; Normal then Fallback/Error | normal=TR1; fallback=remain S1; type=semantic | pending independent QA | pending |
| binding C1:prefs.reduceMotion | visual C1 dataBindings | `[data-binding-id="C1:prefs.reduceMotion"]` | open inspector; Normal then Fallback/Error | normal=false; fallback=capped/off; type=semantic | pending independent QA | pending |
| binding C1:prefs.textScale | visual C1 dataBindings | `[data-binding-id="C1:prefs.textScale"]` | open inspector; Normal then Fallback/Error | normal=1.0; fallback=wrap; type=semantic | pending independent QA | pending |
| binding C1:prefs.controllerMode | visual C1 dataBindings | `[data-binding-id="C1:prefs.controllerMode"]` | open inspector; Normal then Fallback/Error | normal=controller; fallback=controller enabled; type=semantic | pending independent QA | pending |
| binding C2:selection.duration | visual C2 dataBindings | `[data-binding-id="C2:selection.duration"]` | open inspector; Normal then Fallback/Error | normal=2 分钟; fallback=invalid→C7; type=semantic | pending independent QA | pending |
| binding C2:selection.scene | visual C2 dataBindings | `[data-binding-id="C2:selection.scene"]` | open inspector; Normal then Fallback/Error | normal=海面; fallback=invalid→C7; type=semantic | pending independent QA | pending |
| binding C2:selection.pattern | visual C2 dataBindings | `[data-binding-id="C2:selection.pattern"]` | open inspector; Normal then Fallback/Error | normal=潮汐; fallback=invalid→C7; type=semantic | pending independent QA | pending |
| binding C2:prefs.textScale | visual C2 dataBindings | `[data-binding-id="C2:prefs.textScale"]` | open inspector; Normal then Fallback/Error | normal=1.0; fallback=scroll; type=semantic | pending independent QA | pending |
| binding C2:prefs.controllerMode | visual C2 dataBindings | `[data-binding-id="C2:prefs.controllerMode"]` | open inspector; Normal then Fallback/Error | normal=controller; fallback=complete; type=semantic | pending independent QA | pending |
| binding C3:validation.canStart | visual C3 dataBindings | `[data-binding-id="C3:validation.canStart"]` | open inspector; Normal then Fallback/Error | normal=true; fallback=false; type=semantic | pending independent QA | pending |
| binding C3:validation.message | visual C3 dataBindings | `[data-binding-id="C3:validation.message"]` | open inspector; Normal then Fallback/Error | normal=hidden; fallback=选择未完成; type=display | pending independent QA | pending |
| binding C3:prefs.textScale | visual C3 dataBindings | `[data-binding-id="C3:prefs.textScale"]` | open inspector; Normal then Fallback/Error | normal=1.0; fallback=96dp wrap; type=semantic | pending independent QA | pending |
| binding C3:prefs.controllerMode | visual C3 dataBindings | `[data-binding-id="C3:prefs.controllerMode"]` | open inspector; Normal then Fallback/Error | normal=controller; fallback=confirm enabled; type=semantic | pending independent QA | pending |
| binding C4:lifecycle.userPaused | visual C4 dataBindings | `[data-binding-id="C4:lifecycle.userPaused"]` | open inspector; Normal then Fallback/Error | normal=false/暂停; fallback=true/继续; type=semantic | pending independent QA | pending |
| binding C4:lifecycle.systemPaused | visual C4 dataBindings | `[data-binding-id="C4:lifecycle.systemPaused"]` | open inspector; Normal then Fallback/Error | normal=false; fallback=frozen/disabled; type=semantic | pending independent QA | pending |
| binding C4:selection.patternLabel | visual C4 dataBindings | `[data-binding-id="C4:selection.patternLabel"]` | open inspector; Normal then Fallback/Error | normal=潮汐; fallback=当前节奏; type=display | pending independent QA | pending |
| binding C4:action.changePattern | visual C4 dataBindings | `[data-binding-id="C4:action.changePattern"]` | open inspector; Normal then Fallback/Error | normal=TR26; fallback=disabled S5; type=semantic | pending independent QA | pending |
| binding C4:action.restart | visual C4 dataBindings | `[data-binding-id="C4:action.restart"]` | open inspector; Normal then Fallback/Error | normal=TR12; fallback=disabled S5; type=semantic | pending independent QA | pending |
| binding C4:action.exit | visual C4 dataBindings | `[data-binding-id="C4:action.exit"]` | open inspector; Normal then Fallback/Error | normal=TR8; fallback=stable exit; type=semantic | pending independent QA | pending |
| binding C4:prefs.textScale | visual C4 dataBindings | `[data-binding-id="C4:prefs.textScale"]` | open inspector; Normal then Fallback/Error | normal=1.0; fallback=2×2; type=semantic | pending independent QA | pending |
| binding C4:prefs.controllerMode | visual C4 dataBindings | `[data-binding-id="C4:prefs.controllerMode"]` | open inspector; Normal then Fallback/Error | normal=ordered; fallback=all operable; type=semantic | pending independent QA | pending |
| binding C5:pendingAction.label | visual C5 dataBindings | `[data-binding-id="C5:pendingAction.label"]` | open inspector; Normal then Fallback/Error | normal=确认退出？; fallback=确认操作？; type=display | pending independent QA | pending |
| binding C5:pendingAction.detail | visual C5 dataBindings | `[data-binding-id="C5:pendingAction.detail"]` | open inspector; Normal then Fallback/Error | normal=consequence; fallback=请确认。; type=display | pending independent QA | pending |
| binding C5:priorState | visual C5 dataBindings | `[data-binding-id="C5:priorState"]` | open inspector; Normal then Fallback/Error | normal=S3/S4; fallback=S10 safe; type=semantic | pending independent QA | pending |
| binding C5:prefs.textScale | visual C5 dataBindings | `[data-binding-id="C5:prefs.textScale"]` | open inspector; Normal then Fallback/Error | normal=1.0; fallback=vertical/scroll; type=semantic | pending independent QA | pending |
| binding C5:prefs.controllerMode | visual C5 dataBindings | `[data-binding-id="C5:prefs.controllerMode"]` | open inspector; Normal then Fallback/Error | normal=cancel first; fallback=B cancels; type=semantic | pending independent QA | pending |
| binding C6:copy.completion | visual C6 dataBindings | `[data-binding-id="C6:copy.completion"]` | open inspector; Normal then Fallback/Error | normal=完成一次练习; fallback=exact bundled; type=display | pending independent QA | pending |
| binding C6:record.requested | visual C6 dataBindings | `[data-binding-id="C6:record.requested"]` | open inspector; Normal then Fallback/Error | normal=true→TR15; fallback=false/Back TR24; type=semantic | pending independent QA | pending |
| binding C6:record.status | visual C6 dataBindings | `[data-binding-id="C6:record.status"]` | open inspector; Normal then Fallback/Error | normal=saved; fallback=未保存记录; type=semantic | pending independent QA | pending |
| binding C6:record.payload | visual C6 dataBindings | `[data-binding-id="C6:record.payload"]` | open inspector; Normal then Fallback/Error | normal=actual payload; fallback=no saved claim; type=semantic | pending independent QA | pending |
| binding C6:record.writeInProgress | visual C6 dataBindings | `[data-binding-id="C6:record.writeInProgress"]` | open inspector; Normal then Fallback/Error | normal=true; fallback=visible/disabled; type=semantic | pending independent QA | pending |
| binding C6:prefs.textScale | visual C6 dataBindings | `[data-binding-id="C6:prefs.textScale"]` | open inspector; Normal then Fallback/Error | normal=1.0; fallback=reflow; type=semantic | pending independent QA | pending |
| binding C6:prefs.controllerMode | visual C6 dataBindings | `[data-binding-id="C6:prefs.controllerMode"]` | open inspector; Normal then Fallback/Error | normal=controller; fallback=B→TR24; type=semantic | pending independent QA | pending |
| binding C7:error.semantic | visual C7 dataBindings | `[data-binding-id="C7:error.semantic"]` | open inspector; Normal then Fallback/Error | normal=error triangle; fallback=triangle; type=semantic | pending independent QA | pending |
| binding C7:error.message | visual C7 dataBindings | `[data-binding-id="C7:error.message"]` | open inspector; Normal then Fallback/Error | normal=classified; fallback=bundled copy; type=display | pending independent QA | pending |
| binding C7:error.safeAction | visual C7 dataBindings | `[data-binding-id="C7:error.safeAction"]` | open inspector; Normal then Fallback/Error | normal=TR18; fallback=selection return; type=semantic | pending independent QA | pending |
| binding C7:error.restartAction | visual C7 dataBindings | `[data-binding-id="C7:error.restartAction"]` | open inspector; Normal then Fallback/Error | normal=TR27; fallback=hidden; type=semantic | pending independent QA | pending |
| binding C7:error.exitAction | visual C7 dataBindings | `[data-binding-id="C7:error.exitAction"]` | open inspector; Normal then Fallback/Error | normal=TR29; fallback=always stable; type=semantic | pending independent QA | pending |
| binding C7:error.overflowAction | visual C7 dataBindings | `[data-binding-id="C7:error.overflowAction"]` | open inspector; Normal then Fallback/Error | normal=TR31; fallback=plain-safe; type=semantic | pending independent QA | pending |
| binding C7:error.nativeSafeAction | visual C7 dataBindings | `[data-binding-id="C7:error.nativeSafeAction"]` | open inspector; Normal then Fallback/Error | normal=TR32; fallback=bundled; type=semantic | pending independent QA | pending |
| binding C7:error.nativeExitAction | visual C7 dataBindings | `[data-binding-id="C7:error.nativeExitAction"]` | open inspector; Normal then Fallback/Error | normal=TR33; fallback=bundled; type=semantic | pending independent QA | pending |
| binding C7:overflow | visual C7 dataBindings | `[data-binding-id="C7:overflow"]` | open inspector; Normal then Fallback/Error | normal=false; fallback=scroll; type=semantic | pending independent QA | pending |
| binding C7:prefs.textScale | visual C7 dataBindings | `[data-binding-id="C7:prefs.textScale"]` | open inspector; Normal then Fallback/Error | normal=1.0; fallback=scroll; type=semantic | pending independent QA | pending |
| binding C7:prefs.controllerMode | visual C7 dataBindings | `[data-binding-id="C7:prefs.controllerMode"]` | open inspector; Normal then Fallback/Error | normal=safe→exit; fallback=complete; type=semantic | pending independent QA | pending |
| component C1 variant threshold | visual C1 | `[data-component-proof="C1:threshold"]` | select | exact three | pending independent QA | pending |
| component C1 variant active | visual C1 | `[data-component-proof="C1:active"]` | select | phase/countdown | pending independent QA | pending |
| component C1 variant cycleBoundary | visual C1 | `[data-component-proof="C1:cycleBoundary"]` | cycle button | particles once | pending independent QA | pending |
| component C1 variant reduceMotion | visual C1 | `[data-component-proof="C1:reduceMotion"]` | Reduce Motion | capped/no particles | pending independent QA | pending |
| component C1 variant systemPaused | visual C1 | `[data-component-proof="C1:systemPaused"]` | select | diamond/frozen | pending independent QA | pending |
| component C1 variant completion | visual C1 | `[data-component-proof="C1:completion"]` | select | phase hidden | pending independent QA | pending |
| component C2 variant threeColumn | visual C2 | `[data-component-proof="C2:threeColumn"]` | Regular/Large | 3 columns | pending independent QA | pending |
| component C2 variant stacked | visual C2 | `[data-component-proof="C2:stacked"]` | Constrained | scroll stack | pending independent QA | pending |
| component C2 variant recordingLocked | visual C2 | `[data-component-proof="C2:recordingLocked"]` | select | disabled | pending independent QA | pending |
| component C3 variant haloContinuation | visual C3 | `[data-component-proof="C3:haloContinuation"]` | S1 | no fourth element | pending independent QA | pending |
| component C3 variant startButton | visual C3 | `[data-component-proof="C3:startButton"]` | S2 | Start visible | pending independent QA | pending |
| component C3 variant disabledStart | visual C3 | `[data-component-proof="C3:disabledStart"]` | fallback | disabled reason | pending independent QA | pending |
| component C4 variant activeControls | visual C4 | `[data-component-proof="C4:activeControls"]` | S3+select | controls | pending independent QA | pending |
| component C4 variant pausedControls | visual C4 | `[data-component-proof="C4:pausedControls"]` | S4 | continue/status | pending independent QA | pending |
| component C4 variant systemPausedReadOnly | visual C4 | `[data-component-proof="C4:systemPausedReadOnly"]` | S5 | disabled/diamond | pending independent QA | pending |
| component C4 variant recordingLocked | visual C4 | `[data-component-proof="C4:recordingLocked"]` | select | hidden/locked | pending independent QA | pending |
| component C5 variant exit | visual C5 | `[data-component-proof="C5:exit"]` | S6 | exit copy | pending independent QA | pending |
| component C5 variant restart | visual C5 | `[data-component-proof="C5:restart"]` | S7 | restart copy | pending independent QA | pending |
| component C6 variant idle | visual C6 | `[data-component-proof="C6:idle"]` | S8 | optional save | pending independent QA | pending |
| component C6 variant recording | visual C6 | `[data-component-proof="C6:recording"]` | select | spinner | pending independent QA | pending |
| component C6 variant saved | visual C6 | `[data-component-proof="C6:saved"]` | S9 normal | saved circle | pending independent QA | pending |
| component C6 variant notSaved | visual C6 | `[data-component-proof="C6:notSaved"]` | S9 error | 未保存 | pending independent QA | pending |
| component C6 variant overflow | visual C6 | `[data-component-proof="C6:overflow"]` | overflow | wrap | pending independent QA | pending |
| component C7 variant validation | visual C7 | `[data-component-proof="C7:validation"]` | select | TR18/TR29 | pending independent QA | pending |
| component C7 variant clock | visual C7 | `[data-component-proof="C7:clock"]` | select | TR27/TR29 | pending independent QA | pending |
| component C7 variant restart | visual C7 | `[data-component-proof="C7:restart"]` | select | restart/exit | pending independent QA | pending |
| component C7 variant exit | visual C7 | `[data-component-proof="C7:exit"]` | select | exit | pending independent QA | pending |
| component C7 variant overflow | visual C7 | `[data-component-proof="C7:overflow"]` | select | scroll/TR31 | pending independent QA | pending |
| component C7 variant nativeFallback | visual C7 | `[data-component-proof="C7:nativeFallback"]` | select | native TR32/TR33 | pending independent QA | pending |
| component C1 state default | visual C1 state default | `[data-component-proof="C1:default"]` | select C1/default | idle ring | pending independent QA | pending |
| component C1 state active | visual C1 state active | `[data-component-proof="C1:active"]` | select C1/active | breathing ring | pending independent QA | pending |
| component C1 state focused | visual C1 state focused | `[data-component-proof="C1:focused"]` | select C1/focused | focus outline | pending independent QA | pending |
| component C1 state pressed | visual C1 state pressed | `[data-component-proof="C1:pressed"]` | select C1/pressed | press feedback | pending independent QA | pending |
| component C1 state disabled | visual C1 state disabled | `[data-component-proof="C1:disabled"]` | select C1/disabled | inert+reason | pending independent QA | pending |
| component C1 state paused | visual C1 state paused | `[data-component-proof="C1:paused"]` | select C1/paused | square/frozen | pending independent QA | pending |
| component C1 state systemPaused | visual C1 state systemPaused | `[data-component-proof="C1:systemPaused"]` | select | diamond/frozen | pending independent QA | pending |
| component C1 state cycleBoundary | visual C1 state cycleBoundary | `[data-component-proof="C1:cycleBoundary"]` | cycle button | particles once | pending independent QA | pending |
| component C1 state reduceMotion | visual C1 state reduceMotion | `[data-component-proof="C1:reduceMotion"]` | Reduce Motion | capped/no particles | pending independent QA | pending |
| component C1 state loading | visual C1 state loading | `[data-component-proof="C1:loading"]` | select | placeholder/正在准备 | pending independent QA | pending |
| component C1 state empty | visual C1 state empty | `[data-component-proof="C1:empty"]` | select | placeholder+C7 | pending independent QA | pending |
| component C1 state error | visual C1 state error | `[data-component-proof="C1:error"]` | select | frozen+C7 | pending independent QA | pending |
| component C1 state overflow | visual C1 state overflow | `[data-component-proof="C1:overflow"]` | select | wrap/clamp | pending independent QA | pending |
| component C2 state default | visual C2 state default | `[data-component-proof="C2:default"]` | select | groups | pending independent QA | pending |
| component C2 state focused | visual C2 state focused | `[data-component-proof="C2:focused"]` | select | outline | pending independent QA | pending |
| component C2 state selected | visual C2 state selected | `[data-component-proof="C2:selected"]` | select | check | pending independent QA | pending |
| component C2 state pressed | visual C2 state pressed | `[data-component-proof="C2:pressed"]` | select | press | pending independent QA | pending |
| component C2 state disabled | visual C2 state disabled | `[data-component-proof="C2:disabled"]` | select | 不可用 | pending independent QA | pending |
| component C2 state loading | visual C2 state loading | `[data-component-proof="C2:loading"]` | select | 正在准备 | pending independent QA | pending |
| component C2 state empty | visual C2 state empty | `[data-component-proof="C2:empty"]` | select | 无可用节奏 | pending independent QA | pending |
| component C2 state error | visual C2 state error | `[data-component-proof="C2:error"]` | select | triangle+C7 | pending independent QA | pending |
| component C2 state overflow | visual C2 state overflow | `[data-component-proof="C2:overflow"]` | select | scroll/wrap | pending independent QA | pending |
| component C3 state default | visual C3 state default | `[data-component-proof="C3:default"]` | select | solid Start | pending independent QA | pending |
| component C3 state focused | visual C3 state focused | `[data-component-proof="C3:focused"]` | select | outline | pending independent QA | pending |
| component C3 state pressed | visual C3 state pressed | `[data-component-proof="C3:pressed"]` | select | press | pending independent QA | pending |
| component C3 state disabled | visual C3 state disabled | `[data-component-proof="C3:disabled"]` | select | reason | pending independent QA | pending |
| component C3 state loading | visual C3 state loading | `[data-component-proof="C3:loading"]` | select | spinner | pending independent QA | pending |
| component C3 state empty | visual C3 state empty | `[data-component-proof="C3:empty"]` | select | missing choice | pending independent QA | pending |
| component C3 state error | visual C3 state error | `[data-component-proof="C3:error"]` | select | C7 | pending independent QA | pending |
| component C3 state overflow | visual C3 state overflow | `[data-component-proof="C3:overflow"]` | select | 96dp wrap | pending independent QA | pending |
| component C4 state default | visual C4 state default | `[data-component-proof="C4:default"]` | select | controls | pending independent QA | pending |
| component C4 state focused | visual C4 state focused | `[data-component-proof="C4:focused"]` | select | outline | pending independent QA | pending |
| component C4 state pressed | visual C4 state pressed | `[data-component-proof="C4:pressed"]` | select | press | pending independent QA | pending |
| component C4 state paused | visual C4 state paused | `[data-component-proof="C4:paused"]` | S4 | square/继续 | pending independent QA | pending |
| component C4 state systemPaused | visual C4 state systemPaused | `[data-component-proof="C4:systemPaused"]` | S5 | diamond/disabled | pending independent QA | pending |
| component C4 state disabled | visual C4 state disabled | `[data-component-proof="C4:disabled"]` | select | muted/reason | pending independent QA | pending |
| component C4 state loading | visual C4 state loading | `[data-component-proof="C4:loading"]` | select | spinner | pending independent QA | pending |
| component C4 state empty | visual C4 state empty | `[data-component-proof="C4:empty"]` | select | stable exit | pending independent QA | pending |
| component C4 state error | visual C4 state error | `[data-component-proof="C4:error"]` | select | C7 | pending independent QA | pending |
| component C4 state overflow | visual C4 state overflow | `[data-component-proof="C4:overflow"]` | select | 2×2 | pending independent QA | pending |
| component C5 state default | visual C5 state default | `[data-component-proof="C5:default"]` | S6 | modal | pending independent QA | pending |
| component C5 state focused | visual C5 state focused | `[data-component-proof="C5:focused"]` | select | trapped outline | pending independent QA | pending |
| component C5 state pressed | visual C5 state pressed | `[data-component-proof="C5:pressed"]` | select | press | pending independent QA | pending |
| component C5 state disabled | visual C5 state disabled | `[data-component-proof="C5:disabled"]` | select | muted | pending independent QA | pending |
| component C5 state loading | visual C5 state loading | `[data-component-proof="C5:loading"]` | select | spinner | pending independent QA | pending |
| component C5 state error | visual C5 state error | `[data-component-proof="C5:error"]` | select | retry/cancel | pending independent QA | pending |
| component C5 state overflow | visual C5 state overflow | `[data-component-proof="C5:overflow"]` | select | stacked/scroll | pending independent QA | pending |
| component C5 state empty | visual C5 state empty | `[data-component-proof="C5:empty"]` | select | safe C7 | pending independent QA | pending |
| component C6 state default | visual C6 state default | `[data-component-proof="C6:default"]` | S8 | copy/save | pending independent QA | pending |
| component C6 state focused | visual C6 state focused | `[data-component-proof="C6:focused"]` | select | outline | pending independent QA | pending |
| component C6 state pressed | visual C6 state pressed | `[data-component-proof="C6:pressed"]` | select | press | pending independent QA | pending |
| component C6 state recording | visual C6 state recording | `[data-component-proof="C6:recording"]` | select | spinner | pending independent QA | pending |
| component C6 state loading | visual C6 state loading | `[data-component-proof="C6:loading"]` | select | 正在准备 | pending independent QA | pending |
| component C6 state disabled | visual C6 state disabled | `[data-component-proof="C6:disabled"]` | select | save muted | pending independent QA | pending |
| component C6 state saved | visual C6 state saved | `[data-component-proof="C6:saved"]` | S9 normal | circle+saved | pending independent QA | pending |
| component C6 state empty | visual C6 state empty | `[data-component-proof="C6:empty"]` | select | optional action | pending independent QA | pending |
| component C6 state error | visual C6 state error | `[data-component-proof="C6:error"]` | S9 error | 未保存 | pending independent QA | pending |
| component C6 state permission_denied | visual C6 state permission_denied | `[data-component-proof="C6:permission_denied"]` | select | 未保存 | pending independent QA | pending |
| component C6 state overflow | visual C6 state overflow | `[data-component-proof="C6:overflow"]` | select | wrap | pending independent QA | pending |
| component C7 state default | visual C7 state default | `[data-component-proof="C7:default"]` | S10 | recovery | pending independent QA | pending |
| component C7 state focused | visual C7 state focused | `[data-component-proof="C7:focused"]` | select | outline | pending independent QA | pending |
| component C7 state pressed | visual C7 state pressed | `[data-component-proof="C7:pressed"]` | select | press | pending independent QA | pending |
| component C7 state loading | visual C7 state loading | `[data-component-proof="C7:loading"]` | select | 正在恢复 | pending independent QA | pending |
| component C7 state empty | visual C7 state empty | `[data-component-proof="C7:empty"]` | select | native fallback | pending independent QA | pending |
| component C7 state error | visual C7 state error | `[data-component-proof="C7:error"]` | select | native plain | pending independent QA | pending |
| component C7 state overflow | visual C7 state overflow | `[data-component-proof="C7:overflow"]` | select | scroll/TR31 | pending independent QA | pending |
| component C7 state disabled | visual C7 state disabled | `[data-component-proof="C7:disabled"]` | select | exit remains | pending independent QA | pending |
| component C7 state validation | visual C7 state validation | `[data-component-proof="C7:validation"]` | select | TR18/TR29 | pending independent QA | pending |
| component C7 state clock | visual C7 state clock | `[data-component-proof="C7:clock"]` | select | TR27/TR29 | pending independent QA | pending |
| component C7 state restart | visual C7 state restart | `[data-component-proof="C7:restart"]` | select | restart+exit | pending independent QA | pending |
| component C7 state exit | visual C7 state exit | `[data-component-proof="C7:exit"]` | select | safe exit | pending independent QA | pending |
| component C7 state nativeFallback | visual C7 state nativeFallback | `[data-component-proof="C7:nativeFallback"]` | select | TR32/TR33 | pending independent QA | pending |
| component C1 stacking | visual C1 | `[data-component-proof^="C1:"]` | combined focus/pause/error | declared precedence | pending independent QA | pending |
| component C2 stacking | visual C2 | `[data-component-proof^="C2:"]` | selected+focused/error | declared precedence | pending independent QA | pending |
| component C3 stacking | visual C3 | `[data-component-proof^="C3:"]` | loading+disabled/focus | declared precedence | pending independent QA | pending |
| component C4 stacking | visual C4 | `[data-component-proof^="C4:"]` | systemPaused+focus | declared precedence | pending independent QA | pending |
| component C5 stacking | visual C5 | `[data-component-proof^="C5:"]` | modal+loading/error | declared precedence | pending independent QA | pending |
| component C6 stacking | visual C6 | `[data-component-proof^="C6:"]` | recording+focus/error | declared precedence | pending independent QA | pending |
| component C7 stacking | visual C7 | `[data-component-proof^="C7:"]` | native/error/overflow | declared precedence | pending independent QA | pending |
| responsive Large | interaction §9 | `[data-tier="large"]` | Large button | wide 3-column composition | pending independent QA | pending |
| responsive Compact | interaction §9 | `[data-tier="compact"]` | Compact button | compact content | pending independent QA | pending |
| responsive Constrained | interaction §9 | `[data-tier="constrained"]` | Constrained button | preview hidden; C2 392 scroll+16+C3 72=480 | pending independent QA | pending |
| responsive Reduce Motion | visual C1 | `#reduceBtn / .reduced` | Reduce Motion | halo capped; particles off | pending independent QA | pending |

## 8. Independent QA Findings

| ID | Severity | Evidence | Patch target / disposition |
|---|---|---|---|
| PQA-01 | P0 | §3.1 aggregates 10 states/31 transitions; §§3.2–3.4 are prose summaries | require one implementation-map row per each of 235 facts with selector, real trigger, expected, independently observed actual, verdict |
| PQA-02 | P0 | generator checklist actual=`reserved`, verdict=`pending` | independent actual result required for every row; generator cannot self-fill |
| PQA-03 | P0 | report r1 TR27 said no while interaction r10 says yes | report r2 corrects declaration, but any patch requires fresh Preview QA |
| PQA-04 | P0 | host Browser policy rejected `file:///.../preview.html` and explicitly prohibited workaround | 31 real transitions, 51 normal/fallback bindings, dialogs, responsive/motion, and screenshots remain unobserved; hard external blocker in this run |
| PQA-05 | P0 | first localhost rereview observed S4→TR5→S5→TR6 incorrectly returned S3 | preview r3 now stores source pause semantic on TR5 and restores S4 or S3 on TR6; previous rereviewer interrupted; fresh full rereview required |

QA independently rebuilt: states 10, transitions 31, elements 31, bindings 51, variants 29, own states 72, stacking 7, responsive/motion 4. Count differences are zero; TR27 had a semantic difference in report r1. Count equality does not offset missing per-item actual evidence.

Device/runtime/PICO parity/screenshot fidelity remain `not_performed`. No screenshot artifact is claimed.

### 8.1 Host-observed supported-localhost evidence after P-04

Main-thread Browser observation on `http://127.0.0.1:8765/preview.html`: S1→TR1→S2; real Start/TR2 shows blocking Dialog; visible exact `确认` reaches S3 Cadence with `吸气 / 01:42 / 进行中`. Artifacts: `../artifacts/scene-selection.png` and `../artifacts/practice.png`. These observations may support but cannot replace the fresh reviewer’s independent reverse lookup.
