# User Research Report · TideBeacon

> Role: `research_analyst` | Active revision: 3 | Source revision: PM r3 | Produced before PM r4/r5 in CR-01.1; no circular source dependency

## 0. Reasoning Guidance

This report provides evidence and a domain model only. Competitor observations are bounded to needs and opportunity discovery; no competitor layout, state graph, component set, visual style, or motion pattern is adopted.

## 1. Direct Description of Outputs

Five evidence categories, a four-product adjacent benchmark, an explicit evidence-gap register, and a rhythm-session domain model.

## 2. Research Goals and Questions

- **Assumptions to validate**: first-sentence comprehension; acceptable controller load; suitable phase defaults; pause/resume continuity perception; calmness and readability of a low-poly Full Space environment; meaning and privacy of an optional local record.
- **Methods completed**: review of the original user source, official PICO rules bundled with the skill, and current first-party product documentation for adjacent breathing / XR experiences.
- **Methods not yet completed**: interviews, on-device usability testing, hands-on competitor testing, telemetry, and comfort observation.
- **Sample description**: no participant sample. This is a secondary-research stage; all participant-dependent claims remain gaps or assumptions.

## 3. Five Categories of Research Evidence

| ID | Category | Evidence / Gap (claim) | Source | Source Type | Scope | Confidence | Observation Time | Validation Plan |
|---|---|---|---|---|---|---|---|---|
| M1a | market | Apple Breathe publicly documents duration choice, adjustable breath rate/haptics, and grow/shrink pacing. | https://support.apple.com/en-lamr/guide/watch/apd371dfe3d7/watchos | external (first-party) | Apple Watch public guide | high | accessed 2026-08-13 | hands-on current regional build before market claims |
| M1b | market | TRIPP publicly documents adjustable BREATHE guidance and immersive worldscapes/soundscapes. | https://www.tripp.com/support/ | external (first-party) | TRIPP public XR offer | medium | accessed 2026-08-13 | hands-on supported XR build before interaction claims |
| M1c | market | Breathwrk publicly documents configurable voice, sound, haptics, and duration. | https://www.breathwrk.com/ | external (first-party) | Breathwrk public offer | medium | accessed 2026-08-13 | hands-on current mobile build before interaction claims |
| U1 | user | Users need the inhale/exhale mapping to be understood from a single exact Chinese sentence on first entry. This is a direct requirement, not observed comprehension evidence. | original user source | user_supplied | TideBeacon first entry | high for requirement; low for comprehension outcome | 2026-08-13 | moderated comprehension test before users advance |
| U2 | user | Target-user age, XR expertise, visual needs, hearing needs, dominant hand, and preferred posture distribution are unknown. | none | assumption / evidence gap | target population | low | 2026-08-13 | recruit 5–8 Chinese-speaking PICO users across novice/experienced and sitting/standing |
| U3 | user | The product must use a distant lighthouse, halo, spatial audio, and one shared timeline to guide phase; this is a direct required spatialization, not a measured effectiveness claim. | original user source | user_supplied | TideBeacon spatial practice | high | 2026-08-13 | verify implementation traceability and later device comfort; do not infer health effect |
| U4 | user | Explicit pause and app lifecycle pause/resume must freeze animation/audio/countdown and resume without skip/pop. | original user source | user_supplied | TideBeacon active practice | high | 2026-08-13 | deterministic refresh-rate and lifecycle tests plus device audio observation |
| D1 | domain | A pacing experience can represent inhaling as growth and exhaling as contraction; Apple documents this explicit animation mapping. | https://support.apple.com/en-lamr/guide/watch/apd371dfe3d7/watchos | external (first-party) | adjacent 2D wearable breathing guidance | high | accessed 2026-08-13 | confirm TideBeacon mapping comprehension independently; do not copy presentation |
| D2a | domain | Apple publicly documents duration and breath-rate adjustment. | https://support.apple.com/en-lamr/guide/watch/apd371dfe3d7/watchos | external (first-party) | Apple Watch | high | accessed 2026-08-13 | validate TideBeacon choices independently |
| D2b | domain | Breathwrk publicly documents duration and guide-channel adjustment. | https://www.breathwrk.com/ | external (first-party) | Breathwrk mobile | medium | accessed 2026-08-13 | validate TideBeacon choices independently |
| P1 | platform | Stage is legal only in Full Space; opening it is exclusive and must have explicit entry value and stable exit. | bundled `official-rules.json` PICO-SPACESTATE-001/002, PICO-STAGE-001 | official | PICO spatial design | high | skill version 0.4.1 read 2026-08-13 | downstream on-device validation of actual transition and exit |
| P2a | platform | Controller fallback, Reduce Motion, text scaling, and stable exit are hard design baselines. | `official-rules.json` PICO-ACCESS-001–004 | official | PICO spatial design | high | skill version 0.4.1 read 2026-08-13 | design review + device input test |
| P2b | platform | 56dp targets, 12dp body floor, and 65°×40° core FOV are sizing baselines. | `spatial-window-sizing-methodology.md` §2–6 | official | PICO spatial design | high | skill version 0.4.1 read 2026-08-13 | device readability/hit test |
| P3 | platform | Exact PICO device, OS, Spatial SDK version, performance budget, and audio APIs are unknown. | none | assumption / evidence gap | downstream runtime | low | 2026-08-13 | downstream implementation records device/SDK matrix and profiles it |
| S1 | safety | The product must not use fast camera movement, strong flashing, breathing detection, microphone, scoring, or medical/therapeutic wording. | original user source | user_supplied | all TideBeacon states | high | 2026-08-13 | copy lint, motion review, permission audit, preview QA |
| S2 | safety | Automatically moving the virtual camera and continuous full-screen flashing are prohibited; every motion needs Reduce Motion and performance fallbacks. | bundled `official-rules.json` PICO-MOTION-001/002/003 | official | PICO spatial design | high | skill version 0.4.1 read 2026-08-13 | motion spec review and device comfort test |
| S3 | safety | Comfort, fatigue, spatial-audio loudness, and controller hit precision cannot be concluded from a Web preview. | workflow and preview boundary | official workflow boundary | design-stage validation | high | 2026-08-13 | mark `not_performed`; hand to device validation |

