package uk.ac.ebi.fgpt.goci.pussycat.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: dwelter
 * Date: 26/09/12
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
public class TraitSummary {

    private ArrayList<SNPSummary> summary;
    private String gwasTrait, chromBand;

    public TraitSummary(){
        this.gwasTrait = null;
        this.chromBand = null;
        summary = new ArrayList<SNPSummary>();
    }

    public void setGwasTrait(String gwasTrait){
        this.gwasTrait = gwasTrait;
    }

    public String getGwasTrait(){
        return gwasTrait;
    }

    public void setChromBand(String chromBand){
        this.chromBand = chromBand;
    }

    public String getChromBand(){
        return chromBand;
    }


    public ArrayList<SNPSummary> getSNPSummaries(){
        return summary;
    }

    public void addSNP(String study, String author, String date, String snp, String pval, String efotrait, String efouri){
        SNPSummary snpSummary = new SNPSummary(study, author, date, snp,pval,efotrait,efouri);
        summary.add(snpSummary);
    }

    public class SNPSummary{
        private String study, author, date, snp, pval, efotrait, efouri;

        public SNPSummary(String study, String author, String date, String snp, String pval, String efotrait, String efouri){
            this.study = study;
            this.author = author;
            this.date = date;
            this.snp = snp;
            this.pval = pval;
            this.efotrait = efotrait;
            this.efouri = efouri;
        }

        public String getStudy(){
            return study;
        }

        public String getSNP(){
            return snp;
        }

        public String getPval(){
            return pval;
        }

        public String getEfotrait(){
            return efotrait;
        }

        public String getEfouri(){
            return efouri;
        }

        public String getAuthor(){
            return author;
        }

        public String getDate(){
            return date;
        }
    }
}