# Project Plan

Extend the Network Guardian project to include a 'NetworkTestScreen' in the `composeApp`. This screen will be used to verify that the `safeApiCall` correctly maps exceptions (like `NSURLError` on iOS and `IOException` on Android) to clean, user-friendly strings. The component should be isolated from the core library logic.

## Project Brief

# Project Brief: Network Guardian (`network-guard`) - Extension

The project now includes a requirement for a "Debug/Test" UI component that demonstrates the library's functionality, specifically targeting error message verification on iOS and Android.

## Features to Add:
*   **Debug UI Component**: A screen in the `composeApp` (or a sample module) that:
    *   Triggers a network call using `safeApiCall`.
    *   Targets a URL guaranteed to fail (e.g., non-existent or 404).
    *   Displays the resulting `NetworkResult.Error` message.
*   **Production Safety**: Ensure this code is isolated and doesn't clutter the core library module.

## High-Level Technical Stack
*   **Compose Multiplatform**: For the UI.
*   **Ktor**: To trigger the test requests.
*   **Kotlin Multiplatform**: To ensure the test works on both Android and iOS.

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

### Task_5_ImplementDebugUI: Implement 'NetworkTestScreen' in the 'composeApp'. Add a button to trigger a failing Ktor request using 'safeApiCall' and display the resulting clean error message in the UI. Ensure 'composeApp' correctly depends on 'network-guard'.
- **Status:** COMPLETED
- **Updates:** Implemented 'NetworkTestScreen' in the 'composeApp'. Added a button to trigger a failing Ktor request using 'safeApiCall' and display the resulting clean error message in the UI. Ensure 'composeApp' correctly depends on 'network-guard'. Verified that 'composeApp' has a dependency on 'network-guard', 'NetworkTestScreen' is implemented in commonMain, failing request triggers 'safeApiCall', and error message is visible in the UI.
- **Acceptance Criteria:**
  - 'composeApp' has a dependency on 'network-guard'
  - 'NetworkTestScreen' is implemented in commonMain
  - Failing request triggers 'safeApiCall'
  - Error message is visible in the UI

### Task_6_FinalVerification: Perform a final run and verification of the UI integration. Confirm that platform exceptions are correctly mapped and displayed as user-friendly strings. Instruct critic_agent to verify stability and requirement alignment.
- **Status:** COMPLETED
- **Updates:** The 'NetworkTestScreen' was successfully implemented and verified in the 'composeApp'. The critic_agent confirmed that both Android and iOS error mapping work as expected, providing user-friendly strings for network failures (e.g., 'No internet connection'). The 'network-guard' module remains a pure KMP library with no UI dependencies. The app is stable, follows Material Design 3 guidelines, and implements edge-to-edge display with an adaptive icon. Project is ready for use.
- **Acceptance Criteria:**
  - Project builds and runs successfully
  - Error mapping is verified in the UI for both Android and iOS
  - App does not crash
  - 'network-guard' module remains pure KMP and isolated
- **Duration:** N/A

