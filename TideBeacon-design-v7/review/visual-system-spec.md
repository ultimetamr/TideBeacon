# TideBeacon 视觉系统

> artifactRevision: 1 | active interaction@1 | approved VD-A | V7 independent run

## 1. 视觉方向候选

| Direction | thesis/first view | container/depth | hierarchy/affordance | spatial value/risk | preview instruction |
|---|---|---|---|---|---|
| VD-A “静潮灯影” | 暗色程序化地平线，远灯塔+一环+原句 | Planar选择，Stage环境远→环→相位字 | 光环唯一首屏可聚焦物，细轮廓不增对象 | 方向/距离/尺度；dashboard风险低 | low-poly silhouettes, fixed camera, sparse stars |
| VD-B “星盘仪表” | 多层刻度、轨道和数字 | Full Stage 多深度层 | 多数据点可聚焦 | 深度明显；仪表化压过节奏 | reject: no dashboard rings |
| VD-C “近距雾幕” | 近眼大雾面光斑 | 单大 Planar/Stage plane | 整面是操作面 | 像放大 2D，遮挡风险高 | reject: no near full-screen veil |

Structured effect review selects VD-A: single focus pass; world distance+scale pass; no camera/flash pass; three-object first view pass; controller focus can be a state of the halo; device beauty/comfort remains unverified. VD-B and VD-C are rejected structurally, not by color preference. Competitor imagery is not reused.

## 2. Tokens / materials / window shells

- typography: display sans40/48/600; title sans28/36/600; metric mono24/32/500; body sans18/28/500; caption sans14/20/500; textScaling 1–1.5.
- colors: primary `#BFE9FF`; accent `#FFD89A`; surface `#101B2B`; text `#F4F8FF`; muted `#9DB1C8`; danger `#FFB4AB`.
- redundant semantics: inhale blue+circle+吸气; hold amber+square+停留; exhale cyan+diamond+呼气; paused white+dashed+已暂停; danger coral+triangle+需确认.
- scale: spacing 4/8/16/24/32; radius 12/20/32; icon 20/28/36; target min56dp.
- HarborWindow uses system glass Thick .92; modal uses Thickest .96; Web blur is approximation only. Stage labels use opaque `#101B2B` .88, never glass. Vibrant only for monochrome Shared-space text, never image/gradient; solid fallback guarantees 4.5:1 target.

HarborWindow 1280×800/min960×720/max1440×900, inset32, no docked attachment:
```
┌ HarborWindow ──────────┐
│ ┈C1 or C2+C3 or C6┈ │
│ ┈C7 modal when valid┈    │
└──────────────────┘
```
PauseWindow 720×480/min640×440/max840×560, inset32, no docked attachment:
```
┌ PauseWindow ─────────┐
│ ┈C5 resume/restart/exit┈ │
│ ┈C7 valid modal only┈     │
└───────────────────┘
```

## 3. C1 LightPrelude

### Base fields
| field | value |
|---|---|
| derivedFromTasks | T1 |
| derivedFromData | phasePreview,instructionCopy |
| purpose | teach the two-direction mapping |
| layoutRole | primary hero |
| priority | primary |
| runtimeRole | instruction-gate |
### Anatomy · Layout
```
    lighthouse
      (halo)
 exact sentence
```
Grid: 3 centered rows, gap16; halo is the sole focus target; exactly three visible objects.
### Anatomy · Sizing
| tier | size / content fit |
|---|---|
| Large/default | 720×560 within1216×736 |
| Compact/min | 640×520 within896×656 |
| Constrained | 560×500; copy wraps two lines |
### Anatomy · Internal Metrics
| metric | value |
|---|---|
| background/radius/padding | none / N/A /16 |
| gap/stroke |16 / focus2 |
| icon/text/hitTarget |36 / body18 / halo focus region≥96 |
### Render Elements renderSpec.elements[]
| id | label | type | bind | role |
|---|---|---|---|---|
| intro.lighthouse | 灯塔 | procedural model | none | visible anchor |
| intro.halo | none | ring+focus target | phasePreview | visible rhythm+advance target |
| intro.copy | 光扩张时吸气，光收回时呼气。 | text | instructionCopy | instruction |
### Data Bindings dataBindings[]
| source | target | fallback | type |
|---|---|---|---|
| phasePreview | intro.halo.scale | static midpoint | semantic |
| instructionCopy | intro.copy.text | exact required sentence | display |
### Variants variants
normal amplitude; reduceMotion small amplitude; largeText two-line copy. No extra control variant.
### States states
| state/trigger | visual/size/motion/accessibility | precedence |
|---|---|---|
| default/load/empty/error | 3 objects; static midpoint on data fault | fallback |
| focused/controller ray | same halo +2dp outline, no new object | focused |
| pressed/confirm | halo scale .98 for80ms then X01 | pressed>focused |
| disabled | halo readable, activation blocked | disabled>pressed |

