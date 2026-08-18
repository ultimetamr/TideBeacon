# Spatial App Requirement Spec · TideBeacon

> Role: `product_strategist` | Active artifact revision: **5** | Stage ownership: Stage 1 intent; Stage 3 quality contract; Stage 4 bounded evidence-integrity patches.

## 0. Role Trace and Provenance

- Stage 1 source: the user's original requirement only.
- Process source: official `pico-spatial-app-designer` method and blank role template.
- Originality: `templateReuse:false`; historical TideBeacon design artifacts were not read.
- Stage boundary: intent is frozen from revision 1; Stage 3 adds the evidence-backed quality contract without changing frozen intent.

## 1. Direct Description of Outputs

This revision defines product intent, unknowns, and the boundary passed to research. It does not choose layout, component anatomy, visual direction, or delivery status.

## 2. Background and Problem

- **One-sentence requirement description**: TideBeacon is a short PICO spatial breathing-rhythm experience in Chinese that helps a wearer follow an externally presented light-and-spatial-audio cadence without sensing, scoring, diagnosing, or claiming to improve the user's breathing or health.
- **Target users**: Chinese-reading PICO users seeking a low-cognitive-load, two-to-six-minute visual rhythm break; no medical condition or health status is inferred.
- **Use scenarios**: a quiet indoor moment with a PICO headset, where the wearer wants a simple timed visual-and-audio cadence and may need to pause, resume, restart, or leave immediately.
- **Wearing posture**: designed for seated or stationary standing use; walking is outside the intended scenario.
- **Frequency and duration**: selectable 2, 4, or 6 minutes per session; repeat frequency is user-chosen and not prescribed.
- **Preliminary judgment of spatial necessity**: a distant lighthouse, environmental depth, halo scale change, and localized ambient sound can make rhythm legible through direction, distance, scale, and time while keeping instructions minimal. This is a preliminary judgment for Stage 6, not proof by assertion.

## 3. Key Moment

- **The moment a screen cannot achieve as strongly**: light visibly expands around a distant spatial lighthouse while a synchronized spatial-audio swell shares the same phase clock, then both freeze together on pause.
- **Placement on the immersion spectrum**: Stage in Full Space is the requested target only if Stage 6 proves sufficient spatial value. Configuration/confirmation placement remains a downstream architecture decision.
- **Entry path**: launch must preserve the exact first-visible set and require an explicit user action before configuration or progression. Which visible element carries that action, and how it is implemented, are downstream interaction decisions.

## 4. Product Research Baseline Anchors

Deferred to Stage 2. No competitor, duration, usability, or comfort claim is asserted in Stage 1 without evidence.

## 5. Intent Definition (frozen items)

- **Domain / sub-domain**: non-clinical immersive rhythm guidance / short-form breathing-cadence visualization.
- **Risk level**: medium interaction-and-comfort risk; explicitly non-medical and low data sensitivity.
- **Default space**: Full Space is the requested stage-project target; Stage use remains conditional on Stage 6 spatial-value proof and requires explicit entry plus stable exit.
- **Core scenarios**: first-use cadence comprehension; duration/environment/pattern selection; active rhythm following; pause/resume; exit confirmation; restart; system interruption and recovery; completion; optional local record review.
- **Core tasks and decisions**: understand inhale/exhale mapping; choose 2/4/6 minutes; choose sea/cloud/dune environment; choose rhythm; start; pause/resume; decide restart/exit; optionally save a local completion record.
- **Data / AI / sensors / permissions**: local configuration, a shared monotonic session timeline, and optional local completion records only. No AI, microphone, breathing detection, scoring, biometric inference, network data, or sensor permission is required by product intent. Standard controller input is supported without extra product data collection.
- **Collaboration**: none; single wearer, local device.
- **Product identity**: project name `TideBeacon`; downstream requested package name `com.pico.swan.tidebeacon`, recorded for traceability only because this skill does not create runtime code.

## 6. Assumptions List

