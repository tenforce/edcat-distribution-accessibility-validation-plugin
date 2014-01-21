package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin;

import org.openrdf.model.URI;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Describes the accessibility of a dataset.
 */
public class DatasetAccessibility {

  /**
   * Simple constructor for the DatasetAccessibility, given that all of the distribution
   * accessibilities are available at construction time.
   *
   * @param distributionAccessibilities DistributionAccessibility for each of the distributions of
   *                                    the dataset.
   */
  @SuppressWarnings( "UnusedDeclaration" )
  public DatasetAccessibility( URI datasetUri, Collection<DistributionAccessibility> distributionAccessibilities ) {
    this.distributionAccessibilities = new HashSet<DistributionAccessibility>( distributionAccessibilities );
    this.datasetUri = datasetUri;
  }

  /**
   * Constructor for the DatasetAccessibility for which the distributionAccessibilities will be
   * inserted later on.
   *
   * @param datasetUri URI of the dataset which is described by the new DatasetAccessibility.
   */
  public DatasetAccessibility( URI datasetUri ) {
    this.datasetUri = datasetUri;
    this.distributionAccessibilities = new HashSet<DistributionAccessibility>();
  }

  /**
   * URI of the dataset for which this instance describes.
   */
  private URI datasetUri;

  /**
   * Retrieves the identifier for the dataset which is described in this instance.
   *
   * @return URI object identifying the dataset.
   */
  public URI getDatasetUri() {
    return datasetUri;
  }

  /**
   * Set of distribution accessibilities for each distribution of the Dataset.
   */
  private Set<DistributionAccessibility> distributionAccessibilities;

  /**
   * Retrieves the collection of distribution accessibilities for this dataset.
   *
   * @return Collection containing each of the DistributionAccessibility objects.
   */
  public Collection<DistributionAccessibility> getDistributionAccessibilities() {
    return distributionAccessibilities;
  }

  /**
   * Adds a DistributionAccessibility to this DatasetAccessibility
   *
   * @param d DistributionAccessibility to be added.  Duplicates are ignored.
   */
  public void add( DistributionAccessibility d ) {
    this.distributionAccessibilities.add( d );
  }

}
