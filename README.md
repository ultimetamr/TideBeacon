# TideBeacon

TideBeacon 是一个面向 PICO OS 6 的空间呼吸节奏 MVP。应用运行在 PICO Spatial SDK 的 `DefaultStage` 中，以放大的低多边形灯塔和环境场景作为视觉焦点，通过灯室周围的呼吸光环、空间音频和简洁面板提示吸气、停留与呼气节奏。

> 本项目只提供节奏引导和本地练习记录，不包含麦克风、呼吸检测、评分、健康诊断或疗效承诺。

## 功能概览

- Intro → Tutorial → Setup → Practice → Completed 完整练习流程。
- 海面、云层、沙丘三种可切换空间场景。
- 灯塔、海面、云朵和沙丘采用统一的 `3.4×` 空间尺度。
- 灯塔顶部约 `3.91 m`，呼吸光环中心约 `Y=3.56 m`，形成明显的仰视构图。
- 光环在白炽暖白 `#FFF4EA` 与近白 `#FFFDFC` 之间微调，不经过黄色或蓝色。
- 三种呼吸节奏：舒缓 `4·1·6·1`、均匀 `4·1·4·1`、悠长 `5·1·7·1`。
- 支持暂停、恢复、重新开始及应用进入后台后的时间线恢复。
- 用户主动选择时，才会把练习记录保存到本机 `SharedPreferences`。

## 技术栈

- Kotlin 2.1.20
- Android Gradle Plugin 8.13.2
- Gradle 8.13
- PICO Spatial SDK BOM 0.13.3
- SpatialUI + `PicoTheme`
- Android API 35，`arm64-v8a`
- JDK 17

所有二维控件都位于 Stage 的 `AttachmentPanel` 内。项目不使用 Material 或 Material3。

## 核心设计

### 单一时间轴

[`BreathTimeline.kt`](app/src/main/java/com/pico/swan/tidebeacon/domain/usecase/BreathTimeline.kt) 是应用唯一的单调时钟。呼吸阶段、光环缩放、声音增益、倒计时和循环计数都从同一个时间轴快照派生，避免多个动画时钟在暂停或恢复后漂移。

### 场景生命周期

PICO Spatial SDK 不会在 `SpatialView` 离开 Compose 组合时自动销毁其中的实体。[`SceneEntityLifetime.kt`](app/src/main/java/com/pico/swan/tidebeacon/ui/tidebeacon/SceneEntityLifetime.kt) 会跟踪每次场景生命周期内创建的根实体，并在停止、页面切换或场景切换时逆序、幂等销毁，防止旧场景残留。

### 空间布局

灯塔和导入资产的原始包围盒、运行时缩放及位置记录在 [`.spatialsdk/scene_transforms.json`](.spatialsdk/scene_transforms.json) 中。程序化海面和沙丘分别由 [`LowPolySea.kt`](app/src/main/java/com/pico/swan/tidebeacon/ui/tidebeacon/LowPolySea.kt) 与 [`LowPolyDunes.kt`](app/src/main/java/com/pico/swan/tidebeacon/ui/tidebeacon/LowPolyDunes.kt) 生成。

## 工程结构

```text
app/src/main/java/com/pico/swan/tidebeacon/
├── data/repository/          # 本地练习记录
├── domain/model/             # 场景、阶段、节奏与快照模型
├── domain/usecase/           # 单一呼吸时间轴和记录用例
├── platform/                 # Spatial Application、Activity 与空间音频
└── ui/tidebeacon/            # Stage、场景网格、光环和 SpatialUI 面板

app/src/main/assets/
└── third_party/              # 灯塔、云朵和沙漠 GLB 资源
```

## 环境要求

1. Windows 开发环境。
2. JDK 17。
3. Android SDK Platform 35。
4. PICO OS 6 真机或兼容的 PICO 模拟器。
5. 安装应用和读取设备状态时，需要可用的 `pico-cli`。

本机示例 JDK 路径为 `C:\Users\Administrator\.jdks\corretto-17.0.13\bin`，其他环境请替换为自己的 JDK 17 路径。

## 构建与测试

在 PowerShell 中执行：

```powershell
$env:PATH='C:\Users\Administrator\.jdks\corretto-17.0.13\bin;'+$env:PATH
.\gradlew.bat testDebugUnitTest assembleDebug
```

生成的 Debug APK 位于：

```text
app\build\outputs\apk\debug\app-debug.apk
```

## 安装与启动

先确认目标设备：

```powershell
pico-cli device list --format json
```

只有一台设备在线时，可直接安装和启动：

```powershell
pico-cli app install app\build\outputs\apk\debug\app-debug.apk --replace
pico-cli app launch com.pico.swan.tidebeacon --activity .platform.LaunchActivity
```

同时连接真机和模拟器时，应显式指定设备序列号：

```powershell
pico-cli app install app\build\outputs\apk\debug\app-debug.apk --replace --device <device-id>
pico-cli app launch com.pico.swan.tidebeacon --activity .platform.LaunchActivity --device <device-id>
```

PICO 同一时间只能运行一个 Stage。如果启动后没有创建 TideBeacon 进程，请先停止当前前台的其他 Full Space 应用，再重新启动 TideBeacon。

## 运行验证

查看安装和进程状态：

```powershell
pico-cli app info com.pico.swan.tidebeacon --format json
```

查看崩溃记录：

```powershell
adb logcat -b crash -d
```

ADB `screencap` 无法可靠捕获 Spatial Stage，空间布局和光环颜色需要在头显中最终目视确认。

## 数据与隐私

- 不申请麦克风权限。
- 不采集呼吸、健康或生物识别数据。
- 不进行评分、诊断或疗效判断。
- 练习记录仅在用户主动选择后写入本机。
- 当前实现不包含云端同步或账号系统。

## 第三方资源

项目使用以下第三方资产：

- Lighthouse，Poly by Google，CC BY 3.0。
- Cloud，Quaternius，CC0 1.0 Universal。
- Low Poly Desert Scene，Poly by Google，Creative Commons Attribution。

完整来源、授权链接和文件路径见 [`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md)。

## 许可证

当前仓库未附带项目源代码许可证。第三方资产继续遵循各自的授权条款。
