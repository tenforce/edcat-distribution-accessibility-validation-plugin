package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts;

import eu.lod2.edcat.utils.CatalogService;

/**
 * Context for hooks which hook into the {@link eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.AfterAccessibilityValidationHookHandler}
 * with the available information for that hook.
 *
 * @see eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks.AfterAccessibilityValidationHookHandler
 */
public class BeforeAccessibilityValidationContext {

  /**
   * Simple constructor specifying all accessible values.
   *
   * @param catalogService CatalogService for which the accessibility is to be verified.
   */
  public BeforeAccessibilityValidationContext( CatalogService catalogService ){
    this.catalogService = catalogService;
  }

  /** CatalogService for which the accessibility is to be verified. */
  private CatalogService catalogService;

  /**
   * Retrieves the catalogService for which we are verifying the accessibility.
   *
   * @return CatalogService subject to verification.
   */
  @SuppressWarnings( "UnusedDeclaration" )
  public CatalogService getCatalogService(){
    return catalogService;
  }

}
