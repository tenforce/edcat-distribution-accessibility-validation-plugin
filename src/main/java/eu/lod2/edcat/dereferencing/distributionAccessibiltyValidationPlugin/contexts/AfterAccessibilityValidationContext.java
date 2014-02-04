package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts;

import eu.lod2.edcat.utils.CatalogService;
import eu.lod2.edcat.utils.SparqlEngine;
import org.springframework.http.ResponseEntity;

/**
 * Context for hooks which hook into the {@link eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.BeforeAccessibilityValidationHookHandler}
 * with the available information for that hook.
 *
 * @see eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.BeforeAccessibilityValidationHookHandler
 */
public class AfterAccessibilityValidationContext {

  /**
   * Simple constructor specifying all accessible values.
   *
   * @param engine Engine which may be used to retrieve more information from the RDF store.
   * @param catalogService CatalogService for which the accessibility is to be verified.
   */
  public AfterAccessibilityValidationContext( SparqlEngine engine , CatalogService catalogService, ResponseEntity<Object> response ){
    this.engine = engine;
    this.catalogService = catalogService;
    this.response = response;
  }

  /** Connection to the RDF store. */
  private SparqlEngine engine;
  /** CatalogService for which the accessibility is to be verified. */
  private CatalogService catalogService;
  /** Response object. */
  private ResponseEntity<Object> response;

  /**
   * Retrieves the connection to the RDF store.
   *
   * @return SparqlEngine containing a connection to the RDF store.
   */
  @SuppressWarnings( "UnusedDeclaration" )
  public SparqlEngine getEngine(){
    return engine;
  }

  /**
   * Retrieves the catalogService for which we are verifying the accessibility.
   *
   * @return CatalogService subject to verification.
   */
  @SuppressWarnings( "UnusedDeclaration" )
  public CatalogService getCatalogService(){
    return catalogService;
  }

  /**
   * Returns the response for the user.
   *
   * @return ResponseEntity which may be modified to change the user's response.
   */
  @SuppressWarnings( "UnusedDeclaration" )
  public ResponseEntity<Object> getResponse(){
    return response;
  }

}
