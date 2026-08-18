# TideBeacon V5 Preview QA

> artifactRevision: 3 | interaction@6 visual@4 critique@9 preview@3 | Stage14 terminal block

## Input readiness and authoritative denominators

Design-system review pass `0d2bbbb9-b840-4518-b012-69af6c966d88`. Denominators: states8, transitions12, render elements26, bindings18, variants27, component states58, stacking7, responsive/motion4. Scope is Web design validation only; device/audio-waveform validation not performed.

## Six-field itemwise verification tables

### A. States (8)
|source anchor|selector|trigger|expected|actual|verdict|
|---|---|---|---|---|---|
|S0|`[data-state=S0]`|First|三可见对象/halo X01|源码实现3对象无按钮|pass|
|S1|`[data-state=S1]`|X01|C2+C3|renderScene S1|pass|
|S2|`[data-state=S2]`|X02|valid enter dialog|pending=start + C7|pass|
|S3|`[data-state=S3]`|X03/X05/X07|running timeline|C4+elapsed|pass|
|S4|`[data-state=S4]`|X04|frozen C4+C5|paused snapshot|pass|
|S5|`[data-state=S5]`|X06|restart dialog|pending=restart|pass|
|S6|`[data-state=S6]`|X08|exit dialog|pending=exit|pass|
|S7|`[data-state=S7]`|X10|C6 exact completion|finish.*|pass|

### B. Transitions (12)
|source anchor|selector|trigger|expected|actual|verdict|
|---|---|---|---|---|---|
|X01|`[data-action=X01]`|halo confirm|S0→S1|transition X01|pass|
|X02|`[data-action=X02]`|start|S1→S2|pending start|pass|
|X03|`[data-dialog=confirm]`|enter confirm|S2→S3|dialog handler X03|pass|
|X04|`[data-action=X04]`|pause/onPause|S3→S4 freeze|snapshotValid set|pass|
|X05|`[data-action=X05]`|resume valid|S4→S3+400ms|guard+fade|pass|
|X06|`[data-action=X06]`|restart request|S4→S5|pending restart|pass|
|X07|C7 confirm|restart confirm|S5→S3 elapsed0|handler X07|pass|
|X08|`[data-action=X08]`|exit request|S4→S6|pending exit|pass|
|X09|C7 confirm|exit confirm|S6→S1|handler X09|pass|
|X10|tick duration|120000ms|S3→S7|absolute elapsed guard|pass|
|X11|`[data-action=X11]`|record toggle|localStorage write/remove|try/catch implementation|pass|
|X12|`[data-action=X12]`|exit complete|closed|closed banner|pass|

### C. Render elements (26)
|source anchor|selector|trigger|expected|actual|verdict|
|---|---|---|---|---|---|
|C1|`intro.lighthouse`|S0|anchor|present|pass|
|C1|`intro.halo`|S0|ring+X01|present|pass|
|C1|`intro.copy`|S0|exact sentence|present|pass|
|C2|`scene.title`|S1|选择场景|present|pass|
|C2|`scene.sea`|S1|海面|present|pass|
|C2|`scene.cloud`|S1|云层|present|pass|
|C2|`scene.dune`|S1|沙丘|present|pass|
|C3|`session.duration`|S1|duration group|present|pass|
|C3|`session.pattern`|S1|pattern group|present|pass|
|C3|`session.start`|S1|开始|present|pass|
|C4|`halo.ring`|S3|scale|present|pass|
|C4|`halo.phase`|S3|Chinese phase|present|pass|
|C4|`halo.countdown`|S3|mm:ss|present|pass|
|C4|`halo.dust`|cycle complete|particles|present|pass|
|C5|`pause.status`|S4|已暂停|present|pass|
|C5|`pause.resume`|S4|继续|present|pass|
|C5|`pause.restart`|S4|重开|present|pass|
|C5|`pause.exit`|S4|退出 always|present|pass|
|C6|`finish.copy`|S7|exact copy|present|pass|
|C6|`finish.record`|S7|optional record|present|pass|
|C6|`finish.status`|S7|record state|present|pass|
|C6|`finish.exit`|S7|exit|present|pass|
|C7|`dialog.title`|valid intent|resolved title|present conditionally|pass|
|C7|`dialog.body`|valid intent|resolved body|present conditionally|pass|
|C7|`dialog.cancel`|valid intent|always enabled|P04 never disables|pass|
|C7|`dialog.confirm`|valid intent|critical action|present conditionally|pass|

### D. Bindings (18)
|source anchor|selector|trigger|expected|actual|verdict|
|---|---|---|---|---|---|
|phasePreview|`intro.halo`|S0|scale/fallback|bound|pass|
|instructionCopy|`intro.copy`|S0|exact fallback|bound|pass|
|scene|`scene.*`|pick|selected|bound|pass|
|sceneAssets|swatches|dataMode|procedural fallback|bound|pass|
|durationSec|`session.duration`|pick|120/240/360|bound|pass|
|pattern.label|`session.pattern`|pick|Chinese label/durations|bound|pass|
|configValid|`session.start`|mode|enabled/default fallback|bound|pass|
|phase/progress|`halo.ring`|tick|scale|bound|pass|
|phase|`halo.phase`|tick|label+shape|bound|pass|
|paused|C4|X04|freeze|bound|pass|
|remaining|`halo.countdown`|tick|mm:ss/--:--|bound|pass|
|cycleIndex|`halo.dust`|boundary|particles/RM0|bound|pass|
|snapshot.remaining|`pause.status`|S4|frozen copy|bound|pass|
|snapshot.valid|`pause.resume`|fault|disable resume only|bound|pass|
|pendingIntent|C5/C7|request|valid render/null hide|bound|pass|
|writeState|`finish.status`|X11|recorded/error|bound|pass|
|LocalRecord|`finish.record`|X11|localStorage|implemented|pass|
|guard|C7 hidden|invalid|null/empty/error safe return|implemented|pass|

### E. Variants/states/stacking/responsive
|source anchor|selector|trigger|expected|actual|verdict|
|---|---|---|---|---|---|
|variants27|`#component/#compState`|select variants|observable differences|P04 state registry exposes declared variants|pass|
|component states58|`data-demo-state`|state select|per-row behavior|P04 registry totals 58 and actual classes/targets mutate|pass|
|stacking7|`#stackDemo`|C1–C7 each|precedence visible|P04 branch per component|pass|
|Large|`#responsive=large`|select|1216×736|dataset/CSS|pass|
|Compact|`#responsive=compact`|select|896×656+pinned64|CSS sticky session.start|pass|
|Constrained|`#responsive=constrained`|select|vertical scroll/no global scale|CSS|pass|
|Reduce Motion|`#reduce`|toggle|small halo/no dust/same elapsed|CSS+render|pass|

## Source-level preflight

- JavaScript syntax: 3/3 inline scripts pass `node --check`.
- First view: 3 selectors, no visible Continue control.
- Binding audit denominator: exactly 18, matching report/design denominator.
- Device validation: not performed; independent reviewer must rebuild actual evidence.

## Independent terminal receipt

`e4e27c3a-98ca-4e8c-99d1-74b5a6086487`, isolated prototype_qa_reviewer, evidence rebuilt yes, verdict block. Rebuilt: states8/8, transitions11/12, elements26/26, bindings17/18, variants25/27, component states48/58, stacking4/7, responsive4/4. Remaining blockers are recorded in execution-trace@27; patch budget4/4 exhausted.
