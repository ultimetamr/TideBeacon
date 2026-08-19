# Visual System Spec · TideBeacon

> Active revision: 6 | Sources: interaction r10, PM r6, UXR r3 | Stage-12 repair CR-03.3

## 1. Output

Approved direction, precise visual tokens, two window structures, six independently complete component blocks, materials, data trust, and asset handoff.

## 2. Spatial Visual Direction

| Direction | Thesis | First view | Depth / hierarchy | Risk |
|---|---|---|---|---|
| V1 Horizon Breathmark | horizon as quiet temporal instrument | exactly lighthouse, halo, exact sentence | environment far; halo at beacon; sentence lower-center | low dashboard risk |
| V2 Tidal Ribbon | lateral luminous shoreline path | beacon plus moving ribbon | ribbon crosses mid-depth | motion and navigation ambiguity |
| V3 Dune Aperture | near dune frames beacon | near aperture plus far beacon | strong near/far contrast | occlusion and pressure |

Approved: **V1 Horizon Breathmark**. It preserves one distant focus and no camera motion. V2 is rejected for lateral motion; V3 for near-field dominance. Structured design-effect review: spatial composition, hierarchy, non-clinical domain expression, restrained interaction cues, PICO spatial value, maturity, and handoff clarity all pass. This is not device validation. Competitor visuals were not reused.

## 3. Design Tokens

| Token | Value | Use |
|---|---|---|
| accent | #8EDBE4 | focus / active edge |
| surface | #101B28 | readable opaque backing |
| brandPrimary | #F2C879 | lighthouse / confirm |
| nightDeep | #06101B | Stage sky |
| seaLow | #12354B | procedural sea |
| textPrimary | #F4F1E8 | primary copy |
| textMuted | #A8BBC6 | secondary copy |
| danger | #FFB38A | destructive/error |

### 3.1 Typography

| Role | family | size | line | weight |
|---|---|---:|---:|---:|
| display | sans | 40sp | 52sp | 500 |
| title | sans | 28sp | 38sp | 500 |
| metric | mono | 28sp | 36sp | 500 |
| body | sans | 18sp | 28sp | 500 |
| caption | sans | 14sp | 20sp | 500 |

### 3.2 colorSemantics

| Key | color | shape | label | desc | aliases |
|---|---|---|---|---|---|
| active | #8EDBE4 | circle | 进行中 | active timeline | active,进行中 |
| paused | #F2C879 | square | 已暂停 | frozen timeline | paused,已暂停 |
| success | #9BD6A5 | diamond | 已保存 | verified local write | saved,已保存 |
| error | #FFB38A | triangle | 未保存 | error/failure | error,failed,未保存 |
| unavailable | #A8BBC6 | dashed | 暂不可用 | disabled/missing | disabled,unavailable,暂不可用 |

### 3.3 Materials and scale

| Material | desc | treatment | glassStyle | opacity |
|---|---|---|---|---:|
| setupShell | setup shell | glass | Thick | .92 |
| controlShell | invoked controls | glass | Thickest | .96 |
| dialogSolid | critical focus | opaque | none | 1 |
| stageMatte | Stage text fallback | matte | none | .96 |

Scale: spacing xs4/s8/m16/l24/xl32; radius s12/m20/l32; icons s20/m28/l36. Web blur is approximation only. Glass is WindowContainer-only. Custom color and glass never stack.

## 4. Environment Adaptation

Body 18sp (above 12dp floor), targets at least 56dp, Web contrast target 4.5:1. Setup uses Thick glass with opaque fallback; control uses Thickest with opaque fallback. Stage labels use matte backing. Vibrant level 6 is limited to monochrome focus strokes in Shared Space and terminates at the component; image/gradient regions never rely on Vibrant. No saturated full field. Every semantic uses color + shape + label.

## 5. Window and Component Spec

### 5.0 Window structures

**WC-SETUP**: Planar 1280x800dp; min 960x720; max 1440x900; inset32; no attachment.

