# Preview / QA Report · TideBeacon

> Active revision: 3 | P-04 preview-path repair | Sources: interaction r10, visual r6, critique r5 | Scope: `web_design_validation_only` | Device validation: `not_performed`

## 1. Input Readiness

| Input fact | Active source / assertion | Verdict |
|---|---|---|
| Design-system review | critique r5, Stage 12 fresh-context pass | pass |
| States and transitions | interaction r10 §10, 10 states / 20 exact-ID transitions, entry/exit/return/error | pass |
| Component structure | visual r6 §5, C1–C6 each 8/8 sections, 48/48 | pass |
| Elements and bindings | visual r6 §5, 27 stable element IDs, 31 bindings with target + N/F/E + type | pass |
| Variants / states / stacking | visual r6 §5, 25 / 30 / 6 with trigger and stable result selector | pass |
| Responsive / motion | interaction r10 §9/§13 + visual r6 §5, Large/Compact/Constrained + Reduce Motion | pass |
| Visual grammar | visual r6 §3–§4, declared tokens/material/depth semantics are consumable | pass |

The following authoritative manifest was completed before `preview.html` revision 1 was written. Each row is one design fact; summaries are not denominator rows.

## 2. Authoritative Preview Coverage Manifest

### 2.1 States / transitions (30)

| Type | ID | Anchor | Trigger | Target / visible result | Confirm |
|---|---|---|---|---|---|
| state | S0_ORIENTATION | interaction r10 §10 | launch | distant lighthouse + halo + exact sentence; exactly 3 product elements | N/A |
| state | S1_SETUP | interaction r10 §10 | TR-01/TR-17 | duration/scene/pattern choices | N/A |
| state | S2_ENTRY_CONFIRM | interaction r10 §10 | TR-03 | entry dialog over exact setup snapshot | yes |
| state | S3_PRACTICE | interaction r10 §10 | TR-04/07/10/16/20 | running beacon, timeline, controls | N/A |
| state | S4_PAUSED | interaction r10 §10 | TR-06/lifecycle suspend | frozen beacon/audio/countdown | N/A |
| state | S5_RESTART_CONFIRM | interaction r10 §10 | TR-08/TR-13 | restart dialog over prior snapshot | yes |
| state | S6_EXIT_CONFIRM | interaction r10 §10 | TR-15/Back | exit dialog over prior snapshot | yes |
| state | S7_COMPLETE | interaction r10 §10 | TR-18 | exact completion copy + optional actions | N/A |
| state | S8_SAVE_RESULT | interaction r10 §10 | TR-19 | observable real write/readback result | N/A |
| state | S9_STAGE_ERROR | interaction r10 §10 | TR-22 | entry-specific error + exact setup return | N/A |
| transition | TR-01 | interaction r10 §10 | `user.ackInstruction` | S0→S1 | no |
| transition | TR-03 | interaction r10 §10 | `user.requestPractice` | S1→S2 | begins |
| transition | TR-04 | interaction r10 §10 | `user.confirmEntry` | S2→S3/start one timeline | confirms |
| transition | TR-05 | interaction r10 §10 | `user.cancelEntry` | exact prior S1 snapshot | no |
| transition | TR-06 | interaction r10 §10 | `user.pause`/suspend | S3→S4/freeze | no |
| transition | TR-07 | interaction r10 §10 | `user.resume` | S4→S3/same clock + gain fade | no |
| transition | TR-08 | interaction r10 §10 | `user.requestRestart` | S3/S4/S7→S5 | begins |
| transition | TR-09 | interaction r10 §10 | `user.confirmRestart` | S5→S1/reset | confirms |
| transition | TR-10 | interaction r10 §10 | `user.cancelRestart` | exact prior S3/S4/S7 snapshot | no |
| transition | TR-12 | interaction r10 §10 | `user.selectRhythmDuringPractice` | queue only; active run unchanged | no |
| transition | TR-13 | interaction r10 §10 | `user.applyQueuedPattern` | S3/S4→S5 | begins |
| transition | TR-15 | interaction r10 §10 | `user.requestExit`/Back | S3/S4→S6 | begins |
| transition | TR-16 | interaction r10 §10 | `user.cancelExit` | exact prior S3/S4 snapshot | no |
| transition | TR-17 | interaction r10 §10 | `user.confirmExit` | S6→S1/close Stage | confirms |
| transition | TR-18 | interaction r10 §10 | `timeline.sessionComplete` | S3→S7 | no |
| transition | TR-19 | interaction r10 §10 | `user.saveRecord` | S7→S8 after real write/readback | no |
| transition | TR-20 | interaction r10 §10 | `user.dismissSaveResult` | S8→S7/preserve completion | no |
| transition | TR-21 | interaction r10 §10 | `user.finishWithoutSave` | S7/S8→S1 | no |
| transition | TR-22 | interaction r10 §10 | `system.stageOpenFailed` | S2→S9 | no |
| transition | TR-23 | interaction r10 §10 | `user.returnToSetup` | S9→exact S1 snapshot | no |

