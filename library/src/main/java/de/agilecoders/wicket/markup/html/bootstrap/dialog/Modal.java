package de.agilecoders.wicket.markup.html.bootstrap.dialog;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import java.util.List;

/**
 * The {@code Modal} dialog is a simple component with header,
 * footer and body.
 *
 * @author miha
 * @version 1.0
 */
public class Modal extends Panel {

    private WebMarkupContainer header;
    private boolean show = false;
    private boolean fadein = true;
    private boolean keyboard = true;
    private Label headerLabel;
    private List<Component> buttons = Lists.newArrayList();
    private WebMarkupContainer footer;
    private final IModel<Boolean> useCloseHandler = Model.of(false);
    private AjaxEventBehavior closeBehavior;

    /**
     * Constructor.
     *
     * @param markupId The non-null id of this component
     */
    public Modal(final String markupId) {
        this(markupId, null);
    }

    /**
     * Constructor.
     *
     * @param id    The non-null id of this component
     * @param model The component's body model
     */
    public Modal(String id, IModel<String> model) {
        super(id, model);

        setOutputMarkupId(true);

        footer = new WebMarkupContainer("footer");
        header = new WebMarkupContainer("header");
        headerLabel = new Label("header-label", "");
        header.add(headerLabel);

        footer.add(new ListView<Component>("buttons", buttons) {
            @Override
            protected void populateItem(ListItem<Component> item) {
                item.add(item.getModelObject());
            }
        });

        add(header, footer);
        add(new AssertTagNameBehavior("div"));
        add(new CssClassNameAppender("modal", "hide"));
    }

    /**
     * hook to react on modal close event. The {@link }
     *
     * @param target The current {@link AjaxRequestTarget}
     */
    protected void onClose(final AjaxRequestTarget target) {}

    /**
     * Sets the header label text.
     *
     * @param label The header label
     * @return This
     */
    public Modal header(IModel<String> label) {
        headerLabel.setDefaultModel(label);
        setHeaderVisible(true);
        return this;
    }

    /**
     * Sets the header label text and whether model strings should be escaped.
     *
     * @param label        The header label
     * @param escapeMarkup True is model strings should be escaped
     * @return This
     */
    public Modal header(final IModel<String> label, final boolean escapeMarkup) {
        headerLabel.setDefaultModel(label);
        headerLabel.setEscapeModelStrings(escapeMarkup);
        return this;
    }

    /**
     * Sets whether the footer and any children are visible.
     *
     * @param visible True if footer and any children should be visible
     * @return This
     */
    public Modal setFooterVisible(final boolean visible) {
        footer.setVisible(visible);
        return this;
    }

    /**
     * Sets whether the header and any children are visible.
     *
     * @param visible True if header and any children should be visible
     * @return This
     */
    public Modal setHeaderVisible(final boolean visible) {
        header.setVisible(visible);
        return this;
    }

    /**
     * Sets whether the close handler is used or not. Default is false.
     *
     * @param useCloseHandler True if close handler should be used
     * @return This
     */
    public final Modal setUseCloseHandler(final boolean useCloseHandler) {
        this.useCloseHandler.setObject(useCloseHandler);
        return this;
    }

    /**
     * Sets the initial visibility of the modal dialog.
     *
     * @param show Whether to show the dialog or not
     * @return This
     */
    public Modal show(boolean show) {
        this.show = show;
        return this;
    }

    public Modal appendCloseDialogJavaScript(final AjaxRequestTarget target) {
        target.appendJavaScript(createActionScript(getMarkupId(true), "hide"));
        return this;
    }

    public Modal appendShowDialogJavaScript(final AjaxRequestTarget target) {
        target.appendJavaScript(createActionScript(getMarkupId(true), "show"));
        return this;
    }

    /**
     * creates an action script to open/close the dialog on client side.
     *
     * @param markupId The component's markup id
     * @param action   Possible values: show/hide
     * @return new script.
     */
    protected String createActionScript(final String markupId, final String action) {
        return "$('#" + markupId + "').modal('" + action + "');";
    }

    public Modal addOpenerAttributesTo(final Component component) {
        component.add(new AttributeModifier("data-toggle", "modal"));
        component.add(new AttributeModifier("href", "#" + getMarkupId(true)));
        return this;
    }

    public Modal addCloseButton(final IModel<String> label) {
        ModalCloseButton button = new ModalCloseButton(label);
        button.setAnchor(this);

        return addButton(button);
    }

    public Modal addCloseButton() {
        return addCloseButton(Model.of("Close"));
    }

    public Modal addButton(final Component button) {
        if (!"button".equals(button.getId())) {
            throw new IllegalArgumentException("invalid body markup id. Must be 'body'.");
        }

        buttons.add(button);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (useCloseHandler.getObject()) {
            add(closeBehavior = new AjaxEventBehavior("hidden") {
                @Override
                protected void onEvent(final AjaxRequestTarget target) {
                    handleCloseEvent(target);
                }
            });
        }
    }

    /**
     * handles the close event.
     *
     * @param target The current {@link AjaxRequestTarget}
     */
    private void handleCloseEvent(final AjaxRequestTarget target) {
        if (isVisible()) {
            setVisible(false);
            onClose(target);

            appendCloseDialogJavaScript(target);
            target.add(this);
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (fadein) {
            add(new CssClassNameAppender("fade"));
        }

        if (Strings.isEmpty(headerLabel.getDefaultModelObjectAsString())) {
            // there must be at least on character inside the header to prevent
            // layout problems.
            headerLabel.setDefaultModelObject("&nbsp;");
            headerLabel.setEscapeModelStrings(false);
        }

        footer.setVisible(buttons.size() > 0);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript(createInitializerScript(getMarkupId(true))));
    }

    /**
     * creates the initializer script of the modal dialog.
     *
     * @param markupId The component's markup id
     * @return initializer script
     */
    private String createInitializerScript(final String markupId) {
        return addCloseHandlerScript(markupId, createBasicInitializerScript(markupId));
    }
    
    /**
     * creates the basic initialization script of the modal dialog.
     * Override this to pass in your custom initialization, add event handlers, etc.
     * @see createInitializerScript
     * 
     * @param markupId
     * @return
     */
    protected String createBasicInitializerScript(final String markupId) {
        return "$('#" + markupId + "').modal({keyboard:" + useKeyboard() + ", show:" + showImmediately() + "})";
    }
    
    /**
     * adds close handler to initializer script, if use of close handler has been defined.
     * 
     * @param markupId
     * @param script
     * @return
     */
    private String addCloseHandlerScript(final String markupId, final String script) {
    	if (useCloseHandler.getObject()) {
            return script + ";$('#" + markupId + "').on('hidden', function () { "
                   + "  Wicket.Ajax.ajax({'u':'" + closeBehavior.getCallbackUrl() + "','c':'" + markupId + "'});"
                   + "})";
        }
    	return script;
    }

    /**
     * @return true, if keyboard usage is activated
     */
    protected final boolean useKeyboard() {
        return keyboard;
    }

    /**
     * @return true, if modal dialog should be shown after initialization
     */
    protected final boolean showImmediately() {
        return show;
    }

    /**
     * Whether to fadin/fadeout the modal dialog or not
     *
     * @param fadein true, if dialog should be animated
     * @return This
     */
    public Modal fadein(boolean fadein) {
        this.fadein = fadein;
        return this;
    }

    /**
     * Whether to enable keyboard interaction like ESC to close the dialog.
     *
     * @param keyboard true, if keyboard interaction is enabled
     * @return This
     */
    public Modal keyboard(boolean keyboard) {
        this.keyboard = keyboard;
        return this;
    }
}