## 4. C2 SceneDeck

### Base fields
|field|value|
|---|---|
|derivedFromTasks|T2|
|derivedFromData|SessionConfig.scene,sceneAssets|
|purpose|select low-poly environment|
|layoutRole|primary decision|
|priority|primary|
|runtimeRole|decision-list|
### Anatomy · Layout
```
选择场景
[sea] [cloud] [dune]
```
Grid title+3 columns; compact/constrained uses 3 rows; gap16.
### Anatomy · Sizing
|tier|size/fit|
|---|---|
|Large/default|1216×280 within Harbor default|
|Compact/min|896×280 within shared 576dp scroll viewport|
|Constrained|560×520 internal scroll|
### Anatomy · Internal Metrics
|metric|value|
|---|---|
|background/radius/padding|glass Thick/r32/p24|
|gap/stroke|16/2 selected3|
|icon/text/hitTarget|36/title28+body18/96×72|
### Render Elements renderSpec.elements[]
|id|label|type|bind|role|
|---|---|---|---|---|
|scene.title|选择场景|text|none|heading|
|scene.sea|海面|option|sceneAssets.sea|action|
|scene.cloud|云层|option|sceneAssets.cloud|action|
|scene.dune|沙丘|option|sceneAssets.dune|action|
### Data Bindings dataBindings[]
|source|target|fallback|type|
|---|---|---|---|
|SessionConfig.scene|option.selected|sea|semantic|
|sceneAssets.*|option.preview|procedural line swatch|display|
### Variants variants
sea wave-line; cloud layered arcs; dune triangular ridge; structural icon differs, layout fixed.
### States states
|state|visual/motion/accessibility|precedence|
|---|---|---|
|default/loading/empty/error|procedural swatches; readable labels|fallback|
|focused|2dp accent,120ms|max unless disabled|
|pressed|.98/80ms|pressed>focused|
|selected|3dp+check|selected+focused combine|
|disabled|muted+reason|disabled wins|

## 5. C3 SessionDial

### Base fields
|field|value|
|---|---|
|derivedFromTasks|T2,T3|
|derivedFromData|durationSec,pattern,label,configValid|
|purpose|select length/rhythm and request entry|
|layoutRole|supporting decision|
|priority|primary|
|runtimeRole|session-configurator|
### Anatomy · Layout
```
[2] [4] [6]
[舒缓][平衡][深长]
        [开始]
```
Grid 3 rows; start pinned bottom; compact rows wrap.
### Anatomy · Sizing
|tier|size/fit|
|---|---|
|Large/default|1216×350 within default|
|Compact/min|896×280 within shared 576dp scroll viewport; start pinned overlay|
|Constrained|560×560 scroll with pinned start|
### Anatomy · Internal Metrics
|metric|value|
|---|---|
|background/radius/padding|none/r20/p24|
|gap/stroke|16/2 selected3|
|icon/text/hitTarget|28/title28 body18/64×56|
### Render Elements renderSpec.elements[]
|id|label|type|bind|role|
|---|---|---|---|---|
|session.duration|2/4/6 分钟|segmented|durationSec|action|
|session.pattern|舒缓/平衡/深长|segmented|pattern.label|action|
|session.start|开始|button|configValid|critical action|
### Data Bindings dataBindings[]
|source|target|fallback|type|
|---|---|---|---|
|durationSec|session.duration.selected|120|semantic|
|pattern.label|session.pattern.text|平衡|semantic|
|configValid|session.start.enabled|false|semantic|
### Variants variants
duration 120/240/360; patterns 4-1-4-1/4-1-6-1/5-1-7-1; differences change actual phase durations.
### States states
|state|visual/motion/accessibility|precedence|
|---|---|---|
|default/loading/empty/error|reset defaults; text 使用默认; start disabled until valid|fallback|
|focused|outline120ms|focused|
|pressed|.98/80ms|pressed>focused|
|selected|check+text|selected combine|
|disabled|start disabled+reason|disabled wins|

