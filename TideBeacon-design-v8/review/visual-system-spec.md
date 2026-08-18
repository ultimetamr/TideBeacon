# Visual System Spec · TideBeacon

> Active artifact revision: **6** | Stage 12 P-03 final reconciliation with interaction r10; pending gate confirmation.

## 0. Provenance and Boundary

Derived from Threshold-to-Beacon, PM r5, UXR r7, and the active interaction r10. Competitor visuals are observation-only; no historical TideBeacon file or fixed template was used. `templateReuse:false`.

## 1. Direct Output

Three materially different spatial directions are compared before architecture. Only the selected direction may constrain later visual facts.

## 2. Spatial Visual Direction Candidates

| Direction | Spatial thesis | First-view composition | Container relationship | Depth plan | Information hierarchy | Interaction cues | Spatial value | Dashboard risk / preview render instruction |
|---|---|---|---|---|---|---|---|---|
| V1 **Tidal Aperture** | a quiet circular light behaves like a tide around a far lighthouse; environment is negative space | horizon at lower third, lighthouse centered 8° below eye line, thin halo, exact sentence floating below with no plate | bounded threshold visually dissolves into Stage; later controls use one temporary low-contrast backing and never orbit | sky 0m-equivalent backdrop; horizon/far beacon; halo co-planar around beacon; text nearer; controls nearest only when summoned | halo/beacon first, exact sentence second, everything else absent initially | halo luminance rim and focus outline, not a button-shaped ornament | distance + scale + time + directional audio | low dashboard risk; render as low-poly dusk with broad empty sky, one cyan-ivory ring, no cards on first view |
| V2 **Paper Nautical Chart** | cadence is a planar chart whose contour lines expand/contract | large off-white chart plane with lighthouse glyph and concentric contour bands | one Planar experience, no environmental Stage depth | shallow layered paper relief within window | instruction, chart, timer visible together | controller focus resembles map waypoint | scale/time, little distance/depth | medium-high dashboard/illustration risk; render flat ink, typographic chart, visible controls |
| V3 **Constellation Passage** | phase is shown by moving between near/far star gates | wearer looks through several luminous rings toward a star beacon | Full Space with multiple spatial waypoints and wraparound cues | deep tunnel, several z-planes, particles around periphery | depth tunnel dominates, instruction secondary | gaze targets distributed around rings | strong depth/motion | high comfort and spectacle risk; render luminous rings, star field, no panels |

### 2.1 Selected approved visual reference

**V1 Tidal Aperture** is selected. It is the only direction that preserves the exact first-view set, one focus, low motion, distant direction/depth, and quiet environmental identity without a dashboard or tunnel spectacle.

### 2.2 Structured design-effect review (`visual-effect-v8-20260812-01`)

| Dimension | Score 1–5 | Evidence / risk |
|---|---:|---|
| spatial composition | 5 | horizon + distant beacon + near text establish 3 readable depth layers with one focus |
| visual hierarchy | 5 | first view has only three required elements; halo is dominant by area/contrast |
| domain expression | 5 | tide, beacon, cyclical aperture and sparse star accent arise from the product semantics |
| interaction legibility | 4 | focused halo can be legible without adding a fourth element; must be tested |
| PICO nativeness | 4 | Stage uses distance/direction; semantic decisions remain bounded/2D; actual device proof absent |
| aesthetic maturity | 4 | restrained low-poly procedural atmosphere; risk of becoming generic “calm gradient” is controlled by horizon/beacon geometry |
| handoff clarity at Stage 8 | 4 | first view, depth, hierarchy and exclusions are explicit; exact tokens/components correctly deferred |

**Verdict: pass as Stage 8 visual-direction approval.** At least direction/distance and depth/scale are visible qualities; minimum dimension is 4. This is aesthetic/directional approval only, not component, runtime, screenshot parity, or device validation.

### 2.3 Rejected directions

- V2 rejected: efficient but collapses the requested spatial value into a decorative Planar chart and risks a control-heavy app composition.
- V3 rejected: spatially striking but relies on depth tunnel/peripheral particles and implies forward movement, conflicting with comfort and one-focus restraint.

### 2.4 Approved reference constraints for later stages

1. First view: distant lighthouse + one halo + exact sentence only.
2. Stable horizon and camera; no forward travel, parallax ride, tunnel, strong flash, or full-field pulse.
3. Active cadence: halo remains the only dominant moving form; environment motion is subordinate and cycle-boundary-only.
4. Low-poly procedural geometry; matte/soft-emissive materials; no photographic ocean or competitor look.
5. Controls appear only on demand, nearer than the beacon, on one bounded surface; they never surround the wearer.
6. Completion preserves environmental calm and exact binary copy; no celebratory score layout.

## 3. Design Tokens

| Token | Value | Use |
|---|---|---|
| accent | `#A7F3E8` | focus/halo |
| brandPrimary | `#D8FFF7` | beacon light |
| surface | `#101B2CCC` | solid Stage backing |
| textPrimary | `#F4F8F7` | primary copy |
| textMuted | `#AAC0C3` | secondary |
| danger | `#FFB4A8` | exit/restart confirmation |
| radius | `32dp` | window/panels |

Typography: display sans 48/58/600; title sans 28/36/600; metric mono 36/44/500; body sans 18/28/500; caption sans 14/20/500. Chinese fallback `Noto Sans CJK SC, sans-serif`; never <12dp.

| Semantic | color | shape | label | desc | aliases |
|---|---|---|---|---|---|
| active | #A7F3E8 | circle | 进行中 | cadence active | active,进行中 |
| paused | #FFD98A | square | 已暂停 | user pause | paused,已暂停 |
| systemPaused | #B9C4FF | diamond | 系统暂停 | lifecycle pause | systemPaused,系统暂停 |
| error | #FFB4A8 | triangle | 需要恢复 | safe error | error,invalid,C7,错误 |
| saved | #BCE7B4 | circle | 已保存本地记录 | local write succeeded | saved,已保存 |
| notSaved | #D2D8DC | dashed | 未保存记录 | failed/not-written | failed,permission_denied,未保存 |

Materials: `thresholdGlass={glass,Regular,0.88}` only inside WC; `focusGlass={glass,Thick,0.94}` for WC dialog; `stageSolid={matte,none,0.92}` for Stage text/control backing; `beaconMatte={opaque,none,1}`. Web blur is approximation only. Stage never relies on glass/Vibrant. Shared Space threshold uses system glass Regular; key text gets opaque #101B2C backing when contrast falls below 4.5:1. Vibrant: only monochrome threshold copy at light tier; gradients/images use solid fallback.

Scale: spacing xs4/s8/m16/l24/xl32; radius s12/m20/l32; icon s20/m28/l36.

