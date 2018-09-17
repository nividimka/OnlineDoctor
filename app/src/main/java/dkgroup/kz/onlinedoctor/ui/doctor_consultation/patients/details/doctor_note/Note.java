package dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.doctor_note;

import java.util.Date;

public class Note {
    String text;
    Date date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
