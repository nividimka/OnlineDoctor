package dkgroup.kz.onlinedoctor.di


import dagger.Module
import dagger.android.ContributesAndroidInjector
import dkgroup.kz.onlinedoctor.ui.MainActivity
import dkgroup.kz.onlinedoctor.ui.NavigationDrawerFragment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.DoctorAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.canceled.CanceledAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.completed.CompletedAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.details.AppointmentDetailsFragment
import dkgroup.kz.onlinedoctor.ui.appointment.doctor.upcoming.UpcomingAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.appointment.patient.PatientAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.appointment.patient.canceled.PatientCanceledAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.appointment.patient.completed.PatientCompletedAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.appointment.patient.details.PatientAppointmentDetailsFragment
import dkgroup.kz.onlinedoctor.ui.appointment.patient.upcoming.PatientUpcomingAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.auth.AuthActivity
import dkgroup.kz.onlinedoctor.ui.auth.EntryFragment
import dkgroup.kz.onlinedoctor.ui.auth.SignInFragment
import dkgroup.kz.onlinedoctor.ui.auth.SignUpFragment
import dkgroup.kz.onlinedoctor.ui.consultation.DiseaseFragment
import dkgroup.kz.onlinedoctor.ui.consultation.HelpdeskFragment
import dkgroup.kz.onlinedoctor.ui.consultation.OnlineConsultationFragment
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.DoctorListFragment
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.doctor_details.DoctorDetailsFragment
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.doctor_details.book_appointment.BookAppointmentFragment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.DoctorConsultationFragment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.PatientsFragment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.PatientDetailsFragment
import dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.doctor_note.DoctorPatientNotesFragment
import dkgroup.kz.onlinedoctor.ui.medical_card.MedicalCardFragment
import dkgroup.kz.onlinedoctor.ui.medical_card.notes.DoctorNotesFragment
import dkgroup.kz.onlinedoctor.ui.medical_card.patient_info.PatientInfoFragment
import dkgroup.kz.onlinedoctor.ui.notification.NotificationFragment
import dkgroup.kz.onlinedoctor.ui.support.SupportFragment

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindNavigationDrawerFragment(): NavigationDrawerFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindOnlineConsultationFragment(): OnlineConsultationFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindNotificationFragment(): NotificationFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindEntryFragment(): EntryFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindSignInFragment(): SignInFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindSignUpFragment(): SignUpFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindDoctorListFragment(): DoctorListFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindDoctorDetailsFragment(): DoctorDetailsFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindBookDetailsFragment(): BookAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindDiseaseFragment(): DiseaseFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindHelpdeskFragment(): HelpdeskFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindDoctorConsultationFragment(): DoctorConsultationFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindSupportFragment(): SupportFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindPatientAppointmentFragment(): PatientAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindDoctorAppointmentFragment(): DoctorAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMedicalCardFragment(): MedicalCardFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindPatientInfoFragment(): PatientInfoFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindDoctorNotesFragment(): DoctorNotesFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindCompletedAppointmentFragment(): CompletedAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindUpcomingAppointmentFragment(): UpcomingAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindCanceledAppointmentFragment(): CanceledAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindAppointmentDetailsFragment(): AppointmentDetailsFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindPatientCompletedAppointmentFragment(): PatientCompletedAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindPatientUpcomingAppointmentFragment(): PatientUpcomingAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindPatientCanceledAppointmentFragment(): PatientCanceledAppointmentFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindPatientAppointmentDetailsFragment(): PatientAppointmentDetailsFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindPatientsFragment(): PatientsFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindPatientDetailsFragment(): PatientDetailsFragment

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindDoctorPatientNotesFragment(): DoctorPatientNotesFragment

}