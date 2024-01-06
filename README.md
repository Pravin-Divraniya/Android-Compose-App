# Compose example + MVVM with repository pattern + Hilt

This example demonstrates MVVM architecture implementation in Jetpack Compose using different architecture components and DI frameworks like Hilt. In branch featuer/with-paging contains example of Jetpack Compose with Pagination using [paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) library of Architecture component.

You'll find:
*   Kotlin **[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)** for background operations.
*   The **[Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)**.
*   MVVM architecture to separate presentation layer (View) and business logic (**ViewModel**)
*   Reactive UIs using **Flow**.
*   A **data layer** with a repository and data sources.

## Variations

This project hosts different sample app in separate repository branches.

### Samples - Kotlin
| Sample                                                                                             | Description                                                                                                                                                                                                                                                                                             |
|----------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [main](https://github.com/Pravin-Divraniya/Android-Compose-App/tree/main)                         | A sample Jetpack Compose using MVVM, using architecture components.                                                                                                                                                                                                                                     |
| [feature/with-paging](https://github.com/Pravin-Divraniya/Android-Compose-App/tree/feature/with-paging) | A sample Jetpack Compose using MVVM, using architecture components with [paging 3 library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview). API:- [rick and morty api](https://rickandmortyapi.com/). It uses Kotlin, Coroutines, Flow, Retrofit, Navigation Components. |
