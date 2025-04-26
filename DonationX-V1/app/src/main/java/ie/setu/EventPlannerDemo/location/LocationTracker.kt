package ie.setu.EventPlannerDemo.location


class LocationTracker (
    private var locationClient: FusedLocationProviderClient
) : LocationService {

    @SuppressLint("MissingPermission")
    override fun getLocationFlow() = callbackFlow {

        val locationRequest = LocationRequest
            .Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(2000)
            .setMaxUpdateDelayMillis(10000)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                try {
                    trySend(result.lastLocation)
                } catch (e: Exception) {
                    Timber.tag("Error").e(e.message.toString())
                }
            }
        }

        locationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
            .addOnFailureListener { e ->
                close(e)
            }

        awaitClose {
            locationClient.removeLocationUpdates(locationCallback)
        }
    }}