```text
+------------------------------------------+
| [C1 BeaconGuide] OR [C2 SetupChooser]    |
| [C6 StatusNotice]                        |
|          [C4 ConfirmDialog foreground]   |
+------------------------------------------+
```

One primary region. Large shows previews, Compact rows, Constrained steps. No orphan component.

**WC-CONTROL**: Planar 960x240dp; min 720x220; max 1120x280 normally and modal-only max 1120x480; inset24; no attachment.

```text
+------------------------------------------+
| [C3 PracticeControls] OR [C5 Completion] |
| [C6 Status] [C4 Dialog foreground]       |
+------------------------------------------+
```

One primary component; Constrained wraps actions, never scales targets/text.

### Component C1 BeaconGuide

| Field | Content |
|---|---|
| derivedFromTasks | T01,T06 |
| derivedFromData | firstRun, RhythmTimeline |
| purpose | teach/render shared rhythm |
| layoutRole | primary_hero |
| priority | primary |
| runtimeRole | rhythmGuide |

**Anatomy - Layout**

```text
        [c1-halo]
      [c1-lighthouse]
[c1-instruction OR c1-phase + c1-countdown]
```

Practice world anchor: forward horizon; lighthouse nominal 12m hypothesis; halo 6°-14°; text lower-center. Orientation Planar Grid tier rows: Regular halo220/lighthouse240/instruction96 with gaps24 and vertical padding32 total668dp; Compact halo200/lighthouse220/instruction88 with gaps16 and padding24 total588dp; Constrained halo184/lighthouse208/instruction104 with gaps16 and padding24 total576dp. One centered column, exactly three visible elements.

**Anatomy - Sizing**

| Tier | Size | Fit |
|---|---|---|
| Regular | Stage halo 6°-14°; Planar orientation 680x668dp | WC-SETUP default/max 1216x736 |
| Compact | Planar orientation 560x588dp | WC-SETUP Compact 896x656 |
| Constrained | Planar orientation 480x576dp, instruction 2 lines | WC-SETUP min 896x656 |

**Anatomy - Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | none | Stage subject |
| radius | circular halo | semantic geometry |
| padding | Regular xl32 vertical; Compact/Constrained l24 vertical; horizontal m16 | tier-specific scale |
| gap | l24 | scale |
| stroke | 2dp accent at <=.45 opacity | token |
| icon | N/A: lighthouse is subject | reason |
| primary text | body 18/28/500 | typography |
| secondary text | metric 28/36/500 | typography |
| hitTarget | 56dp invisible semantic activation region on lighthouse during S0 only; otherwise N/A | D01 controller activation without fourth visible element |

**Render Elements**

| id | label | type | bind | role |
|---|---|---|---|---|
| c1-lighthouse | 灯塔 | procedural3D+S0 semantic action region | scene.lighthouse | primary subject; S0 dispatch TR-01 |
| c1-halo | 光晕 | procedural3D | timeline.phaseProgress | phase geometry |
| c1-instruction | 光扩张时吸气，光收回时呼气。 | text | firstRun.copy | orientation-only |
| c1-phase | 吸气 / 停留 / 呼气 | text | timeline.currentPhase | practice-only |
| c1-countdown | 02:00 | text | timeline.remainingMs | practice-only |

**Data Bindings**

| Source | Target | Normal / fallback / error | Type |
|---|---|---|---|
| scene.lighthouse | lighthouse geometry | procedural / opaque prism / prism + C6 `灯塔未加载` | display |
| timeline.phaseProgress | halo | 0..1 / freeze last / force pause + C6 `节奏已暂停` | semantic |
| firstRun.copy | instruction | exact / built-in exact / built-in exact | display |
| timeline.currentPhase | phase | Chinese label / `节奏已暂停` / paused square semantic | semantic |
| timeline.remainingMs | countdown | mm:ss / last valid + --:-- / stop decrement + C6 | display |
| playback.visualScale/audioGain | halo/audio audit | timeline-derived / last valid+muted / force pause+mute | semantic |
| cycleAccent.completedCycle | distant accent | one/full cycle / omit / omit+C6 diagnostic | semantic |
| orientation.continueTransitionId | c1-lighthouse S0 activation | exact TR-01 / built-in TR-01 / built-in TR-01, never index | semantic |

