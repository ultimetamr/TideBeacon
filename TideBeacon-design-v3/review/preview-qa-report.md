# TideBeacon Preview / QA Report

> artifactRevision: 9｜scope=`web_design_validation_only`｜source interaction@17, visual@10, coherence=`566059c6-2744-4019-b10c-88f0665b85c3` pass｜preview.html@9
> status: `pass` v3 Stage 14 `766fc5b7-3622-4d69-80c0-67501162ebf6`

## 1 Scope and test boundary

本报告验证自包含 Web 预览的设计结构、状态机、绑定、变体和响应式事实；不把 Web 结果冒充 PICO 设备、空间音频波形或性能实测。产品边界保持：非医疗/诊断产品；不检测呼吸、不接麦克风、不评分、不使用疗效语言。

## 2 Independent invocation history

|Run|reviewerRole|invocationId|contextPolicy|reviewedRevision|evidenceRebuilt|Verdict|
|---|---|---|---|---|---|---|
|R1|prototype_qa_reviewer|`PQA-TIDEBEACON-I11-V6-C9-PR1-P1-20260812-R1`|isolated_subagent|interaction@11+visual@6+critique@9+report@1+preview@1|yes|block|
|R2|prototype_qa_reviewer|`PQA-TIDEBEACON-I11-V6-C9-PR2-P2-20260812-R2`|isolated_subagent|interaction@11+visual@6+critique@9+report@2+preview@2|yes|block|
|R3|prototype_qa_reviewer|`PQA-TIDEBEACON-I11-V6-C9-PR3-P3-20260812-R3`|isolated_subagent|interaction@11+visual@6+critique@9+report@3+preview@3|yes|block|
|R4|prototype_qa_reviewer|`PQA-TIDEBEACON-R4-20260812-7F31`|isolated_subagent|interaction@11+visual@6+critique@9+report@4+preview@4|yes|block|
|R5|prototype_qa_reviewer|`PQA-TIDEBEACON-I11-V6-C9-PR5-P5-20260812-R5-8C42`|isolated_subagent|interaction@11+visual@6+critique@9+report@5+preview@5|yes|block|
|R6|prototype_qa_reviewer|`PQA-TIDEBEACON-I11-V6-C9-PR6-P6-20260812-R6-4D97`|isolated_subagent|interaction@11+visual@6+critique@9+report@6+preview@6|yes|pass|

R1–R5 verdicts are historical and retained。PQA-CR05 addresses R5’s empty-state and exact-height residuals；no historical pass is claimed.

## 3 Coverage Manifest

|Category|Declared|Implementation target|Reconciled|
|---|---:|---:|---|
|Domain states|8|8|yes|
|Transitions|12|12|yes|
|Render elements|34|34|yes|
|Bindings|30|30|yes|
|Variants|27|27|yes|
|Component states|63|63|yes|
|Stacking rules|7|7|yes|
|Responsive/motion cases|4|4|yes|

Component-state denominator: C1 8 + C2 9 + C3 9 + C4 10 + C5 8 + C6 11（含 empty、exit-focused、exit-pressed）+ C7 8 = 63. Stacking denominator is one independently triggerable rule for each C1–C7 = 7.

## 4 Five itemwise verification maps

### 4.1 State / transition map (20/20)

