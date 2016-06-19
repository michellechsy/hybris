package com.mi.backoffice.widgets.step7;

import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.widgets.common.explorertree.ExplorerTreeController;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Messagebox;

/** Step7c: Extend a widget
 * Extend a widget controller
 * Created by michelle on 16-6-19.
 */
public class CustomExplorerTreeController extends ExplorerTreeController {

    @Override
    @ViewEvent(componentID = "hideFilterTextBoxButton", eventName = Events.ON_CLICK)
    public void clickHideButton(final Event event) {
        Messagebox.show("clicked hide button");
        // do sth here
    }
}