- **Boundary of market evidence usage**: M1 frames coverage and differentiation only. It cannot select TideBeacon's UI structure, states, components, visual language, color, motion, or interaction.
- **Handling source conflicts**: no direct conflict found. Health-oriented claims from competitor marketing are excluded from TideBeacon's domain model because the original source prohibits therapeutic framing.

## 3A. Competitive Benchmark

| # | Competitor / Platform | Feature needs | Interaction experience | Visual experience (observation only) | Spatial-capability usage | Source / type / observation time |
|---:|---|---|---|---|---|---|
| C1 | Apple Mindfulness Breathe / Apple Watch | 1–5 minute duration, adjustable breath rate and haptics, begin/end flow, completion summary; includes heart-rate reporting that TideBeacon must not emulate. | Touch/Digital Crown; short path from Breathe to Begin; explicit end gesture. Controller and XR migration do not apply directly. | Single-focus growing/shrinking animation with low information density; tiny wearable context. Composition and appearance are not reusable. | 2D/wearable; temporal scale change and haptics, no spatial environment or directional audio. | https://support.apple.com/en-lamr/guide/watch/apd371dfe3d7/watchos ; first-party; accessed 2026-08-13 |
| C2 | TRIPP XR / supported XR headsets | Observed: immersive worldscapes, dedicated BREATHE category, adjustable guidance, soundscapes, varied session lengths; broad library/AI is outside TideBeacon scope. | Public-doc gap for exact controller path. Analyst hypothesis H-MKT-01: multiple categories may raise choice/onboarding cost (low confidence; validate hands-on and with setup timing). | Observed public worldscape variety. Analyst hypothesis H-MKT-02: visually rich stimulation may distract from a single rhythm (low confidence; validate comparative comprehension/comfort test). | True XR environments, depth, spatial immersion, and soundscapes; also modes beyond breathing. | https://www.tripp.com/support/ ; https://www.tripp.com/trippvr/ ; first-party; accessed 2026-08-13 |
| C3 | Breathwrk / iOS and Android | Observed: 50+ exercises and configurable voice, sound, haptics, duration. Health/performance language and large catalog are outside TideBeacon scope. | Touch-first details not hands-on verified; XR/controller behavior gap. Analyst hypothesis H-MKT-03: catalog breadth may increase setup cost (low confidence; validate with current-build task timing). | Observed marketing emphasis on catalog/brand; exact in-app hierarchy/density not verified. | 2D mobile; audio/haptic cues, no verified spatial depth. | https://www.breathwrk.com/ ; first-party; accessed 2026-08-13 |
| C4 | Othership / iOS and Android | Guided breathwork with audio-led sessions; current public page frames emotional regulation, which TideBeacon must not repeat as a therapeutic claim. | Touch-first content selection; exact fallback/error and accessibility behavior are gaps. | Audio-cover and content-library presentation observed on marketing page; not a source for TideBeacon visuals. | 2D mobile; no verified spatial scene or directional-audio mechanics. | https://www.othership.us/app ; first-party; accessed 2026-08-13 |

**Per-product absorb / avoid distillation**

