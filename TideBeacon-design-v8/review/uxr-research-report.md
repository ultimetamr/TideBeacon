# User Research Report · TideBeacon

> Role: `research_analyst` | Active artifact revision: **7** | Stage 2 research/domain model; Stage 4 bounded source-integrity patches; gate-only closure after independent substantive pass.

## 0. Role Trace and Evidence Boundary

- Product facts come from the user's original requirement; PICO constraints come from `knowledge/official-rules.json` and `knowledge/spatial-window-sizing-methodology.md`.
- Competitor facts are restricted to first-party public pages observed on 2026-08-12 and are used only at the requirement/opportunity layer.
- No competitor or historical TideBeacon layout, state graph, component composition, or visual style is reused. `templateReuse:false`.
- No participant study was supplied. User-behavior claims are explicitly gaps or provisional assumptions.

## 1. Direct Description of Outputs

This document provides the five evidence categories, three-product benchmark, domain workflow, variables, data entities and timeliness, risks, mental model, provisional persona/journey, and validation gaps.

## 2. Research Goals and Questions

- **Assumptions to validate**: a single sentence plus halo can teach the phase mapping; a controller-only user can finish all menu paths; pause/resume remains perceptually continuous; optional local history does not imply performance or efficacy.
- **Methods used now**: requirement analysis, official PICO-rule review, first-party competitor documentation benchmark.
- **Missing methods**: participant interviews, headset usability tests, audio listening tests, and device comfort tests; these are validation plans, not completed evidence.
- **Proposed sample**: five Chinese-reading PICO users spanning novice/experienced XR use, including at least one user who relies on a controller for all interaction. This is a plan, not an observed sample.

## 3. Five Categories of Research Evidence

