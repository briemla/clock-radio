package de.briemla.clockradio;

import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;
import javafx.scene.Node;

public class ActivatedPseudoClassProperty extends BooleanPropertyBase {
	private static final PseudoClass ACTIVATED_PSEUDO_CLASS = PseudoClass.getPseudoClass("activated");

	private final Node node;

	public ActivatedPseudoClassProperty(Node node) {
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