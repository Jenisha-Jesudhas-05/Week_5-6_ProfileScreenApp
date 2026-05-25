# Profile Screen App 

A modern Android application built using **Jetpack Compose** and **Kotlin** showcasing modern Android development practices including:
- Jetpack Compose UI
- Navigation Component
- Hilt Dependency Injection
- WorkManager
- DataStore
- Firebase Cloud Messaging (FCM)
- Dark/Light Theme
- Accessibility Support

---

# 📱 Features

## Week 5 – Jetpack Compose & Dependency Injection

### Jetpack Compose UI
Built a responsive Profile Screen using:
- Text
- Button
- Column
- Row
- LazyColumn

### State Management
- Used `remember`
- Used `mutableStateOf`
- Implemented state hoisting

### Navigation
- Navigation using:
  - `NavController`
  - `NavHost`

### Theme Support
- Dark/Light mode toggle using `MaterialTheme`

### Animation
- Animated profile avatar on click

### Dependency Injection with Hilt
Implemented:
- `@HiltAndroidApp`
- `@Inject`
- `@Module`
- `@HiltViewModel`

### Repository Pattern
- Injected mock `UserRepository` using Hilt

---

# Week 6 – Advanced Android Topics

### Firebase Cloud Messaging (FCM)
- Push notification integration using Firebase

### Daily Notification Testing
- Test button to fire instant notifications
- Runtime permission handling for Android 13+
- Reuses WorkManager notification channel

### WorkManager
- Daily scheduled notifications using WorkManager

### DataStore
- Replaced SharedPreferences with DataStore

### Accessibility
Added:
- Content descriptions
- TalkBack support

### Build Variants
- Debug build
- Release build
- Different app names for flavors

---

# 🛠 Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Hilt
- Navigation Compose
- Firebase FCM
- WorkManager
- DataStore


---
# 📂 Project Structure

```text
app/
├── manifests/
├── kotlin+java/
│   └── com.example.leanerapp/
│       ├── data/
│       ├── di/
│       ├── ui.theme/
│       │   ├── Color.kt
│       │   ├── Theme.kt
│       │   └── Type.kt
│       ├── viewmodel/
│       ├── AppNavigation.kt
│       ├── DailyNotificationWorker.kt
        |-- NotificationTestScreen.kt
│       ├── EditProfileScreen.kt
│       ├── MainActivity.kt
│       ├── MyApp.kt
│       └── ProfileScreen.kt
├── res/
└── Gradle Scripts/
```

---

# 📸 Screenshots

## 🏠 Profile Screen

<img width="300" alt="image" src="https://github.com/user-attachments/assets/166ebe1f-85db-49b3-b11f-8d08c019bf8b" />


---

## 🌗 Theme Screenshots

| Light Theme | Dark Theme |
|-------------|-------------|
| <img width="250" alt="Light Theme" src="https://github.com/user-attachments/assets/d74d638f-984b-4155-a57d-934811ab5bc9" /> | <img width="250" alt="Dark Theme" src="https://github.com/user-attachments/assets/5f153f04-bea9-44f0-8e68-5c33d86b879f" /> |

---

## ✏️ Edit Profile Screen

<img width="300" alt="Edit Profile Screen" src="https://github.com/user-attachments/assets/bbffe100-3fa4-4eec-9df3-6ebb54cfee09" />

---

## 🔔 Notification Test Screen
| <img width="300" alt="image" src="https://github.com/user-attachments/assets/2f2b8ac1-146d-4b95-ace4-6e804b9b8aee" /> | <img width="300" alt="image" src="https://github.com/user-attachments/assets/82009273-09e1-465b-b0e6-62489bc7d582" /> |



---

