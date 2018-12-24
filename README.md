# transport API

## Design approach
1. The application is developed using Spring boot + Kotlin.
1. CSV data is ingested into in memory H2 database.
1. Since, delays need to be taken into account while getting vehicle arriving next at a stop, the time operations are done using native query. Unfortunately spring data JPA JPQL does not support timestamp operations.

## Assumptions
1. The unit of line delay is minutes.
1. Timezone conversions are not considered. All the timings are local.

## Running the application
1. mvn spring-boot:run