|Fact|Selector|Trigger|Expected|Actual|Verdict|
|---|---|---|---|---|---|
|S0|`#app[data-state=S0]`|首次|C1 灯塔、光环、单句|已渲染 C1 与精确句|pass|
|S1|`#app[data-state=S1]`|X01|C2+C3|已渲染场景/时长/节奏|pass|
|S2|`#app[data-state=S2]`|X02|start C7|已渲染开始确认|pass|
|S3|`#app[data-state=S3]`|X03|C4 running|单调时钟驱动 C4|pass|
|S4|`#app[data-state=S4]`|X04/onPause|冻结 C4+C5|pausedAt 快照且不再 tick|pass|
|S5|`#app[data-state=S5]`|X06/Back|exit C7|退出确认可见|pass|
|S6|`#app[data-state=S6]`|X08|restart C7|重开确认可见|pass|
|S7|`#app[data-state=S7]`|X10 only|C6|完成文案与可选记录|pass|
|X01|`[data-action=X01]`|手柄确认/点击|S0→S1|transition 分支存在|pass|
|X02|`[data-action=X02]`|开始|S1→S2|pending=start|pass|
|X03|`[data-dialog=confirm]`|确认开始|S2→S3；elapsed=0|startMono=performance.now|pass|
|X04|`[data-action=X04],[data-system-pause]`|暂停/onPause|S3→S4；同帧冻结|pausedAt 已保存|pass|
|X05|`[data-action=X05]`|继续|S4→S3；无跳时；normal/performance 400ms 淡入|pausedAccum 重基准；audioState 精确标识 400ms|pass|
|X06|`[data-action=X06],[data-system-back]`|退出/Back|S4→S5|pending=exit|pass|
|X07|`[data-dialog=confirm]`|确认退出|S5→S1|丢弃练习并回选择，不显示完成|pass|
|X08|`[data-action=X08]`|重新开始|S4→S6|pending=restart|pass|
|X09|`[data-dialog=confirm]`|确认重开|S6→S3；elapsed=0|时间轴重置|pass|
|X10|`#app[data-state=S3]`|elapsed≥duration|S3→S7|tick 中钳位后完成|pass|
|X11|`[data-action=X11]`|记录切换|本地偏好状态变化|record 与文案同步|pass|
|X12|`[data-action=X12]`|完成页退出|closed|`[data-state=closed]` 横幅|pass|

### 4.2 Render-element map (34/34)

|Fact|Selector|Trigger|Expected|Actual|Verdict|
|---|---|---|---|---|---|
|intro.lighthouse|`[data-preview-id="intro.lighthouse"]`|S0|远处灯塔|程序化灯塔|pass|
|intro.halo|`[data-preview-id="intro.halo"]`|S0|光环|可缩放光环|pass|
|intro.copy|`[data-preview-id="intro.copy"]`|S0|精确引导句|精确中文/错误安全文案|pass|
|intro.ack|`[data-preview-id="intro.ack"]`|S0|继续|可操作按钮|pass|
|scene.title|`[data-preview-id="scene.title"]`|S1|选择场景|标题|pass|
|scene.sea|`[data-preview-id="scene.sea"]`|S1|海面|海面卡|pass|
|scene.cloud|`[data-preview-id="scene.cloud"]`|S1|云层|云层卡|pass|
|scene.dune|`[data-preview-id="scene.dune"]`|S1|沙丘|沙丘卡|pass|
|duration.label|`[data-preview-id="duration.label"]`|S1|时长|标题|pass|
|pattern.label|`[data-preview-id="pattern.label"]`|S1|节奏|标题|pass|
|duration.2|`[data-preview-id="duration.2"]`|S1|2 分钟|120 秒选项|pass|
|duration.4|`[data-preview-id="duration.4"]`|S1|4 分钟|240 秒选项|pass|
|duration.6|`[data-preview-id="duration.6"]`|S1|6 分钟|360 秒选项|pass|
|pattern.calm|`[data-preview-id="pattern.calm"]`|S1|舒缓|pattern=calm|pass|
|pattern.even|`[data-preview-id="pattern.even"]`|S1|均衡|pattern=even|pass|
|pattern.long|`[data-preview-id="pattern.long"]`|S1|延长呼气|pattern=long|pass|
|start.action|`[data-preview-id="start.action"]`|S1|开始|有效时启用，回退/错误禁用|pass|
|halo.ring|`[data-preview-id="halo.ring"]`|S3|时间轴缩放|`--halo` 随 progress|pass|
|halo.phaseLabel|`[data-preview-id="halo.phaseLabel"]`|S3|相位形状+文字|吸气/停留/呼气/暂停|pass|
|halo.lighthouse|`[data-preview-id="halo.lighthouse"]`|S3|空间锚点|场景内灯塔|pass|
|halo.dust|`[data-preview-id="halo.dust"]`|cycle boundary|弱粒子|仅完整循环后递增；Reduce/Performance 隐藏|pass|
|halo.countdown|`[data-preview-id="halo.countdown"]`|S3/S4|倒计时|mm:ss 或安全 `--:--`|pass|
|pause.status|`[data-preview-id="pause.status"]`|S4|已暂停|冻结状态|pass|
|pause.resume|`[data-preview-id="pause.resume"]`|S4|继续|X05|pass|
|pause.restart|`[data-preview-id="pause.restart"]`|S4|重新开始|X08|pass|
|pause.exit|`[data-preview-id="pause.exit"]`|S4|退出|X06|pass|
|complete.copy|`[data-preview-id="complete.copy"]`|S7|完成一次练习|精确完成文案|pass|
|complete.record|`[data-preview-id="complete.record"]`|S7|记录到此设备|可选且仅本地|pass|
|complete.recordState|`[data-preview-id="complete.recordState"]`|S7|写入状态|已记录/未记录/失败|pass|
|complete.exit|`[data-preview-id="complete.exit"]`|S7|退出|X12|pass|
|dialog.title|`[data-preview-id="dialog.title"]`|S2/S5/S6|确认标题|破坏性变体带三角语义|pass|
|dialog.body|`[data-preview-id="dialog.body"]`|S2/S5/S6|解析文案|正常/回退/错误可见|pass|
|dialog.cancel|`[data-preview-id="dialog.cancel"]`|dialog|取消|返回来源状态|pass|
|dialog.confirm|`[data-preview-id="dialog.confirm"]`|dialog|确认|错误模式禁用|pass|