**Variants**

| Variant | Trigger | Difference | Binding impact | Result selector |
|---|---|---|---|---|
| orientation | S0 | exactly lighthouse/halo/instruction | firstRun copy; no phase/countdown | `[data-variant=orientation]` |
| practice | S3/S4 | instruction hidden; phase/countdown shown | timeline bindings active | `[data-variant=practice]` |
| reducedMotion | preference | phase-boundary size/opacity steps | same timeline, no continuous scale | `[data-variant=reducedMotion]` |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility / stacking |
|---|---|---|---|---|---|
| orientation | S0 | exact 3 elements | standard | slow demo | exact text; base |
| active | inhale/exhale | expand/contract | 6°-14° | pattern time | phase text |
| hold | hold phase | fixed dashed edge | fixed | none | `停留` |
| paused | TR-06 | square + frozen | same | none | paused label |
| error | invalid clock | triangle + notice | same | none | C6 foreground |

Stacking: error > paused > hold > active; orientation excludes practice.

### Component C2 SetupChooser

| Field | Content |
|---|---|
| derivedFromTasks | T02,T03,T04,T05 |
| derivedFromData | SessionSelection,BreathPattern |
| purpose | bounded setup decisions |
| layoutRole | primary_setup |
| priority | primary |
| runtimeRole | decisionList |

**Anatomy - Layout**

```text
[c2-duration]
[c2-scene]
[c2-pattern] [c2-pattern-detail]
             [c2-start]
```

Grid 4 rows; choice rows 3 columns; start centered; gaps16/24.

**Anatomy - Sizing**

| Tier | Size | Fit |
|---|---|---|
| Regular | 1216x736dp | max/default safe area |
| Compact | 896x656dp | WC min; all text rows |
| Constrained | 896x656dp outer; active group <=896x220 | same min window, stepwise reflow |

**Anatomy - Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | none | parent glass |
| radius | m20 | scale |
| padding | xl32 | scale |
| gap | m16/l24 | scale |
| stroke | 2dp accent selected | token |
| icon | m28 | scale |
| primary text | title 28/38 | typography |
| secondary text | body 18/28 | typography |
| hitTarget | 64dp | >=56 |

**Render Elements**

| id | label | type | bind | role |
|---|---|---|---|---|
| c2-duration | 2分钟 / 4分钟 / 6分钟 | segmented | selection.duration | action |
| c2-scene | 海面 / 云层 / 沙丘 | segmented | selection.scene | action |
| c2-pattern | 舒缓 / 均衡 / 深长 | segmented | selection.pattern | action |
| c2-pattern-detail | 吸气·停留·呼气·停留 | text | pattern.phaseDurations | detail |
| c2-start | 开始练习 | button | selection.valid | TR-03 |

**Data Bindings**

| Source | Target | Normal / fallback / error | Type |
|---|---|---|---|
| selection.duration | duration selected | selected / 2m visibly default / disable start + duration C6 | semantic |
| selection.scene | scene selected/preview | selected / sea swatch / labels usable, broken preview C6 only | semantic |
| selection.pattern | pattern selected | selected / `舒缓` default / disable start + pattern C6 | semantic |
| pattern.phaseDurations | detail | 4 values / `详细时长暂不可用` / hide invalid values | display |
| selection.valid | start enabled | true / false / false + `请完成选择` | semantic |

**Variants**

