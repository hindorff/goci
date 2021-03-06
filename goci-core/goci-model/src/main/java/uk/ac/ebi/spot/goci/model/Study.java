package uk.ac.ebi.spot.goci.model;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


/**
 * Created by emma on 20/11/14.
 *
 * @author emma
 *         <p>
 *         Model of a GWAS study
 */

@Entity
public class Study {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Please enter an author")
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date studyDate;

    @NotBlank(message = "Please enter a publication")
    private String publication;

    @NotBlank(message = "Please enter a title")
    private String title;

    private String initialSampleSize;

    private String replicateSampleSize;

    private String platform;

    @NotBlank(message = "Please enter a pubmed id")
    private String pubmedId;

    // Defaults set as false
    private Boolean cnv=false;

    private Boolean gxe=false;

    private Boolean gxg=false;

    @OneToMany(mappedBy = "study")
    private Collection<Association> associations;

    @ManyToOne
    @JoinTable(name = "STUDY_DISEASE_TRAIT",
            joinColumns = @JoinColumn(name = "STUDY_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISEASE_TRAIT_ID"))
    private DiseaseTrait diseaseTrait;

    @ManyToMany
    @JoinTable(name = "STUDY_EFO_TRAIT",
            joinColumns = @JoinColumn(name = "STUDY_ID"),
            inverseJoinColumns = @JoinColumn(name = "EFO_TRAIT_ID"))
    private Collection<EfoTrait> efoTraits;

    @ManyToMany
    @JoinTable(name = "STUDY_SNP",
            joinColumns = @JoinColumn(name = "STUDY_ID"),
            inverseJoinColumns = @JoinColumn(name = "SNP_ID"))
    private Collection<SingleNucleotidePolymorphism> singleNucleotidePolymorphisms;

    @OneToOne
    private Housekeeping housekeeping;

    @OneToOne(mappedBy = "study", cascade = CascadeType.REMOVE)
    private StudyReport studyReport;

    // JPA no-args constructor
    public Study() {
    }


    public Study(String author, Date studyDate, String publication, String title, String initialSampleSize, String replicateSampleSize, String platform, String pubmedId, Boolean cnv, Boolean gxe, Boolean gxg, DiseaseTrait diseaseTrait, Collection<EfoTrait> efoTraits, Collection<SingleNucleotidePolymorphism> singleNucleotidePolymorphisms, Housekeeping housekeeping) {
        this.author = author;
        this.studyDate = studyDate;
        this.publication = publication;
        this.title = title;
        this.initialSampleSize = initialSampleSize;
        this.replicateSampleSize = replicateSampleSize;
        this.platform = platform;
        this.pubmedId = pubmedId;
        this.cnv = cnv;
        this.gxe = gxe;
        this.gxg = gxg;
        this.diseaseTrait = diseaseTrait;
        this.efoTraits = efoTraits;
        this.singleNucleotidePolymorphisms = singleNucleotidePolymorphisms;
        this.housekeeping = housekeeping;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInitialSampleSize() {
        return initialSampleSize;
    }

    public void setInitialSampleSize(String initialSampleSize) {
        this.initialSampleSize = initialSampleSize;
    }

    public String getReplicateSampleSize() {
        return replicateSampleSize;
    }

    public void setReplicateSampleSize(String replicateSampleSize) {
        this.replicateSampleSize = replicateSampleSize;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPubmedId() {
        return pubmedId;
    }

    public void setPubmedId(String pubmedId) {
        this.pubmedId = pubmedId;
    }

    public Boolean getCnv() {
        return cnv;
    }

    public void setCnv(Boolean cnv) {
        this.cnv = cnv;
    }

    public Boolean getGxe() {
        return gxe;
    }

    public void setGxe(Boolean gxe) {
        this.gxe = gxe;
    }

    public Boolean getGxg() {
        return gxg;
    }

    public void setGxg(Boolean gxg) {
        this.gxg = gxg;
    }

    public Collection<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(Collection<Association> associations) {
        this.associations = associations;
    }

    public DiseaseTrait getDiseaseTrait() {
        return diseaseTrait;
    }

    public void setDiseaseTrait(DiseaseTrait diseaseTrait) {
        this.diseaseTrait = diseaseTrait;
    }

    public Collection<EfoTrait> getEfoTraits() {
        return efoTraits;
    }

    public void setEfoTraits(Collection<EfoTrait> efoTraits) {
        this.efoTraits = efoTraits;
    }

    public Collection<SingleNucleotidePolymorphism> getSingleNucleotidePolymorphisms() {
        return singleNucleotidePolymorphisms;
    }

    public void setSingleNucleotidePolymorphisms(Collection<SingleNucleotidePolymorphism> singleNucleotidePolymorphisms) {
        this.singleNucleotidePolymorphisms = singleNucleotidePolymorphisms;
    }

    public Housekeeping getHousekeeping() {
        return housekeeping;
    }

    public void setHousekeeping(Housekeeping housekeeping) {
        this.housekeeping = housekeeping;
    }

    public StudyReport getStudyReport() {
        return studyReport;
    }

    public void setStudyReport(StudyReport studyReport) {
        this.studyReport = studyReport;
    }

    @Override public String toString() {
        return "Study{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", studyDate=" + studyDate +
                ", publication='" + publication + '\'' +
                ", title='" + title + '\'' +
                ", pubmedId='" + pubmedId + '\'' +
                '}';
    }
}
