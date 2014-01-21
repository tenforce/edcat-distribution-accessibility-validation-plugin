package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts;

import eu.lod2.edcat.utils.Catalog;
import eu.lod2.edcat.utils.SparqlEngine;

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
   * @param engine Engine which may be used to retrieve more information from the RDF store.
   * @param catalog Catalog for which the accessibility is to be verified.
   */
  public BeforeAccessibilityValidationContext( SparqlEngine engine , Catalog catalog ){
    this.engine = engine;
    this.catalog = catalog;
  }

  /** Connection to the RDF store. */
  private SparqlEngine engine;
  /** Catalog for which the accessibility is to be verified. */
  private Catalog catalog;

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

}