| Variant | Trigger | Difference | Binding impact | Result selector |
|---|---|---|---|---|
| LargePreview | Large tier | preview swatches + phase detail | preview bindings active | `[data-variant=LargePreview]` |
| CompactRows | Compact tier | text-only three rows | preview hidden | `[data-variant=CompactRows]` |
| ConstrainedSteps | Constrained tier | one active group | activeGroup binding | `[data-variant=ConstrainedSteps]` |
| controllerFocus | controller navigation | persistent focus ring/order | focusSource controller | `[data-variant=controllerFocus]` |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility / stacking |
|---|---|---|---|---|---|
| default | S1 | muted outlines | fixed | none | labels |
| focused | focus | accent + 1.03 | visual | 120ms | focus above selected |
| selected | activate | circle/check | fixed | 160ms | text cue |
| disabled | invalid | dashed | fixed | none | reason |
| error | binding error | triangle row | fixed | none | C6, others usable |

Stacking: error > disabled > focused+selected > selected > focused.

### Component C3 PracticeControls

| Field | Content |
|---|---|
| derivedFromTasks | T06,T07,T08,T09,T04 |
| derivedFromData | RhythmTimeline,PauseSnapshot,queuedPattern |
| purpose | interrupt/control current run |
| layoutRole | supporting_control |
| priority | secondary |
| runtimeRole | control |

**Anatomy - Layout**

```text
[c3-status] [c3-pause] [c3-rhythm] [c3-restart] [c3-exit]
```

Grid 1x5 default; 2 rows constrained; fixed focus order.

**Anatomy - Sizing**

| Tier | Size | Fit |
|---|---|---|
| Regular | 1072x224dp | max safe area |
| Compact | 912x192dp | default safe area |
| Constrained | 672x172dp | two rows, targets held |

**Anatomy - Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | glass Thickest | system window material |
| radius | l32 | scale |
| padding | l24 | scale |
| gap | s8/m16 | scale |
| stroke | 1dp #456377 | derived |
| icon | m28 | scale |
| primary text | body 18/28 | typography |
| secondary text | caption 14/20 | typography |
| hitTarget | 56dp | platform |

**Render Elements**

| id | label | type | bind | role |
|---|---|---|---|---|
| c3-status | 进行中 / 已暂停 | status | timeline.runState | semantic |
| c3-pause | 暂停 / 继续 | button | timeline.runState | TR-06/TR-07 |
| c3-rhythm | 节奏 | button | queuedPattern | TR-12 |
| c3-restart | 重新开始 | button | timeline.canRestart | TR-08 |
| c3-exit | 退出 | button | stage.canExit | TR-15 |

**Data Bindings**

| Source | Target | Normal / fallback / error | Type |
|---|---|---|---|
| timeline.runState | status/pause | translated / paused / forced paused+C6 | semantic |
| queuedPattern | rhythm mark | name / `未更改` / clear queue, active unchanged | display |
| timeline.canRestart | restart | bool / false / false+reason | semantic |
| stage.canExit | exit | true / system Back remains / C6 + stable system exit | semantic |
| pauseSnapshot | status/resume | exact snapshot / last safe paused / remain paused+C6 | semantic |

**Variants**

| Variant | Trigger | Difference | Binding impact | Result selector |
|---|---|---|---|---|
| running | S3 | pause primary, active circle | runState active | `[data-variant=running]` |
| paused | S4 | resume primary, paused square | pauseSnapshot visible | `[data-variant=paused]` |
| patternQueued | TR-12 | queued mark/restart invitation | queuedPattern | `[data-variant=patternQueued]` |
| ConstrainedWrap | min tier | two command rows | order unchanged | `[data-variant=ConstrainedWrap]` |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility / stacking |
|---|---|---|---|---|---|
| hidden | no invoke | absent/noninteractive | 0 | 180ms fade | controller reveals |
| default | invoked | Thickest panel | tier | 180ms | focus order |
| focused | focus | accent | 1.03 | 120ms | label |
| paused | S4 | square/resume primary | same | none | status |
| disabled | invalid action | dashed/reason | same | none | skipped focus |

Stacking: C4 > C6 > paused > focused > default > hidden.

### Component C4 ConfirmDialog

| Field | Content |
|---|---|
| derivedFromTasks | T05,T08,T09 |
| derivedFromData | ConfirmationSnapshot |
| purpose | block commit, exact cancel restore |
| layoutRole | critical_primary |
| priority | primary |
| runtimeRole | confirmation |