## 4. Environment Adaptation

Dark Full Space uses no large saturated fields; halo luminance capped and never flashes. Bright Shared Space switches threshold copy to solid backing. Text contrast target ≥4.5:1; text scaling 1.0–1.6. Full Space background controllable; Stage panels use `stageSolid`, not glass. Color always paired with shape/label.

## 5. Window Structure and Components

### 5.0 WC-Threshold shell

Planar 1120×700dp, min720×560/max1440×900, depth640dp, inset32dp, no docked attachments.

```text
┌── WC-Threshold 1120×700 ──────────┐
│ ┈C1 BeaconHaloGuide / preview 280h┈ │
│ gap16                               │
│ ┈C2 SessionChooser 3 columns 184h┈ │
│ ┈C3 PrimaryAction 72h┈           │
│ conditional: ┈C7 SafeRecovery┈       │
└──────────────────────────────┘
```

Large **1440×900**, Regular/default **1120×700**, Compact **960×640**, Constrained **720×560**. At Constrained, the S2 scene preview is hidden and the 656×496 content area contains C2 internal scroll 392dp + gap16dp + sticky C3 72dp = **480dp**, leaving 16dp slack. S1 renders only C1's lighthouse+halo+instruction elements. Stage maps C1,C4,C5,C6,C7 using the explicit world geometry below.

The seven core components C1–C7 below each preserve all eight required sections.

### C1 BeaconHaloGuide

| Field | Content |
|---|---|
| derivedFromTasks | T1,T4,T7 |
| derivedFromData | SessionClock,BreathPattern,SessionSelection,AppLifecycle,AccessibilityPrefs |
| Purpose | communicate phase and spatial anchor |
| layoutRole | primary_hero |
| Priority | primary |
| runtimeRole | cadenceGuide |

**Anatomy · Layout**
```text
┌─world/preview─┐
│ ┈lighthouse┈ │
│   ┈halo┈      │
│ ┈phase/countdown┈│
└──────────────┘
```
Grid: centered 3 rows. Stage lighthouse root is world-fixed at azimuth 0°, elevation −8°, distance 8m (allowed 7–9m), local center (0,0,0), upright and facing the user, with procedural bounds 0.9m wide × 2.1m high. Halo is a user-facing plane under that root at local (0,0,0.05m), diameter 2.2–4.5m (about 8°–16° visual radius). Active phase/countdown backing is user-head-relative at azimuth 0°, elevation −20°, distance 1.7m, local center (0,0,0), width 0.64m, height 0.12m, facing the user, readable at 1.4–1.9m. WC preview uses the same centered hierarchy without claiming metre equivalence.

**Anatomy · Sizing**
| Tier | Size | fit |
|---|---|---|
| Large 1440×900 | WC 1376×320; Stage lighthouse/halo 32°×28°, text 0.64×0.12m | maximum fits |
| Regular 1120×700 | WC 1056×280; same Stage geometry | default fits |
| Compact 960×640 | WC 896×280; same Stage geometry | compact fits |
| Constrained 720×560 | WC halo280×280 + copy ≤2 lines; same Stage geometry | minimum fits |

**Anatomy · Internal Metrics**
| Metric | Value |
|---|---|
| background | none |
| radius | N/A world subject |
| padding | m16 text backing only |
| gap | m16 |
| stroke | halo 4dp-equivalent #A7F3E8 |
| icon | N/A procedural lighthouse |
| text | body18/28; metric36/44 |
| hitTarget | halo focus volume ≥56dp equivalent only in S1 |

**Render Elements renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| c1-lighthouse | 灯塔 | procedural3d | scene.id | anchor |
| c1-halo | 呼吸光环 | ring+focusTarget | clock.phaseProgress; action.continue | primary phase and S1 confirmation target |
| c1-instruction | 光扩张时吸气，光收回时呼气。 | text | copy.instruction | S1 only |
| c1-phase | 吸气 | text | clock.phaseLabel | S3 only |
| c1-countdown | 02:00 | text | clock.remaining | active metric |
| c1-cycle-accent | 细微星光 | particleGroup | clock.cycleBoundary | cycle-boundary only |
| c1-lifecycle | 系统暂停 | shape+status | lifecycle.semantic | conditional non-color lifecycle status |

**Data Bindings dataBindings[]**
| path | target | fallback | type |
|---|---|---|---|
| scene.id | c1-lighthouse/model | preview fixture may use procedural sea; runtime invalid routes C7 | display |
| clock.phaseProgress | c1-halo/pose | freeze+S10 | semantic |
| copy.instruction | c1-instruction/text | exact bundled copy | display |
| clock.phaseLabel | c1-phase/text | `暂停` if frozen | semantic |
| clock.remaining | c1-countdown/text | `--:--`+S10 | display |
| clock.cycleIndex | c1-cycle-accent/event | no particles | semantic |
| lifecycle.semantic | c1-lifecycle/label+diamond or square | freeze clock and audio | semantic |
| action.continue | c1-halo/onActivate | remain in S1 | semantic |
| prefs.reduceMotion | halo amplitude/particles | amplitude capped; particles off | semantic |
| prefs.textScale | text/reflow | wrap ≤2 lines | semantic |
| prefs.controllerMode | c1-halo/focus affordance | controller-confirm enabled | semantic |

**Variants variants**: `threshold` shows exactly lighthouse/halo/instruction; `active` shows lighthouse/halo/phase/countdown; `cycleBoundary` adds one subtle particle event after a completed loop only; `reduceMotion` caps halo amplitude and removes particles; `systemPaused` adds diamond status and freezes all clock-derived output; `completion` hides phase/countdown and freezes halo.

**States states**
| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | S1 idle | emissive rim | base | none | exact instruction |
| active | valid clock | emissive rim | clock-derived | phase spec | phase label+audio |
| focused | S1 focus | 2dp diamond outline | 1.03 | 120ms | controller focus |
| pressed | continue | brighter edge | .98 | 80ms | action ack |
| disabled | invalid threshold data | focus target inert; error label | fixed | none | reason+C7 |
| paused | user pause | square label 已暂停 | frozen | none | text label |
| systemPaused | lifecycle | diamond label 系统暂停 | frozen | none | distinct label |
| cycleBoundary | cycleIndex increments | ≤24 subtle particles once | bounded | ≤600ms | decorative, nonessential |
| reduceMotion | preference true | no particles; capped scale | bounded | phase still readable | phase text retained |
| loading | valid resource load | stable lighthouse placeholder | fixed | none | 正在准备 |
| empty | no renderable scene resource | procedural lighthouse remains; C7 | fixed | none | readable recovery |
| error | invalid clock/scene | freeze stable frame; C7 | no NaN | none | readable recovery |
| overflow | text/metric overflow | wrap/clamp without clipping | bounded | none | full spoken label |

