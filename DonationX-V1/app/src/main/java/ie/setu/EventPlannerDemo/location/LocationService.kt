package ie.setu.EventPlannerDemo.location

interface LocationService {
    fun getLocationFlow(): Flow<Location?>
}
