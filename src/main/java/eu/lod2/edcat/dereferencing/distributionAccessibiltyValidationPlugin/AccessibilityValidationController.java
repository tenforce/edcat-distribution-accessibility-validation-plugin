package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin;

import eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts.AfterAccessibilityValidationContext;
import eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts.BeforeAccessibilityValidationContext;
import eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.AfterAccessibilityValidationHookHandler;
import eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.BeforeAccessibilityValidationHookHandler;
import eu.lod2.edcat.utils.Catalog;
import eu.lod2.edcat.utils.SparqlEngine;
import eu.lod2.hooks.util.HookManager;
import org.openrdf.model.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Publishes a route which informs the user about the accessibility of a specified DataSet.
 */
@Controller
public class AccessibilityValidationController {

  // GET /datasets/id/validate-accessibility
  @RequestMapping( value = "datasets/{datasetId}/validate-accessibility", method = RequestMethod.GET, produces = "application/json;charset=UTF-8" )
  public ResponseEntity<Object> update( @PathVariable String datasetId ) throws Throwable {
    SparqlEngine engine = new SparqlEngine();
    Catalog catalog = new Catalog( engine, eu.lod2.edcat.utils.Constants.getURIBase() );
    URI datasetUri = catalog.generateDatasetUri( datasetId );

    HookManager.callHook( BeforeAccessibilityValidationHookHandler.class,
      "handleBeforeAccessibilityValidation",
      new BeforeAccessibilityValidationContext( engine, catalog ) );

    DatasetAccessibility accessibility = Validator.dereferenceDataset( datasetUri, engine );
    Object jsonBody = jsonifyAccessibilities( accessibility );

    ResponseEntity<Object> response = new ResponseEntity<Object>( jsonBody, new HttpHeaders(), HttpStatus.OK );
    HookManager.callHook( AfterAccessibilityValidationHookHandler.class,
      "handleAfterAccessibilityValidation",
      new AfterAccessibilityValidationContext(
        engine, catalog, response
       ) );

    return response;
  }

  /**
   * Converts {@code accessibility} to something which can be used as a json response.
   *
   * @param accessibility Accessibility to be translated.
   * @return Object which can be used in the body of the response.
   */
  private static Object jsonifyAccessibilities( DatasetAccessibility accessibility){
    List<Map<String,String>> innerObjects = new ArrayList<Map<String, String>>();
    for(DistributionAccessibility da : accessibility.getDistributionAccessibilities()){
      Map<String,String> innerObject = new HashMap<String, String>();
      String accessibilityName;
      switch(da.getAccessibility()){
        case ACCESSIBLE:
          accessibilityName = "Accessible";
          break;
        case INACCESSIBLE:
          accessibilityName = "Inaccessible";
          break;
        case UNCHECKED:
          accessibilityName = "Unchecked";
          break;
        default:
          accessibilityName = "Unknown";
      }
      innerObject.put( da.getDistributionUri().stringValue() , accessibilityName );
      innerObjects.add( innerObject );
    }

    Map<String,Object> result = new HashMap<String, Object>();
    result.put("Dataset", accessibility.getDatasetUri().stringValue());
    result.put("Accessibilities" , innerObjects);
    return result;
  }
}