Stacking: error > disabled > systemPaused > paused > pressed > focused > active; focused+paused keeps square label and focus outline.

### C2 SessionChooser

| Field | Content |
|---|---|
| derivedFromTasks | T2,T3 |
| derivedFromData | SessionSelection,BreathPattern,AccessibilityPrefs |
| Purpose | choose duration, scene, rhythm |
| layoutRole | primary_explore |
| Priority | primary |
| runtimeRole | decisionList |

**Anatomy · Layout**
```text
┌─duration─┬─scene─┬─rhythm─┐
│ ┈options┈ │┈options┈│┈options┈ │
└─────────┴───────┴────────┘
```
Grid 3 columns 1fr, gap16; Constrained 3 rows/internal scroll.

**Anatomy · Sizing**
| Tier | Size | fit |
|---|---|---|
| Large 1440×900 | 1376×184 | maximum |
| Regular 1120×700 | 1056×184 | default |
| Compact 960×640 | 896×248 stacked | compact |
| Constrained 720×560 | 656×392 internal scroll | min; combined fit 392+16+72=480≤496 |

**Anatomy · Internal Metrics**
| Metric | Value |
|---|---|
| background | glass Regular (WC only) |
| radius | m20 |
| padding | m16 |
| gap | s8/m16 |
| stroke | 2dp selected #A7F3E8 |
| icon | m28 scene symbols |
| text | title28; body18 |
| hitTarget | 56×56dp |

**Render Elements renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| c2-duration | 时长 | optionGroup | selection.duration | decision |
| c2-scene | 场景 | optionGroup | selection.scene | decision |
| c2-pattern | 节奏 | optionGroup | selection.pattern | decision |

**Data Bindings dataBindings[]**
| path | target | fallback | type |
|---|---|---|---|
| selection.duration | c2-duration/selected | preview fixture may show 2 minutes; runtime invalid routes C7 | semantic |
| selection.scene | c2-scene/selected | preview fixture may show 海面; runtime invalid routes C7 | semantic |
| selection.pattern | c2-pattern/selected | preview fixture may show first preset; runtime invalid routes C7 | semantic |
| prefs.textScale | groups/reflow | internal scroll; targets retained | semantic |
| prefs.controllerMode | options/focus | all options controller operable | semantic |

**Variants variants**: `threeColumn`; `stacked`; `recordingLocked` disables edits while an active record/write operation exists.

**States states**
| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | valid options | glass | regular | none | group labels |
| focused | controller/gaze | outline+diamond | 1.03 |120ms| announced option |
| selected | valid chosen option | accent+check | same |none| text check |
| pressed | choose | brighter edge | .98 |80ms| action acknowledgement |
| disabled | recording/active | muted + 不可用 | same | none | label |
| loading | presets loading | skeleton + 正在准备 | same | none | readable live label |
| empty | presets absent | `无可用节奏` + C7 | same | none | readable |
| error | invalid pattern | triangle + C7 action | same | none | error label |
| overflow | textScale/long labels | vertical scroll/wrap | same | none | no clipping |

Stacking: error > disabled(recordingLocked) > pressed > selected > focused > default; focused+selected retains check+outline.

### C3 PrimaryAction

| Field | Content |
|---|---|
| derivedFromTasks | T1,T3 |
| derivedFromData | SessionSelection,validation,AccessibilityPrefs |
| Purpose | continue/start without ambiguity |
| layoutRole | critical_primary |
| Priority | primary |
| runtimeRole | control |

**Anatomy · Layout**
```text
┌─┈label┈─┈status┈─┐
└── centered row ────┘
```
Grid 1 row; S1 visible geometry is C1 halo, so C3 has no separate visible DOM in S1.

**Anatomy · Sizing**
| Tier | Size | fit |
|---|---|---|
| Large 1440×900 | 400×72 | maximum |
| Regular 1120×700 | 360×72 | default |
| Compact 960×640 | 100%×72 | compact |
| Constrained 720×560 | sticky 656×72 | min; participates in 480≤496 fit |

**Anatomy · Internal Metrics**
| Metric | Value |
|---|---|
| background | customColor #D8FFF7 |
| radius | l32 |
| padding | 16/24 |
| gap | s8 |
| stroke | 2dp transparent/focus |
| icon | s20 optional |
| text | title28 #10202A |
| hitTarget | 72dp |

**Render Elements renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| c3-start | 开始 | button | validation.canStart | Stage entry |
| c3-status | 选择未完成 | text | validation.message | conditional |

**Data Bindings dataBindings[]**
| path | target | fallback | type |
|---|---|---|---|
| validation.canStart | c3-start/enabled | false | semantic |
| validation.message | c3-status/text | hidden | display |
| prefs.textScale | c3-start+status/reflow | height may grow to 96dp; no clip | semantic |
| prefs.controllerMode | c3-start/focus+activate | controller-confirm enabled | semantic |

**Variants variants**: `haloContinuation` has no extra visible element in S1; `startButton` in S2; `disabledStart` with reason.

**States states**
| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | valid idle | solid | 72 |none| label |
| focused | focus | outline | 72 |120ms| focus+label |
| pressed | activate | solid .98 | 72 |80ms| action ack |
| disabled | invalid | gray + reason | same |none| 不可开始 |
| loading | Stage opening | spinner+正在进入 |same|500ms max| status text |
| empty | missing choice | disabled + reason |same|none| readable reason |
| error | validation/entry fail | C7 |same|none| readable reason |
| overflow | text scale | wrap, height max96 | variable |none| no clip |

Stacking: error > loading > disabled > pressed > focused > default.

### C4 PracticeControls

| Field | Content |
|---|---|
| derivedFromTasks | T5,T6 |
| derivedFromData | SessionClock,AppLifecycle,AccessibilityPrefs |
| Purpose | pause/resume/restart/exit |
| layoutRole | supporting_on_demand |
| Priority | secondary |
| runtimeRole | controlCluster |

**Anatomy · Layout**
```text
┌┈resume/pause┈┈rhythm┈┈restart┈┈exit┈┐
└─────────────────────────────┘
```
Stage Grid is four equal columns with 0.016m gap. The strip is user-head-relative at azimuth 0°, elevation −24°, distance 1.4m, local center (0,0,0), width 0.68m, height 0.088m, facing the user; readable range 1.2–1.8m. With textScale ≥1.3 it becomes a 2×2 Grid, width 0.36m, height 0.168m, same anchor/orientation.

