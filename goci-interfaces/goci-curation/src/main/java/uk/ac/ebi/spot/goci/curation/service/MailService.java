package uk.ac.ebi.spot.goci.curation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.goci.model.Study;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by emma on 10/02/15.
 *
 * @author emma
 *         <p/>
 *         Provides email notification
 */
@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    // Reading these from application.properties
    @Value("${mail.from}")
    private String from;
    @Value("${mail.to}")
    private String to;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailNotification(Study study, String status) {

        // Set up some of the values used in mail body
        String studyTitle = study.getTitle();
        String pubmedLink = "http://europepmc.org/abstract/MED/" + study.getPubmedId();
        String currentCurator = study.getHousekeeping().getCurator().getLastName();

        // These could be null so catch this case
        String studyTrait = null;
        if (study.getDiseaseTrait() != null && !study.getDiseaseTrait().getTrait().isEmpty()) {
            studyTrait = study.getDiseaseTrait().getTrait();
        }

        String notes = null;
        if (study.getHousekeeping().getNotes() != null && !study.getHousekeeping().getNotes().isEmpty()) {
            notes = study.getHousekeeping().getNotes();
        }

        // Format date
        Date studyDate = study.getStudyDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String bodyStudyDate = dateFormat.format(studyDate);

        String editStudyLink = "http://orange.ebi.ac.uk:55000/studies/" + study.getId();

        // Format mail message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(getTo());
        mailMessage.setFrom(getFrom());
        mailMessage.setSubject(study.getAuthor() + " - " + status);
        mailMessage.setText("The GWAS paper by " + study.getAuthor() + " with study date " + bodyStudyDate + " now has status " + status
                + "\n" + "Title: " + studyTitle
                + "\n" + "Trait: " + studyTrait
                + "\n" + "Pubmed link: " + pubmedLink
                + "\n" + "Edit link: " + editStudyLink
                + "\n" + "Current curator: " + currentCurator
                + "\n" + "Notes: " + notes);
        javaMailSender.send(mailMessage);

    }

    // Getter and setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}