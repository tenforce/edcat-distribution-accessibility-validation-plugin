package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts;

import eu.lod2.edcat.model.Catalog;
import org.springframework.http.ResponseEntity;

/**
 * Context for hooks which hook into the {@link eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.BeforeAccessibilityValidationHookHandler}
 * with the available information for that hook.
 *
 * @see eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.BeforeAccessibilityValidationHookHandler
 */
public class AfterAccessibilityValidationContext {

  /** Catalog for which the accessibility is to be verified. */
  private Catalog catalog;
  /** Response object. */
  private ResponseEntity<Object> response;

  /**
   * Simple constructor specifying all accessible values.
   *
   * @param catalog Catalog for which the accessibility is to be verified.
   */
  public AfterAccessibilityValidationContext( Catalog catalog, ResponseEntity<Object> response ){
    this.catalog = catalog;
    this.response = response;
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