**Anatomy · Sizing**
| Tier | Size | fit |
|---|---|---|
| WC Large/Regular/Compact/Constrained | N/A for Stage-only C4 | WC resize does not scale Stage controls |
| Stage default | 0.68×0.088m at 1.4m ≈27.3°×3.6° | within 65°×40° core; readable range 1.2–1.8m |
| Stage textScale/reflow | 0.36×0.168m at 1.4m ≈14.7°×6.9°, 2×2 | target ≥64mm-equivalent; device validation pending |

**Anatomy · Internal Metrics**
| Metric | Value |
|---|---|
| background | customColor #101B2CEB (Stage) |
| radius | l32 |
| padding | m16 |
| gap | m16 |
| stroke | 1dp #AAC0C3 |
| icon | m28 |
| text | body18 |
| hitTarget | 64×64 |

**Render Elements renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| c4-pause | 暂停 | button | lifecycle.userPaused | pause/resume |
| c4-pattern | 节奏 | button | selection.patternLabel; action.changePattern | confirmed restart/edit-selection path |
| c4-restart | 重新开始 | button | action.restart | high impact |
| c4-exit | 退出 | button | action.exit | high impact |
| c4-system-status | 系统暂停 | shape+status | lifecycle.systemPaused | read-only diamond status |

**Data Bindings dataBindings[]**
| path | target | fallback | type |
|---|---|---|---|
| lifecycle.userPaused | c4-pause/label | 暂停 | semantic |
| lifecycle.systemPaused | c4-system-status/visible; all controls/enabled | controls disabled and clock/audio frozen | semantic |
| selection.patternLabel | c4-pattern/subLabel | 当前节奏 | display |
| action.changePattern | c4-pattern/onActivate | disabled when systemPaused; otherwise TR26 | semantic |
| action.restart | c4-restart/action | disabled if systemPaused | semantic |
| action.exit | c4-exit/action | always stable | semantic |
| prefs.textScale | strip/Grid | 2×2 reflow | semantic |
| prefs.controllerMode | controls/focus order | pause→rhythm→restart→exit | semantic |

**Variants variants**: `activeControls`; `pausedControls`; `systemPausedReadOnly` distinct; `recordingLocked` hides during local write.

**States states**
| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | active summoned controls | solid |same|none| labels/icons |
| focused | controller focus | outline |same|120ms| spoken focus |
| pressed | valid action | .98 |same|80ms| action ack |
| paused | user pause | square+已暂停 |same|none| explicit |
| systemPaused | lifecycle | diamond+系统暂停; actions disabled |same|none| distinct |
| disabled | blocked/system pause | muted |same|none| reason |
| loading | opening dialog | spinner |same|none| live status |
| empty | no available control action | controls hide; stable exit remains |same|none| no dead focus |
| error | action fail | C7 |same|none| readable |
| overflow | textScale | 2×2 |same|none| readable |

Stacking: systemPaused > error > loading > disabled > empty > paused > pressed > focused; focused+disabled never scales.

### C5 ConfirmDecision

| Field | Content |
|---|---|
| derivedFromTasks | T6 |
| derivedFromData | pendingAction,priorState,AccessibilityPrefs |
| Purpose | block restart/exit until explicit response |
| layoutRole | critical_primary |
| Priority | primary |
| runtimeRole | alertDialog |

**Anatomy · Layout**
```text
┌┈title┈─────┐
│┈body┈          │
│┈cancel┈ ┈confirm┈│
└───────────────┘
```
Grid: title row, body row, action row with two equal columns and 0.016m/16dp gap; Compact/Constrained actions stack. In WC it is centered within the 656dp content width. In Stage it is user-head-relative at azimuth 0°, elevation 0°, distance 1.35m, local center (0,0,0), width 0.56m, height 0.30m, facing the user; readable range 1.2–1.6m. Modal focus trap applies in both legal render contexts.

**Anatomy · Sizing**
| Tier | Size | fit |
|---|---|---|
| WC Large/Regular 1440×900 / 1120×700 | 560×300dp | WC maximum/default |
| WC Compact/Constrained 960×640 / 720×560 | 480×340dp / 480×400dp, actions vertical | WC compact/minimum |
| Stage default | 0.56×0.30m at 1.35m ≈23.4°×12.7° | within 65°×40° core; readable 1.2–1.6m |
| Stage reflow | 0.48×0.40m at 1.35m ≈20.2°×16.9° | independent of WC tier; device validation pending |

**Anatomy · Internal Metrics**
| Metric | Value |
|---|---|
| background | WC glass Thick; Stage customColor #101B2CF5 |
| radius | l32 |
| padding | l24 |
| gap | m16 |
| stroke | 2dp danger on confirm |
| icon | m28 triangle |
| text | title28/body18 |
| hitTarget | 64dp |

**Render Elements renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| c5-title | 确认退出？ | heading | pendingAction.label | decision |
| c5-body | 本次进度不会保留。 | text | pendingAction.detail | consequence |
| c5-cancel | 取消 | button | action.cancel | safe default |
| c5-confirm | 确认 | button | action.confirm | high impact |

**Data Bindings dataBindings[]**
| path | target | fallback | type |
|---|---|---|---|
| pendingAction.label | c5-title/text | 确认操作？ | display |
| pendingAction.detail | c5-body/text | 请确认。 | display |
| priorState | cancel/return target | S10 safe return | semantic |
| prefs.textScale | dialog/reflow | vertical actions; internal body scroll | semantic |
| prefs.controllerMode | dialog/focus trap | cancel first; B cancels | semantic |

**Variants variants**: `exit`; `restart`; copy differs, behavior identical.

**States states**
| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | open | modal |same|220ms| focus trap |
| focused | controller focus | outline |same|120ms| spoken focus |
| pressed | activate | .98 |same|80ms| action ack |
| disabled | action unavailable | buttons muted |same|none| reason |
| loading | action executing | buttons disabled/spinner |same|none| status |
| error | action fail | triangle+retry/cancel |same|none| readable |
| overflow | textScale | vertical actions/scroll |max400|none| no clip |
| empty | missing action | C7 close dialog |same|none| safe return |

Stacking: error > loading > disabled > pressed > focused; modal always above C4 and below system shell.

### C6 CompletionRecord

| Field | Content |
|---|---|
| derivedFromTasks | T7,T8 |
| derivedFromData | LocalRecord,SessionSelection,AccessibilityPrefs |
| Purpose | binary completion and optional local write |
| layoutRole | primary_hero |
| Priority | primary |
| runtimeRole | completionPrompt |