**Anatomy - Layout**

```text
[c4-title]
[c4-body]
[c4-cancel] [c4-confirm]
```

Grid 3 rows; actions stack in Constrained.

**Anatomy - Sizing**

| Tier | Size | Fit |
|---|---|---|
| Regular | 640x360dp | default/max |
| Compact | 560x320dp | WC-CONTROL modal 800x420/content372 |
| Constrained | 480x360dp | modal 720x420/content372; stacked |

**Anatomy - Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | customColor #101B28 | opaque, no glass |
| radius | l32 | scale |
| padding | xl32 | scale |
| gap | m16/l24 | scale |
| stroke | 2dp brandPrimary | token |
| icon | l36 | scale |
| primary text | title 28/38 | typography |
| secondary text | body 18/28 | typography |
| hitTarget | 64dp | >=56 |

**Render Elements**

| id | label | type | bind | role |
|---|---|---|---|---|
| c4-title | 进入练习 / 重新开始 / 退出练习 | text | confirmation.kind | title |
| c4-body | 确认或返回 | text | confirmation.body | consequence |
| c4-cancel | 返回 | button | confirmation.cancelTransitionId | exact dispatch |
| c4-confirm | 确认 | button | confirmation.confirmTransitionId | exact dispatch |

**Data Bindings**

| Source | Target | Normal / fallback / error | Type |
|---|---|---|---|
| confirmation.kind | copy | kind-specific / `确认操作` / cancel-only | semantic |
| confirmation.body | c4-body | kind-specific consequence / `确认或返回` / safe cancel-only consequence | display |
| confirmation.cancelTransitionId | cancel | exact ID / snapshot-close / safe cancel + C6 log | semantic |
| confirmation.confirmTransitionId | confirm | exact ID / disabled / no commit+C6 | semantic |
| confirmation.snapshot | restore | full snapshot / captured in-memory prior / no mutation, safe return | semantic |

**Variants**

| Variant | Trigger | Difference | Binding impact | Result selector |
|---|---|---|---|---|
| entry | TR-03 | entry consequence; TR-04/TR-05 | setup snapshot | `[data-variant=entry]` |
| restart | TR-08 | restart consequence; TR-09/TR-10 | prior S3/S4/S7 | `[data-variant=restart]` |
| exit | TR-15 | exit consequence; TR-17/TR-16 | prior S3/S4 | `[data-variant=exit]` |
| cancelOnlyError | invalid ID/snapshot | confirm absent | no mutation | `[data-variant=cancelOnlyError]` |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility / stacking |
|---|---|---|---|---|---|
| open | TR-03/08/15 | opaque | tier | 180ms fade | focus trapped |
| cancelFocused | focus | accent cancel | same | 120ms | default destructive focus |
| confirmFocused | focus | brand confirm | same | 120ms | consequence |
| committing | confirm | disabled+spinner | same | <=300ms | status |
| error | invalid ID/snapshot | cancel-only | same | none | safe return |

Stacking z40; error > committing > focus > open. Cancel restores exact priorState, selection, timeline snapshot, and control visibility; only ephemeral focus may differ.

### Component C5 CompletionRecord

| Field | Content |
|---|---|
| derivedFromTasks | T11,T12,T08,T09 |
| derivedFromData | LocalPracticeRecord,completion |
| purpose | quiet closure and optional record |
| layoutRole | primary_closure |
| priority | primary |
| runtimeRole | completionAction |

**Anatomy - Layout**

```text
[c5-copy]
[c5-save] [c5-restart] [c5-exit]
[c5-result]
```

Grid 3 rows; actions wrap/stack.

**Anatomy - Sizing**

| Tier | Size | Fit |
|---|---|---|
| Regular | 1072x224dp | max |
| Compact | 912x192dp | default |
| Constrained | 672x172dp | vertical actions/internal scroll |