## 6. C4 RhythmHalo

### Base fields
|field|value|
|---|---|
|derivedFromTasks|T4,T5a,T5d|
|derivedFromData|TimelineSnapshot,SessionConfig.scene,reduceMotion|
|purpose|render phase, countdown, cycle event and audio-coupled scale|
|layoutRole|Stage primary subject|
|priority|primary|
|runtimeRole|rhythm-visualizer|
### Anatomy · Layout
```
world lighthouse @ z-8m
   halo ring 1.2..2.5m
 phase label + countdown child @ z-3m
 cycle dust behind ring
```
World anchors face user; central ±20°; labels are C4 children, not independent panels.
### Anatomy · Sizing
|tier|size/fit|
|---|---|
|Regular Stage|diameter1.2–2.5m at -8m; central FOV|
|ReduceMotion|1.6–1.9m; same timing|
|Performance|32-segment outline; no dust|
### Anatomy · Internal Metrics
|metric|value|
|---|---|
|background/radius/padding|Stage opaque label/N/A/16dp label|
|gap/stroke|16dp/halo 0.03m|
|icon/text/hitTarget|none/title28+metric24/N/A passive|
### Render Elements renderSpec.elements[]
|id|label|type|bind|role|
|---|---|---|---|---|
|halo.ring|none|world ring|phase+progress|rhythm|
|halo.phase|吸气/停留/呼气|text+shape|phase|semantic|
|halo.countdown|mm:ss|text|remaining|status|
|halo.dust|none|particles|cycleIndex|decoration|
### Data Bindings dataBindings[]
|source|target|fallback|type|
|---|---|---|---|
|phase/progress|ring scale+label|节奏暂不可用; static1.6m|semantic|
|paused|motion freeze|冻结 true|semantic|
|remaining|countdown|--:--|display|
|cycleIndex|dust count|0|semantic|
### Variants variants
sea/cloud/dune change only procedural background; reduceMotion narrows amplitude/removes dust; performance removes dust and uses simpler ring.
### States states
|state/trigger|visual/size/motion/accessibility|precedence|
|---|---|---|
|inhale/hold1/exhale/hold2|circle/square/diamond/square labels; exact pattern|timeline|
|paused|dashed+已暂停; all frozen|paused|
|loading/empty/error|danger dashed+节奏暂不可用; freeze; exit remains|error>paused|
|reduceMotion/performance|small range/no dust; same elapsed|policy wins decoration only|

## 7. C5 PauseDock

### Base fields
|field|value|
|---|---|
|derivedFromTasks|T5a,T5b,T5c,T5d|
|derivedFromData|TimelineSnapshot,PendingIntent|
|purpose|resume/restart/exit from frozen snapshot|
|layoutRole|interrupt control|
|priority|primary|
|runtimeRole|pause-controller|
### Anatomy · Layout
```
已暂停  remaining
[继续]
[重新开始] [退出]
```
Grid status+primary+secondary; modal C7 overlays.
### Anatomy · Sizing
|tier|size/fit|
|---|---|
|Large/default|656×416 content exact|
|Compact/min|576×376 content exact|
|Constrained|520×360 scroll secondary, resume pinned|
### Anatomy · Internal Metrics
|metric|value|
|---|---|
|background/radius/padding|glass Thick/r32/p32|
|gap/stroke|16/2|
|icon/text/hitTarget|28/title28 body18/64×56|
### Render Elements renderSpec.elements[]
|id|label|type|bind|role|
|---|---|---|---|---|
|pause.status|已暂停|text|snapshot|status|
|pause.resume|继续|button|snapshot|primary action|
|pause.restart|重新开始|button|pendingIntent|destructive request|
|pause.exit|退出|button|pendingIntent|destructive request|
### Data Bindings dataBindings[]
|source|target|fallback|type|
|---|---|---|---|
|snapshot.remaining|status.secondary|时间已冻结|display|
|snapshot.valid|resume.enabled|false; exit stays enabled|semantic|
|pendingIntent|request state|null|semantic|
### Variants variants
userPaused/systemPaused labels differ; error keeps safe exit; compact stacks actions.
### States states
|state|visual/motion/accessibility|precedence|
|---|---|---|
|default/focused/pressed|outline120ms; .98/80ms|pressed>focused|
|disabled|resume disabled; exit enabled|disabled|
|loading/empty/error|remain frozen; status fallback; safe exit visible|error|
|modal stacking|C7 traps focus and covers actions|modal wins|