**Anatomy · Layout**
```text
┌┈完成一次练习┈┐
│ ┈保存本地记录┈ │
│ ┈result┈          │
└────────────────┘
```
Grid: three centered rows with 0.016m gap. Stage anchor is user-head-relative at azimuth 0°, elevation 0°, distance 1.5m, local center (0,0,0), width 0.56m, height 0.28m, facing the user; readable range 1.3–1.8m. Only completion copy and optional-save action are initially visible.

**Anatomy · Sizing**
| Tier | Size | fit |
|---|---|---|
| WC Large/Regular/Compact/Constrained | N/A for Stage-only C6 | WC resize does not scale completion |
| Stage default | 0.56×0.28m at 1.5m ≈21.1°×10.7° | within 65°×40° core; readable 1.3–1.8m |
| Stage textScale/reflow | 0.48×0.36m at 1.5m ≈18.2°×13.7° | targets retained; device validation pending |

**Anatomy · Internal Metrics**
| Metric | Value |
|---|---|
| background | customColor #101B2CCC |
| radius | l32 |
| padding | l24 |
| gap | m16 |
| stroke | 1dp #AAC0C3 |
| icon | s20 local symbol |
| text | display48/body18/caption14 |
| hitTarget | 64dp |

**Render Elements renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| c6-copy | 完成一次练习 | heading | copy.completion | required exact |
| c6-save | 保存本地记录 | button | record.requested | optional |
| c6-result | 已保存本地记录 | status | record.status | conditional semantic |
| c6-spinner | 正在保存 | spinner+status | record.writeInProgress | conditional live status |

**Data Bindings dataBindings[]**
| path | target | fallback | type |
|---|---|---|---|
| copy.completion | c6-copy/text | exact bundled copy | display |
| record.requested | c6-save/action | false until optional save activates; System Back TR24 leaves without writing | semantic |
| record.status | c6-result/label+shape | 未保存记录 | semantic |
| record.payload | localStorage[`tidebeacon.records`] | do not claim saved | semantic |
| record.writeInProgress | c6-spinner/visible; c6-save/enabled | visible; save disabled | semantic |
| prefs.textScale | completion/reflow | panel height grows to 0.36m | semantic |
| prefs.controllerMode | optional save/focus; System Back | save is the only page target; B executes interaction TR24 | semantic |

**Variants variants**: `idle`; `recording` (real write in progress); `saved`; `notSaved`; `overflow` storage quota.

**States states**
| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | completion | exact copy |same|none| labels |
| focused | controller focus | outline |same|120ms| spoken focus |
| pressed | save input | .98 |same|80ms| action ack |
| recording | `writeInProgress=true` | spinner+正在保存 |same|none| live status |
| loading | completion payload formatting | stable copy+正在准备 |same|none| live status |
| disabled | write already active | save muted; System Back TR24 remains available |same|none| 正在保存 reason |
| saved | success | circle+已保存 |same|none| label |
| empty | no prior records | optional action remains |same|none| no false claim |
| error | write fail | triangle/dashed+未保存记录 |same|none| completion preserved |
| permission_denied | storage denied | dashed+未保存记录 |same|none| completion preserved |
| overflow | quota/text | same notSaved + wrap |same|none| no crash |

Stacking: error/overflow > recording > disabled > saved > pressed > focused > default; focused+recording disabled, no scale.

### C7 SafeRecovery

| Field | Content |
|---|---|
| derivedFromTasks | T9 |
| derivedFromData | validation,errorClass,overflow,AccessibilityPrefs |
| Purpose | guarantee non-render-failing stable recovery |
| layoutRole | critical_primary |
| Priority | primary |
| runtimeRole | errorRecovery |

**Anatomy · Layout**
```text
┌┈triangle┈┈message┈┐
│      ┈safeAction┈ │
└──────────────────┘
```
Grid: icon/message row plus action row, 0.016m gap. WC recovery is centered in the 656dp content width. Stage recovery is user-head-relative at azimuth 0°, elevation 0°, distance 1.3m, local center (0,0,0), width 0.56m, height 0.24m, facing the user; readable range 1.1–1.6m. If app DOM/world primitives cannot render, the OS/native plain fallback occupies the platform safe viewport and uses the same text/actions; it does not depend on this Grid.

**Anatomy · Sizing**
| Tier | Size | fit |
|---|---|---|
| WC Large/Regular 1440×900 / 1120×700 | 560×240dp | WC maximum/default |
| WC Compact/Constrained 960×640 / 720×560 | 480×280dp / ≤656×360dp scroll | WC compact/minimum |
| Stage default | 0.56×0.24m at 1.3m ≈24.3°×10.5° | within 65°×40° core; readable 1.1–1.6m |
| Stage reflow | 0.48×0.36m at 1.3m ≈20.9°×15.8° | independent of WC tier; device validation pending |

**Anatomy · Internal Metrics**
| Metric | Value |
|---|---|
| background | customColor #101B2CF5 |
| radius | l32 |
| padding | l24 |
| gap | m16 |
| stroke | 2dp #FFB4A8 |
| icon | m28 triangle |
| text | title28/body18 |
| hitTarget | 64dp |

**Render Elements renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| c7-icon | 需要恢复 | shape | error.semantic | non-color error |
| c7-message | 暂时无法继续。 | text | error.message | readable fallback |
| c7-action | 返回选择 | button | error.safeAction | stable return |
| c7-restart | 重试重新开始 | button | error.restartAction | clock/restart recovery only |
| c7-exit | 安全退出 | button | error.exitAction | always-stable exit |
| c7-overflow | 内容过长，可滚动 | status | overflow.semantic | overflow-specific status |

**Data Bindings dataBindings[]**
| path | target | fallback | type |
|---|---|---|---|
| error.semantic | c7-icon/shape+label | error/triangle | semantic |
| error.message | c7-message/text | 暂时无法继续。 | display |
| error.safeAction | c7-action/action | validation → TR18 return selection | semantic |
| error.restartAction | c7-restart/action | hidden unless restart is safe; otherwise exit remains | semantic |
| error.exitAction | c7-exit/action | close current app path safely | semantic |
| error.overflowAction | c7-action/action | overflow → TR31 plain-safe return selection | semantic |
| error.nativeSafeAction | native plain safe button/action | nativeFallback → TR32 | semantic |
| error.nativeExitAction | native plain exit button/action | nativeFallback → TR33 | semantic |
| overflow | layout/reflow | internal scroll | semantic |
| prefs.textScale | message/actions/reflow | internal scroll; no clip | semantic |
| prefs.controllerMode | recovery actions/focus order | safe action then exit | semantic |

