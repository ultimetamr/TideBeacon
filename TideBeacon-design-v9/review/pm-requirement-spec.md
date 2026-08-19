# Spatial App Requirement Spec · TideBeacon

> Role: `product_strategist` | Active revision: 6 | Source revisions: PM r5, UXR r3 | Change Request CR-01.3; PM r6 is produced after UXR r3

## 0. Reasoning Guidance

This revision freezes only product intent from the original user source. Layout, components, visual direction, container architecture, and delivery status remain undecided.

## 1. Direct Description of Outputs

This active revision freezes the intent definition, governed assumptions, research-anchored quality contract, and complete requirement traceability.

## 2. Background and Problem

- **One-sentence requirement description**: TideBeacon is a PICO Spatial SDK breathing-rhythm experience that uses only coordinated light, a low-poly coastal environment, spatial audio, and a countdown to help a user follow a chosen rhythm without medical, diagnostic, scoring, or therapeutic claims.
- **Target users**: Chinese-speaking PICO users seeking a short, low-cognitive-load visual and spatial-audio rhythm session; no clinical user segment is asserted.
- **Use scenarios**: a quiet indoor session in which the user can select a duration and scene, enter a spatial practice, pause or exit safely, and optionally save a local completion record.
- **Wearing posture**: sitting or standing in place; moving use is outside scope.
- **Frequency and duration**: user-selectable 2, 4, or 6 minutes; frequency is user-determined and unknown.
- **Preliminary judgment of spatial necessity**: a lighthouse at distance, halo expansion/contraction, spatialized sea ambience, and a surrounding low-poly environment can make rhythm phase legible through direction, distance, depth, and temporal change while keeping the user's camera stationary.

## 3. Key Moment

- **The moment a screen cannot achieve**: the user reads the inhale/exhale phase from a distant lighthouse halo expanding and returning while the matching spatial-audio envelope and countdown remain phase-locked around a calm coastal scene.
- **Placement on the immersion spectrum**: the brief explicitly asks for a Stage app; the exact Full Space immersion tier and entry confirmation are deferred to spatial-structure derivation.
- **Entry path**: first entry is a restrained orientation moment, followed by user choices and an explicit user-controlled transition into practice; the exact container sequence is not decided in this stage.

## 4. Product Research Baseline

| Dimension | Content | Source |
|---|---|---|
| Competitor feature matrix | UXR r3 benchmarks four adjacent products across function, interaction, visual, and spatial dimensions; adoption is bounded to needs/opportunities. | UXR r3 §3A C1–C4 |
| Decision duration baseline | Active session duration is user-fixed at 2/4/6 minutes; comprehension/setup timing has no participant baseline and remains a governed validation target. | UXR r3 §10; PM A11 |
| Safety and comfort conventions | Original source prohibits fast camera movement and strong flashing; official PICO rules require Reduce Motion, controller fallback, and stable exit. | user source; official-rules.json |

## 5. Intent Definition (frozen items)

- **Product identity**: TideBeacon
- **Android package**: `com.pico.swan.tidebeacon`
- **Domain / sub-domain**: personal rhythm guidance / non-medical breathing-tempo experience.
- **Risk level**: medium comfort and wording risk; low data/privacy risk because no microphone, breathing detection, sensor inference, network account, or medical data is requested.
- **Default space**: undecided until Stage 9; the request for a Stage makes Full Space a candidate, not a Stage-1 architectural fact.
- **Core scenario list**: first-entry orientation; choose 2/4/6 minutes; choose low-poly sea/cloud/dune scene; choose a configurable BreathPattern; practice with synchronized visual/audio/countdown; pause and resume; exit with confirmation; restart; complete and optionally save a local record.
- **BreathPattern intent**: ordered phases `inhale-expand → short hold → exhale-contract → short hold`; exact phase durations are configurable and not supplied.
- **Exact first-entry sentence**: “光扩张时吸气，光收回时呼气。”
- **Exact completion copy**: “完成一次练习”; no additional mandatory completion wording.
- **Data / AI / sensors / permissions**: no AI, microphone, breathing detection, scoring, sensor inference, or therapeutic classification; only session configuration, timeline state, and optional local record. Spatial-audio playback is required. Runtime permissions are not assumed.
- **Collaboration**: none.
- **Safety and wording boundary**: not medical or diagnostic; no therapeutic wording; no claim that the app detects breathing or improves health.
- **Interaction boundary**: all menus must be controller-operable; gaze/pinch support may be designed but cannot replace controller coverage.
- **Validation intent**: timeline/state-machine tests across refresh rates and app pause/resume; capture scene-selection and practice screenshots; runnable 2-minute flow.

