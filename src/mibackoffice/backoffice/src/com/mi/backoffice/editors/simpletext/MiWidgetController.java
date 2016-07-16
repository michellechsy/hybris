package com.mi.backoffice.editors.simpletext;

import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.components.Editor;
import com.hybris.cockpitng.util.DefaultWidgetController;

/** step2d: handle events in widget controller
 * Created by michelle on 16-7-16.
 */
public class MiWidgetController extends DefaultWidgetController {

    // same name as the id in the widgetid.zul
    private Editor textEditor;

    // register the callback method
    @ViewEvent(componentID = "textEditor", eventName = "onValueChanged")
    public void miCallback() {
        final String tmp = (String) textEditor.getValue();
        // do something with the value
    }

}
