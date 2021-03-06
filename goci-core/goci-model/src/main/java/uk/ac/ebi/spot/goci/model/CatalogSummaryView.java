package uk.ac.ebi.spot.goci.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by emma on 17/02/15.
 *
 * @author emma
 */
@Entity
public class CatalogSummaryView {

    @Id
    private Long id;

    private Date catalogAddedDate;

    private String pubmedId;

    private String author;

    private Date publicationDate;

    private String journal;

    private String link;

    private String study;

    private String diseaseTrait;

    private String initialSampleDescription;

    private String replicateSampleDescription;

    private String region;

    private String chromosomeName;

    private String chromosomePosition;

    private String reportedGene;

    private String mappedGene;

    private String entrezGeneId;

    private String upstreamMappedGene;

    private String upstreamEntrezGeneId;

    private Integer upstreamGeneDistance;

    private String downstreamMappedGene;

    private String downstreamEntrezGeneId;

    private Integer downstreamGeneDistance;

    private String strongestSnpRiskAllele;

    private String snpRsid;

    private Boolean merged;

    private Long snpId;

    private String context;

    private Boolean isIntergenic;

    private String riskAlleleFrequency;

    private Float pValue;

    private String pValueQualifier;

    private Float orBeta;

    private String ci;

    private String ciQualifier;

    private String platform;

    private Boolean cnv;

    private Long associationId;

    private Long studyId;

    private Date resultPublished;

    private Long curationStatus;

    // JPA no-args constructor
    public CatalogSummaryView() {

    }