| # | Assumption | Confidence | Impact | Validation Plan |
|---|---|---|---|---|
| A1 | An explicit Stage-entry action can be provided without adding a fourth element to the exact first-visible set; its carrier and interaction are unknown. | medium | If false, first-view and Stage-entry obligations conflict. | Compare downstream interaction hypotheses and test the selected mapping; no extra first-view element may become visible. |
| A2 | The experience can support at least one stationary posture without prescribing a viewing distance, container, or layout at this stage. | medium | Affects comfort and reach. | Derive viewing conditions after concept/container selection, then validate controller targeting on device. |
| A3 | Rhythm presets can start with a small non-medical set, with exact phase durations treated as configurable data. | high | Affects timing fixtures but not product promise. | Run state-machine tests for every preset and review labels for non-therapeutic language. |
| A4 | Optional local record means a minimal timestamp/duration/environment/pattern completion entry saved locally, never a streak, score, or efficacy metric. | high | Affects data model and completion UI. | Preview localStorage create/read/clear checks; privacy copy review. |
| A5 | Spatial audio assets may initially be generated or placeholder loops but must expose phase-aligned gain envelopes. | medium | Affects perceived synchronization and audio quality. | Audio timeline instrumentation and downstream headset listening test. |
| A6 | Chinese system font fallback is available on target runtime. | medium | Affects typography fidelity. | Downstream device font render check; preview uses a Chinese-capable fallback stack. |
| A7 | No research participant evidence is available in the user source. | high | Persona and comfort claims cannot be treated as observed facts. | Label persona/journey as provisional and schedule 5-participant comprehension/usability study. |

## 7. Quality Contract

### 7.1 Required user outcomes

| Outcome | Acceptance assertion |
|---|---|
| O1 Immediate cadence comprehension | On first view, only a distant lighthouse, halo, and exact sentence `光扩张时吸气，光收回时呼气。` are visible; at least 4/5 usability participants correctly describe the two halo directions within 5 seconds without extra explanation. |
| O2 Complete choice | A controller-only user can choose one of 2/4/6 minutes, sea/cloud/dune, and a rhythm preset, then start within 20 seconds after confirming the first view. |
| O3 Deterministic cadence | Halo pose, spatial-audio envelope, phase label/countdown, and completion derive from the same monotonic active-time value; simulated 60/72/90/120Hz runs end each phase and session within ±1 rendered frame of the same timestamp, with no cumulative drift. |
| O4 Coherent pause/resume | User pause and `systemPaused` freeze visual pose, audio playhead/gain, active elapsed time, cycle/particle emission, and countdown together; resume preserves phase position and ramps audio from 0 to target gain over 600ms without a discontinuity. |
| O5 Safe control | Pause, resume, restart, exit, rhythm selection, confirmation dialogs, and all menu paths are controller operable; System Back in paused state opens exit confirmation and never restarts. |
| O6 Non-medical privacy | No microphone, breath sensing, biometric input, AI, network account, score, streak, diagnosis, health assessment, or therapeutic copy exists. |
| O7 Completion and optional local record | Natural completion shows only `完成一次练习` as the required completion statement; a separate optional action writes an actual local record binding, and storage failure never invalidates completion. |
| O8 Comfort and restraint | No automatic camera movement or large forced displacement; authored content produces no more than three flashes in any one-second period (WCAG 2.2 SC 2.3.1 project-adopted accessibility threshold, not claimed as a PICO rule); star/particle accent occurs once only after a completed cycle and is suppressed while paused/systemPaused. |
| O9 Recoverability | Invalid pattern/timeline/storage data, overflow, lifecycle interruption, restart, and exit paths remain render-safe and lead to a stable selection, paused, completion, or close outcome. |
| O10 Acceptance evidence | A complete 2-minute Web logic flow is triggerable; named preview states support capture of scene selection and active practice; downstream state/timeline tests are specified. |

### 7.2 Success / efficiency criteria

- First-view comprehension: 4/5 participants correctly state the mapping in ≤5 seconds.
- Controller selection/start: 4/5 complete without assistance in ≤20 seconds after first-view confirmation.
- Pause acknowledgement: logical freeze is effective on the same event turn; preview-visible response ≤100ms; physical result requires device validation.
- Refresh-rate invariance: phase/session boundary error ≤ one rendered frame against monotonic timestamps, zero cycle-count divergence across tested rates.
- Two-minute happy path: all mandatory states, including pause/resume and completion, are runnable in accelerated preview mode and specified for real-time downstream testing.
- Preview implementation coverage: 100% item-by-item denominator for states/transitions, render elements, bindings normal/fallback/error, variants/component states/stacking, Large/Compact/Constrained/Reduce Motion.

### 7.3 Risks and must-not-fail items