## 8. C6 FinishNote

### Base fields
|field|value|
|---|---|
|derivedFromTasks|T6|
|derivedFromData|LocalRecord,writeState|
|purpose|show completion and optional local record|
|layoutRole|completion focus|
|priority|primary|
|runtimeRole|completion-marker|
### Anatomy · Layout
```
完成一次练习
[保存本地记录]
[退出]
```
Grid 3 rows; no score/stat/streak.
### Anatomy · Sizing
|tier|size/fit|
|---|---|
|Large/default|720×440 within default|
|Compact/min|640×420 within min|
|Constrained|560×400 scroll, exit pinned|
### Anatomy · Internal Metrics
|metric|value|
|---|---|
|background/radius/padding|none/r20/p24|
|gap/stroke|16/2|
|icon/text/hitTarget|28/display40+body18/64×56|
### Render Elements renderSpec.elements[]
|id|label|type|bind|role|
|---|---|---|---|---|
|finish.copy|完成一次练习|text|none|heading|
|finish.record|保存本地记录|toggle|writeState|optional action|
|finish.status|已记录/未记录|text|writeState|status|
|finish.exit|退出|button|none|safe action|
### Data Bindings dataBindings[]
|source|target|fallback|type|
|---|---|---|---|
|writeState|record/status|unrecorded+未记录|semantic|
|LocalRecord|local store|do not write|semantic|
### Variants variants
unrecorded/recording/recorded/writeError; no content variant.
### States states
|state|visual/motion/accessibility|precedence|
|---|---|---|
|default/focused/pressed|outline; .98/80ms|pressed>focused|
|recording|toggle disabled+保存中|loading|
|recorded|已记录+check|semantic|
|empty/error|未记录; exit remains|error|

## 9. C7 SafetyDialog

### Base fields
|field|value|
|---|---|
|derivedFromTasks|T3,T5b,T5c|
|derivedFromData|PendingIntent,SessionConfig|
|purpose|confirm enter/restart/exit only when intent is valid|
|layoutRole|modal overlay|
|priority|critical|
|runtimeRole|safety-confirmation|
### Anatomy · Layout
```
[title]
[resolved body]
[取消] [确认]
```
Grid title/body/actions; anchored to host center; focus trap.
### Anatomy · Sizing
|tier|size/fit|
|---|---|
|Large/default|560×360 within both windows|
|Compact/min|520×340 within both min areas|
|Constrained|480×320 body scroll, actions pinned|
### Anatomy · Internal Metrics
|metric|value|
|---|---|
|background/radius/padding|glass Thickest/r32/p32|
|gap/stroke|16/2 danger for destructive|
|icon/text/hitTarget|28/title28 body18/64×56|
### Render Elements renderSpec.elements[]
|id|label|type|bind|role|
|---|---|---|---|---|
|dialog.title|开始/重新开始/退出|text|PendingIntent|heading|
|dialog.body|resolved Chinese copy|text|PendingIntent+config|explanation|
|dialog.cancel|取消|button|PendingIntent|safe action|
|dialog.confirm|确认|button|PendingIntent|critical action|
### Data Bindings dataBindings[]
|source|target|fallback|type|
|---|---|---|---|
|PendingIntent valid enum|title/body/actions|null/error => do not render; return host|semantic|
|SessionConfig summary|body|use defaults only for enter|display|
### Variants variants
enter neutral; restart triangle warning; exit triangle warning. Each has distinct resolved copy.
### States states
|state|visual/motion/accessibility|precedence|
|---|---|---|
|valid default/focused/pressed|visible; focus trap; .98/80ms|modal>pressed>focused|
|loading|visible only if intent remains valid; confirm disabled|disabled|
|null/empty/error|**not rendered**; clear intent; safe return S1/S4|non-rendered guard|
|overflow|body scroll; actions pinned|modal|

