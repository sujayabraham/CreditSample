## 📄 License

Copyright 2024 Sujay Abraham

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

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

### 1. Installation

Add the **Maven** repository to your `settings.gradle.kts`:

