# TideBeacon 视觉系统规格

> artifactRevision: 6｜Stage 8–11 + DS-CR01..04｜active interaction@11｜approved D1

## 2. 空间视觉方向候选

| Direction | 空间命题 | 首视图 | 容器/深度 | 信息/交互 | 空间价值 | Dashboard 风险 |
|---|---|---|---|---|---|---|
| D1 潮汐灯标 | 空旷暗海中一座稳定低模灯塔，环是唯一节奏对象 | 地平线下1/3，灯塔中心，环低亮；一句中文 | 选择为 Planar；Stage 环绕低多边形环境，控制层近 | 几何+相位字；手柄焦点细轮廓 | 方向/距离/尺度 | 低 |
| D2 星图舱 | 环被拆成星座刻度和轨道 | 多层轨道/刻度 | 多深度信息层 | 仪表式选择 | 深度较强 | 高：像 dashboard，压过呼吸 |
| D3 雾中巨幕 | 大幅半透明雾幕上扩缩光斑 | 全视野雾幕 | 单一近距面 | 手柄点大面板 | 空间净增益低 | 中：像放大的2D动画 |

- **Approved visual reference**：D1「潮汐灯标」。结构化设计效果审查：单焦点=pass；≥2空间品质（世界锁定距离+尺度）=pass；最小目标计划≥56dp；无强闪烁/相机移动=pass；与竞品“内容丰富/环境数量”观察形成克制差异，不复制其画面。
- **Rejected**：D2 因仪表化和信息密度；D3 因 2D 放大与近距遮挡。审批依据为 PM P1/P3/P5、interaction §4/§6；不等同真人美学确认。

## 3. Tokens

| Token | Value | Use |
|---|---|---|
| brandPrimary | #BFE9FF | lighthouse light |
| accent | #FFD89A | focus/selected |
| surface | #101B2B | solid readable backing |
| text | #F4F8FF | primary copy |
| muted | #9DB1C8 | secondary |
| danger | #FFB4AB | destructive confirmation |

Typography: display sans 40/48/600; title sans 28/36/600; metric mono 24/32/500; body sans 18/28/500; caption sans 14/20/500. Text scaling 1–1.5.

| Semantic | color | shape | label | aliases |
|---|---|---|---|---|
| inhale | #BFE9FF | circle | 吸气 | inhale,吸气 |
| hold | #FFD89A | square | 停留 | hold,停留 |
| exhale | #8FC7E8 | diamond | 呼气 | exhale,呼气 |
| paused | #F4F8FF | dashed | 已暂停 | paused,已暂停 |
| destructive | #FFB4AB | triangle | 需确认 | exit,restart,退出,重开 |

Materials: WindowShell glass Thick opacity .92 (PICO system material; Web blur only approximation); Dialog glass Thickest .96; StageLabel matte/opaque #101B2B .88 (Stage cannot use system glass). Scale: spacing 4/8/16/24/32; radius 12/20/32; icon 20/28/36.

## 4. Environment adaptation

Body≥14sp (product minimum 18), target≥56dp, contrast target≥4.5:1. Shared Space text uses Thick/Thickest glass or solid fallback; gradient/image regions never use Vibrant. Monochrome labels may use Vibrant light tier, terminate at image preview; fallback #101B2B. Full Space uses controlled navy sky and opaque label backing. No saturation blocks/flashes.

## 5.0 Window layouts

BeaconWindow shell: Planar 1280×800, min960×720,max1440×900,inset32,no docked attachments.
```
┌ BeaconWindow ─────────────────┐
│ ┈BeaconIntro / ScenePicker┈   │
│ gap16                         │
│ ┈DurationPatternPicker┈       │
│ ┈C3.start.action┈             │
│ ┈C7 ConsentDialog overlay┈    │
│ ┈CompletionMarker (S7 only)┈  │
└───────────────────────────────┘
```
Grid: Large 3 cols; Compact one column; Constrained internal scroll/action pinned. Region mapping exact to listed components.

PausePanel shell: Planar 720×480,min640×440,max840×560,inset32,no docked attachments.
```
┌ PausePanel ───────────────┐
│ ┈PauseControl resume┈     │
│ ┈restart┈ ┈exit┈          │
│ ┈C7 ConsentDialog overlay┈ │
└───────────────────────────┘
```
Large horizontal secondary actions; Compact/Constrained vertical.

## 5. Deprecated compressed draft (history only; MUST NOT be consumed or reviewed)

> Superseded in full by §5A–§5C. All pass/fail and implementation facts use C1–C7 expanded blocks only.

### C1 BeaconIntro
| Field | Content |
|---|---|
| derivedFromTasks | T1 |
| derivedFromData | firstRun, phasePreview |
| Purpose | teach mapping |
| layoutRole | primary_hero |
| Priority | primary |
| runtimeRole | instruction |
Anatomy.layout:
```
┌ lighthouse ┐
│  ┈halo┈    │
│ ┈sentence┈ │
└────────────┘
```
Grid 3 rows centered gap16.
Sizing: Regular 720×560 within default; Compact 640×520 within min; Constrained 560×500 reflow text 2 lines.
Metrics: background none; radius N/A no panel; padding16; gap16; stroke2 accent; icon36; primary body18/28/500; secondary N/A; hitTarget56 invisible ack affordance.
renderSpec: `intro.lighthouse` “灯塔” model phasePreview; `intro.halo` no label ring phasePreview; `intro.copy` exact sentence text instruction; `intro.ack` “继续” action after focus.
dataBindings: `phasePreview→intro.halo.scale` fallback static mid; `instructionCopy→intro.copy.text` fallback exact required sentence; `firstRun→intro.ack.visible` fallback true.
Variants: normal; reduceMotion small-range; largeText two-line.
States: default visible/no motion; focused ack outline120ms; pressed ack 0.98/80ms; disabled N/A; loading static fallback; empty exact fallback; error same fallback; overflow wrap2 lines. Precedence disabled>pressed>focused.

