package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks;

import eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts.BeforeAccessibilityValidationContext;
import eu.lod2.hooks.handlers.HookHandler;
import eu.lod2.hooks.util.ActionAbortException;

/**
 * Hook which is called before the accessibility of a Dataset is verified.
 */
public interface BeforeAccessibilityValidationHookHandler extends HookHandler {

  /**
   * Called before the accessibility is validated.
   *
   * @param context All information available for this handler for the validation.
   * @throws ActionAbortException Throwing this aborts the validation entirely.
   */
  @SuppressWarnings( "UnusedDeclaration" )
  public void handleBeforeAccessibilityValidation( BeforeAccessibilityValidationContext context )
    throws ActionAbortException;

}