- First-view exclusivity cannot be relaxed for onboarding convenience; explicit continuation must not add a fourth visible element, while its interaction mapping remains a downstream decision.
- Exit/restart are high-impact state changes and require a blocking confirmation with confirm/cancel; System Back from any user-paused condition routes to exit confirmation.
- `systemPaused` is distinct from user pause, and restoration must not advance hidden elapsed time.
- Invalid/error paths must never render undefined/NaN styles or throw; they freeze/mute time-sensitive output and provide an unnamed stable, readable recovery outcome.
- Independent clocks, frame-count accumulation, audio restart from sample zero, particles before cycle completion, health/efficacy language, and invisible controller-only focus are release blockers.

### 7.4 Default visible primary-window orientation

- Product-level preference: one primary attention locus at a time and no default multi-window dashboard. Container count/form, Stage selection after spatial-value proof, and any fallback are downstream concept/architecture decisions.

### 7.5 Domain-specialized component orientation

Downstream component synthesis must make these semantic obligations explicit without inheriting predetermined names or combinations: four-phase visual cue; one-source active-time representation; duration/scene/rhythm decisions; pause/resume/restart/exit commands; blocking restart/exit confirmation; binary completion with optional local persistence; lifecycle/error recovery. Whatever components Stage 11 independently derives must each contain all eight required structural segments.

### 7.6 Real-time data trust orientation

- There is no network/realtime external data. `SessionClock` is nevertheless time-critical and must expose source, active elapsed time, phase and lifecycle status to the implementation.
- Local records are optional, device-local, and non-authoritative; show `未保存记录` on denial/full/error and never simulate a successful write.
- Machine enums never appear as end-user copy. Invalid values map to human-readable render-safe recovery.

### 7.7 PICO platform and spatial-design hard constraints

