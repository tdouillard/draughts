package org.tdouillard.draughts.web;

import org.tdouillard.draughts.core.PawnColour;

public class ChipColourWrapper {
	private PawnColour cell;

	public ChipColourWrapper(PawnColour colour) {
		this.cell = colour;
	}

	public String getCssColor() {
		if (PawnColour.BLACK == cell) {
			return "black";
		} else if (PawnColour.WHITE == cell) {
			return "red";
		} else {
			return "";
		}
	}

}
