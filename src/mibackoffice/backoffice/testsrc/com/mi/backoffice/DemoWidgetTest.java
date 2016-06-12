package com.mi.backoffice;

import com.google.common.collect.Lists;
import com.hybris.cockpitng.testing.AbstractWidgetUnitTest;
import com.hybris.cockpitng.testing.annotation.DeclaredInput;
import com.hybris.cockpitng.testing.annotation.DeclaredViewEvent;
import com.hybris.cockpitng.testing.annotation.NullSafeWidget;
import com.mi.backoffice.widgets.DemoWidgetController;
import org.junit.Test;
import org.zkoss.zk.ui.event.Events;

/**
 * Step2c: test a widget
 * The base widget test class provide some function for testing a widget. Underlying uses Mockito's mocks
 * *Cover: Sockets, Commands, View events, Global events, null-safe check against all declared inputs
 *
 * - execute*(): trigger the interaction
 * - assertSocketOutput(): test whether the widget answered with a socket event
 * - assertValueSet() / assertValueNotNull(): has the value set on its model
 * *** Limitatioins **
 *   - No possibility to auto-check socket output
 *   - Testing of behavior defined in Java without cockpitng-based annotations, e.g. anonymous classes for UI event handling
 * Created by michelle on 16-6-11.
 */

 /** By default, all widgets are assumed to be null-safe. It means that any valid object, including
 null, may be flawlessly passed as a value of any socket input. If the widget is not null-safe,
  it may be indicated by annotating the test class with @NullSafeWidget(false).

The annotation allows to define a safety level: the values are constant integers declared on
com.hybris.cockpitng.testing.annotation.NullSafeWidget:
ALL: all options described below are used (default value).
DEFAULT_VALUES: declared a null for references, empty collection for collections, default value for
                primitives and so on.
PROXY_VALUES: if the argument is an interface, a dynamic proxy is generated for the argument.
                Any method on the interface may either return the default value of its return type,
                 or throw an exception.
PROXY_EXCEPTIONS: behaves the same way as PROXY_VALUES, but instead of returning a default value,
               it tries to throw the first of the non-runtime exceptions declared on the method called.
*/
@NullSafeWidget(false)
//Describes a single input socket of the tested widget
@DeclaredInput(value = DemoWidgetController.IN_SOCKET, socketType = String.class)
//Describes a single view event of the tested widget
@DeclaredViewEvent(componentID = DemoWidgetController.LAST_SIZE_BUTTON_ID, eventName = Events.ON_CLICK)
public class DemoWidgetTest extends AbstractWidgetUnitTest<DemoWidgetController> {

    private final DemoWidgetController controller = new DemoWidgetController();

    @Override
    protected DemoWidgetController getWidgetController() {

        return controller;
    }

    @Test
    public void testSocketAndViewEvent() {

        executeViewEvent(DemoWidgetController.LAST_SIZE_BUTTON_ID, Events.ON_CLICK);
        assertSocketOutput(DemoWidgetController.SIZE_SOCKET, Integer.valueOf(0));

        executeInputSocketEvent(DemoWidgetController.IN_SOCKET, "dot.separated.string");
        assertSocketOutput(DemoWidgetController.OUT_SOCKET,
                Lists.newArrayList("dot", "separated", "string"));
        assertValueSet(DemoWidgetController.LAST_RESULT_SIZE, Integer.valueOf(3));

        executeViewEvent(DemoWidgetController.LAST_SIZE_BUTTON_ID, Events.ON_CLICK);
        assertSocketOutput(DemoWidgetController.SIZE_SOCKET, Integer.valueOf(3));
    }
}