### 4.3 Binding map (30/30)

“Actual” below names the target mutation, not an audit-label. The lower-left contract viewer only reports which real selector is currently rendered; it is not counted as implementation evidence.

|Fact|Selector|Trigger|Expected|Actual|Verdict|
|---|---|---|---|---|---|
|C1 phasePreview|`[data-binding=phasePreview]`|S0+Reduce|正常 28% / Reduce 6%|`data-motion-amplitude` 与 `--halo` 实际变化|pass|
|C1 instructionCopy|`[data-binding=instructionCopy]`|dataMode|精确句/安全错误句|mode() 改写可见正文|pass|
|C1 firstRun|`[data-binding=firstRun]`|S0|继续始终可用|按钮实际存在|pass|
|C1 textScaling|`#app[data-text-scaling]`|dataMode/Constrained|100/125/150%|body class 与 app dataset 改变字号并重排|pass|
|C1 reduceMotion|`#app[data-reduce-motion]`|#reduce|true 时小幅且无焦点缩放|amplitude=.06；CSS 取消 focused transform|pass|
|C2 config.scene|`[data-binding=config.scene]`|场景卡/dataMode|选中 sea/cloud/dune；非正常回退 sea|selected 与 state.scene 同步；dataMode handler 重置 sea|pass|
|C2 sceneAssets|`[data-binding=sceneAssets]`|dataMode|程序化/安全占位/海面占位|色块与 assetStatus 可见变化|pass|
|C2 textScaling|`#app[data-text-scaling]`|dataMode/Constrained|重排卡片|字号与 grid 实际变化|pass|
|C3 durationSec|`[data-preview-id^=duration]`|时长按钮/dataMode|120/240/360；非正常回退120|state.duration 更新；dataMode handler 重置120|pass|
|C3 pattern|`[data-preview-id^=pattern]`|节奏按钮/dataMode|calm/even/long；非正常回退calm|state.pattern 更新；phaseSets/cycleMs 消费；dataMode 重置 calm|pass|
|C3 config.valid|`[data-binding=config.valid]`|dataMode|正常启用；回退/错误禁用|disabled 与文案实际变化|pass|
|C3 textScaling|`[data-component=C3]`|dataMode|100/125/150%|inline style 与 body class 实际变化|pass|
|C4 progress|`[data-binding=timeline.progress]`|requestAnimationFrame|环缩放|单调 elapsed 计算 `--halo`|pass|
|C4 phase|`[data-binding=timeline.phase]`|elapsed/compState|四相位/暂停|形状 class 与中文标签实际变化|pass|
|C4 paused|`[data-binding=timeline.paused]`|X04/onPause|视觉、倒计时、音频状态冻结|paused 阻止 tick，audioState=frozen|pass|
|C4 cycleIndex|`[data-binding=timeline.cycleIndex]`|跨当前 pattern cycleMs|每完整循环 +3 弱点|calm/even/long 分别由 12000/10000/13000ms 周期计算 cycleIndex|pass|
|C4 remainingMs|`[data-binding=remainingMs]`|tick/dataMode|mm:ss / `--:--`|可见文本实际变化|pass|
|C4 reduceMotion|`[data-preview-id=halo.ring]`|#reduce|6% 小幅、无粒子|scale 公式与 dust 条件实际变化|pass|
|C4 textScaling|`#app[data-text-scaling]`|Constrained|150% 且重排|app dataset/body font-size|pass|
|C4 config.scene|`[data-component=C4]`|scene|背景 sea/cloud/dune|section class 实际变化|pass|
|C5 snapshot.remaining|`[data-binding=timeline.snapshot.remaining]`|X04|显示已暂停并保持|pausedAt 后 elapsed 不更新|pass|
|C5 pendingIntent|`[data-binding=pendingIntent]`|X06/X08|exit/restart|对应 C7 变体|pass|
|C5 textScaling|`#app[data-text-scaling]`|Constrained|150%/滚动|body 规则与 modal 宽度|pass|
|C6 preference|`[data-binding=localRecord.preference]`|X11/dataMode|切换本地记录；非正常回退 false|checkbox/state.record 同步；dataMode 重置 false 并禁用|pass|
|C6 writeState|`[data-binding=writeState]`|X11/dataMode|已记录/未记录/不可用/失败|可见状态文案实际变化|pass|
|C6 textScaling|`#app[data-text-scaling]`|Constrained|150%|实际继承字号|pass|
|C7 pendingIntent|`[data-binding=pendingIntent]`|X02/X06/X08；dataMode fallback/error|start/exit/restart；数据回退时保持对话框并展示安全文案|标题/data-variant 实际变化；fallback 显示“使用安全默认配置继续？”；error 显示错误且禁用确认；仅 component state empty/error 安全返回|pass|
|C7 config.summary|`[data-binding~=config.summary]`|配置/dataMode|场景+分钟+节奏/安全默认/错误|dialog.body 实际解析|pass|
|C7 textScaling|`#app[data-text-scaling]`|Constrained|150%+sticky confirm|字号与 sticky 实际生效|pass|
|C7 resolvedCopy|`[data-binding~=resolvedCopy]`|pending/dataMode|变体文案/安全回退/错误|body 文案和 confirm disabled 实际变化|pass|