**Variants variants**: `validation` offers TR18 return selection + TR29 exit; `clock` offers TR27 restart + TR29 exit; `restart` exposes TR27 validated restart + TR29 exit; `exit` exposes TR29; `overflow` exposes scroll status + TR31 safe return + TR29 exit; `nativeFallback` uses plain text/buttons with TR32 return selection + TR33 exit and no custom visual primitive. Storage denial/full/write failure is canonically S9/C6, not C7; only an independent app-render failure may replace S9 visually with `nativeFallback`, while preserving the same completion/not-saved semantics.

**States states**
| State | Trigger | Visual | Size | Motion | Accessibility |
|---|---|---|---|---|---|
| default | classified error | solid+triangle |same|none| label |
| focused | action focus | outline |same|120ms| controller |
| pressed | action input | .98 |same|80ms| action ack |
| loading | safe close | status 正在恢复 |same|none| live text |
| empty | missing error info | nativeFallback |same|none| bundled copy |
| error | fallback renderer fail | native/plain minimal |same|none| never recurse |
| overflow | content/text | scroll/wrap |auto|none| no clipping |
| disabled | no safe return yet | close-app action remains enabled |same|none| stable exit |
| validation | invalid selection/config | triangle+返回选择+安全退出 |same|none| no guessed default |
| clock | invalid/regressed clock | triangle+validated restart+exit |same|none| channels frozen/muted |
| restart | restart recovery allowed | 重试重新开始+exit |same|none| explicit action |
| exit | stable exit required | 安全退出 |same|none| always controller operable |
| nativeFallback | app renderer unsafe | OS plain text/buttons |safe viewport|none| independent renderer |

Stacking: nativeFallback/error > overflow > loading > disabled > pressed > focused; C7 always above app content, below system UI.

Fault-action contract: `validation` binds `safeAction=returnSelection`/TR18; `clock` binds `restartAction`/TR27 only after the monotonic clock is reinitializable; `restart` and `exit`/TR29 are independently addressable; `overflowAction` binds TR31 while retaining the overflow class; native plain safe/exit buttons bind TR32/TR33 independently of the app renderer. Storage failure remains S9/C6; System Back TR24 is its only exit, so no extra completion-page control appears. No C7 path silently substitutes a valid SessionSelection.

### 5.1 Structure checklist

| Core | Base | layout | sizing | metrics | render | bindings | variants | states/stack | Verdict |
|---|---|---|---|---|---|---|---|---|---|
| C1 |yes|yes|yes|yes|yes|yes|yes|yes|pass|
| C2 |yes|yes|yes|yes|yes|yes|yes|yes|pass|
| C3 |yes|yes|yes|yes|yes|yes|yes|yes|pass|
| C4 |yes|yes|yes|yes|yes|yes|yes|yes|pass|
| C5 |yes|yes|yes|yes|yes|yes|yes|yes|pass|
| C6 |yes|yes|yes|yes|yes|yes|yes|yes|pass|
| C7 |yes|yes|yes|yes|yes|yes|yes|yes|pass|

### 5.2 Coverage reconciliation

**Table A — every authoritative data field/decision variable (one row each)**

| Entity.field / decision | Timeliness | Render target | Transition/action target | Invalid/edge behavior | Coverage |
|---|---|---|---|---|---|
| BreathPattern.id | pre-start static | C2 pattern selected | C3 validation/start | unknown id → C7 validation | covered |
| BreathPattern.label | pre-start static | C2/C4 human label | restart edit | missing label → C7 validation | covered |
| BreathPattern.inhaleMs | pre-start static | C1 halo/phase | SessionClock phase boundary | ≤0/nonfinite → no start+C7 | covered |
| BreathPattern.holdInMs | pre-start static | C1 fixed expanded halo/phase | SessionClock phase boundary | <0/nonfinite → no start+C7 | covered |
| BreathPattern.exhaleMs | pre-start static | C1 halo/phase | SessionClock phase boundary | ≤0/nonfinite → no start+C7 | covered |
| BreathPattern.holdOutMs | pre-start static | C1 fixed contracted halo/phase | SessionClock phase boundary | <0/nonfinite → no start+C7 | covered |
| SessionClock.startMonotonicNanos | on confirmed start | no raw display | derives active elapsed | absent/regression → freeze+mute+C7 clock | covered |
| SessionClock.pausedAccumulatedNanos | pause/resume | no raw display | subtract from active elapsed | invalid → remain frozen+C7 clock | covered |
| SessionClock.pausedAtMonotonicNanos | pause event | C1/C4 pause status | freezes shared timeline | invalid → no resume jump+C7 | covered |
| SessionClock.activeElapsedNanos | every tick | C1 countdown | duration completion | NaN/regression → freeze+mute+C7 | covered |
| SessionClock.phase | every tick | C1 phase label | shared audio/visual envelope | invalid → stable frame+C7 | covered |
| SessionClock.phaseProgress | every tick | C1 halo pose | shared audio/visual envelope | clamp prohibited as recovery; freeze+C7 | covered |
| SessionClock.cycleIndex | completed-cycle boundary | C1 cycle accent | emit ≤24 subtle particles once | no boundary/no increment → no particles | covered |
| SessionSelection.duration | pre-start; frozen active | C2 selected; C1 countdown | C3 start/durationReached | preview-only 2m fixture; runtime invalid → C7, never default | covered |
| SessionSelection.scene | pre-start; frozen active | C2 selected; C1 scene | C3 start/load scene | preview-only sea fixture; runtime invalid → C7, never default | covered |
| SessionSelection.pattern | pre-start; frozen active | C2/C4 label | C3 start/confirmed restart | invalid → C7 validation | covered |
| LocalRecord.id | after opt-in write | no raw display | localStorage append | write failure → C6 未保存记录 | covered |
| LocalRecord.completedAt | terminal wall-clock only | no scoring/history claim | localStorage payload | missing → do not claim saved | covered |
| LocalRecord.durationMinutes | terminal payload | optional record data | localStorage payload | invalid → abort write; completion preserved | covered |
| LocalRecord.sceneLabel | terminal payload | optional record data | localStorage payload | missing → abort write; completion preserved | covered |
| LocalRecord.patternLabel | terminal payload | optional record data | localStorage payload | missing → abort write; completion preserved | covered |
| LocalRecord.requested | S8 user choice | C6 optional action | true begins actual write | false remains idle; System Back TR24 exits without write/result claim | covered |
| LocalRecord.writeInProgress | actual write interval | C6 spinner 正在保存 | disable repeat save | stall/error → S9/C6 error, completion preserved | covered |
| LocalRecord.status | write result | C6 shape+label | finish/exit remains | denied/full/error → 未保存记录 | covered |
| AppLifecycle.foreground | lifecycle event | C1/C4 status | restore originating pause semantic | missing event → remain safely frozen | covered |
| AppLifecycle.systemPaused | lifecycle event | C1/C4 diamond+系统暂停 | freeze all channels | System Back exposes exit, not restart | covered |
| AppLifecycle.resumeEpoch | resume event | no raw display | 600ms gain ramp from 0 | invalid → remain muted/frozen+C7 | covered |
| AccessibilityPrefs.reduceMotion | local preference | C1 amplitude; no particles | selects reduced-motion branch | missing → conservative capped motion | covered |
| AccessibilityPrefs.textScale | local 1.0–1.6 | C1–C7 wrap/reflow | changes tier layout only | overflow → scroll/C7, no clipping | covered |
| AccessibilityPrefs.controllerMode | local/input capability | focus marker on C1–C7 | controller ray/D-pad/A/B paths | missing gaze capability still controller-complete | covered |

