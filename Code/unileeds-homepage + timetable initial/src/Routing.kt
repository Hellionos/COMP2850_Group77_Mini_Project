import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.pebble.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {

        staticResources("/static", "static")

        get("/") {
            call.respond(
                PebbleContent(
                    "home.peb",emptyMap()
                )
            )
        }
        get("/timetable") {
            val day = call.request.queryParameters["day"] ?: "Monday"

            val selectedCourses = courses.filter { it.day == day }

            call.respond(
                PebbleContent(
                    "timetable.peb",
                    mapOf(
                        "day" to day,
                        "courses" to selectedCourses
                    )
                )
            )
        }
    }
}
