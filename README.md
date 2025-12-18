# 💳 CreditCardInputField (Jetpack Compose)

A highly flexible, Material 3-ready Jetpack Compose library for smart credit card inputs.

## 🌟 Key Advantage: Logic-Agnostic Architecture
This library is designed for **Zero-Change Maintenance**. Unlike traditional libraries that hardcode card brands, this library allows the client-side (Sample App) to inject Regex patterns and Logos.

**Adding a new card union (like UnionPay, JCB, or RuPay) requires ZERO changes to the library project.**

---

## 🛠 Project TODO & Roadmap

### ✅ Completed
- [x] **Inversion of Control**: Logic (Regex) and assets (Logos) are passed from the client app.
- [x] **Configurable Parameters**: Added `cardConfigs: List<CardTypeConfig>` to the primary component.
- [x] **Named Arguments**: API enforces clean, readable code using explicit parameter names.
- [x] **Dynamic Validation**: Library respects `maxLength` (e.g., 15 for Amex, 16 for others) provided via config.
- [x] **Apache 2.0 License**: Fully integrated into Maven POM metadata.
- [x] **Zero-Change Maintenance**: Library decoupled from specific card brands.
- [x] **GitHub Release**: Initial Maven ZIP uploaded to GitHub Releases.

### 🚀 Future Implementation
- [ ] **Accessibility (A11y)**: Sync `activeConfig.name` with `contentDescription` for screen readers.
- [ ] **Luhn Algorithm**: Add optional `verifyChecksum: Boolean` for mathematical validation.
- [ ] **Error UI**: Implement `isError` parameter for Material 3 error states (red borders).
- [ ] **Unit Testing**: Test `CreditCardVisualTransformation` for various grouping patterns.

### 📦 Client-Side Tasks (Sample App)
- [ ] Add SVG/Vector assets for Discover, Diners Club, JCB, and RuPay to `app/src/main/res/drawable`.
- [ ] Uncomment JCB and RuPay configurations in `MainActivity.kt`.

## 🚀 Optimized Maintenance
Adding a new card union (UnionPay, RuPay, etc.) requires **zero changes** to this library. All patterns and logos are configurable from the client app.

## 🛠 Usage & Configuration
markdown
## 📦 How to use the Maven ZIP
1. Download `creditcard-input-maven.zip` from the Releases tab.
2. Unzip it to a folder (e.g., `rootDirectory/creditcardedit/build/repo`).
3. In your `settings.gradle.kts`,  add: maven { url = uri("path/to/unzipped/repo") }
Example: 
   `repositories {
       google()
       mavenCentral()
       maven { url = uri("${layout.rootDirectory}/creditcardedit/build/repo") }
   }`
Add the **Maven** repository to your `settings.gradle.kts`:

### 1. Installation

### 3. Add Dependency
Add this to your module-level `build.gradle.kts`:
`dependencies {
    implementation("io.github.sujayabraham:creditcard-input:1.0.0")
}`

