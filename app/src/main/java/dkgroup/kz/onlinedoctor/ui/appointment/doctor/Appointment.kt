package dkgroup.kz.onlinedoctor.ui.appointment.doctor

import dkgroup.kz.onlinedoctor.User
import java.util.*

class Appointment(var appointmentId: String) {
    var user: User? = null
    var date: Date? = null
    var time: Long? = null
    var status: String? = null
}
