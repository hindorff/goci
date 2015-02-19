/*

################################################################################
Create a view table that joins across all the salient tables to produce a single
summary view over all the data in the catalogue.  A dump of the entire contents
of this view should produce something appropriate to expose as a catalogue
download

Designed for execution with Flyway database migrations tool.

author:  Emma Hastings
date:    February 17th 2015
version: 1.9.9.029 (pre 2.0)
################################################################################

*/


--------------------------------------------------------
-- Generate view
--------------------------------------------------------

CREATE OR REPLACE VIEW CATALOG_SUMMARY_VIEW (
  ID,
  CATALOG_ADDED_DATE,
  PUBMED_ID,
  AUTHOR,
  PUBLICATION_DATE,
  JOURNAL,
  LINK,
  STUDY,
  DISEASE_TRAIT,
  INITIAL_SAMPLE_DESCRIPTION,
  REPLICATE_SAMPLE_DESCRIPTION,
  REGION,
  CHROMOSOME_NAME,
  CHROMOSOME_POSITION,
  REPORTED_GENE,
  MAPPED_GENE,
  ENTREZ_GENE_ID,
  UPSTREAM_MAPPED_GENE,
  UPSTREAM_ENTREZ_GENE_ID,
  UPSTREAM_GENE_DISTANCE,
  DOWNSTREAM_MAPPED_GENE,
  DOWNSTREAM_ENTREZ_GENE_ID,
  DOWNSTREAM_GENE_DISTANCE,
  STRONGEST_SNP_RISK_ALLELE,
  SNP_RSID,
  MERGED,
  SNP_ID,
  CONTEXT,
  IS_INTERGENIC,
  RISK_ALLELE_FREQUENCY,
  P_VALUE,
  P_VALUE_QUALIFIER,
  OR_BETA,
  CI,
  CI_QUALIFIER,
  PLATFORM,
  CNV,
  ASSOCIATION_ID,
  STUDY_ID,
  RESULT_PUBLISHED,
  CURATION_STATUS)
  AS SELECT ROWNUM, V.* FROM
    (SELECT
    h.STUDY_ADDED_DATE AS CATALOG_ADDED_DATE,
    s.PUBMED_ID,
    s.AUTHOR,
    s.STUDY_DATE AS PUBLICATION_DATE,
    s.PUBLICATION AS JOURNAL,
    CONCAT('http://europepmc.org/abstract/MED/', s.PUBMED_ID) AS LINK,
    s.TITLE AS STUDY,
    dt.TRAIT AS DISEASE_TRAIT,
    s.INITIAL_SAMPLE_SIZE AS INITIAL_SAMPLE_DESCRIPTION,
    s.REPLICATE_SAMPLE_SIZE AS REPLICATE_SAMPLE_DESCRIPTION,
    r.NAME AS REGION,
    snp.CHROMOSOME_NAME,
    snp.CHROMOSOME_POSITION,
    rg.GENE_NAME AS REPORTED_GENE,
    img.GENE_NAME AS MAPPED_GENE,
    igc.ENTREZ_GENE_ID,
    umg.GENE_NAME AS UPSTREAM_MAPPED_GENE,
    ugc.ENTREZ_GENE_ID AS UPSTREAM_ENTREZ_GENE_ID,
    ugc.DISTANCE AS UPSTREAM_GENE_DISTANCE,
    dmg.GENE_NAME AS DOWNSTREAM_MAPPED_GENE,
    dgc.ENTREZ_GENE_ID AS DOWNSTREAM_ENTREZ_GENE_ID,
    dgc.DISTANCE AS DOWNSTREAM_GENE_DISTANCE,
    ra.RISK_ALLELE_NAME AS STRONGEST_SNP_RISK_ALLELE,
    snp.RS_ID AS SNP_RS_ID,
    snp.MERGED,
    snp.ID AS SNP_ID,
    snp.FUNCTIONAL_CLASS AS CONTEXT,
    igc.IS_INTERGENIC,
    a.RISK_FREQUENCY AS RISK_ALLELE_FREQUENCY,
    a.PVALUE_FLOAT AS P_VALUE,
    a.PVALUE_TEXT AS P_VALUE_QUALIFIER,
    a.OR_PER_COPY_NUM AS OR_BETA,
    a.OR_PER_COPY_RANGE AS CI,
    a.OR_PER_COPY_UNIT_DESCR AS CI_QUALIFIER,
    s.PLATFORM,
    s.CNV,
    a.ID AS ASSOCIATION_ID,
    s.ID AS STUDY_ID,
    h.PUBLISH_DATE AS RESULT_PUBLISHED,
    h.CURATION_STATUS_ID as CURATION_STATUS
    FROM STUDY s
    JOIN HOUSEKEEPING h ON h.ID = s.HOUSEKEEPING_ID
    JOIN STUDY_DISEASE_TRAIT sdt ON sdt.STUDY_ID = s.ID
    JOIN DISEASE_TRAIT dt ON dt.ID = sdt.DISEASE_TRAIT_ID
    JOIN ASSOCIATION a ON a.STUDY_ID = s.ID
    JOIN ASSOCIATION_LOCUS al ON al.ASSOCIATION_ID = a.ID
    JOIN LOCUS_RISK_ALLELE lra ON lra.LOCUS_ID = al.LOCUS_ID
    JOIN RISK_ALLELE ra ON ra.ID = lra.RISK_ALLELE_ID
    JOIN RISK_ALLELE_SNP ras ON ras.RISK_ALLELE_ID = lra.RISK_ALLELE_ID
    JOIN SINGLE_NUCLEOTIDE_POLYMORPHISM snp ON snp.ID = ras.SNP_ID
    JOIN SNP_REGION sr ON sr.SNP_ID = snp.ID
    JOIN REGION r ON r.ID = sr.REGION_ID
    LEFT JOIN AUTHOR_REPORTED_GENE arg ON arg.LOCUS_ID = al.LOCUS_ID
    LEFT JOIN GENE rg ON rg.ID = arg.REPORTED_GENE_ID
    LEFT JOIN GENOMIC_CONTEXT ugc ON ugc.SNP_ID = snp.ID AND ugc.IS_UPSTREAM = '1'
    LEFT JOIN GENOMIC_CONTEXT dgc ON dgc.SNP_ID = snp.ID AND dgc.IS_DOWNSTREAM = '1'
    LEFT JOIN GENOMIC_CONTEXT igc ON igc.SNP_ID = snp.ID AND igc.IS_INTERGENIC = '0'
    LEFT JOIN GENE umg ON umg.ID = ugc.GENE_ID
    LEFT JOIN GENE dmg ON dmg.ID = dgc.GENE_ID
    LEFT JOIN GENE img ON img.ID = igc.GENE_ID
    ORDER BY s.STUDY_DATE DESC, CHROMOSOME_NAME ASC, CHROMOSOME_POSITION ASC) V