### C2 ScenePicker
| Field | Content |
|---|---|
| derivedFromTasks | T2 |
| derivedFromData | config.scene, sceneAssets |
| Purpose | choose environment |
| layoutRole | primary_explore |
| Priority | primary |
| runtimeRole | decisionList |
Anatomy.layout:
```
┌ title ┐
│┈sea┈ ┈cloud┈ ┈dune┈│
└───────┘
```
Grid title + 3 columns; compact 3 rows.
Sizing: Regular 1216×280 default; Compact 896×360 min; Constrained 560×520 scroll.
Metrics: background glass Thick; radius32; padding24; gap16; stroke2 semantic; icon36; title28; label18; hitTarget96×72.
renderSpec: `scene.title`“选择场景”; `scene.sea`“海面”; `scene.cloud`“云层”; `scene.dune`“沙丘”.
dataBindings: `config.scene→item.selected` fallback sea; `sceneAssets.*→item.preview` fallback programmatic low-poly swatch.
Variants: sea wave-line; cloud layered-arc; dune triangle-ridge.
States: default stroke muted; focused accent120ms; selected 3dp+check; pressed .98; disabled readable; loading procedural placeholder; empty same; error fallback swatch; overflow vertical scroll. selected+focused both check+accent; disabled wins.

### C3 DurationPatternPicker
| Field | Content |
|---|---|
| derivedFromTasks | T2 |
| derivedFromData | config.durationSec, config.pattern |
| Purpose | choose duration and rhythm |
| layoutRole | supporting_decision |
| Priority | primary |
| runtimeRole | decisionList |
Anatomy.layout:
```
┌ duration: ┈2┈ ┈4┈ ┈6┈ ┐
│ pattern: ┈舒缓┈ ┈均衡┈ ┈延长呼气┈│
└────────────────────────┘
```
Grid 2 rows, label+3 options; compact stacks.
Sizing: Regular1216×200; Compact896×280; Constrained560×400.
Metrics: background glass Regular; radius32;padding24;gap16;stroke2;icon N/A text choices; title18; value18; hitTarget≥64×56.
renderSpec: `duration.label`“时长”; `duration.2/4/6` labels; `pattern.label`“节奏”; `pattern.calm/even/long` labels; `start.action`“开始”.
dataBindings: `config.durationSec→duration.selected` fallback120; `config.pattern→pattern.selected` fallback calm; `config.valid→start.enabled` fallback false.
Variants: regular/largeText/compact.
States: default; focused accent120; selected check+3dp; pressed .98; disabled “不可用”; loading N/A local; empty defaults; error “使用默认”; overflow wrap options. disabled>selected>focused.

### C4 BreathHalo
| Field | Content |
|---|---|
| derivedFromTasks | T4,T5d |
| derivedFromData | timeline.phase,progress,paused,cycleIndex,reduceMotion |
| Purpose | show four-phase rhythm |
| layoutRole | primary_hero_world |
| Priority | primary |
| runtimeRole | phaseIndicator |
Anatomy.layout:
```
[world lighthouse @0,0,-8m]
   ((halo 1.2–2.5m))
   [phase label @0,-.55,-3m]
```
World anchors fixed, billboard label, no camera motion.
Sizing: Regular halo diameter1.2–2.5m Stage; Compact reduceMotion1.6–1.9m; Constrained N/A Stage adapts and label stays central±20°.
Metrics: background none; radius N/A ring; padding N/A; gap0.2m; stroke0.025m; icon N/A; primary label28sp; secondary countdown18sp; hitTarget N/A noninteractive.
renderSpec: `halo.ring` ring progress; `halo.phaseLabel` “吸气/停留/呼气”; `halo.lighthouse` low-poly anchor; `halo.dust` particles; `halo.countdown` remaining.
dataBindings: `timeline.progress→ring.scale` fallback0; `timeline.phase→label/text+shape` fallback“已暂停”; `timeline.paused→motion.frozen` fallback true; `timeline.cycleIndex→dust.count` fallback0; `remainingMs→countdown.text` fallback“--:--”.
Variants: sea/cloud/dune environment; normal/reduceMotion/performance.
States: default phase motion exact timeline; focused N/A; pressed N/A; disabled N/A; loading static paused; empty static paused; error “已暂停” dashed; overflow countdown clamps. paused/error freeze wins; reduceMotion changes amplitude not time.

