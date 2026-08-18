# TideBeacon 交互与空间规格

> artifactRevision:4 | active source@1 pm@3 uxr@2 | visual/container/state/layout: forward | V7 independent run

## 1 Task decisions

|Task|Actor/context|Input|Decision|Failure|Frequency/dependency|
|---|---|---|---|---|---|
|T1 understand light|first user|R3/U1|confirm expand=inhale/retract=exhale|cannot follow|once/none|
|T2 configure|user/choice|R4–6|duration+scene+pattern|wrong preference|each/T1|
|T3 enter|user/pre-stage|QC/P1|start or cancel|unexpected Full Space|each/T2|
|T4 follow|user/running|R7–9|identify phase/continue or stop|desync/no stop|continuous/T3|
|T5a pause/resume|user/running|R10/11|freeze or same-snapshot resume|jump/pop|occasional/T4|
|T5b restart|user/paused|R10|confirm elapsed0 or cancel|lost progress|occasional/T5a|
|T5c exit|user/paused|R10/12|confirm choice/closed, never complete|false completion|occasional/T5a|
|T5d lifecycle|system/running|R11/14|freeze; foreground stays paused|background advance|occasional/T4|
|T6 complete|user/duration|R13|no record/local record/exit|record pressure|each/T4|

## 2 Spatial value / 2D counterfactual

|Task|Spatial value|Rationale|2D counterfactual|Rating|
|---|---|---|---|---|
|T1|perspective only|distant beacon hints direction|flat ring fully sufficient|low|
|T2|none|comparison task|Planar is faster|low|
|T3|agency only|explicit entry|2D dialog sufficient|low|
|T4|direction+distance+scale+depth+time|world beacon and audio/halo scale share phase|2D ring+headphones works; Stage net gain unverified|high-provisional|
|T5a|time/control|pause semantics|2D pause panel|low|
|T5b|confirmation|destructive short choice|2D dialog|low|
|T5c|stable exit|no spatial benefit|2D dialog|low|
|T5d|lifecycle|no visible value|no UI, snapshot only|none|
|T6|none|short completion choice|2D completion|low|

## 3 Concepts

|Concept|Information/container hypothesis|Spatial/navigation|Interaction|Tradeoff|
|---|---|---|---|---|
|A Beacon line|teaching/choice Planar; practice Stage|fixed beacon+one halo; sequential|controller focus/confirm|timeline/audio precision|
|B Ring corridor|all config/practice in Stage arc|multi-section fixed arc|controller along arc|slower low-value config|
|C Depth islands|teach/choose/complete as exclusive landmarks|Full Stage fixed-camera crossfade|confirm landmark|metaphor learning cost|

## 4 Selection matrix

Anchors per dimension: 1=missing/no value, 3=specified executable but unvalidated, 5=user/device validated. Efficiency, Spatial, Comfort, Domain, Safety, Access, Feasible and Distinct each use this domain-specific 1/3/5 interpretation; 2/4 require adjacent-anchor evidence and unvalidated scores cap4.

|C|Eff|Spatial|Comfort|Domain|Safety|Access|Feasible|Distinct|Total/cell evidence|
|---|---:|---:|---:|---:|---:|---:|---:|---:|---|
|A|3|3|3|3|3|3|3|3|24; tasks executable; T4 provisional; fixed camera; R3–14; safe exit; controller+text/RM; SDK gap; O1–5|
|B|1|1|3|1|3|1|1|3|14; T2 slower; T2 no value; fixed arc; misses T5/6; confirm; controller only; arc SDK gap; O1|
|C|1|3|3|1|3|1|1|3|16; T1/2 forced Stage; T4 candidate; fixed crossfade; misses T5; exclusive landmarks; controller only; SDK gap; O1|

Selected A: keeps low-spatial-value tasks planar and spatializes only T4. Positioning is an unmeasured/no-score rhythm experience with auditable time semantics, grounded in M1/D1/O1–5. Absorbs environment choice, visible rhythm and stable exit at requirement level only; copies no competitor layout/state/component/visual.

## 5 Forward boundary

Container architecture, attachments, sizing and state graph will be formed at Stage09; composition at Stage10; component/motion/timeline contracts at Stage11.