    public CatalogSummaryView(Long id,
                              Date catalogAddedDate,
                              String pubmedId,
                              String author,
                              Date publicationDate,
                              String journal,
                              String link,
                              String study,
                              String diseaseTrait,
                              String initialSampleDescription,
                              String replicateSampleDescription,
                              String region,
                              String chromosomeName,
                              String chromosomePosition,
                              String reportedGene,
                              String mappedGene,
                              String entrezGeneId,
                              String upstreamMappedGene,
                              String upstreamEntrezGeneId,
                              Integer upstreamGeneDistance,
                              String downstreamMappedGene,
                              String downstreamEntrezGeneId,
                              Integer downstreamGeneDistance,
                              String strongestSnpRiskAllele,
                              String snpRsid,
                              Boolean merged,
                              Long snpId,
                              String context,
                              Boolean isIntergenic,
                              String riskAlleleFrequency,
                              Float pValue,
                              String pValueQualifier,
                              Float orBeta,
                              String ci,
                              String ciQualifier,
                              String platform,
                              Boolean cnv,
                              Long associationId,
                              Long studyId,
                              Date resultPublished, Long curationStatus) {
        this.id = id;
        this.catalogAddedDate = catalogAddedDate;
        this.pubmedId = pubmedId;
        this.author = author;
        this.publicationDate = publicationDate;
        this.journal = journal;
        this.link = link;
        this.study = study;
        this.diseaseTrait = diseaseTrait;
        this.initialSampleDescription = initialSampleDescription;
        this.replicateSampleDescription = replicateSampleDescription;
        this.region = region;
        this.chromosomeName = chromosomeName;
        this.chromosomePosition = chromosomePosition;
        this.reportedGene = reportedGene;
        this.mappedGene = mappedGene;
        this.entrezGeneId = entrezGeneId;
        this.upstreamMappedGene = upstreamMappedGene;
        this.upstreamEntrezGeneId = upstreamEntrezGeneId;
        this.upstreamGeneDistance = upstreamGeneDistance;
        this.downstreamMappedGene = downstreamMappedGene;
        this.downstreamEntrezGeneId = downstreamEntrezGeneId;
        this.downstreamGeneDistance = downstreamGeneDistance;
        this.strongestSnpRiskAllele = strongestSnpRiskAllele;
        this.snpRsid = snpRsid;
        this.merged = merged;
        this.snpId = snpId;
        this.context = context;
        this.isIntergenic = isIntergenic;
        this.riskAlleleFrequency = riskAlleleFrequency;
        this.pValue = pValue;
        this.pValueQualifier = pValueQualifier;
        this.orBeta = orBeta;
        this.ci = ci;
        this.ciQualifier = ciQualifier;
        this.platform = platform;
        this.cnv = cnv;
        this.associationId = associationId;
        this.studyId = studyId;
        this.resultPublished = resultPublished;
        this.curationStatus = curationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCatalogAddedDate() {
        return catalogAddedDate;
    }

    public void setCatalogAddedDate(Date catalogAddedDate) {
        this.catalogAddedDate = catalogAddedDate;
    }

    public String getPubmedId() {
        return pubmedId;
    }

    public void setPubmedId(String pubmedId) {
        this.pubmedId = pubmedId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getDiseaseTrait() {
        return diseaseTrait;
    }

    public void setDiseaseTrait(String diseaseTrait) {
        this.diseaseTrait = diseaseTrait;
    }

    public String getInitialSampleDescription() {
        return initialSampleDescription;
    }

    public void setInitialSampleDescription(String initialSampleDescription) {
        this.initialSampleDescription = initialSampleDescription;
    }

    public String getReplicateSampleDescription() {
        return replicateSampleDescription;
    }

    public void setReplicateSampleDescription(String replicateSampleDescription) {
        this.replicateSampleDescription = replicateSampleDescription;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getChromosomeName() {
        return chromosomeName;
    }

    public void setChromosomeName(String chromosomeName) {
        this.chromosomeName = chromosomeName;
    }

    public String getChromosomePosition() {
        return chromosomePosition;
    }

    public void setChromosomePosition(String chromosomePosition) {
        this.chromosomePosition = chromosomePosition;
    }

    public String getReportedGene() {
        return reportedGene;
    }

    public void setReportedGene(String reportedGene) {
        this.reportedGene = reportedGene;
    }

    public String getMappedGene() {
        return mappedGene;
    }

    public void setMappedGene(String mappedGene) {
        this.mappedGene = mappedGene;
    }

    public String getEntrezGeneId() {
        return entrezGeneId;
    }

    public void setEntrezGeneId(String entrezGeneId) {
        this.entrezGeneId = entrezGeneId;
    }

    public String getUpstreamMappedGene() {
        return upstreamMappedGene;
    }

    public void setUpstreamMappedGene(String upstreamMappedGene) {
        this.upstreamMappedGene = upstreamMappedGene;
    }

    public String getUpstreamEntrezGeneId() {
        return upstreamEntrezGeneId;
    }

    public void setUpstreamEntrezGeneId(String upstreamEntrezGeneId) {
        this.upstreamEntrezGeneId = upstreamEntrezGeneId;
    }

    public Integer getUpstreamGeneDistance() {
        return upstreamGeneDistance;
    }

    public void setUpstreamGeneDistance(Integer upstreamGeneDistance) {
        this.upstreamGeneDistance = upstreamGeneDistance;
    }

    public String getDownstreamMappedGene() {
        return downstreamMappedGene;
    }

    public void setDownstreamMappedGene(String downstreamMappedGene) {
        this.downstreamMappedGene = downstreamMappedGene;
    }

    public String getDownstreamEntrezGeneId() {
        return downstreamEntrezGeneId;
    }

    public void setDownstreamEntrezGeneId(String downstreamEntrezGeneId) {
        this.downstreamEntrezGeneId = downstreamEntrezGeneId;
    }

    public Integer getDownstreamGeneDistance() {
        return downstreamGeneDistance;
    }

    public void setDownstreamGeneDistance(Integer downstreamGeneDistance) {
        this.downstreamGeneDistance = downstreamGeneDistance;
    }

    public String getStrongestSnpRiskAllele() {
        return strongestSnpRiskAllele;
    }

    public void setStrongestSnpRiskAllele(String strongestSnpRiskAllele) {
        this.strongestSnpRiskAllele = strongestSnpRiskAllele;
    }

    public String getSnpRsid() {
        return snpRsid;
    }

    public void setSnpRsid(String snpRsid) {
        this.snpRsid = snpRsid;
    }

    public Boolean getMerged() {
        return merged;
    }

    public void setMerged(Boolean merged) {
        this.merged = merged;
    }

    public Long getSnpId() {
        return snpId;
    }

    public void setSnpId(Long snpId) {
        this.snpId = snpId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Boolean getIsIntergenic() {
        return isIntergenic;
    }

    public void setIsIntergenic(Boolean isIntergenic) {
        this.isIntergenic = isIntergenic;
    }

    public String getRiskAlleleFrequency() {
        return riskAlleleFrequency;
    }

    public void setRiskAlleleFrequency(String riskAlleleFrequency) {
        this.riskAlleleFrequency = riskAlleleFrequency;
    }

    public Float getpValue() {
        return pValue;
    }

    public void setpValue(Float pValue) {
        this.pValue = pValue;
    }

    public String getpValueQualifier() {
        return pValueQualifier;
    }

    public void setpValueQualifier(String pValueQualifier) {
        this.pValueQualifier = pValueQualifier;
    }

    public Float getOrBeta() {
        return orBeta;
    }

    public void setOrBeta(Float orBeta) {
        this.orBeta = orBeta;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getCiQualifier() {
        return ciQualifier;
    }

    public void setCiQualifier(String ciQualifier) {
        this.ciQualifier = ciQualifier;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Boolean getCnv() {
        return cnv;
    }

    public void setCnv(Boolean cnv) {
        this.cnv = cnv;
    }

    public Long getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Long associationId) {
        this.associationId = associationId;
    }

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }

    public Date getResultPublished() {
        return resultPublished;
    }

    public void setResultPublished(Date resultPublished) {
        this.resultPublished = resultPublished;
    }

    public Long getCurationStatus() {
        return curationStatus;
    }

    public void setCurationStatus(Long curationStatus) {
        this.curationStatus = curationStatus;
    }

    @Override public String toString() {
        return "CatalogSummaryView{" +
                "id=" + id +
                ", catalogAddedDate=" + catalogAddedDate +
                ", pubmedId='" + pubmedId + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                ", journal='" + journal + '\'' +
                ", link='" + link + '\'' +
                ", study='" + study + '\'' +
                ", diseaseTrait='" + diseaseTrait + '\'' +
                ", initialSampleDescription='" + initialSampleDescription + '\'' +
                ", replicateSampleDescription='" + replicateSampleDescription + '\'' +
                ", region='" + region + '\'' +
                ", chromosomeName='" + chromosomeName + '\'' +
                ", chromosomePosition='" + chromosomePosition + '\'' +
                ", reportedGene='" + reportedGene + '\'' +
                ", mappedGene='" + mappedGene + '\'' +
                ", entrezGeneId='" + entrezGeneId + '\'' +
                ", upstreamMappedGene='" + upstreamMappedGene + '\'' +
                ", upstreamEntrezGeneId='" + upstreamEntrezGeneId + '\'' +
                ", upstreamGeneDistance=" + upstreamGeneDistance +
                ", downstreamMappedGene='" + downstreamMappedGene + '\'' +
                ", downstreamEntrezGeneId='" + downstreamEntrezGeneId + '\'' +
                ", downstreamGeneDistance=" + downstreamGeneDistance +
                ", strongestSnpRiskAllele='" + strongestSnpRiskAllele + '\'' +
                ", snpRsid='" + snpRsid + '\'' +
                ", merged=" + merged +
                ", snpId=" + snpId +
                ", context='" + context + '\'' +
                ", isIntergenic=" + isIntergenic +
                ", riskAlleleFrequency='" + riskAlleleFrequency + '\'' +
                ", pValue=" + pValue +
                ", pValueQualifier='" + pValueQualifier + '\'' +
                ", orBeta=" + orBeta +
                ", ci='" + ci + '\'' +
                ", ciQualifier='" + ciQualifier + '\'' +
                ", platform='" + platform + '\'' +
                ", cnv=" + cnv +
                ", associationId=" + associationId +
                ", studyId=" + studyId +
                ", resultPublished=" + resultPublished +
                ", curationStatus=" + curationStatus +
                '}';
    }
}