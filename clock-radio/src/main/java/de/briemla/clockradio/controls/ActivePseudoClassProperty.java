package de.briemla.clockradio.controls;

import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.Node;

public class ActivePseudoClassProperty extends BooleanPropertyBase {

    private static final PseudoClass ACTIVATED_PSEUDO_CLASS = PseudoClass.getPseudoClass("active");

    private final Node node;

    public ActivePseudoClassProperty(Node node) {
        super();
        this.node = node;
    }

    @Override
    protected void invalidated() {
        node.pseudoClassStateChanged(ACTIVATED_PSEUDO_CLASS, get());
    }

    @Override
    public String getName() {
        return "activated";
    }

    @Override
    public Object getBean() {
        return this;
    }
}