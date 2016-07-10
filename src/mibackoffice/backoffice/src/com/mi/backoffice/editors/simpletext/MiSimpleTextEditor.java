package com.mi.backoffice.editors.simpletext;

import com.hybris.cockpitng.editors.CockpitEditorRenderer;
import com.hybris.cockpitng.editors.EditorContext;
import com.hybris.cockpitng.editors.EditorListener;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Textbox;

/**
 * Section2 - Creating and Configuring an Editor
 * Step1b: Creating an Editor, create the Editor view
 * <p>
 * The renderer class of the editor is responsible for creation of the actual view.
 * It is also responsible for setting up the communication of value changes back to the widget
 * using the EditorListener.
 * The class must implement the CockpitEditorRenderer interface.
 *
 * NOTE: Editors do not have access to the Spring application context. use @Resource annotation
 * if you need to inject a Spring bean into the editor.
 */
public class MiSimpleTextEditor implements CockpitEditorRenderer<String> {

    // step1c: add editor parameter (on instance level)
    // extend the editor with a capability to have multiple lines.
    public final static String PARAM_IS_MULTILINE = "is-multiline";
    public final static String PARAM_ROWS = "rows";

    @Override
    public void render(Component parent, EditorContext<String> editorContext,
                       EditorListener<String> editorListener) {

        // create UI component
        final Textbox editorView = new Textbox();

        // ---- step1c: start ----
        if (isMultiline(editorContext)) {
            editorView.setMultiline(true);
            editorView.setRows(getRows(editorContext));
        }
        // ---- step1c: end ----

        // set initial value
        editorView.setValue(editorContext.getInitialValue());

        // set the editable state
        // the EditorContext contains info on whether the editor should be rendered read-only.        if (!editorContext.isEditable()) {
        if (Executions.getCurrent().getUserAgent().contains("MSIE")) {
            editorView.setReadonly(true);
        } else {
            editorView.setDisabled(true);
        }

        // eventListener: passed as a parameter of the render method of our renderer, used to inform
        // the widget about changes of the value this editor holds
        editorView.addEventListener(Events.ON_CHANGE, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                handleEvent(editorView, event, editorListener);
            }
        });
        editorView.addEventListener(Events.ON_OK, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                handleEvent(editorView, event, editorListener);
            }
        });


        // add the UI component to the component tree
        editorView.setParent(parent);

    }

    /**
     * Handle a view event on the editor view component.
     *
     * @param editorView
     *  the view component
     * @param event
     *  the event to be handled
     * @param listener
     *  the editor listener to send change notifications to
     */

    protected void handleEvent(final Textbox editorView, final Event event, final EditorListener<String> listener)
    {
        final String result = (String) editorView.getRawValue();
        listener.onValueChanged(StringUtils.isEmpty(result) ? "" : result);
        if (Events.ON_OK.equals(event.getName()))
        {
            listener.onEditorEvent(EditorListener.ENTER_PRESSED);
        }
    }

    // step1c
    private boolean isMultiline(final EditorContext<String> context)
    {
        final Object isMultiline = context.getParameter(PARAM_IS_MULTILINE);
        if (isMultiline instanceof Boolean)
        {
            return ((Boolean) isMultiline).booleanValue();
        }
        else if (isMultiline instanceof String)
        {
            return BooleanUtils.toBoolean((String) isMultiline);
        }
        return false;
    }

    private int getRows(final EditorContext<String> context)
    {
        int result = 1;
        final Object rows = context.getParameter(PARAM_ROWS);
        if (rows instanceof Integer)
        {
            result = ((Integer) rows).intValue();
        }
        else if (rows instanceof String)
        {
            try
            {
                result = NumberUtils.createInteger(((String) rows));
            }
            catch (final NumberFormatException e)
            {
                result = 1;
            }
        }
        return Math.max(1, result);
    }


}