## 6. Assumptions List

| # | Assumption / unknown | Confidence | Impact | Validation Plan |
|---:|---|---|---|---|
| A1 | Target users can understand the exact one-sentence instruction without a longer tutorial. | medium | High: first-view simplicity and task comprehension. | Comprehension test with Chinese-speaking first-time PICO users; ask them to explain inhale/exhale mapping before continuing. |
| A2 | Sitting and standing-in-place are the dominant postures. | medium | Medium: comfort zone and controller reach. | Observe at least five representative users in both postures during device validation. |
| A3 | BreathPattern phase durations will ship with at least one calm default and optional alternatives; exact seconds are unknown. | low | High: timeline, copy, and audio envelope. | Product owner supplies allowed phase sets before runtime implementation; verify total cycle math in tests. |
| A4 | The spatial-audio content can be produced procedurally or from licensed loop assets; asset identity is unknown. | medium | Medium: prototype can model behavior but not final audio character. | Audio design review and license audit before implementation handoff. |
| A5 | A completion record may store only completion time, chosen duration, scene, and rhythm locally. | medium | Medium: privacy and observable save result. | Confirm data-minimization policy and test real `localStorage` success/failure in the Web preview. |
| A6 | First entry means the initial product scene before any user input, not every subsequent launch. | medium | Medium: state persistence behavior. | Stakeholder review; define first-run reset behavior during implementation planning. |
| A7 | The user directly requires both explicit and lifecycle pause to freeze all channels; the remaining assumption is that one monotonic timeline plus PauseSnapshot is the implementation mechanism. | medium | High: mechanism affects testability and continuity, while freeze behavior itself is mandatory. | Verify the mechanism through deterministic state-machine tests; permit another single-authority mechanism only if it proves identical no-skip/pop behavior. |
| A8 | The device validation target and PICO OS / Spatial SDK version are not supplied. | low | High: final material, controller, and performance behavior cannot be claimed. | Downstream implementation records exact SDK/device matrix; this design package marks device validation not performed. |
| A9 | Local record failure must be visible but non-blocking; exact failure copy is not supplied. | medium | Medium: completion remains calm and recoverable. | Copy review and preview QA of success/failure paths. |
| A10 | The exact procedural implementation method, polygon budget, and audio/texture budget for the user-required placeholder sky/sea/lighthouse are unknown. | medium | Medium: affects performance and asset handoff, not the requirement to use procedural placeholders. | Confirm downstream asset budget and render approach; validate screenshots and performance on target hardware. |
| A11 | First-time setup ≤30s, returning setup ≤15s, and a 350ms resume audio fade are provisional acceptance targets, not observed baselines. | low | Medium: affects perceived efficiency/continuity but must not override comfort. | Measure moderated setup and device audio; adjust if comprehension falls below 80%, gain discontinuity is audible, or comfort review prefers a 300–500ms range. |
| A12 | At most one pre-practice primary window is a provisional attention-cost constraint, not an official limit. | medium | Medium: bounds visual competition before Full Space entry. | Stage 9 must compare zero/one/multiple windows; adjust only if another window has an independent task and central-FOV/attention evidence. |

## 7. Quality Contract

