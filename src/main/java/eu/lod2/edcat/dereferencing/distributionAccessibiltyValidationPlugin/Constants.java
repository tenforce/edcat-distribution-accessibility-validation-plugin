package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin;

/**
 * Container all constants used in this plugin.
 */
public class Constants {

  /** Enum describing the possibilities regarding accessibility. */
  public enum Accessibility {
    ACCESSIBLE, INACCESSIBLE, UNCHECKED
  }

  /** String of prefixes which may be used in SPARQL queries */
  final public static String SPARQL_PREFIXES = " " +
      "PREFIX : <http://lod2.tenforce.com/edcat/example/config/> \n" +
      "PREFIX edcat: <http://lod2.tenforce.com/edcat/terms/> \n" +
      "PREFIX cterms: <http://lod2.tenforce.com/edcat/terms/config/> \n" +
      "PREFIX catalogs: <http://lod2.tenforce.com/edcat/catalogs/> \n";

}
