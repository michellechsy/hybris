package com.mi.backoffice.widgets.step3;

import com.hybris.cockpitng.annotations.SocketEvent;
import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.util.DefaultWidgetController;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

/**
 * Step3c: Passing data between widget
 *  Create a controller
 *  Note: while sending data through a socket, it all should be (de)serializable to JSON format,
 *      otherwise,, tests will fail by default.
 * Created by michelle on 16-6-12.
 */
public class MiChatController extends DefaultWidgetController {

    @WireVariable
    private Label lastMsgLabel;
    @WireVariable
    private Textbox msgInput;

    @ViewEvent(componentID = "sendBtn", eventName = Events.ON_CLICK)
    public void sendMsg() {
        sendOutput("outgoingMsg", msgInput.getText());

        //Step4c: Modify a widget
        // access the setting and trigger storing the message in history
       if (this.getWidgetSettings().getBoolean("history")) {
           Messagebox.show("Saved to history"); // assume this is some real logic before this.
       }

    }

    @SocketEvent(socketId = "incomingMsg")
    public void updateTranscript(final String msg) {
        lastMsgLabel.setValue(msg);
    }
}
