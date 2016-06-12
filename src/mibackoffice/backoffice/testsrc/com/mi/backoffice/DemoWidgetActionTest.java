package com.mi.backoffice;

import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.testing.AbstractActionUnitTest;
import org.junit.Ignore;

/**
 * Testing Actions
 * @link{AbstractActionUnitTest} provide basic actions testing functionality. By default, it checks:
 * - If the implementation of the action provides a constructor without arguments
 *      or a default constructor (no constructor explicity declared)
 * - If the action is null-safe for example, it checks if the implementation can accept
 *      @link{ActionContext} initialized with just <b>null</b> and default values
 *
 * Testing Editor Renderers
The framework provides the AbstractCockpitEditorRendererUnitTest class that can be extended to provide the basic editor renderers testing functionality. By default, the test checks:

If the implementation of the renderer provides a constructor without arguments or a default constructor (no constructor explicitly declared).
Checks the null safety of the renderer in the same way as it is done for the widgets.

 * Created by michelle on 16-6-12.
 */
@Ignore
public class DemoWidgetActionTest extends AbstractActionUnitTest {
    @Override
    public CockpitAction<?, ?> getActionInstance() {
        return null;
    }
}