### C5 PauseControl
| Field | Content |
|---|---|
| derivedFromTasks | T5a,T5b,T5c |
| derivedFromData | timeline.snapshot, pendingIntent |
| Purpose | resume/restart/exit safely |
| layoutRole | critical_primary |
| Priority | primary |
| runtimeRole | control |
Anatomy.layout:
```
┌ ┈已暂停┈ ┐
│ ┈继续┈   │
│ ┈重开┈ ┈退出┈│
└──────────┘
```
Grid 3 rows; dialog replaces row3 on confirm.
Sizing: Regular656×416 within default; Compact576×376; Constrained576×376 scroll if text1.5.
Metrics: background glass Thickest;radius32;padding32;gap16;stroke2;icon28;title28;button18;hitTarget≥64×56.
renderSpec: `pause.status`“已暂停”; `pause.resume`“继续”; `pause.restart`“重新开始”; `pause.exit`“退出”; `pause.dialog` confirmation copy/actions.
dataBindings: `snapshot.remaining→status.secondary` fallback“时间已冻结”; `pendingIntent→dialog.copy` fallback hide.
Variants: base; exitConfirm; restartConfirm; largeText.
States: default resume primary; focused outline120; pressed .98; disabled readable; loading N/A; empty fallback frozen; error “保持暂停”; overflow vertical scroll. dialog modal>all; disabled>focused.

### C6 CompletionMarker
| Field | Content |
|---|---|
| derivedFromTasks | T6 |
| derivedFromData | localRecord.preference,writeState |
| Purpose | communicate completion and optional record |
| layoutRole | primary_hero |
| Priority | primary |
| runtimeRole | completion |
Anatomy.layout:
```
┌ ┈完成一次练习┈ ┐
│ ┈记录到此设备 toggle┈│
└────────────────┘
```
Grid 2 rows centered.
Sizing: Regular720×360;Compact640×320;Constrained560×360.
Metrics: background glass Thick;radius32;padding32;gap24;stroke0;icon N/A;primary28;secondary18;hitTarget64×56.
renderSpec: `complete.copy` exact required copy; `complete.record`“记录到此设备”; `complete.recordState`“已记录/未记录”.
dataBindings: `preference→record.selected` fallback false; `writeState→recordState.text` fallback“未记录”.
Variants: unrecorded/recorded/writeError/largeText.
States: default; focused toggle outline; pressed toggle; disabled N/A; loading “正在记录”; empty unrecorded; error “未记录”; overflow wrap. error overrides loading; selected+focused both check+outline.

## 5.1 Deprecated checklist (invalidated by DS-CR01)

| Component | base | layout | sizing | metrics | render | binding | variants | states | verdict |
|---|---|---|---|---|---|---|---|---|---|
| C1–C6 compressed draft | no | no | no | no | no | no | no | no | invalidated |

## 5A. Authoritative expanded component contract (supersedes compressed §5 rows)

Shared window content areas: Beacon default1216×736, min896×656, max1376×836 after 32dp inset; Pause default656×416,min576×376,max776×496. Every `background=glass` means `treatment=glass`, system `Modifier.backgroundMaterial(Material.<style>)`, never customColor simultaneously. Shared/MR contrast fallback for every glass component is opaque `surface #101B2B`; C3 uses Thick, not Regular.

### C1 BeaconIntro — expanded

|Base field|Value|
|---|---|
|derivedFromTasks|T1|
|derivedFromData|firstRun,phasePreview,instructionCopy,textScaling,reduceMotion|
|purpose|teach mapping|
|layoutRole|primary_hero|
|priority|primary|
|runtimeRole|instruction|

**anatomy.layout**
```
┌C1 720×560┐
│┈lighthouse┈│
│ ┈halo┈     │
│ ┈copy┈     │
│ ┈ack┈      │
└────────────┘
```
Grid 4 rows, center, gap16.

**sizing**
| Tier/window | W×H | Fits |
|---|---:|---|
| Regular/Beacon default1216×736 | 720×560 | yes |
| Compact/Beacon min896×656 | 640×560 | yes |
| Constrained/min with text1.5 | 640×624 | yes; copy wraps |

**metrics**
| Metric | Value |
|---|---|
| background | none |
| radius | N/A no panel |
| padding | m16 |
| gap | m16 |
| stroke | 2dp accent |
| icon | l36dp |
| primary text | body 18/28/500 |
| secondary | caption14/20/500 |
| hitTarget | 56×56dp |

**renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| intro.lighthouse | 灯塔 | model | phasePreview | anchor |
| intro.halo | — | ring | phasePreview | preview |
| intro.copy | 光扩张时吸气，光收回时呼气。 | text | instructionCopy | instruction |
| intro.ack | 继续 | button | firstRun | action |

**dataBindings[]**
| source | target/property | fallback | type |
|---|---|---|---|
| phasePreview | halo.scale | mid/static | display |
| instructionCopy | copy.text | exact required sentence | display |
| firstRun | ack.visible | true | semantic |
| textScaling | layout/font reflow | 1.0 | semantic |
| reduceMotion | halo.previewRange | true/small range | semantic |

**variants**: normal=full amplitude; reduceMotion=small amplitude; largeText=two-line copy.

**states**
| state | trigger | visual | size | motion | accessibility |
|---|---|---|---|---|---|
| default | enter S0 | text #F4F8FF | 1 | timeline preview | exact copy |
| focused | focus ack | 2dp accent | 1.03 |120ms|outline+label|
| pressed | A/trigger | accent fill |.98|80ms|haptic optional|
| disabled | N/A | readable muted |1|none|label|
| loading | preview unavailable | static halo |1|none|copy remains|
| empty/error | null/error | static mid |1|none|exact fallback|
| overflow | text1.5 | wrap2 lines |1|none|no truncate|
Stacking: error>loading>pressed>focused>default.