## 10. Coverage reconciliation

### Table A · Data → binding
|Data/decision|timeliness|component.binding|presentation/disposition|
|---|---|---|---|
|durationSec/scene/pattern/label|session frozen|C2/C3|visible choices/defaults|
|elapsed/phase/progress/remaining/cycleIndex/paused|per render pure-derived|C4/C5|phase/countdown/freeze/dust|
|reduceMotion|local preference|C1/C4|small amplitude/no dust|
|LocalRecord/writeState|on demand|C6|only-device status; failure nonblocking|
|PendingIntent valid|null/error transient|C5/C7|valid renders C7; null/error intentionally non-rendered safe return|
|microphone/sensors/network/AI|prohibited|none|intentionally not presented|

### Table B · Task decision → interaction
|Task/output|kind|component/action|disposition|
|---|---|---|---|
|T1 mapping confirmed|actionable|C1 intro.halo confirm→X01|no fourth visible control|
|T2 config|actionable|C2 options+C3 segments|bound|
|T3 enter/cancel|actionable|C3 start+C7 valid actions|bound|
|T4 phase/continue|read+action|C4 phase; controller B→pause|bound|
|T5a pause/resume|actionable|C5 resume/X04/05|bound|
|T5b restart/cancel|actionable|C5+C7/X06/07|bound|
|T5c exit/cancel|actionable|C5+C7/X08/09|bound; never complete|
|T5d lifecycle|system|C4/C5 freeze/X04|bound|
|T6 record/exit|actionable|C6 toggle/exit|bound|

### Table C · Primary substates
|component→subcomponent|runtime substates|primitive|binding|
|---|---|---|---|
|C1→halo|default/focused/pressed/disabled/loading/empty/error|intro.halo|phasePreview|
|C2→item|default/focused/pressed/selected/disabled/loading/empty/error|scene.*|scene/assets|
|C3→duration+pattern+start|default/focused/pressed/selected/disabled/loading/empty/error|session.*|config/configValid|
|C4→ring+label+countdown+dust|inhale/hold1/exhale/hold2/paused/loading/empty/error/reduceMotion/performance|halo.*|snapshot/policy|
|C5→actions+status|default/focused/pressed/disabled/loading/empty/error/modal|pause.*|snapshot/intent|
|C6→record+status+exit|unrecorded/focused/pressed/recording/recorded/empty/error|finish.*|writeState|
|C7→visible dialog|valid-enter/valid-restart/valid-exit/focused/pressed/disabled/overflow|dialog.*|valid PendingIntent only|
|C7→guard|null/empty/error|no rendering; safe host return|PendingIntent invalid|

Structure integrity: C1–C7 each have Base/Layout/Sizing/Metrics/Render/Bindings/Variants/States = 56/56 pass. TableA=6 rows; TableB=9; TableC=8. First view render elements=3 exactly. C7 invalid states have no visible primitive.

## 10A. Authoritative expanded metrics and states (P-03)

The compact metric/state rows in §3–§9 are superseded only for the two sections below. Base/Layout/Sizing/Render/Bindings/Variants remain authoritative above. Each component uses these independent rows.

### C1 metrics
|metric|value|
|---|---|
|background|none|
|radius|N/A; no panel|
|padding|16dp scale|
|gap|16dp scale|
|stroke|2dp focus|
|icon|36dp lighthouse detail|
|text|body18/28/500|
|hitTarget|halo focus region 96×96dp|
### C1 states
|state|trigger|visual|size|motion|accessibility|precedence|
|---|---|---|---|---|---|---|
|default|enter S0|three objects|regular|preview phase|exact copy|base|
|focused|controller ray on halo|same halo +2dp outline|unchanged|120ms|non-color outline|focused|
|pressed|confirm|same halo|.98|80ms|controller A/trigger|pressed>focused|
|disabled|input unavailable|same 3 objects+muted outline|unchanged|none|Back still exits|disabled>pressed|
|loading|preview pending|static halo midpoint|1.0|none|copy remains|fallback|
|empty|preview absent|static halo midpoint|1.0|none|copy remains|fallback|
|error|preview fault|static halo midpoint|1.0|none|copy remains, Back exits|error|

