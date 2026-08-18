# TideBeacon

TideBeacon 是一个 `DefaultStage` PICO Spatial SDK MVP。当前使用 SDK 程序化网格呈现海面、云层、沙丘和远处灯塔，并保留呼吸光环、空间面板和空间音频。所有 2D 控件都位于 Stage 的 `AttachmentPanel` 内。

核心实现：

- `domain/usecase/BreathTimeline.kt`：唯一单调时间轴；呼吸相位、光环、声音增益、倒计时和循环计数都从这里派生。
- `ui/tidebeacon/TideBeaconViewModel.kt`：Intro → Setup → Practice → Completed 状态机及应用暂停/恢复。
- `ui/tidebeacon/TideBeaconScreen.kt`：CC0/CC-BY GLB 场景资源、AttachmentPanel、生命周期和帧驱动；场景切换时显式销毁旧实体，呼吸光环仅在白炽暖白 `#FFF4EA` 到近白 `#FFFDFC` 之间微调，不经过黄色。
- `ui/tidebeacon/SceneEntityLifetime.kt`：跟踪单次 `SpatialView` 生命周期创建的实体，并在退出/切换时逆序、幂等销毁。
- `ui/tidebeacon/TideBeaconSceneLayout.kt`：灯塔、海面、云朵和沙丘共用的 `3.4×` 场景尺度。
- `ui/tidebeacon/LowPolySea.kt`：18×10 连续低多边形海面网格，使用窄色阶与静态柔和波形，并按统一场景尺度扩展。
- `ui/tidebeacon/LowPolyDunes.kt`：边缘收拢的椭圆低多边形沙丘底形，按统一场景尺度承托导入的沙漠细节。
- `platform/SpatialBreathAudio.kt`：低音量程序化 PCM，通过 `SpatialAudioTrackExtension` 附着灯塔灯室；恢复时读取时间轴的 400ms 淡入。
- `data/repository/PracticeRecordRepository.kt`：仅用户选择时写入本地 SharedPreferences。

约束：所有 2D UI 必须使用 SpatialUI + `PicoTheme`，禁止 Material/Material3。不要加入麦克风、呼吸检测、评分、健康诊断或疗效措辞。

构建与运行：

```powershell
$env:PATH='C:\Users\Administrator\.jdks\corretto-17.0.13\bin;'+$env:PATH
.\gradlew.bat testDebugUnitTest assembleDebug
pico-cli app install app\build\outputs\apk\debug\app-debug.apk
pico-cli app launch com.pico.swan.tidebeacon --activity .platform.LaunchActivity
```

当前三种场景为：程序化连续海面、Quaternius CC0 云朵、Poly by Google CC-BY 沙漠场景。授权记录见 `THIRD_PARTY_NOTICES.md`。后续可继续调校空间位置、材质和环境音；保持 `BreathTimeline` 为唯一时钟。

当前灯塔实测原始高度为 `19.6991m`，运行时缩放为 `0.1904×`，塔顶约 `3.91m`，呼吸光环中心约 `Y=3.56m`。资产包围盒与布局记录见 `.spatialsdk/scene_transforms.json`。`SpatialView` 离开组合不会自动销毁实体，新增内容必须经 `SceneEntityLifetime.track` 纳入清理。真机同时只能运行一个 Stage；启动 TideBeacon 前需先停止其他 Full Space 应用。ADB `screencap` 无法可靠捕获 Spatial Stage。