### C2 ScenePicker — expanded

|Base field|Value|
|---|---|
|derivedFromTasks|T2|
|derivedFromData|config.scene,sceneAssets,textScaling|
|purpose|choose environment|
|layoutRole|primary_explore|
|priority|primary|
|runtimeRole|decisionList|

**anatomy.layout**
```
┌C2┐
│┈title┈│
│┈sea┈ ┈cloud┈ ┈dune┈│
└──┘
```
Grid title+3 columns; compact one column.

**sizing**
| Tier/window | W×H | Fits |
|---|---:|---|
| Regular/default | 1216×280 | yes |
| Compact/min | 896×360 | yes |
| Constrained/min text1.5 | 896×520 | yes; scroll region |

**metrics**
| Metric | Value |
|---|---|
| background | glass Thick; MR fallback surface |
| radius | l32 |
| padding | l24 |
| gap | m16 |
| stroke | 2dp semantic |
| icon | l36 |
| primary text | title28/36/600 |
| secondary | body18/28/500 |
| hitTarget | 96×72dp |

**renderSpec.elements[]**
| id | label | type | bind | role |
|---|---|---|---|---|
| scene.title | 选择场景 | text | — | heading |
| scene.sea | 海面 | option | config.scene | decision |
| scene.cloud | 云层 | option | config.scene | decision |
| scene.dune | 沙丘 | option | config.scene | decision |

**dataBindings[]**
| source | target | fallback | type |
|---|---|---|---|
| config.scene | option.selected | sea | semantic |
| sceneAssets.sea/cloud/dune | option.preview | procedural swatch | display |
| textScaling | layout reflow | 1.0 | semantic |

**variants**: sea=wave line; cloud=layer arcs; dune=triangle ridge; largeText=stack.

**states**
|state|trigger|visual|size|motion|accessibility|
|---|---|---|---|---|---|
|default|none|muted stroke|1|none|labels|
|focused|ray/gaze|accent 2dp|1.03|120ms|outline|
|selected|activate|check+3dp|1|150ms|check+label|
|pressed|trigger|accent fill|.98|80ms|haptic optional|
|disabled|asset invalid|muted+“不可用”|1|none|text|
|loading|asset wait|procedural swatch|1|none|label|
|empty/error|null|procedural swatch|1|none|label|
|overflow|text1.5|vertical scroll|1|none|no truncate|
Stacking: disabled>selected+focused>focused>default.

### C3 DurationPatternPicker — expanded

|Base field|Value|
|---|---|
|derivedFromTasks|T2|
|derivedFromData|config.durationSec,config.pattern,config.valid,textScaling|
|purpose|choose duration/pattern and request start|
|layoutRole|supporting_decision|
|priority|primary|
|runtimeRole|decisionList|

**anatomy.layout**
```
┌C3┐
│┈时长┈ ┈2┈┈4┈┈6┈│
│┈节奏┈ ┈舒缓┈┈均衡┈┈延长呼气┈│
│        ┈开始┈│
└──┘
```
Grid 3 rows; compact wraps.

**sizing**
|Tier/window|W×H|Fits|
|---|---:|---|
|Regular/default|1216×240|yes|
|Compact/min|896×320|yes|
|Constrained/min text1.5|896×480|yes; options wrap|

**metrics**
|Metric|Value|
|---|---|
|background|glass Thick; MR opaque fallback|
|radius|l32|
|padding|l24|
|gap|m16|
|stroke|2dp semantic|
|icon|N/A text choices|
|primary text|body18/28/500|
|secondary|caption14/20/500|
|hitTarget|≥64×56dp|

**renderSpec.elements[]**
|id|label|type|bind|role|
|---|---|---|---|---|
|duration.label|时长|text|—|heading|
|duration.2|2 分钟|option|config.durationSec|decision|
|duration.4|4 分钟|option|config.durationSec|decision|
|duration.6|6 分钟|option|config.durationSec|decision|
|pattern.calm|舒缓|option|config.pattern|decision|
|pattern.even|均衡|option|config.pattern|decision|
|pattern.long|延长呼气|option|config.pattern|decision|
|start.action|开始|button|config.valid|action|

**dataBindings[]**
|source|target|fallback|type|
|---|---|---|---|
|config.durationSec|duration.selected|120|semantic|
|config.pattern|pattern.selected|calm|semantic|
|config.valid|start.enabled|false|semantic|
|textScaling|layout reflow|1.0|semantic|

**variants**: regular=inline; compact=wrapped; largeText=stacked.

**states**
|state|trigger|visual|size|motion|accessibility|
|---|---|---|---|---|---|
|default|valid|text|1|none|labels|
|focused|focus option|accent outline|1.03|120ms|outline|
|selected|activate|check+3dp|1|150ms|check|
|pressed|trigger|fill|.98|80ms|haptic optional|
|disabled|!valid start|muted readable|1|none|“请完成选择”|
|loading|N/A local|same default|1|none|local only|
|empty/error|null config|defaults+“使用默认”|1|none|text|
|overflow|text1.5|wrap/scroll|1|none|no truncate|
Stacking: disabled>selected+focused>focused.