### 2.2 `renderSpec.elements[]` (27)

| Component | Element ID | Anchor | Visible label | Bind | Conditional rule |
|---|---|---|---|---|---|
| C1 | c1-lighthouse | visual r6 §5 C1 | 灯塔 | scene.lighthouse | always; S0 semantic action |
| C1 | c1-halo | visual r6 §5 C1 | 光晕 | timeline.phaseProgress | always |
| C1 | c1-instruction | visual r6 §5 C1 | 光扩张时吸气，光收回时呼气。 | firstRun.copy | S0 only |
| C1 | c1-phase | visual r6 §5 C1 | 吸气 / 停留 / 呼气 | timeline.currentPhase | practice only |
| C1 | c1-countdown | visual r6 §5 C1 | 02:00 | timeline.remainingMs | practice only |
| C2 | c2-duration | visual r6 §5 C2 | 2分钟 / 4分钟 / 6分钟 | selection.duration | S1/S2 |
| C2 | c2-scene | visual r6 §5 C2 | 海面 / 云层 / 沙丘 | selection.scene | S1/S2 |
| C2 | c2-pattern | visual r6 §5 C2 | 舒缓 / 均衡 / 深长 | selection.pattern | S1/S2 |
| C2 | c2-pattern-detail | visual r6 §5 C2 | 吸气·停留·呼气·停留 | pattern.phaseDurations | Large/Compact; step detail when active |
| C2 | c2-start | visual r6 §5 C2 | 开始练习 | selection.valid | enabled only valid |
| C3 | c3-status | visual r6 §5 C3 | 进行中 / 已暂停 | timeline.runState | controls invoked |
| C3 | c3-pause | visual r6 §5 C3 | 暂停 / 继续 | timeline.runState | practice/paused |
| C3 | c3-rhythm | visual r6 §5 C3 | 节奏 | queuedPattern | practice/paused |
| C3 | c3-restart | visual r6 §5 C3 | 重新开始 | timeline.canRestart | practice/paused |
| C3 | c3-exit | visual r6 §5 C3 | 退出 | stage.canExit | practice/paused |
| C4 | c4-title | visual r6 §5 C4 | 进入练习 / 重新开始 / 退出练习 | confirmation.kind | dialog only |
| C4 | c4-body | visual r6 §5 C4 | 确认或返回 | confirmation.body | dialog only |
| C4 | c4-cancel | visual r6 §5 C4 | 返回 | confirmation.cancelTransitionId | dialog only |
| C4 | c4-confirm | visual r6 §5 C4 | 确认 | confirmation.confirmTransitionId | hidden cancelOnlyError |
| C5 | c5-copy | visual r6 §5 C5 | 完成一次练习 | completion.copy | S7/S8 |
| C5 | c5-save | visual r6 §5 C5 | 保存本地记录 | record.canSave | S7/S8; disabled unavailable |
| C5 | c5-restart | visual r6 §5 C5 | 再来一次 | completion.canRestart | S7/S8 |
| C5 | c5-exit | visual r6 §5 C5 | 结束 | completion.canExit | S7/S8 |
| C5 | c5-result | visual r6 §5 C5 | 已保存 / 未保存 | record.writeResult | after attempt only |
| C6 | c6-shape | visual r6 §5 C6 | 状态 | bindingStatus.semantic | issue only |
| C6 | c6-message | visual r6 §5 C6 | 具体问题 | bindingStatus.message | issue only |
| C6 | c6-action | visual r6 §5 C6 | 重试 / 返回 / 跳过 | bindingStatus.recoveryAction | safe action only |

### 2.3 `dataBindings[]` (31)