- **Required user outcomes**:
  1. On first entry, see exactly three product elements: one distant lighthouse, one halo, and the exact sentence “光扩张时吸气，光收回时呼气。”
  2. Choose exactly one duration from 2/4/6 minutes, one procedural low-poly scene family from sea/cloud/dune, and one configurable BreathPattern before practice.
  3. Explicitly enter a calm spatial practice whose light, spatial audio, countdown, and phase labels derive from one authoritative timeline.
  4. Pause and lifecycle-suspend without phase, animation, audio, or countdown advancement; resume at the same timeline position with a smooth volume fade and no skip/pop.
  5. Use controller input for every menu and complete pause, exit, restart, and rhythm-choice paths; gaze/pinch may supplement, not replace it.
  6. Complete a runnable 2-minute flow, then see only the exact completion message “完成一次练习” plus an optional local-record action/result.
  7. If saving is chosen, expose honest success or failure without blocking completion. Web preview acceptance is a real `localStorage` write/readback only for Web behavior; downstream PICO/Android acceptance separately requires a runtime local-persistence write/readback on the target build.
- **Success / efficiency criteria**: first-entry instruction comprehension target ≥80% in a small moderated test without added tutorial copy; provisional setup targets ≤30 seconds first-time / ≤15 seconds returning and provisional 350ms resume audio fade are governed by A11 and may adjust under its failure criteria; pause/resume timeline discontinuity 0 logical milliseconds in deterministic tests; selected active duration accurate to ±1 frame at 60/72/90/120Hz simulations; all menu actions controller-triggerable; two requested screenshots captured from real preview paths.
- **Risks and must-not-fail items**: no medical/diagnostic/therapeutic wording; no microphone, breathing detection, sensor inference, score, streak, or health metric; no fast camera movement, forced camera motion, or strong/full-field flashing; cycle accents only after complete cycles; exit and restart require an explicit confirmation that preserves the exact prior state when cancelled; local-save failure is honest and recoverable; app pause/background never advances the timeline.
- **Preference for default number of visible primary windows**: provisional maximum one primary WindowContainer before Full Space entry, governed by A12; Stage 9 must independently compare zero/one/multiple windows and may revise only with task/attention evidence. Stage is not counted as a window.
- **Preference for domain-specialized components**: components must expose the rhythm-domain facts that matter—phase, progress, duration/scene/pattern choice, pause snapshot, cycle completion, and local-record result—without turning the experience into a generic dashboard or a content catalog.
- **Preference for real-time data trust**: there is no network real-time data. Timeline state is authoritative and monotonic; derived channels must declare that source. Local-record outcome must report real success/failure, never optimistic fake success. Unknown or invalid bindings need binding-specific fallback/error behavior.
- **PICO platform and spatial-design hard constraints**: legal Full/Shared Space combination; explicit Stage entry value and stable exit; controller fallback, Reduce Motion, text scaling, color-independent semantics, and stable exit; 56×56dp minimum targets and 12dp body floor; no automatic camera movement or continuous flashing; all spatial motion has comfort/performance fallbacks; device-only facts remain unvalidated.
- **Originality requirement**: absorb only the adjacent-market strengths of immediate phase legibility, bounded duration/pacing choice, explicit early exit, and meaningful spatial sound/worldscapes; avoid catalog overload, sensor/health coupling, health claims, score pressure, and gratuitous visual stimulus. Differentiation is one auditable rhythm timeline expressed through a distant beacon, environment, and sound—not a competitor-derived interface. Evidence: UXR r3 §3A C1–C4, absorb/avoid table, and differentiation summary.
- **Design / readability / downstream-implementation acceptance plan**: six role documents must pass their minimum-completeness gates; all review gates use fresh isolated reviewers and exact revisions; at least three concepts and 2–3 visual directions are compared; every core component keeps the full eight-section contract; Stage 13 creates its complete manifest before implementation; every state/transition/element/binding/variant/component state/responsive tier/Reduce Motion row has a stable selector and binding-specific normal/fallback/error evidence; transition controls dispatch exact IDs; confirmation cancellation preserves prior state; Stage 14 serves via supported localhost HTTP, triggers real paths independently in the in-app Browser, and captures scene-selection and practice screenshots; Web validation never claims device validation.

## 8. Requirements Traceability