### C4 BreathHalo — expanded

|Base field|Value|
|---|---|
|derivedFromTasks|T4,T5d|
|derivedFromData|timeline.phase,progress,paused,cycleIndex,remainingMs,reduceMotion,textScaling,config.scene|
|purpose|show rhythm/countdown|
|layoutRole|primary_hero_world|
|priority|primary|
|runtimeRole|phaseIndicator|

**anatomy.layout**
```
Stage world:
 lighthouse (0,-.15,-8m)
 ((ring center 0,-.15,-8m; faces user; normal +Z; diameter1.2–2.5m))
 ┈phaseLabel┈ (0,-.55,-3m)
 ┈countdown┈  (0,-.78,-3m)
```
World locked; Countdown is C4 subcomponent, not separate core component.

**sizing**
|Tier|metric|Fits|
|---|---|---|
|Regular Stage|ring1.2–2.5m|core±20°|
|Compact reduceMotion|1.6–1.9m|core±16°|
|Constrained performance|1.6m static segments|label/countdown central|

**metrics**
|Metric|Value|
|---|---|
|background|none; labels customColor #101B2BE0, no glass|
|radius|N/A ring|
|padding|label m16dp|
|gap|0.2m|
|stroke|0.025m|
|icon|N/A|
|primary text|title28/36/600|
|secondary|body18/28/500|
|hitTarget|N/A noninteractive|

**renderSpec.elements[]**
|id|label|type|bind|role|
|---|---|---|---|---|
|halo.ring|—|world ring|timeline.progress|primary metric|
|halo.phaseLabel|吸气/停留/呼气|text+shape|timeline.phase|semantic|
|halo.lighthouse|灯塔|model|config.scene|anchor|
|halo.dust|—|particles|timeline.cycleIndex|cycle event|
|halo.countdown|02:00|text|remainingMs|supporting metric|

**dataBindings[]**
|source|target|fallback|type|
|---|---|---|---|
|timeline.progress|ring.scale|0/static|display|
|timeline.phase|label text+shape|paused/已暂停|semantic|
|timeline.paused|ring.motion|true/frozen|semantic|
|timeline.cycleIndex|dust.count|0|display|
|remainingMs|countdown.text|--:--|display|
|reduceMotion|ring.range/dust.visible|true small/no dust|semantic|
|textScaling|label/countdown reflow|1.0|semantic|
|config.scene|lighthouse/environment variant|sea|semantic|

**variants**: sea=wave plane+navy sky; cloud=layered cloud planes+blue-gray sky; dune=triangular ridges+amber horizon; normal=1.2–2.5m ring+dust; reduceMotion=1.6–1.9m/no dust/text unchanged; performance=1.6m 32-segment ring/no dust/text unchanged.

**states**
|state|trigger|visual|size|motion|accessibility|
|---|---|---|---|---|---|
|inhale|phase|circle+吸气|grow|timeline exact|shape+text|
|hold1/hold2|phase|square+停留|locked|none|shape+text|
|exhale|phase|diamond+呼气|shrink|timeline exact|shape+text|
|paused|pause/lifecycle|dashed+已暂停|locked|none|text|
|loading/empty|missing snapshot|dashed|1.6m|none|已暂停|
|error|clock error|dashed danger outline|1.6m|none|已暂停|
|performance|budget|no dust 32 segments|1.6m|timeline exact|same text|
|overflow|remaining>99m|99:59+|same|none|readable|
Stacking: error>paused>reduceMotion/performance>phase.

### C5 PauseControl — expanded

|Base field|Value|
|---|---|
|derivedFromTasks|T5a,T5b,T5c|
|derivedFromData|timeline.snapshot,pendingIntent,textScaling|
|purpose|resume/restart/exit|
|layoutRole|critical_primary|
|priority|primary|
|runtimeRole|control|

**anatomy.layout**
```
┌C5┐
│┈已暂停┈│
│┈继续┈│
│┈重开┈ ┈退出┈│
└──┘
```
Grid 3 rows; C7 overlays for confirmation.

**sizing**
|Tier/window|W×H|Fits|
|---|---:|---|
|Regular/Pause default656×416|656×416|yes exact|
|Compact/Pause min576×376|576×376|yes vertical|
|Constrained/min text1.5|576×376 viewport|internal scroll, buttons stay64 high|

**metrics**
|Metric|Value|
|---|---|
|background|glass Thickest; MR opaque fallback|
|radius|l32|
|padding|xl32|
|gap|m16|
|stroke|2dp accent/danger|
|icon|m28|
|primary text|title28/36/600|
|secondary|body18/28/500|
|hitTarget|≥64×56|

**renderSpec.elements[]**
|id|label|type|bind|role|
|---|---|---|---|---|
|pause.status|已暂停|text|timeline.snapshot|status|
|pause.resume|继续|button|timeline.snapshot|primary action|
|pause.restart|重新开始|button|pendingIntent|destructive request|
|pause.exit|退出|button|pendingIntent|destructive request|

**dataBindings[]**
|source|target|fallback|type|
|---|---|---|---|
|timeline.snapshot.remaining|status.secondary|时间已冻结|display|
|pendingIntent|restart/exit selected|none|semantic|
|textScaling|layout/font reflow|1.0|semantic|

