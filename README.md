# MyTaxi Android App
  - Display all vehicles in a list
  - Display the location of all vehicles in a map
  - When vehicle clicked from the list show the location of the vehicle in the map
  - When vehicle clicked inside the map show the id of that vehicle

### How we did it?
  - When MainActivity created `VehiclePresenter.fetchVehicles()` is called and pushed the results to `ListFragment` and `VehicleMapFragment` using `VehicleViewModel` when vehicles are available.

### FAQ's
  - Q: How to update the list?
  - A: Go to `List` and pull down the list of vehicles to refresh.
  - Q: How did the fragments (`ListFragment` and `VehicleMapFragment`) connected to `MainActivity`
  - A: Using `Koin's` `viewmodel`. Key functions are `viewModel()` declared from your `Activity` and `sharedViewModel()` from `Fragment`

### Limitation
  - Network handling, make sure you have internet connection before using the app. It will show network error when you don't.

### Libraries
  - `koin` for dependency injection with `koin-androidx-viewmodel` to support `viewmodel`
  - `retrofit` together with `rxjava2` and `rxandroid` for networking task
  - `play-services-maps` for maps
  - `core-ktx` and `anko-commons` for utilities
  - `navigation-fragment-ktx` and `navigation-ui-ktx` to support navigation

### Build
  - `$./gradlew clean`
  - `$./gradlew build`
  - Output apk file can be found in `/app/build/outputs/apk` `debug` and `release` folders.