### 4.4 Variant / component-state / stacking map (97/97)

Every manifest fact is a separate row; no semicolon-compressed denominator.

|Fact|Selector|Trigger|Expected|Actual|Verdict|
|---|---|---|---|---|---|
|C1.variant.normal|[data-component=C1]|S0|默认幅度|默认幅度|pass|
|C1.variant.reduceMotion|[data-component=C1]|#reduce|6% 幅度|6% 幅度|pass|
|C1.variant.largeText|[data-component=C1]|Constrained|150% 重排|150% 重排|pass|
|C2.variant.sea|[data-component=C2]|scene.sea|海面色块|海面色块|pass|
|C2.variant.cloud|[data-component=C2]|scene.cloud|云层色块|云层色块|pass|
|C2.variant.dune|[data-component=C2]|scene.dune|沙丘色块|沙丘色块|pass|
|C2.variant.largeText|[data-component=C2]|Constrained|单列|单列|pass|
|C3.variant.regular|[data-component=C3]|Large|行布局|行布局|pass|
|C3.variant.compact|[data-component=C3]|Compact|换行|换行|pass|
|C3.variant.largeText|[data-component=C3]|Constrained|纵向+sticky start|纵向+sticky start|pass|
|C4.variant.sea|[data-component=C4]|scene.sea|海面背景|海面背景|pass|
|C4.variant.cloud|[data-component=C4]|scene.cloud|云层背景|云层背景|pass|
|C4.variant.dune|[data-component=C4]|scene.dune|沙丘背景|沙丘背景|pass|
|C4.variant.normal|[data-component=C4]|Reduce off|全幅+弱粒子|全幅+弱粒子|pass|
|C4.variant.reduceMotion|[data-component=C4]|#reduce|小幅无粒子|小幅无粒子|pass|
|C4.variant.performance|[data-component=C4]|compState=performance|无粒子且性能提示|无粒子且性能提示|pass|
|C5.variant.base|[data-component=C5]|Large|横向操作|横向操作|pass|
|C5.variant.largeText|[data-component=C5]|Constrained|滚动/换行|滚动/换行|pass|
|C5.variant.controllerFocus|[data-component=C5]|focused|明确焦点|明确焦点|pass|
|C6.variant.unrecorded|[data-component=C6]|record=false|未记录|未记录|pass|
|C6.variant.recorded|[data-component=C6]|recorded/X11|已记录|已记录|pass|
|C6.variant.writeError|[data-component=C6]|dataMode=error|写入失败|写入失败|pass|
|C6.variant.largeText|[data-component=C6]|Constrained|150% 重排|150% 重排|pass|
|C7.variant.start|[data-component=C7]|X02|开始文案/无三角|开始文案/无三角|pass|
|C7.variant.restart|[data-component=C7]|X08|重开+三角|重开+三角|pass|
|C7.variant.exit|[data-component=C7]|X06|退出+三角|退出+三角|pass|
|C7.variant.largeText|[data-component=C7]|Constrained|150%+sticky confirm|150%+sticky confirm|pass|
|C1.state.default|[data-component=C1][data-demo-state=default]|#component=C1; #compState=default|默认渲染|默认渲染|pass|
|C1.state.focused|[data-component=C1][data-demo-state=focused]|#component=C1; #compState=focused|主操作获得 focus 样式|主操作获得 focus 样式|pass|
|C1.state.pressed|[data-component=C1][data-demo-state=pressed]|#component=C1; #compState=pressed|主操作获得 pressed 样式|主操作获得 pressed 样式|pass|
|C1.state.disabled|[data-component=C1][data-demo-state=disabled]|#component=C1; #compState=disabled|交互控件 disabled|交互控件 disabled|pass|
|C1.state.loading|[data-component=C1][data-demo-state=loading]|#component=C1; #compState=loading|可见“加载中…”|可见“加载中…”|pass|
|C1.state.empty|[data-component=C1][data-demo-state=empty]|#component=C1; #compState=empty|exact static fallback|精确句/灯塔/ack 保留；halo `--halo=1` 静止|pass|
|C1.state.error|[data-component=C1][data-demo-state=error]|#component=C1; #compState=error|危险色轮廓与安全回退|危险色轮廓与安全回退|pass|
|C1.state.overflow|[data-component=C1][data-demo-state=overflow]|#component=C1; #compState=overflow|150% 字号并重排|150% 字号并重排|pass|
|C2.state.default|[data-component=C2][data-demo-state=default]|#component=C2; #compState=default|默认渲染|默认渲染|pass|
|C2.state.focused|[data-component=C2][data-demo-state=focused]|#component=C2; #compState=focused|主操作获得 focus 样式|主操作获得 focus 样式|pass|
|C2.state.selected|[data-component=C2][data-demo-state=selected]|#component=C2; #compState=selected|当前选项 selected 边框|当前选项 selected 边框|pass|
|C2.state.pressed|[data-component=C2][data-demo-state=pressed]|#component=C2; #compState=pressed|主操作获得 pressed 样式|主操作获得 pressed 样式|pass|
|C2.state.disabled|[data-component=C2][data-demo-state=disabled]|#component=C2; #compState=disabled|交互控件 disabled|交互控件 disabled|pass|
|C2.state.loading|[data-component=C2][data-demo-state=loading]|#component=C2; #compState=loading|可见“加载中…”|可见“加载中…”|pass|
|C2.state.empty|[data-component=C2][data-demo-state=empty]|#component=C2; #compState=empty|程序化占位色块|三张 swatch 保留并标 `data-fallback=procedural`|pass|
|C2.state.error|[data-component=C2][data-demo-state=error]|#component=C2; #compState=error|危险色轮廓与安全回退|危险色轮廓与安全回退|pass|
|C2.state.overflow|[data-component=C2][data-demo-state=overflow]|#component=C2; #compState=overflow|150% 字号并重排|150% 字号并重排|pass|
|C3.state.default|[data-component=C3][data-demo-state=default]|#component=C3; #compState=default|默认渲染|默认渲染|pass|
|C3.state.focused|[data-component=C3][data-demo-state=focused]|#component=C3; #compState=focused|主操作获得 focus 样式|主操作获得 focus 样式|pass|
|C3.state.selected|[data-component=C3][data-demo-state=selected]|#component=C3; #compState=selected|当前选项 selected 边框|当前选项 selected 边框|pass|
|C3.state.pressed|[data-component=C3][data-demo-state=pressed]|#component=C3; #compState=pressed|主操作获得 pressed 样式|主操作获得 pressed 样式|pass|
|C3.state.disabled|[data-component=C3][data-demo-state=disabled]|#component=C3; #compState=disabled|交互控件 disabled|交互控件 disabled|pass|
|C3.state.loading|[data-component=C3][data-demo-state=loading]|#component=C3; #compState=loading|可见“加载中…”|可见“加载中…”|pass|
|C3.state.empty|[data-component=C3][data-demo-state=empty]|#component=C3; #compState=empty|defaults+使用默认|handler 重置120/calm；选项保留；start.action=使用默认|pass|
|C3.state.error|[data-component=C3][data-demo-state=error]|#component=C3; #compState=error|强制120秒/calm且显示“使用默认”|handler 重置选中目标；start.action 改写|pass|
|C3.state.overflow|[data-component=C3][data-demo-state=overflow]|#component=C3; #compState=overflow|150% 字号并重排|150% 字号并重排|pass|
|C4.state.inhale|[data-component=C4][data-demo-state=inhale]|#component=C4; #compState=inhale|相位文字、形状、环/粒子可见改变|相位文字、形状、环/粒子可见改变|pass|
|C4.state.hold1|[data-component=C4][data-demo-state=hold1]|#component=C4; #compState=hold1|相位文字、形状、环/粒子可见改变|相位文字、形状、环/粒子可见改变|pass|
|C4.state.hold2|[data-component=C4][data-demo-state=hold2]|#component=C4; #compState=hold2|相位文字、形状、环/粒子可见改变|相位文字、形状、环/粒子可见改变|pass|
|C4.state.exhale|[data-component=C4][data-demo-state=exhale]|#component=C4; #compState=exhale|相位文字、形状、环/粒子可见改变|相位文字、形状、环/粒子可见改变|pass|
|C4.state.paused|[data-component=C4][data-demo-state=paused]|#component=C4; #compState=paused|相位文字、形状、环/粒子可见改变|相位文字、形状、环/粒子可见改变|pass|
|C4.state.loading|[data-component=C4][data-demo-state=loading]|#component=C4; #compState=loading|可见“加载中…”|可见“加载中…”|pass|
|C4.state.empty|[data-component=C4][data-demo-state=empty]|#component=C4; #compState=empty|static paused，真正冻结|handler 进入 empty 时设置 paused/pausedAt；已暂停、--:--、audio frozen、dust=0|pass|
|C4.state.error|[data-component=C4][data-demo-state=error]|#component=C4; #compState=error|暂停/虚线/--:--/音频冻结|实际改写 phaseLabel、countdown 与 audioState，并加危险轮廓|pass|
|C4.state.performance|[data-component=C4][data-demo-state=performance]|#component=C4; #compState=performance|相位文字、形状、环/粒子可见改变|相位文字、形状、环/粒子可见改变|pass|
|C4.state.overflow|[data-component=C4][data-demo-state=overflow]|#component=C4; #compState=overflow|150% 字号并重排|150% 字号并重排|pass|
|C5.state.default|[data-component=C5][data-demo-state=default]|#component=C5; #compState=default|默认渲染|默认渲染|pass|
|C5.state.focused|[data-component=C5][data-demo-state=focused]|#component=C5; #compState=focused|主操作获得 focus 样式|主操作获得 focus 样式|pass|
|C5.state.pressed|[data-component=C5][data-demo-state=pressed]|#component=C5; #compState=pressed|主操作获得 pressed 样式|主操作获得 pressed 样式|pass|
|C5.state.disabled|[data-component=C5][data-demo-state=disabled]|#component=C5; #compState=disabled|交互控件 disabled|交互控件 disabled|pass|
|C5.state.loading|[data-component=C5][data-demo-state=loading]|#component=C5; #compState=loading|可见“加载中…”|可见“加载中…”|pass|
|C5.state.empty|[data-component=C5][data-demo-state=empty]|#component=C5; #compState=empty|fallback frozen“保持暂停”|正文改为保持暂停；继续/重开/退出均保留|pass|
|C5.state.error|[data-component=C5][data-demo-state=error]|#component=C5; #compState=error|保持暂停|可见正文实际改为“保持暂停”并保留冻结|pass|
|C5.state.overflow|[data-component=C5][data-demo-state=overflow]|#component=C5; #compState=overflow|150% 字号并重排|150% 字号并重排|pass|
|C6.state.default|[data-component=C6][data-demo-state=default]|#component=C6; #compState=default|默认渲染|默认渲染|pass|
|C6.state.focused|[data-component=C6][data-demo-state=focused]|#component=C6; #compState=focused|record 获得 focus 样式|record 获得 focus 样式|pass|
|C6.state.pressed|[data-component=C6][data-demo-state=pressed]|#component=C6; #compState=pressed|record 获得 pressed 样式|record 获得 pressed 样式|pass|
|C6.state.loading|[data-component=C6][data-demo-state=loading]|#component=C6; #compState=loading|可见“加载中…”|可见“加载中…”|pass|
|C6.state.recorded|[data-component=C6][data-demo-state=recorded]|#component=C6; #compState=recorded|record checked 且 recordState=已记录|record checked 且 recordState=已记录|pass|
|C6.state.error|[data-component=C6][data-demo-state=error]|#component=C6; #compState=error|强制未记录|handler record=false；checkbox unchecked；recordState=未记录|pass|
|C6.state.disabled|[data-component=C6][data-demo-state=disabled]|#component=C6; #compState=disabled|交互控件 disabled|交互控件 disabled|pass|
|C6.state.empty|[data-component=C6][data-demo-state=empty]|#component=C6; #compState=empty|unrecorded toggle/status|handler record=false；toggle 保留且 unchecked；status=未记录；exit 保留|pass|
|C6.state.overflow|[data-component=C6][data-demo-state=overflow]|#component=C6; #compState=overflow|150% 字号并重排|150% 字号并重排|pass|
|C6.state.exit-focused|[data-component=C6][data-demo-state=exit-focused]|#component=C6; #compState=exit-focused|仅 complete.exit 获得 focus 样式|仅 complete.exit 获得 focus 样式|pass|
|C6.state.exit-pressed|[data-component=C6][data-demo-state=exit-pressed]|#component=C6; #compState=exit-pressed|仅 complete.exit 获得 pressed 样式|仅 complete.exit 获得 pressed 样式|pass|
|C7.state.default|[data-component=C7][data-demo-state=default]|#component=C7; #compState=default|默认渲染|默认渲染|pass|
|C7.state.focused|[data-component=C7][data-demo-state=focused]|#component=C7; #compState=focused|主操作获得 focus 样式|主操作获得 focus 样式|pass|
|C7.state.pressed|[data-component=C7][data-demo-state=pressed]|#component=C7; #compState=pressed|主操作获得 pressed 样式|主操作获得 pressed 样式|pass|
|C7.state.disabled|[data-component=C7][data-demo-state=disabled]|#component=C7; #compState=disabled|交互控件 disabled|交互控件 disabled|pass|
|C7.state.loading|[data-component=C7][data-demo-state=loading]|#component=C7; #compState=loading|可见“加载中…”|可见“加载中…”|pass|
|C7.state.empty|#app[data-state=S1],#app[data-state=S4]|#component=C7; #compState=empty（选择前或进入后）|安全关闭并恢复|override applyDemo 每次渲染检查：start→S1；exit/restart→S4；pending 清空|pass|
|C7.state.error|#app[data-state=S1],#app[data-state=S4]|#component=C7; #compState=error|关闭不安全弹窗并返回|start 返回 S1；exit/restart 返回 S4；pending 清空|pass|
|C7.state.overflow|[data-component=C7][data-demo-state=overflow]|#component=C7; #compState=overflow|150% 字号并重排|150% 字号并重排|pass|
|C1.stack|`[data-stacking]`|#stackDemo + C1|error>loading>focused|C1 同时得到 error/loading；主操作 focused|pass|
|C2.stack|`[data-stacking]`|#stackDemo + C2 selected+focused|选中与焦点共存|data-stacking + selected/focused|pass|
|C3.stack|`[data-stacking]`|#stackDemo + C3 selected+focused+invalid start|disabled start 优先|选中项 focused；start.action 精确 disabled|pass|
|C4.stack|`[data-stacking]`|#stackDemo + C4 paused+error+Reduce|error>paused>reduce>phase|错误轮廓、暂停文字、小幅无粒子|pass|
|C5.stack|`[data-stacking]`|#stackDemo + C5 error + C7|C7 modal > C5 error|C5 后插入独立 C7 modal，层级覆盖 error C5|pass|
|C6.stack|`[data-stacking]`|#stackDemo + C6 recorded-attempt+error+focused|error>recorded>focused|recordAttempt=recorded；error；record focused 但 unchecked；“写入失败，未记录”|pass|
|C7.stack|`[data-stacking]`|#stackDemo + C7 loading+disabled+focused|loading>disabled>focused|加载文案、确认禁用、组合标记|pass|