**variants**: base=horizontal secondary actions; largeText=vertical actions+scroll; controllerFocus=order resume→restart→exit with persistent 2dp ray outline.

**states**
|state|trigger|visual|size|motion|accessibility|
|---|---|---|---|---|---|
|default|S4|resume accent|1|none|labels|
|focused|ray|2dp outline|1.03|120ms|outline|
|pressed|activate|fill|.98|80ms|haptic optional|
|disabled|snapshot invalid|muted readable|1|none|error label|
|loading|N/A local|same|1|none|local|
|empty/error|null snapshot|“保持暂停”|1|none|stable exit remains|
|overflow|text1.5|scroll|1|none|focus visible|
Stacking: C7 modal>error>pressed>focused.

### C6 CompletionMarker — expanded

|Base field|Value|
|---|---|
|derivedFromTasks|T6|
|derivedFromData|localRecord.preference,writeState,textScaling|
|purpose|completion/optional record and exit|
|layoutRole|primary_hero|
|priority|primary|
|runtimeRole|completion|

**anatomy.layout**
```
┌C6┐
│┈完成一次练习┈│
│┈记录到此设备┈│
│┈记录状态┈│
│┈退出┈│
└──┘
```
Grid 4 rows centered; focus order record→exit.

**sizing**
|Tier/window|W×H|Fits|
|---|---:|---|
|Regular/Beacon default|720×416|yes|
|Compact/Beacon min|640×376|yes|
|Constrained/min text1.5|640×480|yes|

**metrics**
|Metric|Value|
|---|---|
|background|glass Thick; MR opaque fallback|
|radius|l32|
|padding|xl32|
|gap|l24|
|stroke|2dp focused only|
|icon|N/A|
|primary text|title28/36/600|
|secondary|body18/28/500|
|hitTarget|64×56|

**renderSpec.elements[]**
|id|label|type|bind|role|
|---|---|---|---|---|
|complete.copy|完成一次练习|text|—|completion|
|complete.record|记录到此设备|toggle|localRecord.preference|optional action|
|complete.recordState|已记录/未记录|text|writeState|status|
|complete.exit|退出|button|—|stable exit|

**dataBindings[]**
|source|target|fallback|type|
|---|---|---|---|
|localRecord.preference|record.selected|false|semantic|
|writeState|recordState.text|未记录|semantic|
|textScaling|layout/font reflow|1.0|semantic|

**variants**: unrecorded=toggle off/status未记录; recorded=check/status已记录; writeError=toggle off/status未记录; largeText=3 rows wrap.

**states**
|state|trigger|visual|size|motion|accessibility|
|---|---|---|---|---|---|
|default/empty|S7|unrecorded|1|none|optional label|
|focused|focus toggle|outline|1.03|120ms|outline|
|pressed|toggle|check|.98|80ms|check+label|
|exit-focused|focus complete.exit|2dp accent outline|1.03|120ms|outline+退出|
|exit-pressed|activate complete.exit|accent fill|.98|80ms|label|
|loading|write|正在记录|1|none|text|
|recorded|success|check+已记录|1|150ms|text|
|error|fail|未记录|1|none|not danger/flow continues|
|disabled|storage unavailable|muted+不可记录|1|none|can exit|
|overflow|text1.5|wrap|1|none|no truncate|
Stacking: error>loading>recorded>pressed>focused>default; exit focus follows record.

### C7 ConsentDialog — expanded

|Base field|Value|
|---|---|
|derivedFromTasks|T3,T5b,T5c|
|derivedFromData|pendingIntent,config.summary,textScaling|
|purpose|confirm Stage/restart/exit|
|layoutRole|critical_modal|
|priority|primary|
|runtimeRole|dialog|

**anatomy.layout**
```
┌C7┐
│┈title┈│
│┈summary/question┈│
│┈cancel┈ ┈confirm┈│
└──┘
```
Grid 3 rows, focus trap.

**sizing**
|Tier/host|W×H|Fits|
|---|---:|---|
|Regular/Beacon or Pause default|560×320|yes|
|Compact/min|520×320|yes|
|Constrained text1.5|520×376|yes scroll body|

**metrics**
|Metric|Value|
|---|---|
|background|glass Thickest; MR opaque fallback|
|radius|l32|
|padding|l24|
|gap|m16|
|stroke|2dp destructive on confirm|
|icon|m28 triangle for destructive only|
|primary text|title28/36/600|
|secondary|body18/28/500|
|hitTarget|≥64×56|

**renderSpec.elements[]**
|id|label|type|bind|role|
|---|---|---|---|---|
|dialog.title|开始/重新开始/退出|text|pendingIntent|heading|
|dialog.body|确认问题|text|resolvedCopy|explanation|
|dialog.cancel|取消|button|pendingIntent|safe action|
|dialog.confirm|确认|button|pendingIntent|critical action|

**dataBindings[]**
|source|target|fallback|type|
|---|---|---|---|
|pendingIntent|title/confirm/resolvedCopy selector|cancel/hide dialog|semantic|
|config.summary|resolvedCopy input|使用当前选择|display|
|textScaling|layout/body scroll|1.0|semantic|
|resolvedCopy|dialog.body.text|start=使用当前选择; restart=将从头开始; exit=将退出本次练习|display|