**Table B — every task output/outcome (one row each)**

| Task output | Actionability | Owner | Binding/transition | Edge behavior | Coverage |
|---|---|---|---|---|---|
| T1 mappingUnderstood | actionable confirmation | C1 halo | action.continue → TR1 | remain S1 on no action | covered |
| T2 selectionConfirmed | actionable | C2 | duration+scene+pattern → validation | invalid never silently defaults | covered |
| T3 startAccepted | actionable/system | C3 | canStart → TR2 | opening shows loading | covered |
| T3 startRejected | system outcome | C3/C7 | validation.message/C7 | readable reason | covered |
| T4 phaseGuidanceAccepted | read/act | C1 | phase/progress/cycle shared timeline | drift/invalid → freeze+C7 | covered |
| T5 user interruption | actionable | C4 | TR3/TR4 | all channels freeze/ramp | covered |
| T5 system interruption | system/read-only | C1/C4 | TR5/TR6 | distinct systemPaused | covered |
| T6 exit decision | actionable | C4+C5 | TR8/TR10/TR11a/TR11b | System Back in S4 routes S6 | covered |
| T6 rhythm-change decision | actionable | C4+C5 | TR26 then TR13/TR14 | confirmed restart/edit path | covered |
| T6 restart decision | actionable | C4+C5 | TR12–TR14 | never entered by System Back | covered |
| T7 completed | terminal read-only | C6 | TR7 | exact binary copy only | covered |
| T8 persistence saved | actionable result | C6 | TR15/localStorage | actual write only | covered |
| T8 persistence notSaved | readable result with stable exit | S9/C6 | display 未保存记录; System Back TR24 exits | completion preserved; no extra button | covered |
| T9 validation recovery | actionable | C7 | returnSelection/exit | no guessed selection | covered |
| T9 clock recovery | actionable | C7 | restart/exit | restart only if safe | covered |
| T9 storage recovery | readable result with stable exit | S9/C6 | completion remains; 未保存记录; System Back TR24 exits | canonical route never enters C7 unless renderer itself fails | covered |
| T9 overflow recovery | actionable | C7 | scroll + TR31 safe return or TR29 exit | no render recursion | covered |
| T9 native-render recovery | actionable | native fallback | TR32 native return or TR33 native exit | independent of app renderer | covered |

**Table C — every primary subcomponent and runtime state (one row each)**