| Component | Source path | Target | Normal sample | Fallback / error sample | Type |
|---|---|---|---|---|---|
| C1 | scene.lighthouse | c1-lighthouse geometry | procedural lighthouse | opaque prism / △灯塔未加载 | display |
| C1 | timeline.phaseProgress | c1-halo | 0.42 expanding | freeze .42 / □节奏已暂停 | semantic |
| C1 | firstRun.copy | c1-instruction | exact sentence | built-in exact / built-in exact | display |
| C1 | timeline.currentPhase | c1-phase | 吸气 | 节奏已暂停 / paused square | semantic |
| C1 | timeline.remainingMs | c1-countdown | 01:42 | last valid + --:-- / stop+C6 | display |
| C1 | playback.visualScale/audioGain | c1-halo/audio audit | .86/.72 | last+muted / force pause+mute | semantic |
| C1 | cycleAccent.completedCycle | stars | subtle full-cycle accent | omit / omit+diagnostic | semantic |
| C1 | orientation.continueTransitionId | c1-lighthouse activation | exact TR-01 | built-in TR-01 / built-in, never index | semantic |
| C2 | selection.duration | c2-duration | 2m selected | visible 2m default / disable+C6 | semantic |
| C2 | selection.scene | c2-scene | sea selected | sea swatch / labels usable+C6 | semantic |
| C2 | selection.pattern | c2-pattern | 舒缓 selected | 舒缓 default / disable+C6 | semantic |
| C2 | pattern.phaseDurations | c2-pattern-detail | 4·1·6·1 | unavailable copy / invalid hidden | display |
| C2 | selection.valid | c2-start | true | false / false+请完成选择 | semantic |
| C3 | timeline.runState | c3-status/c3-pause | ○进行中 | □已暂停 / forced paused+C6 | semantic |
| C3 | queuedPattern | c3-rhythm | 均衡待启用 | 未更改 / queue cleared | display |
| C3 | timeline.canRestart | c3-restart | true | false / false+reason | semantic |
| C3 | stage.canExit | c3-exit | true | system Back / stable exit+C6 | semantic |
| C3 | pauseSnapshot | c3-status/resume | exact phase+elapsed | last safe paused / remain paused+C6 | semantic |
| C4 | confirmation.kind | c4-title | 进入练习 | 确认操作 / cancel-only | semantic |
| C4 | confirmation.body | c4-body | kind consequence | 确认或返回 / safe cancel consequence | display |
| C4 | confirmation.cancelTransitionId | c4-cancel | exact TR-05 | snapshot-close / safe cancel+C6 | semantic |
| C4 | confirmation.confirmTransitionId | c4-confirm | exact TR-04 | disabled / no commit+C6 | semantic |
| C4 | confirmation.snapshot | restore mechanism | full snapshot | in-memory prior / no mutation | semantic |
| C5 | completion.copy | c5-copy | exact completion | built-in exact / built-in exact | display |
| C5 | record.canSave | c5-save | true | unavailable false / false+other exits | semantic |
| C5 | record.writeResult | c5-result | ◇verified write/readback | hidden not-attempted / △未保存+retry | semantic |
| C5 | completion.canRestart | c5-restart | true | false / false+exit | semantic |
| C5 | completion.canExit | c5-exit | true | system exit / system exit | semantic |
| C6 | bindingStatus.semantic | c6-shape | ◇ mapped | dashed unavailable / △error | semantic |
| C6 | bindingStatus.message | c6-message | binding-specific copy | 此项暂不可用 / named domain failure | display |
| C6 | bindingStatus.recoveryAction | c6-action | exact action | safe return / unsafe retry hidden | semantic |

### 2.4 Variants / component states / stacking (61)

| Component | Kind | Value | Anchor | Trigger | Expected observable result |
|---|---|---|---|---|---|
| C1 | variant | orientation | visual r6 §5 C1 | S0/demo | exactly lighthouse/halo/instruction; selector |
| C1 | variant | practice | visual r6 §5 C1 | S3/S4/demo | phase/countdown replace instruction; selector |
| C1 | variant | reducedMotion | visual r6 §5 C1 | preference/demo | phase-boundary steps; selector |
| C1 | state | orientation | visual r6 §5 C1 | S0/demo | exact 3 elements |
| C1 | state | active | visual r6 §5 C1 | inhale/exhale/demo | circle expands/contracts |
| C1 | state | hold | visual r6 §5 C1 | hold/demo | dashed fixed edge |
| C1 | state | paused | visual r6 §5 C1 | TR-06/demo | square/frozen label |
| C1 | state | error | visual r6 §5 C1 | invalid clock/demo | triangle notice |
| C1 | stacking | error>paused>hold>active | visual r6 §5 C1 | demo | declared order selector |
| C2 | variant | LargePreview | visual r6 §5 C2 | Large/demo | preview swatches + detail |
| C2 | variant | CompactRows | visual r6 §5 C2 | Compact/demo | text rows; previews hidden |
| C2 | variant | ConstrainedSteps | visual r6 §5 C2 | Constrained/demo | one active group |
| C2 | variant | controllerFocus | visual r6 §5 C2 | controller/demo | persistent focus ring |
| C2 | state | default | visual r6 §5 C2 | S1/demo | muted outlines |
| C2 | state | focused | visual r6 §5 C2 | focus/demo | accent outline |
| C2 | state | selected | visual r6 §5 C2 | activate/demo | check/selected cue |
| C2 | state | disabled | visual r6 §5 C2 | invalid/demo | dashed + reason |
| C2 | state | error | visual r6 §5 C2 | binding error/demo | triangle row |
| C2 | stacking | error>disabled>focused+selected>selected>focused | visual r6 §5 C2 | demo | declared order selector |
| C3 | variant | running | visual r6 §5 C3 | S3/demo | pause primary + active circle |
| C3 | variant | paused | visual r6 §5 C3 | S4/demo | resume primary + square |
| C3 | variant | patternQueued | visual r6 §5 C3 | TR-12/demo | queued mark + restart invite |
| C3 | variant | ConstrainedWrap | visual r6 §5 C3 | min/demo | two rows, order stable |
| C3 | state | hidden | visual r6 §5 C3 | no invoke/demo | absent/noninteractive cue |
| C3 | state | default | visual r6 §5 C3 | invoke/demo | thick panel |
| C3 | state | focused | visual r6 §5 C3 | focus/demo | accent outline |
| C3 | state | paused | visual r6 §5 C3 | S4/demo | square/resume primary |
| C3 | state | disabled | visual r6 §5 C3 | invalid/demo | dashed/skipped focus |
| C3 | stacking | C4>C6>paused>focused>default>hidden | visual r6 §5 C3 | demo | declared order selector |
| C4 | variant | entry | visual r6 §5 C4 | TR-03/demo | entry consequence + exact IDs |
| C4 | variant | restart | visual r6 §5 C4 | TR-08/demo | restart consequence + snapshot |
| C4 | variant | exit | visual r6 §5 C4 | TR-15/demo | exit consequence + snapshot |
| C4 | variant | cancelOnlyError | visual r6 §5 C4 | invalid ID/demo | confirm absent/no mutation cue |
| C4 | state | open | visual r6 §5 C4 | dialog/demo | opaque focus trap cue |
| C4 | state | cancelFocused | visual r6 §5 C4 | focus/demo | cancel accent |
| C4 | state | confirmFocused | visual r6 §5 C4 | focus/demo | confirm accent |
| C4 | state | committing | visual r6 §5 C4 | confirm/demo | disabled+spinner cue |
| C4 | state | error | visual r6 §5 C4 | invalid/demo | cancel-only cue |
| C4 | stacking | error>committing>focus>open | visual r6 §5 C4 | demo | declared order selector |
| C5 | variant | unsaved | visual r6 §5 C5 | S7/demo | result hidden/save enabled |
| C5 | variant | saving | visual r6 §5 C5 | TR-19 pending/demo | spinner/live cue |
| C5 | variant | saved | visual r6 §5 C5 | readback success/demo | diamond verified label |
| C5 | variant | saveFailed | visual r6 §5 C5 | write/readback fail/demo | triangle/retry cue |
| C5 | state | default | visual r6 §5 C5 | S7/demo | exact copy/actions |
| C5 | state | saving | visual r6 §5 C5 | TR-19/demo | spinner cue |
| C5 | state | saved | visual r6 §5 C5 | success/demo | diamond + label |
| C5 | state | failed | visual r6 §5 C5 | failure/demo | triangle + retry |
| C5 | state | focused | visual r6 §5 C5 | focus/demo | accent outline |
| C5 | stacking | failed/saved>saving>focused>default | visual r6 §5 C5 | demo | declared order selector |
| C6 | variant | durationError | visual r6 §5 C6 | invalid duration/demo | names duration + safe 2m/return |
| C6 | variant | sceneFallback | visual r6 §5 C6 | preview fail/demo | dashed sea swatch + continue |
| C6 | variant | patternError | visual r6 §5 C6 | invalid phases/demo | start disabled + safe pattern |
| C6 | variant | timelinePaused | visual r6 §5 C6 | invalid clock/demo | square + remain paused |
| C6 | variant | stageEntryError | visual r6 §5 C6 | TR-22/demo | entry-specific + TR-23 |
| C6 | variant | saveFailure | visual r6 §5 C6 | write/readback fail/demo | local-record retry/skip |
| C6 | state | hidden | visual r6 §5 C6 | no issue/demo | absent/nonfocusable cue |
| C6 | state | fallback | visual r6 §5 C6 | substitution/demo | dashed + specific label |
| C6 | state | error | visual r6 §5 C6 | fail/demo | triangle + action |
| C6 | state | focused | visual r6 §5 C6 | focus/demo | action accent |
| C6 | state | recovering | visual r6 §5 C6 | retry/demo | spinner/live cue |
| C6 | stacking | C4>C6error>C6fallback>content | visual r6 §5 C6 | demo | declared order selector |

### 2.5 Responsive / motion (4)

| Scenario | Anchor | Tier / content | Trigger | Expected result |
|---|---|---|---|---|
| Large | interaction r10 §9; visual r6 C2/C3 | max: WC-SETUP 1280×800; previews/detail; controls 1 row | audit Large | `data-responsive=Large`; full choice grid |
| Compact | same | default: WC-SETUP 960×720; text rows; WC-CONTROL 960×240 | audit Compact | `data-responsive=Compact`; preview swatches hidden |
| Constrained | same | min: WC-SETUP 896×656; active setup step; controls 2 rows | audit Constrained | `data-responsive=Constrained`; structural step/wrap, no scaling |
| Reduce Motion | interaction r10 §13; visual r6 C1 | same state semantics | audit Reduce Motion | `data-reduce-motion=true`; continuous scale/slide removed |

Manifest reconciliation: states 10 + transitions 20 + elements 27 + bindings 31 + variants 25 + component states 30 + stacking 6 + responsive/motion 4 = **153**. Missing = **0**.

## 3. Generation-Side Implementation Maps

All selectors and triggers below exist in `preview.html` r3. The generator intentionally does not fill independent Actual/Verdict columns.

### 3.1 State / transition → scene mapping (30)

| Fact | Stable selector / trigger | Expected |
|---|---|---|
| S0_ORIENTATION | `#product[data-state=S0_ORIENTATION]` / launch or force-state | exact 3 `[data-product-element]` |
| S1_SETUP | `#product[data-state=S1_SETUP]` / TR-01 | C2 choices |
| S2_ENTRY_CONFIRM | `#product[data-state=S2_ENTRY_CONFIRM]` / TR-03 | C4 entry dialog |
| S3_PRACTICE | `#product[data-state=S3_PRACTICE]` / TR-04 | running C1+C3 |
| S4_PAUSED | `#product[data-state=S4_PAUSED]` / TR-06 | frozen C1+C3 |
| S5_RESTART_CONFIRM | `#product[data-state=S5_RESTART_CONFIRM]` / TR-08/13 | C4 restart dialog |
| S6_EXIT_CONFIRM | `#product[data-state=S6_EXIT_CONFIRM]` / TR-15 | C4 exit dialog |
| S7_COMPLETE | `#product[data-state=S7_COMPLETE]` / TR-18 | C5 exact copy |
| S8_SAVE_RESULT | `#product[data-state=S8_SAVE_RESULT]` / TR-19 | C5 observed result |
| S9_STAGE_ERROR | `#product[data-state=S9_STAGE_ERROR]` / TR-22 | C6 entry error |
| TR-01 | `[data-transition=TR-01]` lighthouse/audit | S0→S1 exact-ID object lookup |
| TR-03 | `[data-transition=TR-03]` | S1→S2 dialog |
| TR-04 | `[data-transition=TR-04]` | confirm→S3 |
| TR-05 | `[data-transition=TR-05]` | exact prior setup restore |
| TR-06 | `[data-transition=TR-06]` | freeze elapsed/remaining/gain |
| TR-07 | `[data-transition=TR-07]` | resume same clock + 350ms gain fade |
| TR-08 | `[data-transition=TR-08]` | snapshot + restart dialog |
| TR-09 | `[data-transition=TR-09]` | reset→setup |
| TR-10 | `[data-transition=TR-10]` | exact prior restore |
| TR-12 | `[data-transition=TR-12]` | queue without active mutation |
| TR-13 | `[data-transition=TR-13]` | queued restart dialog |
| TR-15 | `[data-transition=TR-15]` | snapshot + exit dialog |
| TR-16 | `[data-transition=TR-16]` | exact prior restore |
| TR-17 | `[data-transition=TR-17]` | close→setup |
| TR-18 | `[data-transition=TR-18]` / QA completion | completion + cycle accent |
| TR-19 | `[data-transition=TR-19]` | real localStorage write+readback |
| TR-20 | `[data-transition=TR-20]` / audit | result→completion |
| TR-21 | `[data-transition=TR-21]` | finish→setup |
| TR-22 | `[data-transition=TR-22]` / QA Stage failure | entry error |
| TR-23 | `[data-transition=TR-23]` | exact setup return |

### 3.2 Elements → DOM mapping (27)

| Element | Stable selector | Render trigger / conditional result |
|---|---|---|
| c1-lighthouse | `[data-preview-id=c1-lighthouse]` | S0/S3/S4; action only S0 |
| c1-halo | `[data-preview-id=c1-halo]` | S0/S3/S4 |
| c1-instruction | `[data-preview-id=c1-instruction]` | S0 only |
| c1-phase | `[data-preview-id=c1-phase]` | S3/S4 only |
| c1-countdown | `[data-preview-id=c1-countdown]` | S3/S4 only |
| c2-duration | `[data-preview-id=c2-duration]` | S1/S2 |
| c2-scene | `[data-preview-id=c2-scene]` | S1/S2 |
| c2-pattern | `[data-preview-id=c2-pattern]` | S1/S2 |
| c2-pattern-detail | `[data-preview-id=c2-pattern-detail]` | S1/S2; constrained active step |
| c2-start | `[data-preview-id=c2-start]` | S1/S2 |
| c3-status | `[data-preview-id=c3-status]` | S3/S4/S5/S6 |
| c3-pause | `[data-preview-id=c3-pause]` | S3/S4/S5/S6 |
| c3-rhythm | `[data-preview-id=c3-rhythm]` | S3/S4/S5/S6 |
| c3-restart | `[data-preview-id=c3-restart]` | S3/S4/S5/S6 |
| c3-exit | `[data-preview-id=c3-exit]` | S3/S4/S5/S6 |
| c4-title | `[data-preview-id=c4-title]` | S2/S5/S6 |
| c4-body | `[data-preview-id=c4-body]` | S2/S5/S6 |
| c4-cancel | `[data-preview-id=c4-cancel]` | dialog; exact bound ID |
| c4-confirm | `[data-preview-id=c4-confirm]` | valid dialog only |
| c5-copy | `[data-preview-id=c5-copy]` | S7/S8 |
| c5-save | `[data-preview-id=c5-save]` | S7/S8 |
| c5-restart | `[data-preview-id=c5-restart]` | S7/S8 |
| c5-exit | `[data-preview-id=c5-exit]` | S7/S8 |
| c5-result | `[data-preview-id=c5-result]` | S7/S8; empty before attempt |
| c6-shape | `[data-preview-id=c6-shape]` | S9 or binding demo |
| c6-message | `[data-preview-id=c6-message]` | S9 or binding demo |
| c6-action | `[data-preview-id=c6-action]` | safe recovery only |

### 3.3 Bindings → data/fallback map (31)

Every row uses the audit binding selector plus separate `normal`, `fallback`, and `error` buttons; the rendered evidence selector is `[data-binding="<path>"][data-mode="<mode>"]`, and `data-binding-target` names the corresponding element.

| Source path | Target | Normal / fallback / error entry |
|---|---|---|
| scene.lighthouse | c1-lighthouse | select path → three mode buttons; procedural / prism / named error |
| timeline.phaseProgress | c1-halo | select path → three modes; expand / freeze / pause |
| firstRun.copy | c1-instruction | select path → three modes; exact/built-in exact/built-in exact |
| timeline.currentPhase | c1-phase | select path → three modes; phase/paused/square |
| timeline.remainingMs | c1-countdown | select path → three modes; mm:ss/last/stop+C6 |
| playback.visualScale/audioGain | c1-halo | select path → three modes; timeline/last+mute/pause+mute |
| cycleAccent.completedCycle | stars | select path → three modes; accent/omit/diagnostic |
| orientation.continueTransitionId | c1-lighthouse | select path → three modes; exact/built-in/built-in never-index |
| selection.duration | c2-duration | select path → three modes; selected/default/disabled |
| selection.scene | c2-scene | select path → three modes; selected/sea/labels+C6 |
| selection.pattern | c2-pattern | select path → three modes; selected/default/disabled |
| pattern.phaseDurations | c2-pattern-detail | select path → three modes; values/unavailable/hidden-invalid |
| selection.valid | c2-start | select path → three modes; enabled/disabled/named error |
| timeline.runState | c3-status | select path → three modes; running/paused/forced paused |
| queuedPattern | c3-rhythm | select path → three modes; name/unchanged/cleared |
| timeline.canRestart | c3-restart | select path → three modes; true/false/reason |
| stage.canExit | c3-exit | select path → three modes; true/Back/stable exit |
| pauseSnapshot | c3-status | select path → three modes; exact/last safe/remain paused |
| confirmation.kind | c4-title | select path → three modes; kind/generic/cancel-only |
| confirmation.body | c4-body | select path → three modes; consequence/generic/safe cancel |
| confirmation.cancelTransitionId | c4-cancel | select path → three modes; exact/snapshot-close/safe cancel |
| confirmation.confirmTransitionId | c4-confirm | select path → three modes; exact/disabled/no commit |
| confirmation.snapshot | restore mechanism | select path → three modes; full/in-memory/no mutation |
| completion.copy | c5-copy | select path → three modes; exact/built-in exact/built-in exact |
| record.canSave | c5-save | select path → three modes; true/unavailable/other exits |
| record.writeResult | c5-result | select path → three modes; verified/hidden/retry |
| completion.canRestart | c5-restart | select path → three modes; true/false/exit remains |
| completion.canExit | c5-exit | select path → three modes; true/system/system |
| bindingStatus.semantic | c6-shape | select path → three modes; diamond/dashed/triangle |
| bindingStatus.message | c6-message | select path → three modes; specific/unavailable/named failure |
| bindingStatus.recoveryAction | c6-action | select path → three modes; exact/safe return/unsafe hidden |

