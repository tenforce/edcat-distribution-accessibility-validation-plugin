package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin;

import org.openrdf.model.URI;

/**
 * Describes the accessibility of a distribution.
 */
public class DistributionAccessibility {

  /** State of this distribution. */
  Constants.Accessibility accessible;

  /**
   * Returns the state of accessibility of the distribution.
   *
   * @return Object which describes the accessibility state of the distribution.
   */
  public Constants.Accessibility getAccessibility(){
    return accessible;
  }

  /** URI identifying the described distribution. */
  URI distributionUri;

  /**
   * Returns an identifier for the Distribution which is described here.
   *
   * @return URI of the distribution which is described by this instance.
   */
  public URI getDistributionUri() {
    return this.distributionUri;
  }

  /**
   * Simple constructor containing the
   *
   * @param distributionUri URI identifier of the distribution which is described by this accessibility.
   * @param accessibility State of accessibility of the described distribution.
   */
  public DistributionAccessibility( URI distributionUri, Constants.Accessibility accessibility ){
    this.distributionUri = distributionUri;
    this.accessible = accessibility;
  }
}