| # | Strengths worth absorbing at needs/opportunity layer | Weaknesses / anti-patterns to avoid |
|---:|---|---|
| C1 | Immediately legible phase mapping; duration and pace configuration; clear early-exit capability. | Sensor/heart-rate coupling, medical adjacency, and wearable interaction assumptions. |
| C2 | Spatial environment and sound can make an experience feel intentionally immersive. | Broad catalog, excessive visual stimulus, AI/personalization, or immersion without a simple stable exit. |
| C3 | Configurable guidance channels and duration. | Large exercise catalog, performance claims, and browsing-heavy setup. |
| C4 | Audio-led continuity and approachable session entry. | Therapeutic/emotional-regulation claims and content-library overhead. |

- **Our differentiation opportunities**: create a deliberately narrow PICO experience where one shared authoritative rhythm timeline drives distant spatial light, coastal audio, and countdown; retain explicit duration/rhythm choice and early exit while avoiding sensors, health metrics, scoring, content-library browsing, and therapeutic claims. The claimed choice-cost/distraction advantages remain low-confidence analyst hypotheses H-MKT-01–03 until hands-on comparative testing; they are not competitor facts and do not determine UI structure. Make complete-cycle stars/particles the only environmental accent, and make pause/lifecycle continuity auditable.
- **Sample and gap notes**: four adjacent products satisfy the denominator. Only TRIPP is an XR sample; public interaction details remain incomplete and are not promoted to design facts.
- **Absorption boundary declaration**: no competitor layout, state graph, component combination, style, color, copy, or motion is reused.

## 4. Domain Model

- **Domain workflow**: orient to the phase metaphor → choose duration → choose scene → choose BreathPattern → explicitly enter practice → follow one synchronized timeline → pause/resume/restart/exit as needed → complete → optionally write a minimal local record.
- **Decision variables**: `durationMinutes {2,4,6}`; `sceneId {sea,cloud,dune}`; `patternId`; phase durations; current phase; elapsed active time; remaining active time; completed cycle count; paused flag; lifecycle-suspended flag; reduce-motion flag; local-save result.
- **Data entities and timeliness**:

| Entity | Key fields | Timeliness / authority |
|---|---|---|
| SessionSelection | duration, scene, pattern | stable until changed before start or restart |
| BreathPattern | inhaleMs, holdAfterInhaleMs, exhaleMs, holdAfterExhaleMs | immutable during an active run unless restart begins a new run |
| RhythmTimeline | origin, accumulatedPausedMs, currentPhase, phaseProgress, remainingMs | single monotonic authority; evaluated every frame but independent of refresh rate |
| PlaybackEnvelope | visualScale, haloOpacity, audioGain, countdownText | derived from RhythmTimeline only; never independent clocks |
| CycleAccent | completedCycleCount, lastEmittedCycle | emitted only after a complete four-phase cycle; no partial-cycle reward |
| PauseSnapshot | timeline position, audio gain, suspension reason | captured immediately; exact restore source |
| LocalPracticeRecord | completedAt, duration, scene, pattern | optional write once after completion; local only; success/failure observable |

- **Specialized risks**: timer drift between channels; resume skip/pop; duplicate cycle accents after backgrounding; accidental exit; strong flashing; excessive particle density; controller-inaccessible menus; misleading health language; false success for local save.
- **User mental model**: the lighthouse is a calm external metronome; the halo growing means inhale, returning means exhale; the scene is a chosen atmosphere, not a scored game; completion records an event, not performance.
- **Mature patterns worth referencing**: explicit duration and pace choice; clear pause/end; redundant visual/audio cues; deterministic timeline tests.
- **Anti-patterns**: independent timers for visual/audio/countdown; breathing detection; score/streak pressure; forced camera travel; endless pulsing; generic content dashboard; save button that does not write; cancellation that mutates underlying state.

## 5. Persona

### Persona 1: First-time Chinese-speaking PICO rhythm user

| Dimension | Content |
|---|---|
| Basic information | Age, occupation, and XR expertise are evidence gaps; recruitment must span novice and experienced users. |
| Use scenario and frequency | Short indoor 2/4/6-minute session; actual frequency unknown. |
| Goals / motivations | Follow a clear audiovisual rhythm with little reading and retain control over duration, scene, rhythm, pause, and exit. |
| Pain points / frustrations | Ambiguous phase cues, menu complexity, motion discomfort, and losing place after pause. |
| Spatial usage habits | Sitting or standing in place is a product assumption; comfort threshold needs device observation. |
| Accessibility needs | Controller-only completion, Reduce Motion, text scaling, and color-independent states are baseline requirements; individual needs unknown. |
| Key quote (verbatim) | Evidence gap: no participant quote exists; do not invent one. The only verbatim product instruction is “光扩张时吸气，光收回时呼气。” |

## 6. Journey Map