### 3.4 Variants / states / stacking → behavior map (61)

For every row, select its component, click the individually generated button `[data-demo-kind][data-demo-value]`, then observe the matching stable attribute in `#demo`. The values are itemized to avoid merged coverage.

| Component | Kind | Trigger button value | Rendered stable selector |
|---|---|---|---|
| C1 | variant | orientation | `[data-variant=orientation]` |
| C1 | variant | practice | `[data-variant=practice]` |
| C1 | variant | reducedMotion | `[data-variant=reducedMotion]` |
| C1 | state | orientation | `[data-component-state=orientation]` |
| C1 | state | active | `[data-component-state=active]` |
| C1 | state | hold | `[data-component-state=hold]` |
| C1 | state | paused | `[data-component-state=paused]` |
| C1 | state | error | `[data-component-state=error]` |
| C1 | stacking | error>paused>hold>active | `[data-stack="error>paused>hold>active"]` |
| C2 | variant | LargePreview | `[data-variant=LargePreview]` |
| C2 | variant | CompactRows | `[data-variant=CompactRows]` |
| C2 | variant | ConstrainedSteps | `[data-variant=ConstrainedSteps]` |
| C2 | variant | controllerFocus | `[data-variant=controllerFocus]` |
| C2 | state | default | `[data-component-state=default]` |
| C2 | state | focused | `[data-component-state=focused]` |
| C2 | state | selected | `[data-component-state=selected]` |
| C2 | state | disabled | `[data-component-state=disabled]` |
| C2 | state | error | `[data-component-state=error]` |
| C2 | stacking | error>disabled>focused+selected>selected>focused | exact `[data-stack]` value |
| C3 | variant | running | `[data-variant=running]` |
| C3 | variant | paused | `[data-variant=paused]` |
| C3 | variant | patternQueued | `[data-variant=patternQueued]` |
| C3 | variant | ConstrainedWrap | `[data-variant=ConstrainedWrap]` |
| C3 | state | hidden | `[data-component-state=hidden]` |
| C3 | state | default | `[data-component-state=default]` |
| C3 | state | focused | `[data-component-state=focused]` |
| C3 | state | paused | `[data-component-state=paused]` |
| C3 | state | disabled | `[data-component-state=disabled]` |
| C3 | stacking | C4>C6>paused>focused>default>hidden | exact `[data-stack]` value |
| C4 | variant | entry | `[data-variant=entry]` |
| C4 | variant | restart | `[data-variant=restart]` |
| C4 | variant | exit | `[data-variant=exit]` |
| C4 | variant | cancelOnlyError | `[data-variant=cancelOnlyError]` |
| C4 | state | open | `[data-component-state=open]` |
| C4 | state | cancelFocused | `[data-component-state=cancelFocused]` |
| C4 | state | confirmFocused | `[data-component-state=confirmFocused]` |
| C4 | state | committing | `[data-component-state=committing]` |
| C4 | state | error | `[data-component-state=error]` |
| C4 | stacking | error>committing>focus>open | exact `[data-stack]` value |
| C5 | variant | unsaved | `[data-variant=unsaved]` |
| C5 | variant | saving | `[data-variant=saving]` |
| C5 | variant | saved | `[data-variant=saved]` |
| C5 | variant | saveFailed | `[data-variant=saveFailed]` |
| C5 | state | default | `[data-component-state=default]` |
| C5 | state | saving | `[data-component-state=saving]` |
| C5 | state | saved | `[data-component-state=saved]` |
| C5 | state | failed | `[data-component-state=failed]` |
| C5 | state | focused | `[data-component-state=focused]` |
| C5 | stacking | failed/saved>saving>focused>default | exact `[data-stack]` value |
| C6 | variant | durationError | `[data-variant=durationError]` |
| C6 | variant | sceneFallback | `[data-variant=sceneFallback]` |
| C6 | variant | patternError | `[data-variant=patternError]` |
| C6 | variant | timelinePaused | `[data-variant=timelinePaused]` |
| C6 | variant | stageEntryError | `[data-variant=stageEntryError]` |
| C6 | variant | saveFailure | `[data-variant=saveFailure]` |
| C6 | state | hidden | `[data-component-state=hidden]` |
| C6 | state | fallback | `[data-component-state=fallback]` |
| C6 | state | error | `[data-component-state=error]` |
| C6 | state | focused | `[data-component-state=focused]` |
| C6 | state | recovering | `[data-component-state=recovering]` |
| C6 | stacking | C4>C6error>C6fallback>content | exact `[data-stack]` value |

