package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts;

import eu.lod2.edcat.model.Catalog;

/**
 * Context for hooks which hook into the {@link eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.AfterAccessibilityValidationHookHandler}
 * with the available information for that hook.
 *
 * @see eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.AfterAccessibilityValidationHookHandler
 */
public class BeforeAccessibilityValidationContext {

  /** Catalog for which the accessibility is to be verified. */
  private Catalog catalog;

  /**
   * Simple constructor specifying all accessible values.
   *
   * @param catalog Catalog for which the accessibility is to be verified.
   */
  public BeforeAccessibilityValidationContext( Catalog catalog ){
    this.catalog = this.catalog;
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

}