| Stage | Awareness / entry | First hands-on | Core use | Depth / immersion | Exit / return |
|---|---|---|---|---|---|
| User goal | understand mapping | make three small choices | follow rhythm | remain oriented and comfortable | finish or leave without losing control |
| User behavior | reads exact sentence | selects duration/scene/pattern | watches lighthouse and hears phase envelope | may pause/resume/restart | sees exact completion copy; optionally saves |
| Touchpoints | first spatial composition | selection controls | Full Space practice | fixed camera, distant beacon, environment | completion / confirm-exit paths |
| Thoughts | “What should I do?” | “How long and what atmosphere?” | “Which phase is now?” | “Can I stop safely?” | “Did it finish/save?” |
| Emotion | neutral | calm anticipation | steady attention | vulnerable to discontinuity | closure |
| Pain points | instruction overload | excessive catalog | desynchronization | resume pop or accidental exit | ambiguous save result |
| Opportunities | exactly three first-view elements | bounded choices | one timeline | freeze and smooth gain fade | exact copy + observable optional write |

- **Emotional low point**: a pause/resume discontinuity would immediately break trust because all three guidance channels would disagree.
- **Key opportunities**: make synchronization and cancellation preservation observable and testable; keep the first entry radically sparse.

## 7. Key Findings

| # | Finding | Evidence | Confidence | Design Implication |
|---:|---|---|---|---|
| F1 | Growth/contraction is an established phase metaphor but TideBeacon must independently spatialize it. | D1 + U3 | high | keep the exact mapping, derive a distant lighthouse halo rather than a copied 2D form |
| F2 | Duration and guidance customization are baseline expectations. | D2a + D2b + M1a + M1b + M1c | medium | cover 2/4/6, scene, and pattern without a catalog dashboard |
| F3 | The central differentiator is synchronized spatial depth/audio, not quantity of content. | benchmark C1–C4 | medium | one authoritative timeline and one primary practice focus |
| F4 | Lifecycle pause continuity is a trust requirement, not cosmetic polish. | U4 + domain model specialized risks | high | timeline snapshots and refresh-rate/lifecycle tests |
| F5 | Comfort and accessibility cannot be closed by Web QA. | P2a + P2b + S2 + S3 | high | explicit fallbacks and device-validation boundary |

## 8. Wearing Posture and Field-of-View Insights

- **Usual posture**: evidence gap; design assumption is sitting or standing in place.
- **Arm range**: unknown; controller operation must avoid prolonged reach and be tested on device.
- **Central field of view**: official design baseline keeps core content within 65° horizontal × 40° vertical; secondary content within 85° × 55°.
- **Fatigue threshold**: product sessions are capped at 6 minutes; subjective fatigue remains `not_performed` until device validation.

## 9. Eye-Hand Interaction Usability

- **Gaze focus + pinch hit rate**: evidence gap; no measured data.
- **Low-load assumption**: controller use with arms relaxed is intended but unverified.
- **Mis-touch and feedback**: every focusable element must expose focus/pressed/disabled states and a 56×56dp minimum target; device hit precision remains unverified.

## 10. Duration Baseline Data

| Decision Type | Duration Anchor | Source |
|---|---|---|
| First-view comprehension | no supplied value; design target will be acceptance-derived, not claimed as observed | evidence gap U2; validate in comprehension test |
| Duration/scene/pattern selection | no supplied value; target must be set by quality contract | evidence gap U2 |
| Phase guidance | BreathPattern-configured milliseconds, single authoritative timeline | original user source |
| Session | exactly selected 2/4/6 active minutes, excluding paused time | original user source |

## 11. Motion Sickness / Fatigue and Safety

- **Risk scenarios**: camera motion, large fast scene displacement, full-field flashing, dense particles, abrupt audio gain changes, and resuming at a different phase.
- **High Motion label needed**: no, because the concept must prohibit high-motion behavior rather than label it.
- **Duration/rest cadence**: sessions are 2/4/6 minutes; no evidence-backed rest cadence is asserted.
- **Boundary**: comfort, fatigue, loudness, hit precision, and runtime performance require device validation.

## 12. Minimum Completeness Gate

| Check Item | Minimum Pass Condition | Evidence Anchor | Verdict |
|---|---|---|---|
| Five categories | each has evidence or explicit gap | §3 | pass |
| Competitive benchmark | at least three products and four dimensions | §3A (4 products) | pass |
| Domain model | workflow, variables, entities/timeliness, risks, mental model, anti-patterns | §4 | pass |
| User evidence | Persona/Journey/findings preserve gaps and sources | §5–§7 | pass |
| Quantitative and safety | values or explicit gaps for duration/posture/input/safety | §8–§11 | pass |

| Field | Value |
|---|---|
| minimumCompletenessGate | pass |

## 13. Delivery and Recipients

- **Deliverables**: research evidence and domain model, revision 3.
- **Recipients**: product strategist, task/decision designer, interaction/XR designer, visual designer.