### 3.5 Responsive / motion → reflow map (4)

| Scenario | Trigger | Stable selector | Structural result |
|---|---|---|---|
| Large | audit `Large/max` | `#product[data-responsive=Large]` | full 3-row choice grid, preview/detail, 1-row controls |
| Compact | audit `Compact/default` | `#product[data-responsive=Compact]` | 896-wide text rows, preview swatches hidden |
| Constrained | audit `Constrained/min` | `#product[data-responsive=Constrained]` | one active setup group, 2-row controls, stacked dialog actions |
| Reduce Motion | audit toggle | `#product[data-reduce-motion=true]` | animations/transitions removed; halo uses boundary steps |

## 4. Generator Declarative Checks

| Check | Evidence | Generator result |
|---|---|---|
| Coverage manifest complete | §2 has 153 individually anchored rows; missing 0 | mapped |
| State machine exists | `STATES`, exact-ID-keyed `TRANSITIONS`, `dispatch(id)`, `render()`, `#product[data-state]` | mapped |
| DOM lookup entry | 27 rows §3.2, each `[data-preview-id]` | mapped |
| Data modes | 31 rows §3.3, `BINDINGS` values differ per binding and mode | mapped |
| High-risk confirmation | C4 `role=dialog`; TR-03/08/13/15 snapshot; exact confirm/cancel IDs; restore equality log | mapped |
| Responsive / Reduce Motion | §3.5 structural selectors and audit triggers | mapped |
| Local record boundary | TR-19 invokes `localStorage.setItem`, immediate `getItem`, equality gate; forced exception path observable | mapped |
| First-view count | only C1 lighthouse/halo/instruction carry `data-product-element` in S0; live count reports 3 | mapped |

## 5. Independent QA (Stage 14 only)

The generation role leaves Actual/Verdict unset. The fresh-context `prototype_qa_reviewer` must independently rebuild all denominators, run real paths over supported localhost HTTP in the in-app Browser, capture scene-selection and practice screenshots, and fill this section.

| Gate | QA actual | QA verdict |
|---|---|---|
| Manifest denominator rebuild |  |  |
| State/transition paths |  |  |
| Elements |  |  |
| Bindings N/F/E |  |  |
| Variants/states/stacking |  |  |
| Responsive/motion |  |  |
| Exact cancel restore |  |  |
| localStorage write/readback + forced failure |  |  |
| Screenshots |  |  |

## 6. Requirements Traceability

| Requirement | State / component | Web validation |
|---|---|---|
| exact sparse first view | S0 / C1 | live product-element count + screenshot/path |
| duration/scene/pattern setup | S1 / C2 | controller/keyboard choices |
| one timeline drives halo/audio/countdown | S3/S4 / C1,C3 | shared timeline object and pause snapshot |
| exact confirmation restore | S2/S5/S6 / C4 | equality log on cancel |
| pause/resume continuity | S3/S4 / C1,C3 | frozen counter; same clock; gain fade |
| complete cycles only accent | S3→S7 / C1 | TR-18/cycle event only |
| quiet completion + optional record | S7/S8 / C5 | exact copy; real Web local write/readback |
| binding-specific recovery | all / C6 | 31 per-binding N/F/E audit entries |
| controller operation | all actionable components | arrows, Enter, Escape and focus-visible |
| scene-selection/practice captures | S1/S3 | Stage 14 Browser capture targets |

Coverage: 10/10 product requirement groups represented in Web design validation; PICO runtime acceptance remains outside scope.

## 7. Web Logic Tolerance / Device Boundary

| Item | Status |
|---|---|
| exact ID relationships / declared token reference presence | Web validation scope |
| screenshot visual diff / CSS-to-physical-size / device color delta / Web-PICO parity | excluded |
| physical distance/readability/occlusion/fatigue/controller precision/runtime performance/audio loudness | `not_performed`; requires device validation |

## 8. Defects

Pending independent Stage 14 review. Generator asserts no known open defect but does not issue the QA verdict.