**Anatomy - Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | glass Thickest | window material |
| radius | l32 | scale |
| padding | l24 | scale |
| gap | m16 | scale |
| stroke | 1dp #456377 | derived |
| icon | m28 | scale |
| primary text | title 28/38 | typography |
| secondary text | caption 14/20 | typography |
| hitTarget | 56dp | platform |

**Render Elements**

| id | label | type | bind | role |
|---|---|---|---|---|
| c5-copy | 完成一次练习 | text | completion.copy | exact closure |
| c5-save | 保存本地记录 | button | record.canSave | TR-19 |
| c5-restart | 再来一次 | button | completion.canRestart | TR-08 |
| c5-exit | 结束 | button | completion.canExit | TR-21 |
| c5-result | 已保存 / 未保存 | status | record.writeResult | result |

**Data Bindings**

| Source | Target | Normal / fallback / error | Type |
|---|---|---|---|
| completion.copy | copy | exact / built-in exact / built-in exact | display |
| record.canSave | save | true / false+unavailable / false but exit/restart remain | semantic |
| record.writeResult | result | real write-readback success / not-attempted hides / triangle `未保存`+retry | semantic |
| completion.canRestart | restart | true / false / false while exit remains | semantic |
| completion.canExit | exit | true / system exit / system exit | semantic |

**Variants**

| Variant | Trigger | Difference | Binding impact | Result selector |
|---|---|---|---|---|
| unsaved | S7 | result hidden; save enabled | no result | `[data-variant=unsaved]` |
| saving | TR-19 pending | spinner/live status | write pending | `[data-variant=saving]` |
| saved | write+readback success | diamond label | verified result | `[data-variant=saved]` |
| saveFailed | throw/readback mismatch | triangle/retry; exit remains | error detail | `[data-variant=saveFailed]` |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility / stacking |
|---|---|---|---|---|---|
| default | S7 | exact copy/actions | tier | 240ms fade | no score/metrics |
| saving | TR-19 | spinner | same | <=300ms | live status |
| saved | readback success | diamond+label | same | 180ms | label |
| failed | write/readback error | triangle+retry | same | none | exit remains |
| focused | focus | accent | 1.03 | 120ms | label |

Stacking: failed/saved > saving > focused > default; C4 above.

### Component C6 StatusNotice

| Field | Content |
|---|---|
| derivedFromTasks | T13 |
| derivedFromData | BindingStatus |
| purpose | binding-specific recovery |
| layoutRole | status |
| priority | secondary |
| runtimeRole | statusBadge |

**Anatomy - Layout**

```text
[c6-shape] [c6-message] [c6-action]
```

One row; action optional; wraps Constrained.

**Anatomy - Sizing**

| Tier | Size | Fit |
|---|---|---|
| Regular | max720x96dp | WC-SETUP default/max or WC-CONTROL modal/max |
| Compact | max600x96dp | WC-SETUP Compact or WC-CONTROL default |
| Constrained | max480x144dp | both owning windows min tiers; two rows |

**Anatomy - Internal Metrics**

| Metric | Value | Source |
|---|---|---|
| background | customColor #101B28 | opaque, no glass |
| radius | s12 | scale |
| padding | m16 | scale |
| gap | s8 | scale |
| stroke | 2dp semantic | colors |
| icon | s20 | scale |
| primary text | body 18/28 | typography |
| secondary text | caption 14/20 | typography |
| hitTarget | 56dp when action | platform |

**Render Elements**

| id | label | type | bind | role |
|---|---|---|---|---|
| c6-shape | 状态 | semanticShape | bindingStatus.semantic | non-color cue |
| c6-message | 具体问题 | text | bindingStatus.message | detail |
| c6-action | 重试 / 返回 / 跳过 | button | bindingStatus.recoveryAction | recovery |

**Data Bindings**

| Source | Target | Normal / fallback / error | Type |
|---|---|---|---|
| bindingStatus.semantic | shape/color/label | mapped / dashed unavailable / triangle error | semantic |
| bindingStatus.message | message | binding-specific / `此项暂不可用` / must name duration,scene,pattern,timeline,stage,or save | display |
| bindingStatus.recoveryAction | action | exact action / safe return / hide unsafe retry, preserve exit | semantic |