**variants**: startConfirm=accent/no triangle; restartConfirm=triangle+danger stroke; exitConfirm=triangle+danger stroke; largeText=body scroll/actions pinned.

**states**
|state|trigger|visual|size|motion|accessibility|
|---|---|---|---|---|---|
|default|open|cancel initial focus for destructive|1|200ms fade|focus trap|
|focused|focus|2dp outline|1.03|120ms|outline|
|pressed|activate|fill|.98|80ms|haptic optional|
|disabled|action unavailable|muted readable|1|none|reason text|
|loading|transition|confirm disabled|1|none|“正在处理”|
|empty/error|intent null|close safely|1|200ms|return host|
|overflow|text1.5|body scroll|1|none|actions fixed|
Stacking: loading>disabled>pressed>focused; modal above C1/C3/C5.

## 5B. Authoritative structure checklist

|Core|base|layout|sizing|metrics|render|bindings|variants|states|Verdict|
|---|---|---|---|---|---|---|---|---|---|
|C1|yes|yes|yes|yes|yes|yes|yes|yes|pass|
|C2|yes|yes|yes|yes|yes|yes|yes|yes|pass|
|C3|yes|yes|yes|yes|yes|yes|yes|yes|pass|
|C4|yes|yes|yes|yes|yes|yes|yes|yes|pass|
|C5|yes|yes|yes|yes|yes|yes|yes|yes|pass|
|C6|yes|yes|yes|yes|yes|yes|yes|yes|pass|
|C7|yes|yes|yes|yes|yes|yes|yes|yes|pass|

## 5C. Authoritative coverage reconciliation

### Table A data→binding
|Entity|Timeliness|Binding|Presentation|Disposition|
|---|---|---|---|---|
|SessionConfig.durationSec|frozen at start|C3 config.durationSec|2/4/6分钟|bound|
|SessionConfig.scene|frozen|C2 config.scene,C4 config.scene|human labels/geometry|bound|
|SessionConfig.pattern|frozen|C3 config.pattern|human labels|bound|
|Timeline phase/progress|per monotonic tick|C4 timeline.*|shape+label+geometry|bound|
|paused/snapshot|event/frozen|C4/C5|已暂停+remaining|bound|
|cycleIndex|cycle boundary|C4 dust|≤3/cycle cap24|bound|
|remainingMs|tick|C4 countdown|mm:ss|bound|
|reduceMotion|preference/live|C1 preview + C4 timeline visual + GlobalMotionPolicy (§7)|small range/no dust/all UI translation removed|bound|
|textScaling|preference/live|C1–C7 explicit bindings|reflow|bound|
|pendingIntent/lifecycle|event|C5/C7|dialog/frozen|bound|
|LocalRecord preference/writeState|on demand|C6|human label|bound|
|mic/sensor/network|never|none|not presented|intentionally prohibited|

### Table B decision→interaction
|Decision|Type|Component/render/behavior|Disposition|
|---|---|---|---|
|T1 acknowledge|actionable|C1 intro.ack→X01|bound|
|T2 scene|actionable|C2 scene.* select|bound|
|T2 duration/pattern/start|actionable|C3 options/start.action|bound|
|T3 consent|actionable|C7 dialog.confirm/cancel→X03/S1|bound|
|T4 phase recognition|read-only|C4 phaseLabel/ring|bound|
|T4 pause/exit|actionable controller B|C4→X04 then C5|bound|
|T5a resume|actionable|C5 pause.resume→X05|bound|
|T5b/c confirm|actionable|C5 request→C7 confirm/cancel|bound|
|T5d lifecycle|system|C4/C5 frozen; foreground remains S4|bound|
|T6 record/no-record|actionable|C6 complete.record; default no record|bound|
|T6 exit|actionable|C6 complete.exit→X12|bound|