| Component.element | Runtime state | Primitive | Direct binding | Visible semantics | Coverage |
|---|---|---|---|---|---|
| C1 lighthouse | default | procedural3d | scene.id | 灯塔 | covered |
| C1 lighthouse | loading | procedural3d | scene.id | stable placeholder | covered |
| C1 lighthouse | empty | procedural3d | scene.id | procedural placeholder+C7 | covered |
| C1 lighthouse | error | procedural3d | scene.id | stable frame+C7 | covered |
| C1 halo | default | ring+focusTarget | action.continue | 呼吸光环 | covered |
| C1 halo | active | ring | phaseProgress | phase scale | covered |
| C1 halo | focused | ring+outline | controllerMode | focus diamond | covered |
| C1 halo | pressed | ring | action.continue | activation ack | covered |
| C1 halo | disabled | inert ring+status | threshold validity | reason+C7 | covered |
| C1 halo | paused | ring+square | lifecycle.userPaused | 已暂停 | covered |
| C1 halo | systemPaused | ring+diamond | lifecycle.systemPaused | 系统暂停 | covered |
| C1 halo | error | frozen ring | clock validity | stable frame+C7 | covered |
| C1 instruction | threshold | text | copy.instruction | exact sentence | covered |
| C1 phase | active | text | clock.phase | Chinese phase | covered |
| C1 phase | paused | text+square | lifecycle.userPaused | 已暂停 | covered |
| C1 phase | systemPaused | text+diamond | lifecycle.systemPaused | 系统暂停 | covered |
| C1 countdown | active | mono text | activeElapsedNanos | MM:SS | covered |
| C1 countdown | frozen | mono text | pausedAtMonotonicNanos | frozen MM:SS | covered |
| C1 countdown | invalid | mono text | clock validity | --:--+C7 | covered |
| C1 countdown | overflow | mono text | textScale | bounded/wrapped | covered |
| C1 cycle accent | cycleBoundary | particles | cycleIndex | one subtle event | covered |
| C1 cycle accent | reduceMotion | none | reduceMotion | no particles | covered |
| C2 duration | default | optionGroup | selection.duration | valid options | covered |
| C2 duration | focused | optionGroup+outline | controllerMode | focus | covered |
| C2 duration | selected | optionGroup+check | selection.duration | chosen duration | covered |
| C2 duration | pressed | optionGroup | selection.duration | action ack | covered |
| C2 duration | disabled | optionGroup | session lock | 不可用 | covered |
| C2 duration | loading | optionGroup | option source | 正在准备 | covered |
| C2 duration | empty | optionGroup | option source | readable empty+C7 | covered |
| C2 duration | error | optionGroup | selection validity | C7 | covered |
| C2 duration | overflow | scroll/wrap | textScale | no clipping | covered |
| C2 scene | default | optionGroup | selection.scene | 海面/云层/沙丘 | covered |
| C2 scene | focused | optionGroup+outline | controllerMode | focus | covered |
| C2 scene | selected | optionGroup+check | selection.scene | chosen scene | covered |
| C2 scene | pressed | optionGroup | selection.scene | action ack | covered |
| C2 scene | disabled | optionGroup | session lock | 不可用 | covered |
| C2 scene | loading | optionGroup | option source | 正在准备 | covered |
| C2 scene | empty | optionGroup | option source | readable empty+C7 | covered |
| C2 scene | error | optionGroup | selection validity | C7 | covered |
| C2 scene | overflow | scroll/wrap | textScale | no clipping | covered |
| C2 rhythm | default | optionGroup | selection.pattern | presets | covered |
| C2 rhythm | focused | optionGroup+outline | controllerMode | focus | covered |
| C2 rhythm | selected | optionGroup+check | selection.pattern | chosen rhythm | covered |
| C2 rhythm | pressed | optionGroup | selection.pattern | action ack | covered |
| C2 rhythm | disabled | optionGroup | session lock | 不可用 | covered |
| C2 rhythm | loading | optionGroup | pattern source | 正在准备 | covered |
| C2 rhythm | empty | optionGroup | pattern source | 无可用节奏+C7 | covered |
| C2 rhythm | error | optionGroup | pattern validity | C7 | covered |
| C2 rhythm | overflow | scroll/wrap | textScale | no clipping | covered |
| C3 Start | default | button | validation.canStart | 开始 | covered |
| C3 Start | focused | button+outline | controllerMode | focus | covered |
| C3 Start | pressed | button | action.start | action ack | covered |
| C3 Start | disabled | button+status | validation | 不可开始+reason | covered |
| C3 Start | loading | button+spinner | Stage entry | 正在进入 | covered |
| C3 Start | empty | button+status | selection | missing-choice reason | covered |
| C3 Start | error | button+status | validation | C7 | covered |
| C3 Start | overflow | wrap | textScale | no clipping | covered |
| C4 cluster | default | buttons | actions | controls | covered |
| C4 cluster | focused | button+outline | controllerMode | focus | covered |
| C4 cluster | pressed | button | action | action ack | covered |
| C4 cluster | paused | square+buttons | userPaused | 已暂停/继续 | covered |
| C4 cluster | systemPaused | diamond+disabled buttons | systemPaused | 系统暂停 | covered |
| C4 cluster | disabled | buttons | action availability | reason | covered |
| C4 cluster | loading | spinner | pending dialog | opening status | covered |
| C4 cluster | empty | stable exit button | action availability | no dead focus | covered |
| C4 cluster | error | status | action result | C7 | covered |
| C4 cluster | overflow | 2×2 Grid | textScale | readable labels | covered |
| C5 dialog | default | heading+body+buttons | pendingAction | consequence | covered |
| C5 dialog | focused | button+outline | controllerMode | focus trapped | covered |
| C5 dialog | pressed | button | confirm/cancel | action ack | covered |
| C5 dialog | disabled | buttons | action availability | reason | covered |
| C5 dialog | loading | spinner | action executing | status | covered |
| C5 dialog | error | triangle+buttons | action result | retry/cancel | covered |
| C5 dialog | overflow | scroll+stacked buttons | textScale | no clip | covered |
| C5 dialog | empty | safe fallback | pendingAction | C7 close dialog | covered |
| C6 completion | default | heading | copy.completion | 完成一次练习 | covered |
| C6 completion | focused | button+outline | controllerMode | focus | covered |
| C6 completion | pressed | button | record.requested | action ack | covered |
| C6 completion | recording | spinner+status | writeInProgress | 正在保存 | covered |
| C6 completion | loading | status | payload formatting | 正在准备 | covered |
| C6 completion | disabled | button+status | writeInProgress | 正在保存 | covered |
| C6 completion | saved | circle+status | record.status | 已保存本地记录 | covered |
| C6 completion | empty | button | record list | optional action | covered |
| C6 completion | error | triangle+status | record.status | 未保存记录 | covered |
| C6 completion | permission_denied | dashed+status | record.status | 未保存记录 | covered |
| C6 completion | overflow | wrap | quota/textScale | completion preserved | covered |
| C7 recovery | default | shape+text+button | error class | recovery | covered |
| C7 recovery | focused | button+outline | controllerMode | focus | covered |
| C7 recovery | pressed | button | selected recovery action | action ack | covered |
| C7 recovery | loading | status | recovery progress | 正在恢复 | covered |
| C7 recovery | empty | native fallback | error info | bundled recovery | covered |
| C7 recovery | error | native plain UI | renderer status | nonrecursive fallback | covered |
| C7 recovery | disabled | button/status | safe-action validity | exit remains | covered |
| C7 recovery | validation | triangle+buttons | safeAction+exitAction | 返回选择/安全退出 | covered |
| C7 recovery | clock | triangle+buttons | restartAction+exitAction | 重试重新开始/安全退出 | covered |
| C7 recovery | restart | button | restartAction | 重试重新开始 | covered |
| C7 recovery | exit | button | exitAction | 安全退出 | covered |
| C7 recovery | overflow | scroll+status+buttons | overflow.semantic+overflowAction+exitAction | 内容可滚动; TR31/TR29 | covered |
| C7 recovery | nativeFallback | OS plain text/buttons | nativeSafeAction+nativeExitAction | TR32/TR33 stable recovery | covered |

## 6. Material and Depth

Environment opaque/matte; WC Regular glass; WC dialog Thick; Stage panels solid. Depth mapping follows interaction §14. No glass in Stage. Passthrough WC uses solid contrast fallback; no TabBar issue because no TabBar. Vibrant only threshold monochrome copy, terminates before gradient/scene.

## 7. Data Display and Trust

Display-only: `copy.*`, `selection.*Label`, formatted remaining time, error.message. Semantic enums: lifecycle/status/error map through §3 labels; machine enums never display.

| State | Source/trust/presentation |
|---|---|
| loading | local operation pending; spinner+human label |
| fresh | valid in-memory selection/monotonic tick; no freshness badge needed |
| partial/conflicting | invalid selection/binding; C7, never guessed |
| offline | fully supported; no network dependency |
| permission_denied/error | localStorage denial/error; notSaved label; completion preserved |

Formatting: nanos never display; remaining `MM:SS`, fallback `--:--`; duration Chinese `2 分钟`; null label hidden or C7 if actionable. Trust: local only; no fabricated save; wall clock only `completedAt`; no medical inference.

## 8. PICO Numeric Spec

Radius32dp shell; body≥12dp (18 default); target≥56dp; window core 65°×40° conditional; Planar depth640dp.

## 9. Assets

Procedural sky/sea/cloud/dune/lighthouse, max lighthouse 8k tris LOD0/2k LOD1/500 LOD2; matte PBR 1024 textures max or shader-only. Halo shader procedural. Spatial audio 48kHz OGG/WAV loops, lighthouse/ambient source fixed; gain envelope shared clock. Particle max24/8 fallback. No downloaded case asset.

## 10. Minimum Completeness Gate

| Check | Evidence | Verdict |
|---|---|---|
| 3 substantially different directions | §2 V1–V3 | pass |
| selected direction and two rejections | §2.1/§2.3 | pass |
| structured design-effect approval | §2.2 | pass |
| final visual-system facts, 7 components x8 sections, reconciliation | §3–§9 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass |
