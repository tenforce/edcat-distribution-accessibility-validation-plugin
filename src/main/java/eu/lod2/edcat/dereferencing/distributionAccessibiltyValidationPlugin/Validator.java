package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin;

import eu.lod2.edcat.utils.QueryResult;
import eu.lod2.edcat.utils.SparqlEngine;
import eu.lod2.query.Sparql;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the logic for validating whether or not the target of a dataset can be
 * dereferenced.
 */
public class Validator {

  /**
   * Dereferences the dataset with uri {@code datasetURI} and returns true iff it could be
   * dereferenced.
   *
   * @param datasetURI Identifier for the dataset of which we want to validate the accessibility.
   * @return True iff the dataset could be dereferenced.
   */
  public static DatasetAccessibility dereferenceDataset( URI datasetURI, SparqlEngine engine ) throws Throwable {
    DatasetAccessibility da = new DatasetAccessibility( datasetURI );
    // fetch the distributions
    Map<URI, URL> distributions = retrieveDistributions( datasetURI, engine );
    // check their accessibility
    for ( URI key : distributions.keySet() )
      da.add( new DistributionAccessibility( key, accessibilityOfUrl( distributions.get( key ) ) ) );

    return da;
  }

  /**
   * Retrieves the distributions which are contained in the supplied dataset.
   *
   * @param datasetURI Identifier of the DataSet for which we will search the distributions.
   * @param engine     Connection to the database.
   * @return Map with the URI identifier of the distribution as the keys and the corresponding
   * downloadURL as the value.
   */
  private static Map<URI, URL> retrieveDistributions( URI datasetURI, SparqlEngine engine ) {
    // fetch distribution_uri <-> distribution_download_url
    String query = Sparql.query( "" +
      " @PREFIX " +
      " SELECT ?distribution ?accessURL " +
      " FROM $dataset" +
      " WHERE {" +
      "  $dataset dcat:distribution ?distribution." +
      "  ?distribution dcat:accessURL ?accessURL." +
      " }",
      "dataset", datasetURI );

    QueryResult sparqlResults = engine.sparqlSelect( query );

    // translate the results for a nicer interface
    Map<URI, URL> resultMap = new HashMap<URI, URL>();

    for ( Map<String, String> result : sparqlResults )
      try {
        resultMap.put( new URIImpl( result.get( "distribution" ) ), new URL( result.get( "accessURL" ) ) );
      } catch ( MalformedURLException e ) {
        LoggerFactory.getLogger( AccessibilityValidationController.class ).error( "Skipping malformed url " + result.get( "accessURL" ) );
      }

    return resultMap;
  }

  /**
   * Checks the accessibility of a specific URL.
   * <p/>
   * Does not support all protocols, but does its best to identify both HTTP and FTP.
   *
   * @param url Location which ought to be checked.
   * @return The accessibility of the supplied URL:
   * - Accessibility.AVAILABLE if there was a resource at {@code url}.
   * - Accessibility.UNAVAILABLE if there was no resource at {@code url}.
   * - Accessibility.UNCHECKED if {@code url} was not understood.
   */
  private static Constants.Accessibility accessibilityOfUrl( URL url ) {
    String urlProtocol = url.getProtocol().toLowerCase();
    // HTTP connections
    if ( urlProtocol.equals( "http" ) || urlProtocol.equals( "https" ) ) {
      return checkHttpAccessibility( url );
    }
    // FTP connections
    else if ( urlProtocol.equals( "ftp" ) || urlProtocol.equals( "sftp" ) ) {
      return checkFtpAccessibility( url );
    }
    // ANYTHING ELSE
    else {
      return Constants.Accessibility.UNCHECKED;
    }
  }

  /**
   * Checks the accessibility of an HTTP(s) URL.
   *
   * @param url URL for which we want to verify the accessibility.
   * @return The accessibility of the supplied URL:
   * - Accessibility.AVAILABLE if there was a resource at {@code url}.
   * - Accessibility.UNAVAILABLE if there was no resource at {@code url}.
   */
  private static Constants.Accessibility checkHttpAccessibility( URL url ) {
    try {
      HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
      httpCon.setRequestMethod( "HEAD" );
      if ( httpCon.getResponseCode() / 100 == 2 ) {
        if ( httpCon.getResponseCode() != HttpURLConnection.HTTP_OK )
          LoggerFactory.getLogger( AccessibilityValidationController.class )
                       .info( "Accepting HTTP response code " + httpCon.getResponseCode() + " which is not 200." );
        return Constants.Accessibility.ACCESSIBLE;
      } else {
        return Constants.Accessibility.INACCESSIBLE;
      }
    } catch ( IOException e ) {
      return Constants.Accessibility.INACCESSIBLE;
    }
  }

  /**
   * Checks the accessibility of an (s)FTP URL.
   *
   * @param url URL for which we want to verify the accessibility.
   * @return The accessibility of the supplied URL:
   * - Accessibility.AVAILABLE if there was a resource at {@code url}.
   * - Accessibility.UNAVAILABLE if there was no resource at {@code url}.
   */
  private static Constants.Accessibility checkFtpAccessibility( URL url ) {
    try {
      url.openStream(); // throws an IOException if we can't get to the file
      return Constants.Accessibility.ACCESSIBLE;
    } catch ( IOException e ) {
      return Constants.Accessibility.INACCESSIBLE;
    }
  }

}