**Variants**

| Variant | Trigger | Difference | Binding/recovery | Result selector |
|---|---|---|---|---|
| durationError | invalid duration | triangle names duration | safe 2m or return | `[data-variant=durationError]` |
| sceneFallback | preview fail | dashed sea swatch | keep label/continue | `[data-variant=sceneFallback]` |
| patternError | invalid phases | start disabled | choose safe pattern | `[data-variant=patternError]` |
| timelinePaused | invalid clock | paused square | remain paused/retry | `[data-variant=timelinePaused]` |
| stageEntryError | TR-22 | entry-specific copy | TR-23 exact return | `[data-variant=stageEntryError]` |
| saveFailure | write/readback fail | local-record copy | retry TR-19/skip TR-21 | `[data-variant=saveFailure]` |

**States**

| State | Trigger | Visual | Size | Motion | Accessibility / stacking |
|---|---|---|---|---|---|
| hidden | no issue | absent | 0 | none | not focusable |
| fallback | substitution | dashed+label | tier | 180ms | specific message |
| error | failed binding | triangle+action | tier | none | assertive region |
| focused | action focus | accent | 1.03 | 120ms | action label |
| recovering | retry | spinner/status | tier | <=300ms | live status |

Stacking: C4 > C6 error > C6 fallback > content. Every variant/state exposes its own preview selector/trigger.

### 5.1 Structure Checklist

| Component | Base | layout | sizing | metrics | render | bindings | variants | states/stack | Verdict |
|---|---|---|---|---|---|---|---|---|---|
| C1 | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| C2 | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| C3 | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| C4 | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| C5 | yes | yes | yes | yes | yes | yes | yes | yes | pass |
| C6 | yes | yes | yes | yes | yes | yes | yes | yes | pass |

### 5.2 Coverage Reconciliation

| Data entity | Timeliness | Binding | Method | Gap |
|---|---|---|---|---|
| SessionSelection | per run | C2 selection.* | choices | covered |
| BreathPattern | per run | C2 durations; C1 phase | detail+phase | covered |
| RhythmTimeline | every frame, single authority | C1/C3 timeline.* | geometry/text/state | covered |
| PlaybackEnvelope | every frame | C1 playback.visualScale/audioGain | direct audit binding | Web logic only, device audio later |
| CycleAccent | full-cycle event | C1 cycleAccent.completedCycle | direct subtle-accent binding | covered |
| PauseSnapshot | immediate | C3 pauseSnapshot + C4 snapshot | direct exact-restore binding | covered |
| LocalPracticeRecord | optional | C5 record.* | Web write/readback; runtime separate | covered boundary |
| BindingStatus | event | C6 status.* | specific copy/action | covered |

| Decision | Type | Component interaction | Gap |
|---|---|---|---|
| D01 | actionable | visible c1-lighthouse activation dispatches exact TR-01 | covered without fourth element |
| D02-D04 | actionable | C2 segmented | covered |
| D05 | high-risk | C2 start -> C4 entry | covered |
| D06 | actionable | C3 pause | covered |
| D07/D08 | destructive/high-risk | C3/C5 -> C4 | covered |
| D09 | actionable | C5 save/skip | covered |
| D10 | actionable | C6 saveFailure retry TR-19 / skip TR-21 | covered |
| D11 | actionable | C6 per-binding safe default/retry/return/remain-paused actions | covered |

