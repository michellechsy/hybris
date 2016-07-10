Hybris Backoffice

### Please refer to branches for different source code

### Some main concepts of backoffice
- Widget: A widget tree represents an application
    - widgetslot: it defines a place where exactly one child widget can be placed.
    - widgetchildren: is a place for a list of widgets.
- Editor: a small component that can manage a value of a certain type. The value type is part of the editor definition and part of its interface
- Action: basically a clickable element that can perform an action for a value of a certain type


### Implementation list:
- Creating & Configuring Widgets
    - [x] Step1: Creating a Widget
    - [x] Step2: Testing a Widget
    - [x] Step3: Passing Data Between Widgets
    - [x] Step4: Parametrizing a Widget (define settings)
    - [x] Step5: Creating Composed Widgets
    - [x] Step6: Creating a configurable flow wizard widget
    - [x] Step7: Extending a Widget in the custom backoffice-based app
    - [x] Step8: Creating a reusable widget group for use on separate host machines
    - [x] Step9: Positioning elements in a widget within configuration
        You can set up position in the following widgets:
        - Editor Area widget: position of tabs, sections, panels, and attributes
        - Advanced Search widget: position of fields
        - Collection Browser widget: position of columns
    - [x] Step10: Widget application context
        Registered as a child of the web application context during the deployment.
        A bean of a (backoffice) extension can be overwritten in another one.
- Creating & Configuring Editors
    - [x] Creating an Editor
    - [] Using Editors in Widgets
    - [] Configuring Sets of Actions and Editors
    - [] Nested Editors
    - [] Thumbnails in the Reference Editors
    - [] Creating Socket-Aware Actions and Editors
- Creating & Configuring Actions
    - []