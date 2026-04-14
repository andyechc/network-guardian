# Network Guardian (`network-guard`)

Network Guardian is a pure **Kotlin Multiplatform (KMP)** library designed to provide a robust, resilient layer for network operations. It abstracts the complexities of cross-platform error handling, ensuring that both Android and iOS applications can handle API responses consistently and gracefully.

Built with **Ktor** and **Kotlin Coroutines**, this library focuses on the data layer, offering a UI-agnostic approach to network resilience.

## 🚀 Features

*   **Safe API Call Wrapper**: A standardized `safeApiCall` function that executes network requests and automatically catches exceptions, returning a structured `NetworkResult` type.
*   **Unified Error Mapping**: Automatically maps platform-specific exceptions (e.g., Android's `IOException` or iOS's `NSURLError`) into a shared `Sealed Interface`.
*   **Configurable Error Strings**: Support for injecting custom `NetworkErrorProvider` implementations, allowing for localized or branded error messages.
*   **Pure KMP Architecture**: Zero UI dependencies, making it perfect for shared data modules.
*   **JitPack Ready**: Easy integration into any project via JitPack.

## 📦 Installation

To use Network Guardian in your project, add the JitPack repository and the dependency to your `build.gradle.kts` files.

### 1. Add Repository
In your root `settings.gradle.kts` (or `build.gradle.kts`):

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. Add Dependency
In your shared module's `build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation("com.github.andyechc:network-guardian:1.0.0")
}
```

## 🛠 Usage

### Basic `safeApiCall`
Wrap your Ktor requests with `safeApiCall` to automatically handle exceptions and map them to a `NetworkResult`.

```kotlin
import com.andyechc.network_guard.safeApiCall
import com.andyechc.network_guard.NetworkResult

suspend fun getUserProfile(): NetworkResult<User> {
    return safeApiCall {
        client.get("https://api.example.com/profile").body<User>()
    }
}
```

### Handling the Result
Use a `when` expression to handle the success and error states.

```kotlin
val result = getUserProfile()

when (result) {
    is NetworkResult.Success -> {
        println("User: ${result.data.name}")
    }
    is NetworkResult.Error -> {
        println("Error: ${result.message}")
        // Accessible error types: HttpError, NetworkError, TimeoutError, etc.
    }
}
```

### Custom Error Messages
Implement the `NetworkErrorProvider` interface to customize error strings for your app.

```kotlin
import com.andyechc.network_guard.NetworkErrorProvider

class MyCustomErrorProvider : NetworkErrorProvider {
    override fun getHttpErrorMessage(code: Int): String = "Server returned error $code. Please try again later."
    override fun getNetworkErrorMessage(): String = "Please check your internet connection."
    override fun getSerializationErrorMessage(): String = "Data processing error."
    override fun getTimeoutErrorMessage(): String = "The request took too long."
    override fun getUnknownErrorMessage(throwable: Throwable): String = "Something went wrong."
}

// Pass it to the safeApiCall
val result = safeApiCall(errorProvider = MyCustomErrorProvider()) {
    // API call
}
```

## 📱 Platform Support

| Platform | Support | Underlying Engine Mappings |
| :--- | :---: | :--- |
| **Android** | ✅ | `IOException`, `SocketTimeoutException` |
| **iOS** | ✅ | `NSURLError` (via `DarwinHttpRequestException`) |

## 📄 License
This project is licensed under the MIT License.