### C2 metrics
|metric|value|
|---|---|
|background|system glass Thick|
|radius|32dp scale|
|padding|24dp scale|
|gap|16dp scale|
|stroke|2dp; selected3dp|
|icon|36dp scale|
|text|title28/body18|
|hitTarget|96×72dp|
### C2 states
|state|trigger|visual|size|motion|accessibility|precedence|
|---|---|---|---|---|---|---|
|default|S1 ready|3 swatches|tier sizing|none|labels+shapes|base|
|focused|ray/stick|2dp accent|unchanged|120ms|outline|focused|
|pressed|confirm|accent|.98|80ms|haptic downstream|pressed>focused|
|selected|config.scene match|3dp+check|unchanged|none|check+label|selected combine|
|disabled|policy|muted+reason|unchanged|none|readable reason|disabled wins|
|loading|assets pending|procedural swatches|unchanged|none|labels remain|fallback|
|empty|assets empty|procedural swatches|unchanged|none|labels remain|fallback|
|error|asset fault|procedural swatches|unchanged|none|selection works|error fallback|

### C3 metrics
|metric|value|
|---|---|
|background|none|
|radius|20dp scale|
|padding|24dp scale|
|gap|16dp scale|
|stroke|2dp; selected3dp|
|icon|28dp scale|
|text|title28/body18|
|hitTarget|64×56dp minimum|
### C3 states
|state|trigger|visual|size|motion|accessibility|precedence|
|---|---|---|---|---|---|---|
|default|valid config|options+start|tier sizing|none|labels|base|
|focused|ray/stick|outline|unchanged|120ms|non-color|focused|
|pressed|confirm|accent|.98|80ms|controller|pressed>focused|
|selected|value match|check+text|unchanged|none|dual encoded|selected|
|disabled|config invalid|start muted+reason|unchanged|none|focus skips|disabled wins|
|loading|config pending|defaults+使用默认|unchanged|none|start disabled|fallback|
|empty|null config|reset defaults+使用默认|unchanged|none|start revalidates|fallback|
|error|invalid config|reset defaults+使用默认|unchanged|none|start only after valid|error|

### C4 metrics
|metric|value|
|---|---|
|background|opaque Stage label only|
|radius|N/A world ring|
|padding|16dp label|
|gap|16dp label-to-countdown|
|stroke|0.03m ring|
|icon|N/A|
|text|title28/metric24|
|hitTarget|N/A passive subject|
### C4 states
|state|trigger|visual|size|motion|accessibility|precedence|
|---|---|---|---|---|---|---|
|inhale|phase=inhale|吸气+circle|1.2→2.5m|pattern exact|shape+text|timeline|
|hold1|phase=hold1|停留+square|locked2.5m|none|shape+text|timeline|
|exhale|phase=exhale|呼气+diamond|2.5→1.2m|pattern exact|shape+text|timeline|
|hold2|phase=hold2|停留+square|locked1.2m|none|shape+text|timeline|
|paused|paused=true|已暂停+dashed|snapshot size|frozen|text+shape|paused|
|loading|snapshot pending|节奏暂不可用|1.6m|none|exit available|fallback|
|empty|snapshot absent|节奏暂不可用|1.6m|none|exit available|fallback|
|error|clock fault|danger dashed+节奏暂不可用|1.6m|none|exit available|error wins|
|reduceMotion|preference=true|same labels|1.6–1.9m|same elapsed,no dust|RM|policy decoration|
|performance|budget low|32 segment ring|same phase size|no dust|same labels|policy decoration|

