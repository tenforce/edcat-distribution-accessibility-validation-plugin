package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts;

import eu.lod2.edcat.utils.Catalog;
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
   * @param catalog Catalog for which the accessibility is to be verified.
   */
  public AfterAccessibilityValidationContext( SparqlEngine engine , Catalog catalog , ResponseEntity<Object> response ){
    this.engine = engine;
    this.catalog = catalog;
    this.response = response;
  }

  /** Connection to the RDF store. */
  private SparqlEngine engine;
  /** Catalog for which the accessibility is to be verified. */
  private Catalog catalog;
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
   * Retrieves the catalog for which we are verifying the accessibility.
   *
   * @return Catalog subject to verification.
   */
  @SuppressWarnings( "UnusedDeclaration" )
  public Catalog getCatalog(){
    return catalog;
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