- Full Space legality for Stage and explicit entry/stable exit: `PICO-SPACESTATE-001`, `PICO-SPACESTATE-002`, `PICO-STAGE-001` in official-rules v2.2.0.
- Controller fallback: `PICO-ACCESS-002`; Reduce Motion: `PICO-ACCESS-001`; text scaling: `PICO-ACCESS-003`; stable exit: `PICO-ACCESS-004`; non-color semantics: `PICO-COLOR-001`.
- ≥56dp targets, ≥12dp body text and 65°×40° central FOV: `PICO-WINDOW-SIZING-005/006` and the official skill window-sizing methodology. These are PICO-method constraints.
- No automatic camera movement or continuous/full-screen flashing: `PICO-MOTION-001/002`. The more measurable no-more-than-three-flashes-per-second acceptance invariant is a project adoption from [WCAG 2.2 SC 2.3.1](https://www.w3.org/WAI/WCAG22/Understanding/three-flashes-or-below-threshold), not a PICO claim.
- Attachments require a documented decision matrix comparing `None` and `InlineControl`; no Toolbar/TabBar by default.
- Window sizing, if a WindowContainer is later derived, must follow the complete applicable PICO methodology chain; physical/device assertions remain unvalidated at this stage.

### 7.8 Originality requirement

Anchored to UXR §3A: absorb direct phase mapping, rhythm/duration choice, stable early exit, and authentic XR depth/audio opportunity; reject dashboard catalogs, rankings, sensor/biofeedback, AI/account ecosystems, efficacy language, spectacle, and forced motion. TideBeacon differentiates through one distant lighthouse focus, one shared clock, no sensing, quiet procedural environments, exact whole-system freeze, and local-only optional completion history. `templateReuse:false`; no historical TideBeacon case is available to generation.

### 7.9 Design / readability / downstream-implementation acceptance plan

- Six role documents pass their minimum-completeness tables; all 17 ordered receipts and six independent review invocations carry exact revisions.
- State/transition and every component fact are uniquely identified and mapped into the pre-generation Preview Manifest.
- `preview.html` is self-contained and labeled `web_design_validation_only`; it must expose normal/fallback/error samples and independently observable obligations for responsive tiers, Reduce Motion, system interruption, local-record activity, restart, exit, overflow, and stacking. Concrete state/component structure is not prescribed here.
- A downstream implementation plan must include deterministic virtual-clock tests, lifecycle pause/resume tests, audio gain-envelope assertions, controller traversal, local-storage failure, and screenshot capture for selection and practice states.
- Device-only comfort, occlusion, readability, hit precision, audio quality, and runtime performance remain `not_performed`.

## 8. Requirements Traceability

### 8.0 Requirement source ledger

Source record `SRC-USER-20260812` is the user-provided product requirement. Source record `SRC-HOST-PREFLIGHT-20260812` is a project-process gate supplied to this worker and is **not** user-product evidence. `verbatim` preserves exact wording; `normalized` separates a compound clause; `project-derived` never masquerades as user evidence.

| Requirement | Source record / locator | Exact source text | Transformation |
|---|---|---|---|
| R1 | SRC-USER-20260812 ¶1 | `在当前目录创建名为 TideBeacon、包名 com.pico.swan.tidebeacon 的 PICO Spatial SDK stage 项目。` | verbatim; this design skill records downstream identity only |
| R2 | SRC-USER-20260812 ¶1 | `开发一段纯视觉和空间音频的中文呼吸节奏体验。它不是医疗或健康诊断产品` | normalized product/copy obligation |
| R3 | SRC-USER-20260812 ¶1 | `不要检测呼吸、接麦克风、做评分或使用疗效语言。` | verbatim |
| R4 | SRC-USER-20260812 ¶2 | `首次进入只显示一座远处灯塔、一圈光环和一句话：“光扩张时吸气，光收回时呼气。”` | verbatim |
| R5 | SRC-USER-20260812 ¶2 | `用户选择 2、4、6 分钟，以及海面、云层、沙丘三个低多边形场景。` | verbatim |
| R6 | SRC-USER-20260812 ¶2 | `光环按可配置 BreathPattern 运行：吸气扩张、短暂停留、呼气收缩、短暂停留。` | verbatim |
| R7 | SRC-USER-20260812 ¶2 | `声画、计时必须使用同一时间轴。` | verbatim |
| R8 | SRC-USER-20260812 ¶2 | `场景只在完成循环时增加细微星光/粒子，不能有快速相机移动或强烈闪烁。` | verbatim; numeric flash threshold is separate project-derived acceptance |
| R9 | SRC-USER-20260812 ¶3 | `提供暂停、退出、重新开始和节奏选择。暂停时动画、声音、倒计时应一起冻结；恢复时音量平滑淡入。` | verbatim |
| R10 | SRC-USER-20260812 ¶3 | `手柄应能完成所有菜单操作。` | verbatim |
| R11 | SRC-USER-20260812 ¶3 | `完成后仅显示“完成一次练习”和可选本地记录。` | verbatim |
| R12 | SRC-USER-20260812 ¶4 | `请写时间轴或状态机测试，验证不同刷新率下节奏一致，并处理应用暂停/恢复。` | verbatim |
| R13 | SRC-USER-20260812 ¶4 | `先采用程序化天空、海面和灯塔占位美术。` | verbatim |
| R14 | SRC-USER-20260812 ¶5 | `验收：不看长说明也能理解呼吸节奏；暂停恢复无跳帧/爆音；2 分钟完成流程可运行。` | verbatim |
| R15 | SRC-USER-20260812 ¶5 | `截图场景选择页和练习进行页。` | verbatim |
| R16 | SRC-HOST-PREFLIGHT-20260812 | `System Back 在暂停S4必须走退出确认而非重启` | project-derived gate: semantic paused condition; `S4` is trace only, not frozen state name |
| R17 | SRC-HOST-PREFLIGHT-20260812 | `C7错误/无效路径非渲染安全返回` | project-derived gate: render-safe invalid/error recovery; C7 is trace only |
| R18 | SRC-HOST-PREFLIGHT-20260812 | `LocalRecord必须有真实localStorage数据绑定目标；必须独立表现systemPaused、recording、C7 restart/exit/overflow和全部stacking场景。` | project-derived gate: independently observable coverage obligations; labels retained only for trace |

### 8.1 Outcome-to-obligation coverage

| Requirement ID | Semantic obligation | Implementation node / downstream ownership | Validation method |
|---|---|---|---|
| R1 · user | Deliver design for TideBeacon PICO Stage target and preserve downstream package identity `com.pico.swan.tidebeacon`; this skill produces no runtime project. | PM §5 identity/boundary; downstream container derivation | design-package review + later project metadata check |
| R2 · user | Pure visual/spatial-audio Chinese cadence experience with explicit non-medical positioning. | O6; later copy/semantic facts | complete string audit |
| R3 · user | No breath detection, microphone, score, or efficacy language. | O6; later data/permission facts | permission manifest + string/data audit |
| R4 · user | First visible set is exactly lighthouse, halo, and the supplied sentence. | O1; downstream first-entry obligation | DOM/scene visible-set assertion + capture |
| R5 · user | Choices: 2/4/6 minutes; sea/cloud/dune; rhythm preset. | O2; downstream selection obligation | controller task test + binding samples |
| R6 · user | Configurable four-phase BreathPattern: inhale/hold/exhale/hold. | O3; domain entity | invalid/valid pattern tests |
| R7 · user | Audio, visuals, and countdown use one timeline. | O3; time-source obligation | virtual-time trace equality |
| R8 · user | Particles only after a completed loop; no fast camera/strong flashing. | O8; motion/safety obligation | boundary/suppression test + ≤3 flashes/s audit |
| R9 · user | Pause/exit/restart/rhythm controls; pause freezes all; resume audio fades smoothly. | O4/O5; control/lifecycle obligations | transition, clock, audio-envelope tests |
| R10 · user | Controller completes every menu operation. | O2/O5; input obligation | controller-only traversal |
| R11 · user | Completion copy is `完成一次练习`; local record is optional. | O7; persistence obligation | exact copy + actual local write/failure |
| R12 · user | Test timing across refresh rates and app pause/resume. | O3/O4/O10; acceptance plan | unit/integration test specification |
| R13 · user | Begin with procedural placeholder sky, sea, lighthouse. | later asset obligation | asset inventory review |
| R14 · user | Understandable without long copy; no resume jump/pop; 2-minute flow works. | O1/O4/O10 | usability target + accelerated preview + downstream real-time test |
| R15 · user | Capture scene-selection and active-practice views. | downstream named capture obligations | two reproducible preview scenarios |
| R16 · project preflight | System Back from a user-paused condition reaches exit confirmation, never restart. | O5; semantic transition obligation | state-machine assertion |
| R17 · project preflight | Invalid/error input returns render-safely. | O9; semantic recovery obligation | NaN/invalid/missing-data triggers |
| R18 · project preflight | Independently represent system interruption, local-record activity, restart, exit, overflow, and stacking. | O4/O5/O7/O9; denominator obligations | itemized Preview Manifest and QA |
| O1 · contract | Exact first-visible set enables immediate phase comprehension. | downstream entry interaction + visual cue facts | 4/5 users explain mapping within 5s; visible-set assertion |
| O2 · contract | Controller user completes duration/scene/rhythm choice and start efficiently. | task flow + input mapping | 4/5 users finish unaided within 20s |
| O3 · contract | All cadence channels derive from one monotonic active-time value. | time model + phase derivation | 60/72/90/120Hz virtual-clock boundary equality |
| O4 · contract | User/system interruption freezes every cadence channel and resume preserves phase with a smooth gain ramp. | lifecycle/time/audio semantics | frozen elapsed/pose/gain/countdown + 600ms gain assertion |
| O5 · contract | All high-impact controls are controller-operable and safe; paused Back never restarts. | transition/input/confirmation semantics | controller traversal + state-machine route assertion |
| O6 · contract | Product remains non-medical, non-sensing, non-scoring, private and offline. | copy/data/permission semantics | string, data-source, network and permission audit |
| O7 · contract | Completion is binary; optional record performs a real local write and degrades honestly. | completion + persistence semantics | exact copy; localStorage success/denial/full/error tests |
| O8 · contract | Motion/particles remain restrained and cycle-boundary-correct. | motion and cycle-event semantics | no camera automation; particle boundary/suppression; ≤3 flashes/s audit |
| O9 · contract | Invalid/lifecycle/storage/overflow inputs always retain render-safe stable recovery. | exception and fallback semantics | NaN/missing/invalid/overflow/restart/exit tests |
| O10 · contract | Two-minute flow and required capture states have complete evidence. | preview coverage + downstream test specification | accelerated full flow; selection/practice captures; itemized manifest |

## 9. Minimum Completeness Gate

| Check Item | Evidence Anchor | Verdict |
|---|---|---|
| Background and intent complete | §2–§5 | pass |
| Assumption governance complete | §6 | pass |
| Quality contract complete and measurable | §7.1–§7.9 | pass |
| Requirements inventory complete | §8 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass |

## 10. Delivery and Recipients

Frozen intent and the Stage 3 quality contract are complete for independent problem/evidence review and downstream task modeling.
