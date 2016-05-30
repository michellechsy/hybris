package com.mi.backoffice.widgets;

/**
 * Step1d : create the action for search button
 * Created by michelle on 16-5-30.
 */
import com.hybris.cockpitng.annotations.ViewEvent;
import com.mi.backoffice.services.MiSearchService;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import com.hybris.cockpitng.util.DefaultWidgetController;

import java.util.List;

public class MiSearchController extends  DefaultWidgetController {

    // when a search query is typed in the text box, search will be triggered.
    private Textbox searchInput;

    @WireVariable
    private MiSearchService miSearchService;

    @ViewEvent(componentID = "searchBtn", eventName = Events.ON_CLICK)
    public void doSearch() throws InterruptedException {
        List<String> result = miSearchService.search(searchInput.getText());
        Messagebox.show(result.get(0));
    }
}
