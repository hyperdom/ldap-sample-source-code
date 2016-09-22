package samplecode.util;

import java.util.*;

/**
 * Static data used by the {@code samplecode} packages.
 */
public final class StaticData
{

  /**
   * provides access to the project resource bundle
   */
  public static final ResourceBundle getResourceBundle()
  {
    if(resourceBundle == null)
    {
      String baseName = getResourceBundleBaseName();
      Locale locale = getDefaultLocale();
      resourceBundle = ResourceBundle.getBundle(baseName,locale);
    }
    return resourceBundle;
  }






  /**
   * The default {@link Locale} when the Locale is not otherwise specified
   */
  public static final Locale getDefaultLocale()
  {
    return StaticData.DEFAULT_LOCALE;
  }






  /**
   * The base name of the resource bundle that contains resource data used by the {@code samplecode}
   * project.
   */
  public static final String getResourceBundleBaseName()
  {
    return StaticData.RESOURCE_BUNDLE_BASE_NAME;
  }






  /**
   * The default {@link Locale} when the Locale is not otherwise specified.
   */
  public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

  /**
   * The base name of the resource bundle that contains resource data used by the {@code samplecode}
   * project.
   */
  public static final String RESOURCE_BUNDLE_BASE_NAME = "samplecode";

  // The resource bundle used by the sample code package
  private static ResourceBundle resourceBundle;






  private StaticData()
  {
    // this block deliberately left empty
  }

}
