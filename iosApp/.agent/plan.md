# Project Plan

Create a pure KMP module named 'network-guard' with no UI dependencies. It should use Ktor Core and Coroutines. Implement a SafeApiCall mapping Throwables from iOS and Android to a Sealed Interface (Success/Error) with clean, configurable error strings. The project should be ready for JitPack (Git: https://github.com/andyechc/network-guardian.git).

## Project Brief

# Project Brief: Network Guardian (`network-guard`)


Network Guardian is a pure Kotlin Multiplatform (KMP) library designed to provide a robust, resilient layer for network operations. It abstracts the complexities of cross-platform error handling, ensuring that both Android and iOS applications can handle API responses consistently and gracefully.

## Features

*   **Safe API Call Wrapper**: A standardized `safeApiCall` function that executes network requests and automatically catches exceptions, returning a structured `Result` type.
*   **Unified Error Mapping**: Automatically maps platform-specific exceptions (e.g., Android's `IOException` or iOS's `NSURLError`) into a shared `Sealed Interface` with `Success` and `Error` states.
*   **Configurable Error Strings**: Support for injecting custom error message providers, allowing developers to define clean, user-friendly strings for different error scenarios (Timeout, No Connection, Server Error).
*   **Pure KMP Architecture**: A UI-agnostic module built strictly for the data layer, ensuring maximum portability and zero overhead for consumer applications.
*   **JitPack Ready**: Pre-configured build scripts for seamless publishing and integration via JitPack.

## High-Level Technical Stack

*   **Kotlin Multiplatform**: To share core logic across Android and iOS.
*   **Ktor Core**: For multiplatform asynchronous network requests.
*   **Kotlin Coroutines**: To manage asynchronous execution and thread safety across platforms.
*   **KSP (Kotlin Symbol Processing)**: For efficient, low-overhead code generation and metadata processing.
*   **Gradle**: Configured with the Maven Publish plugin for JitPack compatibility.

## Implementation Steps

### Task_1_ModuleSetup: Create the 'network-guard' KMP module. Configure build.gradle.kts for Android and iOS targets. Update libs.versions.toml with Ktor Core, Coroutines, and KSP dependencies.
- **Status:** COMPLETED
- **Updates:** Created the 'network-guard' KMP module. Configured build.gradle.kts for Android and iOS targets. Updated libs.versions.toml with Ktor Core, Coroutines, and KSP dependencies. Project syncs successfully.
- **Acceptance Criteria:**
  - Module 'network-guard' is created and recognized by Gradle
  - Dependencies for Ktor, Coroutines, and KSP are added to libs.versions.toml
  - Project syncs successfully

### Task_2_CoreLogicAndMapping: Implement the 'NetworkResult' sealed interface and 'safeApiCall' function in commonMain. Implement platform-specific Throwable mapping for Android (IOException, SocketTimeoutException) and iOS (NSURLError) to shared error states.
- **Status:** COMPLETED
- **Updates:** Implemented the 'NetworkResult' sealed interface and 'safeApiCall' function in commonMain. Implemented platform-specific Throwable mapping for Android (IOException, SocketTimeoutException) and iOS (NSURLError) to shared error states. Added support for configurable error strings. Verified that the module remains pure KMP with no UI dependencies.
- **Acceptance Criteria:**
  - NetworkResult sealed interface defined in commonMain
  - safeApiCall handles both Success and Error cases
  - Exception mapping is implemented for both Android and iOS
  - Module remains pure KMP with no UI dependencies

### Task_3_CustomizationAndPublishing: Implement configurable error strings via an interface or provider. Configure the 'maven-publish' plugin in build.gradle.kts for JitPack compatibility.
- **Status:** COMPLETED
- **Updates:** Implemented configurable error strings via an interface or provider. Configured the 'maven-publish' plugin in build.gradle.kts for JitPack compatibility. Verified that custom error message providers can be injected and that the Maven Publish plugin is correctly configured for all targets.
- **Acceptance Criteria:**
  - Custom error message providers can be injected
  - Maven Publish plugin is configured for all targets
  - JitPack compatibility confirmed (pom generation setup)

### Task_4_RunAndVerify: Perform a final build and verify application stability. Instruct critic_agent to verify alignment with user requirements and report critical UI issues.
- **Status:** COMPLETED
- **Updates:** The 'network-guard' KMP module was successfully created, implemented, and verified for stability. The critic_agent confirmed that the core logic for the 'safeApiCall' function, unified error mapping, and configurable error strings are correctly implemented. The module follows a pure KMP architecture and is configured for JitPack. The critic_agent noted that while the library is functionally complete, the 'composeApp' does not currently demonstrate its features and there are no unit tests. The project builds successfully and is ready for use.
- **Acceptance Criteria:**
  - Project builds successfully
  - All existing tests pass
  - App does not crash
  - Pure KMP architecture is maintained
- **Duration:** N/A