| Primary -> subcomponent | Substates | Primitive | Binding |
|---|---|---|---|
| C1 lighthouse | orientation/fallback/error | c1-lighthouse | scene.lighthouse |
| C1 halo | orientation/inhale/hold/exhale/paused/error | c1-halo | phaseProgress/currentPhase |
| C1 instruction | orientation/hidden/fallback-exact/error-exact | c1-instruction | firstRun.copy |
| C1 phase | hidden/inhale/hold/exhale/paused/error | c1-phase | currentPhase |
| C1 countdown | hidden/active/paused/error/complete | c1-countdown | remainingMs |
| C2 duration | default/focused/selected/disabled/error | c2-duration | selection.duration |
| C2 scene | default/focused/selected/fallback/error | c2-scene | selection.scene |
| C2 pattern | default/focused/selected/disabled/error | c2-pattern | selection.pattern |
| C2 detail | loaded/fallback/error/hidden | c2-pattern-detail | phaseDurations |
| C2 start | enabled/focused/disabled/error | c2-start | selection.valid |
| C4 title | entry/restart/exit/cancelOnlyError | c4-title | confirmation.kind |
| C4 body | kind-specific/fallback/error | c4-body | confirmation.body |
| C4 cancel | default/focused/committing/error-safe | c4-cancel | cancelTransitionId/snapshot |
| C4 confirm | default/focused/committing/disabled/error-hidden | c4-confirm | confirmTransitionId |
| C5 copy | default/fallback-exact/error-exact | c5-copy | completion.copy |
| C5 save | enabled/focused/saving/disabled/error | c5-save | canSave/writeResult |
| C5 restart | enabled/focused/disabled | c5-restart | canRestart |
| C5 exit | enabled/focused/system-fallback | c5-exit | canExit |
| C5 result | hidden/saving/saved/failed | c5-result | writeResult |

## 6. Material and Depth

| Layer | treatment | glass | opacity | Content | Contrast |
|---|---|---|---:|---|---|
| Dialog z40 | opaque | none | 1 | confirmations | guaranteed |
| Control z24 | glass | Thickest | .96 | control/completion | opaque fallback |
| Setup z20 | glass | Thick | .92 | setup | opaque text fallback |
| Stage label | matte | none | .96 | phase/countdown | guaranteed |
| Environment | procedural matte | none | 1 | sky/sea/dune | no text |

## 7. Data Display Contract

Display-only: firstRun.copy, phaseDurations, remainingMs, confirmation.body, bindingStatus.message. Semantic: currentPhase, runState, writeResult, bindingStatus.semantic; always translated through §3.2. States are fresh/fallback/error for local config, and active/paused/complete/error for timeline. Network/offline/permission states are N/A because no such dependency exists. One timeline is authoritative; fallback cannot advance time; save success requires write/readback; Web proof never closes runtime persistence.

| Rule | Input | Output | Fallback | States |
|---|---|---|---|---|
| countdown | remainingMs | mm:ss ceiling | --:-- + pause | active/paused/error |
| phase | currentPhase | Chinese label | 节奏已暂停 | active/paused/error |
| save | writeResult | 已保存/未保存 | not-attempted hidden | fresh/error |
| null selection | selection.* | explicit safe default+notice | start disabled if unsafe | fallback/error |

## 8. PICO Numeric Spec

Window radius 32dp; body 18sp above 12dp; target 56dp; core FOV 65°x40°, secondary 85°x55°; Planar depth 640dp.

## 9. Assets

| Asset | Spec | Budget/use |
|---|---|---|
| lighthouse | procedural low-poly | <=2k triangles; base anchor; 3 LODs |
| sea/cloud/dune | procedural mesh/material | aggregate provisional <=30k; device profile |
| halo | procedural emissive ring | capped luminance; no flash |
| spatial audio | loopable source | sea/wind/lamp; provisional 350ms fade; device loudness test |
| icons | tintable SVG | 28dp grid |
| screenshots | PNG | selection and practice real preview paths |

## 10. Minimum Completeness Gate

| Check | Evidence | Verdict |
|---|---|---|
| Visual direction | §2, 3 directions + approval | pass |
| Visual language | §3-§4 precise values | pass |
| Window structures | §5.0 | pass |
| Component structure | six 8-part blocks + §5.1 | pass |
| Coverage reconciliation | §5.2 | pass |
| Semantics/trust | §6-§8 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass |
