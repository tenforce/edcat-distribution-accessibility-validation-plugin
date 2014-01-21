package eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.hooks;

import eu.lod2.edcat.dereferencing.distributionAccessibiltyValidationPlugin.contexts.AfterAccessibilityValidationContext;
import eu.lod2.hooks.handlers.HookHandler;

/**
 * Hook which is called after the accessibility of a dataset has been verified.
 */
public interface AfterAccessibilityValidationHookHandler extends HookHandler {

  /**
   * Called after the accessibility is validated.
   *
   * @param context All information available for this handler for the validation.
   */
  @SuppressWarnings( "UnusedDeclaration" )
  public void handleBeforeAccessibilityValidation( AfterAccessibilityValidationContext context );
}
