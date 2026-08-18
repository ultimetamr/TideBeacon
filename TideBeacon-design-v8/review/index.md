# Human Review Package · TideBeacon

> Target platform: PICO spatial | Package scope: `web_design_validation_only` | Run: `tidebeacon-v8-20260812T225635+0800`

> Current workflow status: **Stage14 blocked / designStatus=invalid**. Stage12 design-system review passed; Stage13 preview exists; independent Preview QA cannot pass because per-item actual browser evidence and the requested screenshots were prevented by the host's local-file navigation policy. Stages15–17 were not opened; this package is not ready for design delivery.

This package is independently derived from the user's TideBeacon requirement. It reuses only the official workflow, blank document structures, and PICO design rules. `templateReuse:false`; no historical TideBeacon design file was opened, copied, or compared.

| Document | Role / purpose |
|---|---|
| [PM Requirement Spec](pm-requirement-spec.md) | intent and quality contract |
| [UXR Research Report](uxr-research-report.md) | five evidence categories, competitive benchmark, domain model |
| [Interaction / Spatial Design Spec](interaction-spatial-spec.md) | tasks, concepts, architecture, states, interaction, motion, layout |
| [Visual System Spec](visual-system-spec.md) | visual direction, tokens, window layout, components, data semantics |
| [Design Critique Report](design-critique-report.md) | independent reviews, hard gates, audits, readiness |
| [Preview / QA Test Report](preview-qa-report.md) | manifest, implementation maps, independent QA |
| [Execution Trace](execution-trace.md) | ordered stage receipts, revisions, invocations |

Allowed outputs are these review documents plus `../preview.html`. No Android project, PICO runtime code, device evidence, parity report, `design-spec.json`, or `design-graph.json` is part of this package.