| ID | Requirement | Implementation Node | Validation Method |
|---|---|---|---|
| R1 | PICO spatial Stage app named TideBeacon | interaction §7 container architecture | legality and entry/exit review |
| R2 | package `com.pico.swan.tidebeacon` | downstream-only package fact; preserved as intent | document inspection; no Android output here |
| R3 | visual + spatial-audio Chinese rhythm experience | practice state + visual/audio components | preview semantics; device audio later |
| R4 | non-medical, non-diagnostic, no therapeutic wording | wording boundary and copy inventory | copy lint/review |
| R5 | no breathing detection, mic, scoring | intent permissions and negative list | permission/data-flow audit |
| R6 | first entry only lighthouse, halo, exact sentence | first-entry state | DOM/product-element count = 3 and exact-string check |
| R7 | choose 2/4/6 minutes | session selection task | trigger each option; selection persists/cancels correctly |
| R8 | choose low-poly sea/cloud/dune | scene selection task | trigger all scene variants; capture scene-selection screenshot |
| R9 | configurable four-phase BreathPattern | rhythm selection + pattern entity | test configured durations and phase ordering |
| R10 | one timeline for visual/audio/countdown | RhythmTimeline authority | refresh-rate and binding-source tests |
| R11 | stars/particles only after complete cycles | CycleAccent rule | partial/full-cycle boundary tests |
| R12 | no fast camera or strong flashing | motion negative list | motion review + Reduce Motion assertions |
| R13 | pause, exit, restart, rhythm choice | transitions and confirmation paths | trigger exact transition IDs; cancel/confirm checks |
| R14 | pause freezes all channels | PauseSnapshot + timeline | explicit and lifecycle pause tests |
| R15 | resume smoothly fades volume | resume envelope | 350ms gain-ramp logic assertion; device audio later |
| R16 | all menus controller-operable | controller mapping | controller path matrix |
| R17 | completion exact copy only + optional local record | completion state | exact-copy and visible-element check; local write success/failure |
| R18 | local record is real and observable | LocalPracticeRecord | Web: actual `localStorage` write/readback + forced failure; downstream runtime: separate PICO/Android persistence write/readback evidence |
| R19 | timeline/state tests across refresh rates | test requirements | 60/72/90/120Hz deterministic simulation plan |
| R20 | app pause/resume tests | lifecycle transition requirements | snapshot/restore scenarios |
| R21 | procedural placeholder sky/sea/lighthouse | user-supplied requirement anchored directly to original source; exact budget governed by A10 | asset inventory inspection and target-device profile downstream |
| R22 | understandable without long instructions | first-entry comprehension outcome | moderated comprehension target ≥80% |
| R23 | no skip/pop on resume | timeline + audio envelope | exact-position and continuous-gain assertions |
| R24 | runnable 2-minute flow | happy path | end-to-end preview flow and downstream runtime acceptance |
| R25 | capture selection and practice screenshots | Preview QA evidence | Browser screenshots from real paths |
| R26 | confirmation cancel preserves exact prior state | confirmation snapshot contract | cancel each dialog and compare state snapshot |
| R27 | binding-specific normal/fallback/error | component bindings and Preview Manifest | three-mode evidence per binding |
| R28 | every declared state/variant/stack triggerable | Preview Manifest and maps | stable selector + trigger + actual result per row |
| R29 | exact transition-ID dispatch | transition table implementation | event log equals declared transition ID |

## 9. Minimum Completeness Gate

| Check Item | Minimum Pass Condition | Evidence Anchor | Verdict |
|---|---|---|---|
| Background and intent | intent fields are complete or governed as assumptions | §2–§5 | pass |
| Assumption governance | every unknown has confidence, impact, validation | §6 | pass |
| Quality contract | all nine items complete and testable | §7 | pass |
| Requirements traceability | every mandatory outcome mapped | §8 (R1–R29) | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass |

## 10. Delivery and Recipients

- **Stage-1 deliverable**: frozen intent definition and governed assumptions.
- **Recipients**: research analyst, product strategist for Stage 3, task/decision designer after the evidence gate.