### Table C primary substates (exact rows)
|Component/part|Substate|Primitive|Binding|
|---|---|---|---|
|C1 halo|preview|intro.halo|phasePreview|
|C1 halo|static/error|intro.halo|phasePreview fallback|
|C1 ack|focused/pressed|intro.ack|firstRun|
|C1 ack|disabled|intro.ack|firstRun=false or N/A rationale: normally enabled|
|C1 halo|loading/empty|intro.halo|phasePreview fallback|
|C1 layout|overflow|intro.copy/ack|textScaling|
|C2 option|loading|scene.sea/cloud/dune|sceneAssets|
|C2 option|fallback/error|scene.sea/cloud/dune|sceneAssets fallback|
|C2 option|focused/selected|scene.sea/cloud/dune|config.scene|
|C2 option|default/pressed|scene.sea/cloud/dune|config.scene|
|C2 option|disabled|scene.sea/cloud/dune|sceneAssets invalid|
|C2 layout|overflow|scene.title/items|textScaling|
|C3 options|focused/selected|duration.*/pattern.*|config.durationSec/pattern|
|C3 options|pressed|duration.*/pattern.*|config.durationSec/pattern|
|C3 start|invalid/disabled|start.action|config.valid|
|C3 start|loading|start.action|N/A local transition; visual uses config.valid|
|C3 options|error/default|duration.*/pattern.*|config fallback|
|C3 layout|overflow|all C3 ids|textScaling|
|C4 ring|inhale|halo.ring/phaseLabel|timeline.phase/progress|
|C4 ring|hold1/hold2|halo.ring/phaseLabel|timeline.phase|
|C4 ring|exhale|halo.ring/phaseLabel|timeline.phase/progress|
|C4 ring|paused/error|halo.ring/phaseLabel|timeline.paused|
|C4 ring|loading/empty|halo.ring/phaseLabel|timeline snapshot fallback|
|C4 dust|reduce/performance|halo.dust|reduceMotion/cycleIndex|
|C4 countdown|overflow|halo.countdown|remainingMs|
|C5 actions|focused/pressed|pause.resume/restart/exit|pendingIntent|
|C5 actions|default|pause.resume/restart/exit|snapshot/pendingIntent|
|C5 actions|disabled|pause.resume|snapshot invalid|
|C5 actions|loading|pause.resume|N/A local; no async action|
|C5 status|empty|pause.status|snapshot fallback|
|C5 status|error|pause.status|snapshot fallback|
|C5 layout|overflow|pause.*|textScaling|
|C6 record|unrecorded|complete.record/recordState|preference false|
|C6 record|default/focused/pressed|complete.record|preference|
|C6 record|loading|complete.recordState|writeState loading|
|C6 record|recorded|complete.record/recordState|writeState success|
|C6 record|error|complete.recordState|writeState error|
|C6 record|disabled|complete.record/complete.recordState|writeState unavailable|
|C6 exit|exit-focused|complete.exit|N/A local action|
|C6 exit|exit-pressed|complete.exit|N/A local action→X12|
|C6 layout|overflow|complete.*|textScaling|
|C7 dialog|start/restart/exit|dialog.title/body/confirm|pendingIntent|
|C7 action|focused/loading/error|dialog.confirm/cancel|pendingIntent|
|C7 action|pressed/disabled|dialog.confirm/cancel|pendingIntent|
|C7 dialog|empty|dialog.body|resolvedCopy fallback|
|C7 layout|overflow|dialog.body/actions|textScaling|

## 5.2 Deprecated compressed reconciliation (invalidated; use §5C)

Table A: SessionConfig→C2/C3 (frozen per session); TimelineSnapshot/phase/progress/remaining/cycleIndex→C4/C5 (per frame from monotonic source); LocalCompletionRecord→C6 (on demand, failure nonblocking); microphone/sensors intentionally not presented because prohibited.

Table B: T1 ack→C1 intro.ack; T2 scene→C2 items; T2 duration/pattern/start→C3 elements; T3 consent→C3 start + Dialog; T4 identify/pause→C4 label + controller B; T5a/b/c→C5 actions/dialog; T6 record→C6 toggle. No actionable output is read-only.

Table C: C1 halo {preview/static/error}; C2 item {loading/fallback/focused/selected/disabled}; C3 options {default/focused/selected/invalid/overflow}; C4 ring {inhale/hold1/exhale/hold2/paused/error/reduceMotion/performance}; C5 actions {focused/pressed/dialog/disabled/error}; C6 record {unrecorded/loading/recorded/error}. Each maps to the corresponding render id and bindings above.

## 6. Materials/depth

Shared foreground: Thick/Thickest glass, opacity .92/.96, contrast pass planned; Full Stage label opaque #101B2B .88. Near PausePanel z80 > window content z20 > environment0. Glass only WindowContainer. Vibrant light for monochrome Shared text; terminates at image preview, solid fallback. No TabBar issue applicable.

## 7. Data semantic contract

displayOnlyPaths: instructionCopy, remainingText, completionCopy. semanticEnumPaths: timeline.phase→colorSemantics inhale/hold/exhale/paused; writeState→human labels recorded/unrecorded/error. All data local; states fresh/paused/error only, no network/offline/freshness claim. Null fallback specified per binding. Local record source “此设备”; failure never blocks completion. Machine enum never shown.

**GlobalMotionPolicy (locatable implementation fact)**: `reduceMotion=true` removes all C1–C7 translation/micro-scale, preserves 2dp focus outline, changes C1/C4 halo amplitude to small range, disables C4 dust, and keeps the unified timeline duration unchanged. `false` uses each component state table. Fallback is `true`; data source is local accessibility preference. This global policy consumes the preference for C2/C3/C5/C6/C7, whose component-specific data has no otherwise meaningful motion variant.

## 8. PICO numeric

Window radius32dp; minimum body12dp (project18); targets56dp minimum; central core65°×40°, secondary85°×55°; Planar depth640dp.

## 9. Asset handoff

Procedural low-poly lighthouse≤2k triangles, scene≤10k, particles≤24; solid/vertex colors, no texture required, LOD50%. Audio 48kHz mono source loops positioned at lighthouse plus stereo ambience; zero-cross/crossfade clips. Sky/sea/dune procedural. SVG icons tintable 28dp. No generated medical imagery.

## 10. 最低完整性门（当前）

| Check | Evidence | Verdict |
|---|---|---|
| Visual direction | §2 三案、选定、两拒绝、结构化效果审查 | pass |
| Visual language | §3–§4 | pass |
| Window structure | §5.0 | pass |
| Component structure | C1–C7 §5A + §5B | pass |
| Coverage reconciliation | §5C | pass |
| Semantics/trust | §6–§8 | pass |

| minimumCompletenessGate | pass |