| ID | Category | Evidence / gap claim | Source | Type | Scope | Confidence | Observed | Validation plan |
|---|---|---|---|---|---|---|---|---|
| E-M1 | market | In the sampled Apple Watch product, first-party documentation describes expanding/shrinking visual guidance, selectable duration/rate, haptics, and explicit early end; this is one product claim, not a category prevalence claim. | [Apple Watch Mindfulness guide](https://support.apple.com/en-lamr/guide/watch/apd371dfe3d7/watchos) | official (vendor first-party) | documented wearable same-task features only; no hands-on usability evidence | high for documented feature, low for experience quality | 2026-08-12 | Re-check first-party docs and run hands-on path observation before making interaction-quality claims. |
| E-M2 | market | The sampled Breathwrk first-party page describes timed inhale/exhale/hold exercises, music/haptics/customization, records, challenges, and health/performance positioning; TideBeacon's user boundary excludes scoring and efficacy framing. | [Breathwrk first-party site](https://www.breathwrk.com/) | official (vendor first-party) | documented mobile product claims only | high for documented claims | 2026-08-12 | Legal/content review; hands-on evidence required before any usability comparison. |
| E-M3 | market | Accessible TRIPP first-party pages describe XR immersion, 100+ immersive worlds, soundscapes, AI-powered breathwork/coaching, PICO support, and activity/personalized-stat tracking. Adjustable guidance/session length is an explicit evidence gap in this active source set. | [TRIPP VR](https://www.tripp.com/trippvr/); [TRIPP device page](https://www.tripp.com/device/); [TRIPP help center](https://help.tripp.com/en/articles/5547480-what-is-tripp) | vendor first-party | documented TRIPP claims only | high for listed claims; low for comparative value | 2026-08-12 | Re-check accessible sources and conduct hands-on XR comparison before any superiority/usability claim. |
| E-U1 | user | The intended user needs the rhythm to be understandable without a long explanation and needs pause/exit/restart/controller completion. | user's original requirement | user_supplied | TideBeacon acceptance | high | 2026-08-12 | Five-person comprehension and controller task test. |
| E-U2 | user | Unknown: actual preference distribution for 2/4/6 minutes, three scenes, or specific BreathPattern presets. | none | assumption/evidence gap | target audience | low | 2026-08-12 | Instrument opt-in local aggregate only if later authorized; otherwise interview participants. |
| E-D1 | domain | The domain clock contains four ordered phases: inhale expansion, short hold, exhale contraction, short hold; phase, sound, countdown, and visual pose must derive from one timeline. | user's original requirement | user_supplied | all active sessions | high | 2026-08-12 | Deterministic state-machine tests at multiple simulated refresh rates. |
| E-D2 | domain | Completion is binary and non-evaluative: show only `完成一次练习`, with an optional local record; no breathing measurement or score exists. | user's original requirement | user_supplied | completion/data semantics | high | 2026-08-12 | Copy audit and localStorage path test. |
| E-P1 | platform | Registry rules require legal Full/Shared Space combinations and explicit Stage entry/stable exit. | `official-rules.json` v2.2.0: PICO-SPACESTATE-001 (`official`), -002 (`official+comfort`), PICO-STAGE-001 (`project+comfort`) | official registry with mixed declared provenance; project+comfort item is not elevated to purely official | PICO spatial design | high for registry requirement | skill v0.4.1, 2026-08-12 | Downstream architecture review and device validation. |
| E-P2a | platform | Registry rules require Controller Fallback, Reduce Motion, text scaling and stable exit. | `official-rules.json` v2.2.0: PICO-ACCESS-001 (`safety`), -002 (`project`), -003 (`safety`), -004 (`safety`) | official registry with declared safety/project provenance | PICO spatial design contract | high for registry requirement | skill v0.4.1, 2026-08-12 | Design coherence review; physical validation not performed. |
| E-P2b | platform | When the applicable PICO window-sizing method is invoked, interaction targets are ≥56dp, body text ≥12dp, and core content uses the 65°×40° clear-FOV check. | PICO-WINDOW-SIZING-005/006 (registry provenance `official-capability+project`) + `spatial-window-sizing-methodology.md` | official capability + project method; conditional, no container form selected here | high for skill gate | skill v0.4.1, 2026-08-12 | Apply only after architecture derivation; device readability remains unverified. |
| E-P2c | platform | Registry safety rules prohibit authored automatic camera movement and continuous full-screen flashing. | `official-rules.json` v2.2.0: PICO-MOTION-001/002 (both declared source `safety`) | official registry, safety provenance | TideBeacon motion content | high | skill v0.4.1, 2026-08-12 | Motion inventory review; headset comfort remains unverified. |
| E-P3 | platform | Unknown: actual headset frame pacing, spatial-audio behavior, controller precision, and physical readability for TideBeacon. | none | assumption/evidence gap | device runtime | low | 2026-08-12 | Downstream PICO build plus headset test; Web preview cannot close this gap. |
| E-S1 | safety | No fast camera movement, sensor capture, scoring, diagnosis, or therapeutic language is allowed; authored content additionally adopts a project threshold of no more than three flashes in any one-second period. The numeric flash threshold is WCAG-derived and is not represented as a PICO rule. | user's requirement; PICO-MOTION-001/002; [WCAG 2.2 SC 2.3.1](https://www.w3.org/WAI/WCAG22/Understanding/three-flashes-or-below-threshold) | user_supplied + official/external standard | all scenes and copy | high | 2026-08-12 | Motion/copy/permission audit; enumerate every luminance-changing effect. |
| E-S2 | safety | Unknown: which pattern phase lengths are comfortable for all users; no universal comfort claim is supported. | none | assumption/evidence gap | rhythm presets | low | 2026-08-12 | Test presets with participants; always expose pause/exit and avoid prescriptive wording. |

- **Source conflict handling**: competitor health/wellness or biofeedback positioning is not transferred to TideBeacon; the user's explicit non-medical, no-sensing boundary wins.
- **Market-evidence boundary**: evidence informs coverage and differentiation only; it cannot choose TideBeacon's layout, state graph, components, visuals, color, or motion.

## 3A. Competitive Benchmark

| # | Competitor / platform | Functional needs | Interaction experience | Visual experience observation only | Spatial-capability usage | Source / observed |
|---|---|---|---|---|---|---|
| CB1 | Apple Watch Mindfulness / wearable 2D | Documented: expanding/contracting cue, 1–5 minute duration, rate/haptic settings, early end. Missing: spatial depth. | Documented steps use watch touch/options; no hands-on learning-cost evidence was collected. Migration risk: watch input differs from gaze/controller. | First-party page shows a compact watch presentation; hierarchy/readability quality is not independently evaluated and must not be reused. | No spatial direction/depth claim in the cited flow; scale/time and haptic guidance are documented. | [Apple Support](https://support.apple.com/en-lamr/guide/watch/apd371dfe3d7/watchos), vendor first-party, 2026-08-12 |
| CB2 | Breathwrk / mobile 2D | Documented: many timed exercises, voice/sound/haptics/duration, classes, records/challenges; health/performance claims exceed TideBeacon boundary. | First-party page establishes mobile app customization/catalog features; input path, learning cost and usability are gaps without hands-on review. | Marketing page shows broad content branding; density, comfort and readability quality are gaps. Do not reuse visuals. | No XR spatial direction/depth is documented on the cited page; platform migration risk remains. | [Breathwrk](https://www.breathwrk.com/), vendor first-party, 2026-08-12 |
| CB3 | TRIPP / XR + mobile | Documented: immersive worlds, soundscapes, AI-powered breathwork/coaching, activity/personalized statistics; adjustable guidance/session length is a gap. | Accessible first-party pages establish XR/mobile categories and PICO support; controller flow, setup and learning cost are gaps. | Immersive/content-rich positioning is documented; hierarchy and comfort quality are not independently evaluated. | Immersive environment and 3D/binaural sound are documented; a deterministic shared phase clock is unknown. | [TRIPP VR](https://www.tripp.com/trippvr/); [device page](https://www.tripp.com/device/); [help center](https://help.tripp.com/en/articles/5547480-what-is-tripp), vendor first-party, 2026-08-12 |

### Per-product absorb / avoid

| # | Strength worth absorbing at needs/opportunity layer | Weakness / anti-pattern to avoid |
|---|---|---|
| CB1 | Vendor-documented expansion/contraction, duration/rate settings, and early end are coverage references for this sample. | Project risk hypothesis: TideBeacon must not import health-data semantics or wrist-sized density; this is not attributed as an Apple weakness. |
| CB2 | Vendor-documented timed phases and sound/haptic/duration customization establish configurability at the needs layer. | Vendor-documented challenges and health/performance framing are outside TideBeacon scope. Any catalog-density or efficacy-copy concern is a bounded TideBeacon risk, not a measured Breathwrk flaw. |
| CB3 | Vendor-documented immersive environments, guidance and sound establish XR opportunity categories. | Vendor-documented AI and session data are outside scope. Spectacle and forced motion are general TideBeacon risks from its own safety contract, not claims about TRIPP. |

- **Our differentiation opportunities within this three-product sample**: explore one spatial lighthouse/halo focus; bind light, sound, countdown, and particle-cycle acknowledgment to one monotonic timeline; preserve privacy through no sensing, account, network, or score; freeze every channel on pause; preserve controller and system-back exits. These are hypotheses derived from user requirements and E-P1/E-P2a–c, not proven market superiority and not competitor UI.
- **Sample/gap note**: three vendor first-party adjacent products are covered. No current PICO-native same-task competitor evidence was retrieved. Before claiming PICO-native differentiation, perform a bounded PICO Store/official catalog search and hands-on review of any matching product; if none is found, record query/date/region and formally restrict claims to this sample. Until then, PICO-native superiority claims are prohibited.
- **Absorption boundary**: needs/opportunities only. No layout, state graph, component combination, or visual style is copied.

## 4. Domain Model

- **Domain workflow**: orient to phase mapping → choose duration/environment/pattern → explicitly start → follow repeated four-phase cycles on one clock → optionally pause/resume → complete or confirm restart/exit → optionally persist a local completion record.
- **Decision variables**: `durationMinutes ∈ {2,4,6}`; `scene ∈ {sea,cloud,dune}`; `patternId`; four phase durations; session state; elapsed active time; cycle index; Reduce Motion; text scale; controller focus; local-record choice.
- **Data entities and timeliness**:

| Entity | Required fields | Timeliness / source | Trust and fallback |
|---|---|---|---|
| `BreathPattern` | id, Chinese label, inhaleMs, holdInMs, exhaleMs, holdOutMs | static local configuration; read before start | invalid/zero duration → clock must not start and a render-safe recovery outcome must remain available; structure is derived later |
| `SessionClock` | startMonotonicNanos, pausedAccumulatedNanos, pausedAtMonotonicNanos?, activeElapsedNanos, phase, phaseProgress, cycleIndex | monotonic nanosecond tick source only; `activeElapsedNanos = currentMonotonicNanos - startMonotonicNanos - pausedAccumulatedNanos`; while paused use the frozen pause tick; wall-clock is prohibited here | missing/NaN/regression → freeze, mute, show safe error return |
| `SessionSelection` | duration, scene, pattern | local in-memory; changes before start or via confirmed restart | invalid value → default sample only in preview; runtime returns to selection |
| `LocalRecord` | id, completedAt, durationMinutes, sceneLabel, patternLabel | localStorage/local device only; written only after completion and opt-in | storage denied/full → completion remains valid; show `未保存记录` |
| `AppLifecycle` | foreground/systemPaused/resumeEpoch | event-driven | system pause freezes clock/audio/visual; resume audio gain ramps smoothly |
| `AccessibilityPrefs` | reduceMotion, textScale, controllerMode | local preference; capability required by E-P2a | concrete defaults are a downstream decision; missing preference must preserve capability and minimum readability, then be validated in accessibility review |

- **Specialized risks**: independent clocks drift; frame-count phase advancement changes with refresh rate; app pause causes time jump; resuming sound clips; incomplete loop emits particles; system Back in a paused condition restarts by mistake; invalid data crashes rendering; optional history becomes implicit scoring; first view leaks controls.
- **User mental model**: “the halo is the breath cue; larger means inhale, smaller means exhale; pause stops the whole experience; completion means only that time elapsed through the selected sequence.”
- **Mature patterns worth keeping**: direct scale-to-phase mapping, explicit duration/rhythm controls, stable early exit, audio/haptic alternatives where appropriate.
- **Anti-patterns**: dashboard/curriculum catalogs, biometrics, streaks/scores, medical outcomes, moving camera, bright full-field flashes, independent audio/countdown clocks, hidden system-back behavior.

## 5. Provisional Persona

### Persona P-01: short-session Chinese-reading PICO wearer (research hypothesis)

| Claim | Source / status | Confidence | Validation |
|---|---|---|---|
| Target is a PICO user presented with Chinese cadence copy; actual reading proficiency is unknown | SRC-USER R1/R2 target requirement, not observed trait | high as target requirement; low as actual-user trait | screen Chinese reading fluency in recruitment |
| Product must support immediate cadence understanding and 2/4/6-minute completion; actual motivation is unknown | SRC-USER R5/R14 acceptance requirement, not observed motivation | high as product requirement; low as user motivation | comprehension interview and full-flow task test |
| Can complete menus with controller | SRC-USER R10, required capability rather than observed habit | high as requirement, unknown as usability | controller-only task test |
| Stationary posture; exact seated/standing distribution unknown | assumption from motion-safety context; no user evidence | low | recruit both stationary seated and standing participants; device comfort test |
| XR experience, age, occupation and frequency are unknown | evidence gap | low | collect screening data without inventing demographics |
| Likely pain points are phase ambiguity, A/V drift, resume discontinuity and unclear exit | project-risk hypothesis from R4/R7/R9/R14 | medium | observe errors and confidence in usability test |
| Reduce Motion, text scaling, non-color cues and controller fallback are acceptance needs | E-P2a plus PICO-COLOR-001 (`safety`) | high for registry requirement, unknown user preference | accessibility review + device task test |
| Verbatim anchor | SRC-USER R4: `光扩张时吸气，光收回时呼气。` | high | exact-string audit |

## 6. Journey Map

| Stage | Entry | First hands-on | Selection | Active rhythm | Pause/interruption | Completion/exit |
|---|---|---|---|---|---|---|
| User-product fact (SRC-USER R2/R4–R11, high as requirement; validate implementation by preview/task test) | exact first-visible set R4 | product source prescribes no action | choose duration/scene R5 | visual/audio cadence R2/R6/R7 | pause/resume/restart/exit R9 | exact completion/local option R11 |
| Host-preflight fact (SRC-HOST R16–R18, high as project gate; validate itemized manifest/state tests) | R18 forbids missing coverage but adds no product UI | no entry-action claim | observable decision coverage R18 | single-timeline coverage is not host-derived; N/A | system interruption + paused Back R16/R18 | real local binding/activity R18 |
| Registry constraint (E-P1/E-P2a/E-P2c, high as skill gate; validate exact-rule review) | N/A | explicit Stage entry/stable exit E-P1 | controller fallback E-P2a | no automatic camera/continuous flash E-P2c | stable exit/Reduce Motion E-P2a | N/A |
| Assumption / hypothesis (low–medium) | mapping may be understood quickly | continuation mapping is unknown | decision presentation can stay low-load | one focus may reduce ambiguity | pause/resume is likely trust low point | binary completion may prevent score inference |
| Assumption risks (medium) | extra UI leakage | target ambiguity | setup load | drift/flash/motion | jump/pop/wrong Back route | storage failure/efficacy implication |

Validation: user-product and preflight rows are acceptance inputs; registry rows are checked against exact rule IDs. Every assumption cell is tested with a five-participant comprehension/controller study plus independent preview observation, and unsupported behavior is discarded.

- **Emotional low point to validate**: pause/resume uncertainty; it can destroy trust immediately even if the rest of the experience is attractive.

## 7. Key Findings

| # | Finding | Evidence | Confidence | Design implication |
|---|---|---|---|---|
| F1 | One sampled product documents expansion/contraction guidance; its teachability in TideBeacon is unproven. | E-M1 + user R4 | high for feature precedent, low for TideBeacon usability | preserve the user-required exact instruction and validate comprehension |
| F2 | XR value must be tested through depth/direction/synchronized ambiance rather than assumed. | E-M3 + E-P1/E-P2a/E-P2c | medium | test Stage necessity against a 2D counterfactual |
| F3 | Within this three-product sample, privacy/non-medical restraint contrasts with documented health/performance, AI, or tracking features; market differentiation is a hypothesis, not a proven advantage. | E-M2/M3 + user R2/R3/R11 | medium | zero permissions, no sensing, no outcome language; avoid superiority claims |
| F4 | One time source is the core engineering and trust decision. | E-D1 + R7/R9/R12 | high | derive phase, visual pose, gain and countdown from monotonic elapsed active time |
| F5 | System lifecycle is part of the primary flow, not an edge case. | R9/R12/R16/R18 | high | distinct `systemPaused`; resume does not consume hidden elapsed time |

## 8. Posture and Field-of-View Insights

- **Usual posture**: stationary use is PM assumption A2, not a user-supplied or observed trait; seated/standing distribution is unknown and requires recruitment/device validation.
- **Arm range**: controller must support indirect targeting; physical comfortable reach is device-validation evidence gap.
- **Central FOV**: no Stage-wide FOV number is asserted here. If a Shared Space WindowContainer is later derived, apply E-P2b's 65°×40° core and 85°×55° secondary window checks; Stage composition requires separate downstream comfort reasoning and device validation.
- **Fatigue threshold**: product durations are 2/4/6 minutes; comfort at each duration is not yet validated.

## 9. Eye-Hand / Controller Usability

- Conditional sizing-method constraint: if the applicable WindowContainer method is derived, targets are ≥56×56dp under E-P2b. Controller fallback is required by E-P2a. Gaze/focus feedback is a downstream interaction hypothesis and must be separately derived/tested; it is not claimed as sourced by these rules.
- Measured hit rate: evidence gap; no device test performed.
- Interaction-treatment hypothesis: applicable actionable targets need observable focus and press feedback; disabled/error states exist only where the downstream task/data model derives them. Exact visual/sound-free redundancy is a downstream accessibility hypothesis to validate with controller traversal and non-audio feedback review.

## 10. Duration Baseline

| Decision type | Current anchor | Source / status |
|---|---|---|
| First-view comprehension | target ≤5 s | project acceptance hypothesis; validate with 5 users |
| Selection completion | target ≤20 s | project efficiency hypothesis; validate with controller task test |
| Pause feedback | visible/audible freeze ≤100 ms from event handling | engineering target; device validation required |
| Resume gain ramp | 600 ms | project motion/audio target to validate by listening test |

## 11. Motion, Fatigue, Safety

- Risk scenarios: automatic camera movement, full-field flash, particle bursts before a completed loop, fast large-scale displacement, phase discontinuity, audio pop on resume.
- High Motion label: **no**, because High Motion content is prohibited rather than accepted.
- Cadence: 2/4/6 minutes selectable; no recommendation or therapeutic rest cadence is claimed.
- Device boundary: comfort, fatigue, occlusion, physical target precision, and audio artifacts remain `not_performed` until headset validation.

## 12. Minimum Completeness Gate

| Check Item | Evidence Anchor | Verdict |
|---|---|---|
| Five evidence categories | §3 E-M/E-U/E-D/E-P/E-S | pass |
| Three-product benchmark with four dimensions | §3A CB1–CB3 | pass |
| Domain model | §4 | pass |
| Persona/journey/findings source status | §5–§7 | pass |
| Quantitative/safety items are values or explicit gaps | §8–§11 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass |

## 13. Delivery and Recipients

Stage 2 evidence and domain model pass their content gate. Participant and device evidence gaps remain explicit and do not authorize runtime claims.