### C5 metrics
|metric|value|
|---|---|
|background|system glass Thick|
|radius|32dp|
|padding|32dp|
|gap|16dp|
|stroke|2dp|
|icon|28dp|
|text|title28/body18|
|hitTarget|64×56dp|
### C5 states
|state|trigger|visual|size|motion|accessibility|precedence|
|---|---|---|---|---|---|---|
|default|S4|已暂停+3 actions|tier|none|focus order|base|
|focused|ray/stick|outline|unchanged|120ms|non-color|focused|
|pressed|confirm|accent|.98|80ms|controller|pressed>focused|
|disabled|snapshot invalid|resume disabled; exit active|unchanged|none|reason|disabled|
|loading|snapshot pending|frozen fallback|unchanged|none|exit active|fallback|
|empty|snapshot absent|frozen fallback|unchanged|none|exit active|fallback|
|error|snapshot fault|error status|unchanged|none|exit active|error|
|modal|valid intent|background subdued|unchanged|none|focus trapped C7|modal wins|

### C6 metrics
|metric|value|
|---|---|
|background|none|
|radius|20dp|
|padding|24dp|
|gap|16dp|
|stroke|2dp|
|icon|28dp|
|text|display40/body18|
|hitTarget|64×56dp|
### C6 states
|state|trigger|visual|size|motion|accessibility|precedence|
|---|---|---|---|---|---|---|
|unrecorded|enter S7|未记录|tier|none|optional label|base|
|focused|ray/stick|outline|unchanged|120ms|non-color|focused|
|pressed|confirm|accent|.98|80ms|controller|pressed>focused|
|recording|write pending|保存中|unchanged|none|toggle disabled|loading|
|recorded|write success|已记录+check|unchanged|none|dual encoded|semantic|
|empty|no record|未记录|unchanged|none|exit active|fallback|
|error|write fail|未记录|unchanged|none|exit active|error|

### C7 metrics
|metric|value|
|---|---|
|background|system glass Thickest|
|radius|32dp|
|padding|32dp|
|gap|16dp|
|stroke|2dp danger on destructive|
|icon|28dp|
|text|title28/body18|
|hitTarget|64×56dp|
### C7 states
|state|trigger|visual|size|motion|accessibility|precedence|
|---|---|---|---|---|---|---|
|enter|intent=enter|neutral resolved copy|tier|fade200ms|focus trap|modal|
|restart|intent=restart|triangle warning|tier|fade200ms|focus trap|modal|
|exit|intent=exit|triangle warning|tier|fade200ms|focus trap|modal|
|focused|ray/stick|outline|unchanged|120ms|non-color|focused|
|pressed|confirm|accent|.98|80ms|controller|pressed>focused|
|disabled|valid intent loading|confirm disabled|unchanged|none|cancel active|disabled|
|overflow|textScaling|body scroll/actions pinned|within tier|none|read order|modal|
|null|intent absent|not rendered|0×0|none|safe host return|guard wins|
|empty|intent payload empty|not rendered|0×0|none|clear and safe host return|guard wins|
|error|intent resolution fault|not rendered|0×0|none|clear and safe host return|guard wins|

### Compact allocation correction

At HarborWindow min content height656dp, Chooser uses one scroll viewport `C2+C3 = 576dp` plus 16dp inter-section gap and a 64dp pinned start region that overlays the viewport bottom rather than adding document height; scroll bottom-padding64 prevents occlusion. C2 compact content280dp and C3 compact content280dp are inside the 576dp viewport; all controls remain ≥56dp. Thus occupied content height is `576 + 16 + 64 overlay-not-additive = 592dp logical flow`, within656dp.

## 11. Motion/audio/data trust

All consumers use `elapsed=max(0,now-start-pausedAccum)`. Inhale expands; holds lock; exhale contracts. `cycleCompleted` emits ordered events only at complete boundaries; ≤3 dust particles/event, cap24, 800ms fade; Reduce Motion=0. Camera transform constant. Resume gain is monotonic 0→target over400ms for normal and performance.

Formatting: remaining `mm:ss`, null `--:--`; phase machine enums map to Chinese labels/shapes; record source text “仅此设备”. Timeline error never says “已暂停”: it says “节奏暂不可用”, freezes, and preserves exit. No network freshness semantics.