### 4.5 Responsive / ReduceMotion map (4/4)

|Fact|Selector|Trigger|Expected|Actual|Verdict|
|---|---|---|---|---|---|
|Large|`#app[data-responsive=large][data-content-area="1216x736"] .window`|#responsive=large|inner content 1216×736；三列|content-box width=1216px/height=736px/min-height=0；overflow auto；无 cap|pass|
|Compact|`#app[data-responsive=compact][data-content-area="896x656"] .window`|#responsive=compact|inner content 896×656；单列|content-box width=896px/height=656px/min-height=0；overflow auto；内容不撑大|pass|
|Constrained|`#app[data-responsive=constrained][data-content-area="896x656@150%"] .window`|#responsive=constrained|inner content 896×656、150%、滚动、固定操作|content-box width=896px/height=656px；无 viewport cap；sticky|pass|
|Reduce Motion|`#app[data-reduce-motion=true]`|#reduce|环幅度≤6%、无粒子、无 focus scale|amplitude=.06；dust 空；focused transform none|pass|

## 5 Temporal and lifecycle verification

|Case|Selector/trigger|Expected|Actual|Verdict|
|---|---|---|---|---|
|Unified clock|tick(now)|声画、倒计时同源|elapsed=now-startMono-pausedAccum；phase/ring/countdown/cycle all derive elapsed|pass|
|Refresh independence|requestAnimationFrame at any cadence|no frame accumulation|no `elapsed += frameDelta`; monotonic clock derivation|pass|
|Pause snapshot|X04/onPause|同一帧冻结|pausedAt set, paused=true, tick guard blocks changes, audioState=frozen|pass|
|Resume rebase|X05|no jump + smooth audio return|pausedAccum adds pause span; normal/performance 400ms|pass|
|Pattern consumption|pattern control|BreathPattern changes all consumers|phaseSets calm=12s/even=10s/long=13s; phase, progress and cycleIndex use selected set|pass|
|Cycle particles|cross current pattern boundary|only after complete cycle|cycleIndex=floor(elapsed/cycleMs); particle count grows only on boundary|pass|
|Completion|elapsed≥duration×1000|2-minute flow completes|duration default 120; clamp then X10→S7|pass|
|System Back|data-system-back|controller-only flow complete|running→pause; pause→exit confirm; dialog→pause; completion→closed|pass|
|Start dialog Back|S2 + data-system-back|cancel to selection|state=S1; pending=null; never closes app|pass|

## 6 Requirement trace

PM@11 R1–R15 map to S0–S7, X01–X12 and C1–C7. Exact first-use and completion copy are rendered. Durations 2/4/6, scenes sea/cloud/dune, pattern selection, pause/exit/restart, optional local record, ReduceMotion, weak cycle particles and controller Back are all triggerable. Screenshot capture, Android/PICO runtime and device audio remain downstream; this package intentionally generates no Android code.

## 7 Device boundary

`deviceValidation.status=not_performed`: physical distance, occlusion, fatigue, controller ray precision, PICO lifecycle integration, spatial-audio pop waveform and runtime performance are not validated by this Web preview. Those remain explicit downstream tests.

## 8 V3 active gate

V3 active review `766fc5b7-3622-4d69-80c0-67501162ebf6` rebuilt states 8, transitions 12, render elements 34, bindings 30, variants 27, component states 63, stacking 7, responsive/motion 4；all diffs zero and no active finding. `minimumCompletenessGate=pass`.
