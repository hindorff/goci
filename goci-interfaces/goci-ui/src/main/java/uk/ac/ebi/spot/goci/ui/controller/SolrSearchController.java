package uk.ac.ebi.spot.goci.ui.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.spot.goci.ui.SearchConfiguration;
import uk.ac.ebi.spot.goci.ui.exception.IllegalParameterCombinationException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Javadocs go here!
 *
 * @author Tony Burdett
 * @date 31/01/15
 */
@Controller
public class SolrSearchController {
    private SearchConfiguration searchConfiguration;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public SolrSearchController(SearchConfiguration searchConfiguration) {
        this.searchConfiguration = searchConfiguration;
    }

    protected Logger getLog() {
        return log;
    }

    @RequestMapping(value = "api/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doSolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "group", required = false, defaultValue = "false") boolean useGroups,
            @RequestParam(value = "group.by", required = false) String groupBy,
            @RequestParam(value = "group.limit", required = false, defaultValue = "10") int groupLimit,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        addFacet(solrSearchBuilder, searchConfiguration.getDefaultFacet());
        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        if (useGroups) {
            addGrouping(solrSearchBuilder, groupBy, groupLimit);
        }
        else {
            addRowsAndPage(solrSearchBuilder, maxResults, page);
        }
        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    @RequestMapping(value = "api/search/study", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doStudySolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        addRowsAndPage(solrSearchBuilder, maxResults, page);
        addFilterQuery(solrSearchBuilder, searchConfiguration.getDefaultFacet(), "Study");
        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    @RequestMapping(value = "api/search/singlenucleotidepolymorphism", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doSnpSolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        addRowsAndPage(solrSearchBuilder, maxResults, page);
        addFilterQuery(solrSearchBuilder,
                       searchConfiguration.getDefaultFacet(),
                       "SingleNucleotidePolymorphism");
        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    @RequestMapping(value = "api/search/association", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doAssociationSolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        addRowsAndPage(solrSearchBuilder, maxResults, page);
        addFilterQuery(solrSearchBuilder, searchConfiguration.getDefaultFacet(), "Association");
        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    @RequestMapping(value = "api/search/diseasetrait", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doTraitSolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        addRowsAndPage(solrSearchBuilder, maxResults, page);
        addFilterQuery(solrSearchBuilder, searchConfiguration.getDefaultFacet(), "DiseaseTrait");
        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    @RequestMapping(value = "api/search/traitcount", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doTraitCountSolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "facet.mincount", required = false, defaultValue = "1") int mincount,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        addRowsAndPage(solrSearchBuilder, maxResults, page);
        addFacet(solrSearchBuilder, "traitName_s");
        addFacetMincount(solrSearchBuilder, mincount);
        addFilterQuery(solrSearchBuilder, "resourcename", "study");
        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    private StringBuilder buildBaseSearchRequest() {
        // build base request
        StringBuilder solrSearchBuilder = new StringBuilder();
        solrSearchBuilder.append(searchConfiguration.getGwasSearchServer().toString())
                .append("/select?")
                .append("wt=json");
        return solrSearchBuilder;
    }

    @RequestMapping(value = "api/search/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doFilterSolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "group", required = false, defaultValue = "false") boolean useGroups,
            @RequestParam(value = "group.by", required = false) String groupBy,
            @RequestParam(value = "group.limit", required = false, defaultValue = "10") int groupLimit,
            @RequestParam(value = "pvalfilter", required = false) String pvalRange,
            @RequestParam(value = "orfilter", required = false) String orRange,
            @RequestParam(value = "betafilter", required = false) String betaRange,
            @RequestParam(value = "datefilter", required = false) String dateRange,
            @RequestParam(value = "traitfilter[]", required = false) String[] traits,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        addFacet(solrSearchBuilder, searchConfiguration.getDefaultFacet());
        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        if (useGroups) {
            addGrouping(solrSearchBuilder, groupBy, groupLimit);
        }
        else {
            addRowsAndPage(solrSearchBuilder, maxResults, page);
        }

        if (pvalRange != "") {
            getLog().debug(pvalRange);
            addFilterQuery(solrSearchBuilder, "pValue", pvalRange);
        }
        /**TO DO - when we split OR and beta, modify this controller to reflect that change!!***/
        if (orRange != "") {
            getLog().debug(orRange);

            addFilterQuery(solrSearchBuilder, "orPerCopyNum", orRange);
            addFilterQuery(solrSearchBuilder, "orType", "true");
        }
        if (betaRange != "") {
            getLog().debug(betaRange);

            addFilterQuery(solrSearchBuilder, "orPerCopyNum", betaRange);
            addFilterQuery(solrSearchBuilder, "orType", "false");
        }
        if (dateRange != "") {
            getLog().debug(dateRange);

            addFilterQuery(solrSearchBuilder, "publicationDate", dateRange);
        }
        if (traits != null) {
            System.out.println(String.valueOf(traits));

            addFilterQuery(solrSearchBuilder, "traitName_s", traits);
        }

        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    @RequestMapping(value = "api/search/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doSortSolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "facet", required = false) String facet,
            @RequestParam(value = "group", required = false, defaultValue = "false") boolean useGroups,
            @RequestParam(value = "group.by", required = false) String groupBy,
            @RequestParam(value = "group.limit", required = false, defaultValue = "10") int groupLimit,
            @RequestParam(value = "pvalfilter", required = false) String pvalRange,
            @RequestParam(value = "orfilter", required = false) String orRange,
            @RequestParam(value = "betafilter", required = false) String betaRange,
            @RequestParam(value = "datefilter", required = false) String dateRange,
            @RequestParam(value = "traitfilter[]", required = false) String[] traits,
            @RequestParam(value = "sort", required = false) String sort,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        addFacet(solrSearchBuilder, searchConfiguration.getDefaultFacet());
        addFilterQuery(solrSearchBuilder, "resourcename", facet);
        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        if (useGroups) {
            addGrouping(solrSearchBuilder, groupBy, groupLimit);
        }
        else {
            addRowsAndPage(solrSearchBuilder, maxResults, page);
        }

        if (pvalRange != "") {
            getLog().debug(pvalRange);
            addFilterQuery(solrSearchBuilder, "pValue", pvalRange);
        }
        /**TO DO - when we split OR and beta, modify this controller to reflect that change!!***/
        if (orRange != "") {
            getLog().debug(orRange);

            addFilterQuery(solrSearchBuilder, "orPerCopyNum", orRange);
            addFilterQuery(solrSearchBuilder, "orType", "true");
        }
        if (betaRange != "") {
            getLog().debug(betaRange);

            addFilterQuery(solrSearchBuilder, "orPerCopyNum", betaRange);
            addFilterQuery(solrSearchBuilder, "orType", "false");
        }
        if (dateRange != "") {
            getLog().debug(dateRange);

            addFilterQuery(solrSearchBuilder, "publicationDate", dateRange);
        }
        if (traits != null) {
            System.out.println(String.valueOf(traits));

            addFilterQuery(solrSearchBuilder, "traitName_s", traits);
        }
        if(sort != ""){
            addSortQuery(solrSearchBuilder, sort);
        }

        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    @RequestMapping(value = "api/search/moreresults", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doMoreResultsSolrSearch(
            @RequestParam("q") String query,
            @RequestParam(value = "jsonp", required = false, defaultValue = "false") boolean useJsonp,
            @RequestParam(value = "callback", required = false) String callbackFunction,
            @RequestParam(value = "max", required = false, defaultValue = "10") int maxResults,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "facet", required = false) String facet,
            @RequestParam(value = "pvalfilter", required = false) String pvalRange,
            @RequestParam(value = "orfilter", required = false) String orRange,
            @RequestParam(value = "betafilter", required = false) String betaRange,
            @RequestParam(value = "datefilter", required = false) String dateRange,
            @RequestParam(value = "traitfilter[]", required = false) String[] traits,
            @RequestParam(value = "sort", required = false) String sort,
            HttpServletResponse response) throws IOException {
        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        if (useJsonp) {
            addJsonpCallback(solrSearchBuilder, callbackFunction);
        }
        addFilterQuery(solrSearchBuilder, "resourcename", facet);
         addRowsAndPage(solrSearchBuilder, maxResults, page);


        if (pvalRange != "") {
            getLog().debug(pvalRange);
            addFilterQuery(solrSearchBuilder, "pValue", pvalRange);
        }
        /**TO DO - when we split OR and beta, modify this controller to reflect that change!!***/
        if (orRange != "") {
            getLog().debug(orRange);

            addFilterQuery(solrSearchBuilder, "orPerCopyNum", orRange);
            addFilterQuery(solrSearchBuilder, "orType", "true");
        }
        if (betaRange != "") {
            getLog().debug(betaRange);

            addFilterQuery(solrSearchBuilder, "orPerCopyNum", betaRange);
            addFilterQuery(solrSearchBuilder, "orType", "false");
        }
        if (dateRange != "") {
            getLog().debug(dateRange);

            addFilterQuery(solrSearchBuilder, "publicationDate", dateRange);
        }
        if (traits != null) {
            System.out.println(String.valueOf(traits));

            addFilterQuery(solrSearchBuilder, "traitName_s", traits);
        }

        if(sort != ""){
            addSortQuery(solrSearchBuilder, sort);
        }

        addQuery(solrSearchBuilder, query);

        // dispatch search
        dispatchSearch(solrSearchBuilder.toString(), response.getOutputStream());
    }

    private void addSortQuery(StringBuilder solrSearchBuilder, String sort) {
        solrSearchBuilder.append("&sort=").append(sort);
    }

    private void addFacet(StringBuilder solrSearchBuilder, String facet) {
        // add configuration
        solrSearchBuilder.append("&facet=true&facet.field=").append(facet);
    }

    private void addFacetMincount(StringBuilder solrSearchBuilder, int min){
        solrSearchBuilder.append("&facet.mincount=").append(min);
    }

    private void addJsonpCallback(StringBuilder solrSearchBuilder, String callbackFunction) {
        if (callbackFunction == null) {
            throw new IllegalParameterCombinationException("If jsonp = true, you must specify a callback function " +
                                                                   "name with 'callback' parameter");
        }
        else {
            solrSearchBuilder.append("&json.wrf=").append(callbackFunction);
        }
    }

    private void addGrouping(StringBuilder solrSearchBuilder, String groupBy, int maxResults) {
        solrSearchBuilder.append("&rows=10000")
                .append("&group=true")
                .append("&group.limit=").append(maxResults)
                .append("&group.field=").append(groupBy);
    }

    private void addRowsAndPage(StringBuilder solrSearchBuilder, int maxResults, int page) {
        solrSearchBuilder.append("&rows=").append(maxResults)
                .append("&start=").append((page - 1) * maxResults);
    }

    private void addFilterQuery(StringBuilder solrSearchBuilder, String filterOn, String filterBy) {
        solrSearchBuilder.append("&fq=").append(filterOn).append("%3A").append(filterBy);
    }

    private void addFilterQuery(StringBuilder solrSearchBuilder, String filterOn, String[] filterBy) {
        int counter = 0;
        String filterString = "";
        for(String filter : filterBy) {
            if(counter == 0){
                filterString = filterString.concat(filterOn).concat("%3A%22").concat(filter).concat("%22");
                counter++;
            } else{
                filterString = filterString.concat("+OR+").concat(filterOn).concat("%3A%22").concat(filter).concat("%22");
                counter++;
            }
        }
        System.out.println(filterString);
        solrSearchBuilder.append("&fq=").append(filterString);

    }

    private void addQuery(StringBuilder solrSearchBuilder, String query) throws IOException {
        try {
            solrSearchBuilder.append("&q=").append(URLEncoder.encode(query, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            throw new IOException("Invalid query string - " + query, e);
        }
    }

    private void dispatchSearch(String searchString, OutputStream out) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(searchString);
        if (System.getProperty("http.proxyHost") != null) {
            HttpHost proxy;
            if (System.getProperty("http.proxyPort") != null) {
                proxy = new HttpHost(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty
                        ("http.proxyPort")));
            }
            else {
                proxy = new HttpHost(System.getProperty("http.proxyHost"));
            }
            httpGet.setConfig(RequestConfig.custom().setProxy(proxy).build());
        }

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            getLog().debug("Received HTTP response: " + response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            entity.writeTo(out);
            EntityUtils.consume(entity);
        }
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private String handleIllegalParameterCombinationException(IllegalParameterCombinationException e) {
        getLog().error("An illegal parameter combination was received", e);
        return "An illegal parameter combination was received: " + e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private String handleIOException(IOException e) {
        getLog().error("An IOException occurred during solr search communication", e);
        return "Your search could not be performed - we encountered a problem.  We've been notified and will attempt " +
                "to rectify the problem as soon as possible.  If problems persist, please email gwas-info@ebi.ac.uk";
    }

    @RequestMapping(value = "api/search/downloads", produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String getSearchResults(
            @RequestParam("q") String query,
            @RequestParam(value = "pvalfilter", required = false) String pvalRange,
            @RequestParam(value = "orfilter", required = false) String orRange,
            @RequestParam(value = "betafilter", required = false) String betaRange,
            @RequestParam(value = "datefilter", required = false) String dateRange,
            @RequestParam(value = "traitfilter[]", required = false) String[] traits
        ) throws IOException {

        StringBuilder solrSearchBuilder = buildBaseSearchRequest();

        int maxResults = 10000;
        int page = 1;
        String facet = "association";
        addFilterQuery(solrSearchBuilder, "resourcename", facet);
        addRowsAndPage(solrSearchBuilder, maxResults, page);


        if (pvalRange != "") {
            getLog().debug(pvalRange);
            addFilterQuery(solrSearchBuilder, "pValue", pvalRange);
        }
        /**TO DO - when we split OR and beta, modify this controller to reflect that change!!***/
        if (orRange != "") {
            getLog().debug(orRange);

            addFilterQuery(solrSearchBuilder, "orPerCopyNum", orRange);
            addFilterQuery(solrSearchBuilder, "orType", "true");
        }
        if (betaRange != "") {
            getLog().debug(betaRange);

            addFilterQuery(solrSearchBuilder, "orPerCopyNum", betaRange);
            addFilterQuery(solrSearchBuilder, "orType", "false");
        }
        if (dateRange != "") {
            getLog().debug(dateRange);

            addFilterQuery(solrSearchBuilder, "publicationDate", dateRange);
        }
        if (traits != null) {
            System.out.println(String.valueOf(traits));

            addFilterQuery(solrSearchBuilder, "traitName_s", traits);
        }

        addQuery(solrSearchBuilder, query);

        // dispatch search
        return dispatchSearch(solrSearchBuilder.toString());
    }

    @RequestMapping(value = "api/search/stats", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getCatalogStats() {
        Map<String, Object> response = new HashMap<>();

        String releasedate;
        String studycount;
        String associationcount;
        String genebuild;

        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResource("catalogstats.properties").openStream());
            releasedate = properties.getProperty("releasedate");
            studycount = properties.getProperty("studycount");
            associationcount = properties.getProperty("assocationcount");
            genebuild = properties.getProperty("genomebuild");

            response.put("date", releasedate);
            response.put("studies", studycount);
            response.put("associations", associationcount);
            response.put("genebuild", genebuild);

        }
        catch (IOException e) {
            throw new RuntimeException(
                    "Unable to create dispatcher service: failed to read pubmed.properties resource", e);
        }
        catch (NumberFormatException e) {
            throw new RuntimeException(
                    "Unable to create dispatcher service: you must provide a integer query interval " +
                            "in minutes (pubmed.query.interval.mins)", e);
        }

        return response;
    }

    //TO DO use jackson to read the json and parse it into a string
    private String dispatchSearch(String searchString) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(searchString);
        if (System.getProperty("http.proxyHost") != null) {
            HttpHost proxy;
            if (System.getProperty("http.proxyPort") != null) {
                proxy = new HttpHost(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty
                        ("http.proxyPort")));
            }
            else {
                proxy = new HttpHost(System.getProperty("http.proxyHost"));
            }
            httpGet.setConfig(RequestConfig.custom().setProxy(proxy).build());
        }

        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            getLog().debug("Received HTTP response: " + response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        }

        return "Hello world. Your search results are coming soon to a browser near you.";
    }
}
