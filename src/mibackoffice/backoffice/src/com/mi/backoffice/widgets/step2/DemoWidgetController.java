package com.mi.backoffice.widgets.step2;

import com.google.common.collect.Lists;
import com.hybris.cockpitng.annotations.SocketEvent;
import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.util.DefaultWidgetController;
import org.zkoss.zk.ui.event.Events;

/**
 * Step2b: Test a widget
 * Created by michelle on 16-6-11.
 */
public class DemoWidgetController extends DefaultWidgetController {
    public static final String LAST_SIZE_BUTTON_ID = "lastSizeButton";
    public static final String SIZE_SOCKET = "size";
    public static final String OUT_SOCKET = "out";
    public static final String IN_SOCKET = "in";
    public static final String LAST_RESULT_SIZE = "lastResultSize";

    @SocketEvent(socketId = IN_SOCKET)
    public void inSocket(final String input)
    {
        final String[] result = input.split("\\.");
        setValue(LAST_RESULT_SIZE, Integer.valueOf(result.length));
        sendOutput(OUT_SOCKET, Lists.newArrayList(result));
    }

    @ViewEvent(componentID = LAST_SIZE_BUTTON_ID, eventName = Events.ON_CLICK)
    public void sizeClicked()
    {
        final Integer size = getValue(LAST_RESULT_SIZE, Integer.class);
        sendOutput(SIZE_SOCKET, size == null ? Integer.valueOf(0) : size);
    }
}
